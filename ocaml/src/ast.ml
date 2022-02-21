(*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 *)


(* Position dans le fichier source *)
type pos = Lexing.position

(* Paire de positions début/fin dans le source *)
type extent = { pos_begin: pos; pos_end: pos; }
type 'a with_extent = 'a * extent

                    
(* Termes *)

type var = string with_extent (* variable *)

type term =
  | Variable of var
  | Predicate of pred

and pred = (string * term list) with_extent (* predicat *)


(* Declarations *)

type decl =
  | Assertion of pred * (pred list)  (* assertion  Head :- Body. *)
  | Goal of pred list                (* but        ?- Body.      *)


(* Un programme est une liste de déclarations, avec information de position *)
type prog = decl with_extent list


(* Égalité de termes (en ignorant la position) *)

let rec eq_term t1 t2 = match (t1, t2) with
  | Variable(v1,_), Variable(v2,_) -> v1=v2
  | Predicate((p1,l1),_), Predicate((p2,l2),_) when p1=p2 ->  eq_term_iter l1 l2
  | _ -> false
and eq_term_iter l1 l2 = match (l1, l2) with
  | ([],[]) -> true
  | (t1::tl1, t2::tl2) when (eq_term t1 t2) -> eq_term_iter tl1 tl2
  | _ -> false


(* Conversions en chaîne pour l'affichage *)

let string_of_position p =
  Printf.sprintf "%s:%i:%i"
    p.Lexing.pos_fname p.Lexing.pos_lnum
    (p.Lexing.pos_cnum - p.Lexing.pos_bol)
  
let rec string_of_term = function
  | Variable v -> string_of_var v
  | Predicate f -> string_of_pred f

and string_of_var (s, _) = s

and string_of_pred ((f, ls), _) =
  if ls = [] then f
  else f ^ "(" ^ (String.concat ", " (List.map string_of_term ls)) ^ ")"
  
let string_of_decl (d,_) = match d with
  | Assertion (t, []) -> (string_of_pred t) ^ "."
  | Assertion (t, tl) -> (string_of_pred t) ^ " :- " ^ (String.concat ", " (List.map string_of_pred tl)) ^ "."
  | Goal tl ->  " ?- " ^ (String.concat ", " (List.map string_of_pred tl)) ^ "."

let string_of_decl_list l =
  String.concat "\n" (List.map string_of_decl l)
