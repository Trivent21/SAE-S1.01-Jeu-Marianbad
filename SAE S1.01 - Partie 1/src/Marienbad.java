/**
 * SAE Jeu de Marienbad
 * @author Matéo FLEJOU
 * principal -  Appelle la Méthode de début 
 */
class Marienbad {
	void principal(){	
		///Met soit lancerJeu ou lancerTest en commantaire	
		lancerJeu();
		///lancerTest();		
	}
	/**
	* Méthode lancerJeu 
	* Principe - demande des valeurs aux l'utilisateurs,
	* Nom + taille du tableau souhaite
	* Appelle et regroupe les Méthodes pour lancer le jeu 
	*/
	void lancerJeu(){	
		String player1 =  SimpleInput.getString("Name J1 : ");
		player1 = "J1 "+player1;
		String player2 =  SimpleInput.getString("Name J2 : ");
		player2 = "J2 "+player2;
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
	 * Méthode afficheBaton - appelle dans lancerJeu();
	 * @param plateau - Tableau crée par creeTableau();
	 * Principe -  demande les données a l'utilisateur si veux choisir
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
	 * Méthode tourChoix - appelle dans tourChoix();
	 * @param plateau - Tableau crée par creeTableau();
	 * @param total -  Résultat de la Méthode totalPlateau(); 
	 * @param a - Nom de l'utilisateur J1 
	 * @param b - Nom de l'utilisateur J2
	 * Principe - Qui commence entre les deux joueur 
	 */
	void tourChoix (int[] plateau, int total, String J1, String J2) {		
		char res = SimpleInput.getChar
			("Qui commence " + J1 + " ou " + J2 + " - J");
		while ( res != '1' && res != '2') {
			res = SimpleInput.getChar
				("Qui commence " + J1 + " ou " + J2 + ": ");
		}
		if (res == '1') {
			tour(plateau,total,J1,J2);	
		}
		if ( res == '2'){
			tour(plateau,total,J2,J1);			
		}
	}
	/**
	 * Méthode tourChoix - appelle dans tourChoix
	 * @param plateau - Tableau crée par creeTableau();
	 * @param total -  Résultat de la Méthode totalPlateau(); 
	 * @param a - Nom de l'utilisateur J1 ou J2
	 * @param b - Nom de l'utilisateur J2 ou J2
	 * Principe - Gestion des tours simplde passerelle non testable 
	 */
	void tour (int[] plateau, int total, String a, String b) {
		int tour = 1;
		while ( total > 0) {
			if (tour == 1) {
					total = retireBaton(plateau,total,a);
					tour--;		
			} else { 
					total = retireBaton(plateau,total,b);
					tour++;						
			}
		}
	}
	/**
	 * Méthode retireBaton - appelle dans tour(); 
	 * @param plateau - Tableau crée par creeTableau();
	 * @param total -  Résultat de la Méthode totalPlateau(); 
	 * @param a - Nom de l'utilisateur J1 soit de l'utilisateur J2
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
				("limite : ligne > -1 et ligne < aux nombre de ligne et prend > 0 ");
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
	
	///Test humain - Il faut donc la coppération de l'utilisateur pour realise ce test
	
	/**
	 * Méthode - lancerTest(); appelle dans principal();
	 * Principe - Regroupe les tests et les lances
	 */		
	void lancerTest () {
		int val = 0;	
        
        System.out.println("*********TEST AFFICHE*********"); 	
		testAffiche();
		System.out.println("*****TEST CREE TABLEAU*****");
		val = SimpleInput.getInt
				("choisir la taille du plateau (limite a 20) : ");
		testCreeTableau(val);
		System.out.println("*********TEST TOTALE*********");	
		testTotalPlateau();
		System.out.println("******TEST RETIRE BATON******");
		testRetireBaton();		
	}
	/// Méthode des différents tests 
	
	/**
	 * Méthode - testAffiche(); appelle dans lancerTest();
	 * @param tab - tableau vide de 20
	 * Principe - test tous les affichages possible. 
	 * Choisi par l'utilisateur.
	 */		
	void testAffiche () {
		char res = 0;
		boolean end = true;
		int [] tab = tableauTest();	
		afficheTab(tab);
		afficheBaton(tab);		
		res = SimpleInput.getChar
				("les valeur correspond a l'affichage [Y/N] : ");
		end = okOrNo(res);		
		System.out.println("*******TEST AFFICHE FIN*******");
	}
	/**
	 * Méthode - testCreeTableau(); appelle dans lancerTest();
	 * @param val - taille choisi par l'utilisateur dans lancerTest();
	 * Principe - test l'ajoute des batons dans un tableau de X taille,  
	 * choisi par l'utilisateur.
	 */
	void testCreeTableau (int val) {
		int[] plateau = new int [val];
		char res = 0;
		boolean end = false;
		int baton = 1;
		int total = 0;	
		afficheTab(plateau);
		res = SimpleInput.getChar
				("la valeur correspond a l'affichage [Y/N] : "); 
		end = okOrNo(res);
		if (end == false) {
			for (int i = 0; i < plateau.length && i <= 20; i++ ) {
				plateau[i] = baton;
				baton = baton + 2;
			}
			afficheTab(plateau);
			System.out.println
			("Plus 2 a chaque indice en commence par 1");
			res = SimpleInput.getChar
					("les valeur correspond a la condition ? [Y/N] : ");
			end = okOrNo(res); 
			System.out.println("***TEST CREE TABLEAU FIN***");
		}
		
	}
	/**
	 * Méthode - testTotalPlateau(); appelle dans lancerTest();
	 * @param t -  tableau vide de 20.
	 * Principe - laisse utilisateur selection un tableau,
	 * Cacule le totale en même temps, 
	 * compare le resultat avec de la méthode TotalPlateau();  
	 */
	void testTotalPlateau () {
		int val = 0;
        int nbVal = 0;
        int cumul = 0;
        int res = 0;
        int taille = SimpleInput.getInt("Taille du tableau ? : ");
		int [] tab = new int [taille];
		while (val > -1 && nbVal < tab.length) { 	
			val = SimpleInput.getInt
				("choisir une valeur (un negatif arrete la prise) : ");
			if (val > -1) { 	
				tab[nbVal] = val;
				nbVal = nbVal + 1;
				cumul = cumul + val;
			}				
		}
		afficheTab(tab);
		res = totalPlateau(tab);
		if (res == cumul) {
			System.out.println("Cumul = " + cumul);
			System.out.println("Test OK");
		} else {
			System.out.println("Cumul = " + cumul);
			System.out.println("Test ERREUR");		
		}
		System.out.println("***TEST TOTALE FIN***");					
	}
	/**
	 * Méthode - testRetireBaton(); appelle dans lancerTest();
	 * Principe - laisse utilisateur selection un tableau,
	 * Affiche avec les méthode affichage (afficheBaton(),afficheTab()).
	 * Utilise la méthode totalPlateau() pour cacule le totale du tableau utilisateur.
	 * Vérife que la Méthode reduit bien le totale du tableau aprés l'intervention de retireBaton
	 */
	void testRetireBaton() {
		int totale1 = 0;
		int totale2 = 0;
		char res = 0;
		boolean end;	
		String J1 = "Joueur X";
		int [] t = tableauTest();
		System.out.println("Version baton :");	
		afficheBaton(t);
		System.out.println("Version tableau:");	
		afficheTab(t);
		totale1 = totalPlateau(t);
		System.out.println("totale de baton avant : " + totale1);
		totale2 = retireBaton(t,totale1,J1);
		afficheTab(t);
		System.out.println("totale de baton apres : " + totale2);
		if (totale1 > totale2){
			System.out.println("Test OK pour diminution");
			res = SimpleInput.getChar
					("La bonne colonne a change ? [Y/N] : "); 
			end = okOrNo(res);
		} else { 
			System.out.println("Test ERREUR pour diminution");	
		}
	}	 	

	///Méthode utilitaire test (répete souvent plusieurs fois) 
	
	/**
	* Methode okOrNo - Si l'utilisateur ne ment pas indique si test et bon ou non
	* @param a - reponse de l'utilisateur
	* Historique de utilisation - testCreeTableau(); - testAffiche();
	**/
	boolean okOrNo(char a) {
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
		return end;
	}
	/**
	* Methode affiche - Affiche un tableau 
	* @param Tab1 -  tableau choisie
	* Historique de utilisation - testAffiche(); - testRetireBaton();
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
	* Methode tableauTest - remplie un tableau test avec des valeurs,
	* choisie par l'utilisateur
	* @param tab -  tableau de 20 (limite)
	* Historique de utilisation - testAffiche(); - testRetireBaton(); -
	* testTotalPlateau(); - testCreeTableau();
	**/
	int [] tableauTest () {
		int taille = SimpleInput.getInt("Taille du tableau ? : ");
		while (taille > 20 || taille <= 0) { 	
			SimpleInput.getInt("Taille du tableau ? : ");
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
