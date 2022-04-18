package comp1721.cwk1;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Guess {
  public int guessNumber;
  public List<String> WordList;
  int count = 1;
  String chosenword = "";
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);

  // TODO: Implement constructor with int parameter
  public Guess(int num){
      if(num < 1 || num > 6){
        throw new GameException("This number has wrong range.GuessNumber is in the allowed range of 1–6.");
      }
      this.guessNumber = num;
  }
  // TODO: Implement constructor with int and String parameters
  public Guess(int num, String chosenWord){
    if(num < 1 || num > 6){
      throw new GameException("This number has wrong range.GuessNumber is in the allowed range of 1–6.");
    }
    if(!Pattern.compile("(?i)[A-Z]]").matcher(chosenWord.toUpperCase()).find()){
      throw new GameException("This chosen does not have all letters.");
    }
    if(chosenWord.length() != 5) {
      throw new GameException("word consists of 5 alphabetic characters.");
    }
    this.guessNumber = num;
    this.chosenword = chosenWord.toUpperCase();
  }
  // TODO: Implement getGuessNumber(), returning an int

  public int getGuessNumber() {
//    Scanner sc = new Scanner(System.in);
//    guessNumber = sc.nextInt();
//    if(guessNumber<0 || guessNumber > WordList.size()){
//      throw new GameException("Your guess Number is invalid.");
//    }
    return this.guessNumber;
  }

  public static String removeAccents(String target){
    return Normalizer.normalize(target,Normalizer.Form.NFD).replace("[^\\p{ASCII}]", "");
  }

  // TODO: Implement getChosenWord(), returning a String
  public String getChosenWord() {
//    Scanner sc = new Scanner(System.in);
//    String chosenWord_1 = sc.nextLine();
//    chosenWord = chosenWord_1.toUpperCase();
//    chosenWord = removeAccents(chosenWord);
//
//    while ((chosenWord.length() != 5) || (WordList.contains(chosenWord))){
//      if((chosenWord.length()) != 5){
//        System.out.println("This word" + chosenWord + "has wrong length which does not have 5 letters");
//      }else {
//        System.out.println("This word" + chosenWord + "does not exist in the word list.");
//      }
//      System.out.println("Please enter the correct length '5' letters:");
//      chosenWord_1 = sc.nextLine();
//      chosenWord = chosenWord_1.toLowerCase();
//      chosenWord = removeAccents(chosenWord);
//    }
    return this.chosenword;
  }

  // TODO: Implement readFromPlayer()
  public void readFromPlayer(){
    System.out.print("Enter guess ("+this.count+"/"+this.guessNumber+"): ");
    this.chosenword = INPUT.nextLine().toUpperCase();
    this.count++;
  }

  // TODO: Implement compareWith(), giving it a String parameter and String return type
  public String compareWith(String target){
//    String t = "";
//    int i,j;
//    int cot = 0;
//    for(i = 0;i < chosenWord.length(); i++){
//      for(j = 0; j < chosenWord.length(); j++){
//        if(target.charAt(i) == chosenWord.charAt(i)){
//          System.out.printf("\033[30;102m %c \033[0m",chosenWord.charAt(i));
//          cot++;
//        }else if(target.charAt(i) == chosenWord.charAt(i + j)){
//          System.out.printf("\033[30;103m %c \033[0m",chosenWord.charAt(i));
//        }else {
//          System.out.printf("\033[30;107m %c \033[0m",chosenWord.charAt(i));
//        }
//      }
//    }
//    if(cot == 5){
//      t = "Well done!";
//    }else {
//      t = "Failed";
//    }
//    return t;
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < this.chosenword.length() && i < target.length(); i++){
      if(target.charAt(i) == this.chosenword.charAt(i)){
        sb.append("\033[30;102m " + this.chosenword.charAt(i) + " \033[0m");
      }else if(target.indexOf(this.chosenword.charAt(i)) != -1){
        sb.append("\033[30;103m " + this.chosenword.charAt(i) + " \033[0m");
      }else {
        sb.append("\033[30;107m " + this.chosenword.charAt(i) + " \033[0m");
      }
    }
    return sb.toString();
  }

  public String compareWithInAccess(String target) {
    int j = 0;
    int k = 0;
    StringBuffer sb = new StringBuffer();
    String accessible[] = {"1st", "2nd", "3rd", "4th", "5th"};
    String[] correctButWrongPlace = new String[target.length()];
    String perfect[] = new String[target.length()];
    for (int i = 0; i < this.chosenword.length() && i < target.length(); i++) {
      if (target.charAt(i) == this.chosenword.charAt(i)) {
        perfect[k++] = accessible[i];
      } else if (target.indexOf(this.chosenword.charAt(i)) != -1) {
        correctButWrongPlace[j++] = accessible[i];
      }
    }
    if (k == target.length()) {
      return new String("You won!");
    }
    int a = 0;
    while (a < j) {
      if (a < j - 2) {
        sb.append(correctButWrongPlace[a++] + ",");
      } else if (a == j - 2) {
        sb.append(correctButWrongPlace[a++] + " and ");
      } else {
        sb.append(correctButWrongPlace[a++] + " correct but in wrong place");
      }
    }
    if (j != 0 && k > 0) {
      sb.append(", ");
    }
    a = 0;
    while (a < k) {
      if (a < k - 2) {
        sb.append(perfect[a++] + ", ");
      } else if (a == k - 2) {
        sb.append(perfect[a++] + " and ");
      } else {
        sb.append(perfect[a++] + " perfect");
      }
    }
    return sb.toString();
  }
  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(String target){
    if(target.equals(this.chosenword)){
      switch (this.count-1){
        case 1:
          System.out.println("Superb - Got it in one!");
          return false;
        case 2: case 3: case 4: case 5:
          System.out.println("Well done!");
          return false;
        case 6:
          System.out.println("That was a close call!");
          return false;
        default:
          return false;
      }
    }else {
      if(this.count > 6){
        System.out.println("Nope - Better luck next time!");
        System.out.println(target);
        return false;
      }
    }
    return true;
  }
  public boolean matchesInAccess(String target){
    return false;
  }
  public static void main(String[] args) {
    Guess g = new Guess(1,"CAUSE");
    System.out.println(g.compareWithInAccess("PAUSE"));
  }
}
