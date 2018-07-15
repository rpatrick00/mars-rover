package rover;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoverTest {

    private Plateau plateau;

    @Before
    public void init() {
        plateau = new Plateau(10, 10);
    }

    @Test
    public void testRoverCreate() {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.N;
        Rover rover = new Rover(position, direction, plateau);
        Assert.assertNotNull("Expected rover to not be null", rover);
    }

    @Test
    public void testRotateLeft() {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.N;
        Rover rover = new Rover(position, direction, plateau);

        direction = rover.rotateLeft();
        Assert.assertEquals("Expected the direction to be West after rotating left", Direction.W, direction);

        direction = rover.rotateLeft();
        Assert.assertEquals("Expected the direction to be South after rotating left", Direction.S, direction);

        direction = rover.rotateLeft();
        Assert.assertEquals("Expected the direction to be East after rotating left", Direction.E, direction);

        direction = rover.rotateLeft();
        Assert.assertEquals("Expected the direction to be North after rotating left", Direction.N, direction);
    }

    @Test
    public void testRotateRight() {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.N;
        Rover rover = new Rover(position, direction, plateau);

        direction = rover.rotateRight();
        Assert.assertEquals("Expected the direction to be East after rotating right", Direction.E, direction);

        direction = rover.rotateRight();
        Assert.assertEquals("Expected the direction to be South after rotating right", Direction.S, direction);

        direction = rover.rotateRight();
        Assert.assertEquals("Expected the direction to be West after rotating right", Direction.W, direction);

        direction = rover.rotateRight();
        Assert.assertEquals("Expected the direction to be North after rotating right", Direction.N, direction);
    }

    @Test
    public void testValidMoveNorth() throws Exception {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.N;
        Rover rover = new Rover(position, direction, plateau);

        Coordinate newPosition = rover.move();
        Assert.assertNotNull("Expected new position not to be null", newPosition);
        Assert.assertEquals("Expected new X position to be 3", 3, newPosition.getX());
        Assert.assertEquals("Expected new Y position to be 6", 6, newPosition.getY());
    }

    @Test
    public void testValidMoveEast() throws Exception {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.E;
        Rover rover = new Rover(position, direction, plateau);

        Coordinate newPosition = rover.move();
        Assert.assertNotNull("Expected new position not to be null", newPosition);
        Assert.assertEquals("Expected new X position to be 4", 4, newPosition.getX());
        Assert.assertEquals("Expected new Y position to be 5", 5, newPosition.getY());
    }

    @Test
    public void testValidMoveSouth() throws Exception {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.S;
        Rover rover = new Rover(position, direction, plateau);

        Coordinate newPosition = rover.move();
        Assert.assertNotNull("Expected new position not to be null", newPosition);
        Assert.assertEquals("Expected new X position to be 3", 3, newPosition.getX());
        Assert.assertEquals("Expected new Y position to be 4", 4, newPosition.getY());
    }

    @Test
    public void testValidMoveWest() throws Exception {
        Coordinate position = new Coordinate(3, 5);
        Direction direction = Direction.W;
        Rover rover = new Rover(position, direction, plateau);

        Coordinate newPosition = rover.move();
        Assert.assertNotNull("Expected new position not to be null", newPosition);
        Assert.assertEquals("Expected new X position to be 2", 2, newPosition.getX());
        Assert.assertEquals("Expected new Y position to be 5", 5, newPosition.getY());
    }

    @Test(expected = OffPlateauException.class)
    public void testInvalidMoveWest() throws Exception {
        Coordinate position = new Coordinate(0, 5);
        Direction direction = Direction.W;
        Rover rover = new Rover(position, direction, plateau);

        rover.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving West when X was equal to zero");
    }

    @Test(expected = OffPlateauException.class)
    public void testInvalidMoveSouth() throws Exception {
        Coordinate position = new Coordinate(3, 0);
        Direction direction = Direction.S;
        Rover rover = new Rover(position, direction, plateau);

        rover.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving South when Y was equal to zero");
    }

    @Test(expected = OffPlateauException.class)
    public void testInvalidMoveEast() throws Exception {
        Coordinate position = new Coordinate(10, 10);
        Direction direction = Direction.E;
        Rover rover = new Rover(position, direction, plateau);

        rover.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving East when X was equal to ten");
    }

    @Test(expected = OffPlateauException.class)
    public void testInvalidMoveNorth() throws Exception {
        Coordinate position = new Coordinate(10, 10);
        Direction direction = Direction.N;
        Rover rover = new Rover(position, direction, plateau);

        rover.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving North when Y was equal to ten");
    }

    @Test
    public void testRoverOutput() {
        Coordinate position = new Coordinate(9, 9);
        Direction direction = Direction.N;
        Rover rover = new Rover(position, direction, plateau);

        Assert.assertEquals("Expected rover output of \"9 9 N\"", "9 9 N", rover.toString());
    }
}
