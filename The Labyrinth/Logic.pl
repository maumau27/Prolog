:- dynamic posicao_jogador/2.

tile_size(10).

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