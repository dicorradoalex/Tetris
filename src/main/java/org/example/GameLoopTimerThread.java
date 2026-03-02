package org.example;

public class GameLoopTimerThread implements Runnable {

    private volatile boolean running = true;
    private long tickDelay = 1000; // Iniziale

    public void setSpeed(long newDelay) {
        this.tickDelay = newDelay;

        //  - ogni 20 pezzi posati sul fondale => livello++ (TODO: chi lo fa?)
        //  - 10 livelli, ognuno con una velocità di discesa diversa (ENUM?)
        //  --- livello 1 => 5 secondi
        //  --- livello 2 => 3 secondi
        //  --- livello 3 => 2 secondi
        //  --- livello 4 => 1 secondo
        //  --- livello 5 => 0.8 secondi
        //  --- livello 6 => 0.7 secondi
        //  --- livello 7 => 0.6 secondi
        //  --- livello 8 => 0.5 secondi
        //  --- livello 9 => 0.4 secondi
        //  --- livello 10 => 0.3 secondi

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
    }
}
