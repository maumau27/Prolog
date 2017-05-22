package managers;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener{
	
	public static Boolean moveButtonPressed = false;
	
	public KeyboardController()
	{
		
	}

	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'c':
			Controller.grid.CenterGrid();
			break;

		default:
			break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 67:
				Controller.grid.CenterGrid();
				break;
				
			case 32:
				if(!moveButtonPressed)
					MouseMotionController.lastMousePosition = MouseInfo.getPointerInfo().getLocation();
					moveButtonPressed = true;
				break;
	
			default:
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 67:
				Controller.grid.CenterGrid();
				break;
				
			case 32:
				moveButtonPressed = false;
				break;
	
			default:
				break;
		}
	}
}
