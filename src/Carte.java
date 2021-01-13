public class Carte {
    public int taille_x = 20;
    public int taille_y = 20;
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


}
