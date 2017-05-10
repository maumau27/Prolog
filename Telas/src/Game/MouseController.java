package Game;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController extends MouseAdapter {
	
	private int idTela;
	
	public MouseController(int idTela)
	{
		this.idTela = idTela;
	}

	public void mousePressed(MouseEvent e) {
		if(!GameController.LockPlayerAction)
		{
			switch (e.getButton()) {
				case 1:
					GameController.translator.doAcao(new Acao(this.idTela, TipoAcao.troca_cor, Direcao.esquerda));
					break;
					
				case 2:
					GameController.translator.doAcao(new Acao(this.idTela, TipoAcao.troca_sinal));
					break;
		
				case 3:
					GameController.translator.doAcao(new Acao(this.idTela, TipoAcao.troca_cor, Direcao.direita));
					break;
					
				default:
					break;
			}
		}
	}
}
