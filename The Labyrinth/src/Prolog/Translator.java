package Prolog;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

import Draw.Grid;
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
	
	public void MakeMap()
	{
		Map<String, Term> solution;
		
		this.query = this.prolog.doQuery("tamanho_mapa(X, Y), gerar_mapa(X, Y).");
		solution = query.oneSolution();
		
		return;
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
	
	public void changeTileSize(int ammount)
	{
		int value = getTileSize() + ammount;
		System.out.println(value);
		if(value >= 1)
		{
			this.query = this.prolog.doQuery("tile_size(X), atualiza_tile_size(" + value +").");
			query.allSolutions();
		}

		MainFrame.tileSize = new Dimension(getTileSize(), getTileSize());
		MainFrame.gridSize = new Dimension(getMapSize().x * MainFrame.tileSize.width + 1, getMapSize().y * MainFrame.tileSize.height + 1);
		MainFrame.deslocamento = new Point((MainFrame.LARG_DEFAULT / 2) - (MainFrame.gridSize.width / 2), (MainFrame.ALT_DEFAULT / 2) - (MainFrame.gridSize.height / 2));
		
		MainFrame.mf.Clear();
		
		MainFrame.mf.addComponent(new Grid());
		
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
