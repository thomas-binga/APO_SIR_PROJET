import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class Population {
    ArrayList<Personne> Pop = new ArrayList<Personne>();
    Parametres Param;
    Carte Map=new Carte();
    public Stats Statistiques = new Stats();

    /**
     *
     * @param Param
     */
    public Population(Parametres Param){
        this.Param=Param;
        int NbrPop = Param.getNbrPop();
        int NbrMalades = Param.getNbrMalades();
        for(int i=0; i<NbrMalades; i++){
            int[] loc = RandomLoc();
            double rnd= Math.random()*10;
            Pop.add(new Personne("I", loc, 30+(int)rnd));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
        for(int i=0; i<NbrPop-NbrMalades;i++){
            int[] loc = RandomLoc();
            int rnd = (int)Math.random()*10;
            Pop.add(new Personne("S", loc, 30+rnd));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
        Statistiques.update(this);

    }

    /**
     *
     */
    public void Avancer(){ //Reste a faire le déplacement de chaque personne chaque jour.
        if(Param.Spatialisation) Deplacement();
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



    /**
     *
     * @param p
     * @return
     */
    private boolean Contaminable(Personne p){
        int[] loc = p.position.clone();
        for(int i=0;i<Pop.size();i++){
            if((distance(loc,Pop.get(i).position)<2)&&(Pop.get(i).getEtat()==EtatPersonne.I)){ //Remplacer le 2 si ajout de parametre distance minimale
//                System.out.println(loc[0]+","+loc[1]+" "+Pop.get(i).position[0]+","+Pop.get(i).position[1]);
//                System.out.println(distance(loc,Pop.get(i).position));
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param loc1
     * @param loc2
     * @return
     */
    public double distance(int[] loc1, int[] loc2){
        int x1=loc1[0];
        int y1=loc1[1];
        int x2=loc2[0];
        int y2=loc2[1];
        return Math.sqrt((double)Math.pow((x2-x1), 2)+(double)Math.pow((y2-y1),2));
    }

    /**
     *
     */
    private void Naissances(){
        double eta=Param.Eta;
        int naissances = 0;
        for(int j=0; j<Pop.size(); j++){
            double rnd = Math.random();
            if(rnd<eta) naissances++;
        }
        for(int i=0;i<naissances;i++){
            int[] loc = RandomLoc();
            Pop.add(new Personne("S", loc, 0));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
    }

    private void Deplacement(){
        for(int i=0; i<Pop.size();i++){
            double rnd = Math.random();
            if(rnd<0.15){
                int x = Pop.get(i).position[0];
                int y = Pop.get(i).position[1];
                int new_x = (x+1)%Map.taille_x;
                Pop.get(i).position[0] = new_x;
                Map.carte[x][y]-=1;
                Map.carte[new_x][y]++;
            }
            else if(rnd<0.30){
                int x = Pop.get(i).position[0];
                int y = Pop.get(i).position[1];
                int new_x = (x-1+Map.taille_x)%Map.taille_x;
                Pop.get(i).position[0] = new_x;
                Map.carte[x][y]-=1;
                Map.carte[new_x][y]++;
            }
            else if(rnd<0.45){
                int x = Pop.get(i).position[0];
                int y = Pop.get(i).position[1];
                int new_y = (y+1)%Map.taille_y;
                Pop.get(i).position[1] = new_y;
                Map.carte[x][y]-=1;
                Map.carte[x][new_y]++;
            }
            else if(rnd<0.60){
                int x = Pop.get(i).position[0];
                int y = Pop.get(i).position[1];
                int new_y = (y-1+Map.taille_y)%Map.taille_y;
                Pop.get(i).position[1] = new_y;
                Map.carte[x][y]-=1;
                Map.carte[x][new_y]++;
            }
        }
    }

    /**
     *
     * @return
     */
    private int[] RandomLoc(){
        if(!Param.Spatialisation) {
            int[] loc = {0, 0};
            return loc;
        }
        else{
            int[] loc={Math.round((float) Math.random()*(Map.taille_x-1)),Math.round((float) Math.random()*(Map.taille_y-1))};
            return loc;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Personne> getPop(){
        return Pop;
    }

    /**
     *
     * @param i
     * @return
     */
    public Personne getPersonne(int i){
        return Pop.get(i);
    }
}
