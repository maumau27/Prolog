package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import components.Tile;
import managers.Controller;
import managers.GridMouseController;

public class Grid extends JPanel{
	private Dimension tileSize = new Dimension(Controller.translator.getTileSize(), Controller.translator.getTileSize());
	private Point position = new Point(0, 0);
	private Dimension gridSize = new Dimension(Controller.translator.getGridSize().x * tileSize.width, Controller.translator.getGridSize().y * tileSize.height);
	private Dimension size = new Dimension(gridSize.width + 1, gridSize.height + 1 );
	//private GridSection section;
	
	//public GridMouseController mouseController;
	//private static Point deslocamento = new Point((MainFrame.LARG_DEFAULT / 2) - (MainFrame.gridSize.width / 2), (MainFrame.ALT_DEFAULT / 2) - (MainFrame.gridSize.height / 2));
	
	public Grid()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
	}
	
	public void updateTileSize()
	{
		tileSize = new Dimension(Controller.translator.getTileSize(), Controller.translator.getTileSize());
		gridSize = new Dimension(Controller.translator.getGridSize().x * tileSize.width, Controller.translator.getGridSize().y * tileSize.height);
		size = new Dimension(gridSize.width + 1, gridSize.height + 1 );
		setPos();
	}
	
	public Dimension getTileSize()
	{
		return tileSize;
	}
	
	public Point getGridPosition()
	{
		return position;
	}
	
	public void updatePosition(int adicao_X, int adicao_Y)
	{
		position = new Point(position.x - adicao_X, position.y - adicao_Y);
		setPos();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		ArrayList<Tile> tiles = Controller.translator.getTiles();
		
		for (Tile tile : tiles) {
			drawTile(g2d, tile, tileSize);
		}
	} 
	
	public void CenterGrid()
	{
		position = new Point((Controller.mainFrame.gridSection.getSize().width / 2) - (gridSize.width / 2), (Controller.mainFrame.gridSection.getSize().height / 2) - (gridSize.height / 2));
		setPos();
	}
	
	private void drawTile(Graphics2D g2d, Tile tile, Dimension size)
	{
		int posX = tile.position.x * size.width;
		int posY = tile.position.y * size.height;
		int larg = size.width;
		int alt = size.height;
		
		try {
			if(tile.tileType <= (Controller.tileSet.getTileCount() - 1) && tile.tileType != -1)
			{
				g2d.drawImage(Controller.tileSet.sprites.getSprite(tile.tileType), posX, posY, larg, alt, null);
			}
		} catch (Exception e) {
			System.out.println("Tile Set não carregado");
			System.out.println(e);
		}
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg, alt);
		switch (tile.collisionType) 
		{
			case 0:
				g2d.setColor(Color.BLACK);
				g2d.draw(rectangle);
				break;
			case 1:
				g2d.setColor(Color.RED);
				g2d.draw(rectangle);
				break;
	
			default:
				break;
		}

		//g2d.fill(rectangle);
	}
}
