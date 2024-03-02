package wordle;

import wordle.Display;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameDriver {

    public static void main(String[] args) {
        String[] words = readFile("words.txt");
        assert words != null;
        String answer = words[new Random().nextInt(words.length)].toUpperCase();

        WordleBoard board = new WordleBoard(words);

        Scanner sc = new Scanner(System.in);
        Display display = new Display(board, sc);
        while (!board.isGameOver()) {
            display.print();
            display.promptGuess();
        }
        display.print();
        if (board.didWin()) {
            System.out.println("Congratulations, you win!");
        } else {
            System.out.println("Sorry, you did not guess correctly");
        }
        System.out.println("The answer was " + board.getWord());
        sc.close();
    }

    private static String[] readFile(String filename) {
        ArrayList<String> wordList = new ArrayList<>();
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine().toUpperCase();
                wordList.add(word);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return null;
        }
        return wordList.toArray(new String[0]);
    }
}
