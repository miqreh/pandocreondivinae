package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.Carte;
import modele.Joueur;
import modele.Partie;

public class VueMains extends JPanel implements Observer{

	private Partie partie;

	public VueMains(Joueur joueur, Partie partie){

		this.partie=partie;

		joueur.addObserver(this);

		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1250,250));


		int i;
		for (i=0;i<=joueur.getMain().size()-1;i++){
			this.add(new VueCarte(joueur.getMain().get(i),partie,joueur));
		}

	}

	@Override
	public void update(Observable joueur, Object main) {



		if(main==((Joueur) joueur).getMain()){
			this.removeAll();
			if((((Joueur)joueur).getMain().size()==0)){
				this.add(new JLabel("Aucune cartes."));
			} else {
				int i;
				for (i=0;i<=((Joueur)joueur).getMain().size()-1;i++){
					this.add(new VueCarte(((Joueur)joueur).getMain().get(i),(Partie) partie, (Joueur) joueur));
				}
			}
			this.repaint();
			this.revalidate();

		}

	}
}





