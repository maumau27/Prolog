package managers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridMouseController implements MouseListener{
	
	public enum setType{
		TILE_TYPE,
		COLLISION_TYPE
	}
	
	public setType settingType = setType.TILE_TYPE;

	@Override
	public void mouseClicked(MouseEvent e) {
		
		switch (settingType) {
			case TILE_TYPE:
				
				
				switch (e.getButton()) 
				{
					case 1:
						try {
							Point p = new Point((e.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (e.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height);
							Controller.grid.setMultiplesTiles(p, Controller.tileSet.selectBox);
							Controller.mainFrame.reDraw();
						} catch (Exception e2) {
							System.out.println(e2);
						}
						break;
						
					case 3:
						Point p = new Point((e.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (e.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height);
						Controller.grid.setMultiplesTiles(p, Controller.tileSet.selectBox, -1);
						Controller.mainFrame.reDraw();
						break;
			
					default:
						break;
				}
				break;
				
			case COLLISION_TYPE:
					switch (e.getButton()) 
					{
						case 1:
							Controller.translator.changeTileCollisionType(new Point((e.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (e.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height), 1);
							Controller.mainFrame.reDraw();
							break;
							
						case 3:
							Controller.translator.changeTileCollisionType(new Point((e.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (e.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height), 0);
							Controller.mainFrame.reDraw();
							break;
				
						default:
							break;
					}
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
