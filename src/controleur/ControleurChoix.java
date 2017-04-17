package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Joueur;
import modele.Partie;
import vue.VueChoix;
import vue.VueEspJoueur;

public class ControleurChoix {

	private Partie partie;
	private VueChoix vch;


	public ControleurChoix(Partie partie, VueChoix vch){
		this.partie=partie;
		this.vch=vch;

		vch.addEListener(new EListener(partie));
		vch.addJListener(new JListener());
		vch.addFListener(new FListener(partie));
		vch.addSListener(new SListener());
		vch.addCListener(new CListener(partie.getJoueurs().get(0)));  //partie.getJoueurCourant()
		vch.addDListener(new DListener());
	}


	class JListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			partie.setJoue(true);

		}

	}

	class EListener implements ActionListener{

		private Partie partie;

		public EListener(Partie partie){
			this.partie=partie;
		}

		public void actionPerformed(ActionEvent e) {

			JFrame espacesframe = new JFrame();
			espacesframe.setSize(1600, 1200);
			JPanel pan = new JPanel();
			pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
			ArrayList<Joueur> listejoueurs = partie.getJoueurs();

			Iterator<Joueur> it = listejoueurs.iterator();
			while(it.hasNext()){
				pan.add(new VueEspJoueur(it.next()));
			}

			espacesframe.add(pan);
			espacesframe.setVisible(true);



		}

	}

	class CListener implements ActionListener{

		private Joueur joueur;

		public CListener(Joueur joueur){
			this.joueur=joueur;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			joueur.piocherCarte(partie.getPioche());



		}

	}

	class DListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			partie.setDefausse(true);
		}

	}

	class FListener implements ActionListener{

		private Partie partie;

		public FListener(Partie partie){
			this.partie=partie;

		}

		public void actionPerformed(ActionEvent e) {

		}

	}

	class SListener implements ActionListener{


		public void actionPerformed(ActionEvent e) {

			partie.setSacrifie(false);	

		}

	}
}
