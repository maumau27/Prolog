package panels;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import managers.Controller;

public class GetTileSize extends JPanel implements ActionListener{
	
	private Point position = new Point(0, 0);
	private Dimension size = new Dimension(Controller.popUpFrame.POP_LARG_DEFAULT, Controller.popUpFrame.POP_ALT_DEFAULT);
	
	private JLabel tileSizeLabel;
	private JTextField tileSize;
	private JButton salvar;
	private File file;
	
	public GetTileSize(File file)
	{
		this.file = file;
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
		
		tileSizeLabel = new JLabel("Tile Size : ");
		tileSizeLabel.setBounds(20, 0, 70, 70);
		add(tileSizeLabel);
		
		tileSize = new JTextField();
		tileSize.setBounds(90, 20, 50, 30);
		add(tileSize);
		
		salvar = new JButton("Salvar");
		salvar.setBounds(150, 20, 80, 30);
		salvar.addActionListener(this);
		add(salvar);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public Dimension getSize()
	{
		return size;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
	} 
	
	public void actionPerformed(ActionEvent e)
	{
		int size = java.lang.Integer.parseInt(tileSize.getText());
		if(size != 0)
		{
			Controller.translator.setTileSet_TileSize(size);
			Controller.CreateTileSet(file);
			Controller.ClosePopUp();
		}
	}

}
