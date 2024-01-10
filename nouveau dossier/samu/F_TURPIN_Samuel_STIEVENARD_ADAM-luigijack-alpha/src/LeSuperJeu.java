import extensions.CSVFile;
import extensions.File;

class LeSuperJeu extends Program
{
    File regle = newFile("./ressources/regle.txt");
	//Stockage dans une variable de la ligne suivante dans le fichier

    void afficherRegle(){
        while(ready(regle)){//on s'arrête dès qu'on lit une ligne null (fin du fichier)
	    //affichage du contenu de la ligne suivante
	    println(readLine(regle));
	    }
    }
	

    String fichier = "./ressources/question.csv";

    //Chargement  en mémoire d'un fichier CSV (ici situé dans le même répertoire) dans une variable de type CSVFile
	CSVFile fichierLoad = loadCSV(fichier);
	int nbLignes = rowCount(fichierLoad);
	int nbCol = columnCount(fichierLoad);


	String donneQuestion(int lig, CSVFile file){
        /* return la question de la ligne lig dans le fichierLoad*/
        return getCell(file, lig, 0);
    }

    
    void donneLReponses(String[] tab){
        /* affiche les différentes reponses disponibles de la tab mélangé */

        for (int i=0; i<length(tab); i++){
            println (i+1 + ": " + tab[i]);        //gardeRps(...)
        }
    }
    String[] melangeTab(String [] tab){
        /* prend une table et la mélange un nombre aléatoire de fois*/

        for (int i=0; i<(int)random()*10; i++){
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
        while (!(charAt(rps, length(rps)) < 'a') && !(charAt(rps, length(rps)) > 'Z')){
            rps = substring(rps, 0, length(rps)-1);
        }
        return rps;
    }

    String toString(String[] tab){
        String chaine = "";
        for (int indice=0; indice < length(tab); indice ++){
            chaine += tab[indice] + " ";
        }
        return chaine;
    }

    String donneReponseVrai(int lig, CSVFile file){
        /* return la réponse de la ligne lig dans le fichierLoad*/
        return getCell(file, lig, 1);
    }
    /*
    void algorithm(){
        //afficherRegle();
        println(donneQuestion(5, fichierLoad));
        donneLReponses(getRps(fichierLoad, 5));
        println(donneReponseVrai(5, fichierLoad));
    }*/
    Card pioche()
    {
        int randomNumber = (int)(random() * 9);
        int randomColor = (int)(random() * 3);
        String[] color = { "♠️", "♦️", "♥","🍀" };
        int[] val = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        if(val[randomNumber] == 10)
        {
            int rndJR = (int)random();
            if(rndJR == 1)
            {
                Card c = new Card();
                c.str = "J";
                c.point = 10;
                return c;
            }
            else
            {
                Card c = new Card();
                c.str = "R";
                c.point = 10;
                return c;
            }
        }
        Card c = new Card();
        c.str = color[randomColor] + val[randomNumber];
        c.point = val[randomNumber];
        return  c;
    }

    void donnerQuestion(Joueur player)
    {
        int rnd = (int)(random() * 11 + 1);
        String bonne = getCell(fichierLoad, rnd, 1);
        println(donneQuestion(rnd, fichierLoad));
        donneLReponses( melangeTab( getRps(fichierLoad, rnd) ) );
        int rep = readInt();
        if(equals(getCell(fichierLoad, rnd, rep), donneReponseVrai(rnd, fichierLoad)))
        {
            println("Bravo vous avez répondu correctement, vous gagner 3 étoiles !");
            player.nbEtoile = player.nbEtoile + 3;
        }
        else
        {
            println("Vous n'avez pas répondu correctement, vous perdez 3 étoiles !");
            println("la bonne réponse est : " + donneReponseVrai(rnd, fichierLoad));
            player.nbEtoile = player.nbEtoile - 3;
        }
    }

    final int STARSTART = 30;
    final int depassement = 21;

    int calculPointJoueur(Joueur j)
    {
        int calcul = j.ca1.point + j.ca2.point + j.ca3.point + j.ca4.point;
        return calcul;
    }

    void messageMontreDeb(int totalJoueur, int totalIA)
    {
        println("Votre Score : " + totalJoueur + " Points !");
        println("Score de Luigi : " + totalIA + " Points !");
    }

    boolean etoileInf(Joueur cartes_player, Joueur cartes_ia)
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

    void montrerCarte(Joueur cartes_player, Joueur cartes_ia)
    {
        int totalJoueur = calculPointJoueur(cartes_player);
        int totalIA = calculPointJoueur(cartes_ia);
        messageMontreDeb(totalJoueur, totalIA);
        println();
        if(totalJoueur > totalIA && totalJoueur <= depassement)
        {
            int gain = totalJoueur - totalIA;
            cartes_player.nbEtoile = cartes_player.nbEtoile + gain;
            println(totalJoueur + " - " + totalIA + " = +" + gain + "⭐");
            cartes_ia.nbEtoile = cartes_ia.nbEtoile - gain;

            println("Vous gagnez "+ gain +" étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
        }
        else if(totalJoueur == totalIA)
        {
            println("!!! EGALITE !!!");
            println("Vous ne gagnez aucune étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
        }
        else if(totalIA > totalJoueur && totalIA <= depassement)
        {
            int gain = totalIA - totalJoueur;
            cartes_ia.nbEtoile = cartes_ia.nbEtoile + gain;
            println(totalIA + " - " + totalJoueur + " = -" + gain + "⭐");
            cartes_player.nbEtoile = cartes_player.nbEtoile - gain;
                                        
            println("Vous perdez "+ gain +" étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
        }
        else
        {
            if(totalIA > depassement)
            {
                println("Luigi a dépasser la limite de point...");
                int gain = totalIA - totalJoueur;
                cartes_player.nbEtoile = cartes_player.nbEtoile + gain;
                println(totalIA + " - " + totalJoueur + " = +" + gain + "⭐");
                cartes_ia.nbEtoile = cartes_ia.nbEtoile - gain;
                println("Vous gagnez "+ gain +" étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            }
            else if(totalIA > depassement && totalJoueur > depassement)
            {
                println("Les deux Joueurs on dépasser la limite de point...");
                println("Vous ne gagnez aucune étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            }
            else
            {
                println("Le Joueur a dépasser la limite de point...");
                int gain = totalJoueur - totalIA;
                cartes_ia.nbEtoile = cartes_ia.nbEtoile + gain;
                println(totalJoueur + " - " + totalIA + " = -" + gain + "⭐");
                cartes_player.nbEtoile = cartes_player.nbEtoile - gain;
                println("Vous perdez "+ gain +" étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            }
            println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
        }
    }

    void infoNouveauTour(int nbTour, Joueur j, Joueur ia)
    {
        println();
        println("Carte : " + j.ca1.str + "  " + j.ca2.str + "  " + j.ca3.str + "  " + j.ca4.str);
        if(nbTour == 3)
        {
            println("Étoiles Joueur : " + j.nbEtoile + "⭐ | Nombre de Cartes : 4");
            println("Étoiles de Luigi : " + ia.nbEtoile + "⭐ | Nombre de Cartes : 4");
        }
        else
        {
            println("Étoiles Joueur : " + j.nbEtoile + "⭐ | Nombre de Cartes : "+(1+nbTour));
            println("Étoiles de Luigi : " + ia.nbEtoile + "⭐ | Nombre de Cartes : "+(1+nbTour));
        }
        //int scoreManche1 = cartes_player.ca1.point + cartes_player.ca2.point;
        println("Score : " + calculPointJoueur(j) + " Points !");
        print("Choissisez vous de piocher(a) ou de montrer vos cartes(x) : ");
    }

    void choixTour(int nbTour, Joueur j, Joueur ia, boolean b)
    /* */
    {
        char bouton = readChar();
        if(bouton == 'a')   // piocher
        {   
            if(nbTour == 1)
            {
                j.ca3 = pioche();
                ia.ca3 = pioche();
            }
            if(nbTour == 2)
            {
                j.ca4 = pioche();
                ia.ca4 = pioche();
            }
            if(nbTour == 3)
            {
                println("Vous ne pouvez que montrer vos cartes...");
            }
            donnerQuestion(j);
            
            
        }
        if(bouton == 'x')   // montrer ses cartes
        {
            donnerQuestion(j);
            println();
            println("Vous montrez vos cartes !");
            montrerCarte(j, ia);
            if(etoileInf(j, ia))
            {
                b = false;
            }
            else
            {
                print("Pour continuer la partie entrez (1) et pour quitter (2) : ");
            }
        }
    }

    void tour(int nbTour, Joueur j, Joueur ia, boolean b)
    {
        infoNouveauTour(nbTour, j, ia);
        choixTour(nbTour, j, ia, b);
    }

    void loopOption()
    {
        boolean b = true;
        Joueur cartes_player = new Joueur();
        Joueur cartes_ia = new Joueur();
        cartes_player.nbEtoile = STARSTART;
        cartes_ia.nbEtoile = STARSTART;

        while(b)
        {
            int choix = readInt();
            if(choix == 1)
            {
                cartes_player.ca1 = pioche();
                cartes_player.ca2 = pioche();
                cartes_ia.ca1 = pioche();
                cartes_ia.ca2 = pioche();
                cartes_player.ca3 = new Card();
                cartes_player.ca4 = new Card();
                cartes_ia.ca3 = new Card();
                cartes_ia.ca4 = new Card();

                print("Choissisez vous de piocher(a) ou de montrer vos cartes(x) : ");
                tour(1, cartes_player, cartes_ia, b);
                print("Choissisez vous de piocher(a) ou de montrer vos cartes(x) : ");
                tour(2, cartes_player, cartes_ia, b);
                print("Appuyez sur (x) pour montrer vos cartes : ");
                tour(3, cartes_player, cartes_ia, b);
            }
            else
            {
                b = false;
            }
        }
    }

    String regle()
    {
        return "Bienvenue dans le Luigi Jack !\nDans ce jeux votre but est d'obtenir le meilleur score avec différentes carte tout en ne sachant pas celui de l'adversaire...\nLes Différentes Cartes sont :\n- Les PIQUES, CARREAUX, COEUR OU TREFFLE avec leurs nombre donne ce nombre de points\n- Le Joker et le Roi donne chacun 10 points\n\nVous pouvez voire à tout moment votre nombre de point à l'écran...\nLorsque qu'un score est supérieur à l'autre, le jeu fait une soustraction entre le plus grand score et le plus petit (grand - petit = nombre de gain)\nLe résultat de ce calcul est positif pour celui qui gagne et négatif pour celui qui perd également ces pièces.\nChoisissez une option : \n - Jouer (1)\n - Quitter (2)\n ";
    }

    void algorithm()
    {
        println(regle());
        loopOption();
    }
}