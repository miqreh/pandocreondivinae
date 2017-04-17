package modele;

import vue.Lieu;
/**
 * Classes représentant la base commune à toutes les cartes de Pandocréon Divinae.
 *@author Thibaut Counot
 *@author Hassan Mroueh
 *
 */
public class Carte {

	protected Origine origine;
	protected String nom;
	protected String description;
	protected int id;
	private Lieu lieu;



	public Carte(Origine origine, String nom,String description,int id){
		this.origine=origine;
		this.nom=nom;
		this.description=description;
		this.id=id;
		
		this.lieu=null;

	}

	public String getNom() {
		return nom;
	}

	public int getId(){
		return id;
	}

	public void jouerCapacite(){
		
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.nom);
		sb.append("\n");
		return sb.toString();
	}

	public Origine getOrigine() {
		return origine;
	}

	public void setOrigine(Origine origine) {
		this.origine = origine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(int id) {
		this.id = id;
	}


}
