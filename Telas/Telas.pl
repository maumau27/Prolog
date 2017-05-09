:- dynamic tela/3.
:- dynamic turno/2.
:- dynamic troca_cor_recente/1.

tela(1, azul, apagada).
tela(2, azul, apagada).
tela(3, azul, apagada).
tela(4, azul, apagada).

troca_cor_recente(0).

turno(azul, 3).

dicionario_turno(azul, vermelho).
dicionario_turno(vermelho, azul).

dicionario_troca(azul, verde, esquerda).
dicionario_troca(verde, vermelho, esquerda).
dicionario_troca(vermelho, azul, esquerda).
dicionario_troca(azul, vermelho, direita).
dicionario_troca(vermelho, verde, direita).
dicionario_troca(verde, azul, direita).

acao(troca_cor, TelaId, Direcao) :- troca_cor(TelaId, Direcao), tela(TelaId, Cor, Sinal), turno(Jogador, _), atualiza_turno(Cor, Sinal), atualiza_ia(acao(troca_cor, TelaId, Direcao), Jogador), atualiza_troca_cor_recente(TelaId), !.
acao(troca_sinal, TelaId) :- troca_sinal(TelaId), tela(TelaId, Cor, Sinal), atualiza_turno(Cor, Sinal), atualiza_ia(acao(troca_sinal, TelaId), _), atualiza_troca_cor_recente(0), !.

troca_cor(TelaId, Direcao) :- tela(TelaId, Cor, apagada), dicionario_troca(Cor, NovaCor, Direcao), atualiza_tela(TelaId, NovaCor, apagada).

troca_sinal(TelaId) :- tela(TelaId, Cor, apagada), atualiza_tela(TelaId, Cor, acesa), !.
troca_sinal(TelaId) :- tela(TelaId, Cor, acesa), atualiza_tela(TelaId, Cor, apagada), !.

checa_vencedor(Vencedor) :- tela(1, Vencedor, acesa), tela(2, Vencedor, acesa), tela(3, Vencedor, acesa), tela(4, Vencedor, acesa), !.

atualiza_tela(TelaId, Cor, Sinal) :- retract( tela(TelaId, _, _) ), assert( tela(TelaId, Cor, Sinal) ).

atualiza_troca_cor_recente(TelaId) :- retract( troca_cor_recente(_) ), assert( troca_cor_recente(TelaId) ).

atualiza_turno(Cor, Sinal) :- turno(Jogador, _), Cor = Jogador, Sinal = acesa, !.
atualiza_turno(Cor, Sinal) :- turno(Jogador, _), dicionario_turno(Jogador, NovoJogador), Cor = NovoJogador, Sinal = acesa, retract( turno(Jogador, _) ), assert( turno(NovoJogador, 3) ), !.
atualiza_turno(_, _) :- turno(Jogador, 1), retract( turno(Jogador, _) ), dicionario_turno(Jogador, NovoJogador), assert( turno(NovoJogador, 3) ), !.
atualiza_turno(_, _) :- turno(Jogador, JogadasRestantes), retract( turno(Jogador, _) ), NovaJogadasRestantes is JogadasRestantes - 1, assert( turno(Jogador, NovaJogadasRestantes) ), !.


:- dynamic tela_ia/3.
:- dynamic cor_ia/1.

tela_ia(1, [verde], apagada).
tela_ia(2, [verde], apagada).
tela_ia(3, [verde], apagada).
tela_ia(4, [verde], apagada).

cor_op(C) :- cor_ia(Cor), dicionario_turno(Cor, C).

decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, [Cor], apagada), cor_ia(Cor).
decidir(acao(troca_cor, TelaId, Direcao)) :- tela_ia(TelaId, [Cor], apagada), dicionario_troca(Cor, CorIa, Direcao), cor_ia(CorIa).
decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, [CorOp], acesa), cor_op(CorOp).
decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, L, apagada), cor_op(CorOp), count(CorOp, L, 0).
decidir(acao(troca_cor, TelaId, Direcao)) :- tela_ia(TelaId, L, apagada), cor_op(CorOp), atualiza_lista_cores(L, Direcao, NL), count(CorOp, NL, 0).
decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, [Cor], acesa), Cor = verde.
decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, _, apagada).

atualiza_ia(acao(troca_cor, TelaId, Direcao), C) :- cor_ia(C), tela_ia(TelaId, Cor, Sinal), atualiza_lista_cores(Cor, Direcao, NovasCores), !, atualiza_tela_ia(TelaId, NovasCores, Sinal).
atualiza_ia(acao(troca_cor, TelaId, _), C) :- cor_op(C), tela_ia(TelaId, Cor, Sinal), atualiza_lista_cores(Cor, ambas, NovasCores), !, atualiza_tela_ia(TelaId, NovasCores, Sinal).
atualiza_ia(acao(troca_sinal, TelaId), _) :- tela(TelaId, Cor, Sinal), atualiza_tela_ia(TelaId, [Cor], Sinal).

atualiza_tela_ia(TelaId, Cor, Sinal) :- retract( tela_ia(TelaId, _, _) ), assertz( tela_ia(TelaId, Cor, Sinal) ).

atualiza_lista_cores([H], ambas, [NH1, NH2]) :- dicionario_troca(H, NH1, direita), dicionario_troca(H, NH2, esquerda).
atualiza_lista_cores([H|T], ambas, [NH1, NH2|L]) :- dicionario_troca(H, NH1, direita), dicionario_troca(H, NH2, esquerda), atualiza_lista_cores(T, ambas, L).

atualiza_lista_cores([H], Direcao, [NH]) :- dicionario_troca(H, NH, Direcao).
atualiza_lista_cores([H|T], Direcao, [NH|L]) :- dicionario_troca(H, NH, Direcao), atualiza_lista_cores(T, Direcao, L).

count(_, [], 0) :- !.
count(X, [X|T], I) :- count(X, T, NI), I is NI + 1.
count(X, [H|T], I) :- count(X, T, I), X \= H.

append([], L, L).
append([X|L1], L2, [X|L3]) :- append(L1, L2, L3).