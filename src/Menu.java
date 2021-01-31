import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe permettant de gérer le menu textuel
 */
public class Menu {
    /**
     *
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public void MenuDisplay() throws FileNotFoundException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bonjour et bienvenue dans le simulateur SIR+ !\nVoici les paramètres enregistrés : \n\n");
        Parametres P = new Parametres();
        P.print();
        System.out.print("Voulez vous:\n    -Les changer Manuellement via le fichier config.properties -> 1\n    -Ne pas les changer -> 2\nVotre choix : ");
        int r1 = sc.nextInt();
            switch(r1){
                case 1:
                    System.out.println("Vous pouvez dès à présent modifier le fichier Config.properties, appuyez sur Entrer quand cela est fait.");
                    promptEnterKey();
                    MenuDisplay();
                    return;
                case 2:
                    break;
                default: System.out.println("Vous avez rentré une mauvaise valeur, redemarrage du programme.");
                MenuDisplay();
            }
            System.out.print("Chargement du Modèle en cours ");
            Thread.sleep(300);
            System.out.print(". ");
            Thread.sleep(300);
            System.out.print(". ");
            Thread.sleep(300);
            System.out.print(". ");
            Thread.sleep(300);
            Population Pop = new Population(P);
            for(int i=0;i<P.NbrJours;i++){
            Pop.Avancer();
        }
        Affichage a = new Affichage(Pop.Statistiques);
        a.showGraphics(P.NbrJours, P.Courbes);
        System.out.print("\n\nBilan de la simulation: \n"+Pop.Statistiques.dicStat.toString()+"\n   S-> personnes Saines\n");
        System.out.print("   E-> personnes en période d'incubation\n   ");
        System.out.print("I-> personnes infectées\n   ");
        System.out.print("R-> personnes guéries\n   ");
        System.out.print("D-> personnes décedées\n");
        }

    /**
     *
     */
    public void promptEnterKey(){
        System.out.println("Appuyez sur \"ENTRER\" pour continuer...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
