package it.univr.GameOfLife;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Questa classe crea un JSpinner per gestire il numero di thread utilizzati
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class SpinnerThread extends JPanel{
    /**
     * JSpinner per gestire il numero di thread utilizzati
     */
    private final JSpinner spinner;
	/**
	 * Crea ed inizializza il JSpinner
	 * @param DIM numero massimo di thread utilizzabili
	 */
    public SpinnerThread(int DIM){
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, DIM, 1); 
        spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(40,20));
        add(spinner);
        setVisible(true);
    }
    /**
     * Ritorna il valore attuale dello spinner
     * @return (Integer)spinner.getValue() - Il valore attuale dello spinner
     */
    public int getValue(){
        return (Integer)spinner.getValue();
    }
	/**
	 * Abilita o disabilita lo spinner
	 * @param b abilitato o disabilitato
	 */
    @Override
   public void setEnabled(boolean b){
        spinner.setEnabled(b);
    }
}