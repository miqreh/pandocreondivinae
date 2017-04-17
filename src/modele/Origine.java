package modele;

public enum Origine {
	JOUR("Jour"),
	NUIT("Nuit"),
	CREPUSCULE("Crepuscule"),
	AUBE("Aube"),
	NEANT("Neant"),
	NULL("Aucune");

	private String nom="";

	Origine(String nom){
		this.nom=nom;
	}

	public String toString(){
		return nom;
	}
}