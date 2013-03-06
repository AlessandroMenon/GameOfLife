package it.univr.GameOfLife;
/**
 * Questa classe gestisce l'algoritmo che determina se una cella vive o muore
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class ThreadUpdater extends Thread{
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
   public ThreadUpdater(Matrix matrix, int threadID, int numberOfThread) {

        this.matrice = matrix.getMatrice();
        this.workerMatrix = matrix.getworkerMatrix();
        this.threadID = threadID;
        this.numberOfThread = numberOfThread;
    }
   
   /**
    * Esegue il metodo updateMatrix()
    */  
     @Override
    public void run() {
        updateMatrix();
    }
     
  /**
   * Gestisce l'algoritmo che determina se una cella vive o muore
   */
    private void updateMatrix() {
        Cella cella;
        int odd = 0;
        int rowsForThread = matrice.length / numberOfThread;
        int rowsEachThread = rowsForThread * threadID;

        if ((matrice.length % numberOfThread) != 0 && threadID == numberOfThread - 1) 
            odd = matrice.length % numberOfThread;
        
        for (int i = rowsEachThread; i < (rowsEachThread + rowsForThread + odd); i++) {
            for (int j = 0; j < matrice.length; j++) {
                cella = matrice[i][j];
                if (workerMatrix[i][j] == 1 && cella.getnViciniVivi() == 3)
                    cella.setStatoCella(2);                 
                else
                    if ((workerMatrix[i][j] == 2) &&
                            (cella.getnViciniVivi() < 2 || cella.getnViciniVivi() > 3))
                        
                        cella.setStatoCella(1);
            }
        }
    }
}