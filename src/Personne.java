public class Personne {
    //public enum Etat {S,E,I,R};
    public EtatPersonne Etat;
    int[] position = new int[2];
    public Personne(String Etat, int[] position){
        this.Etat= EtatPersonne.valueOf(Etat);
        this.position=position.clone();
    }
    public void changerEtat(String newEtat){
        this.Etat=EtatPersonne.valueOf(newEtat);
    }
    public EtatPersonne getEtat(){
        return Etat;
    }
 }
