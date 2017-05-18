exe(L) :- plan(estado(na_janela, no_chao, na_porta, nao_tem), estado(na_porta, no_chao, _, tem), L).

plan(Objetivo, Objetivo, []).
plan(EstadoInicial, Objetivo, [X|L]):-
	plan(Parcial, Objetivo, L),
	acao(EstadoInicial, X, Parcial).

acao(
	estado(P1, no_chao, P1, Banana),
	empurrar_caixa(P1, P2),
	estado(P2, no_chao, P2, Banana)
	).
	
acao(
	estado(P, acima_caixa, Caixa, Banana),
	descer,
	estado(P, no_chao, Caixa, Banana)
	).
	
acao(
	estado(no_centro, acima_caixa, no_centro, nao_tem),
	pegar_banana,
	estado(no_centro, acima_caixa, no_centro, tem)
	).

acao(
	estado(P, no_chao, P, Banana),
	subir,
	estado(P, acima_caixa, P, Banana)
	).

acao(
	estado(P1, no_chao, Caixa, Banana),
	caminhar(P1, P2),
	estado(P2, no_chao, Caixa, Banana)
	).
		
