package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import components.Tile;
import managers.Controller;
import managers.GridMouseController;

public class Grid extends JPanel{
	private Dimension tileSize = Controller.translator.getTileSize();
	private Point position = new Point(0, 0);
	private Dimension gridSize = new Dimension(Controller.translator.getGridSize().x * tileSize.width, Controller.translator.getGridSize().y * tileSize.height);
	private Dimension size = new Dimension(gridSize.width + 1, gridSize.height + 1 );
	private BufferedImage background;
	private ArrayList<Filter> filters = new ArrayList<>(); 
	//private GridSection section;
	
	//public GridMouseController mouseController;
	//private static Point deslocamento = new Point((MainFrame.LARG_DEFAULT / 2) - (MainFrame.gridSize.width / 2), (MainFrame.ALT_DEFAULT / 2) - (MainFrame.gridSize.height / 2));
	
	public enum Filter
	{
		BACKGROUND, 
		GRID,
		COLLISION,
		TILE
	}
	
	public Grid()
	{
		addFilter(Filter.TILE);
		addFilter(Filter.GRID);
		addFilter(Filter.BACKGROUND);
		addFilter(Filter.COLLISION);
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
		tileSize = Controller.translator.getTileSize();
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
	
	public Dimension getGridSize()
	{
		return size;
	}
	
	public void setBackGround(File file)
	{
		try {
			background = ImageIO.read(file);
		} catch (Exception e) {
			background = null;
		}
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
		
		if(background != null && hasFilter(Filter.BACKGROUND))
		{
			g2d.drawImage(background, 0, 0, size.width, size.height, null);
		}
			
		for (Tile tile : tiles) {
			drawTile(g2d, tile, tileSize);
		}
	} 
	
	public void setMultiplesTiles(Point init, Dimension size)
	{
		Point ini_point = new Point(Controller.tileSet.selectedTile);
		for (int i = init.x; i < (init.x + size.width); i++) {
			ini_point.y = Controller.tileSet.selectedTile.y;
			for (int j = init.y; j < (init.y + size.height); j++) {
				Controller.translator.changeTileType(new Point(i, j), Controller.tileSet.tileSet_PointToType(new Point(ini_point.x, ini_point.y)));
				ini_point.y++;
			}
			ini_point.x++;
		}
	}
	
	public void setMultiplesTiles(Point init, Dimension size, int tipe)
	{
		for (int i = init.x; i < (init.x + size.width); i++) {
			for (int j = init.y; j < (init.y + size.height); j++) {
				Controller.translator.changeTileType(new Point(i, j), tipe);
			}
		}
	}
	
	public void setSelectBoxCollision(Point init, Dimension size, int tipe)
	{
		for (int i = init.x; i < (init.x + size.width); i++) {
			for (int j = init.y; j < (init.y + size.height); j++) {
				Controller.translator.changeTileCollisionType(new Point(i, j), tipe);
			}
		}
	}
	
	public void setSelectBoxTiles(Point init, Dimension size)
	{
		for (int i = init.x; i < (init.x + size.width); i++) {
			for (int j = init.y; j < (init.y + size.height); j++) {
				Controller.grid.setMultiplesTiles(new Point(i, j), Controller.tileSet.selectBox);
			}
		}
	}
	
	public void setSelectBoxTiles(Point init, Dimension size, int tipe)
	{
		for (int i = init.x; i < (init.x + size.width); i++) {
			for (int j = init.y; j < (init.y + size.height); j++) {
				Controller.grid.setMultiplesTiles(new Point(i, j), Controller.tileSet.selectBox, tipe);
			}
		}
	}
	
	public void CenterGrid()
	{
		position = new Point((Controller.mainFrame.gridSection.getSize().width / 2) - (gridSize.width / 2), (Controller.mainFrame.gridSection.getSize().height / 2) - (gridSize.height / 2));
		setPos();
	}
	
	public void addFilter(Filter filter)
	{
		filters.add(filter);
	}
	
	public void removeFilter(Filter filter)
	{
		filters.remove(filter);
	}
	
	public Boolean hasFilter(Filter filter)
	{
		return filters.contains(filter);
	}
	
	private void drawTile(Graphics2D g2d, Tile tile, Dimension size)
	{
		int posX = tile.position.x * size.width;
		int posY = tile.position.y * size.height;
		int larg = size.width;
		int alt = size.height;
		
		try {
			if(tile.tileType <= (Controller.tileSet.getTileCount() - 1) && tile.tileType != -1 && hasFilter(Filter.TILE))
			{
				g2d.drawImage(Controller.tileSet.sprites.getSprite(tile.tileType), posX, posY, larg, alt, null);
			}
		} catch (Exception e) {
			//System.out.println("Tile Set não carregado");
			//System.out.println(e);
		}
		

		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg-1, alt-1);
		if(hasFilter(Filter.GRID))
		{
			g2d.setColor(Color.BLACK);
			g2d.draw(rectangle);
			
		}

		if(hasFilter(Filter.COLLISION) && tile.collisionType == 1)
		{
			g2d.setColor(Color.RED);
			g2d.draw(rectangle);
		}

		//g2d.fill(rectangle);
	}
}
