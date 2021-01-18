public class Main {

    public static void main(String args[]){
        SEIR m = new SEIR();
        Parametres P = new Parametres(50,0,1,8,false,true,1,0.3,0.2,0.3,0,0,m);
        Population Pop = new Population(P);
        System.out.println(Pop.Statistiques.dicStat.toString());
        for(int i=0;i<10;i++){
            Pop.Avancer(false);
            System.out.println(Pop.Statistiques.dicStat.toString());
        }
        System.out.println(Pop.Map.toString()); //Non fonctionnel

    }
}
