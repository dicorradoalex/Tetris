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
        return false;
    }

    @Override
    public boolean canMoveDx() {
        return false;
    }

    @Override
    public void rotate() {

    }

    @Override
    public void moveDown() {

        // Si dovrebbe fare refactoring come fatto in dropIntoPlayground (adesso è poco leggibile)

        // rimuovi @
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_VUOTA;
        GameManager.getInstance().getPlayground()[pivotX][pivotY + 1] = GameManager.CELLA_VUOTA;
        GameManager.getInstance().getPlayground()[pivotX + 1][pivotY] = GameManager.CELLA_VUOTA;
        GameManager.getInstance().getPlayground()[pivotX + 1][pivotY + 1] = GameManager.CELLA_VUOTA;

        this.pivotX++;

        // aggiorna @
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_PEZZO;
        GameManager.getInstance().getPlayground()[pivotX][pivotY + 1] = GameManager.CELLA_PEZZO;
        GameManager.getInstance().getPlayground()[pivotX + 1][pivotY] = GameManager.CELLA_PEZZO;
        GameManager.getInstance().getPlayground()[pivotX + 1][pivotY + 1] = GameManager.CELLA_PEZZO;
    }

    @Override
    public void moveSx() {

    }

    @Override
    public void moveDx() {

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
