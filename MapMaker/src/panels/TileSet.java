package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import components.Tile;
import managers.Controller;

public class TileSet extends JPanel{
	
	private Dimension tileSize = new Dimension(Controller.translator.getTileSet_TileSize(), Controller.translator.getTileSet_TileSize());
	private Point position = new Point(0, 0);
	private Dimension gridSize;
	private Dimension size;
	private int tileSetCount;
	private File tileSetFile;
	private BufferedImage tileSet;
	
	private int tileSetLargCount;
	private int tileSetAltCount;
	
	public BufferedImage[] sprites;
	
	public TileSet(File file)
	{
		tileSetFile = file;
		try {
			tileSet = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		int imgLargCount = tileSet.getWidth() / tileSize.width;
		int imgAltCount = tileSet.getHeight() / tileSize.height;
		tileSetCount = imgLargCount * imgAltCount;
		tileSetLargCount = ((Controller.mainFrame.tileSetSection.getSize().width - 2) / Controller.translator.getTileSet_TileSize());
		tileSetAltCount = tileSetCount / tileSetLargCount;
		gridSize = new Dimension(tileSetLargCount * tileSize.width, tileSetAltCount * tileSize.height);
		size = new Dimension(gridSize.width + 1, gridSize.height + 1);
		
		setPos();
		//setTileSet();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
	}
	
	public void setTileSet()
	{	//TODO - Terminar de separar o spriteSheet
		int width = tileSize.width;
		int height = tileSize.height;
		int rows = tileSet.getWidth() / tileSize.width;
		int cols = tileSet.getHeight() / tileSize.height;
		sprites = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++)
		{
		    for (int j = 0; j < cols; j++)
		    {
		        sprites[(i * cols) + j] = tileSet.getSubimage(
		            j * width,
		            i * height,
		            width,
		            height
		        );
		    }
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;

		int spriteId = 0;
		for (int i = 0; i < tileSetLargCount; i++) {
			for (int j = 0; j < tileSetAltCount; j++) {
				drawTile(g2d, new Point(i, j), tileSize, spriteId);
				spriteId++;
			}
		}
	} 
	
	private void drawTile(Graphics2D g2d, Point pos, Dimension size, int id)
	{
		int posX = pos.x * size.width;
		int posY = pos.y * size.height;
		int larg = size.width;
		int alt = size.height;
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg, alt);
		
		g2d.setColor(Color.RED);
		g2d.draw(rectangle);
		
		//g2d.drawImage(sprites[id], posX, posY, larg, alt, null);	
		//g2d.fill(rectangle);
	}
}
