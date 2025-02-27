package pcd.lab01.ex01;

import org.fusesource.jansi.Ansi.*;

public class ScrollingWordsThread extends Thread {

    private final Screen screen;
    private final String word;
    private final int x0; // Posizione iniziale orizzontale
    private final int speed;

    private boolean stopped = false;

    private int y0 = 1;

    public ScrollingWordsThread(final Screen screen, final String word, final int x0, final int speed) {
        this.screen = screen;
        this.word = word;
        this.x0 = x0;
        this.speed = speed;
    }

    public void run() {
        while (!stopped && y0 < 20) {
            screen.writeStringAt(y0, x0, Color.YELLOW, word);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                ex.printStackTrace(); // stopped = true;
            }
            screen.writeStringAt(y0, x0, Color.BLACK, word);
            y0++;
        }
    }


    public void notifyStop() {
        stopped = true;
        interrupt();
    }
}
