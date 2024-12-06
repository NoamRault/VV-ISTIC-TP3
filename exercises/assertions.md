# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1- Cette assertion échoue en raison des problèmes de précision liés aux nombres à virgule flottante en informatique. Le nombre 0.4 ne peut pas être représenté exactement en binaire dans la plupart des langages de programmation (comme Java). Par conséquent, le résultat de 3 * 0.4 n'est peut-être pas exactement égal à 1.2, mais pourrait être quelque chose comme 1.2000000000000002 à cause de la manière dont les nombres flottants sont stockés en mémoire.
Pour éviter ce genre de problème, il est préférable d'utiliser une petite tolérance.

assertEquals(1.2, 3 * 0.4, 0.0000001);

Cela garantit que la différence entre 3 * 0.4 et 1.2 est inférieure ou égale au delta (valeur de tolérance).


2-  assertEquals : Vérifie si les valeurs de deux objets sont égales. Cela utilise la méthode .equals() pour la comparaison.
    assertSame : Vérifie si deux références pointent vers le même objet en mémoire (c'est-à-dire qu'il s'agit du même objet exact).

Si deux objets sont à la fois la même instance et ont la même valeur (définie par .equals()), alors assertEquals et assertSame donneront tous les deux le même résultat.

Scénarios où elles produisent le même résultat :
Exemple :

```java
String str1 = "hello";
String str2 = "hello";
assertEquals(str1, str2);  // Passe, car les valeurs sont identiques
assertSame(str1, str2);    // Passe, car ce sont la même instance dans le pool de chaînes
```

Si deux objets ont la même valeur mais ne sont pas la même instance (c'est-à-dire qu'ils sont différents objets avec le même état), assertEquals passera, mais assertSame échouera.

Scénarios où elles ne produisent pas le même résultat :
Exemple :

```java
String str1 = new String("hello");
String str2 = new String("hello");

assertEquals(str1, str2);  // Passe, car les valeurs sont identiques
assertSame(str1, str2);    // Échoue, car ce sont des objets différents en mémoire
```
Dans ce cas, bien que str1 et str2 aient la même valeur, ce sont des objets différents, donc assertSame échoue.


1. Pourquoi l'assertion assertTrue(3 * .4 == 1.2) échoue-t-elle et comment vérifier ce type de comparaison ?
  
Cette assertion échoue en raison des problèmes de précision liés aux nombres à virgule flottante en informatique. Le nombre 0.4 ne peut pas être représenté exactement en binaire dans la plupart des langages de programmation (comme Java). Par conséquent, le résultat de 3 * 0.4 n'est peut-être pas exactement égal à 1.2, mais pourrait être quelque chose comme 1.2000000000000002 à cause de la manière dont les nombres flottants sont stockés en mémoire.

Pour éviter ce genre de problème, il est préférable d'utiliser une petite tolérance (un epsilon) lors de la comparaison des nombres à virgule flottante. Cela permet de vérifier si deux nombres sont « suffisamment proches » l'un de l'autre, plutôt que de tester s'ils sont exactement égaux. En JUnit, vous pouvez utiliser assertEquals avec un delta pour cela :

````java
assertEquals(1.2, 3 * 0.4, 0.0000001); // Utilisation d'un delta pour les comparaisons de nombres flottants
````
Cela garantit que la différence entre 3 * 0.4 et 1.2 est inférieure ou égale au delta (valeur de tolérance).

1. Quelle est la différence entre assertEquals et assertSame ? Montre des scénarios où elles produisent le même résultat et des scénarios où elles ne produisent pas le même résultat.
   assertEquals : Vérifie si les valeurs de deux objets sont égales. Cela utilise la méthode .equals() pour la comparaison.
   assertSame : Vérifie si deux références pointent vers le même objet en mémoire (c'est-à-dire qu'il s'agit du même objet exact).
   Scénarios où elles produisent le même résultat :
   Si deux objets sont à la fois la même instance et ont la même valeur (définie par .equals()), alors assertEquals et assertSame donneront tous les deux le même résultat.

Exemple :

```java
String str1 = "hello";
String str2 = "hello";

assertEquals(str1, str2);  // Passe, car les valeurs sont identiques
assertSame(str1, str2);    // Passe, car ce sont la même instance dans le pool de chaînes
```
Scénarios où elles ne produisent pas le même résultat :
Si deux objets ont la même valeur mais ne sont pas la même instance (c'est-à-dire qu'ils sont différents objets avec le même état), assertEquals passera, mais assertSame échouera.

Exemple :

````java
String str1 = new String("hello");
String str2 = new String("hello");

assertEquals(str1, str2);  // Passe, car les valeurs sont identiques
assertSame(str1, str2);    // Échoue, car ce sont des objets différents en mémoire
````
Dans ce cas, bien que str1 et str2 aient la même valeur, ce sont des objets différents, donc assertSame échoue.

1. Autres utilisations de fail en JUnit. Explique avec un exemple.
   La méthode fail() est souvent utilisée pour marquer un endroit dans le test où l'exécution ne devrait pas arriver, généralement parce qu'une exception était attendue. Cependant, elle peut aussi être utilisée dans d'autres cas pour signaler qu'une certaine partie du code ne devrait pas être exécutée.

Voici quelques autres usages de fail() :

Pour signaler un comportement inattendu : Si vous voulez indiquer qu'une certaine partie du code ne devrait pas être exécutée (par exemple, que le code atteint une condition ou un état inattendu), vous pouvez utiliser fail().
Exemple :

````java
@Test
public void testMethodWithConditions() {
if (someCondition()) {
// Comportement normal du test
return;
}

    fail("Ce point ne devrait pas être atteint si la condition est fausse !");
}
````
4. En JUnit 4, on utilisait l'annotation @Test(expected = Exception.class) pour indiquer qu'une exception était attendue. Cependant, cette approche a des limitations, notamment si vous souhaitez vérifier des détails de l'exception (comme son message, son type ou sa cause).
   En JUnit 5, la méthode assertThrows offre un meilleur contrôle et plus de flexibilité pour tester les exceptions, car elle vous permet de capturer et de tester l'exception lancée, ce qui était moins pratique avec @Test(expected = Exception.class).

Avantages de assertThrows :
Contrôle plus précis : Vous pouvez capturer et inspecter l'exception lancée, ce qui vous permet d'effectuer des assertions sur ses propriétés, comme son message d'erreur.
Lisibilité améliorée : Il n'est pas nécessaire de modifier la signature de la méthode de test pour gérer plusieurs exceptions.
Meilleure lisibilité et moins sujette aux erreurs : Vous entourez explicitement le code qui doit lancer l'exception, ce qui rend plus clair ce qui est attendu.

conclusion : assertThrows en JUnit 5 offre plus de flexibilité et de lisibilité pour tester les exceptions que l'annotation @Test en JUnit 4.




