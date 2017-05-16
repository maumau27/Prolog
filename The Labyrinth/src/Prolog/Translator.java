package Prolog;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

import Draw.MainFrame;

public class Translator {
	
	private Prolog prolog;
	private Query query;
	
	public Translator()
	{
		this.prolog = new Prolog();
	}
	
	public ArrayList<Point> getTiles()
	{
		Map<String, Term>[] solution;
		ArrayList<Point> pontos = new ArrayList<>();
		
		this.query = this.prolog.doQuery("mapa(X, Y).");
		solution = query.allSolutions();
		
		for (Map<String, Term> sol : solution) {
			pontos.add(new Point(convertTerm_Int(sol.get("X")), convertTerm_Int(sol.get("Y"))));
		}

		return pontos;
	}
	
	public Point getPlayerPos()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("posicao_jogador(X, Y).");
		solution = query.oneSolution();
		
		return new Point(convertTerm_Int(solution.get("X")), convertTerm_Int(solution.get("Y")));
	}
	
	public Point getMapSize()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("tamanho_mapa(X, Y).");
		solution = query.oneSolution();
		
		return new Point(convertTerm_Int(solution.get("X")), convertTerm_Int(solution.get("Y")));
	}
	
	public int getTileSize()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("tile_size(S).");
		solution = query.oneSolution();
		
		return convertTerm_Int(solution.get("S"));
	}
	
	public void doAcao(String acao)
	{
		this.query = this.prolog.doQuery("acao("+ acao +").");
		query.allSolutions();

		MainFrame.mf.repaint();
	}
	
	public void goTo(Point p)
	{
		this.query = this.prolog.doQuery("go_to("+ p.x +","+ p.y +").");
		query.oneSolution();
		
		MainFrame.mf.repaint();
	}
	
	private int convertTerm_Int(Term t)
	{
		return java.lang.Integer.parseInt(String.valueOf(t));
	}
	
	private String convertTerm_String(Term t)
	{
		return String.valueOf(t);
	}
}
