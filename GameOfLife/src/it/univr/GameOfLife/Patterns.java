package it.univr.GameOfLife;
/**
 * Questa classe costruisce i Patterns (Oscillatori e Navicelle) in base alla scelta fatta dal widget
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class Patterns {
    /**
	 * Classe che che si occupa della crezione e gestione della matrice di celle
	 */
    private Matrix matrix;
    /**
	 * Classe che inizializza i componenti grafici e lancia il gioco.
	 */
    private GameOfLife game;
    
	/**
	 * Inizializza le variabili
	 * @param matrix istanza della classe Matrix
	 * @param game istanza della classe GameOfLife
	 */
    public Patterns(Matrix matrix, GameOfLife game) {
        this.game = game;
        this.matrix = matrix;
    }
	/**
	 * Costruisce il pattern in base alla scelta
	 * @param scelta - scelta fatta dall'utente in base al valore del widget
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 */
    protected void patternsScelta(int Scelta, int i, int j) {
        switch (Scelta) {
            case 0:
                newDefault(i, j);
                break;
            case 1:
                newGlider(i, j);
                break;
            case 2:
                newLightweightSpaceShip(i, j);
                break;
            case 3:
                newBlinker(i, j);
                break;
            case 4:
                newToad(i, j);
                break;
            case 5:
                newBeacon(i, j);
                break;
            case 6:
                newPulsar(i, j);
                break;
            case 7:
                newGosperGliderGun(i, j);
                break;
            default:
                break;
        }
    }
	/**
	 * Costruisce una singola cella
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 */
    private void newDefault(int i, int j) {
        matrix.getMatrice()[i][j].setStatoCella(2);
    }
	/**
	 * Costruisce un Glider
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newGlider(int i, int j) {

        if (matrix.getMatrice().length - (i + 2) > 0
                && matrix.getMatrice().length - (j + 1) > 0
                && j - 1 >= 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 1].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 1].setStatoCella(2);
            matrix.getMatrice()[i + 2][j].setStatoCella(2);
            matrix.getMatrice()[i + 2][j - 1].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione del <b>Glider</b> non sufficiente", game);
    }
	/**
	 * Costruisce un LightweightSpaceShip
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newLightweightSpaceShip(int i, int j) {

        if (matrix.getMatrice().length - (i + 2) > 0
                && i - 1 >= 0
                && matrix.getMatrice().length - (j + 4) > 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 1].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 2].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 3].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 4].setStatoCella(2);
            matrix.getMatrice()[i][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 3].setStatoCella(2);
            matrix.getMatrice()[i + 2][j].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione della <b>LightweightSpaceShip</b> non sufficiente", game);
    }
	/**
	 * Costruisce un LightweightSpaceShip
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newBlinker(int i, int j) {

        if (matrix.getMatrice().length - (j + 2) > 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i][j + 1].setStatoCella(2);
            matrix.getMatrice()[i][j + 2].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione del <b>Blinker</b> non sufficiente", game);
    }
	/**
	 * Costruisce un Toad
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newToad(int i, int j) {

        if (matrix.getMatrice().length - (i + 1) > 0
                && matrix.getMatrice().length - (j + 2) > 0
                && j - 1 >= 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i][j + 1].setStatoCella(2);
            matrix.getMatrice()[i][j + 2].setStatoCella(2);
            matrix.getMatrice()[i + 1][j - 1].setStatoCella(2);
            matrix.getMatrice()[i + 1][j].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 1].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione del <b>Toad</b> non sufficiente", game);
    }
	/**
	 * Costruisce un Beacon
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newBeacon(int i, int j) {

        if (matrix.getMatrice().length - (i + 3) > 0
                && matrix.getMatrice().length - (j + 3) > 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i][j + 1].setStatoCella(2);
            matrix.getMatrice()[i + 1][j].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 1].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 2].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 3].setStatoCella(2);
            matrix.getMatrice()[i + 3][j + 2].setStatoCella(2);
            matrix.getMatrice()[i + 3][j + 3].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione del <b>Beacon</b> non sufficiente", game);
    }
	/**
	 * Costruisce un Pulsar
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newPulsar(int i, int j) {

        if (matrix.getMatrice().length - (i + 4) > 0
                && matrix.getMatrice().length - (j + 4) > 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i + 1][j].setStatoCella(2);
            matrix.getMatrice()[i + 2][j].setStatoCella(2);
            matrix.getMatrice()[i + 3][j].setStatoCella(2);
            matrix.getMatrice()[i + 4][j].setStatoCella(2);
            matrix.getMatrice()[i][j + 2].setStatoCella(2);
            matrix.getMatrice()[i][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 3][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 4][j + 4].setStatoCella(2);
            matrix.getMatrice()[i + 4][j + 2].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione del <b>Pulsar</b> non sufficiente", game);
    }
	/**
	 * Costruisce un GosperGliderGun
	 * @param i - posizione selezionata dell'ascissa della matrice 
	 * @param j - posizione selezionata dell'ordinata della matrice 
	 * @throws  PosizioneNonCorrettaException se manca lo spazio per la costruzione
	 */
    private void newGosperGliderGun(int i, int j) {

        if (matrix.getMatrice().length - (i + 12) > 0
                && i - 2 >= 0
                && matrix.getMatrice().length - (j + 37) > 0) {
            matrix.getMatrice()[i][j].setStatoCella(2);
            matrix.getMatrice()[i][j + 1].setStatoCella(2);
            matrix.getMatrice()[i + 1][j].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 1].setStatoCella(2);

            matrix.getMatrice()[i][j + 9].setStatoCella(2);
            matrix.getMatrice()[i][j + 10].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 8].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 8].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 9].setStatoCella(2);
            matrix.getMatrice()[i + 1][j + 10].setStatoCella(2);

            matrix.getMatrice()[i + 2][j + 16].setStatoCella(2);
            matrix.getMatrice()[i + 3][j + 16].setStatoCella(2);
            matrix.getMatrice()[i + 4][j + 16].setStatoCella(2);
            matrix.getMatrice()[i + 2][j + 17].setStatoCella(2);
            matrix.getMatrice()[i + 3][j + 18].setStatoCella(2);

            matrix.getMatrice()[i - 1][j + 22].setStatoCella(2);
            matrix.getMatrice()[i][j + 22].setStatoCella(2);
            matrix.getMatrice()[i][j + 23].setStatoCella(2);
            matrix.getMatrice()[i - 2][j + 23].setStatoCella(2);
            matrix.getMatrice()[i - 2][j + 24].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 24].setStatoCella(2);

            matrix.getMatrice()[i - 2][j + 34].setStatoCella(2);
            matrix.getMatrice()[i - 2][j + 35].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 34].setStatoCella(2);
            matrix.getMatrice()[i - 1][j + 35].setStatoCella(2);

            matrix.getMatrice()[i + 5][j + 35].setStatoCella(2);
            matrix.getMatrice()[i + 5][j + 36].setStatoCella(2);
            matrix.getMatrice()[i + 6][j + 35].setStatoCella(2);
            matrix.getMatrice()[i + 6][j + 37].setStatoCella(2);
            matrix.getMatrice()[i + 7][j + 35].setStatoCella(2);

            matrix.getMatrice()[i + 10][j + 24].setStatoCella(2);
            matrix.getMatrice()[i + 10][j + 25].setStatoCella(2);
            matrix.getMatrice()[i + 10][j + 26].setStatoCella(2);
            matrix.getMatrice()[i + 11][j + 24].setStatoCella(2);
            matrix.getMatrice()[i + 12][j + 25].setStatoCella(2);
        } else 
            throw new PosizioneNonCorrettaException("Spazio per la creazione del <b>GosperGliderGun</b> non sufficiente", game);
    }
}
