package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the injury rates between different exercises.
 * Take into account that injury rates are not symmetric.
 * @author aguirre
 *
 */
public class ExerciseInjuryRates {

    /**
     * Stores the rate (integers) between pairs of exercises.
     */
    private Map<Tuple<String, String>, Integer> rates = null;

    /**
     * Default constructor.
     * Sets the rates as empty.
     */
    public ExerciseInjuryRates() {
        rates = new HashMap<Tuple<String, String>, Integer>();
    }

    /**
     * Adds a new injury rate.
     * @param ex1 is one of the exercises
     * @param ex2 is the other exercise
     * @param rate is the injury rate for (ex1, ex2)
     */
    public final void addInjuryRate(final String ex1, final String ex2, final int rate) {
        if (ex1 == null || ex2 == null) {
            throw new IllegalArgumentException("Invalid exercise(s) (null)");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Invalid rate (negative)");
        }
        Tuple<String, String> exs = new Tuple<String, String>(ex1, ex2);
        if (rates.containsKey(exs)) {
            throw new IllegalStateException("Exercises already in");
        } else {
            rates.put(exs, rate);
        }
    }

    /**
     * Returns the injury rate between two exercises.
     * If no rate is maintained for the exercises, the value is
     * assumed zero.
     * @param ex1 is one of the exercises to query about
     * @param ex2 is the other exercise to query about
     * @return the injury rate for (ex1, ex2)
     */
    public final int getInjuryRate(final String ex1, final String ex2) {
        if (ex1 == null || ex2 == null) {
            throw new IllegalArgumentException("Invalid exercise(s) (null)");
        }
        Tuple<String, String> exs = new Tuple<String, String>(ex1, ex2);
        if (!rates.containsKey(exs)) {
            // No rate for these exercises. Assuming zero
            return 0;
        } else {
            return rates.get(exs);
        }
    }

    /**
     * Updates the stored rate for a pair of exercises.
     * @param ex1 is one of the exercises for the update
     * @param ex2 is the other exercise for the update
     * @param rate is the new injury rate for (ex1, ex2)
     */
    public final void updateInjuryRate(final String ex1, final String ex2, final int rate) {
        if (ex1 == null || ex2 == null) {
            throw new IllegalArgumentException("Invalid exercise(s) (null)");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Invalid rate (negative)");
        }
        Tuple<String, String> exs = new Tuple<String, String>(ex1, ex2);
        if (!rates.containsKey(exs)) {
            throw new IllegalStateException("No rate for these exercises");
        } else {
            rates.put(exs, rate);
        }
    }

}
