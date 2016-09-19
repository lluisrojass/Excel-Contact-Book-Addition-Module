



// 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// Apache javatuples imports

import org.javatuples.Triplet;

// Apache Jar imports
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
* Class that handles the file interactions. Several methods request a return a value, but ones that just request an action
* return a report of type Triplet<Boolean, String, String> detailing the outcome of the interaction.
* Boolean: Whether an error was encountered
* String (first): Details of encountered error, null otherwise.
* String (second): Action performed by the method.
* 
* TODO: find more data efficient way to return report of interaction, probably with self made method.
*
* @author  Luis E. Rojas
* @version 2.0
* @since   2016-09-19 
*/
public class File_Handler{
	
	private enum WORKBOOKTYPE { 
		/*. xlsx */EXCELCURRENT, 
		/* .xls */EXCELPRE2004, 
		/* unkown */ UNKNOWN
	}
	
	/*Excel File Variables*/
	private Workbook workbook; 
	private File currFile;
	private Sheet currSheet;
	/* Type of Workbook */
	private WORKBOOKTYPE wbType; 
	/* Index for iterating sections */
    private int emailIndex;
	private int relevanceIndex;
	private int nameIndex;
	private int currSheetIndex;
	/*Streams for insertion*/
	private FileInputStream fis;
	private FileOutputStream fos;
	
	
	public File_Handler()
	{ 
		wbType = WORKBOOKTYPE.UNKNOWN;
		fis = null;
		fos = null;
		/* default indexes */
		nameIndex = 0;
		relevanceIndex = 1;
		emailIndex = 2;
	}
	
	public Triplet<Boolean,String,String> close() 
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false,null,"closing file");
		try {
			if (!(wbType == WORKBOOKTYPE.UNKNOWN)) {
				fis.close();
				fos.close();
				workbook.close();
			}
		} catch(java.io.IOException e) {
			out = out.setAt0(true);
			out = out.setAt1("closing file");
		}
		return out;
	}
	
	public Triplet<Boolean,String,String> write(String name,String relevence,String email)
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false,null,"writing to file");
		int workingRow = currSheet.getLastRowNum() + 1;
		currSheet.createRow(workingRow);
		currSheet.getRow(workingRow).createCell(nameIndex).setCellValue(name);
		currSheet.getRow(workingRow).createCell(relevanceIndex).setCellValue(relevence);
		currSheet.getRow(workingRow).createCell(emailIndex).setCellValue(email);
		
		try {
			fos = new FileOutputStream(currFile); 
			workbook.write(fos);
			fos.close();
		}
		catch (java.io.IOException e) {	
			out = out.setAt0(false);
			out = out.setAt1("error accessing file for input");
		} 
		return out;
	}
	
	public Triplet<Boolean,String,String> setSourceFile(String pathname)
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false,null,"Retrieving source file");
		
		String extension = pathname.substring(pathname.length() - 4,pathname.length());
		
		try {
			currFile = new File(pathname);
			fis = new FileInputStream(currFile);
			
			/* check file extension for .xls (Pre-2007 Excel File) */
			switch(extension) {
				case "xlsx":	
					workbook = new XSSFWorkbook(fis);
					wbType = WORKBOOKTYPE.EXCELCURRENT;
					break;
				case ".xls":
					workbook = new HSSFWorkbook(fis); 
					wbType = WORKBOOKTYPE.EXCELPRE2004;
					break;
				default:
					/* throw exception to show error*/
			}
			currSheet = workbook.getSheetAt(0);
		}
		catch (java.io.IOException w){
			out = out.setAt0(true);
			out = out.setAt1("error accessing file from pathname");
		}
		return out;
	}
	
	
	public boolean isFileNull() {
		return wbType == WORKBOOKTYPE.UNKNOWN;
	}
	
	public Triplet<Boolean,String,String> setNameIndex(int index)
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false, null, "changing name index");
		
		if (index < 0) {
			out = out.setAt0(true);
			out = out.setAt1("invalid index");
		}
		else if (index == nameIndex){
			out = out.setAt0(true);
			out = out.setAt1("index already at desired value");	
		}
		else
			nameIndex = index;
		return out;
	}
	
	
	public Triplet<Boolean,String,String> setEmailIndex(int index)
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false,null,"changing email index");
		
		if (index < 0) {
			out = out.setAt0(true);
			out = out.setAt1("invalid index");
		}
		else if (index == emailIndex){
			out = out.setAt0(true);
			out = out.setAt1("index already at desired value");	
		}
		else
			emailIndex = index;
		return out;
	}
	public Triplet<Boolean,String,String> setRelevanceIndex(int index)
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false,null,"changing relevance index");
		
		if (index < 0) {
			out = out.setAt0(true);
			out = out.setAt1("invalid index");
		}
		else if (index == relevanceIndex){
			out = out.setAt0(true);
			out = out.setAt1("index already at desired value");	
		}
		else
			relevanceIndex = index;
		return out;
	}
	public int getNameIndex()
	{
		return nameIndex;
	}
	public int getRelevanceIndex()
	{
		return relevanceIndex;
	}
	public int getEmailIndex()
	{
		return emailIndex;
	}
	public int getSheetIndex()
	{
		return currSheetIndex;
	}
	
	public Triplet<Boolean,String,String> setSheet(int index)
	{
		Triplet<Boolean,String,String> out = new Triplet<Boolean,String,String>(false,null,"changing current sheet");
		
		if (!(wbType == WORKBOOKTYPE.UNKNOWN)) {
			currSheetIndex = index;
			currSheet = workbook.getSheetAt(index);
		}else{
			out = out.setAt0(true);
			out = out.setAt1("file not selected");
		}
		return out;
	}
}