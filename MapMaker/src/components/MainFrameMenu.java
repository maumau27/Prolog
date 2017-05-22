package components;

import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import components.MainFrameMenu.Menu;
import managers.Controller;
import managers.GridMouseController.setType;

public class MainFrameMenu implements ActionListener, ItemListener{
	
	public class Menu{
		public String text;
		private JMenu menu;
		private ActionListener actionListener;
		private ItemListener itemListener;
		public Map<String, JMenuItem> itenList = new HashMap<String, JMenuItem>();
		public Map<String, JMenu> submenuList = new HashMap<String, JMenu>();
		
		public Menu(String text, ActionListener al, ItemListener il)
		{
			text = text;
			menu = new JMenu(text);
			actionListener = al;
			itemListener = il;
		}
		
		public void addItem(String text)
		{
			JMenuItem menuItem;
			menuItem = new JMenuItem(text);
			menuItem.addActionListener(actionListener);
			menu.add(menuItem);
			itenList.put(text, menuItem);
		}
		
		@SuppressWarnings("deprecation")
		public void Disable(JMenu menu)
		{
			menu.disable();
		}
		
		@SuppressWarnings("deprecation")
		public void Enable(JMenu menu)
		{
			menu.enable();
		}
		
		@SuppressWarnings("deprecation")
		public void Disable(JMenuItem menuIten)
		{
			menuIten.disable();
		}
		
		@SuppressWarnings("deprecation")
		public void Enable(JMenuItem menuIten)
		{
			menuIten.enable();
		}
		
		public void addSubMenu(Menu menu)
		{
			this.menu.add(menu.menu);
			submenuList.put(menu.text, menu.getMenu());
		}
		
		public void addSeparator()
		{
			this.menu.addSeparator();
		}
		
		public JMenu getMenu()
		{
			return this.menu;
		}
	}
	
	public JMenuBar menuBar;
	public Menu FileMenu, LoadSubmenu, GridMenu;
	public JFileChooser fileChooser = new JFileChooser();
	
	public MainFrameMenu()
	{
		menuBar = new JMenuBar();
		
		FileMenu = new Menu("File", this, this);
		FileMenu.addItem("New");
		LoadSubmenu = new Menu("Load", this, this);
		LoadSubmenu.addItem("Grid");
		LoadSubmenu.addItem("TileSet");
		FileMenu.addSubMenu(LoadSubmenu);
		FileMenu.addItem("Save");
		FileMenu.addSeparator();
		FileMenu.addItem("Exit");
		
		menuBar.add(FileMenu.getMenu());
	}
	
	public void createGridMenu()
	{
		GridMenu = new Menu("Grid", this, this);
		GridMenu.addItem("Center");
		GridMenu.addItem("Set Collision");
		GridMenu.addItem("Delete");
		menuBar.add(GridMenu.getMenu());
		menuBar.repaint();
	}
	
	public void DeleteGridMenu()
	{
		menuBar.remove(GridMenu.getMenu());
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Exit":
				Controller.Exit();
				break;
			case "New":
				Controller.GetGridSize();
				break;
			case "Center":
				Controller.grid.CenterGrid();
				break;
			case "Set Collision":
				Controller.mainFrame.gridSection.mouseController.settingType = setType.COLLISION_TYPE; 
				break;
			case "Delete":
				Controller.DeleteGrid();
				break;
			case "Grid":
				fileChooser.showOpenDialog(Controller.mainFrame);
				Controller.LoadGrid(fileChooser.getSelectedFile());
				break;
			case "Save":
				fileChooser.showOpenDialog(Controller.mainFrame);
				Controller.SaveGrid(fileChooser.getSelectedFile());
				break;
			case "TileSet":
				fileChooser.showOpenDialog(Controller.mainFrame);
				Controller.GetTileSet_TileSize(fileChooser.getSelectedFile());
				break;
	
			default:
				System.out.println(e.getActionCommand());
				break;
		}
    }

    public void itemStateChanged(ItemEvent e) {

    }
}
