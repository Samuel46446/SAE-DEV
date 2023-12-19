class Joueur{
    Carte = c;              // def class carte
    Carte[] c.main;         
    boolean continuer;
    int nbEtoile = 30;
    int lvl;                // juste pour luigi
    int score;          // addition de la valeur des cartes en main
}

void initMain(Joueur joueur, int[][] tabVal){
    /* initialise la main de début de tour du joueur */

    c.main = new Carte[5]{randomCarte(tabVal), randomCarte(tabVal)};    // peut être ça marche pas
    score = 0;
}

void gagnerEtoile(Joueur joueur, int incrEtoile){
    /* fait incrémenter le nombre d'étoile de joueur de nbEtoile */

    nbEtoile += incrEtoile;
}
// void testGagnerEtoile(){}

void perdreEtoile(Joueur joueur, int decrEtoile){
    /* fait décrémenter le nombre d'étoile de joueur de nbEtoile */

    nbEtoile -= incrEtoile; 
}
// void testPerdreEtoile(){}

boolean vérifieEtoile(Joueur joueur){
    /* vérifie si le joueur n'a pas perdu */
    return nbEtoile > 0;
}
// void testVerifieEtoile(){}