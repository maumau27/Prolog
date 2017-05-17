% implementation of Strips, which is a means-ends planner
% this first simple version is without goal protection or goal regression

% Yoav Shoham's section8.2 file from his book
% "Artifical Intelligence Techniques in Prolog" Morgan Kaufman
% as modified by Alan Bond
% using Sicstus list libraries for reverse and delete
% added subset and subtract
% replaced ttyflush by nl
% changed definition of union

:-use_module(library(lists)).

:- leash([call,exit,fail,redo]).

:- dynamic att/1.

att([[emotion|10],[life|20]]).

getAtt(X,Y):- att(L), pAtt(L,X,Y).

pAtt([[X|Y]|_],X,Y).
pAtt([_|L],X,Y):-pAtt(L,X,Y).

changeAtt(X,Y):- att(L), cAtt(L,LL,X,Y), retract(att(L)), assert(att(LL)).

cAtt([[X|Y]|[]],[[X|Z]|[]],A,_) :- X \= A,  Z = Y, !.
cAtt([[X|_]|[]],[[X|Z]|[]],A,B) :- X = A,  Z = B, !.

cAtt([[X|Y]|XX],[[X|Z]|LL],A,B) :- X \= A,  Z = Y, cAtt([XX],LL,A,B).
cAtt([[X|_]|XX],[[X|Z]|LL],A,B) :- X = A,  Z = B, cAtt([XX],LL,A,B).

%cAtt(L,LL,X,Y):-cAtt(L,LL,X,Y).



strips(Goal, InitState, Plan):-
	strips1(Goal, InitState, [], [], _, RevPlan),
        reverse(RevPlan, Plan).

% strips(+GoalList, +State, +Plan, +ForbiddenActions, -NewState, -NewPlan )
strips1(GoalList, State, Plan, _, State, Plan) :-
	subset(GoalList,State).

strips1(GoalList, State, Plan, ForbiddenActions, NewState, NewPlan):-
	member(Goal, GoalList),
        not(member(Goal, State)),
	adds(Ac, Goal),
	% No repeat action during the planing
	not(member(Ac, ForbiddenActions)),
	preclist(Ac, PrecList), 
	strips1(PrecList, State, Plan, [Ac| ForbiddenActions], TmpState1, TmpPlan1), 
	apply_rule(Ac, TmpState1, TmpState2),	
%	append([Ac|TmpPlan1], Plan, TmpPlan2),
	strips1(GoalList, TmpState2, [Ac|TmpPlan1], ForbiddenActions, NewState, NewPlan).

apply_rule(Ac, State, NewState):-
        nl,write('doing '),write(Ac),nl,
  	dellist(Ac, DelList),
  	subtract(State, DelList, TmpState),
	addlist(Ac, AddList),
	union(AddList, TmpState, NewState).

% load utilities
%:-consult(sutils).
subset([E|[]], List):- member(E,List).
subset([E|Tail], List):- subset(Tail,List), member(E,List).

not(P) :- call(P), !, fail. 
not(P). 

subtract([], _, []) :- !.
subtract([A|C], B, D) :- memberchk(A, B), !, subtract(C, B, D).
subtract([A|B], C, [A|D]) :- subtract(B, C, D).

union([X|Y],Z,W) :- member(X,Z),  union(Y,Z,W).
union([X|Y],Z,[X|W]) :- \+ member(X,Z), union(Y,Z,W).
union([],Z,Z).

% operators for blocks world

/* stack(X,Y) */
preclist(stack(X,Y), [holding(X),clear(Y)]).
dellist(stack(X,Y), [holding(X),clear(Y)]).
addlist(stack(X,Y), [handempty,on(X,Y),clear(X)]).

/* pickup(X) */
preclist(pickup(X),  [ontable(X), clear(X), handempty]).
dellist(pickup(X),  [ontable(X),clear(X),handempty]).
addlist(pickup(X),  [holding(X)]).

/* unstack(X,Y) */
preclist(unstack(X,Y), [on(X,Y), clear(X), handempty]).
dellist(unstack(X,Y), [handempty,clear(Y),on(X,Y)]).
addlist(unstack(X,Y), [holding(X),clear(Y)]).

/* putdown(X) */
preclist(putdown(X), [holding(X)]).
dellist(putdown(X), [holding(X)]).
addlist(putdown(X), [ontable(X),handempty,clear(X)]).

prec( Action, Cond ) :- preclist( Action, Plist ), member( Cond, Plist ).
adds( Action, Cond ) :- addlist( Action, Alist), member( Cond, Alist ). 
dels( Action, Cond ) :- dellist( Action, Dlist), member( Cond, Dlist ). 


inconsistent(holding(X), on(X,_)).
inconsistent(holding(X), on(_,X)).
inconsistent(holding(X), ontable(X)). 
inconsistent(holding(_), handempty).
inconsistent(holding(X), holding(Y)) :- not(X=Y).
inconsistent(on(X,Y), on(X,Z)) :- not(Z=Y).
inconsistent(on(Y,X), on(Z,X)) :- not(Z=Y).
inconsistent(clear(X), on(_,X)).
inconsistent(clear(X), holding(X)).
inconsistent(ontable(X), on(X,_)).

% some useful states

init_state1([clear(a),clear(b),clear(c),ontable(a),ontable(b),ontable(c),handempty]).
goal1([on(a,b),on(b,c)]).

init_state2([clear(a),clear(b),clear(c),ontable(a),ontable(b),ontable(c),handempty]).
goal2([on(b,c),on(a,b) ]).

init_state3([clear(b),clear(c),ontable(a),ontable(b),on(c,a),handempty]).
goal3([on(a,b),on(b,c)]).

% Impossible goal
init_state4([clear(a),clear(b),ontable(a),ontable(b),handempty]).
goal4([on(a,b),on(b,a)]).

init_state5([clear(c),ontable(a),on(b,a),on(c,b),handempty]).
goal5([on(a,b),on(b,c)]).

init_state6([clear(a),clear(b),clear(c),ontable(a),ontable(b),ontable(c),handempty]).
goal6([holding(a)]).




writeplan(X):- print_all(X).

print_all([]).
print_all([X|Rest]) :- write(X), nl, print_all(Rest).

% tests

test1 :-
	init_state1(InitState),
	goal1(Goal),
	do_it(Goal, InitState).
test2 :-
	init_state2(InitState),
	goal2(Goal),
	do_it(Goal, InitState).
test3 :-
	init_state3(InitState),
	goal3(Goal),
	do_it(Goal, InitState).
test4:-
	init_state4(InitState),
	goal4(Goal),
	do_it(Goal, InitState).
test5 :-
	init_state5(InitState),
	goal5(Goal),
	do_it(Goal, InitState).

test6 :-
	init_state6(InitState),
	goal6(Goal),
	do_it(Goal, InitState).

do_it(Goal, InitState) :-
	strips(Goal,InitState,Plan),
	write('from: '),write(InitState),nl,
	write('to: '),write(Goal),nl,
	write('do  -->   '),nl,
      	reverse(Plan,RPlan),
	       	writeplan(RPlan),nl.

test2(Plan) :-
strips([on(b,c), on(a,b)],
       [clear(a),clear(b),clear(c),ontable(a),ontable(b),ontable(c),handempty],
       Plan). 

test3(Plan) :-
strips([on(a,b)],
       [clear(a),clear(b),clear(c),ontable(a),ontable(b),ontable(c),handempty],
       Plan). 


test5(Plan) :-
strips([on(a,b), on(b,c)],
       [clear(c),ontable(a),on(b,a),on(c,b),handempty],
       Plan).
