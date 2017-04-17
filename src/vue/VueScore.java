package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import modele.Joueur;
import modele.Partie;

public class VueScore extends JPanel implements Observer{

	private Partie partie;
	
	public VueScore(Partie partie){
		
		this.partie=partie;
		
		ArrayList<Joueur> joueurs = partie.getJoueurs();
		Iterator<Joueur> it = joueurs.iterator();
		while(it.hasNext()){
			it.next().addObserver(this);
		}


		this.setBorder(new TitledBorder("Scores"));
		

		this.setLayout(new GridLayout(3,2));

		this.setPreferredSize(new Dimension(200,this.getHeight()));

		int i;
		for(i=0;i<=partie.getJoueurs().size()-1;i++){
			this.add(new JLabel(partie.getJoueurs().get(i).getNom()+" : "+partie.getJoueurs().get(i).getPuissancePriere()));
		}

	}

	

	@Override
	public void update(Observable joueur, Object arg) {
		// TODO Auto-generated method stub
		if(arg==(Object)((Joueur)joueur).getPuissancePriere()){
			this.removeAll();
			int i;
			for(i=0;i<=partie.getJoueurs().size()-1;i++){
				this.add(new JLabel(partie.getJoueurs().get(i).getNom()+" : "+partie.getJoueurs().get(i).getPuissancePriere()));
			}
			this.revalidate();
		}
	}


}
