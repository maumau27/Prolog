adiciona(X, L1, L2) :- L2 = [X | L1].

apaga(X, L1 , L2) :- L1 = [X | L2].
apaga(X, [Xi | L1], L2) :- Xi \= X, apaga(X, L1, L2).

membro(X, [X | _]).
membro(X, [_|L]) :- membro(X, L).

concatena(L1, L2, L3) :- L1 = [X | L], L3 = [X | L2], L \= [].

comprimento(0, []).
comprimento(Y, [_|L]) :- comprimento(X, L), Y is X + 1 .