package prolog;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

import frames.MainFrame;
import managers.Controller;
import panels.Grid;
import components.*;

public class Translator {
	
	private Prolog prolog;
	private Query query;
	
	public Translator()
	{
		this.prolog = new Prolog();
	}
	
	public Point getGridSize()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("grid_size(X, Y).");
		solution = query.oneSolution();
		
		return new Point(convertTerm_Int(solution.get("X")), convertTerm_Int(solution.get("Y")));
	}
	
	public int getTileSet_TileSize()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("tileSet_tileSize(S).");
		solution = query.oneSolution();
		
		return convertTerm_Int(solution.get("S"));
	}
	
	public void setTileSet_TileSize(int tileSize)
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("update_tileSet_tileSize("+ tileSize +").");
		solution = query.oneSolution();
	}
	
	public int getTileSize()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("tile_size(S).");
		solution = query.oneSolution();
		
		return convertTerm_Int(solution.get("S"));
	}
	
	public void GenerateGrid(Point size)
	{
		this.query = this.prolog.doQuery("generate_grid("+ size.x +","+ size.y +").");
		query.oneSolution();
	}
	
	public void changeTileSize(int ammount)
	{
		int value = getTileSize() + ammount;
		if(value >= 1)
		{
			this.query = this.prolog.doQuery("update_tile_size(" + value +").");
			query.allSolutions();
		}
	}
	
	public void changeTileType(Point tile, int tileType)
	{
		this.query = this.prolog.doQuery("update_grid_type("+ tile.x +","+ tile.y +","+ tileType +").");
		query.allSolutions();
	}
	
	public void changeTileCollisionType(Point tile, int tileCollisionType)
	{
		this.query = this.prolog.doQuery("update_grid_collision("+ tile.x +","+ tile.y +","+ tileCollisionType +").");
		query.allSolutions();
	}
	
	public void SetGridSize(Point p)
	{
		this.query = this.prolog.doQuery("update_grid_size(" + p.x +","+ p.y +").");
		query.allSolutions();
	}
	
	public void addGrid(Tile tile)
	{
		this.query = this.prolog.doQuery("add_grid("+ tile.position.x +","+ tile.position.y +","+ tile.tileType +","+ tile.collisionType +").");
		query.allSolutions();
	}
	
	public void addGrid(Point position, int tileType, int colisionType)
	{
		this.query = this.prolog.doQuery("add_grid("+ position.x +","+ position.y +","+ tileType +","+ colisionType +").");
		query.allSolutions();
	}
	
	public void removeGrid(Point pos)
	{
		this.query = this.prolog.doQuery("remove_grid("+ pos.x +","+ pos.y +", _, _).");
		query.allSolutions();
	}
	
	public void ClearGrid()
	{
		this.query = this.prolog.doQuery("clear_grid().");
		query.allSolutions();
	}
	
	public ArrayList<Tile> getTiles()
	{
		Map<String, Term>[] solution;
		ArrayList<Tile> tiles = new ArrayList<>();
		
		this.query = this.prolog.doQuery("grid(X, Y, W, Z).");
		solution = query.allSolutions();
		
		for (Map<String, Term> sol : solution) {
			tiles.add(new Tile( new Point(convertTerm_Int(sol.get("X")), convertTerm_Int(sol.get("Y"))), convertTerm_Int(sol.get("W")), convertTerm_Int(sol.get("Z")) ) );
		}

		return tiles;
	}
	
	private int convertTerm_Int(Term t)
	{
		return java.lang.Integer.parseInt(String.valueOf(t));
	}
	
	private String convertTerm_String(Term t)
	{
		return String.valueOf(t);
	}
}
