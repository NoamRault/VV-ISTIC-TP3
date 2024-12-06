# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
1. Le partitionnement de l’espace d’entrée consiste à identifier les caractéristiques des entrées possibles et à les diviser en blocs de partitions distincts.
   
   Validité de l’entrée :
- Partition 1 : null (entrée invalide).
- Partition 2 : Chaîne vide (entrée triviale, équilibrée).
- Partition 3 : Chaîne contenant des espaces uniquement (équilibrée).
- Partition 4 : Chaîne contenant des symboles valides ({}, [], ()).
- Partition 5 : Chaîne contenant des caractères invalides (non-symboles).
  
    Structure des symboles :
- Partition 6 : Chaîne équilibrée (ex : {[]}, ({[]})).
- Partition 7 : Chaîne déséquilibrée (ex : {[}, ([]}).
- Partition 8 : Chaîne mal imbriquée (ex : ([)], {]}).

2. La couverture des instructions (statement coverage) mesure si chaque ligne de code dans votre programme a été exécutée au moins une fois par les tests.
Actuellement, nous avons une couverture de 100%, ce qui signifie que toutes les lignes de notre code sont couvertes par nos tests actuels.
Pour le développement des tests nous avons repris nos partitions de la question 1 et vérifié chacun de ces cas.


3. Dans notre code de la méthode `isBalanced`, il n'y a pas de conditions avec plus de deux opérateurs booléens, mais il existe des prédicats simples qui sont déjà couverts dans les tests. 
Ces tests garantissent que chaque prédicat est évalué à la fois à vrai et à faux. Nos tests actuels satisfont donc à Base Choice Coverage. Aucune modification ou ajout de tests n'est nécessaire pour garantir la couverture de base des choix.


4. Résultat de l'exécution de PIT :
   Mutation Score : 100% (Tous les mutants ont été tués)
   Live Mutants : Aucun mutant vivant

Cela signifie que notre suite de tests actuelle a couvert tous les scénarios créés par le mutation testing de PIT, et tous les mutants générés ont été éliminés par nos tests.