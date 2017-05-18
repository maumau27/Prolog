:- dynamic on/2.
:- dynamic move/3.

on(c, table).
on(b, c).
on(a, b).

put_on(A, B) :-
	A \== table,
	A \== B,
	on(A, X),
	clear(A),
	clear(B),
	retract(on(A, X)),
	assert(on(A, B)),
	assert(move(A,X,B)).
	
clear(table).
clear(A) :-
	not(on(_, A)).
	
r_put_on(A,B) :-
     on(A,B).
r_put_on(A,B) :-
     not(on(A,B)),
     A \== table,
     A \== B,
     clear_off(A),
     clear_off(B),
     on(A,X),
     retract(on(A,X)),
     assert(on(A,B)),
     assert(move(A,X,B)).

clear_off(table).    
clear_off(A) :-      
     not(on(_X,A)).
clear_off(A) :-
     A \== table,
     on(X,A),
     clear_off(X),     
     retract(on(X,A)),
     assert(on(X,table)),
     assert(move(X,A,table)).