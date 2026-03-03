package org.example;

public class GameLoopTimerThread implements Runnable {

    private volatile boolean running = true;
    private long tickDelay = 1000; // Iniziale


    private void checkGameSpeed() {
        //  - ogni 20 pezzi posati sul fondale => livello++
        //  - 10 livelli, ognuno con una velocità di discesa diversa


        switch (GameManager.getInstance().getGameLevel() ) { // TODO
            case 1:
                //  --- livello 1 => 5 secondi
                this.tickDelay = 5000;
                break;
            case 2:
                //  --- livello 2 => 3 secondi
                this.tickDelay = 3000;
                break;
            case 3:
                //  --- livello 3 => 2 secondi
                this.tickDelay = 2000;
                break;
            case 4:
                //  --- livello 4 => 1 secondo
                this.tickDelay = 1000;
                break;
            case 5:
                //  --- livello 5 => 0.8 secondi
                this.tickDelay = 800;
                break;
            case 6:
                //  --- livello 6 => 0.7 secondi
                this.tickDelay = 700;
                break;
            case 7:
                //  --- livello 7 => 0.6 secondi
                this.tickDelay = 600;
                break;
            case 8:
                //  --- livello 8 => 0.5 secondi
                this.tickDelay = 500;
                break;
            case 9:
                //  --- livello 9 => 0.4 secondi
                this.tickDelay = 400;
                break;
            case 10:
                //  --- livello 10 => 0.3 secondi
                this.tickDelay = 300;
                break;
        }
    }

    @Override
    public void run() {
        while (running) {
            execGameLogicAtTimerExpiry();

            try {
                Thread.sleep(tickDelay);   // velocità caduta
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stop() {
        running = false;
    }

    private void execGameLogicAtTimerExpiry() {
        GameManager.getInstance().moveCurrentPieceDownForTimeExpiry();
        checkGameSpeed();
    }
}
