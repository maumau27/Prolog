package managers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import managers.GridMouseController.setType;

public class TileSetMouseController implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		
		switch (e.getButton()) 
		{
			case 1:
				Point p = new Point( (e.getX() - Controller.tileSet.getTileSetPosition().x) / Controller.tileSet.getTileSize().width, (e.getY() - Controller.tileSet.getTileSetPosition().y) / Controller.tileSet.getTileSize().height );
				Controller.tileSet.selectedType = Controller.tileSet.tileSet_PointToType( p );
				Controller.tileSetFrame.reDraw();
				Controller.mainFrame.gridSection.mouseController.settingType = setType.TILE_TYPE;
				break;
	
			default:
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
