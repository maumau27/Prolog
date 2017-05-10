package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import Game.GameController;
import Game.MouseController;
import Game.Acao;
import Game.Cor;
import Game.Dicionario;
import Game.Direcao;
import Game.Sinal;
import Game.TipoAcao;

public class Tela extends JPanel{
	
	public int idTela;
	public boolean mouse_over = false;
	
	public Tela(int idTela)
	{
		this.idTela = idTela; 
		
		setPos();
	}

	public void setPos()
	{
		int leftX = 0, topY = 0;
		
		switch (this.idTela) {
			case 1:
				leftX=0;
				topY=0;
				break;
			case 2:
				leftX=400;
				topY=0;		
				break;
			case 3:
				leftX=0;
				topY=400;
				break;
			case 4:
				leftX=400;
				topY=400;
			break;
		}
		
		addMouseListener(new MouseController(this.idTela));
		
		this.setSize(new Dimension(400, 400));
		this.setPreferredSize(new Dimension(400,400));
		this.setLocation(leftX, topY);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;

		drawTela(this.idTela, g2d);
	} 
	
	
	private void drawTela(int idTela, Graphics2D g2d)
	{
		Color cor;
		Sinal sinal;
		double leftX=0.0;
		double topY=0.0;
		double larg=400.0;
		double alt=400.0;
		
		sinal = GameController.translator.getSinalTela(idTela);
		
		if(sinal == Sinal.apagada)
			cor = Color.BLACK;
		else
			cor = Dicionario.Cor_Color(GameController.translator.getCorTela(idTela));
		
		if(GameController.translator.getTrocaCorRecente() == idTela)
			cor = Color.GRAY;
		
		Rectangle2D rt1=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.setPaint(cor);
		g2d.fill(rt1);
	}
	
}
