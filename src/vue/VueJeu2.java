package vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modele.Partie;

public class VueJeu2 extends JFrame{

	private Partie partie;
	
	private JPanel jpH= new JPanel();
	private JPanel jpB = new JPanel();
	private JPanel jpM = new JPanel();
	private Container c = this.getContentPane();
	private VueMains vm1;
	private VueChoix vc1;
	private VueScore vs1;
	private VueMilieuTable vmt1;
	private VueJoueur vj1;
	private VueTexte vt1;
	
	
	
	public VueJeu2(Partie partie){
		
		this.partie=partie;
		this.setTitle("Pandocreon Divinae");
		
		c.setLayout(new BorderLayout());
		jpB.setLayout(new FlowLayout());
		jpH.setLayout(new BorderLayout());
		jpM.setLayout(new FlowLayout());

		c.add(jpH, BorderLayout.NORTH);
		c.add(jpB, BorderLayout.SOUTH);
		c.add(jpM, BorderLayout.CENTER);
	
		
		
		this.setVj1(new VueJoueur(partie.getJoueurCourant(),partie));
		
		this.getJpB().add(this.getVj1());

		this.setVc1(new VueChoix(partie));
		this.getJpH().add(this.getVc1(),BorderLayout.EAST);

		this.setVmt1(new VueMilieuTable(partie));
		
		this.jpM.add(this.getVmt1());

		this.setVs1(new VueScore(partie));
		this.getJpH().add(this.getVs1(),BorderLayout.WEST);

		this.setVt1(new VueTexte(partie));
		this.getJpH().add(this.getVt1(),BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}



	public Partie getPartie() {
		return partie;
	}



	public void setPartie(Partie partie) {
		this.partie = partie;
	}



	public JPanel getJpH() {
		return jpH;
	}



	public void setJpH(JPanel jpH) {
		this.jpH = jpH;
	}



	public JPanel getJpB() {
		return jpB;
	}



	public void setJpB(JPanel jpB) {
		this.jpB = jpB;
	}



	public JPanel getJpM() {
		return jpM;
	}



	public void setJpM(JPanel jpM) {
		this.jpM = jpM;
	}



	public Container getC() {
		return c;
	}



	public void setC(Container c) {
		this.c = c;
	}



	public VueMains getVm1() {
		return vm1;
	}



	public void setVm1(VueMains vm1) {
		this.vm1 = vm1;
	}



	public VueChoix getVc1() {
		return vc1;
	}



	public void setVc1(VueChoix vc1) {
		this.vc1 = vc1;
	}



	public VueScore getVs1() {
		return vs1;
	}



	public void setVs1(VueScore vs1) {
		this.vs1 = vs1;
	}



	public VueMilieuTable getVmt1() {
		return vmt1;
	}



	public void setVmt1(VueMilieuTable vmt1) {
		this.vmt1 = vmt1;
	}



	public VueJoueur getVj1() {
		return vj1;
	}



	public void setVj1(VueJoueur vj1) {
		this.vj1 = vj1;
	}



	public VueTexte getVt1() {
		return vt1;
	}



	public void setVt1(VueTexte vt1) {
		this.vt1 = vt1;
	}
	
	
	
}
