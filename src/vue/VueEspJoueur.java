package vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modele.Carte;
import modele.Joueur;
import modele.Partie;


public class VueEspJoueur extends JPanel implements Observer{

	private LinkedList<VueCarte> listecartes;
	private JPanel cartepanel;
	private JScrollPane jsp;

	public VueEspJoueur(Joueur joueur){

		joueur.addObserver(this);

		this.listecartes=new LinkedList<VueCarte>();
		
		this.cartepanel=new JPanel();
		
		
		cartepanel.setLayout(new FlowLayout());
		if(joueur.getEspaceJoueur().size()==0){
			cartepanel.add(new JLabel("Aucune cartes."));
		} else {
			int i;
			for (i=0;i<=joueur.getEspaceJoueur().size()-1;i++){
				VueCarte vc = new VueCarte(joueur.getEspaceJoueur().get(i),joueur.getPartie(), joueur);
				vc.getCarte().setLieu(Lieu.ESPACE);
				cartepanel.add(vc);
				
			}
		}
		
		this.jsp = new JScrollPane(cartepanel);
		this.jsp.setPreferredSize(new Dimension(1200,250));
		this.jsp.setBorder(BorderFactory.createEmptyBorder());
		
		this.add(jsp);
		
		
		
	}

	@Override
	public void update(Observable joueur, Object espace) {
		// TODO Auto-generated method stub

		if(espace==((Joueur) joueur).getEspaceJoueur()){
			this.cartepanel.removeAll();
			if((((Joueur)joueur).getEspaceJoueur().size()==0)){
				cartepanel.add(new JLabel("Aucune cartes."));
			} else {
				int i;
				for (i=0;i<=((Joueur)joueur).getEspaceJoueur().size()-1;i++){
					VueCarte vc = new VueCarte(((Joueur) joueur).getEspaceJoueur().get(i),((Joueur) joueur).getPartie(), (Joueur) joueur);
					vc.getCarte().setLieu(Lieu.ESPACE);
					cartepanel.add(vc);
				}
			}
			this.repaint();
			this.revalidate();

		}
		
		
	}


}
