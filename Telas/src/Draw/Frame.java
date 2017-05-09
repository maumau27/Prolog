package Draw;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Game.*;

public class Frame extends JFrame {
	
	public final int LARG_DEFAULT = 815;
	public final int ALT_DEFAULT = 830;
	private JPanel pane = new JPanel();

	double leftX=100.0;
	double topY=100.0;
	double larg=200.0;
	double alt=150.0;
	
	public Frame(String s)
	{
		super(s);
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void reDraw()
	{
		pane.repaint();
	}
	
	public void reset()
	{
		pane.removeAll();
	}
	
	public void addComponent(Component obj)
	{
		pane.add(obj);
	}
	
	public void resetPane()
	{
		getContentPane().removeAll();
		
		pane.setLayout(null);
		
		pane.setBackground(Color.WHITE);
		
		getContentPane().add(pane);
	}
}
