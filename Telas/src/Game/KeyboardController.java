package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener{
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
			case 'a':
				GameController.translator.doAcao(new Acao(1, TipoAcao.troca_sinal));
				break;
			case 's':
				GameController.translator.doAcao(new Acao(2, TipoAcao.troca_sinal));
				break;
			case 'd':
				GameController.translator.doAcao(new Acao(3, TipoAcao.troca_sinal));
				break;
			case 'f':
				GameController.translator.doAcao(new Acao(4, TipoAcao.troca_sinal));
				break;
	
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
