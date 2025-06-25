import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class SpellCheckerMain {
    public static void main(String[] args) throws FileNotFoundException{
        //creates the new spellchecker from a dictionary file.
        File dict = new File("dictionary.txt");
        SpellChecker checker = new SpellChecker(dict);

        //user input to get an input phrase to be corrected.
        Scanner sc = new Scanner(System.in);
        out.print("Enter in a sentence to be corrected: ");
        String input = sc.nextLine();
        //this resultant will consist of the corrected words.
        String result = "";
        out.println("Original input: "+input);

        for (String word: input.split(" ")){
            word = word.toLowerCase(); //since that's how we're meant to compare it.
            LinkedList<String> checkedWords = checker.spellcheck(word);
            if (checkedWords.size() == 1){
                result = result + checkedWords.get(0); //adds the singular right word to the resultant rather than the list
            }else{
                result = result + checkedWords; // adds the potential words to the resultant
            }
            //add a space in between
            result = result + " ";
            //display the correction made
            out.println(word+ " -> " + checker.spellcheck(word));
        }
        out.println("Resultant string: "+ result );
    }
}
