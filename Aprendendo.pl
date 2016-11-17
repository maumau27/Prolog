:- dynamic predicado/1.

predicado(a).

master(X) :- findall(resposta(X), t(X), B), write(B).

t(X) :- findall(valor(X), teste(X), B), write(B) .

teste(X) :- predicado(X), X \= c, assert(predicado(d)) ; predicado(X), X = c, t().	