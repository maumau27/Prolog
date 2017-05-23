:- dynamic grid/4.
:- dynamic tile_size/2.
:- dynamic tileSet_tileSize/2.
:- dynamic grid_size/2.

generate_grid(X, Y) :- NX is X - 1, NY is Y - 1, generate_grid_aux(NX, NY, NX).
generate_grid_aux(-1, 0, _) :- !.
generate_grid_aux(-1, Y, I) :- NY is Y - 1, generate_grid_aux(I, NY, I), !.
generate_grid_aux(X, Y, I) :- 
	NX is X - 1,
	add_grid(X, Y, -1, 0),
	generate_grid_aux(NX, Y, I).
	
update_grid_size(X, Y) :- (retract( grid_size(_, _) ) ; true), assert( grid_size(X, Y) ), !.

update_tileSet_tileSize(X, Y) :- (retract( tileSet_tileSize(_) ) ; true), assert( tileSet_tileSize(X, Y) ), !.

update_tile_size(X, Y) :- (retract( tile_size(_, _) ) ; true), assert( tile_size(X, Y) ), !.

update_grid_type(X, Y, NovoTipo) :- retract( grid(X, Y, _, Collision) ), assert( grid(X, Y, NovoTipo, Collision) ), !.

update_grid_collision(X, Y, NewCollision) :- retract( grid(X, Y, Type, _) ), assert( grid(X, Y, Type, NewCollision) ), !.

update_grid(X, Y, NovoTipo, NewCollision) :- retract( grid(X, Y, _, _) ), assert( grid(X, Y, NovoTipo, NewCollision) ), !.

add_grid(X, Y, Type, Collision) :- assert(grid(X, Y, Type, Collision)), !.

remove_grid(X, Y, Type, Collision) :- retract(grid(X, Y, Type, Collision)), !.

clear_grid() :- forall( grid(X, Y, Type, Collision), retract( grid(X, Y, Type, Collision) ) ), !.