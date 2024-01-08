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
        /* return la réponse de la ligne lig dans le fichierLoad */
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

    void donnerQuestion(Joueur player)
        /* pose une question et donne ou retire 3 étoile en cas de bonne ou de mauvaise réponse */
    {
        int rnd = (int)(random() * 11 + 1);  //changer le 11 pour en count ligne du csv pour pouvoir rajouter des lignes
        String bonne = getCell(fichierLoad, rnd, 1);
        println(donneQuestion(rnd, fichierLoad));
        donneLReponses( melangeTab( getRps(fichierLoad, rnd) ) );
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

    final int STARSTART = 30;       // nb_etoile au debut de partie
    final int depassement = 21;     // valeur de la main à ne pas dépasser

    int calculPointJoueur(Joueur j, boolean[] valJoue)
    {
        int temp = 0;
        for (int indice = 0; indice < length(valJoue); indice ++){
            if (valCard[indice0]){
                temp += j.tabCard[indice];
            }
        }
        return temp;
    }

    void messageMontreDeb(int totalJoueur, int totalIA) ///
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

    void montrerCarte(Joueur cartes_player, Joueur cartes_ia) //opti les println(), trop de trucs a factoriser
    {
        int totalJoueur = calculPointJoueur(cartes_player);
        int totalIA = calculPointJoueur(cartes_ia);
        messageMontreDeb(totalJoueur, totalIA);
        println();

        // si pas de dépassement
        if (totalJoueur <= depassement && totalIA <= depassement){
            if(totalJoueur > totalIA)
            {
                int gain = totalJoueur - totalIA;
                cartes_player.nbEtoile = cartes_player.nbEtoile + gain;
                println(totalJoueur + " - " + totalIA + " = +" + gain + "⭐");
                cartes_ia.nbEtoile = cartes_ia.nbEtoile - gain;

                println("Vous gagnez "+ gain +" étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
                println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
            }
            else if(totalIA > totalJoueur)
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
                println("!!! EGALITE !!!");
                println("Vous ne gagnez aucune étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
                println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
            }
        }
        //sinon dépassement
        else
        {
            if(totalIA > depassement)
            {
                println("Luigi a dépasser la limite de point...");
                int gain = totalIA - totalJoueur;
                cartes_player.nbEtoielse
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
        }le = cartes_player.nbEtoile + gain;
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

        /*
        // si pas de depassement
        if(totalJoueur > totalIA)
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
        else if(totalIA > totalJoueur)
        {
            int gain = totalIA - totalJoueur;
            cartes_ia.nbEtoile = cartes_ia.nbEtoile + gain;
            println(totalIA + " - " + totalJoueur + " = -" + gain + "⭐");
            cartes_player.nbEtoile = cartes_player.nbEtoile - gain;
                                        
            println("Vous perdez "+ gain +" étoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
            println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐");
        }

        // sinon dépassement
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
                println("Vous ne gagnez aucune cartes_ia.nbEtoileétoiles, votre nombre d'étoiles est de " + cartes_player.nbEtoile + " étoiles !");
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
        */
    }
    void afficherInfoPartie(Joueur joueur, Joueur ia, int nb_tour, boolean[] valJoue){
        println();
        println("Carte : " + cartes_player.ca1.str + "  " + cartes_player.ca2.str);
        println("Étoiles Joueur : " + cartes_player.nbEtoile + "⭐ | Nombre de Cartes : " + nb_tour);
        println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐ | Nombre de Cartes : "+ nb_tour);
        int scoreManche = 0;
        int nbcarte = 0;
        for (int i = 0; i<length(valJoue); i++){    // donne le nombre de carte 
            if (valJoue[i]){
                nbcarte += 1;
            }
        }
        for (int indice =0; indice < nbcarte; indice ++){
            scoreManche += tabCard[indice];
        }
        println("Score : " + scoreManche + " Points !");
    }


    void loopOption()
    {
        boolean b = true;
        Boolean[] valJoue = new String[]{true, true, false, false};  //ptere le fouttre dans la class Joueur
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
                pioche3 = true;
                pioche4 = true;

                int nb_tour = 1;
                while(continuer && (nbtour <= 4))
                {
                    afficherInfoPartie(Joueur joueur, Joueur ia, int nb_tour, boolean[] valJoue); //DECASSER CETTE MERDE

                    print("Choissisez vous de piocher(a) ou de montrer vos cartes(x) : ");
                    char bouton = readChar();

                    if(bouton == 'a')
                    {
                        nb_tour += 1;
                        valJoue[nb_tour] = true;
                        donnerQuestion(cartes_player);
                        afficherInfoPartie(Joueur joueur, Joueur ia, int nb_tour, boolean[] valJoue); //PAREIL EN CRABE
                        
                        /*
                        while(pioche3)
                        {
                            println();
                            println("Carte : " + cartes_player.ca1.str + "  " + cartes_player.ca2.str + "  " + cartes_player.ca3.str);
                            println("Étoiles Joueur : " + cartes_player.nbEtoile + "⭐ | Nombre de Cartes : 3");
                            println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐ | Nombre de Cartes : 3");
                            int scoreManche2 = cartes_player.ca1.point + cartes_player.ca2.point + cartes_player.ca3.point;
                            println("Score : " + scoreManche2 + " Points !");
                            print("Choissisez vous de piocher(a) ou de montrer vos cartes(x) : ");
                            char bouton2 = readChar();
                            if(bouton2 == 'a')
                            {
                                donnerQuestion(cartes_player);
                                
                                while(pioche4)
                                {
                                    println();
                                    println("Carte : " + cartes_player.ca1.str + "  " + cartes_player.ca2.str + "  " + cartes_player.ca3.str + "  " + cartes_player.ca4.str);
                                    println("Étoiles Joueur : " + cartes_player.nbEtoile + "⭐ | Nombre de Cartes : 4");
                                    println("Étoiles de Luigi : " + cartes_ia.nbEtoile + "⭐ | Nombre de Cartes : 4");
                                    int scoreManche3 = cartes_player.ca1.point + cartes_player.ca2.point + cartes_player.ca3.point + cartes_player.ca4.point;
                                    println("Score : " + scoreManche3 + " Points !");
                                    print("Appuyez sur (x) pour montrer vos cartes : ");
                                    char bouton3 = readChar();
                                    donnerQuestion(cartes_player);
                                    println();
                                    println("Vous montrez vos cartes !");
                                    montrerCarte(cartes_player, cartes_ia);


                                    if(etoileInf(cartes_player, cartes_ia))
                                    {
                                        b = false;
                                    }
                                    else
                                    {
                                        print("Pour continuer la partie entrez (1) et pour quitter (2) : ");
                                    }
                                    part = false;
                                    pioche3 = false;
                                    pioche4 = false;
                                }
                            }
                            if(bouton2 == 'x')
                            {
                                donnerQuestion(cartes_player);
                                println();
                                println("Vous montrez vos cartes !");
                                montrerCarte(cartes_player, cartes_ia);
                                if(etoileInf(cartes_player, cartes_ia))
                                {
                                    b = false;
                                }
                                else
                                {
                                    print("Pour continuer la partie entrez (1) et pour quitter (2) : ");
                                }
                                continuer = false;
                                pioche3 = false;
                                pioche4 = false;
                            }
                        }
                        */

                    }
                    if(bouton == 'x')
                    {
                        donnerQuestion(cartes_player);
                        println();
                        println("Vous montrez vos cartes !");
                        montrerCarte(cartes_player, cartes_ia);
                        if(etoileInf(cartes_player, cartes_ia))
                        {
                            b = false;
                        }
                        else
                        {
                            print("Pour continuer la partie entrez (1) et pour quitter (2) : ");
                        }
                        continuer = false;
                        pioche3 = false;
                        pioche4 = false;
                    }
                }
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
