%personagem(Nome, Posicao, Objetos).
:- dynamic personagem/3.
:- dynamic objeto/2.

personagem(jorge, sala, []).

mover(Nome, New) :- atualizar_posicao_personagem(Nome, New).
pegar(Nome, Objeto) :- personagem(Nome, L, Old), objeto(Objeto, L), adicionar_objetos(Old, [Objeto], New), atualizar_objetos_personagem(Nome, New).

objeto(faca, cozinha).
lugar(sala).
lugar(quarto).
lugar(banheiro).
lugar(cozinha).

adicionar_objetos(Old, Objetos, New) :- union(Old, Objetos, New).
remover_objetos(Old, Objetos, New) :- subtract(Old, Objetos, New).
atualizar_posicao_personagem(Nome, New) :- lugar(New), retract( personagem(Nome, _, O) ), assert( personagem(Nome, New, O) ).
atualizar_objetos_personagem(Nome, New) :- retract( personagem(Nome, L, _) ), assert( personagem(Nome, L, New) ).