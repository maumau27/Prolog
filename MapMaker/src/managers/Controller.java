package managers;

import java.awt.BorderLayout;
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
import panels.*;
import prolog.Translator;

public class Controller {
	
	public static Translator translator = new Translator();
	public static MainFrame mainFrame = new MainFrame("MapMaker");
	public static PopUpFrame popUpFrame;
	public static Controller controller;
	public static Grid grid = null;
	public static TileSet tileSet = null;
	
	public static void main(String[] args)
	{
		mainFrame.setVisible(true);
		
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("images/CandidatarOK.png"));
//		} 
//		catch (IOException e) {
//			
//		}
//		System.out.println(img.getWidth());
//		System.out.println(img.getHeight());

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
		
		translator.GenerateGrid(translator.getGridSize());
		grid = new Grid();
		mainFrame.gridSection.add(grid);
		grid.CenterGrid();
		mainFrame.reDraw();
		
		mainFrame.mainMenu.createGridMenu();
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
		mainFrame.setJMenuBar(mainFrame.mainMenu.menuBar);
	}
	
	public static void DeleteGrid()
	{
		if(grid != null)
		{
			mainFrame.gridSection.remove(grid);
			mainFrame.reDraw();
			mainFrame.mainMenu.DeleteGridMenu();
			mainFrame.setJMenuBar(mainFrame.mainMenu.menuBar);
			translator.ClearGrid();
			grid = null;
		}
	}
	
	public static void LoadGrid(File file)
	{
		DeleteGrid();
		try 
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedFile = new BufferedReader(fileReader);
			
			String[] gridSize = bufferedFile.readLine().split("\t");
			translator.SetGridSize(new Point(java.lang.Integer.parseInt(gridSize[0]), java.lang.Integer.parseInt(gridSize[1])));

			String line;
			line = bufferedFile.readLine();
			
			while(line != null)
			{
				String[] tileString = line.split("\t");
				Point tilePos = new Point( java.lang.Integer.parseInt(tileString[0]), java.lang.Integer.parseInt(tileString[1]) );
				translator.addGrid(tilePos, java.lang.Integer.parseInt(tileString[2]), java.lang.Integer.parseInt(tileString[3]));
				line = bufferedFile.readLine();
			}
			
			CreateGrid(new Grid());
			
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
		mainFrame.disable();
		popUpFrame = new PopUpFrame("Tile Size");
		popUpFrame.setVisible(true);
		popUpFrame.ClearPanel();
		popUpFrame.panelAdd(new GetTileSize(file));
	}
	
	public static void CreateTileSet(File file)
	{
		DeleteTileSet();
		
		tileSet = new TileSet(file);
		mainFrame.tileSetSection.add(tileSet);
		mainFrame.reDraw();
	}
	
	public static void DeleteTileSet()
	{
		if(tileSet != null)
		{
			mainFrame.tileSetSection.remove(tileSet);
			mainFrame.reDraw();
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