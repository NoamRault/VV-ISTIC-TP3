package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T min = heap.get(0);

        // Replace root with the last element
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);  // Maintain the heap property after adding the element
    }

    public int count() {
        return heap.size();
    }

    // Helper method to maintain heap property by "bubbling up" the element
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Helper method to maintain heap property by "bubbling down" the element
    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallest = index;

            if (leftChildIndex < size && comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
                smallest = leftChildIndex;
            }

            if (rightChildIndex < size && comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
                smallest = rightChildIndex;
            }

            if (smallest == index) {
                break;  // The heap property is satisfied
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    // Swap elements at two indices
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
