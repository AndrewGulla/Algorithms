/* @author Andrew Gulla (100395486)
* PART 1
*/
import java.util.*;
import java.io.*;
import java.lang.*;

public class RadixSort {

	/*Takes the maximum value in the array
	* and returns that value back to 
	* the radix sort function*/
	public static int getMax(int intArr[], int len){
		int max = intArr[0];
		for (int i=1; i<len; i++){
			if (intArr[i]>max)
				max = intArr[i];
		}
		return max;
	}

	/*Uses the given array and sorts
	* using radix sort*/
	public static void radSort(int intArr[], int len){
		int max = getMax(intArr, len);

		for (int i=1; (max/i)>0; i=i*10)//Performans countSort on each column
			countSort(intArr, len, i);
	}

	/*Uses the given array and sorts
	* using counting sort*/
	public static void countSort(int intArr[], int len, int d){
		int i, holder;
		int[] oArr = new int[len];//The output array
		
		int[] bucket = new int[len];
		Arrays.fill(bucket, 0);

		//Store count of occurences in bucket
		for (i=0; i<len; i++){
			holder = (intArr[i]/d) % 10;
			bucket[holder]++;
		}

		for (i=1; i<10; i++)
			bucket[i] += bucket[i-1];

		//Builds the sorted array
		for (i=len-1; i>=0; i--){
			holder = (intArr[i]/d) % 10;
			oArr[bucket[holder]-1] = intArr[i];
			bucket[holder]--;
		}

		//Copies the built array to the main array
		for (i=0; i<len; i++)
			intArr[i] = oArr[i];
	}
	/*Outputs the final sorted array*/
	public static void print(int intArr[], int len){
		System.out.println("After sorting array using RADIX SORT...\nThe following is the sorted array:");
		for (int i=0; i<len; i++){
			System.out.println(intArr[i]);
		}
	}

	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		char loop = 'y';
		while (loop == 'y'){//While loop allows program to be re-used



		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int size = 0;

		boolean bError = true;

		System.out.println("------------RADIX SORT------------");
		System.out.print("Please enter the size of the array: ");
		
		while(bError){//While loop gets proper array size from user
			if(scan.hasNextInt()){
				size = scan.nextInt();
				if(size <= 9){
					System.out.print("Please enter new array size greater than 9: ");
					continue;
				}
			}
			else{
				scan.next();
				continue;
			}
			bError = false;
		}

		int[] randomArr = new int[size];

		for(int i=0; i<size; i++){//Fills array of given size with positive values
			int randInt = rand.nextInt(Integer.MAX_VALUE) + 1;
			randomArr[i] = randInt % (1000000);
		}

		radSort(randomArr, size);
		print(randomArr, size);

		System.out.print("Do you wish to run the program again?(y or n): ");
		loop = reader.next().trim().charAt(0);
		}
		reader.close();
	}
}