// Nafil Atiq
// Kinda a dumb implementation but OH WELL.
import java.util.*;
import java.io.*;

public class SpellChecker {
    public Hashtable<Integer, LinkedList<String>> words;
    // we will use the length of said word as a key, with a list to have multiple words per key
    public SpellChecker(File dict) throws FileNotFoundException{ //create a spell checker with said dictionary of words.
        words = new Hashtable<Integer, LinkedList<String>>();
        //reading file
        Scanner sc = new Scanner(dict); //reads the file
        while (sc.hasNextLine()){ //lower case to make it easier to compare
            String word = sc.nextLine().toLowerCase();
            if (words.get(word.length())==null){ //if there is no table for this key, make a new one
                words.put(word.length(),new LinkedList<String>());
                words.get(word.length()).add(word);
            }else{ // there is a table in this key, add this word to it.
                words.get(word.length()).add(word);
            }
        }
    }
    private LinkedList<String> checkWord(String s, int length){
        LinkedList<String> result = new LinkedList<String>();
        //in the case that the length of the word is too short or the list for that key does not exist
        if (words.get(length) == null || length<=1){
            return result; // returns an empty table
        }
        for(String word : words.get(length)){
            if (word.equals(s)){ // if that's the exact word.
                result.add(s);
                return result;
            }

            int similarity = 0; //sees how much different the char arrays are from eachother.
            for (char dictChar : word.toCharArray()){ //nested for loop to compare each individual character.
                char[] wordChars = s.toCharArray();
                for (int i = 0;i<wordChars.length;i++){
                    if (dictChar == wordChars[i]){
                        similarity++; //if this is within the acceptable range, the word is this.
                        wordChars[i] = ' ';//remove it so that it's not miscounted with another char
                    }
                }
            }
            if (length-similarity<=1){ // if the word is within the accepted range
                //the word is found from subtracting length from similarity, which shows the word's deviance
                result.add(word);
            }
        }
        return result; // return the list of potential correct words
    }
    public LinkedList<String> spellcheck(String s){
        //first we compare the term to words its length
        LinkedList<String> checkedWords = checkWord(s,s.length());

        LinkedList<String> shorterWords = checkWord(s,s.length()-1);
        // check words that are shorter than the list, and add the most similar
        LinkedList<String> longerWords = checkWord(s,s.length()+1);
        //do the same with the longer words.

        //combine all lists.
        checkedWords.addAll(shorterWords);
        checkedWords.addAll(longerWords);

        //in the last case, the word simply doesn't exist in the dictionary
        if (checkedWords.size() == 0){
            checkedWords.add(s); // this is a list only containing s itself.
        }
        return checkedWords;
    }
}
