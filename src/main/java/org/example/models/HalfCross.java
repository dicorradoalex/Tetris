package org.example.models;

import org.example.GameManager;

public class HalfCross  extends Piece{
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;

    public HalfCross(){
        super();
    }

    @Override
    public boolean canRotate() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_VUOTA = GameManager.CELLA_VUOTA;

        switch (rotationStatus) {
            case UP:
                if (playground[pivotX + 1][pivotY] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case LEFT:
                if (this.pivotY + 1 >= playground[0].length) {
                    return false;
                }
                if (playground[pivotX][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case DOWN:
                if (playground[pivotX - 1][pivotY] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case RIGHT:
                if (this.pivotY - 1 <= 0) {
                    return false;
                }
                if (playground[pivotX][pivotY - 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;
        }

        return false;
    }

    @Override
    public boolean canMoveDown() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_VUOTA = GameManager.CELLA_VUOTA;

        switch (rotationStatus) {
            case UP:
                if (this.pivotX + 1 >= playground.length) {
                    return false;
                }
                if (playground[pivotX + 1][pivotY -1] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case LEFT:
                if (this.pivotX + 2 >= playground.length) {
                    return false;
                }
                if (playground[pivotX + 1][pivotY - 1] == CELLA_VUOTA &&
                        playground[pivotX + 2][pivotY] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case DOWN:
                if (this.pivotX + 2 >= playground.length) {
                    return false;
                }
                if (playground[pivotX + 1][pivotY -1] == CELLA_VUOTA &&
                        playground[pivotX + 2][pivotY] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case RIGHT:
                if (this.pivotX + 2 >= playground.length) {
                    return false;
                }
                if (playground[pivotX + 2][pivotY] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;
        }

        return false;
    }

    @Override
    public boolean canMoveSx() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_VUOTA = GameManager.CELLA_VUOTA;

        switch (rotationStatus) {
            case UP:
                if (this.pivotY - 2 < 0) {
                    return false;
                }
                if (playground[pivotX - 1][pivotY - 1] == CELLA_VUOTA &&
                        playground[pivotX][pivotY - 2] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case LEFT:
                if (this.pivotY - 2 < 0) {
                    return false;
                }
                if (playground[pivotX - 1][pivotY - 1] == CELLA_VUOTA &&
                        playground[pivotX][pivotY - 2] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY - 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case DOWN:
                if (this.pivotY - 2 < 0) {
                    return false;
                }
                if (playground[pivotX][pivotY - 2] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY - 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case RIGHT:
                if (this.pivotY - 1 <= 0) {
                    return false;
                }
                if (playground[pivotX - 1][pivotY - 1] == CELLA_VUOTA &&
                        playground[pivotX][pivotY - 1] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY - 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;
        }

        return false;
    }

    @Override
    public boolean canMoveDx() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_VUOTA = GameManager.CELLA_VUOTA;

        switch (rotationStatus) {
            case UP:
                if (this.pivotY + 2 >= playground[0].length) {
                    return false;
                }
                if (playground[pivotX - 1][pivotY + 1] == CELLA_VUOTA &&
                        playground[pivotX][pivotY + 2] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case LEFT:
                if (this.pivotY + 1 >= playground[0].length) {
                    return false;
                }
                if (playground[pivotX - 1][pivotY + 1] == CELLA_VUOTA &&
                        playground[pivotX][pivotY + 1] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case DOWN:
                if (this.pivotY + 2 >= playground[0].length) {
                    return false;
                }
                if (playground[pivotX][pivotY + 2] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;

            case RIGHT:
                if (this.pivotY + 2 >= playground[0].length) {
                    return false;
                }
                if (playground[pivotX - 1][pivotY + 1] == CELLA_VUOTA &&
                        playground[pivotX][pivotY + 2] == CELLA_VUOTA &&
                        playground[pivotX + 1][pivotY + 1] == CELLA_VUOTA) {
                    return true;
                }
                return false;
        }

        return false;
    }

    @Override
    public void rotate() {
        deletePiece();
        switch (rotationStatus) {
            case UP:
                this.rotationStatus = LEFT;
                finalizePieceMovement();
                return;

            case LEFT:
                this.rotationStatus = DOWN;
                finalizePieceMovement();
                return;

            case DOWN:
                this.rotationStatus = RIGHT;
                finalizePieceMovement();
                return;

            case RIGHT:
                this.rotationStatus = UP;
                finalizePieceMovement();
                return;

        }

    }

    @Override
    public void moveDown() {
        deletePiece();
        this.pivotX++;
        finalizePieceMovement();

    }

    @Override
    public void moveSx() {
        deletePiece();
        this.pivotY--;
        finalizePieceMovement();

    }

    @Override
    public void moveDx() {
        deletePiece();
        this.pivotY++;
        finalizePieceMovement();

    }

    @Override
    public void freeze() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_PIENA = GameManager.CELLA_PIENA;

        switch (rotationStatus) {
            case UP:
                playground[pivotX - 1][pivotY] = CELLA_PIENA;
                playground[pivotX][pivotY - 1] = CELLA_PIENA;
                playground[pivotX][pivotY] = CELLA_PIENA;
                playground[pivotX][pivotY + 1] = CELLA_PIENA;
                return;

            case LEFT:
                playground[pivotX - 1][pivotY] = CELLA_PIENA;
                playground[pivotX][pivotY - 1] = CELLA_PIENA;
                playground[pivotX][pivotY] = CELLA_PIENA;
                playground[pivotX + 1][pivotY] = CELLA_PIENA;
                return;

            case DOWN:
                playground[pivotX][pivotY - 1] = CELLA_PIENA;
                playground[pivotX][pivotY] = CELLA_PIENA;
                playground[pivotX][pivotY + 1] = CELLA_PIENA;
                playground[pivotX + 1][pivotY] = CELLA_PIENA;
                return;

            case RIGHT:
                playground[pivotX - 1][pivotY] = CELLA_PIENA;
                playground[pivotX][pivotY] = CELLA_PIENA;
                playground[pivotX][pivotY + 1] = CELLA_PIENA;
                playground[pivotX + 1][pivotY] = CELLA_PIENA;
                return;

        }

    }

    @Override
    public boolean canDropIntoPlayground() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_VUOTA = GameManager.CELLA_VUOTA;

        if ((playground[0][4] == CELLA_VUOTA) &&
                (playground[1][3] == CELLA_VUOTA) &&
                (playground[1][4] == CELLA_VUOTA) &&
                (playground[1][5] == CELLA_VUOTA)) {
            return true; // dovrebbe restituire true
        }
        return false;
    }

    @Override
    public void dropIntoPlayground() {
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_PEZZO = GameManager.CELLA_PEZZO;
        this.pivotX = 1;
        this.pivotY = 4;

        playground[pivotX - 1][pivotY] = CELLA_PEZZO;
        playground[pivotX][pivotY - 1] = CELLA_PEZZO;
        playground[pivotX][pivotY] = CELLA_PEZZO;
        playground[pivotX][pivotY + 1] = CELLA_PEZZO;

    }

    private void deletePiece(){
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_VUOTA = GameManager.CELLA_VUOTA;

        switch (rotationStatus) {
            case UP:
                playground[pivotX - 1][pivotY] = CELLA_VUOTA;
                playground[pivotX][pivotY - 1] = CELLA_VUOTA;
                playground[pivotX][pivotY] = CELLA_VUOTA;
                playground[pivotX][pivotY + 1] = CELLA_VUOTA;
                return;

            case LEFT:
                playground[pivotX - 1][pivotY] = CELLA_VUOTA;
                playground[pivotX][pivotY - 1] = CELLA_VUOTA;
                playground[pivotX][pivotY] = CELLA_VUOTA;
                playground[pivotX + 1][pivotY] = CELLA_VUOTA;
                return;

            case DOWN:
                playground[pivotX][pivotY - 1] = CELLA_VUOTA;
                playground[pivotX][pivotY] = CELLA_VUOTA;
                playground[pivotX][pivotY + 1] = CELLA_VUOTA;
                playground[pivotX + 1][pivotY] = CELLA_VUOTA;
                return;

            case RIGHT:
                playground[pivotX - 1][pivotY] = CELLA_VUOTA;
                playground[pivotX][pivotY] = CELLA_VUOTA;
                playground[pivotX][pivotY + 1] = CELLA_VUOTA;
                playground[pivotX + 1][pivotY] = CELLA_VUOTA;
                return;

        }

    }

    private void finalizePieceMovement(){
        char playground[][] = GameManager.getInstance().getPlayground();
        char CELLA_PEZZO = GameManager.CELLA_PEZZO;

        switch (rotationStatus) {
            case UP:
                playground[pivotX - 1][pivotY] = CELLA_PEZZO;
                playground[pivotX][pivotY - 1] = CELLA_PEZZO;
                playground[pivotX][pivotY] = CELLA_PEZZO;
                playground[pivotX][pivotY + 1] = CELLA_PEZZO;
                return;

            case LEFT:
                playground[pivotX - 1][pivotY] = CELLA_PEZZO;
                playground[pivotX][pivotY - 1] = CELLA_PEZZO;
                playground[pivotX][pivotY] = CELLA_PEZZO;
                playground[pivotX + 1][pivotY] = CELLA_PEZZO;
                return;

            case DOWN:
                playground[pivotX][pivotY - 1] = CELLA_PEZZO;
                playground[pivotX][pivotY] = CELLA_PEZZO;
                playground[pivotX][pivotY + 1] = CELLA_PEZZO;
                playground[pivotX + 1][pivotY] = CELLA_PEZZO;
                return;

            case RIGHT:
                playground[pivotX - 1][pivotY] = CELLA_PEZZO;
                playground[pivotX][pivotY] = CELLA_PEZZO;
                playground[pivotX][pivotY + 1] = CELLA_PEZZO;
                playground[pivotX + 1][pivotY] = CELLA_PEZZO;
                return;

        }
    }
}
