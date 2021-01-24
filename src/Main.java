

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException { // A faire: adapter coefs selon age (dans Param avec getters)+suite du sujet
//        SEIR m = new SEIR();
//        int[] PP = {0,0,0,0};
//        Parametres P = new Parametres(100,0.005,5,8,PP,true,1,0.3,0.12,0.3,0.001,0.004,m);
        Parametres P = new Parametres();
        Population Pop = new Population(P);
        System.out.println(Pop.Statistiques.dicStat.toString());
        System.out.println(Pop.Map.toString());
        for(int i=0;i<P.NbrJours;i++){
            Pop.Avancer();
//            System.out.println(Pop.Statistiques.dicStat.toString());
//            System.out.println(Pop.Map.toString());
        }
        Affichage a = new Affichage(Pop.Statistiques);
        a.showGraphics(P.NbrJours, true);


    }
}
