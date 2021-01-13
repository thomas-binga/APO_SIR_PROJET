import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class Population {
    ArrayList<Personne> Pop = new ArrayList<Personne>();
    Parametres Param;
    Carte Map=new Carte();
    public Stats Statistiques = new Stats();
    public Population(Parametres Param){
        this.Param=Param;
        int NbrPop = Param.getNbrPop();
        int NbrMalades = Param.getNbrMalades();
        for(int i=0; i<NbrMalades; i++){
            int[] loc = RandomLoc();
            Pop.add(new Personne("I", loc));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
        for(int i=0; i<NbrPop-NbrMalades;i++){
            int[] loc = RandomLoc();
            Pop.add(new Personne("S", loc));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
        Statistiques.update(this);

    }

    public void Avancer(boolean Spatial){

       if (Spatial) {
            for (int i = 0; i < Map.taille_x; i++) {
                for (int j = 0; j < Map.taille_y; j++) {

                }
            }
        }
       else{
           if(Param.Mod instanceof SIR) {
               for (int i = 0; i < Pop.size(); i++) {
                   if (Pop.get(i).getEtat() == EtatPersonne.S) {
                       double rnd = Math.random();
                       double Beta = Param.Beta;
                       if (rnd < Beta) {
                           Pop.get(i).changerEtat("I");
                       }
                   } else if(Pop.get(i).getEtat()==EtatPersonne.I){
                       double rnd = Math.random();
                       double Gamma = Param.Gamma;
                       if(rnd < Gamma){
                           Pop.get(i).changerEtat("R");
                       }
                   }
                   Statistiques.update(this);
               }
           }
           else if(Param.Mod instanceof SEIR){
               for(int i=0; i<Pop.size();i++){
                   if(Pop.get(i).getEtat() == EtatPersonne.S){
                       double rnd = Math.random();
                       double Beta = Param.Beta;
                       if(rnd<Beta) {
                           Pop.get(i).changerEtat("E");
                       }
                   }
                   else if(Pop.get(i).getEtat()==EtatPersonne.E){
                       double rnd=Math.random();
                       double Alpha = Param.Alpha;
                       if(rnd<Alpha){
                           Pop.get(i).changerEtat("I");
                       }
                   }
                   else if(Pop.get(i).getEtat()==EtatPersonne.I){
                       double rnd = Math.random();
                       double Gamma= Param.Gamma;
                       if(rnd<Gamma){
                           Pop.get(i).changerEtat("R");
                       }
                   }
                   Statistiques.update(this);
               }

           }
       }
    }

    //private boolean Contagion_possible(int x, int y){}



    private int[] RandomLoc(){
        int[] pop = {0,0}; // à Modifier pour la spatialisation
        return pop;
    }
    public ArrayList<Personne> getPop(){
        return Pop;
    }
    public Personne getPersonne(int i){
        return Pop.get(i);
    }
}
