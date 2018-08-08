package rover;

/**
 * A simple class to represent a single rover.
 */
public class Rover {
    private static final String OFF_PLATEAU_ERROR_MESSAGE_TEMPLATE =
        "The coordinate (%s, %s) is off the plateau that ranges from (0, 0) to (%s, %s)";
    private static final String OCCUPIED_MOVE_ERROR_MESSAGE_TEMPLATE =
        "The rover at (%s, %s) cannot move %s because the space at (%s, %s) is already occupied";

    private Coordinate currentPosition;
    private Direction currentDirection;
    private Plateau plateau;

    /**
     * The constructor.
     *
     * @param currentPosition - the current position
     * @param currentDirection - the current direction
     * @param plateau - the plateau for this rover
     */
    Rover(Coordinate currentPosition, Direction currentDirection, Plateau plateau) {
        this.currentPosition = currentPosition;
        this.currentDirection = currentDirection;
        this.plateau = plateau;
    }

    /**
     * Get the current position of the rover.
     *
     * @return the current position
     */
    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Get the current direction the rover.
     *
     * @return the current direction
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Rotate the rover to the left.
     *
     * @return the new direction
     */
    public Direction rotateLeft() {
        currentDirection = currentDirection.left();
        return currentDirection;
    }

    /**
     * Rotate the rover to the right.
     *
     * @return the new direction
     */
    public Direction rotateRight() {
        currentDirection = currentDirection.right();
        return currentDirection;
    }

    /**
     * Execute the move to update the rover's current position.
     *
     * @return the new position
     * @throws OffPlateauException if the new position is off the map to one of the negative sides
     * @throws SpaceAlreadyOccupiedException if the space ahead is already occupied by another rover
     */
    public Coordinate move() throws OffPlateauException, SpaceAlreadyOccupiedException {
        Coordinate proposed = projectMove();
        if (plateau.isSpaceOccupied(proposed)) {
            String message = String.format(OCCUPIED_MOVE_ERROR_MESSAGE_TEMPLATE, currentPosition.getX(),
                                           currentPosition.getY(), currentDirection, proposed.getX(), proposed.getY());
            throw new SpaceAlreadyOccupiedException(message);
        }
        currentPosition = proposed;
        return currentPosition;
    }

    /**
     * Get the string containing the current location and heading.
     *
     * @return the string containing the current location and heading
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", currentPosition.getX(), currentPosition.getY(), currentDirection.toString());
    }

    private Coordinate projectMove() throws OffPlateauException {
        int x = currentPosition.getX() + currentDirection.getXAxisIncrement();
        int y = currentPosition.getY() + currentDirection.getYAxisIncrement();

        if (!plateau.isOnPlateau(x, y)) {
            String message = String.format(OFF_PLATEAU_ERROR_MESSAGE_TEMPLATE, x, y,
                plateau.getXTopRight(), plateau.getYTopRight());
            throw new OffPlateauException(message);
        }
        return new Coordinate(x, y);
    }
}
