/* @author Andrew Gulla (100395486)*/

import java.util.*;

public class med {
   
   public static void main(String args[]) {
	  Scanner reader = new Scanner(System.in);
	  char loop = 'y';
	  
	  while (loop == 'y'){
	  String first, second;
	  Scanner scan = new Scanner(System.in);
      System.out.println("-----Minimal Edit Distance-----");
      System.out.print("Please input the first string: ");
      first = scan.nextLine();
      System.out.print("Please input the second string: ");
      second = scan.nextLine();
      int H = EditDistance(first, second);
      System.out.println("The minimal edit distance of these two strings is " + H);
      System.out.print("Do you wish to run the program again? (y or n): ");
      loop = reader.next().trim().charAt(0);
	  }
	  reader.close();
   }
   
   public static int EditDistance(String s1, String s2){
      /* m and n represent the length of two strings*/
      int m = s1.length();
      int n = s2.length();
      /*creates 2-d array of lengths of strings*/
      int[][] d = new int[m+1][n+1];
      
      /*Top row of array is filled*/
      for(int i=0;i <= m;i++){
         d[i][0] = i;
      }
      /*First column of array is filled*/
      for(int j=0;j <= n;j++){
         d[0][j] = j;
      }
      
      for(int k=1;k < n+1;k++){
         
         for(int i = 1;i < m+1;i++){
            /*checks if characters are the same*/
            if(s1.charAt(i-1) == s2.charAt(k-1)){
               d[i][k] = d[i-1][k-1]; 
            }
            else {
               int H = Math.min((d[i-1][k]+1),(d[i][k-1]+1));
               H = Math.min(H, (d[i-1][k-1]+1));
               d[i][k] = H;
            }
         }
      }
      
      return(d[m][n]);
   }       
}
