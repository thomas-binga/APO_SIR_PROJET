public class Carte {
    public int taille_x = 10;
    public int taille_y = 10;
    int[][] carte = new int[taille_x][taille_y];
    Personne[][] carte_p = new Personne[taille_x][taille_y];
    public Carte(){
        for(int i=0; i<taille_x;i++){
            for(int j=0; j<taille_y; j++){
                carte[i][j]=0;
                //carte_p[i]
            }
        }
    }
    @Override
    public String toString(){
        String SR = new String();
        for(int i=0; i<taille_x;i++){
            for(int j=0; j<taille_y;j++){
                int pos = carte[i][j];
                SR=SR.concat(Integer.toString(pos));
                SR=SR.concat(" ");
            }
            SR=SR.concat("\n");
        }
        return SR;
    }


}
