// fonction qui gèrent les txt

import extensions.File;

class Ftxt extends Program{
    File regle = newFile("./ressources/regle.txt");
	//Stockage dans une variable de la ligne suivante dans le fichier

    void afficherRegle(){
        while(ready(regle)){//on s'arrête dès qu'on lit une ligne null (fin du fichier)
	    //affichage du contenu de la ligne suivante
	    println(readLine(regle));
	    }
    }

    void algorithm(){
        afficherRegle();
    }
}