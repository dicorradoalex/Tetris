package org.example;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import java.awt.event.*;

public class PlayerComandsReaderThread implements Runnable {

    private volatile boolean running = true;
    private boolean useInput = true;
    @Override
    public void run() {
        
        if(!useInput){
            return;
        }
        try {
            // Costruisce il terminale interattivo di JLine3
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();

            // Attiva "Raw Mode" per intercettare i tasti
            terminal.enterRawMode();

            // Crea il lettore che cattura l'input della tastiera
            NonBlockingReader reader = terminal.reader();

            while (running) {
                // Il thread si mette in attesa e legge il codice numerico del tasto premuto
                int read = reader.read();

                // Se la lettura fallisce o viene interrotta, usciamo dal ciclo
                if (read == -1) {
                    break;
                }

                // Converte il codice numerico nel carattere corrispondente
                char command = Character.toLowerCase((char) read);
                boolean shouldReprintPlayground = true;

                // Mapping dei tasti
                switch (command) {
                    case 'a':
                        GameManager.getInstance().moveCurrentPieceSx();
                        break;
                    case 'd':
                        GameManager.getInstance().moveCurrentPieceDx();
                        break;
                    case 'w':
                        GameManager.getInstance().rotateCurrentPiece();
                        break;
                    case 's':
                        GameManager.getInstance().moveCurrentPieceDown();
                        break;
                    case 'q': // Per uscire
                        System.out.println("Uscita in corso...");
                        System.exit(0);
                        break;
                    default:
                        // Se l'utente preme un tasto non valido, non ristampare la griglia
                        shouldReprintPlayground = false;
                        break;
                }

                // Se il pezzo si è mosso, forza la ristampa immediata del playground
                if (shouldReprintPlayground) {
                    GameManager.getInstance().printPlayground();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }
}