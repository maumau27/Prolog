package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.MainFrameMenu;
import managers.KeyboardController;
import panels.TileSetSection;

public class TileSetFrame extends JFrame {
	
	public final int LARG_DEFAULT;
	public final int ALT_DEFAULT;
	
	private BufferedImage tileSet;
	private JPanel panel = new JPanel();
	
	public TileSetFrame(String s, File file)
	{
		super(s);
		
		try {
			tileSet = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		LARG_DEFAULT = tileSet.getWidth() + 1;
		ALT_DEFAULT = tileSet.getHeight() + 1;

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
	
	public void InitializePanel()
	{
		panel.setLayout(null);
		
		panel.setBackground(Color.WHITE);
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
