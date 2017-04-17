package modele;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe définissant le comportement des joueurs virtuels.
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * @see Joueur
 */
public class JoueurVirtuel extends Joueur{
	protected Partie partie;
	public JoueurVirtuel(Partie partie, String nom){
		super(numero,nom,partie);
		this.partie=partie;
		
	}

	public void jouerCarte(Scanner sc){
		
		boolean a=true;
		System.out.println("Quelle carte voulez vous jouer?");
		this.afficherMain();
		
		
		Random random = new Random();
		int choix = random.nextInt(this.main.size()-1)+1;
		Carte carte = this.main.get(choix-1);
		
		
		if (carte instanceof Croyants){
			this.poserCarte((Croyants) carte);
		} else if (carte instanceof Guides){

			System.out.println("Ce guide peut récupérer " +(((Guides) carte).getNbGuidésMax())+" croyants.");
			while(a){
				if(partie.milieuTable.size()==0){
					System.out.println("Il n'y a pas de croyants sur la table!");
					a=false;
				}else{
					System.out.println("Quels croyants voulez vous récuperer?");
					partie.afficherMilieuTable();
					
					
					int choix2 = random.nextInt(partie.milieuTable.size())+1;
					Croyants c = partie.milieuTable.get(choix2-1);
					
					
					System.out.println("Le croyant "+c.getNom()+" est guidé par le "+carte.getNom());
					this.espaceJoueur.add(c);
					c.setGuide((Guides) carte);
					c.setEstGuidé(true);
					partie.milieuTable.remove(c);
					((Guides) carte).setNbGuidés(((Guides) carte).getNbGuidés()+1);

					System.out.println("Voulez vous récupèrer un autre croyant? 1=oui 0=non");
					int choix3=1;
					switch(choix3)
					{
					case(0):
					{ 	
						this.espaceJoueur.add(carte);
						this.main.remove(carte);
						a=false;
						break;
					}
					case(1):
					{	
						if(((Guides) carte).getNbGuidés()==((Guides) carte).getNbGuidésMax()){
							System.out.println("Il y a trop de croyants pour ce guide!");
							this.espaceJoueur.add(carte);
							this.main.remove(carte);
							choix3=0;
							a=false;
							break;
						}else{
							a=true;
							break;
						}

					}

					}

				}
			}

		} else if (carte instanceof DeusEx){
			partie.capacites.jouerCapacite(this, carte, carte.id, sc);
		} else if (carte instanceof Apocalypses){
			partie.capacites.jouerCapacite(this, carte, carte.id,sc);
		}
	}

	public void defausserCarte(Scanner sc){
System.out.println(this.nom + ", voulez vous défausser des cartes? 1=oui 0=non");
boolean c=true;
while(c==true){
		if(this.main.size()<=4){
			System.out.println(this.nom+" ne défausse pas de cartes!");
			c=false;
		} else {

				System.out.println("Quelle carte voulez vous defausser\n");
				this.afficherMain();
				Random random = new Random();
				int choix = random.nextInt(this.main.size())+1;
				//System.out.println(this.main.get(sc.nextInt()-1).nom + " retourne dans la pioche...");
				System.out.println(this.nom+" a défaussé "+this.main.get(choix-1).nom+"\n");
				partie.ajouterSousPioche(this.main.remove(choix-1));
				//sc.close();
				System.out.println("Voulez vous défausser d'autres cartes? 1=oui 0=non");
				int choix2;
				if(this.main.size()<=4){
					choix2=0;
				} else {
					choix2=1;
				}
				
				switch(choix2)
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


	public void sacrifierCarte(Scanner sc){
		
		Random random = new Random();
		System.out.println("Quelle carte voulez vous sacrifier?");
		this.afficherEspaceJoueur();
		int choix = random.nextInt(this.espaceJoueur.size())+1;
		Carte carte = this.espaceJoueur.get(choix-1);
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


	public int finirTour(Scanner sc){
		return 0;
	}

	public void choisir(Scanner sc){
	
		System.out.println(this.nom + ", que voulez vous faire?"); 
		Random random = new Random();
		
		int choix = random.nextInt(3)+1;
		if(this.espaceJoueur.size()>0){
			choix=2;
		} else {
			choix=1;
		}
		
		switch(choix)
			
			{
			case(1):
			{
				System.out.println(this.nom+" a choisi de jouer des cartes!");
				this.jouerCarte(sc);
				break;
			}
			case(2):
			{
				System.out.println(this.nom+" a choisi de sacrifier des cartes!");
				this.sacrifierCarte(sc);
				break;
			
			}
			case(0):
			{
				break;
			}
			}

		}





	@Override
	public void jouerCroyant(Croyants croyant) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void jouerGuide(Guides carte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guiderCroyant(Guides carte, Croyants c) {
		// TODO Auto-generated method stub
		
	}

}
