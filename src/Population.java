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

    public void Avancer(boolean Spatial){ //Reste a faire le déplacement de chaque personne chaque jour.
           if(Param.Mod instanceof SIR) {
               for (int i = 0; i < Pop.size(); i++) {
                   if (Pop.get(i).getEtat() == EtatPersonne.S) {
                       double rnd = Math.random();
                       double Beta = Param.Beta;
                       if ((rnd < Beta)&&(Contaminable(Pop.get(i)))) {
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
                       if((rnd < Beta)&&(Contaminable(Pop.get(i)))) {
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
           else if (Param.Mod instanceof SEIRn){
                Naissances();
                for(int i=0; i<Pop.size();i++){
                    if(Pop.get(i).getEtat() == EtatPersonne.S){
                        double rnd = Math.random();
                        double Beta = Param.Beta;
                        if((rnd < Beta)&&(Contaminable(Pop.get(i)))) {
                            Pop.get(i).changerEtat("E");
                        }
                        rnd=Math.random();
                        double mu = Param.Mu;
                        if(rnd<mu){
                            Pop.get(i).changerEtat("R"); //A changer en D si on rajoute un nouvel etat
                        }
                    }
                    else if(Pop.get(i).getEtat()==EtatPersonne.E){
                        double rnd=Math.random();
                        double Alpha = Param.Alpha;
                        if(rnd<Alpha){
                            Pop.get(i).changerEtat("I");
                        }
                        rnd=Math.random();
                        double mu = Param.Mu;
                        if(rnd<mu){
                            Pop.get(i).changerEtat("R"); //A changer en D si on rajoute un nouvel etat
                        }
                    }
                    else if(Pop.get(i).getEtat()==EtatPersonne.I){
                        double rnd = Math.random();
                        double Gamma= Param.Gamma;
                        if(rnd<Gamma){
                            Pop.get(i).changerEtat("R");
                        }
                        rnd=Math.random();
                        double mu = Param.Mu;
                        if(rnd<mu){
                            Pop.get(i).changerEtat("R"); //A changer en D si on rajoute un nouvel etat
                        }
                    }
                    Statistiques.update(this);
                }
           }
       }


    //private boolean Contagion_possible(int x, int y){}

    private boolean Contaminable(Personne p){
        int[] loc = p.position;
        for(int i=0;i<Pop.size();i++){
            if((distance(loc,Pop.get(i).position)<2)&&(Pop.get(i).getEtat()==EtatPersonne.S)){ //Remplacer le 2 si ajout de parametre distance minimale
                return true;
            }
        }
        return false;
    }
    private double distance(int[] loc1, int[] loc2){
        int x1=loc1[0];
        int y1=loc1[1];
        int x2=loc2[0];
        int y2=loc2[1];
        return Math.sqrt((double)Math.pow((x2-x1), 2)+(double)Math.pow((y2-y1),2));
    }


    private void Naissances(){
        double eta=Param.Eta;
        int naissances = Math.round((float)eta*(float)Pop.size());
        for(int i=0;i<naissances;i++){
            int[] loc = RandomLoc();
            Pop.add(new Personne("S", loc));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
    }
    private int[] RandomLoc(){
        if(!Param.Spatialisation) {
            int[] loc = {0, 0}; // à Modifier pour la spatialisation
            return loc;
        }
        else{
            int[] loc={Math.round((float) Math.random()*(Map.taille_x-1)),Math.round((float) Math.random()*(Map.taille_y-1))};
            return loc;
        }
    }
    public ArrayList<Personne> getPop(){
        return Pop;
    }
    public Personne getPersonne(int i){
        return Pop.get(i);
    }
}
