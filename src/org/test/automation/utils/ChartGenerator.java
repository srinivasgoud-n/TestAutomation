package org.test.automation.utils;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.test.automation.base.BrowserManager;

public class ChartGenerator extends BrowserManager {
	public static String getChart(int passedcount, int failedCount, int SkippedCount) {
		String chartPath = "";
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		if (passedcount > 0) {
			pieDataset.setValue("PASSED", new Integer(passedcount));
		}
		if (failedCount > 0) {
			pieDataset.setValue("FAILED", new Integer(failedCount));
		}
		if (SkippedCount > 0) {
			pieDataset.setValue("SKIPPED", new Integer(SkippedCount));
		}

		JFreeChart piechart = ChartFactory.createPieChart("Test Case Execution Status", pieDataset, true, true, false);
		PiePlot plot = (PiePlot) piechart.getPlot();
		if (passedcount > 0) {
			plot.setSectionPaint(0, java.awt.Color.GREEN);
		}
		if (failedCount > 0) {
			plot.setSectionPaint(1, java.awt.Color.RED);
		}
		if (SkippedCount > 0) {
			plot.setSectionPaint(2, java.awt.Color.YELLOW);
		}

		try {
			ChartUtilities.saveChartAsJPEG(new File(CURRENTDIR + "\\Snapshots\\AutomationTestReport.jpg"), piechart,
					600, 400);
			chartPath = CURRENTDIR + "\\Snapshots\\AutomationTestReport.jpg";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chartPath;
	}

}
