import javax.lang.model.type.NullType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Classe permettant de lire et exploitier les données de config.properties
 */
public class PropertyReader {
    File configFile;
    Properties props;

    /**
     *
     * @param path
     * @throws FileNotFoundException
     */
    public PropertyReader(String path) throws FileNotFoundException {
        configFile = new File(path);
        try{
            FileReader reader = new FileReader(configFile);
            props = new Properties();
            props.load(reader);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param param
     */
    public void SetParameters(Parametres param){
        param.NbrJours=Integer.parseInt(props.getProperty("NbrJours"));
        param.NbrPop = Integer.parseInt(props.getProperty("NbrPop"));
        param.NbrMalades=Integer.parseInt(props.getProperty("NbrMalades"));
        param.Spatialisation=false;
        if(Integer.parseInt(props.getProperty("Spatialisation"))==1) param.Spatialisation=true;
        int[] PP = {0,0,0,0};
        if(Integer.parseInt(props.getProperty("Confinement"))==1) PP[0]=1;
        if(Integer.parseInt(props.getProperty("Masque"))==1) PP[1]=1;
        if(Integer.parseInt(props.getProperty("Quarantaine"))==1) PP[2]=1;
        if(Integer.parseInt(props.getProperty("Vaccination"))==1) PP[3]=1;
        param.PolitiquesPubliques=PP.clone();
        param.TauxLetalite=Double.parseDouble(props.getProperty("TauxLetalite"));
        Double R0 = Double.parseDouble(props.getProperty("R0"));
        Double TempsGuerison=Double.parseDouble(props.getProperty("TempsGuerison"));
        param.Gamma=1/TempsGuerison;
        param.Beta=R0*param.Gamma;
        param.Mu=Double.parseDouble(props.getProperty("Mu"));
        param.Alpha=1/Double.parseDouble(props.getProperty("DureeIncubation"));
        param.Eta=Double.parseDouble(props.getProperty("Eta"));
        int mod = Integer.parseInt(props.getProperty("Modele"));
        switch(mod){
            case 1:
                SIR m1 = new SIR();
                param.Mod=m1;
                break;
            case 2:
                SEIR m2 = new SEIR();
                param.Mod=m2;
                break;
            case 3:
                SEIRn m3 = new SEIRn();
                param.Mod=m3;
                break;
        }
        param.Courbes= props.getProperty("Courbes").split(",");

    }

    /**
     *
     */
    public void PrintParameters(){
        System.out.println("Nombre de jours: "+props.getProperty("NbrJours"));
        System.out.println("Taille Population: "+props.getProperty("NbrPop"));
        System.out.println("Nombre de malades: "+props.getProperty("NbrMalades"));
        System.out.println("Spatialisation: "+(Integer.parseInt(props.getProperty("Spatialisation"))==1?"Activée":"Désactivée"));
        System.out.println("Confinement: "+(Integer.parseInt(props.getProperty("Confinement"))==1?"Activé":"Désactivé"));
        System.out.println("Port du masque: "+(Integer.parseInt(props.getProperty("Masque"))==1?"Activé":"Désactivé"));
        System.out.println("Quarantaine Obligatoire: "+(Integer.parseInt(props.getProperty("Quarantaine"))==1?"Oui":"Non"));
        System.out.println("Taux de létalité du virus: "+props.getProperty("Tauxletalité"));
        System.out.println("R0 (Nombre moyen de personne contaminées par une seule personne) : "+props.getProperty("R0"));
        System.out.println("Temps de Contagion/de Guerison: "+props.getProperty("TempsGuerison"));
        System.out.println("Durée du temps d'incubation: "+props.getProperty("DureeIncubation"));
        switch(props.getProperty("Modele")){
            case "1": System.out.println("Modèle choisi: SIR");
            break;
            case "2": System.out.println("Modèle choisi: SEIR");
            break;
            case "3": System.out.println("Modèle choisi: SEIR avec naissances et décès naturels");
            break;
        }
    }


}
