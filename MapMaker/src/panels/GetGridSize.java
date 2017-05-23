package panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import managers.Controller;

public class GetGridSize extends JPanel implements ActionListener{
	
	private Point position = new Point(0, 0);
	private Dimension size = new Dimension(Controller.popUpFrame.POP_LARG_DEFAULT, Controller.popUpFrame.POP_ALT_DEFAULT);
	
	private JLabel gridWidthSize;
	private JLabel gridHeigthSize;
	private JTextField gridWidth;
	private JTextField gridHeigth;
	private JButton gerar;
	
	public GetGridSize()
	{
		setPos();
	}
	
	public void setPos()
	{
		this.setLayout(null);
		this.setSize(size);
		this.setPreferredSize(size);
		this.setLocation(position);
		this.setOpaque(false);
		
		gridWidthSize = new JLabel("Informe Largura : ");
		gridWidthSize.setBounds(10, 0, 120, 70);
		add(gridWidthSize);
		
		gridHeigthSize = new JLabel("Informe Altura : ");
		gridHeigthSize.setBounds(10, 40, 120, 70);
		add(gridHeigthSize);
		
		gridWidth = new JTextField();
		gridWidth.setBounds(115, 20, 50, 30);
		add(gridWidth);
		
		gridHeigth = new JTextField();
		gridHeigth.setBounds(115, 60, 50, 30);
		add(gridHeigth);
		
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
		Controller.translator.SetGridSize(new Point(java.lang.Integer.parseInt(gridWidth.getText()), java.lang.Integer.parseInt(gridHeigth.getText())));
		Controller.CreateGrid();
		Controller.ClosePopUp();
	}

}
