package comp1721.cwk1;

import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;


public class Guess {
  public static String chosenWord;
  public static int guessNumber;
  public static List<String> WordList;
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);

  // TODO: Implement constructor with int parameter
  public Guess(int num){
      num = 0;
  }
  // TODO: Implement constructor with int and String parameters
  public Guess(String chosenWord,int num){
    num = 0;
    chosenWord = getChosenWord();
  }
  // TODO: Implement getGuessNumber(), returning an int

  public static int getGuessNumber() {

    return guessNumber;
  }

  public static String removeAccents(String target){
    return Normalizer.normalize(target,Normalizer.Form.NFD).replace("[^\\p{ASCII}]", "");
  }

  // TODO: Implement getChosenWord(), returning a String
  public static String getChosenWord() {
    Scanner sc = new Scanner(System.in);
    String chosenWord_1 = sc.nextLine();
    chosenWord = chosenWord_1.toLowerCase();
    chosenWord = removeAccents(chosenWord);

    while ((chosenWord.length() != 5) || (WordList.contains(chosenWord))){
      if((chosenWord.length()) != 5){
        System.out.println("This word" + chosenWord + "has wrong length which does not have 5 letters");
      }else {
        System.out.println("This word" + chosenWord + "does not exist in the word list.");
      }
      System.out.println("Please enter the correct length '5' letters:");
      chosenWord_1 = sc.nextLine();
      chosenWord = chosenWord_1.toLowerCase();
      chosenWord = removeAccents(chosenWord);
    }
    return chosenWord;
  }
  // TODO: Implement readFromPlayer()
  public void readFromPlayer(){

  }
  // TODO: Implement compareWith(), giving it a String parameter and String return type
  public String compareWith(String target){
    String t = "";
    int i = 0;
    for(i = 0;i < chosenWord.length();i++){
      if(target.charAt(i) == chosenWord.charAt(i)){
          //t = t.concat();
      }
    }
    return target;
  }
  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(){

    return true;
  }
}
