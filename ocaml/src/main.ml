(*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 *)

(* Exemples d'utilisation du parser et de l'affichage *)

open Libparser
open Ast
open Parsing

let s = parse_string "?- p(a,f(a,X))."
let _ = Printf.printf "%s\n\n" (string_of_decl_list s)


let _ =
  List.iter
    (fun f -> Printf.printf "%s\n\n" (string_of_decl_list (parse_file f)))
    ["../exemples/classification.pl";
     "../exemples/genealogie.pl";
     "../exemples/test_list.pl";
    ]


