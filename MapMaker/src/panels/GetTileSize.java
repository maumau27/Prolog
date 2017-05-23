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
	
	private JLabel tileSizeWidthLabel;
	private JLabel tileSizeHeightLabel;
	private JTextField tileSizeWidth;
	private JTextField tileSizeHeight;
	private JButton gerar;
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
		
		tileSizeWidthLabel = new JLabel("Informe Largura : ");
		tileSizeWidthLabel.setBounds(10, 0, 120, 70);
		add(tileSizeWidthLabel);
		
		tileSizeHeightLabel = new JLabel("Informe Altura : ");
		tileSizeHeightLabel.setBounds(10, 40, 120, 70);
		add(tileSizeHeightLabel);
		
		tileSizeWidth = new JTextField();
		tileSizeWidth.setBounds(115, 20, 50, 30);
		add(tileSizeWidth);
		
		tileSizeHeight = new JTextField();
		tileSizeHeight.setBounds(115, 60, 50, 30);
		add(tileSizeHeight);
		
		gerar = new JButton("Gerar");
		gerar.setBounds(175, 20, 70, 70);
		gerar.addActionListener(this);
		add(gerar);
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
		Dimension size = new Dimension(java.lang.Integer.parseInt(tileSizeWidth.getText()), java.lang.Integer.parseInt(tileSizeHeight.getText()));
		if(size.width != 0 && size.height != 0)
		{
			Controller.translator.setTileSet_TileSize(size);
			Controller.CreateTileSet(file);
			Controller.ClosePopUp();
		}
	}

}
