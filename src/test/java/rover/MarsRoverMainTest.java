package rover;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MarsRoverMainTest {
    private static final String MAIN_TEST_FILE_NAME = "src/test/resources/rover/MainTestInputFile.txt";
    private static final String MAIN_ROVER1_RESULT = "5 2 S";
    private static final String MAIN_ROVER2_RESULT = "5 6 N";
    private static final String MAIN_ROVER3_RESULT = "1 7 W";
    private static final String MAIN_ROVER4_RESULT = "8 10 N";
    private static final String MAIN_ROVER5_RESULT = "7 10 N";

    private static final String OFF_PLATEAU_TEST_FILE_NAME = "src/test/resources/rover/OffPlateauInputFile.txt";
    private static final String ADD_COLLISION_TEST_FILE_NAME = "src/test/resources/rover/AddCollisionInputFile.txt";
    private static final String MOVE_COLLISION_TEST_FILE_NAME = "src/test/resources/rover/MoveCollisionInputFile.txt";

    @Test
    public void testMain() throws Exception {
        MarsRoverMain me = new MarsRoverMain(MAIN_TEST_FILE_NAME);
        List<String> results = me.execute();
        Assert.assertNotNull("Expected rover results not to be null", results);
        Assert.assertEquals("Expected rover results to have 5 entries", 5, results.size());

        Assert.assertEquals("Expected rover end location to be " + MAIN_ROVER1_RESULT,
                            MAIN_ROVER1_RESULT, results.get(0));
        Assert.assertEquals("Expected rover end location to be " + MAIN_ROVER2_RESULT,
                            MAIN_ROVER2_RESULT, results.get(1));
        Assert.assertEquals("Expected rover end location to be " + MAIN_ROVER3_RESULT,
                            MAIN_ROVER3_RESULT, results.get(2));
        Assert.assertEquals("Expected rover end location to be " + MAIN_ROVER4_RESULT,
                            MAIN_ROVER4_RESULT, results.get(3));
        Assert.assertEquals("Expected rover end location to be " + MAIN_ROVER5_RESULT,
                            MAIN_ROVER5_RESULT, results.get(4));
    }

    @Test(expected = OffPlateauException.class)
    public void testOffPlateau() throws Exception {
        MarsRoverMain me = new MarsRoverMain(OFF_PLATEAU_TEST_FILE_NAME);
        me.execute();
    }

    @Test(expected = SpaceAlreadyOccupiedException.class)
    public void testAddCollision() throws Exception {
        MarsRoverMain me = new MarsRoverMain(ADD_COLLISION_TEST_FILE_NAME);
        me.execute();
    }

    @Test(expected = SpaceAlreadyOccupiedException.class)
    public void testMoveCollision() throws Exception {
        MarsRoverMain me = new MarsRoverMain(MOVE_COLLISION_TEST_FILE_NAME);
        me.execute();
    }
}
