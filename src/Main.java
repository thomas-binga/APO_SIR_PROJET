public class Main {

    public static void main(String args[]){ // A faire: adapter coefs selon age (dans Param avec getters)+suite du sujet
        SEIRn m = new SEIRn();
        Parametres P = new Parametres(100,0,5,8,false,true,1,0.3,0.2,0.3,0.0001,0.004,m);
        Population Pop = new Population(P);
        System.out.println(Pop.Statistiques.dicStat.toString());
        System.out.println(Pop.Map.toString());
        for(int i=0;i<30;i++){
            Pop.Avancer();
            System.out.println(Pop.Statistiques.dicStat.toString());
            System.out.println(Pop.Map.toString());
        }
//        SEIR m = new SEIR();
//        Parametres P = new Parametres(50,0,1,8,false,true,1,0.3,0.2,0.3,0,0,m);
//        Population Pop = new Population(P);
//        int[] p1 = {1,1};
//        int[] p2 = {2,3};
//        System.out.println(Pop.distance(p1,p2));

    }
}
