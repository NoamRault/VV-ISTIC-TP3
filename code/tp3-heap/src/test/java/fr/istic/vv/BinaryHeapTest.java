package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    // Comparator pour tester avec des entiers (min-heap)
    private final Comparator<Integer> comparator = Integer::compareTo;

    // Test de l'ajout d'un élément
    @Test
    void testPush() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(10);
        assertEquals(1, heap.count(), "La taille du tas devrait être 1 après l'ajout d'un élément.");
        assertEquals(10, heap.peek(), "Le premier élément ajouté devrait être 10.");
    }

    @Test
    void testPushAndPop() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(10);
        heap.push(5);
        heap.push(15);

        assertEquals(3, heap.count(), "La taille du tas devrait être 3 après 3 ajouts.");
        assertEquals(5, heap.pop(), "L'élément minimum (5) devrait être retiré.");
        assertEquals(2, heap.count(), "La taille du tas devrait être 2 après un pop.");
        assertEquals(10, heap.pop(), "L'élément minimum (10) devrait être retiré.");
        assertEquals(1, heap.count(), "La taille du tas devrait être 1 après un deuxième pop.");
        assertEquals(15, heap.pop(), "L'élément restant (15) devrait être retiré.");
        assertEquals(0, heap.count(), "La taille du tas devrait être 0 après tous les pops.");
    }

    @Test
    void testPeek() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(20);
        heap.push(5);
        heap.push(10);

        assertEquals(5, heap.peek(), "Le peek() devrait retourner le minimum sans le retirer.");
        assertEquals(3, heap.count(), "La taille du tas ne devrait pas changer après un peek.");
    }

    @Test
    void testPopOnEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        assertThrows(NoSuchElementException.class, heap::pop, "Un pop() sur un tas vide devrait lancer une exception NoSuchElementException.");
    }

    @Test
    void testPeekOnEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        assertThrows(NoSuchElementException.class, heap::peek, "Un peek() sur un tas vide devrait lancer une exception NoSuchElementException.");
    }

    @Test
    void testPushWithNegativeValues() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(-10);
        heap.push(-5);
        heap.push(-15);

        assertEquals(3, heap.count(), "La taille du tas devrait être 3.");
        assertEquals(-15, heap.pop(), "L'élément minimum (-15) devrait être retiré.");
        assertEquals(-10, heap.pop(), "L'élément suivant (-10) devrait être retiré.");
        assertEquals(-5, heap.pop(), "L'élément restant (-5) devrait être retiré.");
    }

    @Test
    void testPopUntilEmpty() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(30);
        heap.push(10);
        heap.push(20);

        // Popping all elements
        assertEquals(10, heap.pop());
        assertEquals(20, heap.pop());
        assertEquals(30, heap.pop());

        // The heap should be empty now
        assertEquals(0, heap.count(), "La taille du tas devrait être 0 après avoir retiré tous les éléments.");
    }

    @Test
    void testPushTwiceSame() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        heap.push(10);
        heap.push(10);

        // Popping all elements
        assertEquals(10, heap.peek());
        assertEquals(10, heap.pop());

        // The heap should be empty now
        assertEquals(1, heap.count(), "La taille du tas devrait être 1 après avoir retiré un seul des éléments.");
    }

}