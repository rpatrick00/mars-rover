package rover.parser;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Interval;

/**
 * This class implements the Antlr 4 DiagnosticErrorListener interface to capture parsing errors and warnings.
 */
public class RoverInputParserErrorListener extends DiagnosticErrorListener {

    private String fileName;
    private List<String> errors;
    private List<String> warnings;

    /**
     * The constructor.
     *
     * @param fileName the name of the input file being parsed
     * @param exactOnly whether or not the listener should only report exact ambiguities or not
     */
    public RoverInputParserErrorListener(String fileName, boolean exactOnly) {
        super(exactOnly);
        this.fileName = fileName;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    /**
     * Get the error count associated with this parse.
     *
     * @return the error count
     */
    public int getErrorCount() {
        return errors.size();
    }

    /**
     * Get the warning count associated with this parse.
     *
     * @return the warning count
     */
    public int getWarningCount() {
        return warnings.size();
    }

    /**
     * Get the error messages associated with this parse.
     *
     * @return the list of errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Get the warning messages associated with this parse.
     *
     * @return the list of warnings
     */
    public List<String> getWarnings() {
        return warnings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e) {
        String message = MessageFormat.format("Parse error for file {0} at line {1} position {2}: {3}",
                                              e, fileName, line, charPositionInLine, msg);
        errors.add(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex,
                                int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {

        if (exactOnly && !exact) {
            return;
        }

        String decision = getDecisionDescription(recognizer, dfa);
        BitSet conflictingAlts = getConflictingAlts(ambigAlts, configs);
        String text = recognizer.getTokenStream().getText(Interval.of(startIndex, stopIndex));
        String message =
            MessageFormat.format("Parser reported ambiguous decision {0} from alternatives {1} while parsing \"{2}\" from file {3}",
                                 decision, conflictingAlts, text, fileName);
        warnings.add(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex,
                                            int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {

        String decision = getDecisionDescription(recognizer, dfa);
        String text = recognizer.getTokenStream().getText(Interval.of(startIndex, stopIndex));
        String message =
            MessageFormat.format("Parser reported attempting full context decision {0} on input \"{1}\" while parsing file {2}",
                                 decision, text, fileName);
        warnings.add(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex,
                                         int stopIndex, int prediction, ATNConfigSet configs) {

        String decision = getDecisionDescription(recognizer, dfa);
        String text = recognizer.getTokenStream().getText(Interval.of(startIndex, stopIndex));
        String message =
            MessageFormat.format("Parser reported context sensitivity decision {0} on input \"{1}\" while parsing file {2}",
                                 decision, text, fileName);
        warnings.add(message);
    }
}
