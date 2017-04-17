package modele;
/**
 * Classe représentant les cartes apocalypses.
 * 
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * @see Carte
 *
 */
public class Apocalypses extends Carte {

	public Origine origine;
	public  String nom ;
	public static String description =" ";
	public int id;

	public Apocalypses(Origine origine,String nom, int id) {
		super(origine,nom,description,id);
		this.origine=origine;
		this.nom=nom;
		this.id=id;

	}


	public String toString(){
		StringBuffer sb = new StringBuffer();
		String s = super.toString();
		sb.append(s);
		sb.append("Origine: "+this.origine+"\n");
		return sb.toString();	}

}
