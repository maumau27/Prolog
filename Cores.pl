:- dynamic tela/3.
:- dynamic turno/1.
:- dynamic movimentos_restantes/1.

tela( (1, 1), verde, acesa ).
tela( (1, 2), verde, acesa ).
tela( (2, 1), verde, acesa ).
tela( (2, 2), verde, acesa ).

turno(azul).
movimentos_restantes(4).

troca_cor(X, Y, direita) :- tela( (X, Y), Cor, Sinal ), Sinal = apagada, ( (Cor = verde, atualiza_tela(X, Y, azul, Sinal)) ; (Cor = azul, atualiza_tela(X, Y, vermelho, Sinal)) ; (Cor = vermelho, atualiza_tela(X, Y, verde, Sinal)) ) , !.

troca_cor(X, Y, esquerda) :- tela( (X, Y), Cor, Sinal ), Sinal = apagada, ( (Cor = azul, atualiza_tela(X, Y, verde, Sinal)) ; (Cor = vermelho, atualiza_tela(X, Y, azul, Sinal)) ; (Cor = verde, atualiza_tela(X, Y, vermelho, Sinal)) ) , !.

flipa_tela(X, Y) :- tela( (X, Y), Cor, Sinal ), ( (Sinal = acesa, atualiza_tela(X, Y, Cor, apagada)) ; (Sinal = apagada, atualiza_tela(X, Y, Cor, acesa)) ), !.

jogada(flipar, X, Y) :- flipa_tela(X, Y), reduz_movimentos_restantes(1), (checa_vencedor ; true) , !.
jogada(virar_direita, X, Y) :- troca_cor_direita(X, Y), reduz_movimentos_restantes(1), !.

checa_vencedor :- ( forall( tela( (_,_), Cor, Sinal ), ( Cor = azul, Sinal = acesa ) ), write("azul venceu") ) ; ( forall( tela( (_,_), Cor, Sinal ), ( Cor = vermelho, Sinal = acesa ) ), write("vermelho venceu") ), ! .

troca_turno :- turno(X), retract( turno(X) ), ( (X = azul, assert( turno(vermelho) )) ; (X = vermelho, assert( turno(azul) )) ), assert( movimentos_restantes(4) ), !.

atualiza_tela(X, Y, Cor, Sinal) :- retract( tela( (X, Y), _ , _ ) ) , assert( tela( (X, Y), Cor, Sinal ) ).
reduz_movimentos_restantes(Quantidade) :- retract( movimentos_restantes( X ) ) , W is X - Quantidade, assert( movimentos_restantes( W ) ), ( (W) = 0, troca_turno ; true ), !.