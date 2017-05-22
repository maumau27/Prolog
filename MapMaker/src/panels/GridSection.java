package panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import frames.MainFrame;
import managers.Controller;
import managers.GridMouseController;
import managers.KeyboardController;
import managers.MouseMotionController;

public class GridSection extends JPanel{
	
	private Point position = new Point(0, 0);
	private Dimension size = new Dimension(Controller.mainFrame.LARG_DEFAULT, Controller.mainFrame.ALT_DEFAULT);
	public GridMouseController mouseController;
	
	public GridSection()
	{
		mouseController = new GridMouseController();
		addMouseListener(mouseController);
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(Controller.grid != null)
				{
					Controller.translator.changeTileSize(e.getWheelRotation());
					Controller.grid.updateTileSize();
					Controller.mainFrame.reDraw();
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionController());
	}

	public void panelAdd(Component c)
	{
		add(c);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public Dimension getSize()
	{
		return size;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d=(Graphics2D) g;
		
		int posX = 0;
		int posY = 0;
		int larg = size.width;
		int alt = size.height;
		
		Rectangle2D rectangle=new Rectangle2D.Double(posX, posY, larg-1, alt-1);
		g2d.draw(rectangle);
	} 
}
