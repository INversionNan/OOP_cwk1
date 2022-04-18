package comp1721.cwk1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Game {
    public int gameNumber;
    public String target;
    WordList wordList;
    Guess guess;
    String res = "";
    boolean isAccessible = false;
  // TODO: Implement constructor with String parameter
  public Game(String fileName) throws IOException {
      wordList = new WordList(fileName);
      LocalDate now = LocalDate.now();
      LocalDate time = LocalDate.of(2021,6,19);
      int days = Math.toIntExact(time.toEpochDay()-now.toEpochDay());
      this.gameNumber = days;
  //        System.out.println(days);
      if(this.gameNumber < this.wordList.size()) {
          this.target = this.wordList.getWord(this.gameNumber);
      }else {
          throw  new GameException("gameNumber is too big.");
      }
  }

  // TODO: Implement constructor with int and String parameters
  public Game(int gameNumber,String fileName) throws IOException {
    wordList = new WordList(fileName);
    this.gameNumber = gameNumber;
    this.target = this.wordList.getWord(gameNumber);
  }

  public Game(int num, String filename, boolean isAccessible) throws IOException  {
    wordList = new WordList(filename);
    this.gameNumber = num;
    this.target = this.wordList.getWord(num);
    this.isAccessible = isAccessible;
  }
  // TODO: Implement play() method
  public void play(){
      boolean isplay = true;
      guess = new Guess(6);
      System.out.println("WORDLE"+this.gameNumber);
//        for(int i = 1; i < 7;i++){
//            System.out.printf("Enter guess (%d/6)", i);
//            chosenWord = getChosenWord();
//            result = compareWith(chosenWord);
//            if(result.equals("Well done!")){
//                System.out.println(result);
//                break;
//            }
//        }
      while (isplay){
          guess.readFromPlayer();
          if(!this.isAccessible){
              String str = guess.compareWith(this.target)+"\n";
              res += str;
              System.out.print(str);
              isplay = guess.matches(this.target);
          }else {
              String str = guess.compareWithInAccess(this.target)+"\n";
              res += str;
              System.out.print(str);
          }
      }
  }
  // TODO: Implement save() method, with a String parameter
  public void save(String fileName) throws IOException {
      File file = new File(fileName);
      RandomAccessFile src = new RandomAccessFile(file,"rw");
      src.writeChars(res.substring(0,res.length()-2));
  }

    public static void main(String[] args) throws IOException {
//        Game g = new Game(3,"data/words.txt");
        Game g = new Game(3, "data/words.txt",true);
        g.play();
        g.save("build/lastgame.txt");
    }
}
