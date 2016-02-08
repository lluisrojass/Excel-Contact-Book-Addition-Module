package XcelAutomation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controller {
	Window frame;
	Filebase file;
	ErrorHandler errorHandler;
	
	public Controller()
	{
		file = new Filebase();
		frame = new Window(file,errorHandler);
		errorHandler = new ErrorHandler();
		frame.addFileButtonActionlistener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.hazard.setForeground(Color.red);	
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(null);
				fc.setDialogTitle("Please choose an Excel file");
				fc.setMultiSelectionEnabled(false);
				if (fc.showOpenDialog(frame.fileButton) == JFileChooser.APPROVE_OPTION) {	 }
				if(!(fc.getSelectedFile() == null)) { 
					frame.hazard.setText(errorHandler.findError((file
							.setSourceFile(fc.getSelectedFile().getAbsolutePath()))));
				}
				else
				{
					frame.hazard.setText(errorHandler.findError(119));
				}
			}
		});
		frame.addLiftoffActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.hazard.setForeground(Color.red);	
				
				if (frame.email.getText().equals("") || frame.name.getText().equals("")
						|| frame.relevance.getText().equals("")) {
					frame.hazard.setText(errorHandler.findError(36));
					return;
				}
				else if (file.isFileNull()) {
					frame.hazard.setText(errorHandler.findError(65));
					return;
				}
				frame.hazard.setForeground(Color.green);
				frame.hazard.setText(errorHandler.findError(((file.write
						(frame.name.getText(), frame.relevance.getText(), frame.email.getText())))));
				
			}
		});
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	file.close();
	        }});
		frame.add_NameColumnActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						frame.hazard.setForeground(Color.red);	
						String choice = JOptionPane.showInputDialog(frame._nameColumn,"", "Enter Desired Column Number", JOptionPane.PLAIN_MESSAGE);
						frame.hazard.setText(errorHandler.findError(file.setNameIndex(Integer.parseInt(choice))));
						frame._nameColumn.setText("Edit Name Column.....(" + file.getNameIndex()+")");
					}
			
				});
		
	}
	public void setVisible()
	{
		frame.setVisible(true);
	}
	public void setInvisible()
	{
		frame.setVisible(false);
	}
	public static void main(String[] args)
	{
		Controller c = new Controller();
		c.setVisible();
	}
}
