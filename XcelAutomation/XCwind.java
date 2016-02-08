package XcelAutomation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class XCwind extends JFrame {
	
	XcFilebase excelFile;
	XcErrorHandler errorHandler;
	JPanel glass;
	JEditorPane pane;
	JLabel hazard;
	JMenuBar mb;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu helpMenu;
	JMenuItem about;
	JMenuItem SpecName;
	JLabel nameHeader;
	JTextField name;
	JLabel relHeader;
	JTextField relevence;
	JLabel emailHeader;
	JTextField email;
	JButton fileButton;
	JButton liftoff;
	
	
	
	public XCwind() {
		excelFile = new XcFilebase();
		errorHandler = new XcErrorHandler();
		initialize();
	}
	
	private void initialize()
	{
		glass = new JPanel();
		glass.setBackground(Color.WHITE);
		JFileChooser fc = new JFileChooser();
		
		
		setResizable(false);
		
		hazard = new JLabel();
		hazard.setPreferredSize(new Dimension(400,25));
		hazard.setHorizontalAlignment(SwingConstants.CENTER);
		hazard.setForeground(Color.red);
		
		mb = new JMenuBar();
		fileMenu = new JMenu("File");
		mb.add(fileMenu);
		editMenu = new JMenu("Edit");
		mb.add(editMenu);
		helpMenu = new JMenu("About");
		mb.add(helpMenu);
		
		
		about = new JMenuItem("About");
		helpMenu.add(about);
		
		
		SpecName = new JMenuItem("Edit Name Column.....(" + excelFile.getNameIndex()+")");
		SpecName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String choice = JOptionPane.showInputDialog(SpecName,"", "Enter Desired Column Number", JOptionPane.PLAIN_MESSAGE);
				hazard.setText(errorHandler.findError(excelFile.setNameIndex(Integer.parseInt(choice))));
				SpecName.setText("Edit Name Column.....(" + excelFile.getNameIndex()+")");
			}
	
		});

		
		editMenu.add(SpecName);
		
		
		JMenuItem SpecRel = new JMenuItem("Edit Relevance Column....");
		editMenu.add(SpecRel);
		
		JMenuItem SpecEm = new JMenuItem("Edit Email Column....(" + excelFile.getEmailIndex()+")");
		editMenu.add(SpecEm);
		
		JMenuItem sheetNumber = new JMenuItem("Edit Sheet Number....");
		editMenu.add(sheetNumber);
		
		setJMenuBar(mb);
		
		nameHeader = new JLabel("Name:");
		nameHeader.setFont(new Font("futura",Font.PLAIN,20));
		nameHeader.setHorizontalTextPosition(SwingConstants.CENTER);
		glass.add(nameHeader);
		
		
		name = new JTextField();
		name.setPreferredSize(new Dimension(303,20));
		name.setFont(new Font("futura",Font.PLAIN,12));
		glass.add(name);
		
		relHeader = new JLabel("Relationship:");
		relHeader.setFont(new Font("futura",Font.PLAIN,20));
		relHeader.setHorizontalTextPosition(SwingConstants.CENTER);
		glass.add(relHeader);
		
		relevence = new JTextField();
		relevence.setPreferredSize(new Dimension(250,20));
		relevence.setFont(new Font("futura",Font.PLAIN,12));
		glass.add(relevence);
		
		emailHeader = new JLabel("Email:");
		emailHeader.setFont(new Font("futura",Font.PLAIN,20));
		emailHeader.setHorizontalTextPosition(SwingConstants.CENTER);
		glass.add(emailHeader);
		
		email = new JTextField();
		email.setFont(new Font("futura",Font.PLAIN,12));
		email.setPreferredSize(new Dimension(310,20));
		glass.add(email);
		
		
		
		fileButton = new JButton("Choose File");
		fileButton.setFont(new Font("futura",Font.PLAIN,10));
		fileButton.setPreferredSize(new Dimension(250,30));
		fileButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				fc.setCurrentDirectory(null);
				fc.setDialogTitle("Please choose an Excel file");
				fc.setMultiSelectionEnabled(false);
				if (fc.showOpenDialog(fileButton) == JFileChooser.APPROVE_OPTION) {	 }
				if(!(fc.getSelectedFile() == null)) { 
					hazard.setText(errorHandler.findError((excelFile
							.setSourceFile(fc.getSelectedFile().getAbsolutePath()))));
				}
				else
				{
					hazard.setText(errorHandler.findError(119));
				}
			}
		});
		
		glass.add(fileButton);
		
		liftoff = new JButton("Apply");
		liftoff.setFont(new Font("futura",Font.PLAIN,10));
		liftoff.setHorizontalAlignment(SwingConstants.CENTER);
		liftoff.setPreferredSize(new Dimension(80,30));
		glass.add(liftoff);
		liftoff.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (email.getText().equals("") || name.getText().equals("")
						|| relevence.getText().equals("")) {
					hazard.setText(errorHandler.findError(36));
					return;
				}
				else if (excelFile.isFileNull()) {
					hazard.setText(errorHandler.findError(65));
					return;
				}
				if (errorHandler.findError((excelFile.write
						(name.getText(), relevence.getText(), email.getText()))).equals("File written Successfully"))
						{
							hazard.setForeground(Color.green);
						}
				hazard.setText(errorHandler.findError(((excelFile.write
						(name.getText(), relevence.getText(), email.getText())))));
				hazard.setForeground(Color.red);
				long timer = System.currentTimeMillis() / 1000000;
				while(timer < 2)
				{
					
				}
				
			}
		});
		
		glass.add(hazard);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        	excelFile.close();
		        }
		});
		    
		setWindowPosition(this,2);
		setPreferredSize(new Dimension(400,215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(glass);
		pack();
		setVisible(true);
	}
	
	private void setWindowPosition(JFrame window, int screen)
	{        
	    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] allDevices = env.getScreenDevices();
	    int topLeftX, topLeftY, screenX, screenY, windowPosX, windowPosY;

	    if (screen < allDevices.length && screen > -1) {
	        topLeftX = allDevices[screen].getDefaultConfiguration().getBounds().x;
	        topLeftY = allDevices[screen].getDefaultConfiguration().getBounds().y;

	        screenX  = allDevices[screen].getDefaultConfiguration().getBounds().width;
	        screenY  = allDevices[screen].getDefaultConfiguration().getBounds().height;
	    }
	    else {
	        topLeftX = allDevices[0].getDefaultConfiguration().getBounds().x;
	        topLeftY = allDevices[0].getDefaultConfiguration().getBounds().y;

	        screenX  = allDevices[0].getDefaultConfiguration().getBounds().width;
	        screenY  = allDevices[0].getDefaultConfiguration().getBounds().height;
	    }

	    windowPosX = ((screenX - window.getWidth())  / 2) + topLeftX;
	    windowPosY = ((screenY - window.getHeight()) / 2) + topLeftY;
	    window.setLocation(windowPosX, windowPosY);
	}
	
	public static void main(String[] args)
	{
		XCwind c =  new XCwind();
	}
}
