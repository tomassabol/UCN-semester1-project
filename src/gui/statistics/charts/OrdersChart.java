package gui.statistics.charts;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.AxesChartStyler.TextAlignment;
import org.knowm.xchart.style.Styler.LegendPosition;

public class OrdersChart {
 
 
  public XYChart getChart() {
 
    // generates Log data
    List<Integer> xData = new ArrayList<Integer>();
    List<Double> yData = new ArrayList<Double>();
    for (int i = -3; i <= 3; i++) {
      xData.add(i);
      yData.add(Math.pow(10, i));
    }
 
    // Create Chart
    XYChart chart = new XYChartBuilder().width(800).height(600)
    		.title("Revenue from _ to _")
    		.xAxisTitle("Date")
    		.yAxisTitle("DKK")
    		.build();
 
    // Customize Chart
    chart.getStyler().setChartTitleVisible(true);
    chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
    chart.getStyler().setYAxisLogarithmic(true);
    chart.getStyler().setXAxisLabelRotation(45);
 
    // Data Series
    chart.addSeries("10^x", xData, yData);
 
    return chart;
  }
}