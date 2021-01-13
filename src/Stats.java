import java.util.Hashtable;

public class Stats {
    Hashtable dicStat = new Hashtable();
    public void update(Population pop){
        int nbrS = 0, nbrE = 0, nbrI = 0, nbrR= 0;
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
            }
        }
        dicStat.put('S',nbrS);
        dicStat.put('E',nbrE);
        dicStat.put('I',nbrI);
        dicStat.put('R',nbrR);
    }
    public Hashtable getDicStat(){
        return dicStat;
    }
}
