package modele;
/**
 * Classe représentant les cartes Deux Ex.
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * @see Carte
 *
 */
public class DeusEx extends Carte {

	
	public DeusEx(Origine origine, String nom, String description, int id) {
		super(origine,nom,description,id);
		this.origine=origine;
		this.nom=nom;
		this.description=description;
		this.id=id;
	}



	public String toString(){
		StringBuffer sb = new StringBuffer();
		String s = super.toString();
		sb.append(s);
		sb.append("Carte Deus Ex\n");
		sb.append("Origine: "+this.origine+"\n");
		return sb.toString();
	}

}
