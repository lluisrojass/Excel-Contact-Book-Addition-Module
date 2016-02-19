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
	JMenuBar _mb;
	JMenu _fileMenu;
	JMenu _editMenu;
	JMenu _helpMenu;
	JMenuItem _about;
	JMenuItem _nameColumn;
	JLabel nameHeader;
	JTextField name;
	JLabel relHeader;
	JTextField relevance;
	JLabel emailHeader;
	JTextField email;
	JButton fileButton;
	JButton liftoff;
	JMenuItem _relevanceColumn;
	JMenuItem _emailColumn;
	JMenuItem _editFile;
	JMenuItem _sheetNumber;
	

	public Window(Filebase file,ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
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
		
		_mb = new JMenuBar();
		_fileMenu = new JMenu("File");
		_mb.add(_fileMenu);
		_editMenu = new JMenu("Edit");
		_mb.add(_editMenu);
		_helpMenu = new JMenu("About");
		_mb.add(_helpMenu);
		
		_editFile = new JMenuItem("Open File");
		_fileMenu.add(_editFile);
		
		_about = new JMenuItem("About");
		_helpMenu.add(_about);
		
		_nameColumn = new JMenuItem("Edit Name Column.....");
		_editMenu.add(_nameColumn);
		
		
		
		_relevanceColumn = new JMenuItem("Edit Relevance Column.....");
		_editMenu.add(_relevanceColumn);
		
		_emailColumn = new JMenuItem("Edit Email Column.....");
		_editMenu.add(_emailColumn);
		
		_sheetNumber = new JMenuItem("Edit Sheet Number.....");
		_editMenu.add(_sheetNumber);
		
		setJMenuBar(_mb);
		
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
	public void add_SheetNumberActionListener(ActionListener a)
	{
		_sheetNumber.addActionListener(a);
	}
	public void add_EditFileActionListener(ActionListener a)
	{
		_editFile.addActionListener(a);
	}
	public void add_AboutActionListener(ActionListener a)
	{
		_about.addActionListener(a);
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
	public void add_EmailColumnActionListener(ActionListener a)
	{
		_emailColumn.addActionListener(a);
	}
	public void add_RelevanceColumnActionListener(ActionListener a)
	{
		_relevanceColumn.addActionListener(a);
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
