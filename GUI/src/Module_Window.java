

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Module_Window extends JFrame {
	
	/*Swing Variables*/
	/*JPanel*/
	private JPanel panel;
	/*JLabel*/
	private JLabel warningLabel;
	private JLabel nameLabel;
	private JLabel relevanceLabel;
	private JLabel emailLabel;
	/*JMenu items*/
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu helpMenu;
	private JMenuItem aboutMenuItem;
	private JMenuItem editNameColumnMenuItem;
	private JMenuItem editRelevanceColumnMenuItem;
	private JMenuItem editEmailColumnMenuItem;
	private JMenuItem editFileMenuItem;
	private JMenuItem editSheetNumberMenuItem;
	/*JTextFields*/
	private JTextField nameTextField;
	private JTextField relevanceTextField;
	private JTextField emailTextField;
	/*JButton*/
	private JButton fileButton;
	private JButton insertionButton;
	

	public Module_Window(File_Handler file,Error_Handler errorHandler) {
		swingInit();
	}
	
	private void swingInit()
	{
		/* local label, button and text field fonts */
		java.awt.Font labelFont = new java.awt.Font("Helvetica Neue",java.awt.Font.PLAIN,20);
		java.awt.Font textFieldFont = new java.awt.Font("Helvetica Neue",java.awt.Font.PLAIN,12);
		java.awt.Font buttonFont = new java.awt.Font("Helvetica Neue",java.awt.Font.PLAIN,12);
		
		panel = new JPanel();
		panel.setBackground(java.awt.Color.WHITE);
		
		warningLabel = new JLabel();
		warningLabel.setPreferredSize(new Dimension(400,35));
		warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warningLabel.setForeground(java.awt.Color.black);
		warningLabel.setText("Reminder: File must be closed");
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		helpMenu = new JMenu("About");
		menuBar.add(helpMenu);
		
		editFileMenuItem = new JMenuItem("Open File...");
		fileMenu.add(editFileMenuItem);
		
		aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		
		editNameColumnMenuItem = new JMenuItem("Edit Name Column...");
		editMenu.add(editNameColumnMenuItem);
		
		editRelevanceColumnMenuItem = new JMenuItem("Edit Relevance Column...");
		editMenu.add(editRelevanceColumnMenuItem);
		
		editEmailColumnMenuItem = new JMenuItem("Edit Email Column...");
		editMenu.add(editEmailColumnMenuItem);
		
		editSheetNumberMenuItem = new JMenuItem("Edit Sheet Number...");
		editMenu.add(editSheetNumberMenuItem);
		
		setJMenuBar(menuBar);
		
		nameLabel = new JLabel("Name:");
		nameLabel.setFont(labelFont);
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(nameLabel);
		
		
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(303,20));
		nameTextField.setFont(textFieldFont);
		panel.add(nameTextField);
		
		relevanceLabel = new JLabel("Relationship:");
		relevanceLabel.setFont(labelFont);
		relevanceLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(relevanceLabel);
		
		relevanceTextField = new JTextField();
		relevanceTextField.setPreferredSize(new Dimension(250,20));
		relevanceTextField.setFont(textFieldFont);
		panel.add(relevanceTextField);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setFont(labelFont);
		emailLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setFont(textFieldFont);
		emailTextField.setPreferredSize(new Dimension(310,20));
		panel.add(emailTextField);
		
		
		fileButton = new JButton("Choose File");
		fileButton.setFont(buttonFont);
		fileButton.setPreferredSize(new Dimension(250,30));
		panel.add(fileButton);
		
		insertionButton = new JButton("Apply");
		insertionButton.setFont(buttonFont);
		insertionButton.setHorizontalAlignment(SwingConstants.CENTER);
		insertionButton.setPreferredSize(new Dimension(80,30));
		panel.add(insertionButton);
		
		panel.add(warningLabel);
		
		setResizable(false);
		setWindowPosition(this,2);
		setPreferredSize(new Dimension(400,215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		pack();
		setVisible(true);
	}
	
	protected JButton getFileButton(){
		return fileButton;
	}
	protected JLabel getWarningLabel(){
		return warningLabel;
	}
	
	/* add action listeners */
	public void addNameTextFieldActionlistener(ActionListener a) 
	{
		nameTextField.addActionListener(a);
	}
	public void addEditSheetNumberMenuItemActionListener(ActionListener a)
	{
		editSheetNumberMenuItem.addActionListener(a);
	}
	public void addEditFileMenuItemActionListener(ActionListener a)
	{
		editFileMenuItem.addActionListener(a);
	}
	public void addAboutMenuItemActionListener(ActionListener a)
	{
		aboutMenuItem.addActionListener(a);
	}
	public void addEmailTextFieldActionlistener(ActionListener a)
	{
		emailTextField.addActionListener(a);
	}
	public void addFileButtonActionlistener(ActionListener a)
	{
		fileButton.addActionListener(a);
	}
	public void addRelevanceTextFieldActionlistener(ActionListener a)
	{
		relevanceTextField.addActionListener(a);
	}
	public void addInsertionButtonActionListener(ActionListener a)
	{
		insertionButton.addActionListener(a);
	}
	public void addNameColumnMenuItemActionListener(ActionListener a)
	{
		editNameColumnMenuItem.addActionListener(a);
	}
	public void addEmailColumnMenuItemActionListener(ActionListener a)
	{
		editEmailColumnMenuItem.addActionListener(a);
	}
	public void addRelevanceColumnMenuItemActionListener(ActionListener a)
	{
		editRelevanceColumnMenuItem.addActionListener(a);
	}
	
	public JTextField getEmailTextField(){
		return emailTextField;
	}
	public JTextField getRelevenceTextField(){
		return relevanceTextField;
	}
	public JTextField getNameTextField(){
		return nameTextField;
	}
	
	public void setWarningLabel(java.awt.Color labelColor, String label){
		warningLabel.setForeground(labelColor);
		warningLabel.setText(label);
	}
	
	
	/* Set window position to center of screen no matter the OS */
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
}