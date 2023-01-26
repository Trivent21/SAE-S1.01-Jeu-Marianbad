/*
 * Trouve le entier le plus grand entre 3 entier
* @author M.Adam
*/
class Syntaxe{
   void principal(){
	   
	int val1;
	int val2;
	int val3;

	val1 = SimpleInput.getInt("Premier entier :");
	val2 = SimpleInput.getInt("Deuxieme entier :");
	val3 = SimpleInput.getInt("Troisieme entier :");

	if (val1 < val3 && val2 < val3) {
		   System.out.println
			("Le troisieme entier " + val3 + " est le plus grand.");
	} else if (val1 < val2 && val3 < val2) {
		    System.out.println
			("Le deuxieme entier " + val2 + " est le plus grand.");
	} else if (val2 < val1 && val3 < val1) {
		    System.out.println
			("Le premier entier " + val1 + " est le plus grand.");
		}
	}
}
