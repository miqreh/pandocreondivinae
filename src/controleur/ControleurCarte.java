package controleur;

import vue.Lieu;
import vue.VueCarte;
import vue.VueJeu;
import vue.VueMilieuTable;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.Carte;
import modele.Croyants;
import modele.DeusEx;
import modele.Guides;
import modele.Joueur;
import modele.Partie;

public class ControleurCarte {

	private Carte carte;
	private VueCarte vc;
	private Partie partie;
	private Joueur joueur;


	public ControleurCarte(VueCarte vc, Partie partie){

		this.vc=vc;
		this.partie=partie;
		this.carte=vc.getCarte();
		this.joueur=vc.getJoueur();


		vc.addCarteListener(new CarteListener());
	}

	class CarteListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if((partie.isJoue()==true) && carte.getLieu()!=Lieu.ESPACE){
				if(carte instanceof Croyants){
					

					partie.setJoue(false);

					joueur.jouerCroyant((Croyants) carte);

					joueur.notifyObservers(joueur.getMain());
					partie.notifyObservers(partie.getMilieuTable());

				} else if (carte instanceof Guides){

					partie.setJoue(false);

					VueMilieuTable milieutable = new VueMilieuTable(partie);
					LinkedList<VueCarte> cartestable = milieutable.getListecartes();

					int i = 0;
					Iterator<VueCarte> it = cartestable.iterator();
					while(it.hasNext()){
						it.next().addGuiderListener(new GuiderListener(i,milieutable));
					}

					JFrame milieu = new JFrame();
					JPanel pan = new JPanel();
					pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
					pan.add(milieutable);
					JButton b = new JButton("Ok");
					pan.add(b);
					b.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							milieu.dispose();
						}

					});
					milieu.setTitle("Quel croyant voulez vous guider?");
					milieu.add(pan);
					milieu.pack();
					milieu.setVisible(true);

				} else if(carte instanceof DeusEx){
					
					carte.jouerCapacite();
				}
			} else if (partie.isDefausse()==true){
				partie.setDefausse(false);
				joueur.retirerMain(carte);
				joueur.notifyObservers(joueur.getMain());
			} else if (partie.isSacrifie()==true){
				joueur.sacrifierCarte(carte);
			}



		}


		class GuiderListener implements ActionListener{

			private int i;
			private VueMilieuTable vmt;

			public GuiderListener(int i, VueMilieuTable vmt){
				this.i = i;
				this.vmt=vmt;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if(((Guides) carte).dogmesEnCommun((Croyants) vmt.getListecartes().get(i).getCarte())==true){
					joueur.guiderCroyant((Guides) carte, (Croyants)vmt.getListecartes().get(i).getCarte());			
					partie.notifyObservers(partie.getMilieuTable());
				} else {
					Object[] options = {"OK"};
					int n = JOptionPane.showOptionDialog(vmt,
							"Vous n'avez pas de dogmes en commun avec ce croyant! ","Attention",
							JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE,
							null,
							options,
							options[0]);
				}




			}

		}

	}
}
