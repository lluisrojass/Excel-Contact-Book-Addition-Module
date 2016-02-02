package XcelAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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


public class XcFilebase {

	private Workbook bK; //find out if XSF and HSSF have a parent class
	private Sheet currSheet;
	private File XLFile;
	private FileInputStream fis;
	private FileOutputStream fos;
	private Row currRow;
	private Cell currCell;
	private short Wtype;
	
	private int emailIndex;
	private int relevanceIndex;
	private int nameIndex;
	
	public XcFilebase()
	{ 
		Wtype = -1;
	}
		
	public XcFilebase(String pathname) throws IOException
	{
		setSourceFile(pathname);
	}
	
	public void close() 
	{
		try
		{
			if (Wtype != -1)
			{
				bK.close();
			}
		}
		catch(IOException e)
		{
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
		
		try{
			fos = new FileOutputStream(XLFile); 
			bK.write(fos);
			fos.close();
			return 6;
		}
		catch (IOException e) {	return 78; } 
	}
	
	public int setSourceFile(String pathname)
	{
		if (pathname.substring(pathname.length() - 5,pathname.length()).equals(".xlsx")) {
			try {
				XLFile = new File(pathname);
				fis = new FileInputStream(XLFile);
				bK = new XSSFWorkbook(fis);
				Wtype = 1;
				initializeSemantics();
				return 0;
			}
			catch(Exception e) {
				return 15;
			}
		}
		else if (pathname.substring(pathname.length() - 4, pathname.length()).equals(".xls")) {
			try {
		
				XLFile = new File(pathname);
				fis = new FileInputStream(XLFile);
				bK = new HSSFWorkbook(fis); 
				Wtype = 0;
				initializeSemantics();
				return 0;
			}
			catch(Exception e) {
				e.printStackTrace();
				return 45;
			}
		}
		else
		{
			System.out.println("Exception: File not valid");
			return 34;
		}
	}
	public boolean isFileNull() {
		if (bK == null) {
			return true;
		}
		return false;		
	}
	
	public void initializeSemantics()
	{
		emailIndex = 2;
		nameIndex = 0;
		relevanceIndex = 1;
		
		if (Wtype == 1)
		{
			currSheet = bK.getSheetAt(0);
			return;
		}
		else if (Wtype == 0)
		{	
			currSheet = bK.getSheetAt(0);
			return;
		}
		else if (Wtype == -1)
		{
			System.out.println("Error, initializing semantics");
			return;
		}
		
	}
	public boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
	}
	public int setNameIndex(int index)
	{
		if (index == emailIndex)
		{
			emailIndex += 1;
		}
		if (index == relevanceIndex)
		{
			relevanceIndex += 1;
		}
		
		nameIndex = index;
		return 0;
	}
	public int setEmailIndex(int index)
	{
		if (index == emailIndex)
		{
			emailIndex += 1;
		}
		if (index == relevanceIndex)
		{
			relevanceIndex += 1;
		}
		
		emailIndex = index;
		return 0;
	}
	public int setRelevanceIndex(int index)
	{
		if (index == nameIndex)
		{
			 
		}
		if (index == relevanceIndex)
		{
			relevanceIndex += 1;
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
	
	
	
	public double setSheet(int index)
	{
		if (Wtype == 1) {
			try {
				currSheet = bK.getSheetAt(index);
				return 0;
			}
			catch (Exception sheetOutOfIndex) {
				//sheetOutOfIndex.printStackTrace();
				
				System.out.println("Exception thrown: Sheet setting on type 1, sheet might be out of index");
				return 77;
			}
		}
		else if (Wtype == 0)
		{
			try {
				currSheet = bK.getSheetAt(index);
				return 0;
			}
			catch (Exception sheetOutOfIndex) {
				//sheetOutOfIndex.printStackTrace();
				System.out.println("Exception thrown: Sheet setting on type 0 sheet might be out of index");
				return 77;
			}
		}
		System.out.print("Unkown error when setting custom sheet");
		return 78;
	}
}
