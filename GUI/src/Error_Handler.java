import java.util.HashMap;
import java.util.Map;

public class Error_Handler {

	private int index;
	 Map<Integer,String> errorMap;
	
	public Error_Handler()
	{
		errorMap = new HashMap<Integer,String>();
		index = 0;
		initializeErrors();
	}
	public void initializeErrors() {
		errorMap.put(0,"");
		errorMap.put(45, "Issue Encountered With "
				+ "Initially Accessing Your .xls File, Please Reassure File Stability.");
		errorMap.put(15, "Issue encountered with "
				+ "Initially accessing your .xlsx File, File May Be Unstabable.");
		errorMap.put(34, "File Chosen Is Not An .xls or .xlsx Microsoft file");
		errorMap.put(78, "Unkown Error Editing File To Compensate For Custom Sheet Number");
		errorMap.put(77, "Sheet Out Of Index");
		errorMap.put(119,"Careful, No File Chosen");
		errorMap.put(36, "Not All Text Fields Are Filled");
		errorMap.put(65,"No File Chosen");
		errorMap.put(6,"File Successfully Written");
		errorMap.put(78, "Error Writing File");
		errorMap.put(26, "No File Selected");
		errorMap.put(33,"Input Invalid");
	}
	public String findError(int index) {
		return errorMap.get(index);
	}
}