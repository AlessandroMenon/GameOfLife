package it.univr.GameOfLife;

/**
 * Questa classe gestisce i thread per il cambio di generazione
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class Gestore extends Thread {

	/**
	 * Componente grafico che gestisce la velocità di generazione delle celle
	 */
    private Slider slider;
    /**
	 * Classe che che si occupa della crezione e gestione della matrice di celle
	 */
    private Matrix matrix;
    /**
	 * Array di thread per aggiornare il numero di vicini vivi
	 * e aggiorna la matrice workerMatrix
	 */
    private ThreadScanner[] scanners;
    /**
	 * Array di thread per aggiornare lo stato delle celle
	 */
    private ThreadUpdater[] updaters;
    /**
	 * Numero di thread utilizzati
	 */
    private int numberOfThread;
    /**
     * flag per far partire o fermare il thread
     */
    private boolean threadWork;


    /**
	 * Crea e inizializza il Gestore
	 * @param matrix istanza della classe Matrix
	 * @param slider istanza della classe Slider
	 * @param numberOfThread numero di thread utilizzati
	 */
    public Gestore(Matrix matrix, Slider slider, int numberOfThread) {
        this.matrix = matrix;
        this.slider = slider;
        this.numberOfThread = numberOfThread;
        threadWork = true;

    }

    /**
	 * Fa partire il thread Gestore
	 * @exception ex InterruptedException 
	 */
    @Override
    public void run() {
        while (threadWork) {
         //   long startTime = System.currentTimeMillis();
            nextScan(numberOfThread);
            try {
                this.sleep(Math.abs(slider.getValue() - 500));
            } catch (InterruptedException ex) {}
            nextGeneration(numberOfThread);
            matrix.repaint();
           // System.out.println("elapsed time " + (System.currentTimeMillis() - startTime) + "ms\n");

        }
    }

    /**
	 * Ferma il thread Gestore
	 */
    public void stopGestore() {
        threadWork = false;
    }

	/**
	 * Crea gli scanner e attende la loro terminazione
	 * @param numberOfThread  - numero di thread
	 */
    private void nextScan(int numberOfThread) {
        scanners = createScannerThreads(matrix, numberOfThread);
        waitForScannerThreadsToFinish(scanners);
    }

    /**
     * Crea gli updater e attende la loro terminazione
     * @param numberOfThread - numero di thread
     */
    private void nextGeneration(int numberOfThread) {
        updaters = createUpdaterThreads(matrix, numberOfThread);
        waitForUpdaterThreadsToFinish(updaters);
    }

    /**
     * Fa partire i thread scanner
     * @param matrix - istanza della classe Matrix
     * @param numberOfThread - numero di thread
     * @return scanner - array di ThreadScanner
     */
    private ThreadScanner[] createScannerThreads(Matrix matrix, int numberOfThread) {

        ThreadScanner[] scanner = new ThreadScanner[numberOfThread];

        for (int threadID = 0; threadID < numberOfThread; threadID++) {
            (scanner[threadID] = new ThreadScanner(matrix, threadID, numberOfThread)).start();
        }
        return scanner;
    }

    /**
     * Fa partire i thread updater
     * @param matrix - istanza della classe Matrix
     * @param numberOfThread - numero di thread
     * @return updater - array di ThreadUpdater
     */
    private ThreadUpdater[] createUpdaterThreads(Matrix matrix, int numberOfThread) {
        ThreadUpdater[] updater = new ThreadUpdater[numberOfThread];

        for (int threadID = 0; threadID < numberOfThread; threadID++) {
            (updater[threadID] = new ThreadUpdater(matrix, threadID, numberOfThread)).start();
        }
        return updater;
    }

    /**
     * Aspetta la terminazione dei thread
     * @param updaters - array di ThreadUpdater
     */
    private void waitForUpdaterThreadsToFinish(ThreadUpdater[] updaters) {
        for (ThreadUpdater updater : updaters) {
            try {
                updater.join();
            } catch (InterruptedException e) {}
        }
    }

    /**
     * Aspetta la terminazione dei thread
     * @param scanners - array di ThreadScanner
     */
    private void waitForScannerThreadsToFinish(ThreadScanner[] scanners) {
        for (ThreadScanner scanner : scanners) {
            try {
                scanner.join();
            } catch (InterruptedException e) {}
        }
    }
}
