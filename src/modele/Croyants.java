package modele;
/**
 * Classe repr�sentant les cartes croyants.
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * @see Carte
 *
 */
public class Croyants extends Carte {


	
	protected Dogmes dogme1;
	protected Dogmes dogme2;
	protected Dogmes dogme3;
	protected int nbCroyants;
	private boolean estGuid�;
	private Guides guide;
	private boolean justePos�;

	public Croyants(Origine origine, String nom, Dogmes dogme1, Dogmes dogme2, Dogmes dogme3, String description, int nbCroyants, int id) {
		super(origine,nom,description,id);
		this.dogme1=dogme1;
		this.dogme2=dogme2;
		this.dogme3=dogme3;
		this.nbCroyants=nbCroyants;
		this.estGuid�=false;
		this.guide=null;
		this.justePos�=false;

	}


	public String toString(){
		StringBuffer sb = new StringBuffer();
		String s = super.toString();
		sb.append(s);
		sb.append("Croyants: "+this.nbCroyants+"\n");
		sb.append("Origine: "+this.origine+"\n");
		sb.append("Dogmes: "+this.dogme1+", "+this.dogme2+", "+this.dogme3+"\n");
		if(this.estGuid�==true){
			sb.append("Guide: "+this.guide.getNom()+"\n");
		}
		return sb.toString();
		
	}


	public Dogmes getDogme1() {
		return dogme1;
	}


	public Dogmes getDogme2() {
		return dogme2;
	}


	public Dogmes getDogme3() {
		return dogme3;
	}


	public int getNbCroyants() {
		return nbCroyants;
	}


	public boolean isEstGuid�() {
		return estGuid�;
	}

	public Guides getGuide() {
		return guide;
	}


	public void setGuide(Guides guide) {
		this.guide = guide;
	}


	public void setEstGuid�(boolean estGuid�) {
		this.estGuid� = estGuid�;
	}


	public boolean isJustePos�() {
		return justePos�;
	}


	public void setJustePos�(boolean justePos�) {
		this.justePos� = justePos�;
	}


	public void setDogme1(Dogmes dogme1) {
		this.dogme1 = dogme1;
	}


	public void setDogme2(Dogmes dogme2) {
		this.dogme2 = dogme2;
	}


	public void setDogme3(Dogmes dogme3) {
		this.dogme3 = dogme3;
	}


	public void setNbCroyants(int nbCroyants) {
		this.nbCroyants = nbCroyants;
	}
	
	

}