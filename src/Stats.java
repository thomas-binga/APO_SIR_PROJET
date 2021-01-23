import java.util.Hashtable;

/**
 * Classe dans laquelle sont stockées l'ensemble des données afin de les réstituer textuellement, ou via des graphiques
 */
public class Stats {
    Hashtable dicStat = new Hashtable();
    public void update(Population pop){
        int nbrS = 0, nbrE = 0, nbrI = 0, nbrR= 0, nbrD=0;
        for(int i=0; i<pop.getPop().size();i++){
            switch (pop.getPersonne(i).getEtat().toString()){
                case "S": nbrS++;
                break;
                case "E":nbrE++;
                break;
                case "I": nbrI++;
                break;
                case "R": nbrR++;
                break;
                case "D": nbrD++;
                break;
            }
        }
        dicStat.put('S',nbrS);
        dicStat.put('E',nbrE);
        dicStat.put('I',nbrI);
        dicStat.put('R',nbrR);
        dicStat.put('D',nbrD);
    }
    public Hashtable getDicStat(){
        return dicStat;
    }
}
