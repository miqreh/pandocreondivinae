package vue;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.ControleurChoix;
import modele.Partie;

public class VueChoix extends JPanel {
	

	JButton jouer = new JButton("Jouer une carte");
	JButton sacrifier= new JButton("Sacrifier une carte");
	JButton espaces = new JButton("Afficher l'espace d'un joueur");
	JButton finir = new JButton("Finir votre tour");	
	JButton defausser = new JButton("Défausser");
	JButton completer = new JButton("Compléter");

	private Partie partie;

	public VueChoix(Partie partie){

		this.partie=partie;

		LayoutManager layoutMenu = new GridLayout(4,2);
		this.setLayout(layoutMenu);
		this.add(jouer);
		this.add(sacrifier);
		this.add(espaces);
		this.add(defausser);
		this.add(completer);
		this.add(finir);	
		
		ControleurChoix contchoix=new ControleurChoix(partie, this);
		
	}

	public void addJListener(ActionListener al){
		jouer.addActionListener(al);
	}

	public void addSListener(ActionListener al){
		sacrifier.addActionListener(al);
	}

	public void addEListener(ActionListener al){
		espaces.addActionListener(al);
	}


	public void addFListener(ActionListener al){
		finir.addActionListener(al);
	}
	
	public void addDListener(ActionListener al){
		defausser.addActionListener(al);
	}
	
	public void addCListener(ActionListener al){
		completer.addActionListener(al);
	}

}
