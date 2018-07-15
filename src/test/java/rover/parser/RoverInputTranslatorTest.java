package rover.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import rover.Command;
import rover.Direction;

public class RoverInputTranslatorTest {
    private static final String VALID_INPUT_FILE_NAME = "src/test/resources/rover/parser/ValidInputFile.txt";
    private static final String INVALID_INPUT_FILE_NAME = "src/test/resources/rover/parser/InvalidInputFile.txt";


    @Test
    public void testValidInputFile() throws Exception {
        RoverInputTranslator translator = new RoverInputTranslator(VALID_INPUT_FILE_NAME);
        RoverInputData result = translator.parse();

        Assert.assertNotNull("Expected parse result to not be null", result);
        Assert.assertEquals("Expected plateau X value to be 5", 5, result.getXTopRight());
        Assert.assertEquals("Expected plateau Y value to be 5", 5, result.getYTopRight());

        List<RoverInputData.RoverData> roverDataList = result.getRoverData();
        Assert.assertNotNull("Excepted rover data list to not be null", roverDataList);
        Assert.assertEquals("Expected rover data list to contain 2 elements", 2, roverDataList.size());

        RoverInputData.RoverData roverData = roverDataList.get(0);
        Assert.assertNotNull("Excepted first rover data to not be null", roverData);
        Assert.assertEquals("Expected first rover X value to be 1", 1, roverData.getX());
        Assert.assertEquals("Expected first rover Y value to be 2", 2, roverData.getY());
        Assert.assertEquals("Expected first rover direction to be N", Direction.N, roverData.getDirection());

        List<Command> commandList = roverData.getCommands();
        Assert.assertNotNull("Excepted first rover command list to not be null", commandList);
        Assert.assertEquals("Expected first rover to have 9 commands", 9, commandList.size());
        Assert.assertEquals("Expected first rover command number 1 to be L", Command.L, commandList.get(0));
        Assert.assertEquals("Expected first rover command number 2 to be M", Command.M, commandList.get(1));
        Assert.assertEquals("Expected first rover command number 3 to be L", Command.L, commandList.get(2));
        Assert.assertEquals("Expected first rover command number 4 to be M", Command.M, commandList.get(3));
        Assert.assertEquals("Expected first rover command number 5 to be L", Command.L, commandList.get(4));
        Assert.assertEquals("Expected first rover command number 6 to be M", Command.M, commandList.get(5));
        Assert.assertEquals("Expected first rover command number 7 to be L", Command.L, commandList.get(6));
        Assert.assertEquals("Expected first rover command number 8 to be M", Command.M, commandList.get(7));
        Assert.assertEquals("Expected first rover command number 9 to be M", Command.M, commandList.get(8));

        roverData = roverDataList.get(1);
        Assert.assertNotNull("Excepted second rover data to not be null", roverData);
        Assert.assertEquals("Expected second rover X value to be 3", 3, roverData.getX());
        Assert.assertEquals("Expected second rover Y value to be 3", 3, roverData.getY());
        Assert.assertEquals("Expected second rover direction to be E", Direction.E, roverData.getDirection());

        commandList = roverData.getCommands();
        Assert.assertNotNull("Excepted second rover command list to not be null", commandList);
        Assert.assertEquals("Expected second rover to have 10 commands", 10, commandList.size());
        Assert.assertEquals("Expected second rover command number 1 to be M", Command.M, commandList.get(0));
        Assert.assertEquals("Expected second rover command number 2 to be M", Command.M, commandList.get(1));
        Assert.assertEquals("Expected second rover command number 3 to be R", Command.R, commandList.get(2));
        Assert.assertEquals("Expected second rover command number 4 to be M", Command.M, commandList.get(3));
        Assert.assertEquals("Expected second rover command number 5 to be M", Command.M, commandList.get(4));
        Assert.assertEquals("Expected second rover command number 6 to be R", Command.R, commandList.get(5));
        Assert.assertEquals("Expected second rover command number 7 to be M", Command.M, commandList.get(6));
        Assert.assertEquals("Expected second rover command number 8 to be R", Command.R, commandList.get(7));
        Assert.assertEquals("Expected second rover command number 9 to be R", Command.R, commandList.get(8));
        Assert.assertEquals("Expected second rover command number 10 to be M", Command.M, commandList.get(9));
    }

    @Test(expected = ParserException.class)
    public void testInvalidInputFile() throws Exception {
        RoverInputTranslator translator = new RoverInputTranslator(INVALID_INPUT_FILE_NAME);
        RoverInputData result = translator.parse();
    }
}
