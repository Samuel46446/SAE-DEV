import extensions.CSVFile;
import extensions.File;

class Question extends Program{

    //Affichage du contenu d'un fichier texte (ici situé dans le même répertoire)
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

/////////////////////////////////////////////////////////////////////////////////

    void donneLReponses(String[] tabM){
        /* affiche les différentes reponses disponibles de la tab mélangé */

        for (int i=0; i<length(tabM); i++){
            println (i+1 + ": " + tabM[i]);        // gardeRps(...)?
        }
    }


    String[] mélangeTab(String [] tab){             //changer le é par un e
        /* prend une table et la mélange un nombre aléatoire de fois*/

        for (int i=0; i<(int)(random()*10 +1); i++){
            String elt0 = tab[0];

            for (int indice=0; indice<length(tab)-1; indice++){
                tab[indice] = tab[indice +1];
                
            }
            tab[length(tab)-1] = elt0;

            String eltmid = tab[length(tab) / 2];
            tab[length(tab)/2] = tab[length(tab)/2 +1];
            tab[length(tab)/2+1] = eltmid;
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
        return "la bonne réponse est : " + getCell(file, lig, 1);
    }

/////////////////////////////////////////////////////////////////////////////////

    void algorithm(){
        //afficherRegle();
        println(donneQuestion(5, fichierLoad));
        donneLReponses( mélangeTab( getRps(fichierLoad, 5)) ) ;
        println(donneReponseVrai(5, fichierLoad));
    }
}
