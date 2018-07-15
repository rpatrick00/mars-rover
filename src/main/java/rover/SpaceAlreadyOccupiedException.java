package rover;

/**
 * The exception used to represent an error when a move would cause the rover to run into another rover.
 */
public class SpaceAlreadyOccupiedException extends Exception {
    /**
     * The constructor.
     *
     * @param message the message
     */
    public SpaceAlreadyOccupiedException(String message) {
        super(message);
    }
}
