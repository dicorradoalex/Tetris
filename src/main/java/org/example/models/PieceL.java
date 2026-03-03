package org.example.models;


import org.example.GameManager;

public class PieceL extends Piece {

    public PieceL() {
        super();
        this.rotationStatus = 0;
    }

    @Override
    public boolean canRotate() {
        int[][] next = getCellsForRotation((rotationStatus + 1) % 4);
        char[][] pg = GameManager.getInstance().getPlayground();

        for (int[] c : next) {
            int x = c[0];
            int y = c[1];

            if (x < 0 || x >= pg.length) return false;
            if (y < 0 || y >= pg[0].length) return false;
            if (pg[x][y] != GameManager.CELLA_VUOTA) return false;
        }
        return true;
    }

    @Override
    public boolean canMoveDown() {
        int[][] cells = getCells();
        char[][] pg = GameManager.getInstance().getPlayground();

        for (int[] c : cells) {
            int x = c[0] + 1;
            int y = c[1];

            if (x >= pg.length) return false;
            if (pg[x][y] != GameManager.CELLA_VUOTA) return false;
        }
        return true;
    }

    @Override
    public boolean canMoveSx() {
        int[][] cells = getCells();
        char[][] pg = GameManager.getInstance().getPlayground();

        for (int[] c : cells) {
            int x = c[0];
            int y = c[1] - 1;

            if (y < 0) return false;
            if (pg[x][y] != GameManager.CELLA_VUOTA) return false;
        }
        return true;
    }

    @Override
    public boolean canMoveDx() {
        int[][] cells = getCells();
        char[][] pg = GameManager.getInstance().getPlayground();

        for (int[] c : cells) {
            int x = c[0];
            int y = c[1] + 1;

            if (y >= pg[0].length) return false;
            if (pg[x][y] != GameManager.CELLA_VUOTA) return false;
        }
        return true;
    }

    @Override
    public void rotate() {
        if (!canRotate()) return;
        clear();
        rotationStatus = (rotationStatus + 1) % 4;
        draw();
    }

    @Override
    public void moveDown() {
        if (!canMoveDown()) return;
        clear();
        pivotX++;
        draw();
    }

    @Override
    public void moveSx() {
        if (!canMoveSx()) return;
        clear();
        pivotY--;
        draw();
    }

    @Override
    public void moveDx() {
        if (!canMoveDx()) return;
        clear();
        pivotY++;
        draw();
    }

    @Override
    public void freeze() {
        char[][] pg = GameManager.getInstance().getPlayground();
        for (int[] c : getCells()) {
            pg[c[0]][c[1]] = GameManager.CELLA_PIENA;
        }
    }

    @Override
    public boolean canDropIntoPlayground() {
        int[][] cells = getCells();
        char[][] pg = GameManager.getInstance().getPlayground();

        for (int[] c : cells) {
            if (pg[c[0]][c[1]] != GameManager.CELLA_VUOTA) return false;
        }
        return true;
    }

    @Override
    public void dropIntoPlayground() {
        pivotX = 0;
        pivotY = 4;
        draw();
    }

    // -------------------------
    // SUPPORTO INTERNO
    // -------------------------

    private void clear() {
        char[][] pg = GameManager.getInstance().getPlayground();
        for (int[] c : getCells()) {
            pg[c[0]][c[1]] = GameManager.CELLA_VUOTA;
        }
    }

    private void draw() {
        char[][] pg = GameManager.getInstance().getPlayground();
        for (int[] c : getCells()) {
            pg[c[0]][c[1]] = GameManager.CELLA_PEZZO;
        }
    }

    private int[][] getCells() {
        return getCellsForRotation(rotationStatus);
    }

    private int[][] getCellsForRotation(int rot) {
        switch (rot) {

            case 0: // ┘
                return new int[][]{
                        {pivotX, pivotY},
                        {pivotX + 1, pivotY},
                        {pivotX + 2, pivotY},
                        {pivotX + 2, pivotY + 1}
                };

            case 1: // ┴
                return new int[][]{
                        {pivotX, pivotY},
                        {pivotX, pivotY + 1},
                        {pivotX, pivotY + 2},
                        {pivotX + 1, pivotY}
                };

            case 2: // └
                return new int[][]{
                        {pivotX, pivotY},
                        {pivotX, pivotY + 1},
                        {pivotX + 1, pivotY + 1},
                        {pivotX + 2, pivotY + 1}
                };

            case 3: // ┬
                return new int[][]{
                        {pivotX, pivotY + 2},
                        {pivotX + 1, pivotY},
                        {pivotX + 1, pivotY + 1},
                        {pivotX + 1, pivotY + 2}
                };
        }

        return null;
    }
}
