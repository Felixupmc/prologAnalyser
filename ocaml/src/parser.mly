%{
  (*
   * PCOMP (LU3IN032), Licence 3, Sorbonne Université
   * année 2021-2022
   *
   * Projet Prolog
   *)

   open Ast
%}


/* Termes */
%token <string> VAR
%token <string> ATOM

/* Symboles */

%token RULE            /* :- */
%token COMMA           /* , */
%token PERIOD          /* . */
%token LPAREN          /* ( */
%token RPAREN          /* ) */
%token GOAL            /* ?- */

%token EOF

/* Start  symbols */
%start<Ast.prog> program

%%
program:
  p = list(extent(clause)); EOF                               { p }
  
clause:
  | p = extent(predicate); PERIOD                             { Assertion (p, []) }
  | p = extent(predicate); RULE; pl = predicate_list; PERIOD  { Assertion (p, pl) }
  | GOAL; pl = predicate_list; PERIOD                 { Goal pl }

predicate_list:
  | p = extent(predicate)                             { [p] }
  | p = extent(predicate); COMMA; pl = predicate_list { p :: pl }

predicate:
  | a = ATOM                                          { (a, []) }
  | a = ATOM; LPAREN; tl = term_list; RPAREN          { (a, tl) }

term_list:
  | t = term                                          { [t] }
  | t = term; COMMA; tl = term_list                   { t :: tl }

term:
  | v = extent(VAR)                                   { Variable v }
  | p = extent(predicate)                             { Predicate p }


// Ajout de la position de fichier
%inline extent(X): 
| x=X { x, { pos_begin = $startpos; pos_end = $endpos } }

%%
