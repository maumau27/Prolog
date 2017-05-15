package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Prolog.Translator;

public class MainFrame extends JFrame {
	
	public final int LARG_DEFAULT = 300;
	public final int ALT_DEFAULT = 300;
	private JPanel panel = new JPanel();
	
	public static Translator t = new Translator();
	public static MainFrame mf = new MainFrame("Teste");

	public MainFrame(String s)
	{
		super(s);
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		panel.setLayout(null);
		
		panel.setBackground(Color.WHITE);
		
		ArrayList<Point> tile_points = t.getTiles();
		Point player = MainFrame.t.getPlayerPos();
		
		for (Point point : tile_points) {
			panel.add(new Tile(point));
		}
		
		panel.add(new Player(player));
		
		getContentPane().add(panel);
		
		addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) {
				
				switch (e.getKeyChar()) {
				case 'w':
					System.out.println(e.getKeyChar());
					t.doAcao("mover_cima");
					break;

				default:
					break;
				}
			}
		});
				
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args)
	{
		mf.setVisible(true);
	}
}
