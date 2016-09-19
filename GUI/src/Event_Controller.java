import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
* Controller that adds the funcitonality to the graphical component via action listeners 
* 
* TODO: Generalize one method to handle all three identical index modifying action listeners. 
*
* @author  Luis E. Rojas
* @version 2.0
* @since   2016-09-19 
*/

public class Event_Controller {
	
	org.javatuples.Triplet<Boolean,String,String> report;
	
	private Module_Window frame;
	private File_Handler file;
	
	public Event_Controller()
	{
		file = new File_Handler();
		frame = new Module_Window(file);
		addEvents();
	}
	
	
	private void addEvents(){
		
		/* on window close*/
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	file.close(); 
	        }
		});
		
		frame.addFileButtonActionlistener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(null);
				fc.setDialogTitle("Please slect an EXCEL file");
				fc.setMultiSelectionEnabled(false);
				if (fc.showOpenDialog(frame.getFileButton()) == JFileChooser.APPROVE_OPTION) {	 }
				if(!(fc.getSelectedFile() == null)) { 
					report = file.setSourceFile(fc.getSelectedFile().getAbsolutePath());
					alertUser(report);
				}
			}
		});
		
		
		frame.addInsertionButtonActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* is file null */
				if (file.isFileNull()) {
					triggerWarningLabelError("writing to file","no file attached");
					return;
				}
				/* are text fields empty */
				else if (frame.getEmailTextField().getText().equals("") || 
						frame.getNameTextField().getText().equals("") || 
						frame.getRelevanceTextField().getText().equals("")) 
				{
			 		triggerWarningLabelError("writing to file","empty text field(s)");
			 		return;
				} else /* write to file */{
				 	report = file.write(frame.getNameTextField().getText(), frame.getRelevanceTextField().getText(), frame.getEmailTextField().getText());
				 	alertUser(report);
				 	return;
				}
			}
		});
		
		
		
		
		frame.addNameColumnMenuItemActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						String newIndex;
						newIndex = JOptionPane.showInputDialog("Enter desired column number (currently "+file.getNameIndex()+")");
						
						try{
							report = file.setNameIndex(Integer.parseInt(newIndex));
						} catch (NullPointerException npe){
							triggerWarningLabelError("changing name index","no value provided");
							return;
						} catch (NumberFormatException nfe){
							triggerWarningLabelError("changing name index","not a number");
							return;
						}
						alertUser(report);
						// TODO: changing menu item text not always necessary
						frame.getNameColumnMenuItem().setText("Edit name column... (" + file.getNameIndex() + ")");
						return;
					}
				}
		);
		
		frame.addEmailColumnMenuItemActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						String newIndex;
						newIndex = JOptionPane.showInputDialog("Enter desired column number (currently "+file.getEmailIndex()+")");
						try{
							report = file.setEmailIndex(Integer.parseInt(newIndex));
						} catch (NullPointerException npe){
							triggerWarningLabelError("changing email index","no value provided");
							return;
						} catch (NumberFormatException nfe){
							triggerWarningLabelError("changing email index","not a number");
							return;
						}
						alertUser(report);
						// TODO: changing menu item text not always necessary
						frame.getEmailColumnMenuItem().setText("Edit email column... (" + file.getEmailIndex() + ")");
						return;
					}
				});
		
		frame.addRelevanceColumnMenuItemActionListener( 
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					String newIndex;
					newIndex = JOptionPane.showInputDialog("Enter desired column number (currently "+file.getRelevanceIndex()+")");
					try{
						report = file.setRelevanceIndex(Integer.parseInt(newIndex));
					} catch (NullPointerException npe){
						triggerWarningLabelError("changing relevance index","no value provided");
						return;
					} catch (NumberFormatException nfe){
						triggerWarningLabelError("changing relevance index","not a number");
						return;
					}
					alertUser(report);
					// TODO: changing menu item text not always necessary
					frame.getRelevanceColumnMenuItem().setText("Edit relevance column... (" + file.getRelevanceIndex() + ")");
					return;
				} 
			});
		
		frame.addEditFileMenuItemActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(null);
				fc.setDialogTitle("Please select an EXCEL file");
				fc.setMultiSelectionEnabled(false);
				if (fc.showOpenDialog(frame.getFileButton()) == JFileChooser.APPROVE_OPTION) {	 }
				if(!(fc.getSelectedFile() == null)) { 
					report = file.setSourceFile(fc.getSelectedFile().getAbsolutePath());
					alertUser(report);
				}
			}
		});
		
		frame.addEditSheetNumberMenuItemActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					String newIndex;
					newIndex = JOptionPane.showInputDialog("Enter desired column number (currently "+file.getSheetIndex()+")");
					try{
						report = file.setSheet(Integer.parseInt(newIndex));
					} catch (NullPointerException npe){
						triggerWarningLabelError("changing sheet number","no value provided");
						return;
					} catch (NumberFormatException nfe){
						triggerWarningLabelError("changing sheet number","not a number");
						return;
					}
					alertUser(report);
					// TODO: changing menu item text not always necessary
					frame.getSheetNumberMenuItem().setText("Edit sheet column... (" + file.getSheetIndex() + ")");
					return;
				}
			});
		
		frame.addAboutMenuItemActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					
					try {
						java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/lluisrojass/ExcelContactBook"));
					} catch (IOException e1) {
						triggerWarningLabelError("reaching 'about' page","unable to access internet browser");
					} catch  (URISyntaxException e1){
						triggerWarningLabelError("reaching 'about' page","bad URI link");
					}
				}
			});
		
	}
	
	public void alertUser(org.javatuples.Triplet<Boolean,String,String> report) {
		if (report.getValue0() == false){
			System.out.println(report.getValue0());
			frame.getWarningLabel().setForeground(java.awt.Color.GREEN);
			frame.getWarningLabel().setText("Success: " + report.getValue2());
		}
		else {
			frame.getWarningLabel().setForeground(java.awt.Color.RED);
			frame.getWarningLabel().setText("Error " + report.getValue2() + ": " + report.getValue1());
		}
		
		
	}
	protected void triggerWarningLabelError(String Action,String errorText) {
		frame.getWarningLabel().setForeground(java.awt.Color.RED);
		frame.getWarningLabel().setText("Error " + Action + ": " + errorText);
	}
	
	
	public void setVisible(Boolean flag) { frame.setVisible(flag); }
	
	public static void main(String[] args)
	{
		Event_Controller c = new Event_Controller();
		c.setVisible(true);
	}
}