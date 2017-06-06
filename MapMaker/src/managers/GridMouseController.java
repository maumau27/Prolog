package managers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import managers.GridMouseController.setType;

public class GridMouseController implements MouseListener{
	
	public enum setType{
		TILE_TYPE,
		COLLISION_TYPE
	}
	
	private Point ini_selectBox;
	private Point end_selectBox;
	
	public setType settingType = setType.TILE_TYPE;

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{	
		ini_selectBox = new Point( (e.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (e.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height );
		Controller.mainFrame.gridSection.selectedTile = ini_selectBox;
		Controller.mainFrame.gridSection.selectBox = new Dimension(1, 1);
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		end_selectBox = new Point( (e.getX() - Controller.grid.getGridPosition().x) / Controller.grid.getTileSize().width, (e.getY() - Controller.grid.getGridPosition().y) / Controller.grid.getTileSize().height );
		
		//fix directions
		if(ini_selectBox.x > end_selectBox.x)
		{
			int tempX = ini_selectBox.x;
			ini_selectBox.x = end_selectBox.x;
			end_selectBox.x = tempX;
		}
		
		if(ini_selectBox.y > end_selectBox.y)
		{
			int tempY = ini_selectBox.y;
			ini_selectBox.y = end_selectBox.y;
			end_selectBox.y = tempY;
		}
		Controller.mainFrame.gridSection.selectedTile = ini_selectBox;
			
		Controller.mainFrame.gridSection.selectBox = new Dimension( Math.abs(end_selectBox.x - ini_selectBox.x) + 1, Math.abs(end_selectBox.y - ini_selectBox.y) + 1 );
		
		switch (settingType) 
		{
			case TILE_TYPE:
				
				switch (e.getButton()) 
				{
					case 1:
						try {
							Controller.grid.setSelectBoxTiles(Controller.mainFrame.gridSection.selectedTile, Controller.mainFrame.gridSection.selectBox);
							Controller.mainFrame.reDraw();
						} catch (Exception e2) {
							System.out.println(e2);
						}
						break;
						
					case 3:
						Controller.grid.setSelectBoxTiles(Controller.mainFrame.gridSection.selectedTile, Controller.mainFrame.gridSection.selectBox, -1);
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
						Controller.grid.setSelectBoxCollision(Controller.mainFrame.gridSection.selectedTile, Controller.mainFrame.gridSection.selectBox, 1);
						Controller.mainFrame.reDraw();
						break;
						
					case 3:
						Controller.grid.setSelectBoxCollision(Controller.mainFrame.gridSection.selectedTile, Controller.mainFrame.gridSection.selectBox, 0);
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

}
