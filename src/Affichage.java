import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;

public class Affichage {
    Stats data;
    public Affichage(Stats data){
        this.data=data;
    }
    public void showGraphics(int nbr_jours, boolean catE){
        // Create Chart
        final XYChart chart = new XYChartBuilder().width(600).height(400).title("Area Chart").xAxisTitle("X").yAxisTitle("Y").build();

// Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);

// Series
        double[] jours = new double[nbr_jours+1];
       for(int i=0; i<=nbr_jours; i++){
           jours[i]=i;
       }
       double[] S = getNbr('S');
        chart.addSeries("Individus Sains", jours, getNbr('S'));
        chart.addSeries("Individus infectés", jours, getNbr('I'));
        //if (catE) chart.addSeries("Individus en periode d'incubation", jours, getNbr('E'));
        chart.addSeries("Individus Morts", jours, getNbr('D'));
        //chart.addSeries("Individus Rétablis", jours, getNbr('R'));

// Schedule a job for the event-dispatching thread:
// creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                // Create and set up the window.
                JFrame frame = new JFrame("Advanced Example");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // chart
                JPanel chartPanel = new XChartPanel<XYChart>(chart);
                frame.add(chartPanel, BorderLayout.CENTER);

                // label
                JLabel label = new JLabel("Blah blah blah.", SwingConstants.CENTER);
                frame.add(label, BorderLayout.SOUTH);

                // Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public double[] getNbr(char cat){
        double[] LR = new double[data.memory.size()];
        for(int i=0; i<data.memory.size();i++){
            System.out.println(data.memory.get(i).get(cat));
            int dd = (int) data.memory.get(i).get(cat);
            double d = dd;
            LR[i]=d;
        }
        return LR;
    }
}
