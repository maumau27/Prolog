package Draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Prolog.Translator;

public class MainFrame extends JFrame {
	
	public static final int LARG_DEFAULT = 800;
	public static final int ALT_DEFAULT = 600;
	private JPanel panel = new JPanel();
	
	public static Translator t = new Translator();
	public static MainFrame mf;
	
	public static Dimension tileSize = new Dimension(t.getTileSize(), t.getTileSize());
	public static Dimension gridSize = new Dimension(t.getMapSize().x * tileSize.width + 1, t.getMapSize().y * tileSize.height + 1);
	public static Point deslocamento = new Point((MainFrame.LARG_DEFAULT / 2) - (MainFrame.gridSize.width / 2), (MainFrame.ALT_DEFAULT / 2) - (MainFrame.gridSize.height / 2));
	//public static Point deslocamento = new Point(0, 0);
	
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
		
		//panel.add(new Grid());
		
		panel.add(new Grid());
		add(panel);
		
		addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) {
				
				switch (e.getKeyChar()) {
					case 'w':
						t.doAcao("mover_cima");
						break;
					case 's':
						t.doAcao("mover_baixo");
						break;
					case 'd':
						t.doAcao("mover_esquerda");
						break;
					case 'a':
						t.doAcao("mover_direita");
						break;
	
					default:
						break;
				}
			}
		});
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				MainFrame.t.changeTileSize(e.getWheelRotation());
			}
		});
				
		setLocation(new Point(x,y));
		getContentPane().setPreferredSize(new Dimension(LARG_DEFAULT,ALT_DEFAULT));
		pack();
		
//		System.out.println("frame width : "+getWidth());
//		System.out.println("frame height: "+getHeight());
//		System.out.println("content pane width : "+getContentPane().getWidth());
//		System.out.println("content pane height: "+getContentPane().getHeight());
//		System.out.println("width  of left + right  borders: "+(getWidth()-getContentPane ().getWidth()));
//		System.out.println("height of top  + bottom borders: "+(getHeight()-getContentPane().getHeight()));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void addComponent(Component c)
	{
		panel.add(c);
	}
	
	public void Clear()
	{
		panel.removeAll();
	}
	
	public static void main(String[] args)
	{
		mf = new MainFrame("Teste");
		t.MakeMap();
//		JFrame mf2 = new JFrame("Teste2");
//		mf2.setBounds(200, 200, 100, 100);
//		mf2.setVisible(true);
		mf.setVisible(true);
	}
}
