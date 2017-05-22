package components;

import java.awt.Point;

public class Tile
{
	public Point position;
	public int tileType;
	public int collisionType;
	
	public Tile(Point position, int tileType, int colisionType)
	{
		this.position = position;
		this.tileType = tileType;
		this.collisionType = colisionType;
	}
}
