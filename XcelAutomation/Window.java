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


public class Window extends JFrame {
	
	private Filebase file;
	private ErrorHandler errorHandler;
	JPanel glass;
	JLabel hazard;
	JMenuBar mb;
	JMenu fileMenu;
	JMenu editMenu;
	JMenu helpMenu;
	JMenuItem about;
	JMenuItem _nameColumn;
	JLabel nameHeader;
	JTextField name;
	JLabel relHeader;
	JTextField relevance;
	JLabel emailHeader;
	JTextField email;
	JButton fileButton;
	JButton liftoff;

	public Window(Filebase file,ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		this.file = file;
		initialize();
	}
	
	private void initialize()
	{
		glass = new JPanel();
		glass.setBackground(Color.WHITE);
		
		
		
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
		
		
		_nameColumn = new JMenuItem("Edit Name Column.....");
		
		editMenu.add(_nameColumn);
		
		
		JMenuItem SpecRel = new JMenuItem("Edit Relevance Column.....");
		editMenu.add(SpecRel);
		
		JMenuItem SpecEm = new JMenuItem("Edit Email Column.....");
		editMenu.add(SpecEm);
		
		JMenuItem sheetNumber = new JMenuItem("Edit Sheet Number.....");
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
		
		relevance = new JTextField();
		relevance.setPreferredSize(new Dimension(250,20));
		relevance.setFont(new Font("futura",Font.PLAIN,12));
		glass.add(relevance);
		
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
		
		glass.add(fileButton);
		
		liftoff = new JButton("Apply");
		liftoff.setFont(new Font("futura",Font.PLAIN,10));
		liftoff.setHorizontalAlignment(SwingConstants.CENTER);
		liftoff.setPreferredSize(new Dimension(80,30));
		glass.add(liftoff);
		
		glass.add(hazard);
		
		
		    
		setWindowPosition(this,2);
		setPreferredSize(new Dimension(400,215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(glass);
		pack();
		setVisible(true);
	}
	
	public void addNameActionlistener(ActionListener a)
	{
		name.addActionListener(a);
	}
	public void addEmailActionlistener(ActionListener a)
	{
		email.addActionListener(a);
	}
	public void addFileButtonActionlistener(ActionListener a)
	{
		fileButton.addActionListener(a);
	}
	public void addrelevancelistener(ActionListener a)
	{
		relevance.addActionListener(a);
	}
	public void addLiftoffActionListener(ActionListener a)
	{
		liftoff.addActionListener(a);
	}
	public void add_NameColumnActionListener(ActionListener a)
	{
		_nameColumn.addActionListener(a);
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
}
