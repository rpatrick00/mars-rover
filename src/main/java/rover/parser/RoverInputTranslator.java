package rover.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import rover.Command;

/**
 * This class presents the public interface to the input file parser.
 */
public class RoverInputTranslator extends RoverInputBaseListener {

    private File inputFile;
    private RoverInputData result;
    private RoverInputData.RoverData currentRoverData;

    /**
     * The constructor.
     *
     * @param fileName the name of the file to be parsed.
     * @throws IllegalArgumentException if the file name is not a valid, existing file
     */
    public RoverInputTranslator(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Input file name must not be empty");
        }

        File file = new File(fileName).getAbsoluteFile();
        if (file.isDirectory()) {
            throw new IllegalArgumentException("Input file name must not be a directory: " + file.getAbsolutePath());
        } else if (!file.exists()) {
            throw new IllegalArgumentException("Input file name must be an existing file: " + file.getAbsolutePath());
        }
        this.inputFile = file;
    }

    /**
     * Entering the size grammar element callback.
     *
     * @param ctx the parser context
     */
    @Override
    public void enterSize(RoverInputParser.SizeContext ctx) {
        int x = Integer.valueOf(ctx.INTEGER(0).getText());
        int y = Integer.valueOf(ctx.INTEGER(1).getText());
        result = new RoverInputData(x, y);
    }

    /**
     * Entering the start grammar element callback.
     *
     * @param ctx the parser context
     */
    @Override
    public void enterStart(RoverInputParser.StartContext ctx) {
        int x = Integer.valueOf(ctx.INTEGER(0).getText());
        int y = Integer.valueOf(ctx.INTEGER(1).getText());
        String directionName = ctx.DIRECTION().getText();
        currentRoverData = new RoverInputData.RoverData(x, y, directionName);
    }

    /**
     * Entering the commands grammar element callback.
     *
     * @param ctx the parser context
     */
    @Override
    public void enterCommands(RoverInputParser.CommandsContext ctx) {
        List<TerminalNode> commandNodes = ctx.COMMAND();
        if (commandNodes != null) {
            List<Command> commands = new ArrayList<>(commandNodes.size());
            for (TerminalNode commandNode : commandNodes) {
                commands.add(Command.valueOf(commandNode.getText()));
            }
            currentRoverData.setCommands(commands);
        }
        result.addRoverData(currentRoverData);
    }

    /**
     * Parse the file for this translator.
     *
     * @return the data object containing the parsed data
     * @throws ParserException if an error occurs during parsing
     */
    public RoverInputData parse() throws ParserException {
        RoverInputParserErrorListener errorListener =
            new RoverInputParserErrorListener(inputFile.getAbsolutePath(), false);
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            CharStream input = CharStreams.fromStream(fis);
            RoverInputLexer lexer = new RoverInputLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RoverInputParser parser = new RoverInputParser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);

            ParseTree tree = parser.input();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(this, tree);
        } catch (IOException ioe) {
            String message = String.format("Error while parsing %s: %s", inputFile.getAbsolutePath(), ioe.getMessage());
            throw new ParserException(message);
        }

        // TODO - Should be writing to a logging system...
        int warningCount = errorListener.getWarningCount();
        if (warningCount > 0) {
            System.err.printf("Parser returned %d warning(s):\n", warningCount);
            int count = 0;
            for (String warning : errorListener.getWarnings()) {
                System.err.printf("\t%d.) %s\n", ++count, warning);
            }
        }

        int errorCount = errorListener.getErrorCount();
        if (errorCount > 0) {
            System.err.printf("Parser returned %d error(s):\n", errorCount);
            int count = 0;
            for (String error : errorListener.getErrors()) {
                System.err.printf("\t%d.) %s\n", ++count, error);
            }
            String message = String.format("The parser encountered %s errors while parsing %s",
                                           errorCount, inputFile.getAbsolutePath());
            throw new ParserException(message);
        }
        return result;
    }
}
