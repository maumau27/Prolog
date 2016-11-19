:- dynamic tela/3.
:- dynamic turno/1.
:- dynamic movimentos_restantes/1.

tela( (1, 1), [ verde ], acesa ).
tela( (1, 2), [ verde ], acesa ).
tela( (2, 1), [ verde ], acesa ).
tela( (2, 2), [ verde ], acesa ).

troca_cor(Original, Nova, direita) :-
troca_cor(Original, Nova, esquerda)