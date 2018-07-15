package rover;

/**
 * The exception used to represent an error when a move would cause the rover to fall off the plateau.
 */
public class OffPlateauException extends Exception {
    /**
     * The constructor.
     *
     * @param message the error message
     */
    OffPlateauException(String message) {
        super(message);
    }
}
