#!/usr/bin/python3

# PCOMP (LU3IN032), Licence 3, Sorbonne Université
# année 2021-2022
#
# Projet Prolog
#

# Fichier d'exemple : chargement de programmes Prolog depuis
# une chaîne ou un fichier, conversion en AST (instance de
# prolog_ast.ast.Program) puis conversion en chaîne et affichage
# à l'écran.

from prolog_parser import parser

# chargement depuis une chaîne
print(parser.parseString('?- p(a,f(a,X)).'), '\n')

# chargmement depuis un fichier
print(parser.parseFile('../exemples/classification.pl'), '\n')
print(parser.parseFile('../exemples/genealogie.pl'), '\n')
print(parser.parseFile('../exemples/test_list.pl'), '\n')
