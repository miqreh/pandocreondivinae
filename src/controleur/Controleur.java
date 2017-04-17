package controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import modele.CardConstructor;
import modele.Carte;
import modele.Joueur;
import modele.JoueurReel;
import modele.JoueurVirtuel;
import modele.Partie;
import vue.VueChoix;
import vue.VueJeu;
import vue.VueJeu2;
import vue.VueJoueur;
import vue.VueMains;
import vue.VueMilieuTable;
import vue.VueScore;
import vue.VueTexte;

public class Controleur{
	private Partie partie;
	private VueJeu vj;
	public Controleur(Partie partie, VueJeu vj){

		this.vj=vj;
		this.partie=partie;

		vj.addDemarrerListener(new demarrerListener());
	}


	class demarrerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub


			partie.setPartieEnCours(true);

			String nbJoueursReels = (String)vj.getNbJReels().getSelectedItem();
			int valueReel = Integer.parseInt(nbJoueursReels);

			String nbJoueursVirtuels = (String)vj.getNbJVirtuels().getSelectedItem();
			int valueVirtuel = Integer.parseInt(nbJoueursVirtuels);

			int i;
			for(i=0;i<valueReel;i++){
				String retour = JOptionPane.showInputDialog(vj, "Comment se nomme le joueur "+(i+1)+"?"); 
				partie.getJoueurs().add(new JoueurReel(partie,retour));	
			}

			int taille = partie.getJoueurs().size();

			for(i=taille;i<taille+valueVirtuel;i++){
				String retour = JOptionPane.showInputDialog(vj, "Comment se nomme le joueur "+(i+1)+"?"); 
				partie.getJoueurs().add(new JoueurVirtuel(partie,retour));	
			}

			vj.getJpM().removeAll();
			vj.getJpB().removeAll();
			vj.getJpH().removeAll();

			vj.getJpM().setLayout(new FlowLayout());
			vj.getJpH().setLayout(new BorderLayout());

			vj.dispose();

			partie.distribuerDivinites();

			partie.creerPioche();

			partie.distribuerCartes();

			partie.debuterPhase(0);


			VueJeu2 vj2=new VueJeu2(partie);


		}
	}

}



