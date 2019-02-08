package org.test.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {


	public static ArrayList<UserData> readData(String fileName,String sheetName) {

		ArrayList<UserData> countriesList = new ArrayList<UserData>();
		try {
			
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheet(sheetName);
			String userName = "";
			String password = "";
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					// getCellTypeEnum shown as deprecated for version 3.15
					// getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
//						System.out.print(currentCell.getStringCellValue() + "--");
						if (userName.equalsIgnoreCase("")) {
						userName = currentCell.getStringCellValue().trim();
						 System.out.println("userName: " + userName);
						}
						else if (password.equalsIgnoreCase("")) {
							// 2nd column
							password = currentCell.getStringCellValue().trim();
							 System.out.println("password: " + password);
						}
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
//						System.out.print(currentCell.getNumericCellValue() + "--");
					}
					

				}
				System.out.println();

			}
			UserData c = new UserData(userName, password);
			countriesList.add(c);
			System.out.println(c.getUserName()+"::"+c.getPassword());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return countriesList;

	}
	

}
