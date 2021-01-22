/**
 * Classe représentant une personne, elle a un état (S, E, I ou R), une position (des coordonnées x et y) et un age (qui influe directement sur les paramêtres SIR)
 */
public class Personne {
    //public enum Etat {S,E,I,R};
    public EtatPersonne Etat;
    int[] position = new int[2];
    int age=0;
    public Personne(String Etat, int[] position, int age){
        this.Etat= EtatPersonne.valueOf(Etat);
        this.position=position.clone();
        this.age=age;
    }
    public void changerEtat(String newEtat){
        this.Etat=EtatPersonne.valueOf(newEtat);
    }
    public EtatPersonne getEtat(){
        return Etat;
    }
 }
