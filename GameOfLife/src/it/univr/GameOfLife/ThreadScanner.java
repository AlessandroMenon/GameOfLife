package it.univr.GameOfLife;
/**
 * Questa classe setta il numero dei vicini vici e aggiorna workerMatrix alla generaione corretta
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class ThreadScanner extends Thread {
	/**
	 * Matrice di celle
	 */
    private Cella[][] matrice;
    /**
     * Matrice di appoggio per gestire il cambiamento di generazione
     */
    private int[][] workerMatrix;
    /**
     * ID del thread
     */
    private int threadID;
    /**
     * Numero di thread utilizzati
     */
    private int numberOfThread;
    
    /**
     * Inizializza le variabili matrix, threadID e numberOfThread
     * @param matrix istanza della classe Matrix
     * @param threadID ID del thread
     * @param numberOfThread Numero di thread utilizzati
     */
    public ThreadScanner(Matrix matrix, int threadID, int numberOfThread) {
        this.matrice = matrix.getMatrice();
        this.workerMatrix = matrix.getworkerMatrix();
        this.threadID = threadID;
        this.numberOfThread = numberOfThread;
    }
    /**
     * Esegue il metodo scanMatrix()
     */
    @Override
    public void run() {
        scanMatrix();
    }
    /**
     * Assegna le righe della matrice ai vari thread;
     * inoltre setta il numero dei vicini vici e aggiorna workerMatrix alla generaione corretta
     */
    private void scanMatrix() {
        int odd = 0;
        int rowsForThread = matrice.length / numberOfThread;
        int rowsEachThread = rowsForThread * threadID;

        if ((matrice.length % numberOfThread) != 0 && threadID == numberOfThread - 1) 
            odd = matrice.length % numberOfThread;
        
        for (int i = rowsEachThread; i < (rowsEachThread + rowsForThread + odd); i++) {
            for (int j = 0; j < matrice.length; j++) {
                matrice[i][j].setnViciniVivi();
                workerMatrix[i][j] = matrice[i][j].getStatoCella();
            }
        }
    }
}