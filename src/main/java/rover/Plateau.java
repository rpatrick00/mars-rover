package rover;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing the plateau.
 */
public class Plateau {
    private static final String OFF_PLATEAU_ERROR_MESSAGE_TEMPLATE =
        "The coordinate (%s, %s) is off the plateau that ranges from (0, 0) to (%s, %s)";
    private static final String OCCUPIED_ADD_ERROR_MESSAGE_TEMPLATE =
        "Cannot add a new rover at (%s, %s) because the space is already occupied";

    private final int xTopRight;
    private final int yTopRight;
    private final List<Rover> rovers;

    /**
     * The constructor.
     *
     * @param xTopRight the X position for the upper right corner of the plateau
     * @param yTopRight the Y position for the upper right corner of the plateau
     * @throws IllegalArgumentException if value for xTopRight or yTopRight is less than 1
     */
    public Plateau(int xTopRight, int yTopRight) {
        if (xTopRight < 1 || yTopRight < 1) {
            throw new IllegalArgumentException("Plateau size must be greater than zero");
        }

        this.xTopRight = xTopRight;
        this.yTopRight = yTopRight;
        rovers = new ArrayList<>();
    }

    /**
     * Get the X coordinate boundary of the plateau.
     *
     * @return the X coordinate boundary of the plateau
     */
    public int getXTopRight() {
        return xTopRight;
    }

    /**
     * Get the Y coordinate boundary of the plateau.
     *
     * @return the Y coordinate boundary of the plateau
     */
    public int getYTopRight() {
        return yTopRight;
    }

    /**
     * Add a rover at the specified location and heading.
     *
     * @param x the X position of the new rover
     * @param y the Y position of the new rover
     * @param direction the heading of the new rover
     * @return the new Rover
     * @throws OffPlateauException if the position specified is not on the plateau
     * @throws SpaceAlreadyOccupiedException if there is already a rover at the specified position
     */
    public Rover addRover(int x, int y, Direction direction) throws OffPlateauException, SpaceAlreadyOccupiedException {
        if (!isOnPlateau(x, y)) {
            throw new OffPlateauException(String.format(OFF_PLATEAU_ERROR_MESSAGE_TEMPLATE, x, y, xTopRight, yTopRight));
        }

        Coordinate position = new Coordinate(x, y);
        if (isSpaceOccupied(position)) {
            throw new SpaceAlreadyOccupiedException(String.format(OCCUPIED_ADD_ERROR_MESSAGE_TEMPLATE, x, y));
        }

        Rover rover = new Rover(position, direction, this);
        rovers.add(rover);
        return rover;
    }

    /**
     * Is the specified coordinate on the plateau?
     *
     * @param position the coordinate
     * @return true if the coordinate is on the plateau, false otherwise
     */
    public boolean isOnPlateau(Coordinate position) {
        return position != null && isOnPlateau(position.getX(), position.getY());
    }

    /**
     * Is the specified coordinate on the plateau?
     *
     * @param x the X position of the coordinate
     * @param y the Y position of the coordinate
     * @return true if the coordinate is on the plateau, false otherwise
     */
    public boolean isOnPlateau(int x, int y) {
        return x >= 0 && x <= xTopRight && y >= 0 && y <= yTopRight;
    }

    /**
     * Is the specified coordinate already occupied by another rover?
     *
     * @param position the coordinate
     * @return true if the coordinate is already occupied, false otherwise
     */
    public boolean isSpaceOccupied(Coordinate position) {
        for (Rover rover : rovers) {
            if (rover.getCurrentPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
}
