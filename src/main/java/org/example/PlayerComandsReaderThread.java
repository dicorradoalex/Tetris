package org.example;

import java.awt.event.*;

public class PlayerComandsReaderThread implements Runnable, KeyListener  {

    @Override
    public void run() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {

        boolean sholdReprintPlayground = true;

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                GameManager.getInstance().moveCurrentPieceSx();
                break;

            case KeyEvent.VK_RIGHT:
                GameManager.getInstance().moveCurrentPieceDx();
                break;

            case KeyEvent.VK_UP:
                GameManager.getInstance().rotateCurrentPiece();
                break;

            case KeyEvent.VK_DOWN:
                GameManager.getInstance().moveCurrentPieceDown();
                break;

            default:
                sholdReprintPlayground = false;
        }

        if(sholdReprintPlayground) {
            GameManager.getInstance().printPlayground();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

}

