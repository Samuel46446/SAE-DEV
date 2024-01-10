// fonctions de test

class Ftest extends Program{

    /* JEU*/

    void testCalculPointJoueur(){// marche pas encore
        //calculPointJoueur(Joueur j, boolean[] valJoue)

        Joueur test;
        test.ca1.point = 5; 
        test.ca2.point = 3; 
        test.ca3.point = 7;
        test.ca4.point = 5;

        boolean[] valTest = new boolean[]{true,true,false,false};
        boolean[] valTest2 = new boolean[]{true,true,true,true};

        assertEquals(8, calculPointJoueur(test, valTest));
        assertEquals(20, calculPointJoueur(test, valTest2));
    }

    void testEtoileInf(){
        //etoileInf(Joueur cartes_player, Joueur cartes_ia)

        

        //assertEquals(..., ...);
    }


    /* Fscv */

    void testDonneQuestion(){
        //donneQuestion(int lig, CSVFile file)

        //assertEquals(..., ...);
    }

    void testGetRps(){
        //getRps(CSVFile file, int lig)

        //assertEquals(..., ...);
    }

    void testGardeRps(){
        //gardeRps(String rps)

        //assertEquals(..., ...);
    }

    void testToString(){
        //toString(String[] tab)

        //assertEquals(..., ...);
    }

    void testDonneReponseVrai(){
        //donneReponseVrai(int lig, CSVFile file)

        //assertEquals(..., ...);
    }


    /*
    void test(){}
    */


    void algorithm(){
        // JEU
        testCalculPointJoueur();
        testEtoileInf();

        // Fcsv
        testDonneQuestion();
        testGetRps();
        testGardeRps();
        testToString();
        testToString();
        testDonneReponseVrai();
    }
}