package managers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import components.*;
import components.MainFrameMenu.Menu;
import frames.MainFrame;
import frames.PopUpFrame;
import frames.TileSetFrame;
import panels.*;
import prolog.Translator;

public class Controller {
	
	public static Translator translator = new Translator();
	public static MainFrame mainFrame = new MainFrame("MapMaker");
	public static TileSetFrame tileSetFrame;
	public static PopUpFrame popUpFrame;
	public static Controller controller;
	public static Grid grid = null;
	public static TileSet tileSet = null;
	
	public static void main(String[] args)
	{
		mainFrame.setVisible(true);

		return;
	}
	
	public static void GetGridSize()
	{
		mainFrame.disable();
		popUpFrame = new PopUpFrame("GridSize");
		popUpFrame.setVisible(true);
		popUpFrame.ClearPanel();
		popUpFrame.panelAdd(new GetGridSize());
	}
	
	public static void CreateGrid()
	{
		DeleteGrid();
		
		if(!translator.hasTileSize())
			translator.setTileSize(new Dimension(30, 30));
		
		translator.GenerateGrid(translator.getGridSize());
		grid = new Grid();
		mainFrame.gridSection.add(grid);
		grid.CenterGrid();
		mainFrame.reDraw();
		
		mainFrame.mainMenu.createGridMenu();
		mainFrame.mainMenu.createFilterMenu();
		mainFrame.setJMenuBar(mainFrame.mainMenu.menuBar);
	}
	
	public static void CreateGrid(Grid g)
	{
		DeleteGrid();
		
		grid = g;
		mainFrame.gridSection.add(grid);
		grid.CenterGrid();
		mainFrame.reDraw();

		mainFrame.mainMenu.createGridMenu();
		mainFrame.mainMenu.createFilterMenu();
		mainFrame.setJMenuBar(mainFrame.mainMenu.menuBar);
	}
	
	public static void DeleteGrid()
	{
		if(grid != null)
		{
			mainFrame.gridSection.remove(grid);
			mainFrame.reDraw();
			mainFrame.mainMenu.DeleteGridMenu();
			mainFrame.mainMenu.DeleteFilterMenu();
			mainFrame.setJMenuBar(mainFrame.mainMenu.menuBar);
			translator.ClearGrid();
			grid = null;
		}
	}
	
	public static void LoadGrid(File file)
	{
		try 
		{
			DeleteGrid();
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedFile = new BufferedReader(fileReader);

			String[] gridSize = bufferedFile.readLine().split("\t");
			translator.SetGridSize(new Point(java.lang.Integer.parseInt(gridSize[0]), java.lang.Integer.parseInt(gridSize[1])));
			translator.setTileSize(new Dimension(30, 30));

			String line;
			line = bufferedFile.readLine();

			while(line != null)
			{
				String[] tileString = line.split("\t");
				Point tilePos = new Point( java.lang.Integer.parseInt(tileString[0]), java.lang.Integer.parseInt(tileString[1]) );
				translator.addGrid(tilePos, java.lang.Integer.parseInt(tileString[2]), java.lang.Integer.parseInt(tileString[3]));
				line = bufferedFile.readLine();
			}
			
			Grid g = new Grid();
			CreateGrid(g);
			
			bufferedFile.close();
			fileReader.close();
		} 
		catch (Exception e) 
		{
			System.out.println("Erro lendo o file");
			System.out.println(e);
		}
		
	}
	
	public static void SaveGrid(File file) 
	{
		try 
		{
			if(file.exists())
			{
				SavaGrid_to_File(file);
			}
			else
			{
				file.createNewFile();
				SavaGrid_to_File(file);
			}
		}
		catch (Exception e) {
			System.out.println("Erro salvando o arquivo");
		}
	}
	
	public static void SavaGrid_to_File(File file)
	{
		ArrayList<Tile> tiles = Controller.translator.getTiles();
		Point gridSize = translator.getGridSize();
		
		try {
			FileWriter fileWriter  = new FileWriter(file, false); 
			
			fileWriter.write(gridSize.x + "\t" + gridSize.y);
			fileWriter.write("\n");
//			fileWriter.write(tileSetSize + "\t" + tileSetAmmount);
//			fileWriter.write("\n");
			
			for (Tile tile : tiles) {
				fileWriter.write(tile.position.x + "\t" + tile.position.y + "\t" + tile.tileType + "\t" + tile.collisionType);
				fileWriter.write("\n");
			}
			
			fileWriter.close();
			
		} catch (Exception e) {
			System.out.println("Erro salvando o arquivo");
		}
	}
	
	public static void GetTileSet_TileSize(File file)
	{
		try
		{
			if(file.exists())
			{
				mainFrame.disable();
				popUpFrame = new PopUpFrame("Tile Size");
				popUpFrame.setVisible(true);
				popUpFrame.ClearPanel();
				popUpFrame.panelAdd(new GetTileSize(file));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void CreateTileSet(File file)
	{
		DeleteTileSet();
		
		tileSetFrame = new TileSetFrame("Tile Set", file);
		tileSet = new TileSet(file);
		tileSetFrame.panelAdd(tileSet);
		tileSetFrame.setVisible(true);
	}
	
	public static void DeleteTileSet()
	{
		if(tileSet != null)
		{
			tileSetFrame.panelRemove(tileSet);
			tileSetFrame.reDraw();
			tileSetFrame.setVisible(false);
			tileSet = null;
		}
	}
	
	public static void ClosePopUp()
	{
		popUpFrame.setVisible(false);
		mainFrame.enable();
		mainFrame.setVisible(true);
	}
	
	public static void Exit()
	{
		System.exit(0);
	}
}
