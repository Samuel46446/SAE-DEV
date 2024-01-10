// fonction qui gèrent le csv

import extensions.CSVFile;

class Fcsv extends Program{
    
    String fichier = "./ressources/question.csv";

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
    


    void algorithm(){
        println(donneQuestion(5, fichierLoad));
        afficheLReponses(getRps(fichierLoad, 5));
        println(donneReponseVrai(5, fichierLoad));
    }
}