# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer
Pour chaque méthode de la classe Date, nous avons identifiées les différentes caractéristiques et les blocs de test associés :

- a. isValidDate(int day, int month, int year)

  - Caractéristiques :
    - Jour : le jour doit être un entier positif et ne pas dépasser le nombre de jours dans le mois respectif.
    - Mois : le mois doit être un entier entre 1 et 12 inclus.
    - Année : l'année doit être un entier valide (les années négatives peuvent être autorisées si l'on décide de prendre en charge les dates avant l'ère chrétienne).
  - Blocs de test :
    - Mois valide (1 à 12) avec un jour valide.
    - Mois valide avec un jour invalide (par exemple, 31 février).
    - Mois invalide (0, 13, etc.) avec un jour valide.
    - Année valide, mois valide, jour invalide (par exemple, 29 février dans une année non bissextile).
    - Année non valide (par exemple, année 0 ou négative si non prise en charge).

- b. isLeapYear(int year)
  - Caractéristiques :
    - Année divisible par 4 et non divisible par 100 : c'est une année bissextile.
    - Année divisible par 400 : c'est également une année bissextile.
    - Année divisible par 100 mais non par 400 : ce n'est pas une année bissextile.
  - Blocs de test :
    - Année divisible par 4, mais pas par 100 (par exemple, 2024).
    - Année divisible par 100, mais pas par 400 (par exemple, 1900).
    - Année divisible par 400 (par exemple, 2000).
    - Année non divisible par 4 (par exemple, 2023).

- c. nextDate()
  - Caractéristiques :
    - Date normale : on passe au jour suivant dans le même mois.
    - Changement de mois : le jour suivant conduit à un mois différent.
    - Changement d'année : le jour suivant conduit à une nouvelle année.
      - Blocs de test :
    - Date simple, dans le même mois (par exemple, 1er janvier → 2 janvier).
    - Fin de mois, passage au mois suivant (par exemple, 31 janvier → 1er février).
    - Fin de l'année, passage à l'année suivante (par exemple, 31 décembre → 1er janvier).
      - Cas bissextile, passage du 29 février au 1er mars.
- d. previousDate()
  - Caractéristiques :
      - Date normale : on revient au jour précédent dans le même mois.
          - Changement de mois : on revient au mois précédent.
          - Changement d'année : on revient à l'année précédente.
    - Blocs de test :
      -   Date simple, dans le même mois (par exemple, 2 janvier → 1er janvier).
      -   Début de mois, passage au mois précédent (par exemple, 1er mars → 28 février).
      -   Début d'année, passage à l'année précédente (par exemple, 1er janvier → 31 décembre).
      -   Cas bissextile, passage du 1er mars au 29 février.

- e. compareTo(Date other)
  - Caractéristiques :
    - Comparaison de dates égales : les deux dates sont identiques.
    - Comparaison de dates où la première est antérieure : la première date est avant la deuxième.
    - Comparaison de dates où la première est postérieure : la première date est après la deuxième.
  - Blocs de test :
    -   Comparer deux dates identiques.
    -   Comparer une date avant une autre.
    -   Comparer une date après une autre.
    -   Comparer une date avec null (attendu : NullPointerException).
2. Évaluation de la couverture des instructions
   Après avoir défini les cas de test basés sur l'ISP, nous devons vérifier que ces tests couvrent toutes les instructions du code.

Voici ce que nous devons faire :

Tester toutes les branches et les chemins : il faut s'assurer que toutes les conditions et toutes les branches des instructions sont testées.
Tests de couverture de branche : Chaque condition dans des méthodes comme isValidDate, isLeapYear et compareTo doit être activée dans les tests.
En revisitant les méthodes et en intégrant des tests pour chaque scénario, nous pouvons augmenter la couverture. Par exemple :

Tester la validité des dates à la fois dans des mois avec 31 jours et des mois avec moins de jours (février, avril, juin, etc.).
Tester la méthode compareTo avec plusieurs permutations de dates (dates égales, dates différentes, dates dans le passé, dans le futur).
Ces tests garantiront que chaque branche du code est exercée.

3. Base Choice Coverage (BCC)
   La Base Choice Coverage (BCC) exige que chaque prédicat (expression booléenne) avec plus de deux opérateurs soit testé pour toutes ses possibilités de manière indépendante.

Par exemple, pour la méthode isValidDate :

```java
if (month < 1 || month > 12 || day < 1 || day > daysInMonth[month - 1]) {
return false;
}
```
Cela contient un prédicat avec des opérateurs logiques (||) et nous devons tester toutes les combinaisons des conditions suivantes :

month < 1 et month > 12 (test de mois invalide).
day < 1 (test du jour invalide).
day > daysInMonth[month - 1] (test des jours invalides pour chaque mois).
Les tests doivent inclure des cas où chaque condition individuelle est vraie et où elles sont toutes fausses pour tester tous les chemins possibles.

Ajouter des cas de test :

Tester un mois invalide avec un jour valide.
Tester un jour supérieur au nombre de jours dans un mois donné.
Tester un jour valide mais avec un mois invalide.
Cela garantit que tous les prédicats sont vérifiés de manière complète.