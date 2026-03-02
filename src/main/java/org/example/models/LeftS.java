package org.example.models;

import org.example.GameManager;

public class LeftS extends Piece{
    public final static int DEFAULT = 0;
    public final static int UPWARD = 1;
    public LeftS(){
        super();
        rotationStatus = DEFAULT;
    }

    @Override
    public boolean canRotate() {
        switch (rotationStatus){
            case DEFAULT:
                return isEmptyTile(pivotX-1,pivotY)
                        && isEmptyTile(pivotX,pivotY-1)
                        && isEmptyTile(pivotX+1,pivotY-1);
            case UPWARD:
                return isEmptyTile(pivotX-1,pivotY)
                        && isEmptyTile(pivotX+1,pivotY)
                        && isEmptyTile(pivotX+1,pivotY+1);
            default: return false;
        }
    }

    @Override
    public boolean canMoveDown() {
        switch (rotationStatus){
            case DEFAULT:
                return isEmptyTile(pivotX+1,pivotY-1)
                        && isEmptyTile(pivotX+2,pivotY)
                        && isEmptyTile(pivotX+2,pivotY+1);
            case UPWARD:
                return isEmptyTile(pivotX+1,pivotY)
                        && isEmptyTile(pivotX+2,pivotY-1);
            default: return false;
        }
    }

    @Override
    public boolean canMoveSx() {
        switch (rotationStatus){
            case DEFAULT:
                return isEmptyTile(pivotX,pivotY-2)
                        && isEmptyTile(pivotX+1,pivotY-1);
            case UPWARD:
                return isEmptyTile(pivotX-1,pivotY-1)
                        && isEmptyTile(pivotX,pivotY-2)
                        && isEmptyTile(pivotX+1,pivotY-2);
            default: return false;
        }
    }

    @Override
    public boolean canMoveDx() {
        switch (rotationStatus){
            case DEFAULT:
                return isEmptyTile(pivotX,pivotY+1)
                        && isEmptyTile(pivotX+1,pivotY+2);
            case UPWARD:
                return isEmptyTile(pivotX-1,pivotY+1)
                        && isEmptyTile(pivotX,pivotY+1)
                        && isEmptyTile(pivotX+1,pivotY);
            default: return false;
        }
    }

    @Override
    public void rotate() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveSx() {

    }

    @Override
    public void moveDx() {

    }

    @Override
    public void freeze() {

    }

    @Override
    public boolean canDropIntoPlayground() {
        return false;
    }

    @Override
    public void dropIntoPlayground() {

    }
    public void clearTileLeftS(){

    }
    public void setTileLeftS(){}
    public void settleTileLeftS(){}
    public boolean isEmptyTile(int pivotX, int pivotY){return GameManager.getInstance().getPlayground()[pivotX][pivotY]==GameManager.CELLA_VUOTA;}
    private void setTileFull(int pivotX, int pivotY){
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_PIENA;
    }
    private void setTileEmpty(int pivotX, int pivotY){
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_VUOTA;
    }
    private void setTilePiece(int pivotX, int pivotY){
        GameManager.getInstance().getPlayground()[pivotX][pivotY] = GameManager.CELLA_PEZZO;
    }
}
