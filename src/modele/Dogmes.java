package modele;

public enum Dogmes {
	HUMAIN("Humain"),
	NATURE("Nature"),
	CHAOS("Chaos"),
	SYMBOLES("Symboles"),
	MYSTIQUE("Mystique");

	private String nom="";

	Dogmes(String nom){
		this.nom=nom;
	}

	public String toString(){
		return this.nom;
	}


}
