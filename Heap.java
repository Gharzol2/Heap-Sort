import java.util.*;



// Java heap and heap-sort implementation. 
// Construct a heap and run it with heapSort method to get a sorted array, e.g: 
// Heap myHeap = new Heap{5,3,7,4,5)
// myHeap.heapSort();   <--- returns sorted array. Does not modify myHeap's data. 

class Heap {
	private int[] data;
	private int heapSize;

	Heap(int[] A) {
		this.data = A;
		this.heapSize = data.length;
		System.out.println("Heap initialized with data: " + this.data + " length: " + this.heapSize);
	}
	
	Heap(Heap other) {
		this.data = other.data;
		this.heapSize = data.length;
		System.out.println("(Copy constr) Heap initialized with data: " + this.data + " length: " + this.heapSize);
	}

	public int getHeapSize() {
		return heapSize;
	}

	// Gets what is formally defined as height, that is the number of EDGES in
	// the longest simple path from top to bottom.
	public int getHeight() {
		return (int) Math.floor((Math.log(this.heapSize) / Math.log(2)));
	}

	// This could be improved
	// Prints heap data in format somewhat resembling a balanced tree structure.
	public void dynamicPrint() {
		int index = 0;
		for (int i = 1; i <= this.getHeight() + 1; i++) {
			for (int j = i; j <= (this.getHeight() - (i - 1))*2; j++) {
				System.out.print(" ");
			}
			for (int o = 0; o < Math.pow(2, (i - 1)); o++) {
				if (index >= this.heapSize) {
					System.out.println("");
					return;
				}
				System.out.print(this.data[index++]);	
				for (int y = 0; y <= this.getHeight() - (i - 1) + 1; y++) {
					System.out.print(" ");
				}
			}		
			System.out.println("");
		}
	}	

	// Prints all array data (not just data in heap)	
	public void printArray() {
		System.out.println("Printing array. Datalength is: " + data.length + " Heapsize is: "
			+ this.heapSize);
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println("");
	}

	// Purposely not checking if out of bounds in these three functions
	// Need to check this in other functions
	private int parent(int i) {
		return ((++i)/2) - 1; 
	}
	
	private int left(int i) {
		return 2*(++i) - 1;
	}

	private int right(int i) {
		return 2*(++i);
	}

	private void maxHeap(int i) {
		int l = this.left(i);
		int r = this.right(i);
		int largest;
		if (l < this.heapSize && data[i] < data[l]) {
			largest = l;
		}
		else {
			largest = i;
		}
		if (r < this.heapSize && data[r] > data[largest]) {
			largest = r;
		}
		if (largest != i) {
			int temp = data[i];
			data[i] = data[largest];
			data[largest] = temp;
			maxHeap(largest);
		}
	}	

	public void buildMaxHeap() {
		for (int i = this.heapSize/2 - 1; i >= 0; i--) {
			maxHeap(i);
		}
	}

	public int[] heapSort() {
		Heap tmpHeap = new Heap(this);
		tmpHeap.buildMaxHeap();
		for (int i = tmpHeap.heapSize - 1; i >= 1; i--) {
			int temp = tmpHeap.data[0];
			tmpHeap.data[0] = tmpHeap.data[i];
			tmpHeap.data[i] = temp;
			tmpHeap.heapSize--;
			tmpHeap.maxHeap(0);
		}
		tmpHeap.printArray();
		return tmpHeap.data;
	}		
}	
	
