import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class Affichage {
    Stats data;

    /**
     *
     * @param data
     */
    public Affichage(Stats data){
        this.data=data;
    }

    /**
     *
     * @param nbr_jours
     * @param courbes
     */
    public void showGraphics(int nbr_jours, String[] courbes){
        final XYChart chart = new XYChartBuilder().width(600).height(400).title("Résultats").xAxisTitle("Jours").yAxisTitle("Nombre de Personnes").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

        double[] jours = new double[nbr_jours+1];
       for(int i=0; i<=nbr_jours; i++){
           jours[i]=i;
       }
       for(int i=0; i<courbes.length; i++) {
           if(courbes[i].equals("S")) chart.addSeries("Individus Sains", jours, getNbr('S'));
           if(courbes[i].equals("I")) chart.addSeries("Individus infectés", jours, getNbr('I'));
           if (courbes[i].equals("E")) chart.addSeries("Individus en periode d'incubation", jours, getNbr('E'));
           if(courbes[i].equals("D")) chart.addSeries("Individus Morts", jours, getNbr('D'));
           if(courbes[i].equals("R")) chart.addSeries("Individus Rétablis", jours, getNbr('R'));
       }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                // Creation de la fenetre
                JFrame frame = new JFrame("Résultats de la simulation");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Graphique
                JPanel chartPanel = new XChartPanel<XYChart>(chart);
                frame.add(chartPanel, BorderLayout.CENTER);

                // Afficher la fenetre
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    /**
     *
     * @param cat
     * @return un double[] représentant le nombre de personnes dans une catégorie au fur et à mesure du temps
     */
    public double[] getNbr(char cat){
        double[] LR = new double[data.memory.size()];
        for(int i=0; i<data.memory.size();i++){
            int dd = (int) data.memory.get(i).get(cat);
            double d = dd;
            LR[i]=d;
        }
        return LR;
    }
}
