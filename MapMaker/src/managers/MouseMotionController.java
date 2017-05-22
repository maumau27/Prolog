package managers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import frames.MainFrame;

public class MouseMotionController implements MouseMotionListener {
	public static Point lastMousePosition;
	
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
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}
}
