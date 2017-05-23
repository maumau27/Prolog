package managers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import managers.GridMouseController.setType;

public class TileSetMouseController implements MouseListener{
	
	private Point ini_selectBox;
	private Point end_selectBox;

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
		ini_selectBox = new Point( (e.getX() - Controller.tileSet.getTileSetPosition().x) / Controller.tileSet.getTileSize().width, (e.getY() - Controller.tileSet.getTileSetPosition().y) / Controller.tileSet.getTileSize().height );
		Controller.tileSet.selectedTile = ini_selectBox;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		end_selectBox = new Point( (e.getX() - Controller.tileSet.getTileSetPosition().x) / Controller.tileSet.getTileSize().width, (e.getY() - Controller.tileSet.getTileSetPosition().y) / Controller.tileSet.getTileSize().height );
		Controller.tileSet.selectBox = new Dimension( Math.abs(end_selectBox.x - ini_selectBox.x) + 1, Math.abs(end_selectBox.y - ini_selectBox.y) + 1 );
		Controller.mainFrame.gridSection.mouseController.settingType = setType.TILE_TYPE;
	}

}
