package comp1721.cwk1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordList {

    public static String target;
    List<String> wl = new ArrayList<>();

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
          this.wl.add(word);
      }
  }
  // TODO: Implement size() method, returning an int
    public int size() throws IOException {
        int size = 0;
        String dir = "data\\words.txt";
        File fp = new File(dir);
        if(!fp.exists()){
            System.out.println("There is not a word file.");
            fp.createNewFile();
        }
        //BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        BufferedReader br = new BufferedReader(new FileReader(fp));
        String line;
        while ((line = br.readLine())!=null){
            size ++;
        }
        return size;
    }
  // TODO: Implement getWord() with an int parameter, returning a String
  public String getWord(int n){
      return target;
  }
}
