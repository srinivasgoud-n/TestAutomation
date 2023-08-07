package org.test.automation.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.test.automation.base.BrowserManager;

/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class ChartGenerator extends BrowserManager {
	public static String getChart(int passedcount, int failedCount, int skippedCount) {
		String chartPath = "";
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		if (passedcount > 0) {
			pieDataset.setValue("PASSED",passedcount);
		}
		if (failedCount > 0) {
			pieDataset.setValue("FAILED", failedCount);
		}
		if (skippedCount > 0) {
			pieDataset.setValue("SKIPPED", skippedCount);
		}

		JFreeChart piechart = ChartFactory.createPieChart("Test Case Execution Status", pieDataset, true, true, false);
		PiePlot plot = (PiePlot) piechart.getPlot();
		if (passedcount > 0) {
			plot.setSectionPaint(0, Color.GREEN);
		}
		if (failedCount > 0) {
			plot.setSectionPaint(1, java.awt.Color.RED);
		}
		if (skippedCount > 0) {
			plot.setSectionPaint(2, java.awt.Color.ORANGE);
		}
		
		((StandardPieSectionLabelGenerator)plot.getLabelGenerator()).getPercentFormat().setMaximumFractionDigits(2);
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}"));
		/* Set color of the label background on the pie chart */
		plot.setLabelBackgroundPaint(new Color(220, 220, 220));

		try {
			ChartUtilities.saveChartAsJPEG(new File(CURRENTDIR + fs+"Snapshots"+fs+"AutomationTestReport.jpg"), piechart,
					600, 400);
			chartPath = CURRENTDIR + fs+"Snapshots"+fs+"AutomationTestReport.jpg";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chartPath;
	}
	
	public static void main(String args[])
	{
		getChart(6, 4, 1);
	}

}
