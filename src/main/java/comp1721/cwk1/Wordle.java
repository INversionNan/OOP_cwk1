// Main program for COMP1721 Coursework 1
// DO NOT CHANGE THIS!

package comp1721.cwk1;

import java.io.IOException;
import java.util.Random;


public class Wordle {
  public static Random word;
  public static String chosenWord;
  public static void main(String[] args) throws IOException {
    Game game;

    if (args.length > 0) {
      // Player wants to specify the game
      game = new Game(Integer.parseInt(args[0]), "data/words.txt");
    }
    else {
      // Play today's game
      game = new Game("data/words.txt");
    }
    WordList wl = new WordList("data/words.txt");
    game.play();
    game.save("build/lastgame.txt");
  }
}
