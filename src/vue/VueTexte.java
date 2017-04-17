package vue;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Partie;

public class VueTexte extends JPanel{

	private Partie partie;
	private JScrollPane jsp;

	public VueTexte(Partie partie){

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.partie=partie;



		

		jsp = new JScrollPane();
		this.add(jsp);


	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}


}
