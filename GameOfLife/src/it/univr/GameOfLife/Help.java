package it.univr.GameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextPane;
/**
 * Questa classe crea il JTextPane Help e il timer per il cambio del testo
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class Help extends JTextPane {
	/**
	 * Timer per cambiare periodicamente la stringa da visualizzare nell'Help
	 */
    private Timer t;
    /**
     * Stringa che contiene le regole del gioco
     */
    private final String s = "<h2>Game of Life</h2><br><p>Regole del gioco : </p>"
            + " <ol><li>Una cella morta <br>con esattamente 3 vicini vivi<br> diventa viva</li><br>"
            + "<li>Una cella viva <br>con 2 o 3 vicini vivi sopravvive <br>altrimenti muore</li></ol>";
    /**
     * Stringa che contiene i comandi del gioco
     */
    private final String s1 = "<h2>Game of Life</h2><br><p>Comandi : </p>"
            + " <ol><li>Click sinistro :<br>trasforma una cella da viva <br>a morta e viceversa</li><br>"
            + "<li>Click destro :<br>rende una cella sempre morta</li></ol>";
    /**
     * Stringa che contiene l'elenco degli oscillatori
     */
    private final String s2 = "<h2>Game of Life</h2><br><p>Oscillatori : </p>"
            + " <ol><li>Blinker</li><br>" + "<li>Toad</li><br>" + "<li>Beacon</li><br>"
            + "<li>Pulsar</li></ol>";
    /**
     * Stringa che contiene l'elenco delle navicelle
     */
    private final String s3 = "<h2>Game of Life</h2><br><p>Navicelle : </p>"
            + " <ol><li>Glider</li><br>" + "<li>Ligthweith Spaceship</li>"
            + "<br><li>Gosper Glider Gun</li></ol>";
    /**
     * Array che contiene le stringhe da visualizzare nell' help
     */
    private String[] arr = {s, s1, s2, s3};
    /**
     * Indice per scorrere l'array
     */
    private int index = 0;
	/**
	 * Crea ed inizializza il JTextPane
	 */
    public Help() {
        setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
        setContentType("text/html");
        setText(s);
        setPreferredSize(new Dimension(270, 350));
        setMaximumSize(new Dimension(270, 350));
        setMinimumSize(new Dimension(270, 350));
        setEditable(false);

        t = new Timer();
        t.schedule(new Task(), 0, 10000);

    }
    /**
     * Questa classe crea un Timer e cambia periodicamente le stringhe nell' help 
     * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
     */
    private class Task extends TimerTask {
    	/**
    	 * Cambia periodicamente le stringhe nell' help 
    	 */
        @Override
        public void run() {
            Help.this.setText(arr[index]);
            index = ++index % 4;
        }
    }
}

