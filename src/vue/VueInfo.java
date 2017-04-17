package vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.ControleurCarte;
import modele.Joueur;

public class VueInfo extends JPanel{
	
	public VueInfo(Joueur joueur){

		JPanel divinite = new JPanel();
		JPanel points  = new JPanel();

		points.setLayout(new BoxLayout(points, BoxLayout.Y_AXIS));

		this.add(divinite);
		this.add(points);

		BufferedImage carte = null;
		String nom = joueur.getDiviniteJoueur().getNom();
		try {
			carte = ImageIO.read(new File("src/Cartes/"+nom+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel cLabel = new JLabel(new ImageIcon(carte));
		divinite.add(cLabel);

		points.add(new JLabel("Points Jour : "+joueur.getPointsJour()));
		points.add(new JLabel("Points Nuit : "+joueur.getPointsNuit()));
		points.add(new JLabel("Points Neant : "+joueur.getPointsNeant()));
		points.add(new JLabel("Nombre de prières : "+joueur.getPuissancePriere()));
	}


}
