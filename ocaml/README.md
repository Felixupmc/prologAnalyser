Ce répertoire contient la version OCaml de l'analyseur syntaxique (parseur)
Prolog pour bien démarrer le projet.


# Contenu

- `src/` contient les sources OCaml. Elles sont compilées en une bibliothèque `librparser` par Dune.
- `src/ast.ml` contient la structure d'AST (arbre syntaxique abstrait). Un programme Prolog, de type `prog`, est une liste de déclarations de type `decl`. Dans les déclarations, les buts sont introduits par le constructeur `Goal` et les assertions par le constructeur `Assertion`. Ils contiennent des termes, de type `term`. Dans les termes, les variables sont introduites par le constructeur `Variable` et les prédicats par le constructeur `Predicate`. Chacun des types vient avec une fonction `string_of_...` que vous utiliserez pour l'affichage. Les termes peuvent être comparés pour l'égalité grâce à la fonction `eq_term` fournie. Les nœuds de l'AST sont annoté d'information de position (ligne et colonne) dans le programme source Prolog.
- `src/lexer.mll` contient la description d'un lexeur, qui permet de transformer la chaîne de caractères contenue dans un fichier en une suite de tokens qui sera passée au parseur. Lors de la compilation, le lexeur `src/lexer.ml` est généré automatiquement par l'outil `ocamllex` à partir du fichier `src/lexer.mll`.
- `src/parser.mly` contient la grammaire d'un parseur. Lors de la compilation, l'outil `menhir` est utilisé pour générer automatiquement le parseur `src/parser.ml`.
- `src/parsing.ml` est un ensemble d'utilitaires pour lire des sources Prolog depuis un fichier ou une chaîne et les convertir en programmes de type `prog` grâce au parseur.
- `src/main.ml` est une programme d'exemple qui lit des sources Prolog, les convertit en programmes de type `prog`, et réaffiche le programme à l'écran.

# Compilation

Le projet dépend des outils Dune et Menhir, qu'il vous faut installer avec la commande : `opam install menhir dune`.

Il suffit ensuite de taper `dune build` dans un `shell` pour compiler l'analyseur syntaxique Prolog et le programme `main`.
Pour exécuter le programme `main` compilé, faites `dune exec src/main.exe` (ou `./_build/default/src/main.exe`).
