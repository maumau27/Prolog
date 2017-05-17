%estado(na_janela, no_chao, na_porta, nao_tem).

plan(EstadoInicial, EstadoInicial, []).
	
plan(EstadoInicial, Objetivo, NL):-
	acao(EstadoInicial, X, Objetivo),
	append(X, [], NL).

acao(
	estado(Pos1, no_chao, Caixa, Banana),
	mover(Pos1, Pos2),
	estado(Pos2, no_chao, Caixa, Banana)
	).
	
acao(
	estado(Pos, no_chao, Pos, Banana),
	subir,
	estado(Pos, acima_caixa, Pos, Banana)
	).
	
acao(
	estado(Pos, acima_caixa, Pos, Banana),
	descer,
	estado(Pos, no_chao, Pos, Banana)
	).
	
acao(
	estado(no_centro, acima_caixa, no_centro, nao_tem),
	pegar_banana,
	estado(no_centro, acima_caixa, no_centro, tem)
	).
	
acao(
	estado(Pos1, acima_caixa, Pos1, Banana),
	empurar_caixa(Pos1, Pos2),
	estado(Pos2, acima_caixa, Pos2, Banana)
	).
	
append(X, L, [X|L]).