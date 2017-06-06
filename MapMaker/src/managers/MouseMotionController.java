package managers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import frames.MainFrame;

public class MouseMotionController implements MouseMotionListener {
	public static Point lastMousePosition;
	
	private Point current_point = new Point(0, 0);
	
	public MouseMotionController() 
	{
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		if(KeyboardController.moveButtonPressed)
		{
			Controller.grid.updatePosition(lastMousePosition.x - arg0.getXOnScreen(), lastMousePosition.y - arg0.getYOnScreen());
			lastMousePosition = arg0.getLocationOnScreen();
		}
		
		try{
			current_point = new Point( (arg0.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (arg0.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height );
			if(in_grid(current_point))
				Controller.mainFrame.gridSection.change_mousePosition();
			else
				Controller.mainFrame.gridSection.change_mousePosition("Fora da Grid");
			Controller.mainFrame.gridSection.repaint();
		}
		catch (Exception e) {

		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
	
	public boolean in_grid(Point p)
	{
		if(p.x < 0 || p.x > Controller.translator.getGridSize().x-1)
			return false;
		
		if(p.y < 0 || p.y > Controller.translator.getGridSize().y-1)
			return false;
		
		return true;
	}
	
	public Point getCurrentPoint()
	{
		return current_point;
	}
}
