package modele;
import java.util.LinkedList;



/**
 * Classe construisant des listes de cartes, qui seront ensuite ajout�es dans la pioche pour les cartes croyants, guides,apocalypse et deus ex. Les cartes divinites seront distribu�es au d�but de la partie.
 * 
 * @author Thibaut Counot
 * @author Hassan Mroueh
 * 
 *
 */


public class CardConstructor {

	protected LinkedList<Croyants> listeCroyants;
	protected LinkedList<Guides>	listeGuides;
	protected LinkedList<Apocalypses> listeApo;
	protected LinkedList<DeusEx> listeDeusEx;
	protected LinkedList<Divinites> listeDivinites;


	public CardConstructor(){
		this.listeCroyants=new LinkedList<Croyants>();
		this.listeGuides=new LinkedList<Guides>();
		this.listeApo=new LinkedList<Apocalypses>();
		this.listeDeusEx=new LinkedList<DeusEx>();
		this.listeDivinites=new LinkedList<Divinites>();
	}

	/**
	 * Construit une liste de l'ensemble des cartes croyants.
	 * @return une LinkedList de Croyants
	 */
	public  LinkedList<Croyants> construireDeckCroyants(){
		Croyants Moines1 = new Croyants(Origine.JOUR, "Moines1", Dogmes.HUMAIN, Dogmes.NATURE, Dogmes.MYSTIQUE, "Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Moines1);
		Croyants Moines2 = new Croyants(Origine.JOUR, "Moines2", Dogmes.HUMAIN, Dogmes.CHAOS, Dogmes.MYSTIQUE, "Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Moines2);
		Croyants Moines3 = new Croyants(Origine.JOUR, "Moines3", Dogmes.SYMBOLES, Dogmes.CHAOS, Dogmes.MYSTIQUE, "Donne un point d'Action de la meme origine que la carte", 2,1);
		listeCroyants.add(Moines3);
		Croyants Moines4 = new Croyants(Origine.JOUR, "Moines4", Dogmes.SYMBOLES, Dogmes.NATURE, Dogmes.MYSTIQUE, "Donne un point d'Action de la meme origine que la carte", 2,1);
		listeCroyants.add(Moines4);
		Croyants Moines5 = new Croyants(Origine.JOUR, "Moines5", Dogmes.NATURE, Dogmes.CHAOS, Dogmes.MYSTIQUE, "Donne un point d'Action de la meme origine que la carte", 2,1);
		listeCroyants.add(Moines5);
		
		Croyants Demons1 =new Croyants(Origine.NUIT,"Demons1",Dogmes.HUMAIN,Dogmes.NATURE,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Demons1);
		Croyants Demons2 =new Croyants(Origine.NUIT,"Demons2",Dogmes.HUMAIN,Dogmes.CHAOS,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Demons2);
		Croyants Demons3 =new Croyants(Origine.NUIT,"Demons3",Dogmes.SYMBOLES,Dogmes.CHAOS,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Demons3);
		Croyants Demons4 =new Croyants(Origine.NUIT,"Demons4",Dogmes.NATURE,Dogmes.CHAOS,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Demons4);
		Croyants Demons5 =new Croyants(Origine.NUIT,"Demons5",Dogmes.NATURE,Dogmes.CHAOS,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Demons5);
		
		Croyants Esprits1 = new Croyants(Origine.NEANT,"Esprits1",Dogmes.HUMAIN,Dogmes.NATURE,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Esprits1);
		Croyants Esprits2 = new Croyants(Origine.NEANT,"Esprits2",Dogmes.HUMAIN,Dogmes.CHAOS,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Esprits2);
		Croyants Esprits3 = new Croyants(Origine.NEANT,"Esprits3",Dogmes.CHAOS,Dogmes.SYMBOLES,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Esprits3);
		Croyants Esprits4 = new Croyants(Origine.NEANT,"Esprits4",Dogmes.SYMBOLES,Dogmes.NATURE,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Esprits4);
		Croyants Esprits5 = new Croyants(Origine.NEANT,"Esprits5",Dogmes.CHAOS,Dogmes.NATURE,Dogmes.MYSTIQUE,"Donne un point d'Action de la meme origine que la carte",2,1);
		listeCroyants.add(Esprits5);

		
		Croyants Travailleurs1= new Croyants(Origine.JOUR,"Travailleurs1",Dogmes.SYMBOLES,Dogmes.HUMAIN,Dogmes.CHAOS,"Emp�che une Divinit� poss�dant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour",2,2);
		listeCroyants.add(Travailleurs1);
		Croyants Alienes1= new Croyants(Origine.NEANT,"Alienes1",Dogmes.SYMBOLES,Dogmes.HUMAIN,Dogmes.CHAOS,"Emp�che une Divinit� poss�dant le Dogme Nature ou Mystique de sacrifier une de ses cartes de Croyants durant ce tou",2,2);
		listeCroyants.add(Alienes1);

		
		Croyants Travailleurs2= new Croyants(Origine.JOUR,"Travailleurs2",Dogmes.SYMBOLES,Dogmes.HUMAIN,Dogmes.NATURE,"Emp�che une Divinit� poss�dant le Dogme Chaos ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour.",2,15);
		listeCroyants.add(Travailleurs2);
		Croyants Alienes2= new Croyants(Origine.NEANT,"Alienes2",Dogmes.SYMBOLES,Dogmes.HUMAIN,Dogmes.NATURE,"Emp�che une Divinit� poss�dant le Dogme Chaos ou Mystique de sacrifier un de ses Guides Spirituels durant ce tour.",2,15);
		listeCroyants.add(Alienes2);

		
		Croyants Travailleurs3= new Croyants(Origine.JOUR,"Travailleurs3",Dogmes.MYSTIQUE,Dogmes.HUMAIN,Dogmes.CHAOS,"Vous piochez deux cartes au hasard dans la main d'une autre Divinit�.",2,3);
		listeCroyants.add(Travailleurs3);
		Croyants Alchimistes2 = new Croyants(Origine.NUIT,"Alchimistes2",Dogmes.SYMBOLES,Dogmes.NATURE,Dogmes.CHAOS,"Vous piochez deux cartes au hasard dans la main d'une autre Divinit�.",2,3);
		listeCroyants.add(Alchimistes2);
		Croyants Alienes3= new Croyants(Origine.NEANT,"Alienes3",Dogmes.MYSTIQUE,Dogmes.HUMAIN,Dogmes.CHAOS,"Vous piochez deux cartes au hasard dans la main d'une autre Divinit�.",2,3);
		listeCroyants.add(Alienes3);

		
		Croyants Ermite1 = new Croyants(Origine.JOUR,"Ermite1",Dogmes.MYSTIQUE,Dogmes.CHAOS,Dogmes.NATURE,"Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",1,4);
		listeCroyants.add(Ermite1);
		Croyants Ermite2 = new Croyants(Origine.JOUR,"Ermite2",Dogmes.MYSTIQUE,Dogmes.SYMBOLES,Dogmes.NATURE,"Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",1,4);
		listeCroyants.add(Ermite2);
		Croyants Vampire1 = new Croyants(Origine.NUIT,"Vampire1",Dogmes.HUMAIN,Dogmes.NATURE,Dogmes.SYMBOLES,"Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.a",1,4);
		listeCroyants.add(Vampire1);
		Croyants Vampire2 = new Croyants(Origine.NUIT,"Vampire2",Dogmes.MYSTIQUE,Dogmes.HUMAIN,Dogmes.CHAOS,"Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",1,4);
		listeCroyants.add(Vampire2);

		
		Croyants Integristes1 = new Croyants(Origine.JOUR,"Integristes1",Dogmes.HUMAIN,Dogmes.NATURE,Dogmes.CHAOS,"Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit la carte. La capacit� sp�ciale du sacrifice est jou�e.",1,5);
		listeCroyants.add(Integristes1);

		
		Croyants GuerriersSaints1 = new Croyants(Origine.JOUR,"GuerriersSaints1", Dogmes.MYSTIQUE,Dogmes.NATURE,Dogmes.SYMBOLES,"Un Guide Spirituel revient dans la main de sa Divinit�. Ses Croyants reviennent au centre de la table.",4,6);
		listeCroyants.add(GuerriersSaints1);

		
		Croyants Diplomates1= new Croyants(Origine.JOUR,"Diplomates1",Dogmes.HUMAIN,Dogmes.SYMBOLES,Dogmes.CHAOS,"Relancez le d� de Cosmogonie. Le tour se finit normalement sous la nouvelle influence.",4,7);
		listeCroyants.add(Diplomates1);
		Croyants Revenant1= new Croyants(Origine.NEANT,"Revenant1",Dogmes.HUMAIN,Dogmes.NATURE,Dogmes.MYSTIQUE,"Relancez le d� de Cosmogonie. Le tour se finit normalement sous la nouvelle influence.",1,7);
		listeCroyants.add(Revenant1);
		
		
		Croyants Alchimistes1 = new Croyants(Origine.NUIT,"Alchimistes1",Dogmes.SYMBOLES,Dogmes.NATURE,Dogmes.CHAOS,"Emp�che une Divinit� poss�dant le Dogme Humain ou Mystique de sacrifier une de ses cartes de Croyants durant ce tour de jeu.",2,8);
		listeCroyants.add(Alchimistes1);

		
		Croyants Lycanthropes1 = new Croyants(Origine.NUIT,"Lycanthropes1",Dogmes.NATURE,Dogmes.HUMAIN,Dogmes.CHAOS,"Retirez tous les Croyants attach�s � l'un des Guides Spirituels d'une autre Divinit�. Les Croyants reviennent au milieu de la table, le Guide Spirituel est d�fauss�.",4,9);
		listeCroyants.add(Lycanthropes1);

		
		Croyants Pillards1 = new Croyants(Origine.NUIT,"Pillards1",Dogmes.MYSTIQUE,Dogmes.NATURE,Dogmes.SYMBOLES," R�cup�rez les points d'Action d'une Divinit� n'ayant pas encore jou� durant ce tour. Les points d'Action gardent leur Origine. La Divinit� perd ses points.",4,10);
		listeCroyants.add(Pillards1);

		
		Croyants Illusionistes1 = new Croyants(Origine.NUIT,"Illusionistes1",Dogmes.SYMBOLES,Dogmes.HUMAIN,Dogmes.CHAOS," Vous b�n�ficiez de la capacit� sp�ciale de sacrifice d'une carte de Croyants appartenant � une autre Divinit�. La carte en question reste en jeu.",4,11);
		listeCroyants.add(Illusionistes1);
		
		
		Croyants Revolutionnaires1= new Croyants(Origine.NEANT,"Revolutionnaires1",Dogmes.SYMBOLES,Dogmes.HUMAIN,Dogmes.CHAOS,"Imposez le sacrifice d'une carte de Croyants � autant de Divinit�s que vous le voulez. Chaque Divinit� choisit la carte � sacrifier. Les capacit�s sp�ciales sont jou�es.",2,12);
		listeCroyants.add(Revolutionnaires1);

		
		Croyants Nihilistes1= new Croyants(Origine.NEANT,"Nihilistes1",Dogmes.SYMBOLES,Dogmes.MYSTIQUE,Dogmes.CHAOS,"Jusqu'� la fin du tour, plus aucune Divinit� ne re�oit de points d'Action.",4,13);
		listeCroyants.add(Nihilistes1);
		
		
		Croyants Alchimiste3= new Croyants(Origine.NUIT,"Alchimistes3",Dogmes.MYSTIQUE,Dogmes.NATURE,Dogmes.CHAOS,"Emp�che une Divinit� poss�dant le Dogme Humain ou Symboles de sacrifier un de ses Guides Spirituels durant ce tour de jeu.",2,14);
		listeCroyants.add(Alchimiste3);

		return listeCroyants;
	}

	/**
	 * Construit une liste de l'ensemble des cartes guides.
	 * @return une LinkedList de Guides
	 */
	public LinkedList<Guides> construireDeckGuides(){
		
		Guides Martyr1 = new Guides(Origine.JOUR, "Martyr1", Dogmes.NATURE, Dogmes.HUMAIN,"Equivalent � la pose d'une carte Apocalypse.",2,200);
		listeGuides.add(Martyr1);
		Guides Martyr2 = new Guides(Origine.NUIT, "Martyr2", Dogmes.SYMBOLES, Dogmes.HUMAIN,"Equivalent � la pose d'une carte Apocalypse.",2,200);
		listeGuides.add(Martyr2);
		Guides Martyr3 = new Guides(Origine.NEANT, "Martyr3", Dogmes.NATURE, Dogmes.CHAOS,"Equivalent � la pose d'une carte Apocalypse.",2,200);
		listeGuides.add(Martyr3);
		
		
		Guides Clerc1 = new Guides(Origine.JOUR,"Clerc1",Dogmes.HUMAIN,Dogmes.CHAOS,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc1);
		Guides Clerc2 = new Guides(Origine.NUIT,"Clerc2",Dogmes.NATURE,Dogmes.SYMBOLES,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc2);
		Guides Clerc3 = new Guides(Origine.NEANT,"Clerc3",Dogmes.NATURE,Dogmes.MYSTIQUE,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc3);
		Guides Clerc4 = new Guides(Origine.JOUR,"Clerc4",Dogmes.NATURE,Dogmes.CHAOS,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc4);
		Guides Clerc5 = new Guides(Origine.NUIT,"Clerc5",Dogmes.MYSTIQUE,Dogmes.SYMBOLES,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc5);
		Guides Clerc6 = new Guides(Origine.NEANT,"Clerc6",Dogmes.SYMBOLES,Dogmes.CHAOS,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc6);
		Guides Clerc7 = new Guides(Origine.JOUR,"Clerc7",Dogmes.MYSTIQUE,Dogmes.CHAOS,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc7);
		Guides Clerc8 = new Guides(Origine.NUIT,"Clerc8",Dogmes.NATURE,Dogmes.HUMAIN,"Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es. L'Origine des points d'Action est au choix du joueur.",2,102);
		listeGuides.add(Clerc8);
		
		
		Guides Shaman1 = new Guides(Origine.NUIT,"Shaman1",Dogmes.NATURE,Dogmes.SYMBOLES,"Sacrifie tous les Croyants d'Origine N�ant d'une Divinit� ayant le Dogme Humain. Les capacit�s sp�ciales sont jou�es normalement.",3,103);
		listeGuides.add(Shaman1);
		
		
		
		Guides Anarchiste1 = new Guides(Origine.NEANT,"Anarchiste1",Dogmes.HUMAIN,Dogmes.CHAOS,"Sacrifie un Guide Spirituel, si lui ou sa Divinit� ne croit pas au Dogme Chaos. Les capacit�s sp�ciales sont jou�es normalement.",3,104);
		listeGuides.add(Anarchiste1);
		
		
		Guides Paladin1 = new Guides(Origine.JOUR,"Paladin1",Dogmes.HUMAIN,Dogmes.MYSTIQUE,"Tous les Croyants, d'Origine Nuit ou N�ant et ayant le Dogme Nature, actuellement sur la table sont d�fauss�s. Les capacit�s sp�ciales ne sont pas jou�es.",3,105);
		listeGuides.add(Paladin1);
		
		
		Guides Ascete1=new Guides(Origine.NUIT,"Ascete1",Dogmes.HUMAIN,Dogmes.SYMBOLES,"Sacrifie 2 cartes Croyants d'une Divinit� ayant le Dogme Humain ou Symboles.Les capacit�s sp�ciales sont jou�es normalement.",1,106);
		listeGuides.add(Ascete1);
		
		
		Guides Devin1=new Guides(Origine.NEANT,"Devin1",Dogmes.NATURE,Dogmes.MYSTIQUE,"Oblige une Divinit� ayant le Dogme Nature ou Mystique � sacrifier l'un de ses Guides Spirituels.",1,107);
		listeGuides.add(Devin1);
		
		
		Guides Exorciste1 = new Guides(Origine.JOUR,"Exorciste1",Dogmes.MYSTIQUE,Dogmes.CHAOS,"Une Divinit� d'Origine Nuit ou ayant les Dogmes Mystique et Chaos reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y �taient attach�s sont d�fauss�s.",1,108);
		listeGuides.add(Exorciste1);
		
		
		Guides Sorcier1 = new Guides(Origine.NUIT,"Sorcier1",Dogmes.MYSTIQUE,Dogmes.SYMBOLES,"Echangez l'un de vos Guides Spirituels avec un d'une autre Divinit�. Vous choisissez les deux guides Spirituels en question. Les Croyants restent attach�s aux m�mes cartes.",3,109);
		listeGuides.add(Sorcier1);
		
		
		Guides Tyran1 = new Guides(Origine.NEANT,"Tyran1",Dogmes.SYMBOLES,Dogmes.CHAOS,"D�fausse tous les Croyants ayant le Dogme Mystique actuellement au centre de la table.",3,110);
		listeGuides.add(Tyran1);
		
		
		Guides Messie1 = new Guides(Origine.JOUR,"Messie1",Dogmes.HUMAIN,Dogmes.MYSTIQUE,"Le joueur pose le d� de Cosmogonie sur la face qu'il d�sire et commence un nouveau tour de jeu.",3,111);
		listeGuides.add(Messie1);
		
		return listeGuides;
	}
	/**
	 * Construit une liste de l'ensemble des cartes Deux Ex.
	 * @return une LinkedList de DeuxEx
	 */
	public LinkedList<DeusEx> construireDeckDeusEx(){
		
		DeusEx ColereDivine1 = new DeusEx(Origine.JOUR,"ColereDivine1","D�truit une carte Guide Spirituel d'Origine Nuit(si colere divine jour)/Jour(si colere divine nuit ou N�ant, dont la capacit� sp�ciale n'a pas effet. Les Croyants attach�s reviennent au centre de la table.",301);
		listeDeusEx.add(ColereDivine1);
		DeusEx ColereDivine2 = new DeusEx(Origine.NUIT,"ColereDivine2","D�truit une carte Guide Spirituel d'Origine Nuit(si colere divine jour)/Jour(si colere divine nuit ou N�ant, dont la capacit� sp�ciale n'a pas effet. Les Croyants attach�s reviennent au centre de la table.",301);
		listeDeusEx.add(ColereDivine2);
		
		
		DeusEx Stase1 = new DeusEx(Origine.JOUR,"Stase1","Prot�ge un Guide Spirituel et ses Croyants jusqu'� ce que cette carte soit annul�e ou jusqu'� la prochaine tentative d'Apocalypse.",302);
		listeDeusEx.add(Stase1);
		
		
		DeusEx OrdreCeleste1 = new DeusEx(Origine.JOUR,"OrdreCeleste1","Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� et le placez devant vous avec les Croyants qui y sont attach�s.",303);
		listeDeusEx.add(OrdreCeleste1);
		DeusEx Concentration1= new DeusEx(Origine.NEANT,"Concentration1","Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� et le placez devant vous avec les Croyants qui y sont attach�s.",303);
		listeDeusEx.add(Concentration1);
		
		
		DeusEx Fourberie1 = new DeusEx(Origine.NUIT,"Fourberie1","Sacrifiez 2 cartes Croyants d'une autre Divinit�. Les capacit�s sp�ciales ne sont pas jou�es.",304);
		listeDeusEx.add(Fourberie1);
		
		
		DeusEx Diversion1 = new DeusEx(Origine.NUIT,"Diversion1","Prenez 3 cartes dans la main d'un autre joueur et incluez-les � votre main.",305);
		listeDeusEx.add(Diversion1);
		
		
		DeusEx TrouNoir1 = new DeusEx(Origine.NEANT,"TrouNoir1","Aucun autre joueur ne gagne de points d'Action durant ce tour.",306);
		listeDeusEx.add(TrouNoir1);
		
		
		DeusEx Phoenix1 = new DeusEx(Origine.NEANT,"Phoenix1","Permet de b�n�ficier de la capacit� sp�ciale de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte",307);
		listeDeusEx.add(Phoenix1);
		
		
		DeusEx InfluenceJour1 = new DeusEx(Origine.NULL,"InfluenceJour1","Annule la capacit� sp�ciale d'une carte d'Action d'Origine Nuit ou N�ant.",308);
		listeDeusEx.add(InfluenceJour1);
		
		
		DeusEx InfluenceNuit1 = new DeusEx(Origine.NULL,"InfluenceNuit1","Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou N�ant.",309);
		listeDeusEx.add(InfluenceNuit1);
		
		
		DeusEx InfluenceNeant1 = new DeusEx(Origine.NULL,"InfluenceNeant1","Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou Nuit.",310);
		listeDeusEx.add(InfluenceNeant1);
		
		
		DeusEx InfluenceNulle1 = new DeusEx(Origine.NULL,"InfluenceNulle1","Annule la capacit� sp�ciale d'une autre carte d'Action.",311);
		listeDeusEx.add(InfluenceNulle1);
		DeusEx InfluenceNulle2 = new DeusEx(Origine.NULL,"InfluenceNulle2","Annule la capacit� sp�ciale d'une autre carte d'Action.",311);
		listeDeusEx.add(InfluenceNulle2);
		
		
		DeusEx Transe1 = new DeusEx(Origine.NULL,"Transe1","Permet de r�cup�rer les effets b�n�fiques d'une carte d'Action pos�e par une autre Divinit�. S'il s'agit d'une carte Croyants ou Guide Spirituel, vous posez la carte devant vous.",312);
		listeDeusEx.add(Transe1);
		
		
		DeusEx Miroir1 = new DeusEx(Origine.NULL,"Miroir1","Retourne les effets d'une carte d'Action sur la Divinit� qui l'a pos�e.",313);
		listeDeusEx.add(Miroir1);
		
		
		DeusEx Bouleversement1 = new DeusEx(Origine.NULL,"Bouleversement1","Relancez le d� de Cosmogonie. Le tour se finit normalement sous la nouvelle influence.",7);
		listeDeusEx.add(Bouleversement1);
		
		
		DeusEx Inquisition1 = new DeusEx(Origine.NULL,"Inquisition1","Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, sur Nuit le votre est sacrifi�, sur N�ant rien ne se passe.",314);
		listeDeusEx.add(Inquisition1);
		
		return listeDeusEx;
	}
	/**
	 * Construit une liste de l'ensemble des cartes Apocalypse.
	 * @return une LinkedList de Apocalypses
	 */
	public LinkedList<Apocalypses> construireDeckApo(){
		
		Apocalypses Apocalypse1 = new Apocalypses(Origine.JOUR,"Apocalypse1",200);
		listeApo.add(Apocalypse1);
		Apocalypses Apocalypse2 = new Apocalypses(Origine.NUIT,"Apocalypse2",200);
		listeApo.add(Apocalypse2);
		Apocalypses Apocalypse3 = new Apocalypses(Origine.NEANT,"Apocalypse3",200);
		listeApo.add(Apocalypse3);
		Apocalypses Apocalypse4 = new Apocalypses(Origine.NULL,"Apocalypse4",200);
		listeApo.add(Apocalypse4);
		Apocalypses Apocalypse5 = new Apocalypses(Origine.NULL,"Apocalypse5",200);
		listeApo.add(Apocalypse5);
		
		return listeApo;
	}
	/**
	 * Construit une liste de l'ensemble des cartes de divinit�s.
	 * @return une LinkedList de Divinites
	 */
	public LinkedList<Divinites> construireDeckDivinites(){
		
		Divinites Brewalen = new Divinites(Origine.JOUR, "Brewalen", Dogmes.NATURE, Dogmes.HUMAIN, Dogmes.MYSTIQUE, "Premier enfant du Jour, Brewalen cherche � repr�senter une certaine image de l'harmonie, et tente de mettre en place un statu quo entre les Divinit�s",401);
		listeDivinites.add(Brewalen);
		Divinites Drinded = new Divinites(Origine.JOUR,"Drinded", Dogmes.HUMAIN, Dogmes.NATURE, Dogmes.SYMBOLES, "D�fenseur des hommes, quelles que soient leurs croyances, Drinded prot�ge les chefs religieux",402 );
		listeDivinites.add(Drinded);
		Divinites Yarstur = new Divinites(Origine.JOUR,"Yarstur",Dogmes.CHAOS,Dogmes.MYSTIQUE,Dogmes.SYMBOLES,"Dernier pur du jour, Yarstur combat le N�ant sous toutes ses formes",403);
		listeDivinites.add(Yarstur);
		Divinites Killinstred = new Divinites(Origine.NUIT,"Killinstred",Dogmes.NATURE,Dogmes.MYSTIQUE,Dogmes.CHAOS,"Divinit� machiav�lique et manipulatrice, Killinstred cherche � influencer et contr�ler ses ennemis",404);
		listeDivinites.add(Killinstred);
		Divinites Llewella = new Divinites(Origine.NUIT,"Llewella",Dogmes.NATURE,Dogmes.MYSTIQUE,Dogmes.CHAOS,"..",405);
		listeDivinites.add(Llewella);
		Divinites PuiTara = new Divinites(Origine.NUIT,"PuiTara",Dogmes.NATURE,Dogmes.MYSTIQUE,Dogmes.SYMBOLES,"Pui-Tara est la Divinit� sur laquelle l'influence de la Nuit s'est faite la plus forte",406);
		listeDivinites.add(PuiTara);
		Divinites Gwenghelen = new Divinites (Origine.AUBE,"Gwenghelen",Dogmes.HUMAIN,Dogmes.MYSTIQUE,Dogmes.SYMBOLES,"Premi�re Divinit� � recevoir l'influence du N�ant, Gwenghelen est celle qui en a re�u le plus de puissance.",407);
		listeDivinites.add(Gwenghelen);
		Divinites Shingva = new Divinites (Origine.AUBE,"Shingva",Dogmes.HUMAIN,Dogmes.MYSTIQUE,Dogmes.CHAOS,"Perverse et retorse, Shingva est une Divinit� que toutes les autres d�testent",408);
		listeDivinites.add(Shingva);
		Divinites Gorpa = new Divinites (Origine.CREPUSCULE,"Gorpa",Dogmes.HUMAIN,Dogmes.SYMBOLES,Dogmes.CHAOS,"Divinit� joueuse et espi�gle, Gorpa aime g�ner ses cons�urs dans leur recherche de puissance",409);
		listeDivinites.add(Gorpa);
		Divinites Romtec = new Divinites (Origine.CREPUSCULE,"Romtec",Dogmes.NATURE,Dogmes.HUMAIN,Dogmes.CHAOS,"Romtec est une Divinit� individualiste, pour qui chaque �tre vivant doit garder son libre arbitre",410);
		listeDivinites.add(Romtec);

		return listeDivinites;
	}


	public LinkedList<Croyants> getListeCroyants() {
		return listeCroyants;
	}


	public void setListeCroyants(LinkedList<Croyants> listeCroyants) {
		this.listeCroyants = listeCroyants;
	}


	public LinkedList<Guides> getListeGuides() {
		return listeGuides;
	}


	public void setListeGuides(LinkedList<Guides> listeGuides) {
		this.listeGuides = listeGuides;
	}


	public LinkedList<Apocalypses> getListeApo() {
		return listeApo;
	}


	public void setListeApo(LinkedList<Apocalypses> listeApo) {
		this.listeApo = listeApo;
	}


	public LinkedList<DeusEx> getListeDeusEx() {
		return listeDeusEx;
	}


	public void setListeDeusEx(LinkedList<DeusEx> listeDeusEx) {
		this.listeDeusEx = listeDeusEx;
	}


	public LinkedList<Divinites> getListeDivinites() {
		return listeDivinites;
	}


	public void setListeDivinites(LinkedList<Divinites> listeDivinites) {
		this.listeDivinites = listeDivinites;
	}





}
