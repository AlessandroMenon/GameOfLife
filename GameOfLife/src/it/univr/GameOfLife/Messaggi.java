package it.univr.GameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JTextPane;

/**
 * Questa classe crea il JTextPane Messaggi
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class Messaggi extends JTextPane {
	/**
	 * Crea ed inizializza il JTextPane
	 */
    public Messaggi() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(270, 350));
        setMaximumSize(new Dimension(270, 350));
        setMinimumSize(new Dimension(270, 350));
        setContentType("text/html");
        setText("<h2>Error Messages</h2>");
        setEditable(false);
    }
}
