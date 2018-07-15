package rover;

import org.junit.Assert;
import org.junit.Test;

public class PlateauTest {

    @Test
    public void testMultipleRoverHappyPath() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = plateau.addRover(1, 2, Direction.N);
        rover1.rotateLeft();
        rover1.move();
        rover1.rotateLeft();
        rover1.move();
        rover1.rotateLeft();
        rover1.move();
        rover1.rotateLeft();
        rover1.move();
        rover1.move();
        Assert.assertEquals("Expected rover1 X position to be 1", 1, rover1.getCurrentPosition().getX());
        Assert.assertEquals("Expected rover1 Y position to be 3", 3, rover1.getCurrentPosition().getY());
        Assert.assertEquals("Expected rover1 direction to be N", Direction.N, rover1.getCurrentDirection());


        Rover rover2 = plateau.addRover(3, 3, Direction.E);
        rover2.move();
        rover2.move();
        rover2.rotateRight();
        rover2.move();
        rover2.move();
        rover2.rotateRight();
        rover2.move();
        rover2.rotateRight();
        rover2.rotateRight();
        rover2.move();
        Assert.assertEquals("Expected rover2 X position to be 1", 5, rover2.getCurrentPosition().getX());
        Assert.assertEquals("Expected rover2 Y position to be 3", 1, rover2.getCurrentPosition().getY());
        Assert.assertEquals("Expected rover2 direction to be E", Direction.E, rover2.getCurrentDirection());
    }

    @Test(expected = SpaceAlreadyOccupiedException.class)
    public void testAddRoverCollision() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        plateau.addRover(1, 2, Direction.N);
        plateau.addRover(1, 2, Direction.N);
    }

    @Test(expected = SpaceAlreadyOccupiedException.class)
    public void testMoveRoverCollision() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = plateau.addRover(1, 2, Direction.N);
        plateau.addRover(1, 3, Direction.E);
        rover1.move();
    }

    @Test(expected = OffPlateauException.class)
    public void testMoveLeftBoundaryExceeded() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = plateau.addRover(0, 2, Direction.W);
        rover1.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving West when X was equal to zero");
    }

    @Test(expected = OffPlateauException.class)
    public void testMoveRightBoundaryExceeded() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = plateau.addRover(5, 2, Direction.E);
        rover1.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving East when X was equal to five");
    }

    @Test(expected = OffPlateauException.class)
    public void testMoveBottomBoundaryExceeded() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = plateau.addRover(0, 0, Direction.S);
        rover1.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving South when Y was equal to zero");
    }

    @Test(expected = OffPlateauException.class)
    public void testMoveTopBoundaryExceeded() throws Exception {
        Plateau plateau = new Plateau(5, 5);
        Rover rover1 = plateau.addRover(0, 5, Direction.N);
        rover1.move();
        Assert.fail("Expected the OffPlateauException to be thrown when moving North when Y was equal to five");
    }
}
