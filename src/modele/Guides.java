package modele;
/**
 * Classe représentant les carte Guides spirituels.
 *@author Thibaut Counot
 *@author Hassan Mroueh
 *@see Carte
 *
 */
public class Guides extends Carte {

	
	private Dogmes dogme1;
	private Dogmes dogme2;
	private int nbGuidésMax;
	/**
	 * Le nombre de croyants guidés.
	 */
	private int nbGuidés;
	public String description;

	public Guides(Origine origine, String nom, Dogmes dogme1, Dogmes dogme2,  String description, int nbGuidésMax, int id) {
		super(origine,nom,description,id);
		this.dogme1=dogme1;
		this.dogme2=dogme2;
		this.nbGuidésMax=nbGuidésMax;
		this.nbGuidés=0;

	}


	public Dogmes getDogme1() {
		return dogme1;
	}



	public void setDogme1(Dogmes dogme1) {
		this.dogme1 = dogme1;
	}



	public Dogmes getDogme2() {
		return dogme2;
	}



	public void setDogme2(Dogmes dogme2) {
		this.dogme2 = dogme2;
	}



	public int getNbGuidésMax() {
		return nbGuidésMax;
	}



	public void setNbGuidésMax(int nbGuidésMax) {
		this.nbGuidésMax = nbGuidésMax;
	}



	public int getNbGuidés() {
		return nbGuidés;
	}



	public void setNbGuidés(int nbGuidés) {
		this.nbGuidés = nbGuidés;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Cette methode vérifie si un guide a au moins un dogme en commun avec un croyant(afin de le guider).
	 * On ajoute les dogmes du guide et du croyant dans deux tableaux et on itère sur les tableaux pour trouver un dogme en commun.
	 * @param croyant 
	 * @return boolean true si il y a au moins un dogme en commun, false sinon.
	 */
	public boolean dogmesEnCommun(Croyants croyant){
		
		boolean retour=false;
		
		Dogmes[] tabDogmes = new Dogmes[2];
		tabDogmes[0]=this.getDogme1();
		tabDogmes[1]=this.getDogme2();
		
		Dogmes[] dogmesCroyants = new Dogmes [3];
		dogmesCroyants[0]=croyant.getDogme1();
		dogmesCroyants[1]=croyant.getDogme2();
		dogmesCroyants[2]=croyant.getDogme3();
			
		int i;
		for(i=0;i<=1;i++){
			int j;
			for(j=0;j<=2;j++){
				if (tabDogmes[i]==dogmesCroyants[j]){
					retour=true;
				}
			}
		}
		
		return retour;
		
	}
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		String s = super.toString();
		sb.append(s);
		sb.append("Guidés max: "+this.nbGuidésMax+"\n");
		sb.append("Origine: "+this.origine+"\n");
		sb.append("Dogmes: "+this.dogme1+", "+this.dogme2+"\n");
		return sb.toString();
	}


}
