
max(X,[X]).
max(X,[Y|R]):- max(X,R), X > Y, !.
max(Y,[Y|_]).