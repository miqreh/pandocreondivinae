package vue;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import modele.Carte;
import modele.Croyants;
import modele.Partie;

public class VueMilieuTable extends JPanel implements Observer{

	private Partie partie;
	private JPanel cartepanel;
	private JScrollPane jsp;
	private LinkedList<VueCarte> listecartes;

	public VueMilieuTable(Partie partie){

		this.partie=partie;
		this.listecartes=new LinkedList<VueCarte>();
		partie.addObserver(this);

		this.cartepanel=new JPanel();
		this.cartepanel.setLayout(new FlowLayout());
		this.cartepanel.setBorder(BorderFactory.createEmptyBorder());


		this.setBorder(new TitledBorder("Milieu de la table"));

		this.setLayout(new FlowLayout());

		if(partie.getMilieuTable().size()==0){
			cartepanel.add(new JLabel("Aucune cartes."));
		} else {
			int i;
			for (i=0;i<=partie.getMilieuTable().size()-1;i++){
				VueCarte carte = new VueCarte(partie.getMilieuTable().get(i),partie,null);
				cartepanel.add(carte);
				listecartes.add(carte);
			}
		}


		this.jsp = new JScrollPane(cartepanel);
		this.jsp.setPreferredSize(new Dimension(1200,300));
		this.jsp.setBorder(BorderFactory.createEmptyBorder());

		this.add(jsp);

	}

	@Override
	public void update(Observable partie, Object table) {
		// TODO Auto-generated method stub
		this.cartepanel.removeAll();
		if(table==((Partie) partie).getMilieuTable()){
			if(((Partie) partie).getMilieuTable().size()==0){
				cartepanel.add(new JLabel("Aucune cartes."));
			} else {
				int i;
				for (i=0;i<=((Partie) partie).getMilieuTable().size()-1;i++){
					cartepanel.add(new VueCarte(((Partie) partie).getMilieuTable().get(i),(Partie) partie, null));
				}
			}


		}
		
		this.repaint();
		this.revalidate();
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public JPanel getCartepanel() {
		return cartepanel;
	}

	public void setCartepanel(JPanel cartepanel) {
		this.cartepanel = cartepanel;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}

	public LinkedList<VueCarte> getListecartes() {
		return listecartes;
	}

	public void setListecartes(LinkedList<VueCarte> listecartes) {
		this.listecartes = listecartes;
	}

}	