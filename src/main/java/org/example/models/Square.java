package org.example.models;

import org.example.GameManager;

public class Square extends Piece {


    /**
     * Square
     * - 4 blocchi
     * - il pivot è irrilevante / non ruota
     */
    public Square() {
        super();

    }

    @Override
    public boolean canRotate() {
        return false;
    }

    @Override
    public boolean canMoveDown() {
        char playground[][] = GameManager.getInstance().getPlayground();

        if (this.pivotX + 2 >= playground.length) {
            return false;
        }

        if (playground[pivotX + 2][pivotY] == GameManager.CELLA_VUOTA && playground[pivotX + 2][pivotY + 1] == GameManager.CELLA_VUOTA) {
            return true;
        }
        return false;
    }


    @Override
    public boolean canMoveSx() {
        char[][] playground = GameManager.getInstance().getPlayground();

        // Controllo se siamo già al bordo sinistro
        if (this.pivotY <= 0) {
            return false;
        }

        // Controllo collisioni a sinistra
        if (playground[pivotX][pivotY - 1] == GameManager.CELLA_VUOTA && 
            playground[pivotX + 1][pivotY - 1] == GameManager.CELLA_VUOTA) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canMoveDx() {
        char[][] playground = GameManager.getInstance().getPlayground();

        // Controllo se siamo già al bordo destro
        if (this.pivotY + 1 >= playground[0].length - 1) {
            return false;
        }

        // Controllo collisioni a destra
        if (playground[pivotX][pivotY + 2] == GameManager.CELLA_VUOTA && 
            playground[pivotX + 1][pivotY + 2] == GameManager.CELLA_VUOTA) {
            return true;
        }
        return false;
    }

    @Override
    public void rotate() {
        // Il quadrato non ruota, quindi il metodo rimane vuoto
    }

    @Override
    public void moveDown() {
        // Rimuovo le vecchie posizioni '@'
        clearCurrentPosition();

        this.pivotX++;

        // Inserisco le nuove posizioni '@'
        drawCurrentPosition();
    }

    @Override
    public void moveSx() {
        clearCurrentPosition();
        this.pivotY--;
        drawCurrentPosition();
    }

    @Override
    public void moveDx() {
        clearCurrentPosition();
        this.pivotY++;
        drawCurrentPosition();
    }

    /**
     * Pulisce le celle occupate dal pezzo corrente nel playground.
     */
    private void clearCurrentPosition() {
        char[][] playground = GameManager.getInstance().getPlayground();
        playground[pivotX][pivotY] = GameManager.CELLA_VUOTA;
        playground[pivotX][pivotY + 1] = GameManager.CELLA_VUOTA;
        playground[pivotX + 1][pivotY] = GameManager.CELLA_VUOTA;
        playground[pivotX + 1][pivotY + 1] = GameManager.CELLA_VUOTA;
    }

    /**
     * Disegna le celle del pezzo corrente nel playground.
     */
    private void drawCurrentPosition() {
        char[][] playground = GameManager.getInstance().getPlayground();
        playground[pivotX][pivotY] = GameManager.CELLA_PEZZO;
        playground[pivotX][pivotY + 1] = GameManager.CELLA_PEZZO;
        playground[pivotX + 1][pivotY] = GameManager.CELLA_PEZZO;
        playground[pivotX + 1][pivotY + 1] = GameManager.CELLA_PEZZO;
    }

    @Override
    public void freeze() {
        char[][] playground = GameManager.getInstance().getPlayground();

        // Sostituisco le celle del pezzo (CELLA_PEZZO) con le celle di background (CELLA_PIENA)
        playground[pivotX][pivotY] = GameManager.CELLA_PIENA;
        playground[pivotX][pivotY + 1] = GameManager.CELLA_PIENA;
        playground[pivotX + 1][pivotY] = GameManager.CELLA_PIENA;
        playground[pivotX + 1][pivotY + 1] = GameManager.CELLA_PIENA;
    }

    // Verifica se posso droppare
    @Override
    public boolean canDropIntoPlayground() {
        char playground[][] = GameManager.getInstance().getPlayground();
        if (((playground[0][4] == GameManager.CELLA_VUOTA) && (playground[0][5] == GameManager.CELLA_VUOTA)) &&
                ((playground[1][4] == GameManager.CELLA_VUOTA) && (playground[1][5] == GameManager.CELLA_VUOTA))) {
            return true; // dovrebbe restituire true
        }
        return false;
    }

    @Override
    // Ho fatto refactoring
    public void dropIntoPlayground() {
        // Setto i pivot per il quadrato
        this.pivotX = 0;
        this.pivotY = 4;

        // posiziono il pezzo all'inizio del playground
        char[][] playground = GameManager.getInstance().getPlayground();
        playground[pivotX][pivotY] = GameManager.CELLA_PEZZO;
        playground[pivotX][pivotY + 1] = GameManager.CELLA_PEZZO;
        playground[pivotX + 1][pivotY] = GameManager.CELLA_PEZZO;
        playground[pivotX + 1][pivotY + 1] = GameManager.CELLA_PEZZO;
    }
}
