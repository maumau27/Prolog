package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import components.MainFrameMenu;
import managers.Controller;
import panels.GridSection;
import panels.TileSetSection;

public class PopUpFrame extends JFrame {
	
	public static final int POP_LARG_DEFAULT = 250;
	public static final int POP_ALT_DEFAULT = 100;
	
	private JPanel panel = new JPanel();
	
	public PopUpFrame(String s)
	{
		super(s);
		
		InitializePanel();
		
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
	
	public void InitializePanel()
	{
		panel.setLayout(null);
		
		panel.setBackground(Color.WHITE);
	}
	
	private void setConfig()
	{
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-POP_LARG_DEFAULT/2;
		int y=sa/2-POP_ALT_DEFAULT/2;
		
		add(panel);
		
		setLocation(new Point(x,y));
		getContentPane().setPreferredSize(new Dimension(POP_LARG_DEFAULT,POP_ALT_DEFAULT));
		pack();
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}
