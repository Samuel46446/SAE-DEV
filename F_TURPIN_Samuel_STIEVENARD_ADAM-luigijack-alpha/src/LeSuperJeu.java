// fonction du jeu

import extensions.CSVFile;
import extensions.File;

class LeSuperJeu extends Program
{
    /* Fonction CSV */
    
    String fichier = "../ressources/question.csv";

    //Chargement  en mémoire d'un fichier CSV (ici situé dans le même répertoire) dans une variable de type CSVFile
	CSVFile fichierLoad = loadCSV(fichier);
	int nbLignes = rowCount(fichierLoad);
	int nbCol = columnCount(fichierLoad);


	String donneQuestion(int lig, CSVFile file){
        /* return la question de la ligne lig dans le fichierLoad*/
        return getCell(file, lig, 0);
    }

    void afficheLReponses(String[] tab){
        /* affiche les différentes reponses disponibles de la tab mélangé */

        for (int i=0; i<length(tab); i++){
            println (i+1 + ": " + tab[i]);        //gardeRps(...)
        }
    }

    String[] melangeTab(String [] tab){ // changer en piocher random puis return la liste des trucs randoms
        /* prend une table et la mélange un nombre aléatoire de fois*/
        int nbFois = (int)(random()*10);
        for (int i=0; i<nbFois; i++){
            String elt1 = tab[0];

            for (int indice=0; indice<length(tab)-1; i++){
                tab[indice] = tab[indice +1];
                tab[length(tab)] = elt1;
            }

            String eltmid = tab[length(tab) / 2];
            tab[length(tab)/2] = tab[length(tab)/2 +1];
            tab[length(tab)+1] = eltmid;
        }
        return tab;

    }

    String[] getRps(CSVFile file, int lig){
        /* donne un tab de String contenant la liste des réponses possibles */
        String[] tTemp = new String[4];
        int cpt = 0;

        for (int i=1; i<length(tTemp)+1; i++){
            tTemp[cpt] = getCell(file, lig, i);
            cpt++;
        }
        return tTemp;
    }

    String gardeRps(String rps){
        /* garde la réponse en enlevant les séparaeurs */
        
        return getCell(file, lig, 1);
    }

    String toString(String[] tab){
        String chaine = "";
        for (int indice=0; indice < length(tab); indice ++){
            chaine += tab[indice] + " ";
        }
        return chaine;
    }

    String donneReponseVrai(int lig, CSVFile file){
        /* return la réponse de la ligne lig dans le fichierLoad */
        return getCell(file, lig, 1);       ///AAAAAAAAAAAAAHHHHHHHHHHHHHHHHh
    }

/////////////////////////////////////////////////////////////////////

    /* Fonction txt */

    File regle = newFile("../ressources/regle.txt");
	//Stockage dans une variable de la ligne suivante dans le fichier

    void afficherRegle(){
        while(ready(regle)){//on s'arrête dès qu'on lit une ligne null (fin du fichier)
	    //affichage du contenu de la ligne suivante
	    println(readLine(regle));
	    }
    }

    void txtAlgorithm(){
        afficherRegle();
    }

/////////////////////////////////////////////////////////////////////

    /* Fonction de test */

    /* test jeu */

    void testCalculPointJoueur(){// marche pas encore
        //calculPointJoueur(Joueur j, boolean[] valJoue)

        Joueur test = new Joueur();
        test.tabCard[0].point = 5; 
        test.tabCard[1].point = 3; 
        test.tabCard[2].point = 7;
        test.tabCard[3].point = 5;

        boolean[] valTest = new boolean[]{true,true,false,false};
        boolean[] valTest2 = new boolean[]{true,true,true,true};

        assertEquals(8, calculPointJoueur(test, valTest));
        assertEquals(20, calculPointJoueur(test, valTest2));
    }

    void testEtoileInf(){
        //etoileInf(Joueur cartes_player, Joueur cartes_ia)

        Joueur test = new Joueur();
        test.tabCard[0].point = 5; 
        test.tabCard[1].point = 3; 
        test.tabCard[2].point = 7;
        test.tabCard[3].point = 5;

        Joueur test2 = new Joueur();
        test2.tabCard[0].point = 2; 
        test2.tabCard[1].point = 3; 
        test2.tabCard[2].point = 4;
        test2.tabCard[3].point = 1;

        Joueur test3 = new Joueur();
        test3.tabCard[0].point = 5; 
        test3.tabCard[1].point = 8; 
        test3.tabCard[2].point = 9;
        test3.tabCard[3].point = 6;

        assertEquals(true, etoileInf(test, test2));
        assertEquals(false, etoileInf(test, test3));
    }


    /* test csv */

    void testDonneQuestion(){
        //donneQuestion(int lig, CSVFile file)

        assertEquals("Avec quoi mesure t'on quelqu'un ?", donneQuestion( 5, fichierLoad));
    }

    void testGetRps(){
        //getRps(CSVFile file, int lig)

        assertEquals(new String[]{"un mètre","un thermomètre","une pendule","une balance"}, getRps( fichierLoad, 5));
    }

    void testGardeRps(){
        //gardeRps(String rps)

        assertEquals("un mètre", new String[]{"un mètre","un thermomètre","une pendule","une balance"});
    }

    void testToString(){
        //toString(String[] tab)

        //assertEquals(..., ...);
    }

    void testDonneReponseVrai(){
        //donneReponseVrai(int lig, CSVFile file)

        //assertEquals(..., ...);
    }

    void testAlgorithm(){
        // JEU
        testCalculPointJoueur();
        testEtoileInf();

        // csv
        testDonneQuestion();
        testGetRps();
        testGardeRps();
        testToString();
        testToString();
        testDonneReponseVrai();
    }

/////////////////////////////////////////////////////////////////////


    final int STARSTART = 30;       // nb_etoile au debut de partie
    final int depassement = 21;     // valeur de la main à ne pas dépasser

    
    Card pioche()
    /* pioche une carte de valeur et couleur aléatoire */
    {
        

        String[] color = { "♠️", "♦️", "♥","🍀" };
        int[] val = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int randomNumber = (int)(random() * length(val));
        int randomColor = (int)(random() * length(color));

        
        // si roi ou Joker
        if(val[randomNumber] == 10)
        {
            int rndJR = (int)random();
            Card c = new Card();
            c.point = 10;

            if(rndJR == 1)      // si Joker
            {
                c.str = "J";
                return c;
            }
            else                // sinon roi
            {
                c.str = "R";
                return c;
            }
        }

        //si autre val
        Card c = new Card();
        c.str = color[randomColor] + val[randomNumber];
        c.point = val[randomNumber];
        return  c;
    }


    int calculPointJoueur(Joueur j, boolean[] valJoue)
    /* calcul la somme de la valeur des cartes de la main d'un joueur */
    {
        int temp = 0;
        for (int indice = 0; indice < length(valJoue); indice ++){
            if (valJoue[indice]){
                temp += j.tabCard[indice].point;
            }
        }
        return temp;
    }

    boolean etoileInf(Joueur cartes_player, Joueur cartes_ia)
    /* return true si la un joueur n'a plus d'étoiles */
    {
        if(cartes_player.nbEtoile <= 0)
        {
            println("Vous n'avez plus aucune étoile...");
            println("           GAME OVER             ");
            return true;
        }
        else if(cartes_ia.nbEtoile <= 0)
        {
            println("Luigi n'a plus aucune étoile...");
            println("        VOUS AVEZ GAGNER       ");
            return true;
        }
        return false;
    }


    void donnerQuestion(Joueur player)
        /* pose une question aléatoire et donne ou retire 3 étoile en cas de bonne ou de mauvaise réponse */
    {
        int rnd = (int)(random() * nbLignes + 1);  //changer le 11 pour en count ligne du csv pour pouvoir rajouter des lignes
        String bonne = getCell(fichierLoad, rnd, 1);
        println(donneQuestion(rnd, fichierLoad));
        afficheLReponses( melangeTab( getRps(fichierLoad, rnd) ) );
        int rep = readInt();

        // si la réponse est bonne
        if(equals(getCell(fichierLoad, rnd, rep), donneReponseVrai(rnd, fichierLoad)))
        {
            println("Bravo vous avez répondu correctement, vous gagner 3 étoiles !");
            player.nbEtoile = player.nbEtoile + 3;
        }
        else    // sinon mauvaise réponse
        {
            println("Vous n'avez pas répondu correctement, vous perdez 3 étoiles !");
            println("la bonne réponse est : " + donneReponseVrai(rnd, fichierLoad));
            player.nbEtoile = player.nbEtoile - 3;
        }
    }

    void messageMontreDeb(int totalJoueur, int totalIA) /// probablement changer en afficherInfoPartie(){}
    {
        println("Votre Score : " + totalJoueur + " Points !");
        println("Score de Luigi : " + totalIA + " Points !");
    }

    void afficherInfoPartie(Joueur joueur, Joueur ia, int nb_tour, boolean[] valJoue)
    /* affiche les informations du joueur et de l'ia tel que 
            -la main du joueur
            -la nombre de cartes jouée apr le joueur et l'ia
            -le score
    */
    {
        int scoreManche = 0;
        int nbcarte = 0;

        for (int i = 0; i<length(valJoue); i++){    // donne le nombre de carte 
            if (valJoue[i]){
                nbcarte += 1;
            }
        }

        for (int indice =0; indice < nbcarte; indice ++){
            scoreManche += joueur.tabCard[indice].point;
        }

        println();
        println("Carte : " + joueur.tabCard[0].str + "  " + joueur.tabCard[1].str);
        println("Étoiles Joueur : " + joueur.nbEtoile + "⭐ | Nombre de Cartes : " + nb_tour);
        println("Étoiles de Luigi : " + ia.nbEtoile + "⭐ | Nombre de Cartes : "+ nb_tour);
        println("Score : " + scoreManche + " Points !");
    }



    void loopOption()
    {
        boolean b = true;
        boolean[] valJoue = new boolean[]{true, true, false, false};  //ptere le fouttre dans la class Joueur
        boolean continuer = true;
        Joueur cartes_player = new Joueur();
        Joueur cartes_ia = new Joueur();
        cartes_player.nbEtoile = STARSTART;
        cartes_ia.nbEtoile = STARSTART;

        while(b)
        {
            int choix = readInt();
            if(choix == 1)
            {
                continuer = true;  

                int nb_tour = 1;
                while(continuer && (nb_tour <= 4))
                {
                    afficherInfoPartie(cartes_player, cartes_ia, nb_tour, valJoue); //DECASSER CETTE MERDE

                    print("Choissisez vous de piocher(a) ou de montrer vos cartes(x) : ");
                    char bouton = readChar();

                    if(bouton == 'a')
                    {
                        nb_tour += 1;
                        valJoue[nb_tour] = true;
                        donnerQuestion(cartes_player);
                        afficherInfoPartie(cartes_player, cartes_ia, nb_tour, valJoue); //PAREIL EN CRABE

                    }
                    if(bouton == 'x')
                    {
                        donnerQuestion(cartes_player);
                        println();
                        println("Vous montrez vos cartes !");
                        //montrerCarte(cartes_player, cartes_ia);
                        if(etoileInf(cartes_player, cartes_ia))
                        {
                            b = false;
                        }
                        else
                        {
                            print("Pour continuer la partie entrez (1) et pour quitter (2) : ");
                        }
                        continuer = false;
                    }
                }
            }
            else
            {
                b = false;
            }
        }
    }


    void algorithm()
    {
        //loopOption();
        testAlgorithm();
        donneQuestion( 5, fichierLoad);
    }
}
