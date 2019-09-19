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

public class LetterInventory {
	
	private int[] letterFreq;
	private static final int NUM_LETTERS = 26;
	private static final int ASCII_OFFSET = 97;
	private static int sizeCount;
	
	public LetterInventory (String phrase) {
		if(phrase == null) {
			throw new IllegalArgumentException("Input phrase cannot be null!");
		}
		
		// initialize container array to have 26 letters
		letterFreq = new int[NUM_LETTERS];	
		// reset the size to 0 when making a new object
		sizeCount =  0;		
		// implement to be case insensitive
		phrase = phrase.toLowerCase();		
		// store the frequencies of each letter in phrase
		for(int i = 0; i < phrase.length(); i++) {
			char ch = phrase.charAt(i);
			if('a' <= ch && ch <= 'z') {
				letterFreq[ch - ASCII_OFFSET]++;
			}
		}
		
		// initialize the global size to the calculated size
		sizeCount = getValidSize(letterFreq);
		
	}
	
	// constructor to initialize LetterInventory object based on a freq array
	public LetterInventory(int[] freq) {
		sizeCount = 0;
		// change internal container to refer to passed frequencies
		letterFreq = freq;
		// initialize the global size to the calculated size
		sizeCount = getValidSize(letterFreq);
	}
	
	// return frequency of a certain character
	public int get(char tgt) {
		// to represent case insensitivity
		char lower = Character.toLowerCase(tgt);		
		if(!('a' <= lower && lower <= 'z')) {
			throw new IllegalArgumentException("Not an english letter!");
		}
		
		return letterFreq[lower - ASCII_OFFSET];	
	}
	
	// return the total number of letters stored in inventory
	public int size() {
		return sizeCount;
	}
	
	private int getValidSize(int[] freq) {	
		int currentSize =  0;		
		// iterate and find total number of letters present in array
		for(int i = 0; i < freq.length; i++) {
			if(freq[i] > 0) {
				// account for frequency of letters in total size
				currentSize += freq[i];
			}
		}
		return currentSize;
	}
	
	public boolean isEmpty() {
		// boolean expression returning true if size is 0
		return (size() == 0);
	}
	
	public String toString() {
		StringBuilder letters = new StringBuilder(); 	
		// loop through frequency array and build character list
    	for(int i = 0; i < letterFreq.length; i++) {
    		if(letterFreq[i] > 0) {
    			for(int j  = 0 ; j  < letterFreq[i]; j++) {
    				letters.append((char)(i + ASCII_OFFSET));
    			}
    		}
    	}	
    	return letters.toString();
	}
	
	private int[] getInventory() {
		// return reference to frequency array
		return letterFreq;
	}
	
	public LetterInventory add(LetterInventory other) {
		// check precondition that other paramater is not null
		if(other == null) {
			throw new IllegalArgumentException("Null letter inventory!");
		}
		
		int[] thisInventory  = this.getInventory();
		int[] otherInventory = other.getInventory();
		int[] addInventory = new int[NUM_LETTERS];
		
		// iterate through both inventories and save sum
		for(int i = 0; i < thisInventory.length; i++) {
			addInventory[i] = thisInventory[i] + otherInventory[i];
		}
		
		// return reference to a new letterinventory object with sum frequencies
		return new LetterInventory(addInventory);	
	}
	
	// returns reference to letter inventory with subtracted frequencies
	public LetterInventory subtract(LetterInventory other) {
		if(other == null) {
			throw new IllegalArgumentException("Null letter inventory!");
		}
		
		int[] thisInventory  = this.getInventory();
		int[] otherInventory = other.getInventory();
		int[] subtractInventory = new int[NUM_LETTERS];
		
		for(int i = 0; i < thisInventory.length; i++) {
			int subtractVal =  thisInventory[i] - otherInventory[i];
			// means unequal amount of letter frequencies
			// one word can't fit into another LetterInventory
			if(subtractVal < 0) {
				return null;
			}
			// save difference to new letter inventory
			subtractInventory[i] = subtractVal;	
		}
		// return reference to new letterinventory object with diff frequencies
		return new LetterInventory(subtractInventory);	
	}
	
	public boolean equals(Object other) {
		
		// checks if other object is a type of LetterInventory
		if(other instanceof LetterInventory) {
			
			int[] thisInventory  = this.getInventory();
			// cast parameter object as a letter inventory
			LetterInventory otherLetters = ((LetterInventory)(other));
			int[] otherInventory = otherLetters.getInventory();
    
    		
    		// lists aren't equal if sizes don't match
    		if(this.size() !=  otherLetters.size()) {
    			return false;
    		}
    		// empty lists are equal
    		if(this.size() == 0 && otherLetters.size() == 0) {
    			return true;
    		}
    		
    		// checks all the elements in both inventories to ensure equality
    		for(int i = 0; i < thisInventory.length; i++) {
    			if(thisInventory[i] != otherInventory[i]) {
    				return false;
    			}
    		}
    		// if all cases passed, objects are equal
    		return true;
		}
		else {
			return false;
		}
	}
	
}
