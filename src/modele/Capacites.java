package modele;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe contenant l'ensemble des capacités des cartes Deux Ex, des sacrifices et des divinités.
 * 
 * @author Thibaut Counot
 * @author Hassan Mroueh
 *
 */
public class Capacites {

	private Partie partie;

	public Capacites(Partie partie){
		this.partie=partie; 
	}


	/**
	 * Cette méthode récupère l'id de la carte que le joueur veut jouer.
	 * Cet id correspond à une capacité, dont l'apocalypse et les capacités de divinités
	 * 
	 * 
	 * 
	 * 
	 * @param joueur le joueur qui veut jouer la capacité
	 * @param carte la carte que le joueur veut jouer
	 * @param i  l'identificateur de la carte
	 * @param sc un scanner
	 * 
	 * @author thibaut
	 * 
	 * @see CardConstructor
	 * 
	 */
	public void jouerCapacite(Joueur joueur, Carte carte, int i, Scanner sc){ //throws TypeCarteErronneException{
		System.out.println(carte.description+"\n");

		switch(i)
		{
		case(1): 
		{
			if (carte.origine==Origine.JOUR){
				joueur.pointsJour++;
				joueur.afficherPoints();
			}else if (carte.origine==Origine.NUIT){
				joueur.pointsNuit++;
				joueur.afficherPoints();
			}else if (carte.origine==Origine.NEANT){
				joueur.pointsNeant++;
				joueur.afficherPoints();
			}

			break;
		}
		case(2):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if((choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur)&&(partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.NATURE)||partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.MYSTIQUE))){
					Joueur jChoix = partie.joueurs.get(choix);
					System.out.println("Le joueur "+jChoix.getNom()+" ne peut plus sacrifier de Croyants pendant ce tour.");
					jChoix.setSacrificeCroyantsBloque(true);
					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);
			break;
		}
		case(3):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

					Joueur jChoix = this.partie.joueurs.get(sc.nextInt()-1);
					Carte c1 = jChoix.main.get(1+(int)(Math.random()*(jChoix.main.size()-1)+1));
					joueur.main.add(c1);
					jChoix.main.remove(c1);
					Carte c2 = jChoix.main.get(1+(int)(Math.random()*(jChoix.main.size()-1)+1));
					joueur.main.add(c2);
					jChoix.main.remove(c2);



					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);

			break;



		}
		case(4): 
		{
			System.out.println("Quel joueur choisissez vous?");
			partie.afficherJoueurs();


			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur && partie.joueurs.get(choix).espaceJoueur.size()>0){
					Joueur jChoix = this.partie.joueurs.get(choix);
					jChoix.afficherEspaceJoueur();
					boolean choixValide=false;
					do{
						System.out.println("Choisissez un croyant");
						int c=sc.nextInt()-1;
						if (jChoix.espaceJoueur.get(c) instanceof Croyants){
							jChoix.sacrifierCarte(sc);


							choixValide=true;
						}else{
							System.out.println("Cette carte n'est pas un croyant!");}
					}while(choixValide==false);



					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);



			partie.capacites.jouerCapacite(joueur, carte, i, sc);


			break;
		}
		case(5):
		{
			System.out.println("Quel joueur choisissez vous?");
			partie.afficherJoueurs();


			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur && partie.joueurs.get(choix).espaceJoueur.size()>0){
					Joueur jChoix = this.partie.joueurs.get(choix);
					jChoix.afficherEspaceJoueur();
					boolean choixValide=false;
					do{
						System.out.println("Choisissez un guide");
						int c=sc.nextInt()-1;
						if (jChoix.espaceJoueur.get(c) instanceof Guides){
							jChoix.sacrifierCarte(sc);
							choixValide=true;
						}else{
							System.out.println("Cette carte n'est pas un guide!");}
					}while(choixValide==false);



					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);



			partie.capacites.jouerCapacite(joueur, carte, i, sc);


			break;
		}
		case(6):
		{
			System.out.println("Quel guide voulez vous récupérer?");
			joueur.afficherEspaceJoueur();
			joueur.main.add(joueur.espaceJoueur.get(sc.nextInt()-1));
			Iterator<Carte> it = joueur.espaceJoueur.iterator();
			while(it.hasNext()){
				Croyants c = (Croyants) it.next();
				if(c.getGuide()==joueur.espaceJoueur.get(sc.nextInt()-1)){
					partie.milieuTable.add(c);
					joueur.espaceJoueur.remove(c);
				}
			}
			joueur.espaceJoueur.remove(sc.nextInt());
			break;
		}
		case(7):
		{
			partie.lancerDe();
			break;
		}
		case(8):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if((choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur)&&(partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.HUMAIN)||partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.MYSTIQUE))){
					Joueur jChoix = partie.joueurs.get(choix);
					System.out.println("Le joueur "+jChoix.getNom()+" ne peut plus sacrifier de Croyants pendant ce tour.");
					jChoix.setSacrificeCroyantsBloque(true);
					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);
			break;
		}
		case(9):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

					Joueur jChoix = partie.joueurs.get(choix);
					jChoix.afficherEspaceJoueur();
					boolean choixValide=false;
					do{
						System.out.println("Choisissez un guide");
						int c=sc.nextInt()-1;
						if (jChoix.espaceJoueur.get(c) instanceof Guides){
							Guides guideChoix=(Guides) jChoix.espaceJoueur.get(c);
							System.out.println("Les croyants rattachés à ce guide retourne au centre de la table");
							System.out.println("Le guide est défaussé.");
							jChoix.espaceJoueur.remove(guideChoix);
							Iterator<Carte> it = jChoix.espaceJoueur.iterator();
							while(it.hasNext()){
								Croyants cro=(Croyants)it.next();
								if (cro.getGuide()==guideChoix){
									partie.milieuTable.add(cro);
									jChoix.espaceJoueur.remove(cro);
								}
							}

							choixValide=true;
						}else{
							System.out.println("Cette carte n'est pas un guide!");}
					}while(choixValide==false);


					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);


			break;
		}
		case(10):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur && partie.joueurs.get(choix).aJoue==false){
					Joueur jChoix=partie.joueurs.get(choix);
					System.out.println(joueur.getNom()+" récupère les points de "+jChoix.getNom());
					joueur.setPointsJour(joueur.getPointsJour()+jChoix.getPointsJour());
					joueur.setPointsNuit(joueur.getPointsNuit()+jChoix.getPointsNuit());
					joueur.setPointsNeant(joueur.getPointsNeant()+jChoix.getPointsNeant());
					jChoix.setPointsJour(0);
					jChoix.setPointsNuit(0);
					jChoix.setPointsNeant(0);
					joueur.afficherPoints();
					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);

			break;

		}
		case(11):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

					Joueur jChoix = partie.joueurs.get(choix);
					jChoix.afficherMain();
					boolean choixValide=false;
					do{
						System.out.println("Choisissez un croyant à sacrifier");
						int c=sc.nextInt()-1;
						if (jChoix.main.get(c) instanceof Croyants){
							System.out.println(jChoix.nom+" possède les cartes suivantes:");
							jChoix.afficherMain();
							System.out.println("Quel croyant choisissez vous");
							jChoix.partie.capacites.jouerCapacite(joueur,jChoix.main.get(sc.nextInt()),jChoix.main.get(sc.nextInt()).id,sc);
							choixValide=true;
						}else{
							System.out.println("Cette carte n'est pas un croyant!");}
					}while(choixValide==false);


					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);



			break;
		}
		case(12):
		{


			break;
		}
		case(13):
		{	

			break;
		}
		case(14):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if((choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur)&&(partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.HUMAIN)||partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.SYMBOLES))){
					Joueur jChoix = partie.joueurs.get(choix);
					System.out.println("Le joueur "+jChoix.getNom()+" ne peut plus sacrifier de Guides pendant ce tour.");
					jChoix.setSacrificeGuidesBloque(true);
					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);
			break;

		}
		case(15):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if((choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur)&&(partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.CHAOS)||partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.MYSTIQUE))){
					Joueur jChoix = partie.joueurs.get(choix);
					System.out.println("Le joueur "+jChoix.getNom()+" ne peut plus sacrifier de Guides pendant ce tour.");
					jChoix.setSacrificeGuidesBloque(true);
					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);
			break;

		}
		case(102):
		{
			System.out.println("Ce guide a ramené "+((Guides)carte).getNbGuidés()+" croyants. Vous récuperez autant de points d'action.\n Quelle sera leut origine\n1=Jour\n2=Nuit\n3=Néant");
			int choix = sc.nextInt();
			switch(choix){
			case(1):
			{
				joueur.setPointsJour(joueur.getPointsJour()+((Guides)carte).getNbGuidés());
				joueur.afficherPoints();
				break;
			}
			case(2):
			{
				joueur.setPointsNuit(joueur.getPointsNuit()+((Guides)carte).getNbGuidés());
				joueur.afficherPoints();
				break;
			}
			case(3):
			{
				joueur.setPointsNeant(joueur.getPointsNeant()+((Guides)carte).getNbGuidés());
				joueur.afficherPoints();
				break;
			}

			}
			break;
		}
		case(103):
		{

			break;
		}
		case(104):
		{
			break;
		}
		case(105):
		{
			Iterator<Croyants> it = Partie.milieuTable.iterator();
			while(it.hasNext()){
				Croyants croyant = it.next();
				if(((croyant.dogme1==Dogmes.NATURE||croyant.dogme2==Dogmes.NATURE||croyant.dogme3==Dogmes.NATURE) && (croyant.origine==Origine.NUIT||croyant.origine==Origine.NEANT))==true){
					partie.ajouterSousPioche(croyant);
					Partie.milieuTable.remove(croyant);
					System.out.println(croyant.nom + " retourne dans la pioche!");
				}
			} 
			break;
		}
		case(106):
		{

			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size() && partie.joueurs.get(choix)!=joueur && (partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.HUMAIN)||partie.joueurs.get(choix).diviniteCroitDogme(Dogmes.SYMBOLES)) ){
					Joueur jChoix = this.partie.joueurs.get(choix);
					boolean choixValide=true;
					for(i=1;i<=2;i++){
						jChoix.afficherMain();
						do{
							System.out.println("Choisissez un croyant à sacrifier");
							int c=sc.nextInt();
							if (jChoix.main.get(c-1) instanceof Croyants){
								jChoix.main.remove(c-1);
								choixValide=true;
							}else{
								System.out.println("Cette carte n'est pas un croyant!");}
						}while(choixValide==false);
					}

					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);

			break;
		}
		case(107):
		{


			break;
		}
		case(108):
		{
			break;
		}
		case(109):
		{
			break;
		}
		case(110): 
		{
			Iterator<Croyants> it = Partie.milieuTable.iterator();
			while(it.hasNext()){
				Croyants croyant = it.next();
				if((croyant.dogme1==Dogmes.MYSTIQUE||croyant.dogme2==Dogmes.MYSTIQUE||croyant.dogme3==Dogmes.MYSTIQUE)==true){
					partie.ajouterSousPioche(croyant);
					Partie.milieuTable.remove(croyant);
					System.out.println(croyant.nom + " retourne dans la pioche!");
				}
			} 
			break;
		}
		case(111):
		{
			System.out.println("Choisissez la cosmogonie:\n1.Jour\n2.Nuit\n3.Néant");
			break;
		}
		case(200):
		{	
			if(partie.getNbPhases()==1){
				System.out.println("Vous ne pouvez pas jouer d'Apocalypse au premier tour.");
			} else if (partie.getCompteurApo()==0){
				System.out.println("Une Apocalypse a déjà été jouée il y a moins de deux tours!");
			} else {
				System.out.println("Apocalypse!");
				if(partie.joueurs.size()>=4){
					partie.determinerPireJoueur();
					partie.petiteEgalite();
					if(partie.petiteEgalite()==true){
						System.out.println("Il y a égalité (entre les derniers), la carte Apocalypse est défaussée!");
						partie.ajouterSousPioche(carte);
						joueur.main.remove(carte);
						partie.setCompteurApo(2);
					} else {
						System.out.println(partie.getPireJoueur().nom+" est le dernier Joueur avec "+partie.getPireJoueur().puissancePriere+" points\nIl est éliminé!");
						partie.joueurs.remove(partie.getPireJoueur());
					}
				} else if (partie.joueurs.size()<4){
					partie.determinerMeilleurJoueur();
					partie.grandeEgalite();
					if(partie.grandeEgalite()==true){
						System.out.println("Il y a égalité(entre les premiers), la carte Apocalypse est défaussée!");
						partie.ajouterSousPioche(carte);
						joueur.main.remove(carte);
						partie.setCompteurApo(2);
					} else {
						System.out.println(partie.getMeilleurJoueur().nom+" est le meilleur joueur avec "+partie.getMeilleurJoueur().puissancePriere+" points\nIl gagne donc la partie!");
						partie.finirPartie();
					}
				}
			}
			break;
		}
		case(301):
		{
			break;
		}
		case(302):
		{
			break; 
		}
		case(303):
		{	
			boolean espJoueursVides=true;
			Iterator<Joueur> ite =partie.joueurs.iterator();
			while(ite.hasNext()){
				if(ite.next().espaceJoueur.size()!=0){
					espJoueursVides=false;
				}
			}

			if(espJoueursVides==true){
				System.out.println("Personne n'a de cartes récupérées.");
				joueur.main.add(carte); 
			} else {
				boolean choixValide2=false;
				do{
					System.out.println("Quel joueur choisissez vous?");
					partie.afficherJoueurs();
					int choix=sc.nextInt()-1;
					if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur && partie.joueurs.get(choix).espaceJoueur.size()!=0){
						Joueur jChoix = partie.joueurs.get(choix);
						jChoix.afficherEspaceJoueur();
						boolean choixValide=false;
						do{
							System.out.println("Choisissez un guide à récupérer");
							int c=sc.nextInt()-1;
							if (jChoix.espaceJoueur.get(c) instanceof Guides){
								Guides guideChoix =(Guides) jChoix.espaceJoueur.get(c);
								joueur.espaceJoueur.add(guideChoix);
								jChoix.espaceJoueur.remove(guideChoix);

								Iterator<Carte> it = jChoix.espaceJoueur.iterator();
								while(it.hasNext()){
									Croyants cro=(Croyants)it.next();
									if (cro.getGuide()==guideChoix){
										joueur.espaceJoueur.add(cro);
										jChoix.espaceJoueur.remove(cro);
										jChoix.setPuissancePriere(jChoix.getPuissancePriere()-cro.getNbCroyants());
									}
								}

								choixValide=true;
							}else{
								System.out.println("Cette carte n'est pas un guide!");}
						}while(choixValide==false);


						choixValide2=true;
					} else {
						System.out.println("Saisie erronée!");
					}
				}while(choixValide2==false);
			}
			break;
		}
		case(304):
		{	
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){
					Joueur jChoix = this.partie.joueurs.get(choix);
					boolean choixValide=true;
					for(i=1;i<=2;i++){
						jChoix.afficherMain();
						do{
							System.out.println("Choisissez un croyant à sacrifier");
							int c=sc.nextInt();
							if (jChoix.main.get(c-1) instanceof Croyants){
								jChoix.main.remove(c-1);
								choixValide=true;
							}else{
								System.out.println("Cette carte n'est pas un croyant!");}
						}while(choixValide==false);
					}

					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);





			break;
		}
		case(305):
		{
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){
					Joueur jChoix = this.partie.joueurs.get(choix);
					Carte c1 = jChoix.main.get(1+(int)(Math.random()*(jChoix.main.size()-1)+1));
					joueur.main.add(c1);
					jChoix.main.remove(c1);
					Carte c2 = jChoix.main.get(1+(int)(Math.random()*(jChoix.main.size()-1)+1));
					joueur.main.add(c2);
					jChoix.main.remove(c2); 
					Carte c3 = jChoix.main.get(1+(int)(Math.random()*(jChoix.main.size()-1)+1));
					joueur.main.add(c3);
					jChoix.main.remove(c3);
					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);
			break;
		}
		case(306):
		{
			break;
		}
		case(307):
		{
			if(joueur.espaceJoueur.size()==0){
				System.out.println("Vous n'avez pas de cartes dans votre espace!");
				joueur.main.add(carte);
			} else {

				joueur.afficherEspaceJoueur();

				boolean choixValide=false;
				do{
					System.out.println("Choisissez un guide ou un croyant");
					int c=sc.nextInt()-1;
					if (joueur.espaceJoueur.get(c) instanceof Guides){
						Guides guideChoix =(Guides) joueur.espaceJoueur.get(c);
						System.out.println("Vous avez choisi "+guideChoix.nom);
						partie.capacites.jouerCapacite(joueur, guideChoix, guideChoix.id, sc);

						choixValide=true;
					}else if(joueur.espaceJoueur.get(c) instanceof Croyants){
						Croyants croyantChoix =(Croyants) joueur.espaceJoueur.get(c);
						System.out.println("Vous avez choisi "+croyantChoix.nom);
						partie.capacites.jouerCapacite(joueur, croyantChoix, croyantChoix.id, sc);
					}else{
						System.out.println("Vous ne pouvez pas choisir cette carte!");}
				}while(choixValide==false);
			}
			break;
		}
		case(308):
		{
			break;
		}
		case(309):
		{
			break;
		}
		case(310):
		{

			break;
		}
		case(311):
		{
			break;
		}
		case(312):
		{
			break;
		}
		case(313):
		{
			break;
		}
		case(314):
		{	
			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous?");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

					Joueur jChoix = partie.joueurs.get(choix);
					jChoix.afficherMain();
					boolean choixValide=false;
					do{
						System.out.println("Choisissez un guide à sacrifier");
						Carte c=jChoix.main.get(sc.nextInt()-1);
						if (c instanceof Guides){
							Guides guide1=(Guides) c;

							boolean choixValide3=false;
							do{
								System.out.println("Choisissez un de vos guides à sacrifier");
								joueur.afficherMain();
								Carte c1=jChoix.main.get(sc.nextInt()-1);
								if (c1 instanceof Guides){
									Guides guide2=(Guides) c1;

									System.out.println("Lancé du dé...");
									Collections.shuffle(partie.getDe());
									Origine resultat = partie.getDe().getFirst();
									switch(resultat)
									{
									case JOUR:
									{
										System.out.println("Le dé tombe sur jour, votre guide est sacrifié.");
										partie.capacites.jouerCapacite(joueur, guide2, guide2.id, sc);
										joueur.main.remove(guide2);
										break;
									}
									case NUIT:
									{
										System.out.println("Le dé tombe sur Nuit, le guide adverse est sacrifié.");
										partie.capacites.jouerCapacite(jChoix, guide1, guide1.id, sc);
										jChoix.main.remove(guide1);
										break;
									}
									case NEANT:
									{
										System.out.println("Le dé tombe sur Néant, rien ne se passe.");
										break;
									}
									}


									choixValide3=true;
								}else{
									System.out.println("Vous ne pouvez pas choisir cette carte!");}
							}while(choixValide3==false);

							choixValide=true;
						}else{
							System.out.println("Vous ne pouvez pas choisir cette carte!");}
					}while(choixValide==false);


					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);

			break;
		}
		case(401):
		{
			break;
		}
		case(402):
		{
			break;
		}
		case(403): 
		{
			Iterator<Croyants> it = Partie.milieuTable.iterator();
			while(it.hasNext()){
				Croyants croyant = it.next();
				if((croyant.origine==Origine.NEANT)){
					Partie.milieuTable.remove(croyant);
					System.out.println(croyant.nom + " retourne dans la pioche!");
				}
			} 
			break;
		}
		case(404):
		{
			boolean choixValide=false;
			do{
				boolean choixValide2=false;
				do{
					System.out.println("Quel joueur choisissez vous");
					partie.afficherJoueurs();
					int choix=sc.nextInt()-1;
					if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

						Joueur jChoix = partie.joueurs.get(choix);
						boolean possedeApo =false;
						Iterator<Carte> it = jChoix.main.iterator();
						while(it.hasNext()){
							if (it.next() instanceof Apocalypses){
								possedeApo=true;
							}
						}
						if(possedeApo==true){
							partie.capacites.jouerCapacite(jChoix, carte, 200, sc);
							choixValide=true;
						} else {
							System.out.println("Ce joueur ne possède pas de carte Apocalypse");

						}

						choixValide2=true;
					} else {
						System.out.println("Saisie erronée!");
					}
				}while(choixValide2==false);



			}while(choixValide==false);

			break;
		}
		case(405):
		{

			boolean choixValide=false;
			do{
				boolean choixValide2=false;
				do{
					System.out.println("Quel joueur choisissez vous");
					partie.afficherJoueurs();
					int choix=sc.nextInt()-1;
					if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

						Joueur jChoix = partie.joueurs.get(choix);
						boolean possedeApo =false;
						Iterator<Carte> it = jChoix.main.iterator();
						while(it.hasNext()){
							if (it.next() instanceof Apocalypses){
								possedeApo=true;
							}
						}
						if(possedeApo==true){
							partie.capacites.jouerCapacite(jChoix, carte, 200, sc);
							choixValide=true;
						} else {
							System.out.println("Ce joueur ne possède pas de carte Apocalypse");

						}




						choixValide2=true;
					} else {
						System.out.println("Saisie erronée!");
					}
				}while(choixValide2==false);


			}while(choixValide==false);

			break;
		}
		case(406):
		{
			Iterator<Croyants> it = Partie.milieuTable.iterator();
			while(it.hasNext()){
				Croyants croyant = it.next();
				if((croyant.origine==Origine.JOUR)){
					Partie.milieuTable.remove(croyant);
					System.out.println(croyant.nom + " retourne dans la pioche!");
				}
			} 
			break;
		}
		case(407):
		{
			int j=0;
			Iterator<Carte> it = joueur.espaceJoueur.iterator();
			while(it.hasNext()){
				if (it.next() instanceof Guides){
					j++;
				}
			}
			System.out.println("Vous possèdez "+j+" guides. Vous récupérez autant de points Néant");
			joueur.setPointsNeant(joueur.getPointsNeant()+j);
			joueur.afficherPoints();
			break;
		}
		case(408):
		{

			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

					Joueur jChoix = partie.joueurs.get(choix);
					boolean choixValide=false;
					do{
						System.out.println("Choisissez un guide à sacrifier");
						Carte c=jChoix.main.get(sc.nextInt()-1);
						if (c instanceof Guides && (((Guides) c).getDogme1()==Dogmes.NATURE)||((Guides) c).getDogme1()==Dogmes.SYMBOLES){
							partie.capacites.jouerCapacite(jChoix, c, c.id, sc);
							choixValide=true;
						}else{
							System.out.println("Vous ne pouvez pas choisir cette carte!");}
					}while(choixValide==false);


					choixValide2=true;
				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);

			break;
		}
		case(409):
		{

			boolean choixValide2=false;
			do{
				System.out.println("Quel joueur choisissez vous");
				partie.afficherJoueurs();
				int choix=sc.nextInt()-1;
				if(choix>=0 && choix<=partie.joueurs.size()-1 && partie.joueurs.get(choix)!=joueur){

					Joueur jChoix = partie.joueurs.get(choix);

					joueur.setPointsJour(joueur.getPointsJour()+jChoix.getPointsJour());
					joueur.setPointsNuit(joueur.getPointsNuit()+jChoix.getPointsNuit());
					joueur.setPointsNeant(joueur.getPointsNeant()+jChoix.getPointsNeant());
					jChoix.setPointsJour(0);
					jChoix.setPointsNuit(0);
					jChoix.setPointsNeant(0);

					jChoix.setPointBloques(true);



					boolean choixValide=false;

				} else {
					System.out.println("Saisie erronée!");
				}
			}while(choixValide2==false);



			break;
		}
		case(410):
		{

			break;
		}



		}














	}







}
