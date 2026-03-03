package org.example.models;

import org.example.GameManager;

public class LeftL extends Piece {

    public LeftL() {
        super();
    }

    @Override
    public boolean canRotate() {
        char[][] playground = GameManager.getInstance().getPlayground();
        int nextRotation = (rotationStatus + 1) % 4;

        // Verifichiamo se la prossima rotazione collide o esce dai bordi
        switch (nextRotation) {
            case 0: // Verticale, piede a sinistra
                if (pivotX - 2 < 0 || pivotY - 1 < 0) return false;
                if (playground[pivotX - 2][pivotY] == GameManager.CELLA_PIENA ||
                    playground[pivotX - 1][pivotY] == GameManager.CELLA_PIENA ||
                    playground[pivotX][pivotY - 1] == GameManager.CELLA_PIENA) return false;
                break;
            case 1: // Orizzontale, piede in alto
                if (pivotX - 1 < 0 || pivotY + 2 >= 10) return false;
                if (playground[pivotX][pivotY + 1] == GameManager.CELLA_PIENA ||
                    playground[pivotX][pivotY + 2] == GameManager.CELLA_PIENA ||
                    playground[pivotX - 1][pivotY] == GameManager.CELLA_PIENA) return false;
                break;
            case 2: // Verticale, piede a destra
                if (pivotX + 2 >= 20 || pivotY + 1 >= 10) return false;
                if (playground[pivotX + 1][pivotY] == GameManager.CELLA_PIENA ||
                    playground[pivotX + 2][pivotY] == GameManager.CELLA_PIENA ||
                    playground[pivotX][pivotY + 1] == GameManager.CELLA_PIENA) return false;
                break;
            case 3: // Orizzontale, piede in basso
                if (pivotX + 1 >= 20 || pivotY - 2 < 0) return false;
                if (playground[pivotX][pivotY - 1] == GameManager.CELLA_PIENA ||
                    playground[pivotX][pivotY - 2] == GameManager.CELLA_PIENA ||
                    playground[pivotX + 1][pivotY] == GameManager.CELLA_PIENA) return false;
                break;
        }
        return true;
    }

    @Override
    public boolean canMoveDown() {
        char[][] playground = GameManager.getInstance().getPlayground();
        switch (rotationStatus) {
            case 0: // Verticale, piede a sinistra
                if (pivotX + 1 >= 20 || pivotY - 1 < 0) return false;
                return playground[pivotX + 1][pivotY] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY - 1] == GameManager.CELLA_VUOTA;
            case 1: // Orizzontale, piede in alto
                if (pivotX + 1 >= 20 || pivotY + 2 >= 10) return false;
                return playground[pivotX + 1][pivotY] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY + 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY + 2] == GameManager.CELLA_VUOTA;
            case 2: // Verticale, piede a destra
                if (pivotX + 3 >= 20) return false;
                return playground[pivotX + 3][pivotY] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY + 1] == GameManager.CELLA_VUOTA;
            case 3: // Orizzontale, piede in basso
                if (pivotX + 2 >= 20 || pivotY - 2 < 0) return false;
                return playground[pivotX + 1][pivotY - 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY - 2] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 2][pivotY] == GameManager.CELLA_VUOTA;
        }
        return false;
    }

    @Override
    public boolean canMoveSx() {
        char[][] playground = GameManager.getInstance().getPlayground();
        switch (rotationStatus) {
            case 0: // Verticale, piede a sinistra
                if (pivotY - 2 < 0) return false;
                return playground[pivotX][pivotY - 2] == GameManager.CELLA_VUOTA &&
                       playground[pivotX - 1][pivotY - 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX - 2][pivotY - 1] == GameManager.CELLA_VUOTA;
            case 1: // Orizzontale, piede in alto
                if (pivotY - 1 < 0) return false;
                return playground[pivotX][pivotY - 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX - 1][pivotY - 1] == GameManager.CELLA_VUOTA;
            case 2: // Verticale, piede a destra
                if (pivotY - 1 < 0) return false;
                return playground[pivotX][pivotY - 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY - 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 2][pivotY - 1] == GameManager.CELLA_VUOTA;
            case 3: // Orizzontale, piede in basso
                if (pivotY - 3 < 0) return false;
                return playground[pivotX][pivotY - 3] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY - 1] == GameManager.CELLA_VUOTA;
        }
        return false;
    }

    @Override
    public boolean canMoveDx() {
        char[][] playground = GameManager.getInstance().getPlayground();
        switch (rotationStatus) {
            case 0: // Verticale, piede a sinistra
                if (pivotY + 1 >= 10) return false;
                return playground[pivotX][pivotY + 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX - 1][pivotY + 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX - 2][pivotY + 1] == GameManager.CELLA_VUOTA;
            case 1: // Orizzontale, piede in alto
                if (pivotY + 3 >= 10) return false;
                return playground[pivotX][pivotY + 3] == GameManager.CELLA_VUOTA &&
                       playground[pivotX - 1][pivotY + 1] == GameManager.CELLA_VUOTA;
            case 2: // Verticale, piede a destra
                if (pivotY + 2 >= 10) return false;
                return playground[pivotX][pivotY + 2] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY + 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 2][pivotY + 1] == GameManager.CELLA_VUOTA;
            case 3: // Orizzontale, piede in basso
                if (pivotY + 1 >= 10) return false;
                return playground[pivotX][pivotY + 1] == GameManager.CELLA_VUOTA &&
                       playground[pivotX + 1][pivotY + 1] == GameManager.CELLA_VUOTA;
        }
        return false;
    }

    @Override
    public void rotate() {
        removePiece();
        rotationStatus = (rotationStatus + 1) % 4;
        drawPiece();
    }

    @Override
    public void moveDown() {
        removePiece();
        pivotX++;
        drawPiece();
    }

    @Override
    public void moveSx() {
        removePiece();
        pivotY--;
        drawPiece();
    }

    @Override
    public void moveDx() {
        removePiece();
        pivotY++;
        drawPiece();
    }

    @Override
    public void freeze() {
        char[][] playground = GameManager.getInstance().getPlayground();

        switch (rotationStatus) {
            case 0: // Verticale, piede a sinistra
                playground[pivotX][pivotY] = GameManager.CELLA_PIENA;
                playground[pivotX][pivotY - 1] = GameManager.CELLA_PIENA;
                playground[pivotX - 1][pivotY] = GameManager.CELLA_PIENA;
                playground[pivotX - 2][pivotY] = GameManager.CELLA_PIENA;
                break;
            case 1: // Orizzontale, piede in alto
                playground[pivotX][pivotY] = GameManager.CELLA_PIENA;
                playground[pivotX][pivotY + 1] = GameManager.CELLA_PIENA;
                playground[pivotX][pivotY + 2] = GameManager.CELLA_PIENA;
                playground[pivotX - 1][pivotY] = GameManager.CELLA_PIENA;
                break;
            case 2: // Verticale, piede a destra
                playground[pivotX][pivotY] = GameManager.CELLA_PIENA;
                playground[pivotX][pivotY + 1] = GameManager.CELLA_PIENA;
                playground[pivotX + 1][pivotY] = GameManager.CELLA_PIENA;
                playground[pivotX + 2][pivotY] = GameManager.CELLA_PIENA;
                break;
            case 3: // Orizzontale, piede in basso
                playground[pivotX][pivotY] = GameManager.CELLA_PIENA;
                playground[pivotX][pivotY - 1] = GameManager.CELLA_PIENA;
                playground[pivotX][pivotY - 2] = GameManager.CELLA_PIENA;
                playground[pivotX + 1][pivotY] = GameManager.CELLA_PIENA;
                break;
        }
    }

    @Override
    public boolean canDropIntoPlayground() {
        char[][] playground = GameManager.getInstance().getPlayground();
        // Verifichiamo se le celle iniziali (rotazione 0) sono libere
        return playground[2][4] == GameManager.CELLA_VUOTA &&
               playground[2][3] == GameManager.CELLA_VUOTA &&
               playground[1][4] == GameManager.CELLA_VUOTA &&
               playground[0][4] == GameManager.CELLA_VUOTA;
    }

    @Override
    public void dropIntoPlayground() {
        this.pivotX = 2;
        this.pivotY = 4;
        this.rotationStatus = 0;
        drawPiece();
    }

    private void removePiece() {
        char[][] playground = GameManager.getInstance().getPlayground();
        switch (rotationStatus) {
            case 0: // Verticale, piede a sinistra
                playground[pivotX][pivotY] = GameManager.CELLA_VUOTA;
                playground[pivotX][pivotY - 1] = GameManager.CELLA_VUOTA;
                playground[pivotX - 1][pivotY] = GameManager.CELLA_VUOTA;
                playground[pivotX - 2][pivotY] = GameManager.CELLA_VUOTA;
                break;
            case 1: // Orizzontale, piede in alto
                playground[pivotX][pivotY] = GameManager.CELLA_VUOTA;
                playground[pivotX][pivotY + 1] = GameManager.CELLA_VUOTA;
                playground[pivotX][pivotY + 2] = GameManager.CELLA_VUOTA;
                playground[pivotX - 1][pivotY] = GameManager.CELLA_VUOTA;
                break;
            case 2: // Verticale, piede a destra
                playground[pivotX][pivotY] = GameManager.CELLA_VUOTA;
                playground[pivotX][pivotY + 1] = GameManager.CELLA_VUOTA;
                playground[pivotX + 1][pivotY] = GameManager.CELLA_VUOTA;
                playground[pivotX + 2][pivotY] = GameManager.CELLA_VUOTA;
                break;
            case 3: // Orizzontale, piede in basso
                playground[pivotX][pivotY] = GameManager.CELLA_VUOTA;
                playground[pivotX][pivotY - 1] = GameManager.CELLA_VUOTA;
                playground[pivotX][pivotY - 2] = GameManager.CELLA_VUOTA;
                playground[pivotX + 1][pivotY] = GameManager.CELLA_VUOTA;
                break;
        }
    }

    private void drawPiece() {
        char[][] playground = GameManager.getInstance().getPlayground();
        switch (rotationStatus) {
            case 0: // Verticale, piede a sinistra
                playground[pivotX][pivotY] = GameManager.CELLA_PEZZO;
                playground[pivotX][pivotY - 1] = GameManager.CELLA_PEZZO;
                playground[pivotX - 1][pivotY] = GameManager.CELLA_PEZZO;
                playground[pivotX - 2][pivotY] = GameManager.CELLA_PEZZO;
                break;
            case 1: // Orizzontale, piede in alto
                playground[pivotX][pivotY] = GameManager.CELLA_PEZZO;
                playground[pivotX][pivotY + 1] = GameManager.CELLA_PEZZO;
                playground[pivotX][pivotY + 2] = GameManager.CELLA_PEZZO;
                playground[pivotX - 1][pivotY] = GameManager.CELLA_PEZZO;
                break;
            case 2: // Verticale, piede a destra
                playground[pivotX][pivotY] = GameManager.CELLA_PEZZO;
                playground[pivotX][pivotY + 1] = GameManager.CELLA_PEZZO;
                playground[pivotX + 1][pivotY] = GameManager.CELLA_PEZZO;
                playground[pivotX + 2][pivotY] = GameManager.CELLA_PEZZO;
                break;
            case 3: // Orizzontale, piede in basso
                playground[pivotX][pivotY] = GameManager.CELLA_PEZZO;
                playground[pivotX][pivotY - 1] = GameManager.CELLA_PEZZO;
                playground[pivotX][pivotY - 2] = GameManager.CELLA_PEZZO;
                playground[pivotX + 1][pivotY] = GameManager.CELLA_PEZZO;
                break;
        }
    }
}
