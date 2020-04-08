package app;

import java.util.Set;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Finds most frequent words of size k in a given text.
 * Text is passed as a single line in a text file.
 *
 */
public final class FindFrequentWords {

    /**
     * Default constructor of class.
     * Made private to comply with style constraints.
     */
    private FindFrequentWords()  { }

    /**
     * Main method for FindFrequentWords.
     * It computes the frequent words of size k, for a text stored in a text file.
     * @param args has name of text file and the size of pattern to search.
     */
    public static void main(final String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java FindFrequentWords <text file name> <int>");
        } else {
            // Getting the text
            String fileName = args[0];
            String text = null;
            try {
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                text = buffReader.readLine();
                buffReader.close();
            } catch (Exception e) {
                throw new IllegalArgumentException("Problem reading text file");
            }
            if (text == null) {
                throw new IllegalArgumentException("Empty text file");
            }

            // Getting the size of frequent word to search
            int k = 0;
            try {
                k = Integer.parseInt(args[1]);
            } catch (Exception e) {
                throw new IllegalArgumentException("invalid word size (must be a non-negative integer)");
            }
            if (k < 0) {
                throw new IllegalArgumentException("invalid word size (must be a non-negative integer)");
            }

            Set<String> patterns = util.TextPatternUtils.mostFrequentPatterns(text, k);
            for (String s: patterns) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

}
