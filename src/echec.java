class echec {
    static int TAILLE = 8;
    static int[] placementReines = new int[TAILLE];
    static int nombreSolutions = 0;
    static boolean[][] board;
    static int queencount;

    static boolean estPossible(int ligneActuelle, int colonne) {
        // Vérifie si la reine peut être placée sur une ligne et une colonne données
        for (int i = 0; i < ligneActuelle; i++) {
            if (placementReines[i] == colonne || Math.abs(placementReines[i] - colonne) == Math.abs(i - ligneActuelle)) {
                return false;
            }
        }
        return true;
    }



    static void nbSol(int ligneActuelle) {
        if (ligneActuelle == TAILLE) {
            // Si toutes les lignes sont remplies, cela signifie qu'une solution a été trouvée
            nombreSolutions++;
            return;
        }
        for (int i = 0; i < TAILLE; i++) {
            if (estPossible(ligneActuelle, i)) {
                placementReines[ligneActuelle] = i;
                nbSol(ligneActuelle + 1);
            }
        }
    }
    public static boolean remplirTableau()
    {
        if (queencount == 8) return true;
        //Itérer sur tous les champs possibles pour trouver le premier champ libre où aucune capture ne se produit
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                //lorsque placereine renvoie false le spot est disponible
                if(!placerReine(i,j)){
                    board[i][j] = true;
                    ++queencount;
                    //Lorsque vous essayez de remplir le tableau pour la prochaine reine, la position actuelle de la reine sera définie sur la prochaine possible
                    if(!remplirTableau()) {
                        board[i][j] = false;
                        --queencount;
                    }else{
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean placerReine(int i, int j)
    {
        //vérifier les correspondances en ligne droite
        for (int k = 0; k < 8; k++){
            if (k == i) {
                for (int l = 0; l < 8; l++){
                    if(board[k][l]) return true;
                }
            }else {
                if(board[k][j]) return true;
            }
            
        }
           
        //vérifier les correspondances diagonales
        for (int k = 1; k < 8; k ++){
            //case 1 + + 
            if(i + k < 8 && j + k < 8 && board[i+k][j+k]) return true;
            //case 2 + -
            if(i + k < 8 && j - k >= 0 && board[i+k][j-k]) return true;
            //case 3 - + 
            if(i - k >= 0 && j + k < 8 && board[i-k][j+k]) return true;
            //case 4 - - 
            if(i - k >= 0 && j - k >= 0 && board[i-k][j-k]) return true;
        }
        return false;
    }
    public static void affichage()
    {

        System.out.println("****************");

        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 8; i++){
                if (board[i][j])
                    System.out.print(" S |");
                else
                    System.out.print(" - |");
            }
            
            System.out.println("");
            
        }

        
        
    }


    
    public static void main(String[] args) {
        board = new boolean[8][8];
        queencount = 0;
        if (remplirTableau()) affichage();
        nbSol(0);
        System.out.println("Nombre de solutions: " + nombreSolutions);
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        affichage();
        long duration = (endTime - startTime);
        System.out.println("Duration : " + duration / Math.pow(10, 6) + " millisecond");
              
    }
}
