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

    // Helper method to print the current state of the heap
    public void printHeap() {
        // We only print up to 'size' so we don't see the empty capacity slots
        int[] currentHeap = Arrays.copyOfRange(heap, 0, size);
        System.out.println(Arrays.toString(currentHeap));
    }

    // --- MAIN METHOD ---
    public static void main(String[] args) {
        // Create a MaxHeap with a capacity of 10
        MaxHeap myHeap = new MaxHeap(10);

        System.out.println("Building the initial heap...");
        myHeap.insert(50);
        myHeap.insert(30);
        myHeap.insert(20);
        myHeap.insert(15);
        myHeap.insert(10);
        
        System.out.print("Current Heap array: ");
        myHeap.printHeap(); 
        // Expected output: [50, 30, 20, 15, 10]

        System.out.println("\nInserting 60 into the heap...");
        myHeap.insert(60);
        
        System.out.print("New Heap array:     ");
        myHeap.printHeap(); 
        // Expected output: [60, 30, 50, 15, 10, 20]
    }
}