package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import Game.Cor;
import Game.GameController;
import Game.GameState;

public class Placar extends JPanel {
	
	public Placar()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setSize(new Dimension(200, 75));
		this.setPreferredSize(new Dimension(200,75));
		this.setLocation(300, 355);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D) g;

		drawPlacar(g2d);
	} 
	
	private  void drawPlacar(Graphics2D g2d)
	{
		double leftX=0.0;
		double topY=0.0;
		double larg=200.0;
		double alt=75.0;
		Cor vencedor;
		
		Rectangle2D rt1=new Rectangle2D.Double(leftX,topY,larg,alt);
		g2d.setPaint(Color.WHITE);
		g2d.fill(rt1);
		
		vencedor = GameController.translator.getVencedor();
		if(vencedor == Cor.inexistente)
		{
			g2d.setPaint(Color.BLACK);
			g2d.drawString("Jogador da vez : " + GameController.translator.getJogadorDaVez(), (int)leftX + 5, (int)topY + 10);
			g2d.drawString("Jogadas Restantes : " + GameController.translator.getJogadasRestantes(), (int)leftX + 5, (int)topY + 30);
			g2d.drawString("Esquerda : Azul>Verde>Vermelho ", (int)leftX + 5, (int)topY + 50);
			g2d.drawString("Direita : Vermelho>Verde>Azul ", (int)leftX + 5, (int)topY + 70);
		}
		else
		{
			g2d.setPaint(Color.BLACK);
			g2d.drawString("Vencedor : " + vencedor, (int)leftX + 5, (int)topY + 20);
			if(GameController.getGameState() == GameState.GAME)
				GameController.chageGameState(GameState.WINNER);
		}

	}
}
