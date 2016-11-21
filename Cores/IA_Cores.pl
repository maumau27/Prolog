:- dynamic tela_ia/3.

tela_ia( (1, 1), [ verde ], acesa ).
tela_ia( (1, 2), [ verde ], acesa ).
tela_ia( (2, 1), [ verde ], acesa ).
tela_ia( (2, 2), [ verde ], acesa ).




evento_ia(flipar, X, Y) :-			tela( (X, Y), Cor, Sinal), atualiza_tela_ia(X, Y, [Cor], Sinal), !.
evento_ia(virar_direita, X, Y) :-	tela_ia( (X, Y), ListaCores, Sinal), virar_lista(ListaCores, ListaNova, direita), atualiza_tela_ia(X, Y, ListaNova, Sinal), !.
evento_ia(virar_esquerda, X, Y) :-	tela_ia( (X, Y), ListaCores, Sinal), virar_lista(ListaCores, ListaNova, esquerda), atualiza_tela_ia(X, Y, ListaNova, Sinal), !.
evento_ia(virar, X, Y) :-			tela_ia( (X, Y), ListaCores, Sinal), virar_lista(ListaCores, ListaNova, ambas), atualiza_tela_ia(X, Y, ListaNova, Sinal), !.




virar_lista(ListaVelha, ListaNova, ambas) :-	virar_lista(ListaVelha, Lista1, direita), virar_lista(ListaVelha, Lista2, esquerda), concatena(Lista1, Lista2, ListaNova), !.
virar_lista([Cabeca_velha], [Cabeca_nova], Direcao) :-	troca_cor_ia(Cabeca_velha, Cabeca_nova, Direcao), !.
virar_lista([Cabeca_velha | Corpo_velho], [Cabeca_nova | Corpo_novo], Direcao) :-	troca_cor_ia(Cabeca_velha, Cabeca_nova, Direcao), virar_lista(Corpo_velho, Corpo_novo, Direcao).

atualiza_tela_ia(X, Y, ListaCores, Sinal) :-	retract( tela_ia( (X, Y), _, _) ), assert( tela_ia( (X, Y), ListaCores, Sinal) ).

troca_cor_ia(Cor, NovaCor, direita) :-		( (Cor = verde, NovaCor = azul) ; (Cor = azul, NovaCor = vermelho) ; (Cor = vermelho, NovaCor = verde) ) , !.
troca_cor_ia(Cor, NovaCor, esquerda) :-	( (Cor = azul, NovaCor = verde) ; (Cor = vermelho, NovaCor = azul) ; (Cor = verde, NovaCor = vermelho) ) , !.



concatena([],L,L).
concatena([X|L1],L2,[X|L3]):- concatena(L1,L2,L3).