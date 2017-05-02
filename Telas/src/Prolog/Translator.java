package Prolog;

import java.util.HashMap;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

public class Translator {
	private Prolog prolog;
	private Query query;
	
	public Translator()
	{
		this.prolog = new Prolog();
	}
	
	public int getJogadasRestantes()
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("turno(_, X).");
		solution = query.nSolutions(1);
		
		return java.lang.Integer.parseInt( String.valueOf(solution[0].get("X")) );
	}
	
	public String getJogadorDaVez()
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("turno(X, _).");
		solution = query.nSolutions(1);
		
		return String.valueOf(solution[0].get("X"));
	}
	
	public String getSinalTela(int IdTela)
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("tela("+ IdTela +", _, X).");
		solution = query.nSolutions(1);
		
		return String.valueOf(solution[0].get("X"));
	}
	
	public String getCorTela(int IdTela)
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("tela("+ IdTela +", X, _).");
		solution = query.nSolutions(1);
		
		return String.valueOf(solution[0].get("X"));
	}
	
	public String getVencedor()
	{
		Map<String, Term>[] solution;
		
		try {
			this.query = this.prolog.doQuery("checa_vencedor(V).", true);
			solution = query.nSolutions(1);
			return String.valueOf(solution[0].get("V"));
		} catch (Exception e) {
			return "FALSE";
		}
	}
	
	public String getDecisaoIa()
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("decidir(X).");
		solution = query.nSolutions(1);
		
		return String.valueOf(solution[0].get("X"));
	}
	
	public boolean doAcao(String acao)
	{
		try {
			this.prolog.doQuery(acao, true);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void setCorIa(String cor)
	{
		this.prolog.doQuery("assert(cor_ia("+cor+")).");
	}
}
