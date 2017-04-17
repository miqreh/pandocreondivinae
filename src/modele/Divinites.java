package modele;
/**
 * Classe représentant les cartes Divinités.
 * 
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * @see Carte
 *
 */
public class Divinites extends Carte{

	private Dogmes dogme1;
	private Dogmes dogme2;
	private Dogmes dogme3;
	protected String description;
	/**
	 * Le boolean capUtilisee permet de savoir si une divinité peut jouer ou non sa capacité. Un joueur ne pouvant jouer cette capcité qu'une fois dans la partie, la capacité sera jouée seulement si capUTilisee est faux.
	 */
	protected boolean capUtilisee;

	public Divinites(Origine origine, String nom, Dogmes dogme1, Dogmes dogme2, Dogmes dogme3, String description, int id) {
		super(origine,nom,description,id);
		this.dogme1=dogme1;
		this.dogme2=dogme2;
		this.dogme3=dogme3;
		this.capUtilisee=false;
		
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		String s = super.toString();
		sb.append(s);
		sb.append("Origine: "+this.origine+"\n");
		sb.append("Dogmes: "+this.dogme1+", "+this.dogme2+", "+this.dogme3+"\n");
		return sb.toString();
	}
	
	public boolean isCapUtilisee() {
		return capUtilisee;
	}

	public Origine getOrigine() {
		return origine;
	}

	public String getNom() {
		return nom;
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

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
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

	
	
}
