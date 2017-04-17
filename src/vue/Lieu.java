package vue;

public enum Lieu {
	MILIEU("au milieu de la table"),
	PIOCHE("dans la pioche"),
	MAIN("dans la main"),
	ESPACE("dans votre espace");
	
	private String nom="";
	
	Lieu(String nom){
		this.nom=nom;
	}
	
	public String toString(){
		return this.nom;
	}
}
