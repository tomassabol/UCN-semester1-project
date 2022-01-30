package gui.statistics.charts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

import gui.Common;
import models.Order;

/**
 * @author Daniels Kanepe
 *
 */
public class OrdersChart {
	private List<Order> orders;
	private LocalDate from;
	private LocalDate to;

	// Constructor
	public OrdersChart(List<Order> orders) {
		this.orders = orders;
		setFromToDates();
	}
	
	private void setFromToDates() {
		if (!orders.isEmpty()) {
			// set From - To dates
			from = orders.get(0).getCreationDate().toLocalDate();
			to = orders.get(0).getCreationDate().toLocalDate();
			for (Order order: orders) {
				LocalDate creationDate = order.getCreationDate().toLocalDate();
				if (creationDate.isBefore(from)) {
					from = creationDate;
				} else if (creationDate.isAfter(to)) {
					to = creationDate;
				}
			}
		}
	}
	public XChartPanel<XYChart> getChart() {
		// extract dates with orders
		Set<LocalDate> orderDates = new HashSet<>();
		for (Order order: orders) {
			orderDates.add(order.getCreationDate().toLocalDate());
		}
		// For each date, sum up the total revenue and plot it
        List<Date> xData = new ArrayList<Date>();
        List<Double> yData = new ArrayList<Double>();
		for (LocalDate orderDate: orderDates) {
			BigDecimal orderDateSum = new BigDecimal(0);
			for (Order order: orders) {
				if (orderDate.equals(order.getCreationDate().toLocalDate())) {
					orderDateSum = orderDateSum.add(order.getTotalPrice());
				}
			}
        	xData.add(java.sql.Date.valueOf(orderDate));
        	yData.add(orderDateSum.doubleValue());
		}

        // Create Chart
        XYChart chart;
        if (orders.isEmpty()) {
        	chart = new XYChartBuilder().title("No orders...").build();
        } else {
            chart = new XYChartBuilder().width(800).height(600)
                    .title(String.format("Revenue from %s to %s", 
                    		Common.dateToString(from),
                    		to.equals(LocalDate.now()) ? "Today" : Common.dateToString(to)))
                    .xAxisTitle("Date")
                    .yAxisTitle("DKK")
                    .build();
        }


        // Customize Chart
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setXAxisLabelRotation(30);
        chart.getStyler().setDatePattern("dd-MMM-yyyy");

        // Data Series
        if (!orders.isEmpty()) {
        	chart.addSeries("Orders", xData, yData);
        }
        
        return new XChartPanel<>(chart);
	}
	
	public void updateData(List<Order> orders) {
		this.orders = orders;
		setFromToDates();
	}
}