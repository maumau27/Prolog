package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Game.Acao;
import Game.GameController;
import Game.TipoAcao;

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

		add(new Placar());
		for (int i = 1; i < 5; i++) {
			add(new Tela(i));
		}
		
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				
			}
			
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case 'a':
					GameController.translator.doAcao(new Acao(1, TipoAcao.troca_sinal));
					break;
				case 's':
					GameController.translator.doAcao(new Acao(2, TipoAcao.troca_sinal));
					break;
				case 'd':
					GameController.translator.doAcao(new Acao(3, TipoAcao.troca_sinal));
					break;
				case 'f':
					GameController.translator.doAcao(new Acao(4, TipoAcao.troca_sinal));
					break;

				default:
					break;
				}
			}
		});
		
	}
	
	public void reDraw()
	{
		repaint();
	}
}
