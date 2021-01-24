import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Classe dans laquelle sont stockées l'ensemble des données afin de les réstituer textuellement, ou via des graphiques
 */
public class Stats {
    Hashtable dicStat = new Hashtable();
    ArrayList<Hashtable> memory =new ArrayList<>();
    public void update(Population pop){
        dicStat.put('S',0);
        dicStat.put('E',0);
        dicStat.put('I',0);
        dicStat.put('R',0);
        dicStat.put('D',0);
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
        save();
    }
    private void save(){
        memory.add((Hashtable) dicStat.clone());
    }
    public Hashtable getDicStat(){
        return dicStat;
    }
}
