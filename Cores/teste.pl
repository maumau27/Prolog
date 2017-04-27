individuo(alice).
individuo(jaco).
individuo(andre).
caracteristica(jaco, homem).
caracteristica(jaco, rico).
caracteristica(jaco, sozinho).
caracteristica(jaco, imponente).
caracteristica(alice, mulher).
caracteristica(alice, sociavel).
caracteristica(alice, intereseiro).
caracteristica(andre, homem).
caracteristica(andre, rico).
caracteristica(andre, inferior).

motivomatar(X, Y) :- caracteristica(Y, rico), caracteristica(X, intereseiro), Y \= X.
motivomatar(X, Z) :- relacao(X, ama, Y), relacao(Y, ama, Z), relacao(X, odeia, Z), Y \= Z, X \= Y, X \= Z.
motivomatar(X, Y) :- relacao(X, ama, Z), relacao(Y, importa, Z), relacao(X, teme, Y), caracteristica(Y, imponente), Y \= Z, X \= Y, X \= Z.

relacao(X, ama, Y) :- caracteristica(Y, sociavel), caracteristica(X, sozinho), Y \= X.
relacao(X, ama, Y) :- caracteristica(X, intereseiro), caracteristica(Y, rico), Y \= X.
relacao(X, odeia, Y) :- caracteristica(Y, rico), caracteristica(X, rico), Y \= X.
relacao(X, teme, Y) :- caracteristica(X, inferior), caracteristica(Y, imponente), Y \= X.
relacao(X, importa, Y) :- caracteristica(X, imponente), caracteristica(Y, mulher), Y \= X.
