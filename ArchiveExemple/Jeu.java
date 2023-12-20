import extensions.CSVFile;
import extensions.File;

class Jeu extends Program{

    //Affichage du contenu d'un fichier texte (ici situé dans le même répertoire)
	File regle = newFile("regle.txt");

	//Stockage dans une variable de la ligne suivante dans le fichier

    void afficherRegle(){
        while(ready(regle)){//on s'arrête dès qu'on lit une ligne null (fin du fichier)
	    //affichage du contenu de la ligne suivante
	    println(readLine(regle));
	    }
    }
	

    String fichier = "question.csv";

    //Chargement  en mémoire d'un fichier CSV (ici situé dans le même répertoire) dans une variable de type CSVFile
	CSVFile fichierLoad = loadCSV(fichier);
	int nbLignes = rowCount(fichierLoad);
	int nbCol = columnCount(fichierLoad);


	String donneQuestion(int lig, CSVFile file){
        /* return la question de la ligne lig dans le fichierLoad*/
        return getCell(file, lig, 0);
    }

    
    void donneLReponses(String[] tab){
        /* affiche les différentes reponses disponibles de la tab */

        
    }
    void mélangeTab(String [] tab){
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

    }
    String getRps(CSVFile file){

        return "";
    }
    void gardeRps(String rps){
        /* garde la réponse en enlevant les séparateurs */
        while (!(charAt(rps, length(rps)) < 'a') && !(charAt(rps, length(rps)) > 'Z')){
            rps = substring(rps, 0, length(rps)-1);
        }
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
        return getCell(file, lig, 2);
    }




    void algorithm(){
        //afficherRegle();
        println(donneQuestion(5, fichierLoad));
        //donneLReponses(5, fichierLoad);
        donneReponseVrai(5, fichierLoad);
    }
}