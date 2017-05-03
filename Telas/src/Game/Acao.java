package Game;

public class Acao {
	private int IdTela;
	private TipoAcao tipo;
	private Direcao dir = null;
	
	public Acao(int idTela, TipoAcao tipo)
	{
		this.IdTela = idTela;
		this.tipo = tipo;
		if(tipo == TipoAcao.troca_cor)
		{
			this.dir = Direcao.direita;
		}
	}
	
	public Acao(int idTela, TipoAcao tipo, Direcao dir)
	{
		this.IdTela = idTela;
		this.tipo = tipo;
		this.dir = dir;
	}
	
	public String getString()
	{
		String acao_string = "acao("+this.tipo+","+this.IdTela;
		
		if(this.dir == null)
		{
			acao_string = acao_string.concat(").");
		}
		else
		{
			acao_string = acao_string.concat(","+this.dir.toString()+").");
		}
		
		return acao_string;
	}
}
