strips(EstadoInicial, ListaObjetivo, Plano):-
	strips1(EstadoInicial, ListaObjetivo, [], [], _, PlanoInvertido),
	reverse(PlanoInvertido, Plano).

strips1(Estado, ListaObjetivo, Plano, _, Estado, Plano):-
	subset(ListaObjetivo, Estado).
	
strips1(Estado, ListaObjetivo, Plano, AcaoRuim, NovoEstado, NovoPlano):-
	member(Objetivo, ListaObjetivo),
	write("tentando objetivo "),write(Objetivo),nl,
	not(member(Objetivo, Estado)),
	write("valido"),nl,
	ganho(Acao, Objetivo),
	write("resolver com "),write(Acao),nl,
	not(member(Acao, AcaoRuim)),
	write("valida"),nl,
	precond_list(Acao, PreList),
	write("no estado "),write(Estado),nl,
	write("novo Objetivo "),write(PreList),nl,nl,
	strips1(Estado, PreList, Plano, [Acao|AcaoRuim], EstadoTemporario, PlanoParcial),
	aplica_regra(Acao, EstadoTemporario, EstadoParcial),write("fim"),nl,
	strips1(EstadoParcial, ListaObjetivo, [Acao|PlanoParcial], AcaoRuim, NovoEstado, NovoPlano).
	
	
aplica_regra(Acao, Estado, NovoEstado) :-
	perda_list(Acao, PerList),
	subtract(Estado, PerList, EstadoParcial),
	ganho_list(Acao, GanList),
	union(GanList, EstadoParcial, NovoEstado).
	
precond_list(Acao, List) :- regra_strips(Acao, List, _, _).
precond(Acao, Cond) :- precond_list(Acao, List), member(Cond, List).
ganho_list(Acao, List) :- regra_strips(Acao, _, List, _).
ganho(Acao, Ganho) :- ganho_list(Acao, List), member(Ganho, List).
perda_list(Acao, List) :- regra_strips(Acao, _, _, List).
perda(Acao, Perda) :- perda_list(Acao, List), member(Perda, List).


/*regra_strips(pickup(X),
	[ontable(X), clear(X), handempty],
	[holding(X)],
	[ontable(X), clear(X), handempty]).
	
regra_strips(putdown(X),
	[holding(X)],
	[ontable(X), clear(X), handempty],
	[holding(X)]).
	
regra_strips(stack(X,Y),
	[clear(Y), holding(X)],
	[on(X,Y), clear(X), handempty],
	[clear(Y), holding(X)]).

regra_strips(unstack(X,Y),
	[on(X,Y), clear(X), handempty],
	[clear(Y), holding(X)],
	[on(X,Y), clear(X), handempty]).*/
	
regra_strips(Acao, PreCondicao, Ganho, Perda) :- regra(Acao, PreCondicao, Ganho, Perda).

teste :- plan([lixo, estar(quarto)], [lixo, alimentado, dinheiro]).

regra(
	dormir,
	[estar(quarto)],
	[energia],
	[]
).

regra(
	comer_lixo,
	[estar(rua), lixo],
	[alimentado],
	[lixo, fome]
).

regra(
	comprar_ingredientes,
	[dinheiro, estar(loja)],
	[ingredientes],
	[dinheiro]
).

regra(
	trabahar,
	[energia, estar(trabalho)],
	[dinheiro, fome],
	[energia]
).

regra(
	cozinhar,
	[ingredientes, estar(cozinha)],
	[comida_feita],
	[ingredientes]
).

regra(
	comer,
	[comida_feita],
	[alimentado],
	[fome, comida_feita]
).

regra(
	andar(X, Y),
	[estar(X)],
	[estar(Y)],
	[estar(X)]
):-adj(X, Y).

adjacente(porta, rua).
adjacente(trabalho, rua).
adjacente(loja, rua).
adjacente(quarto, sala).
adjacente(sala, cozinha).
adjacente(porta, sala).


adj(X, Y) :- adjacente(X, Y);adjacente(Y, X).

% Pretty-print Init, Goal, and Plan.
plan(InitState, Goal) :-
	strips(InitState,Goal,Plan),
	write('\n\nInit: '), write(InitState),nl,
	write('Goal: '),write(Goal),nl,
	write('Plan:\n'),
	writeplan(Plan),nl.

% Pretty-print the plan.
writeplan([]).
writeplan([A|B]):-
  write('       '),write(A),nl,
  writeplan(B).