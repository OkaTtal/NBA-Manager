package NBA_pac;

/**
 * A class representing a distance that is comparable to other distances.
 * This class implements the Comparable interface to enable comparison
 * based on the distance.
 *
 * @param <T> The type of the name (must be comparable)
 * @param <N> The type of the distance (must be comparable)
 */
public class ComparableDistance<T extends Comparable<T>, N extends Comparable<N>> implements Comparable<ComparableDistance<T, N>> {
    N distance; // The distance value
    T name; // The name associated with the distance

    // Constructor to initialize the distance and name
    public ComparableDistance(N km, T n) {
        distance = km;
        name = n;
    }

    /**
     * Compares this ComparableDistance object with another ComparableDistance object.
     * The comparison is based on the distance.
     *
     * @param cd The ComparableDistance object to be compared with this object
     * @return A negative integer, zero, or a positive integer as this object's
     *         distance is less than, equal to, or greater than the specified object's distance.
     */
    @Override
    public int compareTo(ComparableDistance<T, N> cd) {
        if (Integer.parseInt(distance.toString()) > Integer.parseInt(cd.distance.toString()))
            return 1;
        else if (Integer.parseInt(distance.toString()) < Integer.parseInt(cd.distance.toString()))
            return -1;
        return 0;
    }

    /**
     * Returns a string representation of the distance.
     *
     * @return A string representation of the distance followed by "km"
     */
    @Override
    public String toString() {
        return distance + "km ";
    }
}
