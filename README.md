# PCOMP - Projet : Interprète Prolog

Le projet est un interprète Prolog, à réaliser au choix en OCaml, Java ou Python.

L'énoncé est disponible sur Moodle.

Vous devez faire un _fork_ privé par binôme de ce squelette de projet, et y ajouter votre binôme ainsi que les enseignants du cours (Carlos Agon, Emmanuel Chailloux, Basile Pesin, Antoine Miné, Christine Tasson) comme _maintainer_.
Un seul membre du binôme fait le _fork_, mais les deux membres du binôme peuvent faire un _clone_ du _fork_ du projet afin de travailler ensemble.
Ajoutez les fichiers de votre projet au fur et à mesure (`git add`, `git commit`, `git push`).
N'oubliez pas de remplir le fichier `RENDU.md` pour chaque jalon.
Il est conseillé de faire des commit réguliers.

Les répertoires `java`, `ocaml`, `python` de ce squelette contiennent une structure d'AST et un analyseur syntaxique dans chacun des langages proposés.
Le répertoire `exemples`, que vous pouvez enrichir, contient quelques exemples de programmes Prolog.
Référez-vous au fichier `README.md` de chaque répertoire pour plus d'informations sur la structure des sources et sur la procédure de compilation et de test de l'analyseur syntaxique.


# Commentaires (Antoine Miné)


# Jalon 1

## exécution et tests
* Le code compile et s'exécute sans erreur.
* Attention : le résultat affiché n'est pas la substitution attendue!

## qualité du code
* Attention : vérifiez votre méthode unify, elle ne semble pas respecter ce qui est demandé. Elle doit parcourir récursivement les expressions à unifier. Elle doit signaler une exception en cas d'unification impossible (prédicats de symbole ou de nombre d'arguments différents).
* Un peu de commentaires dans le code, mais il en faut plus. Donnez plus de détails sur l'unification, qui est la partie la plus compliquée.

## rapport de Jalon
* Description détaillée des classes, des structures de données et des patterns.
* Pas de description des tests.
* Pas d'explication sur comment lancer le Jalon.
* Pas de discussion sur l'efficacité.


# Jalon 2

## exécution et tests

* Pas de test pour interprete1 ~~et interprete2.~~ Un test pour `interprete2` a été ajouté dans Main.

## qualité du code

* ~~Petite erreur dans interprete2 : chaque DeclGoal doit être résolu dans son environnement indépendant.~~ C'est maintenant corrigé.


## rapport de Jalon

Mêmes remarques que pour le rapport de Jalon 1 : il manque la description des tests et de l'exécution. 


# Jalon 3

## exécution et tests

* ~~Pas de test sur interprete3. Pas de fichier exécutable Jalon3.~~
Début de test d'`interpete3` dans Main.
Il est néanmoins demandé de fournir au moins 3 tests, dans des fichiers source Prolog chargés avec `PrologParser.parseFile`.

## qualité du code

* ~~choose doit prendre un seul prédicat but, pas un DeclGoal.~~
* ~~appel à rename manquant~~ `choose` a été corrigé

## rapport de Jalon

Rapport de Jalon non encore écrit.


# Jalon 4

* Compile, mais l'exécution du test ne donne pas le résultat attendu.
* Implantation du `Contexte` correcte.
* `Journal` devrait contenir une liste de `Contexte`, pas une liste de listes de déclarations.
* `Backtracking` incorrect. Il doit utiliser un seul journal dans tous les appels à `solve`. Il ne doit pas s'appeler récursivement, mais itérer sur le journal tant qu'il reste des choix non faits.
* Un seul test est fourni ; l'énoncé demande d'autres tests.
* Rapport de Jalon non encore écrit.
