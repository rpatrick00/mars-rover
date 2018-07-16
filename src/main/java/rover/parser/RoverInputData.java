package rover.parser;

import java.util.ArrayList;
import java.util.List;

import rover.Command;
import rover.Direction;

/**
 * This class is a data holder for returning data from the parser.
 */
public class RoverInputData {

    int xTopRight;
    int yTopRight;
    List<RoverData> roverData;

    /**
     * The constructor.
     *
     * @param xTopRight the X coordinate for the top, right corner of the plateau
     * @param yTopRight the Y coordinate for the top, right corner of the plateau
     */
    public RoverInputData(int xTopRight, int yTopRight) {
        this.xTopRight = xTopRight;
        this.yTopRight = yTopRight;
        this.roverData = new ArrayList<>();
    }

    /**
     * Get the X value for the top, right corner of the plateau.
     *
     * @return the X value for the top, right corner of the plateau
     */
    public int getXTopRight() {
        return xTopRight;
    }

    /**
     * Get the Y value for the top, right corner of the plateau.
     *
     * @return the Y value for the top, right corner of the plateau
     */
    public int getYTopRight() {
        return yTopRight;
    }

    /**
     * Add a new rover initial position and commands.
     *
     * @param data the new rover initial position and commands
     */
    public void addRoverData(RoverData data) {
        roverData.add(data);
    }

    /**
     * Get the list of rover data objects.
     *
     * @return the list of rover data objects
     */
    public List<RoverData> getRoverData() {
        return roverData;
    }

    /**
     * This class is a data holder for returning the a rover's initial position, heading, and the list of commands.
     */
    public static class RoverData {
        int x;
        int y;
        Direction direction;
        List<Command> commands;

        RoverData(int x, int y, String directionName) {
            this.x = x;
            this.y = y;
            this.direction = Direction.valueOf(directionName);
        }

        /**
         * Get the X value of the rover's initial position.
         *
         * @return the X value of the rover's initial position
         */
        public int getX() {
            return x;
        }

        /**
         * Get the Y value of the rover's initial position.
         *
         * @return the Y value of the rover's initial position
         */
        public int getY() {
            return y;
        }

        /**
         * Get the initial direction of the rover.
         *
         * @return the initial direction of the rover
         */
        public Direction getDirection() {
            return direction;
        }

        /**
         * Get the list of rover commands.
         *
         * @return the list of rover commands
         */
        public List<Command> getCommands() {
            return commands;
        }

        void setCommands(List<Command> commands) {
            this.commands = new ArrayList<>(commands);
        }
    }
}

