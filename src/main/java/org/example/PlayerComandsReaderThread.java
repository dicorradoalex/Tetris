package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class PlayerComandsReaderThread implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            terminal.setCursorVisible(false);

            while (running) {

                KeyStroke key = terminal.pollInput(); // NON BLOCCA

                if (key == null) {
                    Thread.sleep(10);
                    continue;
                }

                boolean shouldReprint = true;

                KeyType type = key.getKeyType();

                switch (type) {

                    case ArrowLeft:
                        GameManager.getInstance().moveCurrentPieceSx();
                        break;

                    case ArrowRight:
                        GameManager.getInstance().moveCurrentPieceDx();
                        break;

                    case ArrowUp:
                        GameManager.getInstance().rotateCurrentPiece();
                        break;

                    case ArrowDown:
                        GameManager.getInstance().moveCurrentPieceDown();
                        break;

                    default:
                        shouldReprint = false;
                }

                if (shouldReprint) {
                    GameManager.getInstance().printPlayground();
                }
            }

            terminal.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }
}