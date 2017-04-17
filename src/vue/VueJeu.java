package vue;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur;
import modele.CardConstructor;
import modele.Carte;
import modele.Joueur;
import modele.JoueurReel;
import modele.Partie;



public class VueJeu extends JFrame  {

	private Partie partie;
	private JButton b = new JButton("Jouer");
	private JPanel jpH= new JPanel();
	private JPanel jpB = new JPanel();
	private JPanel jpM = new JPanel();
	private Container c = this.getContentPane();
	private VueMains vm1;
	private VueChoix vc1;
	private VueScore vs1;
	private VueMilieuTable vmt1;
	private VueJoueur vj1;
	private VueTexte vt1;
	String[] nbJoueurs = {"0","1","2","3","4","5","6"};
	private JComboBox<String> nbJReels = new JComboBox<String>(nbJoueurs);
	private JComboBox<String> nbJVirtuels = new JComboBox<String>(nbJoueurs);



	public VueJeu(Partie partie){
		super("Pandocreon Divinae");
		this.setSize(500, 350);

		c.setLayout(new BorderLayout());
		jpB.setLayout(new FlowLayout());
		jpH.setLayout(new FlowLayout());
		jpM.setLayout(new FlowLayout());

		c.add(jpH, BorderLayout.NORTH);
		c.add(jpB, BorderLayout.SOUTH);
		c.add(jpM, BorderLayout.CENTER);

		jpH.add(new JLabel("Nombre de joueurs réels"));
		jpH.add(nbJReels);
		jpM.add(new JLabel("Nombre de joueurs virtuels"));
		jpM.add(nbJVirtuels);
		jpB.add(b);

		this.partie=partie;
		Controleur cont = new Controleur(partie,this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}


	public void addDemarrerListener(ActionListener al){
		b.addActionListener(al);
	}




	public Partie getPartie() {
		return partie;
	}


	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public JButton getB() {
		return b;
	}

	public void setB(JButton b) {
		this.b = b;
	}


	public JPanel getJpH() {
		return jpH;
	}


	public void setJpH(JPanel jpH) {
		this.jpH = jpH;
	}

	public JPanel getJpB() {
		return jpB;
	}

	public void setJpB(JPanel jpB) {
		this.jpB = jpB;
	}

	public JPanel getJpM() {
		return jpM;
	}

	public void setJpM(JPanel jpM) {
		this.jpM = jpM;
	}

	public Container getC() {
		return c;
	}

	public void setC(Container c) {
		this.c = c;
	}

	public VueMains getVm1() {
		return vm1;
	}

	public void setVm1(VueMains vm1) {
		this.vm1 = vm1;
	}


	public VueChoix getVc1() {
		return vc1;
	}


	public void setVc1(VueChoix vc1) {
		this.vc1 = vc1;
	}


	public VueScore getVs1() {
		return vs1;
	}


	public void setVs1(VueScore vs1) {
		this.vs1 = vs1;
	}


	public VueMilieuTable getVmt1() {
		return vmt1;
	}


	public void setVmt1(VueMilieuTable vmt1) {
		this.vmt1 = vmt1;
	}


	public JComboBox<String> getNbJReels() {
		return nbJReels;
	}


	public void setNbJReels(JComboBox<String> nbJReels) {
		this.nbJReels = nbJReels;
	}


	public JComboBox<String> getNbJVirtuels() {
		return nbJVirtuels;
	}


	public void setNbJVirtuels(JComboBox<String> nbJVirtuels) {
		this.nbJVirtuels = nbJVirtuels;
	}


	public VueJoueur getVj1() {
		return vj1;
	}


	public void setVj1(VueJoueur vj1) {
		this.vj1 = vj1;
	}


	public String[] getNbJoueurs() {
		return nbJoueurs;
	}


	public void setNbJoueurs(String[] nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}


	public VueTexte getVt1() {
		return vt1;
	}


	public void setVt1(VueTexte vt1) {
		this.vt1 = vt1;
	}

}
