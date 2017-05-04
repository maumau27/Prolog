package Game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {
	
	public MouseController()
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!GameController.LockPlayerAction)
		{
			switch (e.getButton()) {
				case 1:
					GameController.translator.doAcao(new Acao(Posicao_Tela(e.getPoint()), TipoAcao.troca_cor, Direcao.esquerda));
					break;
					
				case 2:
					GameController.translator.doAcao(new Acao(Posicao_Tela(e.getPoint()), TipoAcao.troca_sinal));
					break;
		
				case 3:
					GameController.translator.doAcao(new Acao(Posicao_Tela(e.getPoint()), TipoAcao.troca_cor, Direcao.direita));
					break;
					
				default:
					break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	private int Posicao_Tela(Point pos)
	{
		if(pos.getX() < 400.0 && pos.getY() < 400.0)
		{
			return 1;
		}
		else if(pos.getX() < 800.0 && pos.getY() < 400.0)
		{
			return 2;
		}
		else if(pos.getX() < 400.0 && pos.getY() < 800.0)
		{
			return 3;
		}
		else if(pos.getX() < 800.0 && pos.getY() < 800.0)
		{
			return 4;
		}
		
		return 0;
	}
}
