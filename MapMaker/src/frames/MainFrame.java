package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.MainFrameMenu;
import managers.Controller;
import managers.KeyboardController;
import panels.Grid;
import panels.GridSection;
import panels.TileSetSection;

public class MainFrame extends JFrame {

	public static int LARG_DEFAULT = 800;
	public static int ALT_DEFAULT = 1200;
	private JPanel panel = new JPanel();
	public GridSection gridSection;
	public TileSetSection tileSetSection;
	public MainFrameMenu mainMenu;
	
	public MainFrame(String s)
	{
		super(s);

		InitializePanel();
		
		gridSection = new GridSection();
		panel.add(gridSection);
		
		mainMenu = new MainFrameMenu();
		setJMenuBar(mainMenu.menuBar);
		
		addKeyListener(new KeyboardController());
		
		setConfig();
	}
	
	public void panelAdd(Component c)
	{
		panel.add(c);
	}
	
	public void panelRemove(Component c)
	{
		panel.remove(c);
	}
	
	public void reDraw()
	{
		panel.repaint();
	}
	
	public void InitializePanel()
	{
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		
		panel.setLayout(null);
		
		panel.setBackground(Color.WHITE);
		
		LARG_DEFAULT = screenSize.width;
		ALT_DEFAULT = screenSize.height;
	}
	
	public void ClearPanel()
	{
		panel.removeAll();
	}
	
	public void ResetPanel()
	{
		remove(panel);
		panel = new JPanel();
		add(panel);
	}
	
	public Dimension getPanelSize()
	{
		return new Dimension(panel.getWidth(), panel.getHeight());
	}
	
	private void setConfig()
	{
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		add(panel);
		
		setLocation(new Point(x,y));
		getContentPane().setPreferredSize(new Dimension(LARG_DEFAULT,ALT_DEFAULT));
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
