_Documentation du rendu de projet, à remplir._
## Jalon 1 :
### Question 1 :
Pour représenter les equations, on crée une classe Equations qui possède en attribut, une liste de couple de termes. On utilise ici, plus précisémen, la classe ArrayList qui permet de gérer plus facilement des listes.

### Question 2 :
Pour implémenter l'OccurCheck, on utilise le design pattern visiteur en créant une classe qui gère le test d'occurence pour chaque type de terme. Cette classe possède un attribut de type TermVariable qui représente la variable qui ne doit pas être présente dans le terme à tester.

### Question 3 :
Pour l'environnement, nous avons décidé de créer une classe Environnement qui a pour attribut un Dictionnaire ayant pour clé, une variable, et pour valeur, un terme. La clé représente donc l'élément à remplacer et la valeur l'élément qui doit le remplacer.

### Question 4 :
L'implémentation de subst se fait par une classe qui suit le desing pattern visiteur. Cette classe possède un attribut qui est l'environnement contentnant les remplacements a effectuer.

### Qestion 5 :
On écrit d'abord une méthode pour chaque opération possible, décomposer(), effacer(), orienter() et remplacer(), qui appliquent les transformations vues en cours. Si jamais l'occurcheck n'est pas respecté, alors une exception est levée, sinon l'algorithme continue tant que le système n'est pas vide et que des changements on eu lieut lors des appels des méthodes définies précédament.

## Jalon 2 :
### Question 1 :
On part du principe que le premier element de la list de Decl dans Program est le fait (assertion) et que le deuxieme element est le but. On creer une Equations avec ces deux predicats que l'on transforme en TermPredicate et on fait l'unification sur un environement vide.

### Question 2 :
Comme pour la question 1 mais avec une étape en plus qui est de trouver le fait avec le meme symbole de prédicat que le but.

### Question 3 :
Pour chaque but trouvé, trouver le fait correspondant avec le meme symbole de predicat, puis ajouter dans les Equation ces deux Termes.
Faire l'unification de toutes ces equations trouvé entre les faits et les buts.