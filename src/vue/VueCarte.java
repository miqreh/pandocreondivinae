package vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.ControleurCarte;
import modele.Carte;
import modele.Croyants;
import modele.Joueur;
import modele.Partie;

public class VueCarte extends JButton{

	private String nom;
	private Carte carte;
	private Joueur joueur;
	private static Partie partie;
	private Lieu lieu;



	public VueCarte(Carte carte,Partie partie, Joueur joueur){
		VueCarte.partie=partie;
		this.carte=carte;
		this.joueur=joueur;
		this.nom=carte.getNom();



		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		BufferedImage icarte = null;
		try {
			icarte = ImageIO.read(new File("src/cartes/"+nom+".PNG"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel cLabel = new JLabel(new ImageIcon(icarte));
		this.add(cLabel);
		
		ControleurCarte contcarte = new ControleurCarte(this,partie);



	}

	


	public void addCarteListener(ActionListener al){
		this.addActionListener(al);
	}
	
	public void addGuiderListener(ActionListener al){
		this.addActionListener(al);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}




	public Joueur getJoueur() {
		return joueur;
	}




	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}




	public static Partie getPartie() {
		return partie;
	}




	public static void setPartie(Partie partie) {
		VueCarte.partie = partie;
	}




	public Lieu getLieu() {
		return lieu;
	}




	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}




	




}
//"C:/Users/thibaut/Documents/LO02/Cartes/"+nom+".PNG"