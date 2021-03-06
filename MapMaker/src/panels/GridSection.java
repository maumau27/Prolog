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

import javax.swing.JButton;
import javax.swing.JLabel;
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
	public MouseMotionController mouseMotionController;
	
	public Point selectedTile;
	public Dimension selectBox;
	
	private JLabel mouse_position;
	
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
		
		mouseMotionController = new MouseMotionController();
		
		addMouseMotionListener(mouseMotionController);
		
		mouse_position = new JLabel();
		mouse_position.setText("Fora da Grid");
		mouse_position.setBounds(0, -40, 100, 100);
		add(mouse_position);
	}
	
	public void change_mousePosition()
	{
		mouse_position.setText("("+mouseMotionController.getCurrentPoint().x+" , "+mouseMotionController.getCurrentPoint().y+")");
	}
	
	public void change_mousePosition(String s)
	{
		mouse_position.setText(s);
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
