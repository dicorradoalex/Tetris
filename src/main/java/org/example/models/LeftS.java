package org.example.models;

import org.example.GameManager;

public class LeftS extends Piece {
    private final int DEFAULT = 0;
    private final int UPWARD = 1;

    public LeftS() {
        super();
        rotationStatus = DEFAULT;
    }

    @Override
    public boolean canRotate() {
        if (pivotX > 0) {
            switch (rotationStatus) {
                case DEFAULT:
                    if (pivotY - 2 > 0) {
                        return isEmptyTile(pivotX - 1, pivotY)
                                && isEmptyTile(pivotX, pivotY - 1)
                                && isEmptyTile(pivotX + 1, pivotY - 1);
                    }

                case UPWARD:
                    if (pivotY + 2 <= GameManager.getInstance().getPlayground()[0].length && pivotY - 1 >= 0) {
                        return isEmptyTile(pivotX, pivotY - 1)
                                && isEmptyTile(pivotX + 1, pivotY)
                                && isEmptyTile(pivotX + 1, pivotY + 1);
                    }
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canMoveDown() {
        if (pivotX + 2 < GameManager.getInstance().getPlayground().length) {
            switch (rotationStatus) {
                case DEFAULT:
                    return isEmptyTile(pivotX + 1, pivotY - 1)
                            && isEmptyTile(pivotX + 2, pivotY)
                            && isEmptyTile(pivotX + 2, pivotY + 1);
                case UPWARD:
                    return isEmptyTile(pivotX + 1, pivotY)
                            && isEmptyTile(pivotX + 2, pivotY - 1);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canMoveSx() {
        if (pivotY - 2 >= 0) {
            switch (rotationStatus) {
                case DEFAULT:
                    return isEmptyTile(pivotX, pivotY - 2)
                            && isEmptyTile(pivotX + 1, pivotY - 1);
                case UPWARD:
                    return isEmptyTile(pivotX - 1, pivotY - 1)
                            && isEmptyTile(pivotX, pivotY - 2)
                            && isEmptyTile(pivotX + 1, pivotY - 2);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canMoveDx() {
        switch (rotationStatus) {
            case DEFAULT:
                if (pivotY + 2 >= GameManager.getInstance().getPlayground()[0].length) {
                    return false;
                } else {
                    return isEmptyTile(pivotX, pivotY + 1)
                            && isEmptyTile(pivotX + 1, pivotY + 2);
                }
            case UPWARD:
                if (pivotY + 1 < GameManager.getInstance().getPlayground()[0].length) {
                    return isEmptyTile(pivotX - 1, pivotY + 1)
                            && isEmptyTile(pivotX, pivotY + 1)
                            && isEmptyTile(pivotX + 1, pivotY);
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    @Override
    public void rotate() {
        clearTileLeftS();
        if (rotationStatus == DEFAULT) {
            rotationStatus = UPWARD;
            setTileLeftS();
        } else {
            rotationStatus = DEFAULT;
            setTileLeftS();
        }

    }

    @Override
    public void moveDown() {
        clearTileLeftS();
        pivotX++;
        setTileLeftS();
    }

    @Override
    public void moveSx() {
        clearTileLeftS();
        pivotY--;
        setTileLeftS();
    }

    @Override
    public void moveDx() {
        clearTileLeftS();
        pivotY++;
        setTileLeftS();
    }

    @Override
    public void freeze() {
        clearTileLeftS();
        settleTileLeftS();
    }

    @Override
    public boolean canDropIntoPlayground() {
        return isEmptyTile(0, 4)
                && isEmptyTile(0, 3)
                && isEmptyTile(1, 4)
                && isEmptyTile(1, 5);
    }

    @Override
    public void dropIntoPlayground() {
        this.pivotX = 0;
        this.pivotY = 4;
        setTileLeftS();
    }

    public void clearTileLeftS() {
        switch (rotationStatus) {
            case DEFAULT:
                setTileEmpty(pivotX, pivotY - 1);
                setTileEmpty(pivotX, pivotY);
                setTileEmpty(pivotX + 1, pivotY);
                setTileEmpty(pivotX + 1, pivotY + 1);
                break;
            case UPWARD:
                setTileEmpty(pivotX - 1, pivotY);
                setTileEmpty(pivotX, pivotY);
                setTileEmpty(pivotX, pivotY - 1);
                setTileEmpty(pivotX + 1, pivotY - 1);
                break;
        }
    }

    public void setTileLeftS() {
        switch (rotationStatus) {
            case DEFAULT:
                setTilePiece(pivotX, pivotY - 1);
                setTilePiece(pivotX, pivotY);
                setTilePiece(pivotX + 1, pivotY);
                setTilePiece(pivotX + 1, pivotY + 1);
                break;
            case UPWARD:
                setTilePiece(pivotX - 1, pivotY);
                setTilePiece(pivotX, pivotY);
                setTilePiece(pivotX, pivotY - 1);
                setTilePiece(pivotX + 1, pivotY - 1);
                break;
        }
    }

    public void settleTileLeftS() {
        switch (rotationStatus) {
            case DEFAULT:
                setTileFull(pivotX, pivotY - 1);
                setTileFull(pivotX, pivotY);
                setTileFull(pivotX + 1, pivotY);
                setTileFull(pivotX + 1, pivotY + 1);
                break;
            case UPWARD:
                setTileFull(pivotX - 1, pivotY);
                setTileFull(pivotX, pivotY);
                setTileFull(pivotX, pivotY - 1);
                setTileFull(pivotX + 1, pivotY - 1);
                break;
        }
    }

    public boolean isEmptyTile(int pivotX, int pivotY) {
        return GameManager.getInstance().getPlayground()[pivotX][pivotY] == GameManager.CELLA_VUOTA
                || GameManager.getInstance().getPlayground()[pivotX][pivotY] == GameManager.CELLA_PEZZO;
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
