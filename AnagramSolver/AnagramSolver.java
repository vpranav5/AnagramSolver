/* CS 314 STUDENTS: FILL IN THIS HEADER.
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


import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AnagramSolver {
	
	private static HashMap<String, LetterInventory> wordMap;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {
    	// checks precondition to ensure the dictionary is not null
    	if(dictionary == null) {
    		throw new IllegalArgumentException("Invalid dictionary!");
    	}
    	wordMap = new HashMap<String, LetterInventory>();
    	
    	// store a new letter inventory for each word added
    	for(String s : dictionary) {
    		wordMap.put(s, new LetterInventory(s));
    	}
    }
    
    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
    	// checks precondition to ensure satisfiable target string and valid maxwords
    	if(s == null || maxWords < 0 || !(oneEnglishChar(s))) {
    		throw new IllegalArgumentException("Illegal Paramaeters!");
    	}
    	
    	ArrayList<List<String>> anagramDatabase =  new ArrayList<List<String>>();
    	LetterInventory  wordPool  = new LetterInventory(s);
    	// arraylist storing all the anagrams from one recursive run
    	ArrayList<String>  anagram = new ArrayList<String>();
    	
    	// stores all the words than can be potential anagrams
    	ArrayList<String> activeList = generateValidWords(wordPool);
    	
    	// if no limit specified, use MAX_VALUE for maxWords
    	if(maxWords == 0) {
    		maxWords = Integer.MAX_VALUE;
    	}
    	
    	// ensures that all the individual arraylists are generated in sorted order
    	Collections.sort(activeList);
        recurseAnagrams(wordPool, maxWords, anagramDatabase, anagram, activeList, 0);
        // sorts the database so arraylists are arranged in specified size order
    	Collections.sort(anagramDatabase, new CompareAnagrams());
    
        return anagramDatabase;
    }
    
    // helper method to check if a target string has atleast one english character
    private boolean oneEnglishChar(String s) {	
    	for(int i = 0; i < s.length(); i++) {
    		char ch = s.charAt(i);		
    		// checks whether an english character exists in string
    		if (('a' <= ch) && (ch <= 'z')) {
    			return true;
    		}
    	}
    	// return false if no english characters found
    	return false;
    }
    
    // reduce dictionary so only words that can be possible anagrams are considered
    public ArrayList<String> generateValidWords(LetterInventory wordPool) {
    	ArrayList<String> validWords = new ArrayList<String>();
    	for(String key : wordMap.keySet()) {
    		LetterInventory wordSet  = new LetterInventory(key);
    		// condition ensures that a word has character pool that can fit into target pool
    		if(wordPool.subtract(wordSet) != null) {
    			validWords.add(key);
    		}
    	}   	
    	return validWords;  		
    }
    
    // recursive method to find all possible combinations of anagrams
    public void recurseAnagrams(LetterInventory currentPool, int maxWords, ArrayList<List<String>> database, ArrayList<String> currentAnagram, ArrayList<String> activeList, int index) {
    	// base case for when all the characters in pool have been used up
    	if(currentPool.isEmpty()) {		
    		// add the completed anagram set to database of lists
    		database.add(new ArrayList<String>(currentAnagram));	
    	}
    	// recursive case when more anagrams can be added
    	else if(currentAnagram.size() < maxWords) {
    		for(int i = index; i < activeList.size(); i++) {
    			// represents the base word, from which to form anagrams from
    			String currentWord = activeList.get(i);
    			LetterInventory currentInvent = wordMap.get(currentWord);
    			// gets the letter inventory of remaining characters to use
    			LetterInventory newPool = currentPool.subtract(currentInvent);
    			if(newPool != null) {
    				currentAnagram.add(currentWord);
    				// recursive call to find subsequent anagrams, with index i to mark position in active list
    				recurseAnagrams(newPool, maxWords, database, currentAnagram, activeList, i);
    				// backtracking step to allow for different combinations of subsequent anagrams
    				currentAnagram.remove(currentWord);
    			}
    			
    		}
    	}
    }
    
    // nested class to allow for sorting of list database using comparator
    private static class CompareAnagrams implements Comparator<List<String>> {    	
        public int compare(List<String> a1, List<String> a2) {
        	// if list sizes are diff, return negative int if 1st list is smaller, positive if larger
        	if(a1.size() != a2.size()) {
        		return  (a1.size() - a2.size());
        	}
        	else{
        		// sort lexicographically if list sizes are equal
        		return (a1.toString()).compareTo(a2.toString());
        	}
  
         } 
    }
}