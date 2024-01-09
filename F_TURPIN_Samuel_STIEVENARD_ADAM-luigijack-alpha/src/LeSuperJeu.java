// fonction du jeu

class LeSuperJeu extends Program
{
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

    void donnerQuestion(Joueur player)
        /* pose une question aléatoire et donne ou retire 3 étoile en cas de bonne ou de mauvaise réponse */
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

    

    int calculPointJoueur(Joueur j, boolean[] valJoue)
    /* calcul la somme de la valeur des cartes de la main d'un joueur */
    {
        int temp = 0;
        for (int indice = 0; indice < length(valJoue); indice ++){
            if (valCard[indice0]){
                temp += j.tabCard[indice];
            }
        }
        return temp;
    }

    void messageMontreDeb(int totalJoueur, int totalIA) /// probablement changer en afficherInfoPartie(){}
    {
        println("Votre Score : " + totalJoueur + " Points !");
        println("Score de Luigi : " + totalIA + " Points !");
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

    void montrerCarte(Joueur player, Joueur ia, int nb_tour, boolean[] valJoue) //opti les println(), trop de trucs a factoriser
    /* montre les carte et fait d'autre trucs en plus */     //changer le contenu de la fonction(fragmenter)
    {
        int totalJoueur = calculPointJoueur(cartes_player, valJoue);
        int totalIA = calculPointJoueur(cartes_ia, valJoue);
        afficherInfoPartie(totalJoueur, totalIA, nb_tour, valJoue);
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
        
    }

    void afficherInfoPartie(Joueur joueur, Joueur ia, int nb_tour, boolean[] valJoue)
    /* affiche les informations du joueur et de l'ia tel que 
            -la main du joueur
            -la nombre de cartes jouée apr le joueur et l'ia
            -le score
    */
    {

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

    void algorithm()
    {
        loopOption();
    }
}
