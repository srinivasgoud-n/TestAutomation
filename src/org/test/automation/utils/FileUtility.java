package org.test.automation.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.test.automation.base.BrowserManager;


/**
 * 
 * @author Srinivas Goud Nakka
 *
 */
public class FileUtility extends BrowserManager {

	public static void cleanFolder(String folderName) throws IOException {
		File file = new File(folderName);
		FileUtils.cleanDirectory(file);
		System.out.println("Done. Folder cleaned: " + folderName);
	}

	public static void copyFile(File srcFile, File destFile) throws IOException {
		FileUtils.copyFile(srcFile, destFile);
		System.out.println("Done. file copied: " + srcFile);
	}

	public static void deleteFile(String fileName) throws IOException {
		File file = new File(fileName);
		FileUtils.forceDelete(file);

		System.out.println("Done. file deleted: " + fileName);
	}

	public static void deleteFolder(String folderName) throws IOException {
		File file = new File(folderName);
		FileUtils.deleteDirectory(file);
		System.out.println("Done. folder deleted: " + folderName);
	}
	
	public static void createFile(String fileName) throws IOException
	{
		File f = new File(fileName);
		if(!f.exists())
		{
			f.createNewFile();
			System.out.println("File created !");
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		cleanFolder( CURRENTDIR + fs+"TestAutomationReports");
	}

}
