:- dynamic tela_ia/3.

tela_ia(1, [verde], apagada).
tela_ia(2, [verde], apagada).
tela_ia(3, [verde], apagada).
tela_ia(4, [verde], apagada).

cor_ia(vermelho).
cor_op(C) :- cor_ia(Cor), dicionario_turno(Cor, C).

decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, [Cor], apagada), cor_ia(Cor).
decidir(acao(troca_cor, TelaId, Direcao)) :- tela_ia(TelaId, [Cor], apagada), dicionario_troca(Cor, CorIa, Direcao), cor_ia(CorIa).
decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, [CorOp], acesa), cor_op(CorOp).
decidir(acao(troca_sinal, TelaId)) :- tela_ia(TelaId, L, apagada), cor_op(CorOp), count(CorOp, L, 0).
decidir(acao(troca_cor, TelaId, Direcao)) :- tela_ia(TelaId, L, apagada), cor_op(CorOp), atualiza_lista_cores(L, Direcao, NL), count(CorOp, NL, 0).

atualiza_ia(acao(troca_cor, TelaId, Direcao), C) :- cor_ia(C), tela_ia(TelaId, Cor, Sinal), atualiza_lista_cores(Cor, Direcao, NovasCores), !, atualiza_tela_ia(TelaId, NovasCores, Sinal).
atualiza_ia(acao(troca_cor, TelaId, _), C) :- cor_op(C), tela_ia(TelaId, Cor, Sinal), atualiza_lista_cores(Cor, ambas, NovasCores), !, atualiza_tela_ia(TelaId, NovasCores, Sinal).
atualiza_ia(acao(troca_sinal, TelaId), _) :- tela(TelaId, Cor, Sinal), atualiza_tela_ia(TelaId, [Cor], Sinal).

atualiza_tela_ia(TelaId, Cor, Sinal) :- retract( tela_ia(TelaId, _, _) ), assert( tela_ia(TelaId, Cor, Sinal) ).

atualiza_lista_cores([H], ambas, [NH1, NH2]) :- dicionario_troca(H, NH1, direita), dicionario_troca(H, NH2, esquerda).
atualiza_lista_cores([H|T], ambas, [NH1, NH2|L]) :- dicionario_troca(H, NH1, direita), dicionario_troca(H, NH2, esquerda), atualiza_lista_cores(T, ambas, L).

atualiza_lista_cores([H], Direcao, [NH]) :- dicionario_troca(H, NH, Direcao).
atualiza_lista_cores([H|T], Direcao, [NH|L]) :- dicionario_troca(H, NH, Direcao), atualiza_lista_cores(T, Direcao, L).

count(_, [], 0) :- !.
count(X, [X|T], I) :- count(X, T, NI), I is NI + 1.
count(X, [H|T], I) :- count(X, T, I), X \= H.

append([], L, L).
append([X|L1], L2, [X|L3]) :- append(L1, L2, L3).