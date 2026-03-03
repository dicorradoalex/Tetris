package org.example;

import org.example.models.LeftL;
import org.example.models.LeftS;
import org.example.models.Piece;
import org.example.models.RightL;
import org.example.models.RightS;
import org.example.models.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GameManager {

    static char[][] playground = new char[20][10];
    //  valore interno alla cella avrà un significato specifico
    //  - ' ' => cella vuota, un pezzo può sostare su questa cella
    //  - '#' => cella piena di "background" (pezzi bloccati), un pezzo non può sostare qui, collide
    //  - '@' => cella che indica un "pezzo" del Piece che sta scendendo (oggetto che il player sta gestendo)


    // Attributi
    public static final char CELLA_VUOTA = '_';
    public static final char CELLA_PIENA = '#';
    public static final char CELLA_PEZZO = '@';

    // forse inutile la lista di pezzi
    List<Piece> pieces;
    static Piece currentPiece;

    //Variabili per la gestione della difficoltà di gioco
    private static final int PIECES_PER_LEVEL = 20;
    private int piecesDropped;
    private int gameLevel;

    Thread timerThread;
    Thread commandsThread;

    private static GameManager INSTANCE;

    // Punto di accesso all'istanza del
    public static GameManager getInstance() {
        if (GameManager.INSTANCE == null) {
            GameManager.INSTANCE = new GameManager();
        }
        return GameManager.INSTANCE;
    }

    // Viene avviato questo dall'esterno - È il flusso di operazioni che fa il GameManager
    public void start() {
        printWelcomeMessage();
        initGame(); // inizializza il playground e genera la lista dei pezzi casuali

        currentPiece = dropNewPiece(); // genera un pezzo casuale, vede se lo puo droppare e lo droppa (oppure restituisce null)

        printPlayground(); // stampa il playground

        startTimer(); // avvia il timer
        startThreadToReadUserCommands();
        /**
         * - i metodi start suddetti evidenzieranno se un pezzo ha colliso
         * - quindi causeranno la necessità di invocare nuovamente dropNewPiece()
         evaluateCleanRows(); // -> dropNewPiece()
         checkWinLoseConditions();
         */
    }

    private void initGame() {
        initPlayground();
        genRandomListOfPieces();
        this.piecesDropped = 0;
        this.gameLevel = 1;
    }


    public void initPlayground() {
        // REFACTORING
        for (char[] row : playground)
            Arrays.fill(row, CELLA_VUOTA);
    }

    public char[][] getPlayground() {
        return playground;
    }

    public void printPlayground() {
        //TODO
        System.out.println("PLAYGROUND: ");
        String riga = "";
        for (int i = 0; i < playground.length; i++) {
            for (int j = 0; j < playground[i].length; j++) {
                char c = playground[i][j];
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("==========");
    }

    private GameManager() {

    }


    private void printGoodbyeMessage() {
        // TODO
    }


    private void printWelcomeMessage() {
        // TODO
    }

    public void genRandomListOfPieces() {
        // Se la lista non esiste ancora, la creiamo
        if (pieces == null) {
            pieces = new ArrayList<>();
        }

        // Aggiungiamo i pezzi alla lista
        pieces.add(new Square());
        pieces.add(new RightL());
        pieces.add(new LeftL());
        pieces.add(new LeftS());
        pieces.add(new RightS());
        pieces.add(new RightL());
        pieces.add(new LeftL());
        pieces.add(new RightL());

        Collections.shuffle(pieces);
    }


    // prende un pezzo dallo stack (se non ce n'è più, l'utente ha vinto)
    // lo inserisce in un punto specifico in alto nel playground
    // - invoco il metodo "canDropIntoPlayground()" del Piece
    // --- se non può, il player ha perso
    // --- se può, invoco il metodo "dropIntoPlayground()" del Piece
    public Piece dropNewPiece() {

        if (pieces == null || pieces.isEmpty()) {
            genRandomListOfPieces();
        }
        currentPiece = pieces.remove(0);

        boolean isCanDropInPlayground = currentPiece.canDropIntoPlayground();
        if (!isCanDropInPlayground) {
            return null;
        }
        currentPiece.dropIntoPlayground();

        piecesDropped++;
        checkDifficulty();

        return currentPiece;
    }

    public void moveCurrentPieceDownForTimeExpiry() {
        if (currentPiece == null) {
            System.out.println("ERRORE: currentPiece è null");
            return;
        }
        if (currentPiece.canMoveDown()) {
            currentPiece.moveDown();
        } else {
            currentPiece.freeze();
            evaluateCleanRows();
            currentPiece = dropNewPiece();
            checkWinLoseConditions(); //TODO: verificare se è corretto tenerla qui questa chiamata
        }
        printPlayground();
    }


    public void moveCurrentPieceDown() {
        this.moveCurrentPieceDownForTimeExpiry();
    }

    public void evaluateCleanRows() {
        // logica di eliminazione di una riga "full" e quindi incremento punteggio
        // - ogni volta che un pezzo si integra al fondale dobbiamo controllare se c'è una riga piena
        // - a seconda di quante righe piene ci sono nello stesso momento, daremo un differente punteggio
        // --- 1 riga => 1 punto
        // --- 2 righe => 4 punti
        // --- 3 righe => 9 punti
        // --- 4 righe => 16 punti
        // - tolte le righe piene, fare "scendere" il background in alto per occupare il posto delle righe liberate
    }

    public void checkWinLoseConditions() {
        // TODO

        // * WIN Conditions
        // * - ho posizionato tutti gli oggetti
        // * LOSE Conditions
        // * - non posso più droppare oggetti

        // if(haVinto o hoPerso) {
        //     //stoppo tutti i thread...
        //     timerThread.interrupt();
        //     commandsThread.interrupt();
        //     printGoodbyeMessage();
        //     //nice-to-have: save records...
        //     //nice-to-have: chiedere al player se vuole fare una nuova partita
        // }
    }

    public void startThreadToReadUserCommands() {
        // READ PLAYER COMMANDS -> multi-threading
        // --- thread che legge da console
        // --- thread che stampa (ogni volta che il timer scade)
        // --- thread che ri-stampa (ogni volta che l'utente muove/ruota un pezzo)
        // --- NB: ogni volta che un utente ruota un oggetto, il thread-timer che stampa "riparte"
        // --- un utente potrebbe far scendere velocemente un pezzo sul fondale o cmq
        // facendolo collidere col fondale del playground
        //  -> evaluateCleanRows() -> dropNewPiece() -> checkWinLoseConditions()

        PlayerComandsReaderThread thread = new PlayerComandsReaderThread();
        commandsThread = new Thread(thread);
        commandsThread.start();

    }

    public void startTimer() {
        GameLoopTimerThread loop = new GameLoopTimerThread();
        timerThread = new Thread(loop);
        timerThread.start();

        // nel metodo RUN del thread timer:
        // - ritmo di gioco incrementale man mano poggi pezzi sul fondale
        // - livello si incrementa => velocità di discesa si incrementa
        // - ogni 20 pezzi posati sul fondale => livello++
        // - 10 livelli, ognuno con una velocità di discesa diversa
        // --- livello 1 => 5 secondi
        // --- livello 2 => 3 secondi
        // --- livello 3 => 2 secondi
        // --- livello 4 => 1 secondo
        // --- livello 5 => 0.8 secondi
        // --- livello 6 => 0.7 secondi
        // --- livello 7 => 0.6 secondi
        // --- livello 8 => 0.5 secondi
        // --- livello 9 => 0.4 secondi
        // --- livello 10 => 0.3 secondi

    }

    public void moveCurrentPieceDx() {
        if (currentPiece.canMoveDx()) {
            currentPiece.moveDx();
        }
    }

    public void moveCurrentPieceSx() {
        if (currentPiece.canMoveSx()) {
            currentPiece.moveSx();
        }
    }

    public void rotateCurrentPiece() {
        if (currentPiece.canRotate()) {
            currentPiece.rotate();
            // TODO nice-to-have ogni volta che un utente ruota un oggetto, il thread-timer che stampa "riparte"
            try {
                timerThread.sleep(250); //TODO: potrebbe dipendere dal livello
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkDifficulty(){
        if (piecesDropped >= PIECES_PER_LEVEL) {
            this.gameLevel++;
            this.piecesDropped = 0;
        }
    }

    public int getGameLevel() {
        return gameLevel;
    }

}
