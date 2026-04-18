import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Helper methods
    private int parent(int index) { 
        return (index - 1) / 2; 
    }
    private int leftChild(int index) { 
        return (2 * index) + 1; 
    }
    private int rightChild(int index) {
        return (2 * index) + 2;
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

    // Delete Method
    public boolean delete(int element) {
        // Step 1: Find the index of the element
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (heap[i] == element) {
                index = i;
                break;
            }
        }

        // If the element isn't in the heap, return false
        if (index == -1) {
            return false;
        }

        // Step 2 & 3: Replace the target with the last element and shrink size
        heap[index] = heap[size - 1];
        size--;

        // Step 4: Restore the Max Heap property
        // Check if we need to bubble UP or bubble DOWN
        if (index > 0 && heap[index] > heap[parent(index)]) {
            heapifyUp(index);
        } else {
            heapifyDown(index);
        }

        return true;
    }

    // Helper method to bubble up
    private void heapifyUp(int index) {
        // Continue while we are not at the root AND the current element is strictly greater than its parent
        while (index > 0 && heap[index] > heap[parent(index)]) {
            
            // Swap the current element with its parent
            int parentIndex = parent(index);
            swap(index, parentIndex);
            // Update the current index to the parent's index to continue checking upward
            index = parentIndex;
        }
    }

    // Helper method to bubble down
    private void heapifyDown(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        // Check if the left child is larger than the current largest
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Check if the right child is larger than the current largest
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If the largest is not the current index, swap and continue bubbling down
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest); // Recursive call to continue down the tree
        }
    }

    // Utility method to swap two elements in the array
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void printHeap() {
        // We only print up to 'size' so we don't see the empty capacity slots
        int[] currentHeap = Arrays.copyOfRange(heap, 0, size);
        System.out.println(Arrays.toString(currentHeap));
    }

    // --- MAIN METHOD ---
    public static void main(String[] args) {
        // Create a MaxHeap with a capacity of 10
        MaxHeap myHeap = new MaxHeap(10);

        // Build the heap
        System.out.println("Building the initial heap...");
        myHeap.insert(50);
        myHeap.insert(30);
        myHeap.insert(60);
        myHeap.insert(15);
        myHeap.insert(10);
        myHeap.insert(20);
        
        System.out.print("Current Heap array: ");
        myHeap.printHeap(); 
        // Array: [60, 30, 50, 15, 10, 20]

        // Let's delete a middle element that will force a heapifyDown
        System.out.println("\nDeleting 50...");
        myHeap.delete(50);
        
        System.out.print("Heap after 50:   ");
        myHeap.printHeap(); 
        // The last element (20) replaces 50, then bubbles down if necessary.
        // Array becomes: [60, 30, 20, 15, 10]

        // Let's delete an element that forces a heapifyUp
        System.out.println("\nInserting 40...");
        myHeap.insert(40);
        System.out.print("Heap after 40:   ");
        myHeap.printHeap();
        // Array: [60, 30, 40, 15, 10, 20]

        System.out.println("\nDeleting 30...");
        myHeap.delete(30);
        
        System.out.print("Final Heap:      ");
        myHeap.printHeap(); 
        
    }
}