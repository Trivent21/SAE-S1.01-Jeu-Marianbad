/**
 * SAE Jeu de Marienbad
 * @author Matéo FLEJOU
 * principal -  Appelle la Méthode de début 
 */
class Marienbad {
	void principal(){	
		///Mettez soit lancerJeu ou lancerTest en commantaire	
		lancerJeuIA();
		///lancerTestIA();
		
	}
	/**
	* Méthode lancerJeu 
	* Principe - demande des valeurs aux l'utilisateurs,
	* Nom + taille du tableau souhaite
	* Appelle et regroupe les Méthodes pour lancer le jeu 
	*/
	void lancerJeuIA(){	
		String player1 =  SimpleInput.getString("Name J1 : ");
		player1 = "J1 "+player1;
		String player2 = "J2 IA";	
		int taille = SimpleInput.getInt
		("Combien de ligne ? (limite a 20) :");
		while ( taille <= 2 ) {
			taille = SimpleInput.getInt
				("Combien de ligne ? (limite a 20) :");
		}
		int[] plateau = creeTableau(taille);
		int totale = totalPlateau(plateau);
		afficheBaton(plateau);
		tourChoix(plateau,totale,player1,player2);
	}
	/**
	 * Méthode creeTableau - appelle dans lancerJeu(); 
	 * @param a est la taille du tableau demande
	 * @return plateau (le tableau demande)
	 * Principe - Fabrique le tableau { 1, 3, 5, 7,...
	 * jusqu'à attiendre la taille souhaite par les utilisateurs
	 */
	int[] creeTableau (int a) {		
		int[] plateau = new int [a];
		int baton = 1;
		int total = 0;	
	    for (int i = 0; i < plateau.length && i <= 20; i++ ) {
			plateau[i] = baton;
			baton = baton + 2;
		}	
		return plateau;
	}
	/**
	 * Méthode totalPlateau - appelle dans lancerJeu(); 
	 * @param plateau - Tableau crée par creeTableau();
	 * Principe - Calcule le totale du tableau en addictionnant ces indices
	 * @return total
	 */
	int totalPlateau(int[] plateau) {
		int total = 0;	
		for (int i = 0; i < plateau.length && i <= 20; i++ ) {
			total = total + plateau[i];
		}	
		return total;
	}
	/**
	 * Méthode afficheBaton - appelle dans lancerJeu()  
	 * et utilise a chaque fin tour d'un joueur ;
	 * @param plateau - Tableau crée par creeTableau();
	 * Principe -  affiche le plateau de jeu 
	 */	
	void afficheBaton(int[] plateau) {
		int a = 0; 
		for ( int i = 0; i < plateau.length && i <= 20; i++) {
			System.out.print(i + " : ");
			for ( int i2 = 0; i2 < plateau[a] ; i2++) {
				System.out.print ("| ");		
			}
			System.out.println();
			a++;		
		}		
	}
	/**
	 * Méthode tourChoix - appelle dans lancerJeu();
	 * @param plateau - Tableau crée par creeTableau();
	 * @param total -  Résultat de la Méthode totalPlateau(); 
	 * @param a - Nom de l'utilisateur J1 
	 * @param b - IA
	 * Principe - Qui commence entre les deux joueur 
	 */
	void tourChoix (int[] plateau, int total, String J1, String J2) {		
		char res = SimpleInput.getChar
			("Veux-tu laisse l'IA commence ? [Y/N] : ");
		while ( res != 'Y' && res != 'N') {
			res = SimpleInput.getChar
				("Veux-tu laisse l'IA commence ? [Y/N] : ");
		}
		if (res == 'N') {
			tour(plateau,total,J1,J2,res);	
		}
		if ( res == 'Y'){
			tour(plateau,total,J2,J1,res);			
		}
	}
	/**
	 * Méthode tour - appelle dans tourChoix();
	 * @param plateau - Tableau crée par creeTableau();
	 * @param total -  Résultat de la Méthode totalPlateau(); 
	 * @param a - Nom de l'utilisateur J1 ou IA 
	 * @param b - Nom de l'utilisateur J1 ou IA
	 * @param res - Enclenche le bon enchainement de tour
	 * Principe - Gestion des tours et de la fin la partie. 
	 * tour dépend des autres resultats de méthode = impossible de test
	 * De plus, tour est qu'une simple passerelle pour la méthode retireBaton();
	 */
	void tour (int[] plateau, int total, String a, String b, char res) {
		int tour = 1;
		if ( res == 'N') {
			while ( total > 0) {
				if (tour == 1) {
						total = retireBaton(plateau,total,a);
						tour--;		
				} else { 
						total = retireBatonIA(plateau,total,b);
						tour++;						
				}			
			}
		} else {
			while ( total > 0) {
				if (tour == 1) {
						total = retireBatonIA(plateau,total,a);
						tour--;		
				} else { 
						total = retireBaton(plateau,total,b);
						tour++;						
				}		
			}
		}	
	}
	/**
	 * Méthode retireBaton - appelle dans tour(); 
	 * @param plateau - Tableau crée par creeTableau();
	 * @param total -  Résultat de la Méthode totalPlateau(); 
	 * @param a - Nom de l'utilisateur
	 * @return totale - reclacule Pour bon fonctionement de la méthode tour();
	 * Principe - Permet a l'utilisateur de retire des Batôns d'un colone de son choix
	 * Réaffiche la plateau de jeu avec les batôns enleve avec affiche(); 
	 * Sécurite anti -1 ou 0 ou chiffre plus grand que tableau 
	 */
	int retireBaton (int[] plateau, int total, String a){
		int ligne = 0;
		int col = 0;
		int prend = 0;
		do{
			ligne = SimpleInput.getInt
				("Choisi donc une ligne " + a + ": ");
			prend = SimpleInput.getInt
				("Choisi nombre de baguette a enleve " + a + ": ");
			if ( prend <= 0 || ligne <= -1 || ligne > plateau.length-1){
				System.out.println
					("limite : ligne > -1 et ligne < aux nombre de ligne et nombre baton a enleve > 0");
				ligne = 0;
			}
			if ( prend > plateau[ligne]){
				System.out.println
					("pas un chiffre au dessus du nombre de baguette");
			}
		} while 
		(ligne < 0 || prend <= 0 || prend > plateau[ligne] || ligne > plateau.length-1);			
			if (prend > 0) {
				plateau[ligne] = plateau[ligne] - prend;
				total = total - prend; 
			}
		if (total <= 0) {
			System.out.println("Win :" + a);
		} else {
			afficheBaton(plateau);
		}
		return total;
	}
	/**
	* Méthode retireBatonIA - appelle dans tour();
	* @param plateau - Tableau crée par creeTableau();
	* @param total -  Résultat de la Méthode totalPlateau(); 
	* @param a - IA
	* @return totale - reclacule Pour bon fonctionement de la méthode tour();
	* Principe - Permet a l'IA a choisir sa tactique pour le tour
	* Réaffiche la plateau de jeu avec les batôns enleve avec affiche();  
	**/
	int retireBatonIA (int[] plateau, int total, String a){
		boolean impair = false;
		System.out.println( a + " vas joue");
		impair = determineStrate(impair,plateau);
		if (!impair) {
			plateau = pairPartie(plateau);	
		} else {
			plateau = impairPartie(plateau);
		}
		total = totalPlateau(plateau);
		if (total <= 0) {
			System.out.println("Win :" + a);
		} else {
			afficheBaton(plateau);
		}
		return total;	
		
	}
	/**
	* Méthode dertermineStarte - appelle dans retireBatonIA();
	* @param impair -  facteur determinant la stratégie  
	* @param plateau -  plateau de jeu 
	* principe - detecte les impairs = true 
	* si il y a pas de impair = false
	**/
	boolean determineStrate (boolean impair,int [] plateau){
		int [] tab = tabTotalBinaire(plateau);
		
		for(int k = 0 ; k < tab.length && !impair ; k++){
			if (tab[k] % 2 != 0)  {
				impair = true;
			}
		}
		return impair;
	}
	/**
	* Méthode impairPartie appelle dans retireBatonIA();
	* @param plateau - Tableau crée par creeTableau();
	* @return plateau - tableau modifie;
	* Principe - applique la strategie de rendre quelque chose de pair à l'adversaire 
	**/
	int [] impairPartie (int [] plateau) {
		boolean win = false;
		int i = 0;
		int reducteur = 0; 
		int [] copiePlateau = new int [plateau.length];
		for(int k = 0 ; k < plateau.length ; k++){
           copiePlateau[k] = plateau[k];
		}
		
		while ( i < plateau.length && !win) {
			if (copiePlateau[i] == 0) {
				reducteur = 0;
				copiePlateau[i] = plateau[i]; 
				i++;		
			} else {
				reducteur++;
				copiePlateau[i] = copiePlateau[i]-1;
				
				int [] tab = tabTotalBinaire(copiePlateau);
			
				int [] tabPairImpair = valeurPairImpair(tab);
			
				win = sitGagnant(tabPairImpair);
			}
		}
		if (win) {
			plateau[i] = plateau[i] - reducteur;
		}
		return plateau;
	}
		
	/**
	* Méthode - tabTotalBinaire() appelle dans impairPartie();
	* @param plateau - Tableau crée par creeTableau();
	* @return tab1 -  retroune un tableau compose d'un resultat binaire
	* A l'aide des méthode cacule le totale binaire 
	* et repartie dans un tableau
	* Exemple :  si totale binaire est 30 le tableau sera {3,0}
	**/
	int [] tabTotalBinaire (int [] plateau) {
		int taille  = 0;
		int binaireTotal = totalBinaire(plateau);	
		int [] randomTab = new int [100];									///tab de stockage pour ensuite mettre à la bonne taille le vrais tableau
									 
		String number = String.valueOf(binaireTotal);                  		///Convertissement en chaine de caractére (ASCII) pour pouvoir prendre chaque partie du nombre
		for(int i = 0; i < number.length(); i++) {
			int j = Character.digit(number.charAt(i), 10);              	///si c'est une chiffre ASCII, renvoie la valeur numérique du caractère String (ASCII -> chifffre)																	///Don
			randomTab[i] = j; 
			taille++;
		}
		int [] tab1 = new int [taille];									
		for(int k = 0 ; randomTab[k] != 0 ; k++){
            tab1[k] = randomTab[k];
        }
		return tab1;
    }
    /**
	 * Méthode - tabinaire(); appelle dans totalBinaire();
	 * @param plateau - Le plateau de jeu mis en place
	 * @return tab  -  tableau binaire
	 * Principe - convertie de entier à binaire
	 */	
    String[] tabBinaire(int [] plateau){
        String[]tab = new String[plateau.length];
        
        for(int k = 0 ; k < plateau.length ; k++){
            tab[k] = Integer.toBinaryString(plateau[k]);                	///renvoie une représentation sous forme de chaîne de l'argument entier en base binaire 2
        }
         return tab;
    }
	/**
	 * Méthode - totalBinaire(); appelle dans tabTotalBinaire();
	 * @param plateau - Le plateau de jeu mis en place
	 * @return a  - valeur totale binaire du plateau jeu
	 * Principe - cacule le totale binaire d'un tableau binaire en string
	 */	
    int totalBinaire (int [] plateau){
        int a = 0;
        String[]tb = tabBinaire(plateau);
        
        for (int k = 0; k < tb.length; k++){
            a = a + Integer.parseInt(tb[k]);                            	///obtenir une données primitif d'une certaine chaîne de string						
        }
        return a;
    }
	
	
	/**
	* Méthode - valeurPairImpair(); appelle depuis impairPartie();
	* @param tab - tableau du resultat de tabTotalBinaire();
	* @return tab1 - tableau contenant soit 0 soit 1 possede la taille de tab
	* Principe - detecte les chiffres impair puis la note dans un autre tableau
	* si pair 0 si impair 1 
	**/
	int [] valeurPairImpair (int [] tab) {
		int i = 0;
		int [] tab1 = new int [tab.length];
		while ( i < tab.length) {
			if (tab[i] % 2 == 0){
				i++;
			} else {
				tab1[i] = 1;
				i++;
			}
		}
		return tab1;
	}
	/**
	* Méthode - sitGagnant(); appelle depuis impairPartie();
	* @param tabPairImpair - tableau tiré de valeurPairImpair
	* @return win - si il ne detecte pas de 1 alors vrais sinon faux
	* Principe -  savoir sur les coups est gagnant pour lui ou non
	* Sinon recommence le programme impairePartie(); et cherche une autre solution
	**/
	boolean sitGagnant (int [] tabPairImpair) {
		int i = 0;
		boolean win = true;
		while ( i < tabPairImpair.length && win) {
			if (tabPairImpair[i] == 0){
				i++;
			}  else if (tabPairImpair[i] == 1 ){
				win = false; 
			}
		}
		return win;
	}
	/**
	* Méthode - pairpartie();  appelle dans retireBatonIA();
	* @param plateau - le tableau du jeu actuelle 
	* @return - modification du tableau l'IA a joué
	* Principe - IA choisi de façon aleatoire sachant qu'il a un mains pair
	* et qu'il es donc perdant
	**/
	int [] pairPartie (int [] plateau) {
		///Choix ligne
		double ligne = Math.random() * ( plateau.length );                  ///Choisi un nombre random en fonction du nombre de ligne 
		int iLigne = (int) Math.round(ligne);								///Arrondisement de ce chiffre random et transformation en int pour l'exploite
			
		while ( iLigne == plateau.length || plateau[iLigne] == 0 ) {		///Boucle necessaire pour évite une sortie de tableau
			ligne = Math.random() * ( plateau.length );
			iLigne = (int) Math.round(ligne); 		
		}	
		///Choix nombre baton 																
		double prend = Math.random() * ( plateau[iLigne] );
		int iPrend = (int) Math.round(prend);
		while ( iPrend == 0 ) {
			prend = Math.random() * ( plateau[iLigne]  );
			iPrend = (int) Math.round(prend);
		}	
		plateau[iLigne] = plateau[iLigne] - iPrend;
		
		return plateau;
	}
	
	
	void lancerTestIA () {	
        
        System.out.println("*********TEST IA RETIRE BATON*********"); 	
		testRetireBatonIA();
		System.out.println("*****TEST PAIR PARTIE*****");
		testePairPartie();
		System.out.println("*****TEST IMPAIR PARTIE*****");
		testeImpairPartie();
		System.out.println("*****TEST CONVERSION BINAIRE ET TOTAL BINAIRE*****");
		testeTabBinaire();
		System.out.println("*****TEST DETERMINE STRATEGIE*****");
		testeDetermineStrat();
		System.out.println("*****TERMINE*****");
	}
	
	/**
	 * Méthode - testRetireBaton(); appelle dans lancerTest();
	 * aucun paramétre
	 * Principe - laisse utilisateur selection un tableau,
	 * Affiche avec les méthode affichage (afficheBaton(),afficheTab()).
	 * Utilise la méthode totalPlateau() pour cacule le totale du tableau utilisateur.
	 * Vérife que la Méthode reduit bien le totale du tableau a
	 * prés l'intervention de retireBatonIA peut importe le choix
	 */
	void testRetireBatonIA() {
		int totale1 = 0;
		int totale2 = 0;
		char res = 0;
		boolean impair = false;	
		String J1 = "Joueur X";
		int [] t = tableauTest();
		int [] tab = tabTotalBinaire(t);
		impair = determineStrate(impair,t);
		if (!impair) {
			System.out.println("Choix - Pair partie");	
		} else {
			System.out.println("Choix - impair partie");	
		}
		System.out.println("Version baton :");	
		afficheBaton(t);
		System.out.println("Version tableau:");	
		afficheTab(t);
		System.out.println("Version tableau totale binaire");
		afficheTab(tab);
		totale1 = totalPlateau(t);
		System.out.println("totale de baton avant : " + totale1);
		totale2 = retireBatonIA(t,totale1,J1);
		afficheTab(t);
		System.out.println("totale de baton apres : " + totale2);
		System.out.println("Version tableau totale binaire apres changement");
		tab = tabTotalBinaire(t);
		afficheTab(tab);
		if (totale1 > totale2){
			System.out.println("Test OK pour diminution");
		} else { 
			System.out.println("Test ERREUR pour diminution");	
		}
	}
	/**
	 * Méthode teste -  testePairPartie(); 
	 * aucun paramétre 
	 * Principe - Vérifie qu'il applique bien une diminution aléatoire 
	 */
	void testePairPartie(){
		char res = 0;
		System.out.println
			("Choisi un tableau avec minimum un indice superieur a 0");
		int [] tabTest = tableauTest();
		System.out.println("Votre tableau");
		afficheTab(tabTest);
		pairPartie(tabTest);
		System.out.println
			("Apres application de la tactique partie pair");
		afficheTab(tabTest);
		res = SimpleInput.getChar("Un indice a diminue ? [Y/N] : "); 
		okOrNo(res);	
	}
	/**
	 * Méthode teste -  testeImpairPartie();
	 * aucun paramétre 
	 * Principe - Vérifie qu'il applique bien une diminution 
	 * et que le resultat binaire du tableau soit bien pair à la fin 
	 * de la diminution
	 */
	void testeImpairPartie(){
		char res = 0;
		System.out.println
			("Choisi un tableau avec minimum un indice superieur a 0");
		int [] tabTest = tableauTest();
		System.out.println("Votre tableau");
		afficheTab(tabTest);
		int [] tabTestBinaire = tabTotalBinaire(tabTest);
		System.out.println("Resultat binaire de votre tableau");
		afficheTab(tabTestBinaire);
		impairPartie(tabTest);
		System.out.println
			("Apres application de la tactique partie impair");
		afficheTab(tabTest);
		tabTestBinaire = tabTotalBinaire(tabTest);
		System.out.println("Resultat binaire de votre tableau modifie");
		afficheTab(tabTestBinaire);
		res = SimpleInput.getChar
			("les indices du tableau binaire sont tous pair ? [Y/N] : "); 
		okOrNo(res);	
		
	}
	/**
	 * Méthode teste -  testeTabBinaire();
	 * aucun paramétre 
	 * Principe - Vérifie qu'il transforme bien les valeur d'un tableau 
	 * en valeur binaire base 2 correspondant à la valeur du tableau.
	 * Continue sur le testeTotalBinaire() avec le même tableau.
	 */
	void testeTabBinaire () {
		char res = 0;
		System.out.println
			("Choisi un tableau avec minimum un indice superieur a 0");
		int [] tabTest = tableauTest();
		System.out.println("Votre tableau");
		afficheTab(tabTest);
		String [] tabTestString = tabBinaire(tabTest);
		afficheTabString(tabTestString);
		System.out.println
			("les indices sont censes etre en binaire base 2 maintenant");
		res = SimpleInput.getChar
			("et le binaire doit etre egale au valeur tableau une convertie ? [Y/N] : "); 
		okOrNo(res);
		if ( res == 'Y') {
			testeTotalBinaire(tabTest);
		}
	}
	/**
	 * Méthode teste -  testeTotalBinaire();
	 * aucun paramétre.
	 * Principe - Vérifie qu'il addictione bien les valeur de tableau binaire. 
	 */
	void testeTotalBinaire (int [] tabTest) {
		int a = totalBinaire(tabTest);
		char res = SimpleInput.getChar
			(a + " Si c'est l'addiction de tout les valeur de votre tableau binaire alors Y [Y/N] : "); 
		okOrNo(res);
	}
	/**
	 * Méthode teste -  testeDetermineStrat();
	 * aucun paramétre 
	 * Principe - Vérifie qu'il detecte bien les impaires.
	 */
	void testeDetermineStrat () {
		System.out.println
			("Choisi un tableau avec minimum un indice superieur a 0");
		int [] tabTest = tableauTest();
		boolean res = false;
		res = determineStrate(res,tabTest);
		char rep = 0;
		if (res) {
			rep = SimpleInput.getChar
				("Il y a aux moins une valeur impair dans votre tableau [Y/N] : ");
			okOrNo(rep);
		} else {
			rep = SimpleInput.getChar
				("il y a que des valeurs pair dans votre tableau [Y/N] : ");
			okOrNo(rep);
		}
	}
		///Méthode utilitaire teste (répete souvent plusieurs fois)
		
	/**
	* Methode okOrNo - Si l'utilisateur ne ment pas indique si test et bon ou non
	* @param a - reponse de l'utilisateur
	* Historique de utilisation - testeDetermineStrat(); - testeTotalBinaire(); -
	* testePairPartie();
	**/
	void okOrNo(char a) {
		boolean end = true;
		char res = a;
		while ( res != 'Y' && res != 'N') {
			res = SimpleInput.getChar
				("Recommence la prise [Y/N] : ");
		}
		if (res == 'Y') {
			System.out.println("Test OK");
			end = false;
		}
		if ( res == 'N'){
			System.out.println("Test ERREUR");				
		}
	}	
    /**
	* Methode afficheTab - Affiche un tableau 
	* @param Tab1 -  tableau choisie
	* Historique de utilisation - testeTabBinaire(); - testePairPartie();
	* testRetireBatonIA();
	**/
	void afficheTab(int [] tab1) {
		System.out.print("Le tableau { ");
		for ( int k = 0; k < tab1.length; k++) {
			if ( k >= 0 && k != tab1.length - 1){
				System.out.print(tab1[k]+", ");
			} else {
				System.out.print(tab1[k]);
			}			
		}
		System.out.println(" }");
	}
    /**
	* Methode afficheTabString - Affiche un tableau de String
	* @param Tab1 -  tableau string choisie 
	* Historique de utilisation - testeTabBinaire();
	**/
	void afficheTabString(String [] tab1) {
		System.out.print("Le tableau { ");
		for ( int k = 0; k < tab1.length; k++) {
			if ( k >= 0 && k != tab1.length - 1){
				System.out.print(tab1[k]+", ");
			} else {
				System.out.print(tab1[k]);
			}			
		}
		System.out.println(" }");
	}
	/**
	* Methode tableauTest - remplie un tableau test avec des valeurs,
	* choisie par l'utilisateur
	* @param tab -  tableau de 20 (limite)
	* Historique de utilisation - testeDetermineStrat(); - 
	**/
	int [] tableauTest () {
		int taille = SimpleInput.getInt("Taille du tableau ? : ");
		while (taille > 20 || taille <= 0) { 	
			taille = SimpleInput.getInt("Taille du tableau ? : ");
			}
		int [] tab = new int [taille];
		int val = 0;
        int nbVal = 0;
		while (val > -1 && nbVal < tab.length) { 	
			val = SimpleInput.getInt
				("choisir une valeur (un negatif arrete la prise) : ");
			if (val > -1) { 	
				tab[nbVal] = val;
				nbVal = nbVal + 1;
			}
		}
		return tab;			
	}
	
}
