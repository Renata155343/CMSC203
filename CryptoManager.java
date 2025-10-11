/*
 * Class: CMSC203 
 * Instructor: huseyin aygun
 * Description: (encrypting/decrypting)
 * Due: 10/13/2025
 * Platform/compiler: elcipse
 * I pledge that I have completed the programming  assignment independently. 
*  I have not copied the code from a student or any source. 
*  I have not given my code to any student.
*  Print your Name here: renata podlesny
*/


import java.util.ArrayList;

/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */

public class CryptoManager { 

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_";
    private static final char FILLER = 'X';

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 */   

    public static String vigenereEncryption(String plainText, String key) {
    	if (!isStringInBounds(plainText)) //if the string given is not in bounds
            return "";
       
    	int plainTextLength = plainText.length();
        char[] encryptedChars = new char[plainTextLength]; //array to store encrypted result
        int keyLength = key.length();
        
        for (int i = 0; i < plainTextLength; i++) 
        {
            char plainChar = plainText.charAt(i); //get current text char
            char keyChar = key.charAt(i % keyLength); //finding corresponding key char
            
            int plainPosition = plainChar - LOWER_RANGE; //convert to right position values
            int keyPosition = keyChar - LOWER_RANGE;

            int encryptedPosition = (plainPosition + keyPosition) % RANGE; //the actual encryption number result

            char encryptedChar = (char) (encryptedPosition + LOWER_RANGE); //comvert number to ASCII char

            encryptedChars[i] = encryptedChar; //assign char to the right index
        }
        
        return new String(encryptedChars); //converts out char array to a string for result
    }

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
    	if (!isStringInBounds(encryptedText)) //if the string given is not in bounds
            return "";
       
    	int encryptedTextLength = encryptedText.length();
        char[] decryptedChars = new char[encryptedTextLength]; //array to store decrypted result
        int keyLength = key.length();
        
        for (int i = 0; i < encryptedTextLength; i++) 
        {
            char encryptedChar = encryptedText.charAt(i); //get current encrypted char
            char keyChar = key.charAt(i % keyLength); //finding corresponding key char
            
            int encryptedPosition = encryptedChar - LOWER_RANGE; //convert to right position values
            int keyPosition = keyChar - LOWER_RANGE;

            int decryptedPosition = (encryptedPosition - keyPosition + RANGE) % RANGE; //the actual decryption number result

            char decryptedChar = (char) (decryptedPosition + LOWER_RANGE); //convert number to ASCII char

            decryptedChars[i] = decryptedChar; //assign char to the right index
        }
        
        return new String(decryptedChars); //converts out char array to a string for result
    }


	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 */    

    public static String playfairEncryption(String plainText, String key) {
    	if (!isStringInBounds(plainText)) //if the string given is not in bounds
            return "";
        
        String cleanedText = tidyPlainText(plainText);
        char[][] matrix = makePlayfairMatrix(key);
        
        char[] encryptedChars = new char[cleanedText.length()]; //array to store encrypted result
        
        //so that it processes two chars at a time
        for (int i=0; i<cleanedText.length(); i+=2) {
            char char1 = cleanedText.charAt(i);
            char char2 = cleanedText.charAt(i + 1);
            
            //to find where each char is
            int[] coordinate1 = findCoordinates(char1, matrix);
            int[] coordinate2 = findCoordinates(char2, matrix);
            
            int row1 = coordinate1[0], column1 = coordinate1[1];
            int row2 = coordinate2[0], column2 = coordinate2[1];
            
            char encrypted1, encrypted2;

            if (row1 == row2) { //to shift right if same row
                encrypted1 = matrix[row1][(column1 + 1) % 8];
                encrypted2 = matrix[row2][(column2 + 1) % 8];
            } else if (column1 == column2) { //shift down if same column
                encrypted1 = matrix[(row1 + 1) % 8][column1];
                encrypted2 = matrix[(row2 + 1) % 8][column2];
            } else { //otherwise need to swap columns
                encrypted1 = matrix[row1][column2]; // A takes B's column
                encrypted2 = matrix[row2][column1]; // B takes A's column
            }
            
            encryptedChars[i] = encrypted1; //assign first encrypted char
            encryptedChars[i + 1] = encrypted2; //assign second encrypted char
        }
        
        return new String(encryptedChars); //converts out char array to a string for result
    }

    public static String playfairDecryption(String encryptedText, String key) {
    	if (!isStringInBounds(encryptedText)) //if the string given is not in bounds
            return "";
        
        char[][] matrix = makePlayfairMatrix(key);
        char[] decryptedChars = new char[encryptedText.length()]; //array to store decrypted result
        
        //so that it processes two chars at a time
        for (int i = 0; i < encryptedText.length(); i += 2) {
            char char1 = encryptedText.charAt(i);
            char char2 = encryptedText.charAt(i + 1);
            
            //to find where each char is
            int[] coordinate1 = findCoordinates(char1, matrix);
            int[] coordinate2 = findCoordinates(char2, matrix);
            
            int row1 = coordinate1[0], column1 = coordinate1[1];
            int row2 = coordinate2[0], column2 = coordinate2[1];
            
            char decrypted1, decrypted2;

            if (row1 == row2) //same row means shift left
            { 
                decrypted1 = matrix[row1][(column1 - 1 + 8) % 8];
                decrypted2 = matrix[row2][(column2 - 1 + 8) % 8];
            } else if (column1 == column2) //same column means shift up
            {
                decrypted1 = matrix[(row1 - 1 + 8) % 8][column1];
                decrypted2 = matrix[(row2 - 1 + 8) % 8][column2];
            } else //otherwise swap columns like the encryption
            {
                decrypted1 = matrix[row1][column2]; 
                decrypted2 = matrix[row2][column1];
            }
            
            decryptedChars[i] = decrypted1; //assign first decrypted char
            decryptedChars[i + 1] = decrypted2; //assign second decrypted char
        }
        
        return new String(decryptedChars); //converts out char array to a string for result
    }

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */    
 
    public static String caesarEncryption(String plainText, int key) {
    	 if (!isStringInBounds(plainText)) //if the string given is not in bounds
             return "";

         int normalizedKey = key % RANGE; //so the key fits in our range
         
     	int plainTextLength = plainText.length();
         char[] encryptedChars = new char[plainTextLength]; //array to store encrypted result
         
         for (int i = 0; i < plainTextLength; i++) 
         {
             char plainChar = plainText.charAt(i);  
             int plainPosition = plainChar - LOWER_RANGE; 
             
             int encryptedPosition = (plainPosition + normalizedKey) % RANGE; //the actual encryption number result
             char encryptedChar = (char) (encryptedPosition + LOWER_RANGE); //convert number back to ASCII char

             encryptedChars[i] = encryptedChar; //assign char to the correct index
         }
         
         return new String(encryptedChars); //converts out char array to a string for result
    }

    // Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {
    	if (!isStringInBounds(encryptedText)) //if the string given is not in bounds
            return "";

        int normalizedKey = key % RANGE; //so its in the range
        
    	int encryptedTextLength = encryptedText.length();
        char[] decryptedChars = new char[encryptedTextLength]; //array to store decrypted result
        
        for (int i = 0; i < encryptedTextLength; i++) 
        {
            char encryptedChar = encryptedText.charAt(i); 
            int encryptedPosition = encryptedChar - LOWER_RANGE;
  
            int decryptedPosition = (encryptedPosition - normalizedKey + RANGE) % RANGE; //the actual decryption number result. basically opposite of encryption
            char decryptedChar = (char) (decryptedPosition + LOWER_RANGE); //convert number back to ASCII char

            decryptedChars[i] = decryptedChar; //assign char to the right index
        }
        
        return new String(decryptedChars); //converts out char array to a string for result
    }    

    
    //my added methods
    private static String tidyPlainText(String plainText) {
        String uppercase = plainText.toUpperCase();
        
        ArrayList<Character> cleanedChars = new ArrayList<>();
        
        for (char c : uppercase.toCharArray()) 
        {
            if (c >= LOWER_RANGE && c <= UPPER_RANGE) //to make sure the char exists in our list
                cleanedChars.add(c);
        }

        for (int i = 0; i < cleanedChars.size(); i += 2) //to check two indices apart
        { 
            if (i == cleanedChars.size() - 1) 
            {
                cleanedChars.add(FILLER);
                break;
            }
            if (cleanedChars.get(i) == cleanedChars.get(i + 1)) //make sure there arent identical chars next to each other
                cleanedChars.add(i + 1, FILLER); 
        }
        
        //so the list can be a normal array
        char[] finalArray = new char[cleanedChars.size()];
        for (int i = 0; i < cleanedChars.size(); i++) {
            finalArray[i] = cleanedChars.get(i);
        }
        
        return new String(finalArray);
    }
    
    private static char[][] makePlayfairMatrix(String key) {
    	char[][] finalMatrix = new char[8][8];
        String fullKey = key.toUpperCase() + ALPHABET64;
        
        char[] uniqueChars = new char[64]; //64 is the max
        int uniqueCount = 0; //to see how mnay unique chars are added

        boolean[] seen = new boolean[RANGE]; //to track what chars have been seen base don where they are in range
        
        for (int i = 0; i < fullKey.length(); i++) //finding the unique chars and filling the array with them
        {
            char current = fullKey.charAt(i);

            if (isStringInBounds(String.valueOf(current))) 
            {
                int position = current - LOWER_RANGE;

                if (!seen[position]) //to see if we have seen it before
                {
                    seen[position] = true;
                    uniqueChars[uniqueCount] = current; //if we didnt see it then it can be added to our array
                    uniqueCount++; 
                }
            }
            if (uniqueCount == 64) //if we somehow manage to fill the whole array
                break;
        }

        for (int i = 0; i < 64; i++) //to actually populate the array
        {
            int row = i / 8;
            int column = i % 8; 
            finalMatrix[row][column] = uniqueChars[i];
        }

        return finalMatrix;
    }
    
    private static int[] findCoordinates(char current, char[][] matrix) {
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (matrix[i][j] == current) 
                    return new int[]{i, j};
            }
        }
        return new int[]{-1, -1}; //edge case scenario
    }
}


