package org.example.models;

import org.example.GameManager;

public class RightS extends Piece {
    public final static int DEFAULT = 0;
    public final static int UPWARD = 1;

    public RightS() {
        super();
        rotationStatus = DEFAULT;
    }

    @Override
    public boolean canRotate() {
        switch (rotationStatus) {
            case DEFAULT:
                return isEmptyTile(pivotX - 1, pivotY)
                        && isEmptyTile(pivotX, pivotY + 1)
                        && isEmptyTile(pivotX + 1, pivotY + 1);
            case UPWARD:
                return isEmptyTile(pivotX, pivotY + 1)
                        && isEmptyTile(pivotX + 1, pivotY - 1)
                        && isEmptyTile(pivotX + 1, pivotY);
            default:
                return false;
        }
    }

    @Override
    public boolean canMoveDown() {
        if (pivotX + 2 < GameManager.getInstance().getPlayground().length) {
            switch (rotationStatus) {
                case DEFAULT:
                    return isEmptyTile(pivotX + 1, pivotY + 1)
                            && isEmptyTile(pivotX + 2, pivotY - 1)
                            && isEmptyTile(pivotX + 2, pivotY);
                case UPWARD:
                    return isEmptyTile(pivotX + 1, pivotY)
                            && isEmptyTile(pivotX + 2, pivotY + 1);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canMoveSx() {
        switch (rotationStatus) {
            case DEFAULT:
                if (pivotY - 2 >= 0) {
                    return isEmptyTile(pivotX, pivotY - 1)
                            && isEmptyTile(pivotX + 1, pivotY - 2);
                } else {
                    return false;
                }
            case UPWARD:
                if (pivotY - 1 >= 0) {
                    return isEmptyTile(pivotX - 1, pivotY - 1)
                            && isEmptyTile(pivotX, pivotY - 1)
                            && isEmptyTile(pivotX + 1, pivotY);
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    @Override
    public boolean canMoveDx() {
        if (pivotY + 2 >= GameManager.getInstance().getPlayground()[0].length) {
            switch (rotationStatus) {
                case DEFAULT:
                    return isEmptyTile(pivotX, pivotY + 2)
                            && isEmptyTile(pivotX + 1, pivotY + 1);
                case UPWARD:
                    return isEmptyTile(pivotX - 1, pivotY + 1)
                            && isEmptyTile(pivotX, pivotY + 2)
                            && isEmptyTile(pivotX + 1, pivotY + 2);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void rotate() {
        if (rotationStatus == DEFAULT) {
            rotationStatus = UPWARD;
        } else {
            rotationStatus = DEFAULT;
        }

    }

    @Override
    public void moveDown() {
        clearTileRightS();
        pivotX++;
        setTileRightS();
    }

    @Override
    public void moveSx() {
        clearTileRightS();
        pivotY--;
        setTileRightS();
    }

    @Override
    public void moveDx() {
        clearTileRightS();
        pivotY++;
        setTileRightS();
    }

    @Override
    public void freeze() {
        clearTileRightS();
        settleTileRightS();
    }

    @Override
    public boolean canDropIntoPlayground() {
        return isEmptyTile(0, 4)
                && isEmptyTile(0, 5)
                && isEmptyTile(1, 4)
                && isEmptyTile(1, 3);
    }

    @Override
    public void dropIntoPlayground() {
        this.pivotX = 0;
        this.pivotY = 4;
        setTileRightS();
    }

    public void clearTileRightS() {
        switch (rotationStatus) {
            case DEFAULT:
                setTileEmpty(pivotX, pivotY + 1);
                setTileEmpty(pivotX, pivotY);
                setTileEmpty(pivotX + 1, pivotY - 1);
                setTileEmpty(pivotX + 1, pivotY);
                break;
            case UPWARD:
                setTileEmpty(pivotX - 1, pivotY);
                setTileEmpty(pivotX, pivotY);
                setTileEmpty(pivotX, pivotY + 1);
                setTileEmpty(pivotX + 1, pivotY + 1);
                break;
        }
    }

    public void setTileRightS() {
        switch (rotationStatus) {
            case DEFAULT:
                setTilePiece(pivotX, pivotY + 1);
                setTilePiece(pivotX, pivotY);
                setTilePiece(pivotX + 1, pivotY - 1);
                setTilePiece(pivotX + 1, pivotY);
                break;
            case UPWARD:
                setTilePiece(pivotX - 1, pivotY);
                setTilePiece(pivotX, pivotY);
                setTilePiece(pivotX, pivotY + 1);
                setTilePiece(pivotX + 1, pivotY + 1);
                break;
        }
    }

    public void settleTileRightS() {
        switch (rotationStatus) {
            case DEFAULT:
                setTileFull(pivotX, pivotY);
                setTileFull(pivotX, pivotY + 1);
                setTileFull(pivotX + 1, pivotY - 1);
                setTileFull(pivotX + 1, pivotY);
                break;
            case UPWARD:
                setTileFull(pivotX - 1, pivotY);
                setTileFull(pivotX, pivotY);
                setTileFull(pivotX, pivotY + 1);
                setTileFull(pivotX + 1, pivotY + 1);
                break;
        }
    }

    public boolean isEmptyTile(int pivotX, int pivotY) {
        return GameManager.getInstance().getPlayground()[pivotX][pivotY] == GameManager.CELLA_VUOTA;
    }

    private void setTileFull(int pivotX, int pivotY) {
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_PIENA;
    }

    private void setTileEmpty(int pivotX, int pivotY) {
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_VUOTA;
    }

    private void setTilePiece(int pivotX, int pivotY) {
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_PEZZO;
    }
}
