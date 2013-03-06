package it.univr.GameOfLife;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Questa classe crea la matrice delle celle 
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */

public class Matrix extends JPanel {
	/**
	 * Classe che costruisce i patterns
	 */
    private Patterns patterns;
	/**
	 * Matrice che contiene le celle
	 */
    private Cella[][] matrice;
    /**
     * Matrice di appoggio per gestire il cambio di generazione
     */
    private int[][] workerMatrix;
    /**
     * Dimensione della matrice
     */
    private final int DIM = 50;
    /**
     * Variabile per la scelta dei patterns. Di default è 0.
     */
    private int sceltaPatterns = 0;
    /**
     * Settato a true quando il mouse viene premuto
     */
    private boolean pressMouse = false;
    /**
     * Settato a true quando il mouse destro viene premuto
     */
    private boolean pressMouseRight = false;
    /**
     * Memorizza lo stato in cui si vuole trasformare le celle
     */
    private int state;
	/**
	 * Crea e inizializza la matrice
	 * @param game istanza della classe GameOfLife
	 */
    public Matrix(GameOfLife game) {
        patterns = new Patterns(this, game);
        matrice = new Cella[DIM][DIM];
        workerMatrix = new int[DIM][DIM];
        setLayout(new GridLayout(DIM, DIM));
        initMatrix();
        initListaVicini();

    }
	/**
	 * Inizializza la matrice e workerMatrix e aggiunge un MouseListener ad ogni cella
	 */
    private void initMatrix() {
        for (int i = 0; i < DIM; ++i) {
            for (int j = 0; j < DIM; ++j) {
                workerMatrix[i][j] = 1; //Tutti morti all'inizio
                matrice[i][j] = new Cella(i, j, new LinkedList());
                matrice[i][j].addMouseListener(new MouseSpy());
                add(matrice[i][j]);
            }
        }
    }
	/**
	 * Inizializza la lista dei vicini di ciascuna matrice
	 */
    private void initListaVicini() {
        for (int i = 0; i < DIM; ++i) {
            for (int j = 0; j < DIM; ++j) {
                upDateListaVicini(i, j);
            }
        }
    }
	/**
	 * Restituisce la matrice delle celle
	 * @return matrice - matrice di celle
	 */
    public Cella[][] getMatrice() {
        return matrice;
    }
	/**
	 * Restituisce la matrice worker
	 * @return workerMatrix - matrice di appoggio
	 */
    public int[][] getworkerMatrix() {
        return workerMatrix;
    }
	/**
	 * Setta la matrice di celle
	 */
    public void setMatrice(Cella[][] matrice) {
        this.matrice = matrice;
    }
	/**
	 * Setta la matrice worker di appoggio
	 */
    public void setworkerMatrix(int[][] workerMatrix) {
        this.workerMatrix = workerMatrix;
    }
	/**
	 * Setta la scelta dei pattenrs
	 */
    public void setSceltaPatterns(int sceltaPatterns) {
        this.sceltaPatterns = sceltaPatterns;
    }
    /**
     * Ritorna la dimensione della matrice
     * @return DIM - dimensione della matrice
     */
    public int getDIM(){
    	return DIM;
    }
	/**
	 * Ripulisce la matrice portandola allo stato d'origine
	 */
    public void clearMatrix() {
        for (int i = 0; i < DIM; ++i) 
            for (int j = 0; j < DIM; ++j) 
                matrice[i][j].setStatoCella(1);
    }
	/**
	 * Aggiorna la lista dei vicini vivi. Metodo chiamato dal initListaVicini() 
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 */
    private void upDateListaVicini(int i, int j) {

        if (i != 0 && j != 0 && i != DIM - 1 && j != DIM - 1) // Aggiorna tutti gli 8 vicini alla cella
            upDate(i, j, -1, -1, 1, 1);
       
        if (i == 0 && j == 0) // Aggiorna i vicini delle prima cella
            upDate(i, j, i, j, 1, 1);
        
        if (i == 0 && j == DIM - 1) // Aggiorna i vicini dell'ultima cella nella prima fila
            upDate(i, j, 0, -1, 1, 0);
        
        if (i == DIM - 1 && j == 0) // Aggiorna i vicini dell'ultima cella nella prima colonna
            upDate(i, j, -1, 0, 0, 1);
        
        if (i == DIM - 1 && j == DIM - 1) // Aggiorna i vicini dell'ultima cella nell'ultima riga e colonna
            upDate(i, j, -1, -1, 0, 0);
        
        if (i == 0 && j - 1 >= 0 && j < DIM - 1) // Aggiorna i vicini delle celle della prima riga
            upDate(i, j, 0, -1, 1, 1);
        
        if (j == 0 && i - 1 >= 0 && i < DIM - 1) // Aggiorna i vicini delle celle della prima colonna
            upDate(i, j, -1, 0, 1, 1);
        
        if (i == DIM - 1 && j - 1 >= 0 && j < DIM - 1) // Aggiorna i vicini delle celle dell'ultima riga
            upDate(i, j, -1, -1, 0, 1);
        
        if (j == DIM - 1 && i - 1 >= 0 && i < DIM - 1) // Aggiorna i vicini delle celle dell'ultima colonna
            upDate(i, j, -1, -1, 1, 0);

    }
	/**
	 * In base alle variabili settate dal metodo upDateListaVicini(int i, int j) aggiorna la lista dei vicini di ciscuna cella
     * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice
	 * @param row - riga di partenza
	 * @param col - colonna di partenza
	 * @param limitK - limite massimo che può raggiungere la riga
	 * @param limitM - limite massimo che può raggiungere la colonna
	 */
    private void upDate(int i, int j, int row, int col, int limitK, int limitM) {
        
        for (int k = row; k <= limitK; ++k) 
            for (int m = col; m <= limitM; ++m) 
                if ((k + i) != i || (m + j) != j) 
                    matrice[i][j].getVicini().add(matrice[i + k][j + m]);
    }
    /**
     * Classe interna di Matrix che gestisce gli eventi lanciati dal mouse
     * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
     */
    private class MouseSpy implements MouseListener {
    	/**
    	 * In base al pulsante del mouse premuto e allo stato della cella in quella posizione aggiorna lo stato della cella  
    	 * @param e - Evento lanciato dal mouse 
    	 */
        @Override
        public void mouseClicked(MouseEvent e) {

            Cella c = (Cella) e.getSource();
            if (sceltaPatterns == 0) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    updateLiveOrDead(c);
                } else if (e.getButton() == MouseEvent.BUTTON3) 
                    updateAlwaysDeadOrDead(c);
            }
            else
                if(e.getButton() == MouseEvent.BUTTON1)
                    patterns.patternsScelta(sceltaPatterns, c.getPosX(), c.getPosY());
                else
                    updateAlwaysDeadOrDead(c);
        }
    	/**
    	 * Rileva la pressione del mouse per gestire il trascinamento   
    	 * @param e - Evento lanciato dal mouse 
    	 */
        @Override
        public void mousePressed(MouseEvent e) {

            pressMouse = true;
            Cella c = (Cella) e.getSource();
            if (c.isDead()) {
                state = 2;
            } else if (c.isLive()) 
                state = 1;
            if (e.getButton() == MouseEvent.BUTTON1) {
                pressMouseRight = true;
                if (sceltaPatterns == 0) 
                    updateLiveOrDead(c);
            }
        }
    	/**
    	 * Rileva il rilascio del mouse   
    	 * @param e - Evento lanciato dal mouse 
    	 */
        @Override
        public void mouseReleased(MouseEvent e) {

            pressMouse = false;
            pressMouseRight = false;
        }
    	/**
    	 * Rileva lo spostamento del mouse dentro alla matrice. Gestisce il trascinamento e il popolamento di più celle
    	 * @param e - Evento lanciato dal mouse 
    	 */
        @Override
        public void mouseEntered(MouseEvent e) {

            Cella c = (Cella) e.getSource();
            if (pressMouse == true && pressMouseRight == true && sceltaPatterns == 0) 
                updateLiveOrDead(c);
        }
    	/**
    	 * Gestisce gli eventi quando il mouse esce
    	 * @param e - Evento lanciato dal mouse 
    	 */
        @Override
        public void mouseExited(MouseEvent e) {}

    	/**
    	 * Aggiorna la cella. Se è viva la rende morta e se è morta la rende viva 
    	 * @param c - cella
    	 */
        private void updateLiveOrDead(Cella c) {

            if (!c.isAlwaysDead()) 
                c.setStatoCella(state);
        }
    	/**
    	 * Aggiorna la cella. Se è sempre morta la rende morta e se è morta la rende sempre morta 
    	 * @param c - cella
    	 */
        private void updateAlwaysDeadOrDead(Cella c) {
            
            if (c.isAlwaysDead())
                c.setStatoCella(1);
            else 
                c.setStatoCella(3);
        }
    }
}
