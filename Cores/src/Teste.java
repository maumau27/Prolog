import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
public class Teste extends JFrame {
	
	public final int LARG_DEFAULT = 200;
	public final int ALT_DEFAULT = 300;
	JButton b1 = new JButton("Botão 1");
	JButton b2 = new JButton("Botão 2");
	JPanel p = new JPanel();
	
	public Teste(String s) {
		super(s);
		
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		p.add(b1);
		p.add(b2);
		p.setBackground(Color.WHITE);
		getContentPane().add(p);
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Teste f=new Teste("Exemplo JPanel");
		f.setVisible(true);
	}
}