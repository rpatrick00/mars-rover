package rover;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {
    private static final String MAX_NOT_NULL_ERROR_MESSAGE =
        String.format("Expected coordinate object for (%s, %s) to not be null", Integer.MAX_VALUE, Integer.MAX_VALUE);
    private static final String MAX_X_ERROR_MESSAGE =
        String.format("Expected X value to be %s", Integer.MAX_VALUE);
    private static final String MAX_Y_ERROR_MESSAGE =
        String.format("Expected Y value to be %s", Integer.MAX_VALUE);

    @Test
    public void testCoordinateBoundaries() {
        Coordinate zeros = new Coordinate(0, 0);
        Assert.assertNotNull("Expected coordinate object for (0, 0) to not be null", zeros);
        Assert.assertEquals("Expected X value to be zero", 0, zeros.getX());
        Assert.assertEquals("Expected Y value to be zero", 0, zeros.getY());

        Coordinate max = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Assert.assertNotNull(MAX_NOT_NULL_ERROR_MESSAGE, max);
        Assert.assertEquals(MAX_X_ERROR_MESSAGE, Integer.MAX_VALUE, max.getX());
        Assert.assertEquals(MAX_Y_ERROR_MESSAGE, Integer.MAX_VALUE, max.getY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeXCoordinates() {
        new Coordinate(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeYCoordinates() {
        new Coordinate(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testXOverflowCondition() {
        new Coordinate(Integer.MAX_VALUE + 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testYOverflowCondition() {
        new Coordinate(0, Integer.MAX_VALUE + 1);
    }

    @Test
    public void testCoordinateEquals() {
        Coordinate first = new Coordinate(3, 5);
        Coordinate second = new Coordinate(5, 3);
        Coordinate third = new Coordinate(3, 5);
        Coordinate fourth = new Coordinate(5, 3);

        Assert.assertEquals("Expected coordinate objects to be equal", first, first);
        Assert.assertNotEquals("Expected coordinate objects to not be equal", first, second);
        Assert.assertEquals("Expected coordinate objects to be equal", first, third);
        Assert.assertEquals("Expected coordinate objects to be equal", second, fourth);
        Assert.assertNotEquals("Expected coordinate objects to not be equal", second, third);
    }
}
