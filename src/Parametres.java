/**
 * Classe ayant pour but de gérer l'ensemble des paramètres de la simulation.
 */
public class Parametres { //A faire, getters pour les variables (alpha, beta, etc ... qui doivent prendre compte de l'age
    public int NbrPop, TauxLetalite, NbrMalades, TempsGuerison;
    public boolean PolitiquesPubliques, Spatialisation;
    public double R0;
    public double Beta;
    public double Gamma;
    public double Alpha;
    public double Mu;
    public double Eta;
    public Modele Mod;



    public int getNbrPop(){
        return NbrPop;
    }
    public int getNbrMalades(){
        return NbrMalades;
    }

    public Parametres(int nbrPop, int tauxLetalite, int nbrMalades, int tempsGuerison, boolean politiquesPubliques, boolean spatialisation, double r0, double beta, double gamma, double alpha, double mu, double eta, Modele mod) {
        NbrPop = nbrPop;
        TauxLetalite = tauxLetalite;
        NbrMalades = nbrMalades;
        TempsGuerison = tempsGuerison;
        PolitiquesPubliques = politiquesPubliques;
        Spatialisation = spatialisation;
        R0 = r0;
        Beta = beta;
        Gamma = gamma;
        Alpha = alpha;
        Mu = mu;
        Eta = eta;
        Mod = mod;
    }
}
