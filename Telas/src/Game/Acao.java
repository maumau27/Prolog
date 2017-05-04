package Game;

import java.awt.image.ReplicateScaleFilter;

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
	
	public Acao(String acao)
	{
		if(acao.contains("acao("))
		{
			String strAcao[] = acao.substring(5).replace(")", "").replace(".", "").split(",");

			this.tipo = Dicionario.String_TipoAcao(strAcao[0].trim());
			this.IdTela = Integer.valueOf(strAcao[1].trim());
			if(strAcao.length == 3)
				this.dir = Dicionario.String_Direcao(strAcao[2].trim());
		}
	}
	
	public TipoAcao getTipo()
	{
		return this.tipo;
	}
	
	public int getIdTela()
	{
		return this.IdTela;
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
