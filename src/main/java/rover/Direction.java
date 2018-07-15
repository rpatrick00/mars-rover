package rover;

/**
 * This enum represents the four major compass directions.
 */
public enum Direction {

    /**
     * The North direction.
     */
    N(0,1) {
        @Override
        public Direction left() {
            return W;
        }

        @Override
        public Direction right() {
            return E;
        }
    },

    /**
     * The South direction.
     */
    S(0,-1) {
        @Override
        public Direction right() {
            return W;
        }

        @Override
        public Direction left() {
            return E;
        }
    },

    /**
     * The East direction.
     */
    E(1,0) {
        @Override
        public Direction right() {
            return S;
        }

        @Override
        public Direction left() {
            return N;
        }
    },

    /**
     * The West direction.
     */
    W(-1,0) {
        @Override
        public Direction right() {
            return N;
        }

        @Override
        public Direction left() {
            return S;
        }
    };

    private final int xAxisIncrement;
    private final int yAxisIncrement;

    Direction(int xAxisIncrement, int yAxisIncrement) {
        this.xAxisIncrement = xAxisIncrement;
        this.yAxisIncrement = yAxisIncrement;
    }

    /**
     * Get the direction that corresponds to turning right.
     *
     * @return the new direction after turning right
     */
    public abstract Direction right();

    /**
     * Get the direction that corresponds to turning left.
     *
     * @return the new direction after turning left
     */
    public abstract Direction left();

    /**
     * Get the X coordinate increment when moving in this direction.
     *
     * @return the X coordinate increment
     */
    public int getXAxisIncrement() {
        return xAxisIncrement;
    }

    /**
     * Get the Y coordinate increment when moving in this direction.
     *
     * @return the Y coordinate increment
     */
    public int getYAxisIncrement() {
        return yAxisIncrement;
    }
}
