:- dynamic posicao_jogador/2.
:- dynamic tile_size/1.
:- dynamic grid/2.

tile_size(30).

tamanho_mapa(50, 50).

posicao_jogador(4, 4).

acao(mover_cima) :- posicao_jogador(X, Y), NY is Y - 1, mapa(X, NY), atualiza_posicao_jogador(X, NY).
acao(mover_baixo) :- posicao_jogador(X, Y), NY is Y + 1, mapa(X, NY), atualiza_posicao_jogador(X, NY).
acao(mover_esquerda) :- posicao_jogador(X, Y), NX is X + 1, mapa(NX, Y), atualiza_posicao_jogador(NX, Y).
acao(mover_direita) :- posicao_jogador(X, Y), NX is X - 1, mapa(NX, Y), atualiza_posicao_jogador(NX, Y).

atualiza_posicao_jogador(X, Y) :- retract( posicao_jogador(_, _) ), assert( posicao_jogador(X, Y) ), !.

atualiza_tile_size(X) :- retract( tile_size(_) ), assert( tile_size(X) ), !.

maior(H, [H]).
maior(H, [H|T]) :- maior(M, T), H >= M.
maior(M, [H|T]) :- maior(M, T), M > H.

go_to(X, Y) :- atualiza_posicao_jogador(X, Y).

gerar_mapa(X, Y) :- tamanho_mapa(X, Y), false.
gerar_mapa(-1, 0) :- !.
gerar_mapa(-1, Y) :- tamanho_mapa(X, _), NY is Y - 1, gerar_mapa(X, NY), !.
gerar_mapa(X, Y) :- 
	NX is X - 1,
	assert(mapa(X, Y)),
	gerar_mapa(NX, Y).
