package org.example.models;

public abstract class Piece {
    
    int pivotX;
    int pivotY;
    int rotationStatus;

    // - occupa uno spazio preciso nella matrice/playground
    // --- ogni pezzo specifico, dato il pivot e lo stato di rotazione, sa sempre dove si trovano le altre 3 celle che lo compongono


    public Piece() {
    }


    // --- valutare se quando un pezzo è già al margine sx/dx del playground se vogliamo farlo ruotare consendento uno spostamento laterale
    public abstract boolean canRotate(); 
    public abstract boolean canMoveDown(); 
    public abstract boolean canMoveSx(); 
    public abstract boolean canMoveDx(); 
    public abstract void rotate(); 
    public abstract void moveDown(); 
    public abstract void moveSx(); 
    public abstract void moveDx(); 

    //  collision detection
    //  - con il background bloccato tentanto di andare in basso, a dx, a sx o ruotando
    //  - con il fondale del playground quando si muove in basso
    //  - con le pareti dx e sx quando provo a muovermi a dx/sx o ruoto un pezzo

    public abstract void freeze(); // aggiorna playground: '@' -> '#'

    //  --- se non si può, il player ha perso
    public abstract boolean canDropIntoPlayground();
    //  --- setta il pivot ad una x/y coerente rispetto al tipo e posizione standard pivot
    //  --- in modo che sia più in alto possibile nel playground
    public abstract void dropIntoPlayground();

}
