
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// Apache javatuples imports

import org.javatuples.Triplet;


// Apache Jar imports
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class File_Handler{
	
	private enum WorkbookType { 
		/*. xlsx */EXCELCURRENT, /* .xls */EXCELPRE2004,/*unkown*/ UNKNOWN
	}
	
	/*Excel File Variables*/
	private Workbook workbook; 
	private File currFile;
	private Sheet currSheet;
	/* Type of Workbook */
	private WorkbookType wbType; 
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
		wbType = WorkbookType.UNKNOWN;
		/* starting indexes */
		nameIndex = 0;
		relevanceIndex = 1;
		emailIndex = 2;
	}
		
	public File_Handler(String pathname) 
	{
		setSourceFile(pathname);
		
		wbType = WorkbookType.UNKNOWN;
		/* starting indexes */
		nameIndex = 0;
		relevanceIndex = 1;
		emailIndex = 2;
	}
	
	public void close() 
	{
		try {
			if (!(wbType == WorkbookType.UNKNOWN)) {
				fis.close();
				fos.close();
				workbook.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public int write(String name,String relevence,String email)
	{

		int workingRow = currSheet.getLastRowNum() + 1;
		currSheet.createRow(workingRow);
		currSheet.getRow(workingRow).createCell(nameIndex).setCellValue(name);
		currSheet.getRow(workingRow).createCell(relevanceIndex).setCellValue(relevence);
		currSheet.getRow(workingRow).createCell(emailIndex).setCellValue(email);
		
		try {
			fos = new FileOutputStream(currFile); 
			workbook.write(fos);
			fos.close();
			return 6;
		}
		catch (IOException e) {	
			e.printStackTrace();
			return /*TODO: something bad happened */
		} 
	}
	
	public int setSourceFile(String pathname)
	{
		/* check file extension for .xlsx (Pre-2007 Excel File) */
		String extension = pathname.substring(pathname.length() - 4,pathname.length());
		try {
			currFile = new File(pathname);
			fis = new FileInputStream(currFile);
			switch(extension) {
				case "xlsx":	
					workbook = new XSSFWorkbook(fis);
					wbType = WorkbookType.EXCELCURRENT;
					break;
				case ".xls":
					workbook = new HSSFWorkbook(fis); 
					wbType = WorkbookType.EXCELPRE2004;
					break;
				default:
					/*TODO: bad file path*/
			}
			currSheet = workbook.getSheetAt(0);
		}
		catch (IOException w){
			/* return bad things happened indicator */
		}
		/* TODO: return good things happened indicator */
	}
	
	
	public boolean isFileNull() {
		return wbType == WorkbookType.UNKNOWN;
	}
	
	
	/*public boolean isRowEmpty(Row row) {
	    for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
	        Cell cell = row.getCell(i);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}*/
	
	public int setNameIndex(int index)
	{
		if (index < 0)
		{
			return 33;
		}
		nameIndex = index;
		return 0;
	}
	
	
	public int setEmailIndex(int index)
	{
		emailIndex = (index < 0 && !(index == emailIndex)) ? emailIndex : index;
		int returnCode = (emailIndex == index) ? 0 : 1;
		return returnCode;
	}
	public int setRelevanceIndex(int index)
	{
		if (index < 0)
		{
			return 33;
		}
		relevanceIndex = index;
		return 0;
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
	
	public int setSheet(int index)
	{
		if (!(wbType == WorkbookType.UNKNOWN)) {
			try {
				currSheetIndex = index;
				currSheet = workbook.getSheetAt(index);
				return 0;
			}
			catch (Exception sheetOutOfIndex) {
				/* return sheet out of index */
			}
		}
		else
		{
			/* return no workbook loaded */
		}
		return 26;
	}
}