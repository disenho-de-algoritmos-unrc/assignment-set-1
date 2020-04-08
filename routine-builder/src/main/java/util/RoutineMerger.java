package util;

import java.util.List;

/**
 * Computes an optimal merge of different routines.
 * @author aguirre
 *
 */
public class RoutineMerger {

    /**
     * Contains the injury rates of consecutive exercises.
     * This is the reference to injury rates.
     * Take into account that injury rates are not symmetric.
     */
    private ExerciseInjuryRates rates = null;

    /**
     * First routine, i.e., list of exercises, to merge.
     */
    private List<String> routineA = null;

    /**
     * Second routine, i.e., list of exercises, to merge.
     */
    private List<String> routineB = null;

    /**
     * Constructor for the class.
     * @param newA is the first routine to merge.
     * @param newB is the second routine to merge.
     * @param newRates is the rates to use for computing the optimal merge.
     */
    public RoutineMerger(final List<String> newA, final List<String> newB, final ExerciseInjuryRates newRates) {
        if (newA == null || newB == null) {
            throw new IllegalArgumentException("Invalid routine(s) (null)");
        }
        if (newRates == null) {
            throw new IllegalArgumentException("Invalid injury rates (null)");
        }
        this.routineA = newA;
        this.routineB = newB;
        this.rates = newRates;
    }

    /**
     * Computes the optimal merge of the routines in the merger.
     * @return the optimal routine that combines the routines in the merge.
     * The return value is a tuple that contains the actual merge, and the total injury rate.
     */
    public final Tuple<List<String>, Integer> mergeRoutines() {
        throw new UnsupportedOperationException("method not yet implemented");
    }



}
