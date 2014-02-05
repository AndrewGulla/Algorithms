/* @author Andrew Gulla (100395486)*/

import java.lang.*;
import java.util.*;

public class heap{
/*Original array declared outside main method so it is
 * accessible by other methods without having to send
 * through functions*/
	static List<Integer> original;
	public static void main (String[] args){
		Scanner reader = new Scanner(System.in);
		/*Runs main method repeatedly until user
		 * requests otherwise*/
		char loop = 'y';
		while (loop == 'y'){
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		List<Integer> array = new ArrayList<Integer>();
		int input;
		int data;
		
		System.out.println("--------HEAP SORT--------");
		System.out.print("How many random integers would you like in your heap? : ");
		input = sc.nextInt();
		/*Adds random integer into array*/
		for(int i=0; i < input; i++){
			data = rand.nextInt(100);
			array.add(data);
		}
		/*Output*/
		hashsort(array);
		System.out.println("\nOriginal array:");
		printAsArray(original);
		System.out.println("\nSorted array:");
		printAsArray(array);
		System.out.println("\nArray as tree:");
		printAsTree(array, 1);
		System.out.print("Do you wish to run the program again? (y or n): ");
		loop = reader.next().trim().charAt(0);
		}
		reader.close();
	}
	
	public static void MaxHeapify (List<Integer> array, int i){
		int left = (i*2) - 1;
		int right = (i*2);
		int largest;
		
		/*condition checks if there is a right side and if it's bigger*/
		if(right<=array.size() && array.get(left)>array.get(i-1)){
			largest = left;
		}
		else{
			largest = i - 1;
		}
		
		/*condition checks if there is a left side and if it's bigger*/
		if(right+1<=array.size() && array.get(right)>array.get(largest)){
			largest = right;
		}
		
		/*swaps values*/
		if(largest != i-1){
			array.set(i-1, array.get(i-1) + array.get(largest));
			array.set(largest, array.get(i-1) - array.get(largest));
			array.set(i-1, array.get(i-1) - array.get(largest));
			
			MaxHeapify(array, largest);
		}
	}
	
	public static void BuildMaxHeap(List<Integer> array, double x){
		int heapLength = array.size();
		
		for(int i = (int)Math.floor((heapLength)/2); i>0; i--){
			MaxHeapify(array, i);
		}
		if(x!=0){
			x = x - 1;
			BuildMaxHeap(array, x);
		}
	}
	/*max number from heap at top*/
	public static int HeapMaximum(List<Integer> array){
		return array.get(0);
	}
	
	public static int HeapExtractMax(List<Integer> array){
		if(array.size() < 1){
			System.out.println("Nothing in heap...");
		}
		
		int maximum = array.get(0);
		array.set(0, array.get(array.size()-1));
		array.remove(array.size()-1);
		MaxHeapify(array, 1);
		return maximum;
	}
	//Heapifys the end of the array.
	public static void MaxHeapInsert(List<Integer> array, int element){
		array.add(element);
		int x = array.size();
		int pIndex = (int)Math.floor(x/2);
		
		while(array.get(x-1) > array.get(pIndex-1) && pIndex>=1){
			array.set(x-1, array.get(x-1) + array.get(pIndex-1));
			array.set(pIndex-1, array.get(x-1) - array.get(pIndex-1));
			array.set(x-1, array.get(x-1) - array.get(pIndex-1));
			x = pIndex;
			pIndex = (int)Math.floor(pIndex/2);
			
			if(pIndex == 0){
				break;
			}
		}
	}
	
	public static void hashsort(List<Integer> array){
		/*Declares global array original*/
		original = new ArrayList<Integer>(array);
		double x = array.size();
		double y = Math.floor(Math.log(x+1)/Math.log(2));
		BuildMaxHeap(array, y);
	}
	
	public static void printAsArray(List<Integer> array){
		System.out.println(array);
	}
	
	public static void printAsTree(List<Integer> array, int i){
		/*Checks if the tree isnt empty*/
		if(array.isEmpty() != true){
			/*Checks if there is a right node*/
			if(i*2+1 <= array.size()){
				printAsTree(array, i*2+1);
			}
			
			int s = i;
			int level = (int)Math.ceil(Math.log(s+1)/Math.log(2));
			
			/*Prints out required spaces*/
			for(int x = 0; x<(level*4); x++){
				System.out.print(" ");
			}
			System.out.println(array.get(i-1));
			
			if(i*2 <= array.size()){
				printAsTree(array, i*2);
			}
		}
	}
}

