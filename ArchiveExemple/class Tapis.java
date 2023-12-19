class Tapis{
    Carte = c;                                                      // peut être pas utile
    int[][] tabCartes = new int[4][9];                              

    int[] tabVal = new int[]{1,2,3,4,5,6,7,8,9};
    String[] tabC = new String[]{"S1","S2","S3","S4"};              // à remplacer par les str de Samuel

    int[][] tabinit = new int[][]{tabVal,tabVal,tabVal,tabVal};     // indice : 0=Piques, 1=Trèfles, 2=Coeur, 3=Carreau
}

void initTapis(int[][]tab){
    tab = tabinit;
}