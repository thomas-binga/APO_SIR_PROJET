/**
 * Classe ayant pour but de gérer l'ensemble des paramètres de la simulation.
 */
public class Parametres { //A faire, getters pour les variables (alpha, beta, etc ... qui doivent prendre compte de l'age
    public int NbrPop, NbrMalades, TempsGuerison;
    public boolean Spatialisation;
    public int[] PolitiquesPubliques;
    public double TauxLetalite;
    public double R0;
    public double Beta;
    public double Gamma;
    public double Alpha;
    public double Mu;
    public double Eta;
    public double TauxVaccination = 0;
    public Modele Mod;



    public int getNbrPop(){
        return NbrPop;
    }
    public int getNbrMalades(){
        return NbrMalades;
    }

    public Parametres(int nbrPop, double tauxLetalite, int nbrMalades, int tempsGuerison, int[] politiquesPubliques, boolean spatialisation, double r0, double beta, double gamma, double alpha, double mu, double eta, Modele mod) {
        NbrPop = nbrPop;
        TauxLetalite = tauxLetalite;
        NbrMalades = nbrMalades;
        TempsGuerison = tempsGuerison;
        PolitiquesPubliques = politiquesPubliques;
        Spatialisation = spatialisation;
        R0 = r0;
        Beta = beta;
        if(PolitiquesPubliques[1]==1) beta*=0.2; //Si Port du masque, on réduit fortement le taux de contamination
        if (PolitiquesPubliques[2]==1) beta*=0.01;/**
         Si il y'a mise en place d'une quarantaine,
         nous avons décidé de diminuer fortement le taux de contamination au lieu de le mettre à 0.
         Nous avons fait cela pour prendre en compte le temps entre la connaissance de l'infection et la mise en place de la quarantaine.
         */
        if(PolitiquesPubliques[3]==1) TauxVaccination=0.02;
        Gamma = gamma;
        Alpha = alpha;
        Mu = mu;
        Eta = eta;
        Mod = mod;
    }
    public double getGamma(int age){
        if(age < 15) return Gamma*1.2;
        if (age < 50) return Gamma;
        else return Gamma*0.8;
    }
    public double getTauxLetalite(int age){
        if(age<10) return TauxLetalite*0.2;
        if(age<20) return TauxLetalite*0.5;
        if (age<30) return TauxLetalite*0.8;
        if(age<50) return TauxLetalite;
        if (age<70) return TauxLetalite*1.5;
        if(age<80) return TauxLetalite*2;
        else return TauxLetalite*3;
    }
    public double getMu(int age){
        if(age<10) return TauxLetalite*0.01;
        if(age<20) return TauxLetalite*0.1;
        if (age<30) return TauxLetalite*0.2;
        if(age<50) return TauxLetalite*0.5;
        if (age<70) return TauxLetalite;
        if(age<80) return TauxLetalite*1.2;
        else return TauxLetalite*1.5;
    }
}
