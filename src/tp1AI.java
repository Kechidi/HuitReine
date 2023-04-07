import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
class tp1AI {



    public static int N ;
    public static int getN() {
        return N;
    }

    public static void setN(int n) {
        N = n;
    }

    public Main(int N) {

        this.N=N;
        resultat = new int[N];

    }
    static int[] resultat ;




    public static int compt =0 ;
    static boolean possible(int ligne, int colonne) {

        // Vérifie si la reine peut être  sur une ligne et une colonne données
        for (int i = 0; i < ligne; i++) {
            if (resultat[i] == colonne || Math.abs(resultat[i] - colonne) == Math.abs(i - ligne)) {
                return false;
            }
        }
        return true;
    }


    static void nReines(int ligne) {

        if (ligne == N) {
            compt++;

        }
       if (compt > 0) {
            return;
        }
        for (int i = 0; i < N; i++) {
            if (possible(ligne, i)) {
                resultat[ligne] = i;
                nReines(ligne + 1);


            }

        }

    }





    public static void main(String[] args) {

        try {
            String filePath = System.getProperty("user.dir") + File.separator  + "temps.dat";
            FileWriter fileWriter = new FileWriter(filePath);
            StringBuilder txt = new StringBuilder();
      for (int i=4 ;i<33;i++){
          compt =0;
            Main main = new Main(i);
          double debut = System.nanoTime();
          main.nReines(0);
          double fin = System.nanoTime();
          double temps = (fin - debut)/1000000;
            System.out.println("nombre de solution : pour un echacier de taille " +i+"*"+i+ " = " + compt);
            System.out.println("le temps "+temps);

            txt.append(i).append(" ").append(temps).append("\n");



        System.out.println("nombre de solution :" + compt);
    }

        fileWriter.write(txt.toString());
        fileWriter.close();
        System.out.println("vous pouvez exécuter   ");
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}