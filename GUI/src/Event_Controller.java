import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Event_Controller {
	
	private Module_Window frame;
	private File_Handler file;
	private Error_Handler errorHandler;
	
	public Event_Controller()
	{
		file = new File_Handler();
		errorHandler = new Error_Handler();
		frame = new Module_Window(file,errorHandler);
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
1
			public void actionPerformed(ActionEvent e) {
				//frame.hazard.setForeground(Color.red);	
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(null);
				fc.setDialogTitle("Please choose a file");
				fc.setMultiSelectionEnabled(false);
				if (fc.showOpenDialog(frame.getFileButton()) == JFileChooser.APPROVE_OPTION) {	 }
				if(!(fc.getSelectedFile() == null)) { 
					//frame.hazard.setText(errorHandler.findError((file
							//.setSourceFile(fc.getSelectedFile().getAbsolutePath()))));
				}
				else
				{
					//frame.hazard.setText(errorHandler.findError(119));
				}
			}
		});
		
		
		frame.addInsertionButtonActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				
				if (frame.getEmailTextField().getText().equals("") || frame.getNameTextField().getText().equals("")
						|| frame.getRelevenceTextField().getText().equals("")) {
					
					/*frame.getWarningLabel().setForeground(new Color(228, 230, 0));
					frame.getWarningLabel().setText(errorHandler.findError(36));*/
					
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
		
		
		
		frame.addNameColumnMenuItemActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						try
						{
							frame.hazard.setForeground(Color.red);	
							int choice = Integer.parseInt(JOptionPane.showInputDialog(frame.editNameColumnMenuItem,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));
							String errorOutcome = errorHandler.findError(file.setNameIndex(choice));
							if (errorOutcome.equals(""))
							{
								frame.editNameColumnMenuItem.setText("Edit Name Column.....(" + file.getNameIndex()+")");
							}
							frame.hazard.setText(errorOutcome);
						}
						catch(NullPointerException e2) { } catch (NumberFormatException e3)
						{
							frame.hazard.setText("Not a valid Number");	
						}
					}
		});
		
		frame.addEmailColumnMenuItemActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						try
						{
							frame.hazard.setForeground(Color.red);	
							int choice = Integer.parseInt(JOptionPane.showInputDialog(frame.editEmailColumnMenuItem,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));				
							String errorOutcome = errorHandler.findError(file.setEmailIndex(choice));
							if (errorOutcome.equals(""))
							{
								frame.editEmailColumnMenuItem.setText("Edit Email Column.....(" + file.getEmailIndex()+")");
							}
							frame.hazard.setText(errorOutcome);
						}
						catch(NullPointerException e2) { 
							frame.hazard.setText("Unkown Error");
						} catch (NumberFormatException e3)
						{
							frame.hazard.setText("Not a valid Number");	
						}
					}
		});
		
		frame.addRelevanceColumnMenuItemActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							frame.hazard.setForeground(Color.red);	
							int choice = Integer.parseInt(JOptionPane.showInputDialog(frame.editRelevanceColumnMenuItem,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));
							String errorOutcome = errorHandler.findError(file.setRelevanceIndex(choice));
							if (errorOutcome.equals(""))
							{
								frame.editRelevanceColumnMenuItem.setText("Edit Relevance Column.....(" + file.getRelevanceIndex()+")");
							}
							frame.hazard.setText(errorOutcome);
						}
						catch(NullPointerException e2) { } catch (NumberFormatException e3)
						{
							frame.hazard.setText("Not a valid Number");	
						}
					}
				});
		
		frame.addEditFileMenuItemActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
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
		}});
		
		frame.addEditSheetNumberMenuItemActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					frame.hazard.setForeground(Color.red);	
					int choice = Integer.parseInt(JOptionPane.showInputDialog(frame.editSheetNumberMenuItem,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));
					String outcome = errorHandler.findError(file.setSheet(choice));
					if (outcome.equals(""))
					{
						frame.editSheetNumberMenuItem.setText("Edit Sheet Number.....(" + file.getSheetIndex()+")");		
					}
					frame.hazard.setText(outcome);
						
				}
				catch(NullPointerException e2) { } catch (NumberFormatException e3)
				{
					frame.hazard.setText("Not a Valid Input");	
				}
			}
		});
		
		frame.addAboutMenuItemActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					try {
						java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/lluisrojass/ExcelContactBook"));
					} catch (IOException | URISyntaxException e1) {
						
						frame.hazard.setText("ISSUES ACCESSING THE INTERNET");
					}
					
				}
			});
		
	}
	
	public void setHazard() {
		
		
	}
	
	
	public void setVisible(Boolean flag) { frame.setVisible(flag); }
	
	public static void main(String[] args)
	{
		Event_Controller c = new Event_Controller();
		c.setVisible(true);
	}
}