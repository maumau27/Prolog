:- dynamic tela/3.
:- dynamic turno/2.

tela(1, verde, apagada).
tela(2, vermelho, apagada).
tela(3, verde, apagada).
tela(4, verde, apagada).

turno(azul, 2).

dicionario_turno(azul, vermelho).
dicionario_turno(vermelho, azul).

dicionario_troca(verde, azul, esquerda).
dicionario_troca(azul, vermelho, esquerda).
dicionario_troca(vermelho, verde, esquerda).
dicionario_troca(azul, verde, direita).
dicionario_troca(vermelho, azul, direita).
dicionario_troca(verde, vermelho, direita).

acao(troca_cor, TelaId, Direcao) :- troca_cor(TelaId, Direcao), tela(TelaId, Cor, Sinal), turno(Jogador, _), atualiza_turno(Cor, Sinal), atualiza_ia(acao(troca_cor, TelaId, Direcao), Jogador), !.
acao(troca_sinal, TelaId) :- troca_sinal(TelaId), tela(TelaId, Cor, Sinal), atualiza_turno(Cor, Sinal), atualiza_ia(acao(troca_sinal, TelaId), _), !.

troca_cor(TelaId, Direcao) :- tela(TelaId, Cor, apagada), dicionario_troca(Cor, NovaCor, Direcao), atualiza_tela(TelaId, NovaCor, apagada).

troca_sinal(TelaId) :- tela(TelaId, Cor, apagada), atualiza_tela(TelaId, Cor, acesa), !.
troca_sinal(TelaId) :- tela(TelaId, Cor, acesa), atualiza_tela(TelaId, Cor, apagada), !.

checa_vencedor(Vencedor) :- forall( tela(_, Cor, Sinal), (Sinal = acesa, Cor = Vencedor, \+ Cor = verde) ), tela(_, Vencedor, _), !.

atualiza_tela(TelaId, Cor, Sinal) :- retract( tela(TelaId, _, _) ), assert( tela(TelaId, Cor, Sinal) ).

atualiza_turno(Cor, Sinal) :- turno(Jogador, _), Cor = Jogador, Sinal = acesa, !.
atualiza_turno(Cor, Sinal) :- turno(Jogador, _), dicionario_turno(Jogador, NovoJogador), Cor = NovoJogador, Sinal = acesa, retract( turno(Jogador, _) ), assert( turno(NovoJogador, 2) ), !.
atualiza_turno(_, _) :- turno(Jogador, 1), retract( turno(Jogador, _) ), dicionario_turno(Jogador, NovoJogador), assert( turno(NovoJogador, 2) ), !.
atualiza_turno(_, _) :- turno(Jogador, JogadasRestantes), retract( turno(Jogador, _) ), NovaJogadasRestantes is JogadasRestantes - 1, assert( turno(Jogador, NovaJogadasRestantes) ), !.