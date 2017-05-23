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
import java.util.ResourceBundle.Control;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import components.Tile;
import managers.Controller;
import managers.MouseMotionController;
import managers.SpriteSheet;
import managers.TileSetMouseController;

public class TileSet extends JPanel{
	
	private Dimension tileSize = Controller.translator.getTileSet_TileSize();
	private Point position = new Point(0, 0);
	private Dimension gridSize;
	private Dimension size;
	private int tileSetCount;
	private File tileSetFile;
	private BufferedImage tileSet;
	
	private int imgLargCount;
	private int imgAltCount;
	
	public Point selectedTile;
	public int selectedType;
	public Dimension selectBox;
	
	public SpriteSheet sprites;
	
	public TileSet(File file)
	{
		tileSetFile = file;
		try {
			tileSet = ImageIO.read(file);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			Controller.translator.setTileSize(tileSize);
			Controller.grid.updateTileSize();
			Controller.mainFrame.reDraw();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		imgLargCount = tileSet.getWidth() / tileSize.width;
		imgAltCount = tileSet.getHeight() / tileSize.height;
		tileSetCount = imgLargCount * imgAltCount;
		gridSize = new Dimension(tileSet.getWidth(), tileSet.getHeight());
		size = new Dimension(Controller.tileSetFrame.LARG_DEFAULT, Controller.tileSetFrame.ALT_DEFAULT);
		
		selectedType = 0;
		selectedTile = new Point(0, 0);
		selectBox = new Dimension(1, 1);
		
		setPos();
		buildTileSet();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
		
		addMouseListener(new TileSetMouseController());
	}
	
	public Point getTileSetPosition()
	{
		return position;
	}
	
    public void buildTileSet() {
        int count = tileSetCount;
        int cols = imgLargCount;
        int rows = imgAltCount;
        
        if (count == 0) {
            count = rows * cols;
        }

        BufferedImage sheet = tileSet;

        int width = tileSize.width;
        int height = tileSize.height;
        if (width == 0) {
            width = sheet.getWidth() / cols;
        }
        if (height == 0) {
            height = sheet.getHeight() / rows;
        }

        int x = 0;
        int y = 0;
        ArrayList<BufferedImage> sprites = new ArrayList<>(count);

        for (int index = 0; index < count; index++) {
            sprites.add(sheet.getSubimage(x, y, width, height));
            x += width;
            if (x >= width * cols) {
                x = 0;
                y += height;
            }
        }

       	this.sprites = new SpriteSheet(sprites);
    }
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;

		int spriteId = 0;
		for (int i = 0; i < imgAltCount; i++) {
			for (int j = 0; j < imgLargCount; j++) {
				drawTile(g2d, new Point(j, i), tileSize, spriteId);
				spriteId++;
			}
		}
	} 
	
	public int getTileCount()
	{
		return tileSetCount;
	}
	
	public Dimension getTileSize()
	{
		return tileSize;
	}
	
	public int tileSet_PointToType(Point pos)
	{
		
		return (pos.y * imgLargCount) + pos.x;
	}
	
	public Boolean in_SelectionBox(Point point)
	{
		if(point.x >= selectedTile.x && point.y >= selectedTile.y)
			if(point.x <= (selectedTile.x + (selectBox.width - 1)) && point.y <= (selectedTile.y + (selectBox.height - 1)))
				return true;
		return false;
	}
	
	private void drawTile(Graphics2D g2d, Point pos, Dimension size, int id)
	{
		int posX = pos.x * size.width;
		int posY = pos.y * size.height;
		int larg = size.width;
		int alt = size.height;
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg - 1, alt - 1);
		
		g2d.drawImage(sprites.getSprite(id), posX, posY, larg-1, alt-1, null);
		
		if(in_SelectionBox(pos))
		{
			g2d.setColor(Color.RED);repaint();
			g2d.draw(rectangle);
		}	
		else
		{
			g2d.setColor(Color.BLACK);
			g2d.draw(rectangle);
		}
	}
}
