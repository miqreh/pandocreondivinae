package modele;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;

/**
 * 
 * Classe abstraite servant de bases pour les joueurs réels et virtuels.
 * 
 * @author Thibaut Counot
 * @author Hassan Mroueh
 *
 *@see JoueurReel
 *@see JoueurVirtuel
 *
 */


public abstract class Joueur extends Observable{

	protected static int numero;
	protected int puissancePriere;
	protected int nbCroyantsJoueur;
	protected int nbGuidesJoueurs;
	protected int pointsJour;
	protected int pointsNuit;
	protected int pointsNeant;
	protected LinkedList<Carte> main;
	protected Divinites diviniteJoueur;
	protected String nom;
	protected boolean joue;
	protected Partie partie;
	protected LinkedList<Carte> espaceJoueur;
	protected boolean pointBloques;
	protected boolean sacrificeCroyantsBloque;
	protected boolean sacrificeGuidesBloque;
	protected boolean aJoue;
	
	




	public Joueur(int i, String nom, Partie partie){
		this.numero=i+1;
		this.partie=partie;
		this.nom = nom;
		this.diviniteJoueur=null;
		this.nbCroyantsJoueur=0;
		this.nbGuidesJoueurs=0;
		this.pointsJour=0;
		this.pointsNeant=0;
		this.pointsNuit=0;
		this.puissancePriere=0;
		this.joue=false;
		this.main = new LinkedList<Carte>();
		this.espaceJoueur=new LinkedList<Carte>();
		this.sacrificeCroyantsBloque=false;
		this.sacrificeGuidesBloque=false;
		this.aJoue=false;
		this.pointBloques=false;

	}


	public void afficherMain()
	{

		System.out.println("main de "+this.nom);
		Iterator <Carte> it= main.iterator();
		int i = 1;
		while (it.hasNext())
		{
			System.out.println(i+". "+it.next());
			i++;
		}
	}
	public void afficherPoints(){
		System.out.println("Points Jour: "+this.getPointsJour());
		System.out.println("Points Nuit: "+this.getPointsNuit());
		System.out.println("Points Néant: "+this.getPointsNeant());
	}

	public void afficherNbPrieres(){
		System.out.println("Nombre de prières: "+this.getPuissancePriere());
	}
	
	/**
	 * Méthode pour ajouter les points en début de tour.
	 * @param cosmogonie le résultat du dé : jour, nuit ou néant.
	 */
	public void ajouterPoints(Origine cosmogonie){
		Origine orJoueur = this.diviniteJoueur.getOrigine();
		switch(cosmogonie)
		{
		case JOUR:
		{ 
			if (orJoueur==Origine.JOUR){
				this.setPointsJour(pointsJour+2);
			} else if (orJoueur==Origine.AUBE) {
				this.setPointsJour(pointsJour+1);
			} break;
		} 
		case NUIT:
		{
			if (orJoueur==Origine.NUIT){
				this.setPointsNuit(pointsNuit+2);
			} else if (orJoueur==Origine.CREPUSCULE) {
				this.setPointsNuit(pointsNuit+1);
			} break;
		}
		case NEANT:
		{
			if (orJoueur==Origine.CREPUSCULE){
				this.setPointsNuit(pointsNuit+1);
			} else if (orJoueur==Origine.AUBE) {
				this.setPointsJour(pointsJour+1);
			} break;
		}
		}
	}

	public void prendreCarte(Carte carte)
	{
		main.add(carte);
	}
	/**
	 * Permet de recompléter la main du joueur.
	 * @param pioche la pioche de la Partie.
	 */
	public void piocherCarte(LinkedList<Carte> pioche){
		while(this.main.size()<7){
			this.prendreCarte(pioche.pop());
		}
		this.setChanged();
		this.notifyObservers(this.getMain());
	}

	public abstract void choisir(Scanner sc);
	
	public abstract void jouerCroyant(Croyants croyant);
	
	public abstract void jouerGuide(Guides carte);
	
	public abstract void guiderCroyant(Guides carte,Croyants c);

	public abstract void defausserCarte(Scanner sc);

	public abstract int finirTour(Scanner sc);

	public abstract void sacrifierCarte(Scanner sc);

	public abstract void jouerCarte(Scanner sc);

	public void jouerCapDivinite(){ 
		Scanner sca = new Scanner(System.in);
		this.partie.capacites.jouerCapacite(this, this.getDiviniteJoueur(), this.getDiviniteJoueur().getId(), sca);
	}
	
	
	
	
	public void poserCarte(Croyants carte){
		System.out.println("Vous posez la carte "+carte.nom+" au centre de la table");

		
		this.partie.ajouterMilieu(carte);
		this.main.remove(carte);
		switch(carte.origine)
		{
		case JOUR:
		{
			this.setPointsJour(this.getPointsJour()-1);
			break;
		}
		case NUIT:
		{
			this.setPointsNuit(this.getPointsNuit()-1);
			break;
		}
		case NEANT:
		{
			if(this.getPointsJour()>2||this.getPointsNuit()>2){
				
			} else {
				this.setPointsNeant(this.getPointsNeant()-1);
			}
		}
		}
		this.setChanged();
	}

	public void afficherEspaceJoueur(){
		System.out.println("Nombre de prières de "+this.nom+" : "+this.getPuissancePriere());
		System.out.println("Espace joueur de "+this.nom+" :");
		if (this.espaceJoueur.size()==0){
			System.out.println("Aucune carte\n");
		}else{
			int i = 1;
			Iterator<Carte> it = this.espaceJoueur.iterator();
			while(it.hasNext()){
				System.out.println(i+". "+it.next());
				i++;
			}
		}
	}
	/**
	 * Certaines capacités visent des Divinités croyant à des dogmes précis. Cette méthode permet de vérifier si la divinité du joueur croit à un dogme.
	 * @param dogme le dogme à vérifier
	 * @return boolean : true si la divinité croit au dogme, false sinon.
	 */
	public boolean diviniteCroitDogme(Dogmes dogme){
		boolean croit=false;
		if(this.diviniteJoueur.getDogme1()==dogme||this.diviniteJoueur.getDogme2()==dogme||this.diviniteJoueur.getDogme3()==dogme){
			croit=true;
		} else {
			croit=false;
		}
		return croit;
	}
	
	public void retirerMain(Carte carte){
		partie.getPioche().add(carte);
		this.main.remove(carte);
		this.setChanged();
	}
	
	public void sacrifierCarte(Carte carte){
		
		this.espaceJoueur.remove(carte);
		this.setChanged();
		this.notifyObservers(this.espaceJoueur);
		carte.jouerCapacite();
	}

	public String getNom(){
		return nom;
	}

	public int getNumero() {
		return numero;
	}

	public int getPointsJour() {
		return pointsJour;
	}

	public int getPointsNuit() {
		return pointsNuit;
	}

	public int getPointsNeant() {
		return pointsNeant;
	}

	public LinkedList<Carte> getMain() {
		return main;
	}

	public void setDiviniteJoueur(Divinites diviniteJoueur) {
		this.diviniteJoueur = diviniteJoueur;
	}

	public Divinites getDiviniteJoueur() {
		return diviniteJoueur;
	}

	public boolean isJoue() {
		return joue;
	}

	public void setJoue(boolean joue) {
		this.joue = joue;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPointsJour(int pointsJour) {
		if (this.pointBloques==true){
			System.out.println("Les points sont bloqués pour ce tour");
		} else {

			this.pointsJour = pointsJour;
		}
	}

	public void setPointsNuit(int pointsNuit) {
		if (this.pointBloques==true){
			System.out.println("Les points sont bloqués pour ce tour");
		} else {


			this.pointsNuit = pointsNuit;
		}
	}

	public void setPointsNeant(int pointsNeant) {
		if (this.pointBloques==true){
			System.out.println("Les points sont bloqués pour ce tour");
		} else {

			this.pointsNeant = pointsNeant;
		}
	}


	public int getPuissancePriere() {
		return puissancePriere;
	}


	public void setPuissancePriere(int puissancePriere) {
		this.puissancePriere = puissancePriere;
	}


	public boolean isSacrificeCroyantsBloque() {
		return sacrificeCroyantsBloque;
	}


	public void setSacrificeCroyantsBloque(boolean sacrificeCroyantsBloque) {
		this.sacrificeCroyantsBloque = sacrificeCroyantsBloque;
	}


	public boolean isSacrificeGuidesBloque() {
		return sacrificeGuidesBloque;
	}


	public void setSacrificeGuidesBloque(boolean sacrificeGuidesBloque) {
		this.sacrificeGuidesBloque = sacrificeGuidesBloque;
	}


	public boolean isaJoue() {
		return aJoue;
	}


	public void setaJoue(boolean aJoue) {
		this.aJoue = aJoue;
	}


	public boolean isPointBloques() {
		return pointBloques;
	}


	public void setPointBloques(boolean pointBloques) {
		this.pointBloques = pointBloques;
	}


	public void setMain(LinkedList<Carte> main) {
		this.main = main;
	}


	public LinkedList<Carte> getEspaceJoueur() {
		return espaceJoueur;
	}


	public void setEspaceJoueur(LinkedList<Carte> espaceJoueur) {
		this.espaceJoueur = espaceJoueur;
	}


	public int getNbCroyantsJoueur() {
		return nbCroyantsJoueur;
	}


	public void setNbCroyantsJoueur(int nbCroyantsJoueur) {
		this.nbCroyantsJoueur = nbCroyantsJoueur;
	}


	public int getNbGuidesJoueurs() {
		return nbGuidesJoueurs;
	}


	public void setNbGuidesJoueurs(int nbGuidesJoueurs) {
		this.nbGuidesJoueurs = nbGuidesJoueurs;
	}


	public Partie getPartie() {
		return partie;
	}


	public void setPartie(Partie partie) {
		this.partie = partie;
	}


	public static void setNumero(int numero) {
		Joueur.numero = numero;
	}



}
