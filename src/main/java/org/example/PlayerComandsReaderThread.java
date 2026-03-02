package org.example;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

import java.awt.event.*;
import java.util.Map;

public class PlayerComandsReaderThread implements Runnable {
    private static boolean run = true;

    @Override
    public void run() {
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // Use false here to switch to hook instead of raw input

        keyboardHook.addKeyListener(new GlobalKeyAdapter() {

            @Override
            public void keyPressed(GlobalKeyEvent event) {
                System.out.println(event);
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
                    run = false;
                }
                boolean sholdReprintPlayground = true;

                switch (event.getVirtualKeyCode()) {

                    case GlobalKeyEvent.VK_LEFT:
                        GameManager.getInstance().moveCurrentPieceSx();
                        System.out.println("LEFFFFFFFFFFFT");
                        break;

                    case GlobalKeyEvent.VK_RIGHT:
                        GameManager.getInstance().moveCurrentPieceDx();
                        break;

                    case GlobalKeyEvent.VK_UP:
                        GameManager.getInstance().rotateCurrentPiece();
                        break;

                    case GlobalKeyEvent.VK_DOWN:
                        GameManager.getInstance().moveCurrentPieceDown();
                        break;

                    default:
                        sholdReprintPlayground = false;
                }

                if (sholdReprintPlayground) {
                    GameManager.getInstance().printPlayground();
                }

            }
        });

        try {
            while (run) {
                Thread.sleep(128);
            }
        } catch (InterruptedException e) {
            //Do nothing
        } finally {
            keyboardHook.shutdownHook();
        }
    }
}

