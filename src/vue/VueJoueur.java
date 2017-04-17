package vue;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modele.Joueur;
import modele.Partie;

public class VueJoueur extends JPanel{
	
	private Joueur joueur;	
	private Partie partie;


	public VueJoueur(Joueur joueur, Partie partie){

		this.partie=partie;;

		this.joueur=joueur;
		VueMains vmj = new VueMains(joueur,partie);
		VueEspJoueur vej = new VueEspJoueur(joueur);
		VueInfo vij = new VueInfo(joueur);

		JTabbedPane onglets = new JTabbedPane();
		onglets.addTab("main", vmj);
		onglets.addTab("espace",vej);
		onglets.addTab("infos", vij);

		onglets.setOpaque(false);

		this.add(onglets);


	}

}
