package modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Scanner;

import vue.VueJeu;


/**
 * Le coeur du jeu, cette classe représente une partie de Pandocréon Divinae.
 *	Elle respecte le patron de conception Singleton, qui assure que plusieurs instances de Partie ne peuvent exister à la fois.
 * @author Thibaut Counot
 * @author Hassan Mroueh
 *
 */


public class Partie extends Observable{
	private static int nbJoueurs;
	private Joueur joueurCourant;
	private int nbPhases;
	private boolean partieEnCours;
	protected ArrayList<Joueur> joueurs;
	private LinkedList<Carte> pioche;
	private CardConstructor cardconstructor;
	private LinkedList<Origine> de;
	protected static LinkedList<Croyants> milieuTable=new LinkedList<Croyants>();
	protected Capacites capacites;
	private int i;//compteur
	private int j;//compteur
	
	/**
	 * Booleen qui sera vérifié par le ControleurCarte pour décider du comportement d'une carte
	 * @see ControleurCarte
	 */
	private boolean joue;
	
	/**
	 * Booleen qui sera vérifié par le ControleurCarte pour décider du comportement d'une carte
	 * @see ControleurCarte
	 */
	private boolean sacrifie;
	
	/**
	 * Booleen qui sera vérifié par le ControleurCarte pour décider du comportement d'une carte
	 * @see ControleurCarte
	 */
	private boolean defausse;

	/**
	 * La classe Partie respecte le patron de conception Singleton afin de s'assurer qu'il n'y ait qu'une seule instance de Partie à tout moment.
	 */
	private static Partie instance;

	private Joueur pireJoueur;
	private Joueur meilleurJoueur;
	
	/**
	 * Ce compteur permet de s'assurer qu'une Apocalypse ne peut être jouée au premier tour, ou moins de deux tours après une précèdente Apocalypse.
	 * 
	 *@see Capacites.jouerCapacite(), id 200
	 *@see Partie.deroulerTour()
	 * 
	 * 
	 */
	private int compteurApo;

	public static Partie getInstance() {
		if (instance==null) {
			instance = new Partie();
		}
		return instance;
	}


	private Partie(){
		this.cardconstructor=new CardConstructor();
		this.joueurCourant=null;
		this.nbPhases=0;
		this.partieEnCours=false;
		this.pioche=new LinkedList<Carte>();
		this.joueurs=new ArrayList<Joueur>();
		Partie.milieuTable=new LinkedList<Croyants>();
		this.capacites=new Capacites(this);
		this.pireJoueur=null;
		this.meilleurJoueur=null;
		this.compteurApo=0;
		//Creation du de
		this.de=new LinkedList<Origine>();
		this.de.add(Origine.JOUR);
		this.de.add(Origine.NUIT);
		this.de.add(Origine.NEANT);

		this.joue=false;
		this.sacrifie=false;
		this.defausse=false;
	}
	/**
	 * On démarre une partie. Il faut donc créer les joueurs, distribuer les cartes et commencer la première phase.
	 */
	public void demarrer(){

		this.partieEnCours=true;

		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre de joueurs réels");
		this.creerJoueursReels(sc.nextInt());
		
		System.out.println("Nombre de joueurs virtuels");
		this.creerJoueursVirtuels(sc.nextInt());
		
		nbJoueurs=this.joueurs.size();
		
		if(nbJoueurs==0){
			System.out.println("Il n'y a pas de joueurs.");
			this.demarrer();
		}
		
		
		this.distribuerDivinites();
		
		
		this.creerPioche();
		this.distribuerCartes();

		
		this.deroulerTour();
		
	}

	/**
	 * Une phase est composée de nbJoueurs tours.
	 * 
	 * A la fin d'une phase la liste de joueurs est décalée d'une position vers la gauche. Cela permet au dernier joueur du précèdent tour de joueur en premier au tour suivant.
	 * A la fin d'une phase on remet également les booleens bloquant certaines capacités des joueurs(aJoue, sacrificeCroyantsBloque et sacrificeGuidesBloque) à faux.
	 * 
	 */
	public void deroulerTour(){
		while(this.partieEnCours==true){
			this.debuterPhase(0);
			for(j=0;j<nbJoueurs;j++){
				this.debuterTour(this.joueurs.get(j));
				Iterator<Croyants> ite = Partie.milieuTable.iterator();
				while(ite.hasNext()){
					ite.next().setJustePosé(false);
				}
			}
			Collections.rotate(joueurs, -1);
			Iterator<Joueur> itj = this.joueurs.iterator();
			while(itj.hasNext()){
				itj.next().setSacrificeCroyantsBloque(false);
			}
			while(itj.hasNext()){
				itj.next().setaJoue(false);
			}

			while(itj.hasNext()){
				itj.next().setSacrificeGuidesBloque(false);
			}
			while(itj.hasNext()){
				itj.next().setPointBloques(false);
			}
			this.setCompteurApo(this.getCompteurApo()-1);
		}
	}
	
	
	/**
	 * Methode qui effectue les actions de début de phases:
	 * -lancer le dé
	 * -determiner le joueur courant
	 * @param i le numéro du joueur courant. La méthode de déroulement des phases ayant changée au cours de l'écriture du code, i vaut toujours 0.
	 */
	public void debuterPhase(int i){
		this.nbPhases=this.nbPhases+1;
		this.lancerDe();
		this.joueurCourant=this.joueurs.get(i);
		System.out.println(joueurCourant.nom+" commence\n");
	}

	/**
	 * 
	 * Déroulement d'un tour de jeu
	 * @see Joueur.defaussercarte(sc)
	 * @see Joueur.piocherCarte(pioche)
	 * @see Joueur.choisir(sc)
	 * 
	 * 
	 * @param jCourant
	 */
	public void debuterTour(Joueur jCourant){
		jCourant.joue=true;
		System.out.println("\nC'est au tour de "+jCourant.getNom());
		jCourant.afficherPoints();
		jCourant.afficherMain();
		
		Scanner sc = new Scanner(System.in);
		jCourant.defausserCarte(sc);

		
		if(jCourant.main.size()<7){
			System.out.println("Votre main est recomplétée...\n");
			jCourant.piocherCarte(pioche);
		}

		

		int f= 0;
		while(f==0){
			jCourant.choisir(sc);

			f=jCourant.finirTour(sc);
		}
		jCourant.aJoue=true;



	}
	
	public void creerPioche(){
		cardconstructor.construireDeckCroyants();
		cardconstructor.construireDeckGuides();
		cardconstructor.construireDeckApo();
		cardconstructor.construireDeckDeusEx();
		pioche.addAll(cardconstructor.listeCroyants);
		pioche.addAll(cardconstructor.listeGuides);
		pioche.addAll(cardconstructor.listeDeusEx);
		pioche.addAll(cardconstructor.listeApo);

		
		Collections.shuffle(pioche);	
	}

	public void finirPartie(){
		this.partieEnCours=false;
		this.determinerMeilleurJoueur();
		System.out.println(this.getMeilleurJoueur().nom+" a gagné la partie.\nMerci d'avoir joué");
	}
	/**
	 * Verifie si il y a une égalité entre les derniers joueurs. Dans ce cas l'Apocalypse est rejetée.
	 * @return un boolean
	 */
	public boolean petiteEgalite(){
		boolean e=false;
		this.determinerPireJoueur();
		Iterator<Joueur> it  = this.joueurs.iterator();
		while(it.hasNext()){
			if(it.next().puissancePriere==this.pireJoueur.puissancePriere){
				if(it.next()==this.pireJoueur){
					e=false;
				}else{
					e=true;
				}
			}
		}
		return e;
	}
	/**
	 * Verifie si il y a une égalité entre les premiers joueurs. Dans ce cas l'Apocalypse est rejetée.
	 * @return un boolean
	 */
	public boolean grandeEgalite(){
		boolean e=false;
		this.determinerMeilleurJoueur();
		Iterator<Joueur> it  = this.joueurs.iterator();
		while(it.hasNext()){
			if(it.next().puissancePriere==this.meilleurJoueur.puissancePriere && ((it.next()!=this.meilleurJoueur))){


				e=true;

			}
		}
		return e;
	}

	public Joueur determinerPireJoueur(){
		this.setPireJoueur(this.joueurs.get(1));
		Iterator<Joueur> it = this.joueurs.iterator();
		while(it.hasNext()){
			if(it.next().puissancePriere<this.pireJoueur.puissancePriere){
				this.setPireJoueur(it.next());
			}
		}
		return this.getPireJoueur();
	}

	public Joueur determinerMeilleurJoueur(){
		this.setMeilleurJoueur(this.joueurs.get(1));
		Iterator<Joueur> it = this.joueurs.iterator();
		while(it.hasNext()){
			if(it.next().puissancePriere>this.meilleurJoueur.puissancePriere){
				this.setMeilleurJoueur(it.next());
			}
		}
		return this.getMeilleurJoueur();
	}
	
	
	
	/**
	 * On crée une pioche de divinités qui sera ensuite supprimée
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void distribuerDivinites(){
		cardconstructor.construireDeckDivinites();
		pioche.addAll(cardconstructor.listeDivinites);
		Collections.shuffle(pioche);
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext())
		{
			Joueur joueur = it.next();
			joueur.setDiviniteJoueur((Divinites) pioche.pop());
			System.out.println("\n"+joueur.nom + " vous êtes la divinité " + joueur.diviniteJoueur.nom);
			System.out.println("Origine : " +joueur.diviniteJoueur.origine);
			System.out.println("Dogmes : " + joueur.diviniteJoueur.getDogme1() + ", " + joueur.diviniteJoueur.getDogme2()+ ", " + joueur.diviniteJoueur.getDogme3());
		}

		pioche.clear();
	}
	
	public void creerJoueursReels(int nbJoueurs){
		
		if((nbJoueurs)>6){
			System.out.println("Il y a trop de joueurs. (max 6)");
			this.demarrer();
		}

		
		for(i=0;i<nbJoueurs;i++){
			System.out.print("Comment se nomme le joueur n°"+(i+1)+" ?");
			Scanner sc1 = new Scanner(System.in);
			this.joueurs.add(new JoueurReel(this,sc1.nextLine()));	
		}

		
	}
	
	public void creerJoueursVirtuels(int nbJoueurs){
		
		int taille=this.joueurs.size();
		
		if((nbJoueurs+taille)>6){
			System.out.println("Il y a trop de joueurs. (max 6)");
			this.demarrer();
		}
		
		for(i=taille+1;i<taille+1+nbJoueurs;i++){
			System.out.print("Comment se nomme le joueur n°"+(i+1)+" ?");
			Scanner sc1 = new Scanner(System.in);
			this.joueurs.add(new JoueurVirtuel(this,sc1.nextLine()));	
		}
		
	}
	/**
	 * Cette méthode mélange une collection d'Origines, et envoie la première en paramètre de la méthode Joueur.ajouterPoints(cosmogonie)
	 * @see Joueur
	 */
	public void lancerDe(){
		
		System.out.println("\nLancé du dé...");
		Collections.shuffle(de);
		Origine cosmogonie = this.de.getFirst();
		System.out.println("Le dé tombe sur "+ this.de.getFirst()+"!\n");
		Iterator<Joueur> it = this.joueurs.iterator();
		while (it.hasNext()){
			it.next().ajouterPoints(cosmogonie);
		}
	}

	public void distribuerCartes(){			

		if (pioche.isEmpty()==false){
			Iterator<Joueur> it = joueurs.iterator();
			while(it.hasNext()){
				Joueur j = (Joueur) it.next();
				j.piocherCarte(pioche);
			}
		}
	}

	public boolean piocheVide(){
		return pioche.isEmpty();
	}

	public void ajouterSousPioche(Carte carte){
		this.pioche.addFirst(carte);
	}

	public void poserCarte(Croyants carte){
		this.milieuTable.add(carte);
	}

	public void afficherMilieuTable(){
		System.out.println("Milieu de la table:");
		if(this.milieuTable.size()==0){
			System.out.println("Aucune carte!");
		}else{
			Iterator<Croyants> it = this.milieuTable.iterator();
			int i=1;
			while(it.hasNext()){
				System.out.println(i+". "+it.next());
				i++;
			}
		}

	}

	public void afficherJoueurs(){
		Iterator<Joueur> it = this.joueurs.iterator();
		int k =1;
		while(it.hasNext()){
			Joueur j = it.next();
			System.out.print(k+". "+j.getNom()+"\n");
			System.out.println(j.getDiviniteJoueur());
			k++;
		}
	}


	public void afficherTousEspaces(){

		Iterator<Joueur> it = this.joueurs.iterator();

		while(it.hasNext()){

			it.next().afficherEspaceJoueur();
		}

	}

	public void ajouterMilieu(Croyants croyant){
		this.milieuTable.add(croyant);
		this.setChanged();
	}
	
	public void retirerMilieu(Croyants croyant){
		this.milieuTable.remove(croyant);
		this.setChanged();
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Partie.getInstance();

		System.out.println("Jouer en console ou en vue graphique?1=console 0=graphique");
		Scanner sc = new Scanner(System.in);
		switch(sc.nextInt()){
		case(1):{
			instance.demarrer();
			break;
		}
		case(0):{
			VueJeu vj = new VueJeu(instance);
		}
		}
		
		
		
		
	}


	public Joueur getPireJoueur() {
		return pireJoueur;
	}

	public void setPireJoueur(Joueur pireJoueur) {
		this.pireJoueur = pireJoueur;
	}

	public Joueur getMeilleurJoueur() {
		return meilleurJoueur;
	}

	public void setMeilleurJoueur(Joueur meilleurJoueur) {
		this.meilleurJoueur = meilleurJoueur;
	}

	public boolean isPartieEnCours() {
		return partieEnCours;
	}

	public void setPartieEnCours(boolean partieEnCours) {
		this.partieEnCours = partieEnCours;
	}

	public int getNbPhases() {
		return nbPhases;
	}

	public void setNbPhases(int nbPhases) {
		this.nbPhases = nbPhases;
	}

	public int getCompteurApo() {
		return compteurApo;
	}

	public void setCompteurApo(int compteurApo) {
		this.compteurApo = compteurApo;
	}

	public LinkedList<Origine> getDe() {
		return de;
	}

	public void setDe(LinkedList<Origine> de) {
		this.de = de;
	}


	public Joueur getJoueurCourant() {
		return joueurCourant;
	}


	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}


	public static LinkedList<Croyants> getMilieuTable() {
		return milieuTable;
	}


	public static void setMilieuTable(LinkedList<Croyants> milieuTable) {
		Partie.milieuTable = milieuTable;
	}


	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}


	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}


	public LinkedList<Carte> getPioche() {
		return pioche;
	}


	public void setPioche(LinkedList<Carte> pioche) {
		this.pioche = pioche;
	}


	public static int getNbJoueurs() {
		return nbJoueurs;
	}


	public static void setNbJoueurs(int nbJoueurs) {
		Partie.nbJoueurs = nbJoueurs;
	}


	public CardConstructor getCardconstructor() {
		return cardconstructor;
	}


	public void setCardconstructor(CardConstructor cardconstructor) {
		this.cardconstructor = cardconstructor;
	}


	public boolean isJoue() {
		return joue;
	}


	public void setJoue(boolean joue) {
		this.joue = joue;
	}


	public boolean isSacrifie() {
		return sacrifie;
	}


	public void setSacrifie(boolean sacrifie) {
		this.sacrifie = sacrifie;
	}


	public boolean isDefausse() {
		return defausse;
	}


	public void setDefausse(boolean defausse) {
		this.defausse = defausse;
	}

}
