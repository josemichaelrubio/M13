public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Helper method to get the parent index
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // The Insert Method
    public void insert(int element) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full!");
        }

        // Step 1: Insert the new element at the very end of the array (bottom-most, right-most spot in the tree)
        heap[size] = element;
        int currentIndex = size;
        size++;

        // Step 2: Restore the Max Heap property by bubbling up
        heapifyUp(currentIndex);
    }

    // Helper method to bubble up the element to its correct position
    private void heapifyUp(int index) {
        // Continue while we are not at the root AND the current element is strictly greater than its parent
        while (index > 0 && heap[index] > heap[parent(index)]) {
            
            // Swap the current element with its parent
            int parentIndex = parent(index);
            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;

            // Update the current index to the parent's index to continue checking upward
            index = parentIndex;
        }
    }
}