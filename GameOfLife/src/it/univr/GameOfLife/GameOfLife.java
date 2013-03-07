package it.univr.GameOfLife;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * The Game Of Life.
 * Questa classe inizializza i componenti grafici e lancia il gioco.
 * @author Alessandro Menon, Riccardo Zucchelli, Ranasinghe Uresh
 */
public class GameOfLife extends JFrame {
	/**
	 * Componente grafico che gestisce la velocità di generazione delle celle
	 */
    private Slider slider;
	/**
	 * Etichetta dello JSpinner
	 */
    private JLabel spinnerText;
	/**
	 * Componente grafico che gestisce il numero dei thread
	 */
    private SpinnerThread spinner;
	/**
	 * Pannello ovest che contiene messaggi e help
	 */
    private JPanel west;
	/**
	 * Pannello sud che contine i bottoni per giocare, lo slider e lo spinner 
	 */
    private JPanel south;
	/**
	 * Bottone per iniziare il gioco
	 */
    private JButton start;
	/**
	 * Bottone per bloccare e riprendere il gioco
	 */
    private JButton stop;
	/**
	 * Bottone per iniziare un nuovo gioco
	 */
    private JButton clear;
	/**
	 * Componente che contiene messaggi di errore del gioco
	 */
    private Messaggi messaggi;
	/**
	 * Barra di scroll per i messaggi
	 */
    private JScrollPane scroll;
	/**
	 * Classe che che si occupa della crezione e gestione della matrice di celle
	 */
    private Matrix matrix;
	/**
	 * Classe che gestisce i Thread
	 */
    private Gestore gestore;
    /**
     * Componente che contiene help del gioco
     */
    private Help help;
   
    /**
     * Crea ed inizializza la grafica
     */
    public GameOfLife(String title) {
        initGUI(title);
    }
    /**
     * Crea ed inizializza il JFrame
     * @param title - il titolo del JFrame
     */
    private final void initGUI(String title) {
        this.setTitle(title);
        matrix = new Matrix(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 0, 900, 650);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        initPanelWest();
        initPanelSouth();
        this.getContentPane().add("West", west);
        this.getContentPane().add("Center", matrix);
        this.getContentPane().add("South", south);
        this.setVisible(true);
    }
    /**
     * Crea ed inizializza il JPanel west
     */
    private void initPanelWest() {
        messaggi = new Messaggi();
        scroll = new JScrollPane(messaggi);
        help = new Help();
        west = new JPanel();
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        west.add(help);
        west.add(scroll);
    }
    /**
     * Crea ed inizializza il JPanel south
     */
    private void initPanelSouth() {
        south = new JPanel();
        south.setLayout(new FlowLayout(FlowLayout.LEFT));
        start = new JButton("Start");

        start.addActionListener(new ActionListener() {
            /**
             * Gestisce gli eventi dovuti alla pressione del JButton start
             */
            @Override
            public void actionPerformed(ActionEvent arg0) {

                clear.setEnabled(false);
                start.setEnabled(false);
                stop.setEnabled(true);
                spinner.setEnabled(false);
                gestore = new Gestore(matrix, slider, spinner.getValue());
                gestore.start();
            }
        });
        stop = new JButton("Stop");
        stop.setEnabled(false);
        stop.addActionListener(new ActionListener() {
            /**
             * Gestisce gli eventi dovuti alla pressione del JButton stop
             */
            @Override
            public void actionPerformed(ActionEvent arg0) {

                clear.setEnabled(true);
                start.setEnabled(true);
                stop.setEnabled(false);
                spinner.setEnabled(true);
                gestore.stopGestore();


            }
        });

        clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            /**
             * Gestisce gli eventi dovuti alla pressione del JButton clear
             */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                start.setEnabled(true);
                stop.setEnabled(false);
                matrix.clearMatrix();
                messaggi.setText("<h2>Error Messages</h2>");
            }
        });

        slider = new Slider(1, 500, 250);
        spinnerText = new JLabel("Number Of Thread: ");
        spinner = new SpinnerThread(matrix.getDIM());
        south.add(start);
        south.add(stop);
        south.add(clear);
        south.add(new PatternsSelect(matrix));
        south.add(slider);
        south.add(spinnerText);
        south.add(spinner);

    }
    /**
     * Ritorna il JTextPane dei messaggi
     * @return messaggi - JTextPane dei messaggi
     */
    public Messaggi getMessaggi() {
        return messaggi;
    }
    /**
     * Main che fa partire il gioco
     * @param args argomenti da linea di comando (non usati)
     */
    public static void main(String[] args) {
        new GameOfLife("Game of Life");
    }
}
