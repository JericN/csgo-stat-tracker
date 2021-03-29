import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;

public class Graph extends MethodTools {

    ChartPanel CP;

    public ChartPanel lineGraph() {
        doLine();
        doBar();

        return CP;
    }

    public void doBar() {

    }

    public void doLine() {

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        if (!class_onlyave) {
            for (int i = 0; i < map_choice.size(); i++) {
                dataset.addSeries(timeseries.get(0).get(i));
            }
        } else {
            dataset.addSeries(timeseries.get(0).get(map_choice.size()));
        }


        JFreeChart chart = ChartFactory.createTimeSeriesChart("",
                "x-axis", "y-axis", dataset, true, false, false);
        chart.setBackgroundPaint(new Color(255, 255, 255, 0));
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        chart.getLegend().setBackgroundPaint(Color.PINK);
        CP = new ChartPanel(chart);
        CP.setPreferredSize(new java.awt.Dimension(750, 390));
    }
}
