package rover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rover.parser.ParserException;
import rover.parser.RoverInputData;
import rover.parser.RoverInputTranslator;

/**
 * The main class for running the Mars Rover program.
 */
public final class MarsRoverMain {
    private static final String APP_NAME = "java -jar mars-rover.jar";
    private static final String HELP_HEADER = "";
    private static final String HELP_FOOTER = "";

    private String fileName;

    MarsRoverMain(String fileName) {
        this.fileName = fileName;
    }

    /**
     * The command-line application entry point.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Options options = getCommandLineOptions();
        if (args.length == 0) {
            printHelp(options);
            System.exit(0);
        }

        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = cmdLineParser.parse(options, args);
        } catch (ParseException pe) {
            System.err.printf("ERROR: Unable to parse command-line arguments %s: %s\n", Arrays.toString(args), pe.getMessage());
            printHelp(options);
            System.exit(1);
        }


        String fileName = cmdLine.getOptionValue("f");
        MarsRoverMain me = new MarsRoverMain(fileName);
        List<String> results = null;
        try {
            results = me.execute();
        } catch (IllegalArgumentException iae) {
            System.err.printf("ERROR: Unable to process input file \"%s\": %s\n", fileName, iae.getMessage());
            System.exit(1);
        } catch (ParserException pe) {
            System.err.printf("ERROR: Parsing input file \"%s\" failed: %s", fileName, pe.getMessage());
            System.exit(1);
        } catch (SpaceAlreadyOccupiedException se) {
            System.err.println("ERROR: Rover ran into another rover: " + se.getMessage());
            System.exit(1);
        } catch (OffPlateauException oe) {
            System.err.println("ERROR: Rover ran off the edge of the plateau: " + oe.getMessage());
            System.exit(1);
        } catch (RuntimeException re) {
            System.err.println("ERROR: Unexpected error while moving the rovers: " + re.getMessage());
            re.printStackTrace();
            System.exit(1);
        }

        for (String result : results) {
            System.out.println(result);
        }
    }

    private static void printHelp(Options options) {
        System.out.println();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(APP_NAME, HELP_HEADER, options, HELP_FOOTER, true);
    }

    private static Options getCommandLineOptions() {
        Options options = new Options();

        Option fileOption = Option.builder("f")
                                .desc("the input file name")
                                .required()
                                .longOpt("input-file")
                                .hasArg()
                                .argName("INPUT_FILE").build();
        options.addOption(fileOption);
        return options;
    }

    List<String> execute() throws OffPlateauException, ParserException, SpaceAlreadyOccupiedException {
        RoverInputTranslator translator = new RoverInputTranslator(fileName);
        RoverInputData inputData = translator.parse();

        List<String> roversFinalLocations = new ArrayList<>();
        Plateau plateau = new Plateau(inputData.getXTopRight(), inputData.getYTopRight());
        List<RoverInputData.RoverData> roverDataList = inputData.getRoverData();
        for (RoverInputData.RoverData roverData : roverDataList) {
            int x = roverData.getX();
            int y = roverData.getY();
            Direction heading = roverData.getDirection();
            Rover rover = plateau.addRover(x, y, heading);

            List<Command> roverCommandList = roverData.getCommands();
            for (Command roverCommand : roverCommandList) {
                switch (roverCommand) {
                    case L:
                        rover.rotateLeft();
                        break;

                    case R:
                        rover.rotateRight();
                        break;

                    case M:
                        rover.move();
                        break;
                }
            }
            roversFinalLocations.add(rover.toString());
        }
        return roversFinalLocations;
    }
}
