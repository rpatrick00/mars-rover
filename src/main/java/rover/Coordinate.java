package rover;

import java.util.Objects;

/**
 * A simple class that represents a two-dimensional coordinate in the non-negative space.
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * The constructor.
     *
     * @param x the x value
     * @param y the y value
     * @throws IllegalArgumentException - if x or y is less than zero
     */
    public Coordinate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Negative coordinates are not allowed");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Get the X axis value of this coordinate.
     *
     * @return the X axis value
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y axis value of this coordinate.
     *
     * @return the Y axis value
     */
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate)o;
        return getX() == that.getX() && getY() == that.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
