package org.example.models;

import org.example.GameManager;

public class Bar extends Piece {


    /**
     * Square
     * - 4 blocchi
     * - il pivot è irrilevante / non ruota
     */

    public Bar() {
        super();

    }

    public boolean isVerticalRoutate() {
        char[][] playground = GameManager.getInstance().getPlayground();


        if (playground[pivotX][pivotY] == GameManager.CELLA_PEZZO &&
                playground[pivotX + 1][pivotY] == GameManager.CELLA_PEZZO
        ) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canRotate() {

        char[][] playground = GameManager.getInstance().getPlayground();

        // If Vertical
        if (isVerticalRoutate()) {
            if (playground[pivotX][pivotY - 1] == GameManager.CELLA_PEZZO &&
                    playground[pivotX][pivotY] == GameManager.CELLA_PEZZO &&
                    playground[pivotX][pivotY - 2] == GameManager.CELLA_PEZZO &&
                    playground[pivotX][pivotY - 3] == GameManager.CELLA_PEZZO
            ) {
                return false;
            }

        }


        return true;
    }

    @Override
    public boolean canMoveDown() {
        char[][] playground = GameManager.getInstance().getPlayground();

        // if Vertical
        if (isVerticalRoutate()) {
            if (this.pivotX + 3 >= playground.length) {
                return false;
            }

            if (playground[pivotX + 3][pivotY] == GameManager.CELLA_VUOTA) {
                return true;
            }
        }

        return false;
    }


    @Override
    public boolean canMoveSx() {
        char[][] playground = GameManager.getInstance().getPlayground();


        if (pivotY - 1 < 0) {
            return false;
        }

        if (playground[pivotX ][pivotY-1] == GameManager.CELLA_PEZZO) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canMoveDx() {
        char[][] playground = GameManager.getInstance().getPlayground();

        if (pivotY + 1 >= playground[pivotX].length) {
            return false;
        }

        if (playground[pivotX ][pivotY+1] == GameManager.CELLA_PEZZO) {
            return false;
        }
        return true;

    }

    @Override
    public void rotate() {

        char[][] playground = GameManager.getInstance().getPlayground();

        // Definire le variabili per le celle
        char cellEmpty = GameManager.CELLA_VUOTA;
        char cellPiece = GameManager.CELLA_PEZZO;

        if (isVerticalRoutate()) {
            // BASE 2: Clear
            setTypeCellVertical(playground, cellEmpty);

            // BASE 2: Draw
            setTypeCelleHorizzontal(playground, cellPiece);

        } else {
            // BASE 1: Clear
            setTypeCelleHorizzontal(playground, cellEmpty);

            // BASE 1: Draw
            setTypeCellVertical(playground, cellPiece);
        }


    }

    private void setTypeCelleHorizzontal(char[][] playground, char cellaPezzio) {
        playground[pivotX][pivotY - 1] = cellaPezzio;
        playground[pivotX][pivotY] = cellaPezzio;
        playground[pivotX][pivotY + 1] = cellaPezzio;
        playground[pivotX][pivotY + 2] = cellaPezzio;
    }

    private void setTypeCellVertical(char[][] playground, char cellaVuota) {
        playground[pivotX - 1][pivotY] = cellaVuota;
        playground[pivotX][pivotY] = cellaVuota;
        playground[pivotX + 1][pivotY] = cellaVuota;
        playground[pivotX + 2][pivotY] = cellaVuota;
    }

    @Override
    public void moveDown() {

        // if VERTICAL
        // rimuovi @
        setTypeCellVertical(GameManager.getInstance().getPlayground(), GameManager.CELLA_VUOTA);

        this.pivotX++;

        // aggiorna @
        setTypeCellVertical(GameManager.getInstance().getPlayground(), GameManager.CELLA_PEZZO);

    }

    @Override
    public void moveSx() {

        char[][] playground = GameManager.getInstance().getPlayground();
        if (isVerticalRoutate()){
            //clear
            setTypeCellVertical(playground,GameManager.CELLA_VUOTA);
            // Draw
            pivotY--;
            setTypeCellVertical(playground,GameManager.CELLA_PEZZO);
        }
    }

    @Override
    public void moveDx() {
        char[][] playground = GameManager.getInstance().getPlayground();
        if (isVerticalRoutate()){
            //clear
            setTypeCellVertical(playground,GameManager.CELLA_VUOTA);
            // Draw
            pivotY++;
            setTypeCellVertical(playground,GameManager.CELLA_PEZZO);
        }else {
            System.out.println("BAR - mouve DX Horizzontal not complate");
        }
    }

    @Override
    public void freeze() {
        char[][] playground = GameManager.getInstance().getPlayground();
        if (isVerticalRoutate()) {

            setTypeCellVertical(playground, GameManager.CELLA_PIENA);

        }else {
            setTypeCelleHorizzontal(playground,GameManager.CELLA_PIENA);

        }
    }

    // Verifica se posso droppare
    @Override
    public boolean canDropIntoPlayground() {
        char[][] playground = GameManager.getInstance().getPlayground();
        if (playground[0][4] == GameManager.CELLA_VUOTA && playground[0][5] == GameManager.CELLA_VUOTA &&
                playground[0][6] == GameManager.CELLA_VUOTA && playground[0][7] == GameManager.CELLA_VUOTA) {
            return true;
        }
        return false;
    }

    @Override
    // Ho fatto refactoring
    public void dropIntoPlayground() {
        // Setto i pivot per il quadrato
        this.pivotX = 1;
        this.pivotY = 4;
        // posiziono il pezzo all'inizio del playground
        char[][] playground = GameManager.getInstance().getPlayground();
        setTypeCellVertical(playground, GameManager.CELLA_PEZZO);

    }
}
