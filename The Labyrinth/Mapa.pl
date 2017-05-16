:- dynamic posicao_jogador/2.

tile_size(60).

tamanho_mapa(10, 10).

posicao_jogador(1, 1).

acao(mover_cima) :- posicao_jogador(X, Y), NY is Y - 1, mapa(X, NY), atualiza_posicao_jogador(X, NY).
acao(mover_baixo) :- posicao_jogador(X, Y), NY is Y + 1, mapa(X, NY), atualiza_posicao_jogador(X, NY).
acao(mover_esquerda) :- posicao_jogador(X, Y), NX is X + 1, mapa(NX, Y), atualiza_posicao_jogador(NX, Y).
acao(mover_direita) :- posicao_jogador(X, Y), NX is X - 1, mapa(NX, Y), atualiza_posicao_jogador(NX, Y).

atualiza_posicao_jogador(X, Y) :- retract( posicao_jogador(_, _) ), assert( posicao_jogador(X, Y) ), !.

maior(H, [H]).
maior(H, [H|T]) :- maior(M, T), H >= M.
maior(M, [H|T]) :- maior(M, T), M > H.









mapa(	0	,	0	).
mapa(	1	,	0	).
mapa(	2	,	0	).
mapa(	3	,	0	).
mapa(	4	,	0	).
mapa(	5	,	0	).
mapa(	6	,	0	).
mapa(	7	,	0	).
mapa(	8	,	0	).
mapa(	9	,	0	).
mapa(	10	,	0	).
mapa(	0	,	1	).
mapa(	1	,	1	).
mapa(	2	,	1	).
mapa(	3	,	1	).
mapa(	4	,	1	).
mapa(	5	,	1	).
mapa(	6	,	1	).
mapa(	7	,	1	).
mapa(	8	,	1	).
mapa(	9	,	1	).
mapa(	10	,	1	).
mapa(	0	,	2	).
mapa(	1	,	2	).
mapa(	2	,	2	).
mapa(	3	,	2	).
mapa(	4	,	2	).
mapa(	5	,	2	).
mapa(	6	,	2	).
mapa(	7	,	2	).
mapa(	8	,	2	).
mapa(	9	,	2	).
mapa(	10	,	2	).
mapa(	0	,	3	).
mapa(	1	,	3	).
mapa(	2	,	3	).
mapa(	3	,	3	).
mapa(	4	,	3	).
mapa(	5	,	3	).
mapa(	6	,	3	).
mapa(	7	,	3	).
mapa(	8	,	3	).
mapa(	9	,	3	).
mapa(	10	,	3	).
mapa(	0	,	4	).
mapa(	1	,	4	).
mapa(	2	,	4	).
mapa(	3	,	4	).
mapa(	4	,	4	).
mapa(	5	,	4	).
mapa(	6	,	4	).
mapa(	7	,	4	).
mapa(	8	,	4	).
mapa(	9	,	4	).
mapa(	10	,	4	).
mapa(	0	,	5	).
mapa(	1	,	5	).
mapa(	2	,	5	).
mapa(	3	,	5	).
mapa(	4	,	5	).
mapa(	5	,	5	).
mapa(	6	,	5	).
mapa(	7	,	5	).
mapa(	8	,	5	).
mapa(	9	,	5	).
mapa(	10	,	5	).
mapa(	0	,	6	).
mapa(	1	,	6	).
mapa(	2	,	6	).
mapa(	3	,	6	).
mapa(	4	,	6	).
mapa(	5	,	6	).
mapa(	6	,	6	).
mapa(	7	,	6	).
mapa(	8	,	6	).
mapa(	9	,	6	).
mapa(	10	,	6	).
mapa(	0	,	7	).
mapa(	1	,	7	).
mapa(	2	,	7	).
mapa(	3	,	7	).
mapa(	4	,	7	).
mapa(	5	,	7	).
mapa(	6	,	7	).
mapa(	7	,	7	).
mapa(	8	,	7	).
mapa(	9	,	7	).
mapa(	10	,	7	).
mapa(	0	,	8	).
mapa(	1	,	8	).
mapa(	2	,	8	).
mapa(	3	,	8	).
mapa(	4	,	8	).
mapa(	5	,	8	).
mapa(	6	,	8	).
mapa(	7	,	8	).
mapa(	8	,	8	).
mapa(	9	,	8	).
mapa(	10	,	8	).
mapa(	0	,	9	).
mapa(	1	,	9	).
mapa(	2	,	9	).
mapa(	3	,	9	).
mapa(	4	,	9	).
mapa(	5	,	9	).
mapa(	6	,	9	).
mapa(	7	,	9	).
mapa(	8	,	9	).
mapa(	9	,	9	).
mapa(	10	,	9	).
mapa(	0	,	10	).
mapa(	1	,	10	).
mapa(	2	,	10	).
mapa(	3	,	10	).
mapa(	4	,	10	).
mapa(	5	,	10	).
mapa(	6	,	10	).
mapa(	7	,	10	).
mapa(	8	,	10	).
mapa(	9	,	10	).
mapa(	10	,	10	).

