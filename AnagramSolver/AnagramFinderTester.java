import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
 *
 * Student information for assignment:
 *
 *  On my honor, <Pranav Teja Varanasi>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: ptv247
 *  email address: varanasipranav@gmail.com
 *  Grader name: Jacob Szwejbka
 *  Number of slip days I am using: 1
 */

public class AnagramFinderTester {


    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * @param args Not used.
     */
    public static void main(String[] args) {

    	//cs314StudentTestsForLetterInventory();

        // tests on the anagram solver itself
      // boolean displayAnagrams = getChoiceToDisplayAnagrams();
       //AnagramSolver solver = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
      // runAnagramTests(solver, displayAnagrams);
    }

    private static void cs314StudentTestsForLetterInventory() {
        // CS314 Students, delete the above tests when you turn in your assignment
        // CS314 Students add your LetterInventory tests here. 
    	
        LetterInventory lets1;
        LetterInventory lets2;

        //test 1 String constructor
        
        lets1 = new LetterInventory("cat");

        Object expected = 3;
        Object actual = lets1.size();
        showTestResults(expected, actual, 1, " String constructor");

        //test 2 String constructor
        
        lets1 = new LetterInventory("");

        expected = 0;
        actual = lets1.size();
        showTestResults(expected, actual, 2, " String constructor");

         //test 3 int[] constructor
        
        int[] lets2Arr = {0,0,1,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,0,0,0,0,0,0};
        int[] lets2Arr2 = {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        lets1 = new LetterInventory(lets2Arr);

        expected = 13;
        actual = lets1.size();
        showTestResults(expected, actual, 3, " int[] constructor");

        //test 4 int[] constructor
        
        lets1 = new LetterInventory(lets2Arr2);

        expected = 1;
        actual = lets1.size();
        showTestResults(expected, actual, 4, " int[] constructor");

        // test 5 get
        lets2 = new LetterInventory("APPLE");

        expected = 1;
        actual = lets2.get('L');
        showTestResults(expected, actual, 5, " get() ");

        // test 6 get
        expected = 2;
        actual = lets2.get('P');
        showTestResults(expected, actual, 6, " get()");

        // test 7 size
        lets2 = new LetterInventory("University");
        expected = 10;
        actual = lets2.size(); 
        showTestResults(expected, actual, 7, " size()");

        // test 8 size
        lets1 = new LetterInventory("");
        expected = 0;
        actual = lets1.size(); 
        showTestResults(expected, actual, 8, " size()");

        // test 9 isEmpty()
        expected = true;
        actual = lets1.isEmpty();
        showTestResults(expected, actual, 9, " isEmpty()");

        // test 10 isEmpty()
        lets2 = new LetterInventory("abcdhrfrf");
        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 10, " isEmpty()");

        // test 11 toString()

        lets1 = new LetterInventory("abcdefg");
        expected = "abcdefg";
        actual = lets1.toString();
        showTestResults(expected, actual, 11, " toString()");

        // test 12 toString()

        lets2 = new LetterInventory("thomas");
        expected = "ahmost";
        actual = lets2.toString();
        showTestResults(expected, actual, 12, "toString()");

        // test 13 add()
        lets1 =  new LetterInventory("abcdef");
        lets2 = new LetterInventory("abcdef");

        LetterInventory lets3  = lets1.add(lets2);
        expected = 12;
        actual = lets3.size();
        showTestResults(expected, actual, 13, "add()");

        // test 14 add()
        lets1 =  new LetterInventory("");
        lets2 = new LetterInventory("");
        lets3  = lets1.add(lets2);
        expected = 0;
        actual = lets3.size();
        showTestResults(expected, actual, 14, "add()");


        // test 15 subtract()
        lets1 = new LetterInventory("abbbb");
        lets2 = new LetterInventory("aabb");
        lets3 = lets1.subtract(lets2);
        expected = null;
        actual = lets3;
        showTestResults(expected, actual, 15, "subtract()");

        // test 16 subtract()
        lets1 = new LetterInventory("aa");
        lets2 = new LetterInventory("aa");
        lets3 = lets1.subtract(lets2);
        expected = 0;
        actual = lets3.get('a');
        showTestResults(expected, actual, 16, "subtract()");

        //test 17 equals()
        lets1 =  new LetterInventory("abcd");
        lets2 = null;

        expected = false;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 17, "equals()");

        //test 18 equals()
        lets1 =  new LetterInventory("texas");
        lets2 =  new LetterInventory("texas");

        expected = true;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 18, "equals()");

        
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        console.close();
        return response.length() > 0 && response.toLowerCase().charAt(0) == 'y';
    }

    private static boolean showTestResults(Object expected, Object actual, int testNum, String featureTested) {
        System.out.println("Test Number " + testNum + " testing " + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, boolean displayAnagrams) {
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
                System.out.println("Time to find anagrams: " + st.time());
                // System.out.println("Number of calls to recursive helper method: " + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for(List<String> singleAnagram : anagrams)
            System.out.println(singleAnagram);
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, but either a difference in anagrams or anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<String>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams : "Wrong number of angrams read or expected";
        }
    }
}