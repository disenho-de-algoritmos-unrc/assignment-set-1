package util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import org.apache.commons.io.IOUtils;


import org.junit.Test;

/**
 * Tests for methods in class TextPatternUtils.
 *
 */
public final class TextPatternUtilsTest {

    /**
     * Tests mostFrequentPatterns on invalid text.
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testMostFrequentWordsInvalidText() {
        String text = null;
        int k = 1;
        
        TextPatternUtils.mostFrequentPatterns(text, k);       
    }
    
    /**
     * Tests mostFrequentPatterns on invalid pattern size.
     */
    @Test(expected=IllegalArgumentException.class)
    public final void testMostFrequentWordsInvalidSize() {
        String text = "";
        int k = -1;
        
        TextPatternUtils.mostFrequentPatterns(text, k);       
    }

    /**
     * Tests mostFrequentPatterns on emptyText.
     */
    @Test
    public final void testMostFrequentWordsEmptyText() {
        String text = "";
        int k = 0;
        
        Set<String> mostFreqPatterns = TextPatternUtils.mostFrequentPatterns(text, k);
        
        assertEquals("empty list is the only most frequent pattern", 1, mostFreqPatterns.size());
    }
    
    /**
     * Tests mostFrequentPatterns on simple text, single output.
     */
    @Test
    public final void testMostFrequentWordsSimpleTextSingleOutput() {
        String text = "ACAACTATGCATACTATCGGGAACTATCCT";
        int k = 5;
        
        Set<String> mostFreqPatterns = TextPatternUtils.mostFrequentPatterns(text, k);
        
        assertEquals(1, mostFreqPatterns.size());
        assertTrue(mostFreqPatterns.contains("ACTAT"));
    }
    
    /**
     * Tests mostFrequentPatterns on simpler text, single output.
     */
    @Test
    public final void testMostFrequentWordsSimplerTextSingleOutput() {
        String text = "CGATATATCCATAG";
        int k = 3;
        
        Set<String> mostFreqPatterns = TextPatternUtils.mostFrequentPatterns(text, k);
        
        assertEquals(1, mostFreqPatterns.size());
        assertTrue(mostFreqPatterns.contains("ATA"));
    }
    
    /**
     * Tests mostFrequentPatterns on simple text, two outputs.
     */
    @Test
    public final void testMostFrequentWordsSimpleTextDoubleOutput() {
        String text = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        int k = 4;
        
        Set<String> mostFreqPatterns = TextPatternUtils.mostFrequentPatterns(text, k);
        
        assertEquals(2, mostFreqPatterns.size());
        assertTrue(mostFreqPatterns.contains("CATG"));
        assertTrue(mostFreqPatterns.contains("GCAT"));
    }
    
    /**
     * Tests mostFrequentPatterns on simple text, two outputs.
     */
    @Test(timeout=10000)
    public final void testMostFrequentWordsBiggerInput() throws IOException {      
        String[] lines = IOUtils.toString(
                this.getClass().getResourceAsStream("dataset_2_10.txt"),
                "UTF-8"
              ).split("\n");
        String text = lines[0];
        int k = Integer.parseInt(lines[1]);
        Set<String> mostFreqPatterns = TextPatternUtils.mostFrequentPatterns(text, k);
        
        assertEquals(1, mostFreqPatterns.size());
        assertTrue(mostFreqPatterns.contains("TGATGGCTTGG"));
    }
    
    /**
     * Tests mostFrequentPatterns on simple text, two outputs.
     */
    @Test(timeout=10000)
    public final void testMostFrequentWordsVibrioCholerae() throws IOException {      
        String text = IOUtils.toString(
                this.getClass().getResourceAsStream("Vibrio_cholerae.txt"),
                "UTF-8"
              );
        int k = 9;
        Set<String> mostFreqPatterns = TextPatternUtils.mostFrequentPatterns(text, k);
        
        assertEquals(1, mostFreqPatterns.size());
    }
    
}
