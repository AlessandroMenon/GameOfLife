package it.univr.GameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Questa classe costruisce un widget di selezione per scegliere un oscillatore o una navicella
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class PatternsSelect extends JPanel implements ActionListener {

	/**
	 * Istanza della classe Matrix che gestisce il mondo di gioco
	 */
	private final Matrix matrix;

	/**
	 * Crea il widget di selezione
	 * @param matrix istanza della classe Matrix
	 */
    public PatternsSelect(Matrix matrix) {
        this.matrix = matrix;
        String[] patterns = {"Default","Glider", "Lightweight spaceship", "Blinker",
                            "Toad", "Beacon", "Pulsar", "Gosper Glider Gun"};
        JComboBox patternsList = new JComboBox(patterns);
        patternsList.setSelectedIndex(0);
        patternsList.addActionListener(this);
        add(patternsList);
    }

    /**
     * Imposta la scelta dell'utente su matrix
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        matrix.setSceltaPatterns(cb.getSelectedIndex());
    }
    
    

}
