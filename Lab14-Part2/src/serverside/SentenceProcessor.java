package serverside;

public class SentenceProcessor {
	
	private int size = 65535;
	
	private String sentence;
	
	
	public SentenceProcessor(byte[] byteData) {
		
		this.sentence = new String(byteData);
	}
	
	public String getSentence() {
		
		return sentence;
	}
	
	/**
	 * This method convert value into an array of byte
	 * @param length
	 * @return
	 */
	public byte[] convertToByteArray(int value) {
		
		byte[] outData = new byte[size];
		
		String stringValue = Integer.valueOf(value).toString();
		
        outData = stringValue.getBytes();
        
        return outData;
	}
	
	/**
	 * This method count the number of characters in a sentence
	 * @return
	 */
	public int countCharacters() {
		
        int index = 0;
        char currentChar = sentence.charAt(index);
        while (currentChar != '\0') {
        	
        	currentChar = sentence.charAt(index++);
        }
        

        return index - 1;
	}

	public int countVowels() {
		int count = 0;
        String lowercaseSentence = sentence.toLowerCase();
        for (int i = 0; i < lowercaseSentence.length(); i++) {
            char c = lowercaseSentence.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
	}

	public int countConsonants() {
		int count = 0;
        String lowercaseSentence = sentence.toLowerCase();
        for (int i = 0; i < lowercaseSentence.length(); i++) {
            char c = lowercaseSentence.charAt(i);
            if (Character.isLetter(c) && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                count++;
            }
        }
        return count;
	}

	public int countPunctuations() {
		int count = 0;
		String lowercaseSentence = sentence.toLowerCase();
        for (int i = 0; i < lowercaseSentence.length(); i++) {
	        char c = sentence.charAt(i);
	        if (c == ',' || c == '.' || c == '!' || c == '?' || c == ':' || c == '-') {
	            count++;
	        }
	    }
	    return count;
	}

}