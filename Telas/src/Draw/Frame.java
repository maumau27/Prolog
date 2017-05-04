package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Game.*;

public class Frame extends JFrame {
	
	public final int LARG_DEFAULT = 815;
	public final int ALT_DEFAULT = 830;
	JPanel p = new JPanel();
	
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
		
		getContentPane().setLayout(null);
		
		p.setBackground(Color.WHITE);
		getContentPane().add(p);
		
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void reDraw()
	{
		repaint();
	}
}
