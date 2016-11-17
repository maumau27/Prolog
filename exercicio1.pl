adiciona(X, L1, L2) :- L2 = [X | L1].

apaga(X, L1 , L2) :- L1 = [X | L2].
apaga(X, [Xi | L1], L2) :- Xi \= X, apaga(X, L1, L2).

membro(X, [X | _]).
membro(X, [_|L]) :- membro(X, L).

concatena([L1 | []], L2, [L1 | L2]).
concatena([Cabeca | Cauda], L2, L3) :- concatena(Cauda, L4, L3), L4	 = [Cabeca | L2] .

comprimento(0, []).
comprimento(Y, [_|Cauda]) :- comprimento(X, Cauda), Y is X + 1 .

maximo(Cabeca ,[Cabeca | []]).
maximo(Cabeca, [Cabeca | Cauda]) :- maximo(Maior, Cauda) , Cabeca >= Maior.
maximo(Maior, [Cabeca | Cauda]) :- maximo(Maior, Cauda), Maior > Cabeca.

nelem(1, [Cabeca | _], Cabeca).
nelem(N, [_| Cauda], X) :- Ni is N - 1,  nelem(Ni, Cauda, X).

media(Cabeca, [Cabeca | _]).
media(X, [_ | Cauda]) :- media(N, Cauda), X > N.

ordena([], []).
ordena(L, L3) :- maximo(X, L), apaga(X, L, L1) , ordena(L1, L2),  L3 =  [X | L2].

normal([Cabeca | []]) :- write(Cabeca).
normal([Cabeca | Cauda]) :- write(Cabeca), normal(Cauda).

inversa([Cabeca | []]) :- write(Cabeca).
inversa([Cabeca | Cauda]) :- inversa(Cauda), write(Cabeca).