package it.univr.GameOfLife;

import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Questa classe costruisce uno slider per gestire la velocità di avanzamento delle generazioni
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class Slider extends JPanel {

	/**
	 * Gestisce la velocità
	 */
    private final JSlider slider;

    /**
     * Inizialliza lo slider impostando min,max e init
     * @param min - valore minimo dello slider
     * @param max - valore massimo dello slider
     * @param init - valore iniziale dello slider
     */
    public Slider(int min, int max, int init) {

        slider = new JSlider(min, max, init);
        slider.setToolTipText("Controlla la velocità del passaggio di generzione delle celle");
        this.add(slider);
        setVisible(true);
    }

    /**
     * Restituisce il valore dello slider
     * @return slider.getValue() - Valore dello slider
     */
    public int getValue() {
        return slider.getValue();
    }
}