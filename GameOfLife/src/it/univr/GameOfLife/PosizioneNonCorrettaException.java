package it.univr.GameOfLife;

/**
 * Questa classe gestisce gli errori relativi al non adeguato posizionamento dell'oscillatore
 * o della navicella sulla matrice
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class PosizioneNonCorrettaException extends IllegalArgumentException {

	/**
	 * Stampa su messaggi il messaggio di errore
	 * @param s messaggio di errore
	 * @param game istanza di GameOfLife
	 */
    public PosizioneNonCorrettaException(String s, GameOfLife game) {
        
        String temp = game.getMessaggi().getText();
        String [] itemRemove = {"<html>", "<head>", "</head>", "<body>", "</body>", "</html>"};
        for (int i = 0; i < itemRemove.length; i++) {
            temp = temp.replaceAll(itemRemove[i], "");
        }
        game.getMessaggi().setText("<html>" + "<head>" + "</head>" + "<body>" 
                          + temp + "<p>" + s + "</p>" + "</body> " + "</html>");

    }
}
