/* @author Andrew Gulla (100395486)
* PART 2
*/
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class GreedyKnapsack{
	
	public static void fillItemArr(double itemArray[][], int itemNumber[], boolean bError, Scanner scan){
		for(int i=0; i<4; i++){
			bError = true;
			System.out.print("Insert cost of item " + itemNumber[i] + " ($): ");
			while(bError){
				if(scan.hasNextDouble()){
					itemArray[0][i] = scan.nextDouble();
				}
				else{
					System.out.print("Value must be a data type 'double': ");
					scan.next();
					continue;
				}
				bError = false;
			}
		}

		for(int i=0; i<4; i++){
			bError = true;
			System.out.print("Insert weight of item " + itemNumber[i] + " (lbs): ");
			while(bError){
				if(scan.hasNextDouble()){
					itemArray[1][i] = scan.nextDouble();
				}
				else{
					System.out.print("Weight must be a data type 'double': ");
					scan.next();
					continue;
				}
				bError = false;
			}
		}
	}

	public static void printTable(double itemArray[][], int itemNumber[], double unitValue[], DecimalFormat df){
		System.out.println("This is the current Item Set: ");
		System.out.println("Item " + itemNumber[0] + ": $"+itemArray[0][0]+" | "+itemArray[1][0]+" lbs");
		System.out.println("Item " + itemNumber[1] + ": $"+itemArray[0][1]+" | "+itemArray[1][1]+" lbs");
		System.out.println("Item " + itemNumber[2] + ": $"+itemArray[0][2]+" | "+itemArray[1][2]+" lbs");
		System.out.println("Item " + itemNumber[3] + ": $"+itemArray[0][3]+" | "+itemArray[1][3]+" lbs\n");

		for(int i=0; i<4; i++)
			System.out.println("Item "+itemNumber[i]+" unit value: "+df.format(unitValue[i])+" $/lb");
	}

	public static void calcUnitValue(double unitValue[], double itemArray[][]){
		System.out.println();
		for(int i=0; i<4; i++)
		unitValue[i] = ((itemArray[0][i]) / itemArray[1][i]);
	}

	public static void bubbleSort(double unitValue[], double itemArray[][], int itemNumber[], double bagMaxWeight, DecimalFormat df){
		for(int i=0; i<4; i++){
			for(int j=i+1; j<4; j++){
				if(unitValue[i]<unitValue[j]){
					//Sorts unit values
					double temp1 = unitValue[i];
					unitValue[i] = unitValue[j];
					unitValue[j] = temp1;
					//Sorts item array also (cost, weight)
					int temp2 = itemNumber[i];
					itemNumber[i] = itemNumber[j];
					itemNumber[j] = temp2;
					//Sorts the item total costs
					double temp3 = itemArray[0][i];
					itemArray[0][i] = itemArray [0][j];
					itemArray[0][j] = temp3;
					//Sorts the item total weights
					double temp4 = itemArray[1][i];
					itemArray[1][i] = itemArray [1][j];
					itemArray[1][j] = temp4;
				}
			}
		}

		System.out.println("\n--Item Priority List (Descending Order of Unit Value)--");
		for(int i=0; i<4; i++)
			System.out.println("Item "+itemNumber[i]+" unit value: "+df.format(unitValue[i])+" $/lb");
		System.out.println();
	}
	/*Using the sorted item list and corresponding unit values sorted
	* by the bubble sort algorithm, the fillKnapsack function
	* uses this priority list to put unit by unit into the 
	* knapsack until it is full and uses highest value
	* items until zero weight is left
	*/
	public static void fillKnapsack(double totalValue, double bagMaxWeight, double itemArray[][], double unitValue[], int itemNumber[], DecimalFormat df){
		double[] originalWeight = {itemArray[1][0], itemArray[1][1], itemArray[1][2], itemArray[1][3]};

		int limit = 0;
		for(int i=0; i<4; i++){
			while((itemArray[1][i]) != 0){
				if(limit<bagMaxWeight){
					totalValue += unitValue[i];
					itemArray[1][i]--;
					limit++;
				}
				else{
					break;//Avoid infinite loop if there is no room left in bag
				}
			}
		}

		//Prints the percentage of items taken by knapsack and the optimal total value it can hold
		for(int y=0; y<4; y++)
			System.out.println("Knapsack took %"+df.format(100*((1-itemArray[1][y]/originalWeight[y])))+" of item " + itemNumber[y]);
		System.out.println("\nTotal value knapsack can hold: $" + df.format(totalValue));
	}

	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		char loop = 'y';
		while (loop == 'y'){

			int[] itemNumber = {1,2,3,4};
			double[][] itemArray = new double[2][4];
			double[] unitValue = new double[4];
		
			double bagMaxWeight = 0;//Maximum weight bag can hold defined by user
			double totalValue = 0;//The value of all things put inside knapsack

			boolean bError = true;
			DecimalFormat df = new DecimalFormat("0.00");
			Scanner scan = new Scanner(System.in);

			System.out.println("---------GREEDY KNAPSACK (FRACTIONAL)---------");
		
			fillItemArr(itemArray, itemNumber, bError, scan);//Fills items with weights and costs
			calcUnitValue(unitValue, itemArray);//Calculates the value of each item
			printTable(itemArray, itemNumber, unitValue, df);//Prints item list and value list
		
			System.out.print("\nPlease enter the max weight knapsack can hold (lbs): ");

			bError = true;
			while(bError){//While loop gets proper array size from user
				if(scan.hasNextDouble()){
					bagMaxWeight = scan.nextDouble();
				}
				else{
					System.out.print("Weight must be a data type 'double': ");
					scan.next();
					continue;
				}
				bError = false;
			}
		
			bubbleSort(unitValue, itemArray, itemNumber, bagMaxWeight, df);//Sorts values descending order
			fillKnapsack(totalValue, bagMaxWeight, itemArray, unitValue, itemNumber, df);//Fills knapsack 

			System.out.print("Do you wish to run the program again?(y or n): ");
			loop = reader.next().trim().charAt(0);
		}
		reader.close();
	}
}