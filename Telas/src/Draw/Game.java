package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import Game.Controller;
import Game.Cor;
import Game.Sinal;

public class Game extends JPanel{
	
	public Game()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setSize(new Dimension(800, 800));
		this.setPreferredSize(new Dimension(800,800));
		this.setLocation(0, 0);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;

		for (int i = 1; i < 5; i++) {
			drawTela(i, g2d);
		}
		
		drawPlacar(g2d);
		
		repaint();
	} 
	
	private  void drawPlacar(Graphics2D g2d)
	{
		double leftX=325.0;
		double topY=375.0;
		double larg=150.0;
		double alt=50.0;
		Cor vencedor;
		
		Rectangle2D rt1=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.setPaint(Color.WHITE);
		g2d.fill(rt1);
		
		vencedor = Controller.translator.getVencedor();
		if(vencedor == Cor.inexistente)
		{
			g2d.setPaint(Color.BLACK);
			g2d.drawString("Jogador da vez : " + Controller.translator.getJogadorDaVez(), (int)leftX + 5, (int)topY + 20);
			g2d.drawString("Jogadas Restantes : " + Controller.translator.getJogadasRestantes(), (int)leftX + 5, (int)topY + 40);
		}
		else
		{
			g2d.setPaint(Color.BLACK);
			g2d.drawString("Vencedor : " + vencedor, (int)leftX + 5, (int)topY + 20);
		}
	}
	
	private void drawTela(int idTela, Graphics2D g2d)
	{
		Color cor;
		Sinal sinal;
		double leftX=0.0;
		double topY=0.0;
		double larg=400.0;
		double alt=400.0;
		
		switch (idTela) {
			case 1:
				leftX=0.0;
				topY=0.0;
				break;
			case 2:
				leftX=400.0;
				topY=0.0;		
				break;
			case 3:
				leftX=0.0;
				topY=400.0;
				break;
			case 4:
				leftX=400.0;
				topY=400.0;
				break;
		}
		
		sinal = Controller.translator.getSinalTela(idTela);
		
		if(sinal == Sinal.apagada)
		{
			cor = Color.BLACK;
		}
		else
		{
			cor = dicionarioCor_Color(Controller.translator.getCorTela(idTela));
		}
		
		Rectangle2D rt1=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.setPaint(cor);
		g2d.fill(rt1);
	}
	
	private Color dicionarioCor_Color(Cor c)
	{
		switch (c) {
			case verde:
				return Color.GREEN; 
				
			case vermelho:
				return Color.RED;

			case azul:
				return Color.BLUE;

			default:
				return Color.BLACK;
		}
	}
	
}
