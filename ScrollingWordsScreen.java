package pcd.lab01.ex01;

import static pcd.lab01.ex01.AuxLib.getWordsPos;

import java.util.List;
import java.util.ArrayList;


public class ScrollingWordsScreen {

    public static void main (String[] args) {

        Screen screen = Screen.getInstance();
        screen.clear();

        String sentence = "I am a walking disaster";

        var wordList = getWordsPos(sentence); // import static pcd.lab01.ex01.AuxLib.getWordsPos

        List<ScrollingWordsThread> threads = new ArrayList<>(); // import java.util.List e import java.util.ArrayList

        for (var wp : wordList) {
            ScrollingWordsThread thread = new ScrollingWordsThread(
                    screen,
                    wp.word(),
                    wp.pos(),
                    getRandomSpeed()
                    // getRandomColor()
                    );
            threads.add(thread);
            thread.start();
        }

        for (var thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Tutte le parole sono cadute!");
    }

    private static int getRandomSpeed() {
        return 300 + (int) (Math.random() * 700);
    }

}

