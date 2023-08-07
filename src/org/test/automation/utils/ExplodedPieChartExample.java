package org.test.automation.utils;

import java.awt.Color;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class ExplodedPieChartExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			/* Define Data Range for the Pie Chart */
			DefaultPieDataset myExplodedPieChartData = new DefaultPieDataset();
			/*
			 * Define Values for the Pie Chart - Programming Languages Percentage Difficulty
			 */
			myExplodedPieChartData.setValue("Java", 42.9);
			myExplodedPieChartData.setValue("C++", 78.9);
			myExplodedPieChartData.setValue("C", 56.5);
			myExplodedPieChartData.setValue("VB", 87.2);
			myExplodedPieChartData.setValue("Shell Script", 39.5);
			/*
			 * With the dataset defined for Pie Chart, we can invoke a method in
			 * ChartFactory object to create Pie Chart and Return a JFreeChart object
			 */
			/* This method returns a JFreeChart object back to us */
			JFreeChart myExplodedPieChart = ChartFactory.createPieChart("Programming - Exploded Pie Chart Example",
					myExplodedPieChartData, true, true, false);
			/*
			 * Once we have got the JFreeChart object, it is time to change the colors;
			 * specify explode range and labels for the Pie Chart
			 */
			/* getPlot method of JFreeChart class returns the PiePlot object back to us */
			PiePlot ColorConfigurator = (PiePlot) myExplodedPieChart.getPlot(); /* get PiePlot object for changing */
			/* We can now use setSectionPaint method to change the color of our chart */
			ColorConfigurator.setSectionPaint("Java", new Color(160, 160, 255));
			ColorConfigurator.setSectionPaint("C++", Color.RED);
			ColorConfigurator.setSectionPaint("C", Color.BLUE);
			ColorConfigurator.setSectionPaint("VB", Color.GREEN);
			ColorConfigurator.setSectionPaint("Shell Script", Color.YELLOW);
			/* We specify explode option for the Pie chart using setExplodePercent method */
			/*
			 * This method takes a percentage value and offsets the section of Pie Chart, a
			 * percentage value of radius
			 */
//			ColorConfigurator.setExplodePercent("Shell Script", 0.30);
			/*
			 * A format mask specified to display labels. Here {0} is the section name, and
			 * {1} is the value. We can also use {2} which will display a percent value
			 */
			ColorConfigurator.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}"));
			/* Set color of the label background on the pie chart */
			ColorConfigurator.setLabelBackgroundPaint(new Color(220, 220, 220));
			/*
			 * We will now write the chart as a PNG image file. TO do this we will use the
			 * ChartUtilities class. org.jfree.chart.ChartUtilities
			 */
			int width = 640; /* Width of the image */
			int height = 480; /* Height of the image */
			/* PNG file name */
			File PieChart = new File("my_pie_chart.png");
			/*
			 * Convert JFreeChart to PNG File Using Code below. Note that there is no
			 * quality factor here and we use a different method to write a PNG image file
			 */
			ChartUtilities.saveChartAsPNG(PieChart, myExplodedPieChart, width, height);
		} catch (Exception i) {
			System.out.println(i);
		}

	}

}
