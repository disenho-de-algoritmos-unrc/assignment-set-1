package util;

/**
 * A generic tuple of values.
 *
 * @param <X> is the type of the first component.
 * @param <Y> is the type of the second component.
 */
public class Tuple<X, Y> {

    /**
     * Stores value of first component.
     */
    private X x;

    /**
     * Stores value of second component.
     */
    private Y y;

    /**
     * Default constructor.
     * @param newX is the value to set first component.
     * @param newY is the value to set second component.
     */
    public Tuple(final X newX, final Y newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Returns value of first component.
     * @return value of first component.
     */
    public final X getX() {
        return x;
    }

    /**
     * Returns value of second component.
     * @return value of second component.
     */
    public final Y getY() {
        return y;
    }

    /**
     * Sets first component value.
     * @param newX is the new value for first component.
     */
    public final void setX(final X newX) {
        x = newX;
    }

    /**
     * Sets second component value.
     * @param newY is the new value for second component.
     */
    public final void setY(final Y newY) {
        y = newY;
    }

    /**
     * Produces string representation of the tuple.
     * @return a string representation of the state of the object.
     */
    @Override
    public final String toString() {
        return "(" + x.toString() + "," + y.toString() + ")";
    }

    /**
     * Checks whether receiver equals another tuple.
     * @param other is the object to compare to.
     * @return true iff receiver equals other (same first/second components).
     */
    @Override
    public final boolean equals(final Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tuple)) {
            return false;
        }

        Tuple<X, Y> otherAsTuple = (Tuple<X, Y>) other;

        return otherAsTuple.x.equals(this.x) && otherAsTuple.y.equals(this.y);
    }

    /**
     * Computes hash code for object.
     * @return the hash code of the receiver object.
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        int xHashCode = 0;
        if (x != null) {
            xHashCode = x.hashCode();
        }
        int yHashCode = 0;
        if (y != null) {
            yHashCode = x.hashCode();
        }
        result = prime * result + (xHashCode);
        result = prime * result + (yHashCode);
        return result;
    }
}
