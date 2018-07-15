package rover.parser;

import java.util.ArrayList;
import java.util.List;

import rover.Command;
import rover.Direction;

public class RoverInputData {

    int xTopRight;
    int yTopRight;
    List<RoverData> roverData;

    public RoverInputData(int xTopRight, int yTopRight) {
        this.xTopRight = xTopRight;
        this.yTopRight = yTopRight;
        this.roverData = new ArrayList<>();
    }

    public int getXTopRight() {
        return xTopRight;
    }

    public int getYTopRight() {
        return yTopRight;
    }

    public void addRoverData(RoverData data) {
        roverData.add(data);
    }

    public List<RoverData> getRoverData() {
        return roverData;
    }

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

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Direction getDirection() {
            return direction;
        }

        public List<Command> getCommands() {
            return commands;
        }

        void setCommands(List<Command> commands) {
            this.commands = new ArrayList<>(commands);
        }
    }
}

