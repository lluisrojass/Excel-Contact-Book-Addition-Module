package XcelAutomation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Controller {
	Window frame;
	Filebase file;
	ErrorHandler errorHandler;
	
	public Controller()
	{
		file = new Filebase();
		frame = new Window(file,errorHandler);
		errorHandler = new ErrorHandler();
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        	file.close();
	}});
		
		frame.addFileButtonActionlistener(new ActionListener(){

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
					frame.hazard.setForeground(new Color(228, 230, 0));
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
		
		
		
		frame.add_NameColumnActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						try
						{
							frame.hazard.setForeground(Color.red);	
							int choice = Integer.parseInt(JOptionPane.showInputDialog(frame._nameColumn,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));
							String errorOutcome = errorHandler.findError(file.setNameIndex(choice));
							if (errorOutcome.equals(""))
							{
								frame._nameColumn.setText("Edit Name Column.....(" + file.getNameIndex()+")");
							}
							frame.hazard.setText(errorOutcome);
						}
						catch(NullPointerException e2) { } catch (NumberFormatException e3)
						{
							frame.hazard.setText("Not a valid Number");	
						}
					}
		});
		
		frame.add_EmailColumnActionListener( 
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						try
						{
							frame.hazard.setForeground(Color.red);	
							int choice = Integer.parseInt(JOptionPane.showInputDialog(frame._emailColumn,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));				
							String errorOutcome = errorHandler.findError(file.setEmailIndex(choice));
							if (errorOutcome.equals(""))
							{
								frame._emailColumn.setText("Edit Email Column.....(" + file.getEmailIndex()+")");
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
		
		frame.add_RelevanceColumnActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
							frame.hazard.setForeground(Color.red);	
							int choice = Integer.parseInt(JOptionPane.showInputDialog(frame._relevanceColumn,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));
							String errorOutcome = errorHandler.findError(file.setRelevanceIndex(choice));
							if (errorOutcome.equals(""))
							{
								frame._relevanceColumn.setText("Edit Relevance Column.....(" + file.getRelevanceIndex()+")");
							}
							frame.hazard.setText(errorOutcome);
						}
						catch(NullPointerException e2) { } catch (NumberFormatException e3)
						{
							frame.hazard.setText("Not a valid Number");	
						}
					}
				});
		
		frame.add_EditFileActionListener(new ActionListener(){
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
		
		frame.add_SheetNumberActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					frame.hazard.setForeground(Color.red);	
					int choice = Integer.parseInt(JOptionPane.showInputDialog(frame._sheetNumber,null, "Enter Desired Column Number", JOptionPane.INFORMATION_MESSAGE));
					String outcome = errorHandler.findError(file.setSheet(choice));
					if (outcome.equals(""))
					{
						frame._sheetNumber.setText("Edit Sheet Number.....(" + file.getSheetIndex()+")");		
					}
					frame.hazard.setText(outcome);
						
				}
				catch(NullPointerException e2) { } catch (NumberFormatException e3)
				{
					frame.hazard.setText("Not a Valid Input");	
				}
			}
		});
		
		frame.add_AboutActionListener(new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						try {
							java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/lluisrojass/ExcelContactBook"));
						} catch (IOException | URISyntaxException e1) {
							
							frame.hazard.setText("Issues accessing the internet");
						}
						
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
