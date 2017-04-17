package modele;
import java.util.Iterator;
import java.util.Scanner;
/**
 * Classe représentant les joueurs réels.
 *
 * 
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * @see Joueur
 */
public class JoueurReel extends Joueur{

	protected Partie partie;
	
	public JoueurReel(Partie partie, String nom){

		super(numero,nom,partie);
		this.partie=partie;


	}

	public void choisir(Scanner sc){

		System.out.println(this.nom + ", que voulez vous faire?\n1=jouer une carte\n2=sacrifier une carte\n3=consulter votre main\n4=consulter la table\n5=consulter l'espace de tous les joueurs\n0=finir votre tour"); 
		switch(sc.nextInt())
		{
		case(1):
		{
			this.jouerCarte(sc);
			break;
		}
		case(2):
		{	
			if(this.espaceJoueur.size()>0){
				this.sacrifierCarte(sc);
			}else{
				System.out.println("Vous n'avez aucune carte à sacrifier!");
			}
			break;
		}
		case(3):
		{
			this.afficherMain();
			break;
		}

		case(4):
		{
			this.partie.afficherMilieuTable();
			break;
		}
		case(5):
		{	

			this.partie.afficherTousEspaces();
			break;
		}
		case(6):
		{
			this.jouerCapDivinite();

		}
		case(0):
		{
			break;
		}

		}


	}

	public int finirTour(Scanner sc){
		System.out.println("Avez vous fini? 1=oui 0=non");
		return sc.nextInt();
	}

	public void jouerCroyant(Croyants croyant){
		this.poserCarte((Croyants) croyant);
		((Croyants)croyant).setJustePosé(true);
	}

	public void jouerGuide(Guides carte){
		boolean choixValide2=false;
		do{
			Scanner sc = new Scanner(System.in);
			boolean a;
			System.out.println("Quels croyants voulez vous récuperer?");
			partie.afficherMilieuTable();
			int choix=sc.nextInt()-1;
			if(choix>=0 && choix<=partie.milieuTable.size()-1){
				
				Croyants c = partie.milieuTable.get(sc.nextInt()-1);
				if(c.isJustePosé()==true)
				{
					System.out.println("Le croyants vient d'être posé, vous ne pouvez pas le récupérer!");
				}else if(((Guides) carte).dogmesEnCommun(c)==false){
					System.out.println("Vous n'avez pas de dogmes en commun avec ce croyant!");
					a=false;
				}else{
					this.guiderCroyant(carte,c);

				}
				System.out.println("Voulez vous récupèrer un autre croyant? 1=oui 0=non");
				switch(sc.nextInt())
				{
				case(0):
				{ 	
					this.espaceJoueur.add(carte);
					a=false;
					break;
				}
				case(1):
				{	
					if(((Guides) carte).getNbGuidés()==((Guides) carte).getNbGuidésMax()){
						System.out.println("Il y a trop de croyants pour ce guide!");
						a=false;
						break;
					}else{
						a=true;
						break;
					}

				}
				default:
				{
					a=false;
					break;
				}
				}





				choixValide2=true;
			} else {
				System.out.println("Saisie erronée!");
			}
		}while(choixValide2==false);

	}
	
	public void guiderCroyant(Guides carte,Croyants c){
		this.main.remove(carte);
		this.setChanged();
		this.notifyObservers(this.main);
		System.out.println("Le croyant "+c.getNom()+" est guidé par le "+carte.getNom());

		this.espaceJoueur.add(carte);

		this.espaceJoueur.add(c);
		this.setChanged();
		this.notifyObservers(this.espaceJoueur);
		c.setGuide((Guides) carte);
		c.setEstGuidé(true);
		partie.retirerMilieu(c);
		this.puissancePriere=this.puissancePriere+c.nbCroyants;
		this.setChanged();
		this.notifyObservers(this.puissancePriere);
		((Guides) carte).setNbGuidés(((Guides) carte).getNbGuidés()+1);
	}


	/**
	 * Cette méthode vérifie si un joueur peut jouer une carte, c'est-à-dire s'il possède assez de points, ou si l'état de la partie le permet.
	 */
	public void jouerCarte(Scanner sc){
		
		boolean a=true;
		System.out.println("Quelle carte voulez vous jouer?");
		this.afficherMain();
		Scanner sca= new Scanner(System.in);
		
		
		
		int s1 = sc.nextInt()-1;
		if(s1>this.main.size()-1){
			System.out.println("Saisie erronée!");
		} else {

			Carte carte = this.main.get(s1);



			if((carte.origine==Origine.JOUR && this.getPointsJour()>=1)||(carte.origine==Origine.NUIT && this.getPointsNuit()>=1)||((carte.origine==Origine.NEANT && this.getPointsNeant()>=1))||((carte.origine==Origine.NULL))){


			if (carte instanceof Croyants){
				this.jouerCroyant((Croyants) carte);
			} else if (carte instanceof Guides){

				System.out.println("Ce guide peut récupérer " +(((Guides) carte).getNbGuidésMax())+" croyants.");
				while(a){
					if(partie.milieuTable.size()==0){
						System.out.println("Il n'y a pas de croyants sur la table!");
						a=false;

					}else{
						this.jouerGuide((Guides) carte);
					}
				}
			} else if (carte instanceof DeusEx){
				partie.capacites.jouerCapacite(this, carte, carte.id, sc);
				this.main.remove(carte);
			} else if (carte instanceof Apocalypses){
				partie.capacites.jouerCapacite(this, carte, carte.id,sc);
				this.main.remove(carte);
			}


			}else{
				System.out.println("Vous n'avez pas assez de points pour jouer cette carte");
			}
		}

	}



	public void sacrifierCarte(Scanner sc){

		System.out.println("Quelle carte voulez vous sacrifier?");
		this.afficherEspaceJoueur();
		Carte carte = this.espaceJoueur.get(sc.nextInt()-1);
		if (carte instanceof Croyants){
			if(this.sacrificeCroyantsBloque==true){
				System.out.println("Vous ne pouvez plus sacrifier de croyants pendant ce tour.");
			} else {
				partie.capacites.jouerCapacite(this,carte,carte.id,sc);
				this.espaceJoueur.remove(carte);
				if(((Croyants) carte).getGuide().getNbGuidés()==0){
					System.out.println(((Croyants) carte).getGuide().getNom()+" ne possèdant plus de croyants, retourne dans la pioche.");
					this.espaceJoueur.remove(((Croyants) carte).getGuide());
					this.partie.ajouterSousPioche(((Croyants) carte).getGuide());
				}
			}
		} else if (carte instanceof Guides){
			if(this.sacrificeGuidesBloque==true){
				System.out.println("Vous ne pouvez plus sacrifier de Guides pendant ce tour");
			} else {
				partie.capacites.jouerCapacite(this,carte,carte.id,sc);
				this.espaceJoueur.remove(carte);
				if(((Guides) carte).getNbGuidés()==0){

				}else{
					Iterator<Carte> it = this.espaceJoueur.iterator();
					while( it.hasNext()){
						Croyants c=(Croyants)it.next();
						if (c.getGuide()==carte){
							this.partie.milieuTable.add(c);
							this.espaceJoueur.remove(c);
						}
					}
				}
			}
		}
	}


	public void defausserCarte(Scanner sc){ 

		System.out.println(this.nom + ", voulez vous défausser des cartes? 1=oui 0=non");

		if(sc.nextInt()==1){

			boolean c=true;

			while(c==true){
				System.out.println("Quelle carte voulez vous defausser\n");
				this.afficherMain();

				partie.ajouterSousPioche(this.main.remove(sc.nextInt()-1));

				System.out.println("Voulez vous défausser d'autres cartes? 1=oui 0=non");
				switch(sc.nextInt())
				{
				case(1):
				{
					if(this.main.size()==0){
						System.out.println("Vous n'avez plus de cartes!");
						c=false;
					}else{
						c=true;
					}
					break;
				}
				case(0):
				{
					c=false;
					break;
				}



				}
			}
		}
	}
}
