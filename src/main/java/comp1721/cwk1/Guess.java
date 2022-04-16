package comp1721.cwk1;

import java.util.Scanner;


public class Guess {
  public static String chosenWord;
  public static int guessNumber;
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);

  // TODO: Implement constructor with int parameter
  public Guess(String chosenWord){

  }
  // TODO: Implement constructor with int and String parameters
  public Guess(String chosenWord){

  }
  // TODO: Implement getGuessNumber(), returning an int

  public static int getGuessNumber() {
    return guessNumber;
  }

  // TODO: Implement getChosenWord(), returning a String

  public static String getChosenWord() {
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
          t = t.concat();
      }
    }
  }
  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(){
    return true;
  }
}
