package util;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for class RoutineMerger.
 *
 */
public class RoutineMergerTest {
    
    /**
     * Injury rates to be used for tests (see setup method).
     */
    private ExerciseInjuryRates rates = null;
    
    /**
     * Initializes the injury rates used for testing
     */
    @Before
    public void initializeRates() {
        this.rates = new ExerciseInjuryRates();
        rates.addInjuryRate("burpees", "burpees", 11);
        rates.addInjuryRate("burpees", "jumping jacks", 10);
        rates.addInjuryRate("burpees", "push ups", 2);
        rates.addInjuryRate("burpees", "crunches", 1);
        rates.addInjuryRate("burpees", "low plank", 1);
        rates.addInjuryRate("burpees", "mountain climbers", 5);
        rates.addInjuryRate("burpees", "scissor kicks", 2);

        rates.addInjuryRate("jumping jacks", "burpees", 10);
        rates.addInjuryRate("jumping jacks", "jumping jacks", 10);
        rates.addInjuryRate("jumping jacks", "push ups", 3);
        rates.addInjuryRate("jumping jacks", "crunches", 1);
        rates.addInjuryRate("jumping jacks", "low plank", 1);
        rates.addInjuryRate("jumping jacks", "mountain climbers", 5);
        rates.addInjuryRate("jumping jacks", "scissor kicks", 2);
     
        rates.addInjuryRate("push ups", "burpees", 4);
        rates.addInjuryRate("push ups", "jumping jacks", 3);
        rates.addInjuryRate("push ups", "push ups", 10);
        rates.addInjuryRate("push ups", "crunches", 1);
        rates.addInjuryRate("push ups", "low plank", 1);
        rates.addInjuryRate("push ups", "mountain climbers", 7);
        rates.addInjuryRate("push ups", "scissor kicks", 2);
        
        rates.addInjuryRate("crunches", "burpees", 2);
        rates.addInjuryRate("crunches", "jumping jacks", 2);
        rates.addInjuryRate("crunches", "push ups", 1);
        rates.addInjuryRate("crunches", "crunches", 10);
        rates.addInjuryRate("crunches", "low plank", 8);
        rates.addInjuryRate("crunches", "mountain climbers", 6);
        rates.addInjuryRate("crunches", "scissor kicks", 9);
        
        rates.addInjuryRate("low plank", "burpees", 3);
        rates.addInjuryRate("low plank", "jumping jacks", 2);
        rates.addInjuryRate("low plank", "push ups", 2);
        rates.addInjuryRate("low plank", "crunches", 10);
        rates.addInjuryRate("low plank", "low plank", 10);
        rates.addInjuryRate("low plank", "mountain climbers", 7);
        rates.addInjuryRate("low plank", "scissor kicks", 7);
        
        rates.addInjuryRate("mountain climbers", "burpees", 7);
        rates.addInjuryRate("mountain climbers", "jumping jacks", 5);
        rates.addInjuryRate("mountain climbers", "push ups", 4);
        rates.addInjuryRate("mountain climbers", "crunches", 5);
        rates.addInjuryRate("mountain climbers", "low plank", 3);
        rates.addInjuryRate("mountain climbers", "mountain climbers", 10);
        rates.addInjuryRate("mountain climbers", "scissor kicks", 3);
        
        rates.addInjuryRate("scissor kicks", "burpees", 3);
        rates.addInjuryRate("scissor kicks", "jumping jacks", 3);
        rates.addInjuryRate("scissor kicks", "push ups", 2);
        rates.addInjuryRate("scissor kicks", "crunches", 7);
        rates.addInjuryRate("scissor kicks", "low plank", 5);
        rates.addInjuryRate("scissor kicks", "mountain climbers", 4);
        rates.addInjuryRate("scissor kicks", "scissor kicks", 10);
    }

    /**
     * Test constructor for RoutineMerger, invalid list A.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoutineMergerConstructorInvalidListA() {
        
        RoutineMerger merger = new RoutineMerger(null, null, null);
        
    }

    /**
     * Test constructor for RoutineMerger, invalid list B.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoutineMergerConstructorInvalidListB() {
        List<String> routineA = new LinkedList<String>();
        
        RoutineMerger merger = new RoutineMerger(routineA, null, null);
        
    }

    /**
     * Test constructor for RoutineMerger, invalid rates.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRoutineMergerConstructorInvalidRates() {
        List<String> routineA = new LinkedList<String>();
        List<String> routineB = new LinkedList<String>();

        RoutineMerger merger = new RoutineMerger(routineA, routineB, null);
        
    }
    
    /**
     * Test constructor for RoutineMerger, empty list A
     */
    @Test
    public void testRoutineMergerEmptyListA() {
        
        List<String> routineA = new LinkedList<String>();
        
        List<String> routineB = new LinkedList<String>();
        routineB.add("mountain climbers");
        routineB.add("mountain climbers");
                
        RoutineMerger merger = new RoutineMerger(routineA, routineB, rates);
        
        Tuple<List<String>, Integer> result = merger.mergeRoutines();
        
        assertEquals(routineB.size(), result.getX().size());
        assertEquals(10, result.getY().intValue());
               
    }
    
    /**
     * Test constructor for RoutineMerger, empty list B
     */
    @Test
    public void testRoutineMergerEmptyListB() {
        
        List<String> routineA = new LinkedList<String>();
        routineA.add("burpees");
        routineA.add("burpees");
        
        List<String> routineB = new LinkedList<String>();
        
                
        RoutineMerger merger = new RoutineMerger(routineA, routineB, rates);
        
        Tuple<List<String>, Integer> result = merger.mergeRoutines();
        
        assertEquals(routineA.size(), result.getX().size());
        assertEquals(11, result.getY().intValue());
               
    }
    
    /**
     * Test constructor for RoutineMerger, non-empty small lists
     */
    @Test
    public void testRoutineMergernonNonEmptySmallLists() {
        
        List<String> routineA = new LinkedList<String>();
        routineA.add("scissor kicks");
        routineA.add("scissor kicks");
        
        
        List<String> routineB = new LinkedList<String>();
        routineB.add("mountain climbers");
        routineB.add("mountain climbers");
                
        RoutineMerger merger = new RoutineMerger(routineA, routineB, rates);
        
        Tuple<List<String>, Integer> result = merger.mergeRoutines();
        
        assertEquals(routineA.size() + routineB.size(), result.getX().size());
        assertEquals(10, result.getY().intValue());
               
    }
    
    /**
     * Test constructor for RoutineMerger, non-empty midsize lists
     */
    @Test
    public void testRoutineMergernonNonEmptyMidsizeLists() {
        
        List<String> routineA = new LinkedList<String>();
        routineA.add("scissor kicks");
        routineA.add("burpees");
        routineA.add("scissor kicks");
        routineA.add("jumping jacks");
        
        
        
        List<String> routineB = new LinkedList<String>();
        routineB.add("burpees");
        routineB.add("mountain climbers");
        routineB.add("scissor kicks");
        routineB.add("low plank");
                
        RoutineMerger merger = new RoutineMerger(routineA, routineB, rates);
        
        Tuple<List<String>, Integer> result = merger.mergeRoutines();
        
        assertEquals(routineA.size() + routineB.size(), result.getX().size());
        assertEquals(18, result.getY().intValue());
               
    }
    
    
    /**
     * Test constructor for RoutineMerger, different muscles
     */
    @Test
    public void testRoutineMergerDifferentMuscles() {
        
        List<String> coreRoutine = new LinkedList<String>();
        coreRoutine.add("scissor kicks");
        coreRoutine.add("low plank");
        coreRoutine.add("crunches");
        coreRoutine.add("mountain climbers");
        coreRoutine.add("burpees");
        coreRoutine.add("scissor kicks");
        coreRoutine.add("low plank");
        coreRoutine.add("crunches");
        coreRoutine.add("mountain climbers");
        coreRoutine.add("burpees");
        
        
        List<String> upperRoutine = new LinkedList<String>();
        upperRoutine.add("burpees");
        upperRoutine.add("mountain climbers");
        upperRoutine.add("push ups");
        upperRoutine.add("jumping jacks");
        upperRoutine.add("burpees");
        upperRoutine.add("mountain climbers");
        upperRoutine.add("push ups");
        upperRoutine.add("jumping jacks");
        upperRoutine.add("burpees");
        upperRoutine.add("mountain climbers");
                
        RoutineMerger merger = new RoutineMerger(coreRoutine, upperRoutine, rates);
        
        Tuple<List<String>, Integer> result = merger.mergeRoutines();
        
        assertEquals(coreRoutine.size() + upperRoutine.size(), result.getX().size());
        assertEquals(61, result.getY().intValue());
               
    }
    
    
}
