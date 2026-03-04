package org.example.models;

import org.example.GameManager;

public class Bar extends Piece {


    /**
     * Bar
     * - 4 blocchi
     * - il pivot è -*--
     */

    public Bar() {
        super();
        rotationStatus = Rotation.Vertical.getType();
    }

    enum Rotation {
        Vertical(1),
        Horizontal(2);

        private final int type;

        Rotation(int value) {
            this.type = value;
        }

        public int getType() {
            return type;
        }
    }

    public boolean isVerticalRoutate() {
        return rotationStatus == 1;
    }

    @Override
    public boolean canRotate() {

        char[][] playground = getPlayground();

        // If Vertical
        if (isVerticalRoutate()) {
            if (pivotY + 2 >= playground[pivotX].length || pivotY - 1 < 0) {
                return false;
            }

            if (playground[pivotX][pivotY - 1] != GameManager.CELLA_VUOTA ||
                    playground[pivotX][pivotY + 1] != GameManager.CELLA_VUOTA ||
                    playground[pivotX][pivotY + 2] != GameManager.CELLA_VUOTA
            ) {
                return false;
            }

        } else {
            if (pivotX + 2 > playground.length || pivotX - 1 < 0) {
                return false;
            }
            if (playground[pivotX - 1][pivotY] != GameManager.CELLA_VUOTA ||
                    playground[pivotX + 1][pivotY] != GameManager.CELLA_VUOTA ||
                    playground[pivotX + 2][pivotY] != GameManager.CELLA_VUOTA
            ) {
                return false;
            }

        }

        return true;
    }

    @Override
    public boolean canMoveDown() {
        char[][] playground = getPlayground();

        // if Vertical
        if (isVerticalRoutate()) {
            if (this.pivotX + 3 >= playground.length) {
                return false;
            }

            if (playground[pivotX + 3][pivotY] == GameManager.CELLA_VUOTA) {
                return true;
            }

        } else {
            int newPivotY = this.pivotY;
            int newPivotX = this.pivotX + 1;
            if (newPivotX >= playground.length) {
                return false;
            }

            if (playground[pivotX + 1][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX + 1][newPivotY + 1] == GameManager.CELLA_VUOTA &&
                    playground[pivotX + 1][newPivotY + 2] == GameManager.CELLA_VUOTA
            ) {
                return true;
            }
        }

        return false;
    }


    @Override
    public boolean canMoveSx() {
        char[][] playground = getPlayground();

        if (isVerticalRoutate()) {
            if (pivotY - 1 < 0) {
                return false;
            }
            int newPivotY = this.pivotY - 1;

            if (playground[pivotX - 1][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX + 1][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX + 2][newPivotY] == GameManager.CELLA_VUOTA
            ) {
                return true;
            }

        } else {
            if (pivotY - 2 < 0) {
                return false;
            }

            if (playground[pivotX][pivotY - 2] == GameManager.CELLA_VUOTA) {
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean canMoveDx() {
        char[][] playground = getPlayground();

        if (isVerticalRoutate()) {

            if (pivotY + 1 >= playground[pivotX].length) {
                return false;
            }

            int newPivotY = this.pivotY + 1;

            if (playground[pivotX - 1][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX + 1][newPivotY] == GameManager.CELLA_VUOTA &&
                    playground[pivotX + 2][newPivotY] == GameManager.CELLA_VUOTA
            ) {
                return true;
            }
        } else {
            if (pivotY + 3 >= playground[pivotX].length) {
                return false;
            }

            if (playground[pivotX][pivotY + 3] == GameManager.CELLA_VUOTA) {
                return true;
            }
        }
        return false;

    }

    @Override
    public void rotate() {

        char[][] playground = getPlayground();

        char cellEmpty = GameManager.CELLA_VUOTA;
        char cellPiece = GameManager.CELLA_PEZZO;

        if (isVerticalRoutate()) {
            // BASE 2: Clear
            setTypeCellVertical(playground, cellEmpty);

            // BASE 2: Draw
            setTypeCelleHorizontal(playground, cellPiece);

            rotationStatus = Rotation.Horizontal.getType();
        } else {
            // BASE 1: Clear
            setTypeCelleHorizontal(playground, cellEmpty);

            // BASE 1: Draw
            setTypeCellVertical(playground, cellPiece);

            rotationStatus = Rotation.Vertical.getType();
        }


    }

    private void setTypeCelleHorizontal(char[][] playground, char cellPiece) {
        playground[pivotX][pivotY - 1] = cellPiece;
        playground[pivotX][pivotY] = cellPiece;
        playground[pivotX][pivotY + 1] = cellPiece;
        playground[pivotX][pivotY + 2] = cellPiece;
    }

    private void setTypeCellVertical(char[][] playground, char cellEmpty) {
        playground[pivotX - 1][pivotY] = cellEmpty;
        playground[pivotX][pivotY] = cellEmpty;
        playground[pivotX + 1][pivotY] = cellEmpty;
        playground[pivotX + 2][pivotY] = cellEmpty;
    }

    @Override
    public void moveDown() {

        // if VERTICAL
        if (isVerticalRoutate()) {
            // remove @
            setTypeCellVertical(getPlayground(), GameManager.CELLA_VUOTA);

            this.pivotX++;

            // draw @
            setTypeCellVertical(getPlayground(), GameManager.CELLA_PEZZO);

        } else {
            // clear @
            setTypeCelleHorizontal(getPlayground(), GameManager.CELLA_VUOTA);

            this.pivotX++;

            // draw @
            setTypeCelleHorizontal(getPlayground(), GameManager.CELLA_PEZZO);

        }

    }

    @Override
    public void moveSx() {

        char[][] playground = getPlayground();
        if (isVerticalRoutate()) {
            //clear
            setTypeCellVertical(playground, GameManager.CELLA_VUOTA);
            // Draw
            pivotY--;
            setTypeCellVertical(playground, GameManager.CELLA_PEZZO);
        } else {
            //clear
            setTypeCelleHorizontal(playground, GameManager.CELLA_VUOTA);
            // Draw
            pivotY--;
            setTypeCelleHorizontal(playground, GameManager.CELLA_PEZZO);
        }
    }

    private char[][] getPlayground() {
        return GameManager.getInstance().getPlayground();
    }

    @Override
    public void moveDx() {
        char[][] playground = getPlayground();
        if (isVerticalRoutate()) {
            //clear
            setTypeCellVertical(playground, GameManager.CELLA_VUOTA);
            // Draw
            pivotY++;
            setTypeCellVertical(playground, GameManager.CELLA_PEZZO);
        } else {
            //clear
            setTypeCelleHorizontal(playground, GameManager.CELLA_VUOTA);
            // Draw
            pivotY++;
            setTypeCelleHorizontal(playground, GameManager.CELLA_PEZZO);
        }
    }

    @Override
    public void freeze() {
        char[][] playground = getPlayground();
        if (isVerticalRoutate()) {
            setTypeCellVertical(playground, GameManager.CELLA_PIENA);
        } else {
            setTypeCelleHorizontal(playground, GameManager.CELLA_PIENA);
        }
    }

    // Verifica se posso droppare
    @Override
    public boolean canDropIntoPlayground() {
        char[][] playground = getPlayground();
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
        char[][] playground = getPlayground();
        setTypeCellVertical(playground, GameManager.CELLA_PEZZO);

    }
}
