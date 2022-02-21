(*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2021-2022
 *
 * Projet Prolog
 *)

(* Appel au parser *)

open Ast

let parse lexbuf =
  try
    let ast = Parser.program Lexer.token lexbuf in
    ast
  with
  | Parser.Error ->
      Printf.eprintf "Parse error (invalid syntax) near %s\n" 
        (string_of_position lexbuf.lex_start_p);
      failwith "Parse error"
  | Failure x ->
     if x = "lexing: empty token" then (
       Printf.eprintf "Parse error (invalid token) near %s\n" 
                      (string_of_position lexbuf.lex_start_p);
       failwith "Parse error"
     )
     else raise (Failure x)
  
let parse_string (s : string) : prog =
  parse (Lexing.from_string s)

let parse_file (filename : string) : prog =
  let f = open_in filename in
  let lexbuf = Lexing.from_channel f in
  lexbuf.lex_curr_p <- { lexbuf.lex_curr_p with pos_fname = filename; };
  let r = parse lexbuf in
  close_in f;
  r
