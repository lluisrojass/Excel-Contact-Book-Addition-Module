package XcelAutomation;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {

	private int index;
	 Map<Integer,String> errorMap;
	
	public ErrorHandler()
	{
		errorMap = new HashMap<Integer,String>();
		index = 0;
		initializeErrors();
	}
	public void initializeErrors() {
		errorMap.put(0,"");
		errorMap.put(45, "Issue encountered with "
				+ "initially accessing your .xls file, please take measures to reassure file stability.");
		errorMap.put(15, "Issue encountered with "
				+ "initially accessing your .xlsx file, please take measures to reassure file stability.");
		errorMap.put(34, "Careful, file chosen is not an .xls or .xlsx EXCEL file");
		errorMap.put(78,"Unkown Error editing file to compensate for custom sheet number");
		errorMap.put(77, "Sheet out of index");
		errorMap.put(119,"Careful, no file Chosen");
		errorMap.put(36, "Not all text fields are filled");
		errorMap.put(65,"No File Chosen");
		errorMap.put(6,"File written Successfully");
		errorMap.put(78, "Error Writing File");
	}
	public String findError(int index) {
		return errorMap.get(index);
	}
}
