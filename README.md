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

Jalon non fini. 
Je mettrai à jour les commentaires au fur et à mesure de l'évolution du jalon.

## exécution et tests
* L'exécution doit donner une sortie claire et bien organisée. Pour chaque question : donnez un intitulé, dire ce qui est testé, le résultat attendu, le résultat effectivement calculé.

## qualité du code
*  Utilisation partiellement correcte de visiteur. OccurCheck contient des tests instanceof, or instanceof est inutile dans un visiteur bien construit (la liaison dynamique se charge d'exécuter le code dépendant du type).
* Écrivez des commentaire : en-tête décrivant chaque fichier, documentation de chaque méthode (éventuellement en Javadoc), commentaires sur les parties du code les plus importantes ou les plus complexes.

## rapport de Jalon
* Rapport non écrit.
