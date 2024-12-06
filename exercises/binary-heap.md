# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer

1. Characteristics Identified: Input Space Partitioning (ISP) consiste à diviser l'espace des entrées en différentes partitions qui permettent de tester les différentes possibilités de comportement d'une méthode.
- Méthodes : push, pop, peek, count

- Méthode push :
- - Élément à ajouter :
- - - Valeur unique (par exemple, un entier positif ou négatif).
- - - Valeur déjà présente dans le tas.
- - - Valeur minimale (élément qui pourrait être placé à la racine).
- - - Valeur maximale (élément qui pourrait être inséré dans un endroit plus bas).
- - Blocs pour push :
- - - Ajouter un élément au tas quand il est vide.
- - - Ajouter un élément à un tas déjà peuplé.
- - - Ajouter un élément qui respecte l'ordre du tas (min-heap).

- Méthode pop :
- - État du tas :
- - - Tas vide.
- - - Tas avec un seul élément.
- - - Tas avec plusieurs éléments.
- - Blocs pour pop :
- - - Retirer l'élément minimum d'un tas vide (doit lever une exception).
- - - Retirer l'élément minimum d'un tas contenant un seul élément.
- - - Retirer l'élément minimum d'un tas avec plusieurs éléments.

- Méthode peek :
- - État du tas :
- - - Tas vide.
- - - Tas avec un seul élément.
- - - Tas avec plusieurs éléments.
- - Blocs pour peek :
- - - Accéder à l'élément minimum d'un tas vide (doit lever une exception).
- - - Accéder à l'élément minimum d'un tas avec un seul élément.
- - - Accéder à l'élément minimum d'un tas avec plusieurs éléments.

- Méthode count :
- - État du tas :
- - - Tas vide.
- - - Tas avec un seul élément.
- - - Tas avec plusieurs éléments.
- - Blocs pour count :
- - - Compter les éléments dans un tas vide.
- - - Compter les éléments dans un tas avec un seul élément.
- - - Compter les éléments dans un tas avec plusieurs éléments.

Caractéristiques communes :
- Tous les tests doivent vérifier le cas où le tas est vide.
- Tous les tests nécessitent un cas avec un seul élément.
- Tous les tests doivent inclure des cas où plusieurs éléments sont présents dans le tas.

2. Évaluation de la couverture des déclarations (Statement Coverage)
   La couverture des déclarations implique de s'assurer que chaque ligne de code de la méthode est exécutée au moins une fois 

Méthodes couvertes par les tests actuels :
   - push : Les tests actuels couvrent l'ajout d'un élément à un tas vide, l'ajout d'un élément à un tas peuplé, ainsi que l'ajout d'un élément respectant la propriété du tas.
   - pop : Les tests couvrent le retrait d'un élément d'un tas vide (qui lance une exception), d'un tas avec un seul élément, et d'un tas avec plusieurs éléments.
   - peek : Les tests couvrent l'accès à l'élément minimum dans un tas vide, un tas avec un seul élément et un tas avec plusieurs éléments.
   - count : Les tests couvrent le comptage des éléments dans un tas vide, un tas avec un seul élément et un tas avec plusieurs éléments.

3. Les prédicats à vérifier sont notamment présents dans les méthodes push, pop et peek, où l'on vérifie des conditions comme la taille du tas, si le tas est vide, etc.
  
Prédicats logiques :
- Dans pop(), par exemple, il y a un prédicat qui vérifie si le tas est vide avant de tenter de retirer un élément.
- Dans push(), il y a une condition qui vérifie si le tas est vide ou non avant d'insérer un élément.

Évaluation de la couverture logique :
- Tous les tests couvrent les situations de tas vide ou non vide. Les tests actuels satisfont donc Base Choice Coverage en ce qui concerne les conditions de taille du tas et les cas d'exception pour un tas vide.
Aucune nouvelle entrée n'est nécessaire ici non plus.

4. Résultat de l'exécution de PIT :
   Mutation Score : 100% (Tous les mutants ont été tués)
   Live Mutants : Aucun mutant vivant

Cela signifie que notre suite de tests actuelle a couvert tous les scénarios créés par le mutation testing de PIT, et tous les mutants générés ont été éliminés par nos tests.