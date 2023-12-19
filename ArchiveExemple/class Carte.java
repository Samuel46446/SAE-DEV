class Carte{
    int couleur;
    int valeur;
    String strCarte;
    
}

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
    else(valeur == 10){
        return valRJ();
    }
    return "" + valeur + tabC[couleur];
}
String ValRJ(){
    if ((int)random() == 0){
        return "R";
    }
    return "J";
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
