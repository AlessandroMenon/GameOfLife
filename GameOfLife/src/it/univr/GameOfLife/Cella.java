package it.univr.GameOfLife;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
/**
 * Questa classe rappresenta una singola cella.
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class Cella extends JLabel {
	/**
	 * Rappresenta la posizione dell'ascissa della cella
	 */
    private final int posX;
	/**
	 * Rappresenta la posizione dell'ordinata della cella
	 */
    private final int posY;
	/**
	 * Numero di vicini vivi alla cella
	 */
    private int nViciniVivi;
	/**
	 * Lista contenente tutti i vicini della cella
	 */
    private final LinkedList<Cella> vicini;
	/**
	 * Rappresenta lo stato della cella.
	 * <ol><li>Cella morta = 1 </li><li> Cella viva = 2 </li><li> Cella sempre morta = 3</li></ol>
	 */
    private int statoCella;     // 1 = cella morta  2 = viva  3 = sempre morta
    /**
     * Crea ed inizializza la cella
     */
    public Cella(int posX, int posY, LinkedList<Cella> vicini) {

        this.posX = posX;
        this.posY = posY;
        this.statoCella = 1;     //All'inizio lo statoCella della cella è morta
        this.vicini = vicini;
        this.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        this.setOpaque(true);
        this.setBackground(Color.BLACK);

    }
    /**
     * Ritorna il numero dei vicini vivi
     * @return nViciniVivi - numero dei vicini vivi
     */
    public int getnViciniVivi() {
        return nViciniVivi;
    }
    /**
     * Ritorna la coordinata X della cella
     * @return posX - la coordinata X della cella
     */
    public int getPosX() {
        return posX;
    }
    /**
     * Ritorna la coordinata Y della cella
     * @return posY - la coordinata Y della cella
     */
    public int getPosY() {
        return posY;
    }
    /**
     * Ritorna lo stato in cui si trova la cella
     * @return statoCella - lo stato della cella
     */
    public int getStatoCella() {
        return statoCella;
    }
    /**
     * Ritorna la lista dei vicini della cella
     * @return vicini - Lista vicini
     */
    public LinkedList<Cella> getVicini() {
        return vicini;
    }
    /**
     * Calcola il numero dei vicini vivi in base alla lista dei vicini
     */
    public void setnViciniVivi() {
        nViciniVivi = 0;
        for (Cella c : vicini)
            if (c.isLive())
                nViciniVivi++;
    }
    /**
     * Controlla se una cella é viva
     * @return True or False 
     */
    public boolean isLive() {
        return statoCella == 2;
    }
    /**
     * Controlla se una cella é morta
     * @return True or False 
     */
    public boolean isDead() {
        return statoCella == 1;
    }
    /**
     * Controlla se una cella é sempre morta
     * @return True or False 
     */
    public boolean isAlwaysDead() {
        return statoCella == 3;
    }
    /**
     * Setta lo stato della cella e il colore
     * @param statoCella - stato della cella
     */
    public void setStatoCella(int statoCella) {

        if (statoCella == 1) {
            this.setBackground(Color.BLACK);
            this.statoCella = statoCella;
        }
        if (statoCella == 2) {
            if (!(isAlwaysDead())) {
                this.setBackground(Color.GREEN);
                this.statoCella = statoCella;
            }
        }
        if (statoCella == 3) {
            this.setBackground(Color.WHITE);
            this.statoCella = statoCella;
        }
    }
    /**
     * Controlla se due celle sono uguali
     * @param obj Un'altra cella
     * @return True or False
     */
    @Override
    public boolean equals(Object obj) {
        Cella c;
        if (obj instanceof Cella) {
            c = (Cella) obj;
            if (c.getPosX() == posX && c.getPosY() == posY) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    /**
     * Calcola l'hashCode della cella
     * @return hash - hashCode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.posX;
        hash = 13 * hash + this.posY;
        return hash;
    }
    /**
     * toString della cella
     * @return out - Stringa che descrive la cella
     */
    @Override
    public String toString() {
        String out = "";
        int i = 0;
        switch (this.statoCella) {
            case 1:
                out += "Cella Morta \n";
                break;
            case 2:
                out += "Cella Viva \n";
                break;
            case 3:
                out += "Cella Sempre Morta \n";
                break;
        }
        
        return (out += "- Posizione : " + " x = " + this.posX + " y = " + this.posY);
    }
}