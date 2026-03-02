package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * REQUIREMENTS - TETRIS GAME
     *
     * Classe astratta pezzo (Piece.java) -> polimorfismo per tutti i pezzi specifici
     * - stato di rotazione
     * - pivot (x,y) => punto di rotazione
     * - occupa uno spazio preciso nella matrice/playground
     * --- ogni pezzo specifico, dato il pivot e lo stato di rotazione, sa sempre dove si trovano le altre 3 celle che lo compongono
     * - scendere => canMoveDown() + moveDown()
     * - ruotare => canRotate() + rotate()
     * --- valutare se quando un pezzo è già al margine sx/dx del playground se vogliamo farlo ruotare consendento uno spostamento laterale
     * - spostarsi a destra e sinistra => canMoveDx() + moveDx() + canMoveSx() + moveSX()
     * - collision detection
     * --- con il background blocc ato tentanto di andare in basso, a dx, a sx o ruotando
     * --- con il fondale del playground quando si muove in basso
     * --- con le pareti dx e sx quando provo a muovermi a dx/sx o ruoto un pezzo
     * --- freeze() => '@' -> '#'
     * - dropIntoPlayground()
     * --- setta il pivot ad una x/y coerente rispetto al tipo e posizione standard pivot
     * --- in modo che sia più in alto possibile nel playground
     * - canDropIntoPlayground()
     * --- se non si può, il player ha perso
     *
     * Classe Madre Abstract "Piece"
     * - Square
     * - LeftS
     * - RightS
     * - LeftL
     * - RightL
     * - HalfCross
     * - Bar
     *
     * playground
     * - la stampiano sulla console
     * - rettangolo alto 20 caselle e largo 10 caselle
     * - matrice (20 righe, 10 colonne)
     * - valore interno alla cella avrà un significato specifico
     * --- ' ' => cella vuota, un pezzo può sostare su questa cella
     * --- '#' => cella piena di "background" (pezzi bloccati), un pezzo non può sostare qui, collide
     * --- '@' => cella che indica un "pezzo" del Piece che sta scendendo (oggetto che il player sta gestendo)
     *
     * stack dei pezzi che man mano scenderanno (List<Piece>)
     * - generazione randomica => genRandomListOfPieces()
     *
     * evaluateCleanRows()
     * logica di eliminazione di una riga "full" e quindi incremento punteggio
     * - ogni volta che un pezzo si integra al fondale dobbiamo controllare se c'è una riga piena
     * - a seconda di quante righe piene ci sono nello stesso momento, daremo un differente punteggio
     * --- 1 riga => 1 punto
     * --- 2 righe => 4 punti
     * --- 3 righe => 9 punti
     * --- 4 righe => 16 punti
     * - tolte le righe piene, fare "scendere" il background in alto per occupare il posto delle righe liberate
     *
     * Timer
     * - ritmo di gioco incrementale man mano poggi pezzi sul fondale
     * - livello si incrementa => velocità di discesa si incrementa
     * - ogni 20 pezzi posati sul fondale => livello++
     * - 10 livelli, ognuno con una velocità di discesa diversa
     * --- livello 1 => 5 secondi
     * --- livello 2 => 3 secondi
     * --- livello 3 => 2 secondi
     * --- livello 4 => 1 secondo
     * --- livello 5 => 0.8 secondi
     * --- livello 6 => 0.7 secondi
     * --- livello 7 => 0.6 secondi
     * --- livello 8 => 0.5 secondi
     * --- livello 9 => 0.4 secondi
     * --- livello 10 => 0.3 secondi
     * - ogni volta che scatta il Timer, il pezzo scende in automatico, con tutto ciò che ne consegue:
     * --- piece.canModeDown()
     * ----- se può -> piece.moveDown()
     * ----- se non può -> piece.freeze() -> evaluateCleanRows() -> dropNewPiece()
     * --- il pezzo può bloccarsi sullo sfondo
     * --- riparte il drop di un nuovo pezzo
     * --- eventualmente ad una certa il player perde
     *
     * dropNewPiece()
     * - prende un pezzo dallo stack (se non ce n'è più, l'utente ha vinto)
     * - lo inserisce in un punto specifico in alto nel playground
     * --- invoco il metodo "canDropIntoPlayground()" del Piece
     * ----- se non può, il player ha perso
     * ----- se può, invoco il metodo "dropIntoPlayground()" del Piece
     *
     * WIN Conditions
     * - ho posizionato tutti gli oggetti
     *
     * LOSE Conditions
     * - non posso più droppare oggetti
     *
     * PRINT GAMEBOARD
     *
     * READ PLAYER COMMANDS -> multi-threading
     * --- thread che legge da console
     * --- thread che stampa (ogni volta che il timer scade)
     * --- thread che ri-stampa (ogni volta che l'utente muove un pezzo)
     * --- ogni volta che un utente ruota un oggetto, il thread che stampa "riparte"
     *
     * SALVATAGGIO PUNTI / RECORDS (persistenza)  aaaaaaaa
     *
     * Architettura MVC aaaaaa
     * - model: i vari Piece, Factory per forme dei pezzi, dati utente, records, playground
     * - controller: la gestione dei pezzi (movimenti, rotazione, creazione, gestione livelli), timer
     * - view: print su console
     *
     * @param args
     */

    public static void main(String[] args) {

        GameManager.getInstance().start();

    }
}
