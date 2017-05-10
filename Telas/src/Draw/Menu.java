package Draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.Cor;
import Game.GameController;
import Game.GameState;

public class Menu extends JPanel {
	
	JButton b1 = new JButton("Um Jogador");
	JButton b2 = new JButton("Dois Jogadores");
	
	public Menu() 
	{
		setPos();
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.N_jogadores = 1;
				GameController.chageGameState(GameState.GAME);
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.N_jogadores = 2;
				GameController.chageGameState(GameState.GAME);
			}
		});

		add(b1);
		add(b2);
	}
	
	public void setPos()
	{
		this.setSize(new Dimension(200, 200));
		this.setPreferredSize(new Dimension(200, 200));
		this.setLocation(300, 300);
		this.setBackground(Color.BLACK);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
	} 

}
