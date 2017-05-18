plan(Objetivo, Objetivo, []).
plan(EstadoInicial, Objetivo, ListaAcoes)
	action(X, EstadoInicial, EstadoParcial),
	insert([X], ListaAcoes, NovaLista),
	plan(EstadoParcial, Objetivo, NovaLista).

%plan([on(c, table), on(b, c), on(a, b)], [on(a, table), on(b, a), on(c, a)]).

action(put_on(A, B), EstadoInicial, EstadoFinal) :- 
	A \= table,
	A \= B,
	clear(A, EstadoInicial),
	clear(B, EstadoInicial),
	find(on(A, X), EstadoInicial),
	remove(on(A, X), EstadoInicial, EstadoParcial),
	insert([on(A, B)], EstadoParcial, EstadoFinal).
	
clear(table, _).
clear(A, Estado) :-
	not(find(on(_, A), Estado)).

remove(E, [E|T], T).
remove(E, [H|T], [H|L]) :-
	remove(E, T, L).
	
insert([E], L, [E|L]).
insert([E|T], L, [E|X]) :- insert(T, L, X).
	
find(E, [E|_]).
find(E, [_|T]) :- find(E, T).