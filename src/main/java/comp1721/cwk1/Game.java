package comp1721.cwk1;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Game {
    public int gameNumber;
    public String target;
    WordList wordList;
    Guess guess;
    String res = "";
    boolean isAccessible = false;
    String c = "a";
  // TODO: Implement constructor with String parameter
  public Game(String fileName) throws IOException {
      wordList = new WordList(fileName);
      LocalDate now = LocalDate.now();
      LocalDate time = LocalDate.of(2021,6,19);
      int days = Math.toIntExact(now.toEpochDay()-time.toEpochDay());
      this.gameNumber = days;
  //        System.out.println(days);
      if(this.gameNumber < this.wordList.size()) {
          this.target = this.wordList.getWord(this.gameNumber);
      }else {
          throw  new GameException("gameNumber is too big.");
      }
      this.c = "a";
  }

  // TODO: Implement constructor with int and String parameters
  public Game(int gameNumber,String fileName) throws IOException {
    wordList = new WordList(fileName);
    this.gameNumber = gameNumber;
    this.target = this.wordList.getWord(gameNumber);
    this.c = "a";
  }

//  public Game(int num, String filename, boolean isAccessible) throws IOException  {
//    wordList = new WordList(filename);
//    this.gameNumber = num;
//    this.target = this.wordList.getWord(num);
//    this.isAccessible = isAccessible;
//  }

    public Game(String a,int num, String filename) throws IOException  {
        wordList = new WordList(filename);
        this.gameNumber = num;
        this.target = this.wordList.getWord(num);
        this.c = a;
    }
  // TODO: Implement play() method
  public void play(){
      int count = 0;
      boolean isplay = false;
      guess = new Guess(6);
      System.out.println("WORDLE "+this.gameNumber+"\n");
//        for(int i = 1; i < 7;i++){
//            System.out.printf("Enter guess (%d/6)", i);
//            chosenWord = getChosenWord();
//            result = compareWith(chosenWord);
//            if(result.equals("Well done!")){
//                System.out.println(result);
//                break;
//            }
//        }
      while (!isplay && count < 6){
          guess.readFromPlayer();
          //if(!this.isAccessible){
          if(Objects.equals(this.c, "a")){
              String str = guess.compareWith(this.target)+"\n";
              res += str;
              System.out.print(str);
              isplay = guess.matches(this.target);
              count++;
          }else {
              String str = guess.compareWithInAccess(this.target)+"\n";
              res += str;
              System.out.print(str);
              if(str.equals("You won!\n"))break;
              count++;
          }
      }
  }
  // TODO: Implement save() method, with a String parameter
  public void save(String fileName) throws IOException {
      File file = new File(fileName);
      if(file.exists())file.delete();
      RandomAccessFile src = new RandomAccessFile(file,"rw");
      src.writeChars(res.substring(0,res.length()-2));
      src.close();
  }

  public void save_history(String fileName) throws IOException {
      int winningStreak = 0;
      int longestWinningStreak = 0;
      int compareWinningStreak = 0;
      int gameNumberComplete = 0;
      int winningNumber = 0;
      int num_1 = 0,  num_2 = 0, num_3 = 0, num_4 = 0,num_5 = 0;
      int cot = 0;
      String line;
      File file = new File(fileName);
      //if(file.exists())file.delete();
      RandomAccessFile his = new RandomAccessFile(file,"r");
//      while((line=(src.readLine())) != null) {
//          int num = Integer.parseInt(line);
//      }
//      RandomAccessFile his = new RandomAccessFile(file,"rw");
//      BufferedReader his = new BufferedReader(new FileReader(file));
      while((line=(his.readLine())) != null) {
          if(num_1 == 0 && cot <1){
              num_1 = Integer.parseInt(line);
              //gameNumberComplete = Integer.parseInt(line);
              cot++;
              continue;
          }
          if(num_2 == 0 && cot <2){
              num_2 = Integer.parseInt(line);
              cot++;
              continue;
          }
          if(num_3 == 0 && cot <3){
              num_3 = Integer.parseInt(line);
              cot++;
              continue;
          }
          if(num_4 == 0 && cot <4){
              num_4 = Integer.parseInt(line);
              cot++;
              continue;
          }
          if(num_5 == 0 && cot <5){
              num_5 = Integer.parseInt(line);
              cot++;
          }
      }

      his.close();
      gameNumberComplete = num_1;
      gameNumberComplete++;
      winningNumber = num_2;
      winningStreak = num_3;
      compareWinningStreak = num_4;
      if(guess.matchesInAccess(target)) {
          winningStreak++;
          winningNumber++;
      } else winningStreak = 0;

      longestWinningStreak = Math.max(winningStreak, compareWinningStreak);
      //compareWinningStreak = longestWinningStreak;
      double percentage = ((double)winningNumber/gameNumberComplete) * 100;
      System.out.println("Your number of game played is "+ gameNumberComplete);
      System.out.println("Your percentage of games that were wins is "+ ((double)winningNumber/gameNumberComplete) * 100+"%");
      System.out.println("Your length of current winning streak is "+ winningStreak);
      System.out.println("Your longest winning streak is "+ longestWinningStreak);
//      his.writeInt(gameNumberComplete);
//      his.writeInt(winningNumber);
//      his.writeInt(winningStreak);
//      his.writeInt(longestWinningStreak);
      PrintWriter write = new PrintWriter(new FileWriter(fileName));
      write.println(gameNumberComplete);
      write.println(winningNumber);
      write.println(winningStreak);
      write.println(longestWinningStreak);
      write.flush();
      write.close();
  }
    /*public static void main(String[] args) throws IOException {
//        Game g = new Game(3,"data/words.txt");
        Game g = new Game(3, "data/words.txt",true);
        g.play();
        g.save("build/lastgame.txt");
    }*/
}
