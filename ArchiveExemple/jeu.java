class Jeu extends Program{

    int[] tabVal = new int[]{1,2,3,4,5,6,7,8,9};
    String[] tabC = new String[]{"S1","S2","S3","S4"};

    int[][] tab = new int[][]{tabVal,tabVal,tabVal,tabVal};     // indice : 0=Piques, 1=Trèfles, 2=Coeur, 3=Carreau

    String randomCarte(int[][]tabVal){
        /* return une carte valide aléatoire, puis remplace la val dans le tab par 0 */

        int couleur, valeur;

        do{
            couleur =(int)(random()*3 +1);
            valeur = (int)(random()*9 + 1);
        }while (!carteEstValide(couleur, valeur, tabVal));
        if (valeur != 10){
            tabVal[couleur][valeur] = 0;
        }
        return "" + valeur + tabC[couleur];
    }

    boolean carteEstValide(int couleur, int valeur, int[][] tab){
        /* return true si la carte est disponible, false sinon */

        if (tab[couleur][valeur] == 0){
            return false;
        }
        return true;
    }
    void testCarteEstValide(){

        int[][] tempTab = new int[][]{tabVal,tabVal,{0,0,0,0,0,0,0,0,0},tabVal};
        
        assertEquals(true,carteEstValide(0, 5, tempTab));
        assertEquals(true,carteEstValide(3, 2, tempTab));
        assertEquals(true,carteEstValide(2, 8, tempTab));
    }



    void algorithm(){
        testCarteEstValide();
    }
}