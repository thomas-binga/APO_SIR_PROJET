

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Classe représentant la population étudiée dans la simulation, tous les paramêtres sont gérès par une instance de la classe Parametres.
 * Tous les individus de la populations sont représentés par un 1 sur la carte.
 */
public class Population {
    ArrayList<Personne> Pop = new ArrayList<>();
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
            double rnd= Math.random()*20;
            Pop.add(new Personne("I", loc, 30+(int)rnd));
            int x=loc[0];
            int y=loc[1];
            Map.carte[x][y]+=1;
        }
        for(int i=0; i<NbrPop-NbrMalades;i++){
            int[] loc = RandomLoc();
            int rnd = (int) (Math.random() * 10);
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
    public void Avancer(){
        if(Param.Spatialisation) Deplacement(Param.PolitiquesPubliques);
           if(Param.Mod instanceof SIR) {
               for (Personne personne : Pop) {
                   if (personne.getEtat() == EtatPersonne.S) {
                       double rnd = Math.random();
                       double Beta = Param.Beta;
                       double V = Param.TauxVaccination;
                       if(rnd<V){
                           personne.changerEtat("R");
                       }
                       else if ((rnd < Beta) && (Contaminable(personne))) {
                           personne.changerEtat("I");
                       }
                   } else if (personne.getEtat() == EtatPersonne.I) {
                       double rnd = Math.random();
                       double DeathRate = Param.getTauxLetalite(personne.age);
                       double Gamma = Param.Gamma;
                       if (rnd < DeathRate) {
                           personne.changerEtat("D");
                       }
                       else if (rnd < Gamma) {
                           personne.changerEtat("R");
                       }
                   }
               }
               Statistiques.update(this);
           }
           else if(Param.Mod instanceof SEIR){
               for (Personne personne : Pop) {
                   if (personne.getEtat() == EtatPersonne.S) {
                       double rnd = Math.random();
                       double Beta = Param.Beta;
                       double V = Param.TauxVaccination;
                       if(rnd<V){
                           personne.changerEtat("R");
                       }
                       else if ((rnd < Beta) && (Contaminable(personne))) {
                           personne.changerEtat("E");
                       }
                   } else if (personne.getEtat() == EtatPersonne.E) {
                       double rnd = Math.random();
                       double Alpha = Param.Alpha;
                       if (rnd < Alpha) {
                           personne.changerEtat("I");
                       }
                   } else if (personne.getEtat() == EtatPersonne.I) {
                       double rnd = Math.random();
                       double DeathRate = Param.getTauxLetalite(personne.age);
                       double Gamma = Param.Gamma;
                       if (rnd < DeathRate) {
                           personne.changerEtat("D");
                       }
                       else if (rnd < Gamma) {
                           personne.changerEtat("R");
                       }
                   }
               }
               Statistiques.update(this);
           }
           else if (Param.Mod instanceof SEIRn){
                Naissances();
               for (Personne personne : Pop) {
                   if (personne.getEtat() == EtatPersonne.S) {
                       double rnd = Math.random();
                       double Beta = Param.Beta;
                       double V = Param.TauxVaccination;
                       if(rnd<V){
                           personne.changerEtat("R");
                       }
                       else if ((rnd < Beta) && (Contaminable(personne))) {
                           personne.changerEtat("E");
                       }
                       rnd = Math.random();
                       double mu = Param.getMu(personne.age);
                       if (rnd < mu) {
                           personne.changerEtat("D");
                       }
                   } else if (personne.getEtat() == EtatPersonne.E) {
                       double rnd = Math.random();
                       double Alpha = Param.Alpha;
                       if (rnd < Alpha) {
                           personne.changerEtat("I");
                       }
                       rnd = Math.random();
                       double mu = Param.getMu(personne.age);
                       if (rnd < mu) {
                           personne.changerEtat("D");
                       }
                   } else if (personne.getEtat() == EtatPersonne.I) {
                       double rnd = Math.random();
                       double DeathRate = Param.getTauxLetalite(personne.age);
                       double Gamma = Param.Gamma;

                       if (rnd < DeathRate) {
                           personne.changerEtat("D");
                       }
                       else if (rnd < Gamma) {
                           personne.changerEtat("R");
                       }
                       rnd = Math.random();
                       double mu = Param.getMu(personne.age);
                       if (rnd < mu) {
                           personne.changerEtat("D");
                       }
                   }
               }
               Statistiques.update(this);
           }
       }



    /**
     *
     * @param p
     * @return
     */
    private boolean Contaminable(Personne p){
        int[] loc = p.position.clone();
        for (Personne personne : Pop) {
            if ((distance(loc, personne.position) < 2) && (personne.getEtat() == EtatPersonne.I)) { //Remplacer le 2 si ajout de parametre distance minimale
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
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1),2));
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

    private void Deplacement(int[] PP){
        double C = 1; // 1 si pas de Confinement, 0.1 sinon;
        if(PP[0]==1) C=0.1;
        for (Personne personne : Pop) {
            double rnd = Math.random();
            if (rnd < 0.15*C) {
                int x = personne.position[0];
                int y = personne.position[1];
                int new_x = (x + 1) % Map.taille_x;
                personne.position[0] = new_x;
                Map.carte[x][y] -= 1;
                Map.carte[new_x][y]++;
            } else if (rnd < 0.30*C) {
                int x = personne.position[0];
                int y = personne.position[1];
                int new_x = (x - 1 + Map.taille_x) % Map.taille_x;
                personne.position[0] = new_x;
                Map.carte[x][y] -= 1;
                Map.carte[new_x][y]++;
            } else if (rnd < 0.45*C) {
                int x = personne.position[0];
                int y = personne.position[1];
                int new_y = (y + 1) % Map.taille_y;
                personne.position[1] = new_y;
                Map.carte[x][y] -= 1;
                Map.carte[x][new_y]++;
            } else if (rnd < 0.60*C) {
                int x = personne.position[0];
                int y = personne.position[1];
                int new_y = (y - 1 + Map.taille_y) % Map.taille_y;
                personne.position[1] = new_y;
                Map.carte[x][y] -= 1;
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
            return new int[]{0, 0};
        }
        else{
            return new int[]{Math.round((float) Math.random()*(Map.taille_x-1)),Math.round((float) Math.random()*(Map.taille_y-1))};
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
