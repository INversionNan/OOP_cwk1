package comp1721.cwk1;


import java.io.*;
import java.util.List;

public class WordList {
  // TODO: Implement constructor with a String parameter
  public WordList(){
      List<String> WordList;
  }
  // TODO: Implement size() method, returning an int
    public int size() throws IOException {
        int size = 0;
        String dir = "data\\words.txt";
        File file = new File(dir);
        if(!file.exists()){
            file.createNewFile();
        }
        //BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine())!=null){

            size ++;
        }
        return size;
    }
  // TODO: Implement getWord() with an int parameter, returning a String
  public String getWord(int n){

  }
}
