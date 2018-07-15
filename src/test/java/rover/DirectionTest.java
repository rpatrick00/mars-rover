package rover;

import org.junit.Assert;
import org.junit.Test;
import rover.Direction;

public class DirectionTest {

    @Test
    public void testNorthLeftTurn() {
        Direction north = Direction.N;
        Direction leftTurn = north.left();
        Assert.assertEquals("Expected turning left from facing north to return west", leftTurn, Direction.W);
    }

    @Test
    public void testNorthRightTurn() {
        Direction north = Direction.N;
        Direction rightTurn = north.right();
        Assert.assertEquals("Expected turning right from facing north to return east", rightTurn, Direction.E);
    }

    @Test
    public void testEastLeftTurn() {
        Direction east = Direction.E;
        Direction leftTurn = east.left();
        Assert.assertEquals("Expected turning left from facing east to return north", leftTurn, Direction.N);
    }

    @Test
    public void testEastRightTurn() {
        Direction east = Direction.E;
        Direction rightTurn = east.right();
        Assert.assertEquals("Expected turning right from facing east to return south", rightTurn, Direction.S);
    }

    @Test
    public void testSouthLeftTurn() {
        Direction south = Direction.S;
        Direction leftTurn = south.left();
        Assert.assertEquals("Expected turning left from facing south to return east", leftTurn, Direction.E);
    }

    @Test
    public void testSouthRightTurn() {
        Direction south = Direction.S;
        Direction rightTurn = south.right();
        Assert.assertEquals("Expected turning right from facing south to return west", rightTurn, Direction.W);
    }

    @Test
    public void testWestLeftTurn() {
        Direction west = Direction.W;
        Direction leftTurn = west.left();
        Assert.assertEquals("Expected turning left from facing west to return south", leftTurn, Direction.S);
    }

    @Test
    public void testWestRightTurn() {
        Direction west = Direction.W;
        Direction rightTurn = west.right();
        Assert.assertEquals("Expected turning right from facing west to return north", rightTurn, Direction.N);
    }

    @Test
    public void testMoveNorth() {
        int x = Direction.N.getXAxisIncrement();
        int y = Direction.N.getYAxisIncrement();
        
        Assert.assertEquals("Excepted moving north to leave the X coordinate unchanged", 0, x);
        Assert.assertEquals("Excepted moving north to increment the Y coordinate by 1", 1, y);
    }

    @Test
    public void testMoveEast() {
        int x = Direction.E.getXAxisIncrement();
        int y = Direction.E.getYAxisIncrement();

        Assert.assertEquals("Excepted moving east to increment the X coordinate by 1", 1, x);
        Assert.assertEquals("Excepted moving east to leave the Y coordinate unchanged", 0, y);
    }

    @Test
    public void testMoveSouth() {
        int x = Direction.S.getXAxisIncrement();
        int y = Direction.S.getYAxisIncrement();

        Assert.assertEquals("Excepted moving south to leave the X coordinate unchanged", 0, x);
        Assert.assertEquals("Excepted moving south to decrement the Y coordinate by 1", -1, y);
    }

    @Test
    public void testMoveWest() {
        int x = Direction.W.getXAxisIncrement();
        int y = Direction.W.getYAxisIncrement();

        Assert.assertEquals("Excepted moving west to decrement the X coordinate by 1", -1, x);
        Assert.assertEquals("Excepted moving west to leave the Y coordinate unchanged", 0, y);
    }
}
