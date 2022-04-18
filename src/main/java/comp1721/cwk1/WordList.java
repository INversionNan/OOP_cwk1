package comp1721.cwk1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {

    public static String target;
    List<String> words = new ArrayList<>();

  // TODO: Implement constructor with a String parameter
  public WordList(String fileName) throws IOException {
      File file = new File(fileName);
      if(!file.exists()){
          System.out.println("There is not a word file.");
          file.createNewFile();
      }
      BufferedReader bR = new BufferedReader(new FileReader(file));
      String word;
      while((word = bR.readLine())!= null){
          this.words.add(word);
      }
  }
  // TODO: Implement size() method, returning an int
    public int size() throws IOException {return words.size();}
  // TODO: Implement getWord() with an int parameter, returning a String
  public String getWord(int n) throws IOException {
      if(n < 0 || n > this.size()-1){
          throw new GameException("Invalid game number.");
      }else {
          target = words.get(n);
      }
      return target;
  }
}
