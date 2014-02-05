/* @author Andrew Gulla (100395486)
* PART 3
*/
import java.util.*;
import java.io.*;
import java.lang.*;

public class HuffmanCoding{
	//data to replace text
   public static class strNode{
      String original;
      String replace;
   }
   //struct that holds tree data
   public static class node{
      node l;
      node r;
      String val;
      int frequency;
   }
   
   public static void printCodes(node rootN, StringBuffer prefix) {
   //Makes the prefix codes for the characters
      node n = (node)rootN;
      
      if(n.l == null && n.r == null){
         System.out.println(n.val + "\t" + n.frequency + "\t" + prefix);
         strNode a = new strNode();
         a.original = n.val;
         a.replace = prefix.toString();
         replaceArr.add(a);
      }
      else{
         prefix.append('0');
         printCodes(n.l, prefix);
         prefix.deleteCharAt(prefix.length()-1);

         prefix.append('1');
         printCodes(n.r, prefix);
         prefix.deleteCharAt(prefix.length()-1);
      }
   }  

	public static node Huffman(ArrayList<node> list){//Creates tree and returns root
      int n = list.size();
      node x = eMin(list);
      node y = eMin(list);
      node no = new node();
      no.l = x;
      no.r = y;
      no.val = x.val + y.val;
      no.frequency = x.frequency+y.frequency;
      list.add(no);
      
      if(list.size()> 1)
         no = Huffman(list);//recursion
      
      return no;   
  }

  //Arrays for replacements and frequencies
   static ArrayList<strNode> replaceArr = new ArrayList<strNode>();
   static ArrayList<Integer> freqArr = new ArrayList<Integer>();
   

   public static void main(String[] args){
      ArrayList<String> valArr = new ArrayList<String>();
      ArrayList<node> nodeList = new ArrayList<node>();
      
      try{
         String input;

         System.out.println("----------HUFFMAN CODING----------");
         System.out.println("Please enter the file name to be read...");
         
         //Reads in input
         Scanner scan = new Scanner(System.in);
         input = scan.next();
         File file = new File(input);
         InputStream inStream = new FileInputStream(file);
         Reader reader = new InputStreamReader(inStream);
         String[] cHolder = new String[128];
         //Fills array with characters
         for(int i=0; i<128;i++)
            cHolder[i] = Character.toString((char)i);

         int[] frequencyArr = new int[128];
         Arrays.fill(frequencyArr,0);
         int holder;

         while((holder = reader.read()) != -1){//updates frequency of characters
            int index = (int)holder;
            frequencyArr[index]++;
         }
         
         for(int i = 0;i<128;i++){//Adds value to proper array lists
            if(frequencyArr[i] >0){
               valArr.add(cHolder[i]);
               freqArr.add(frequencyArr[i]);
            }
         }
         
         int n = freqArr.size();
         
         for(int i = 0; i<n;i++){
            node h = new node();
            h.val = valArr.get(i);
            h.frequency = freqArr.get(i);
            nodeList.add(h);
         }
         
         reader.close();
         scan.close();
         
         ArrayList<node> tList = (ArrayList<node>)nodeList.clone();
         node t = Huffman(tList);
         printCodes(t, new StringBuffer());
         //new input stream
         inStream = new FileInputStream(file);
         reader = new InputStreamReader(inStream);
         File f = new File("HuffmanOutput.txt");//Begin writing to file
         FileWriter fWrite = new FileWriter(f);
         BufferedWriter bWrite = new BufferedWriter(fWrite);
         
         int holderr;
         int finishedSize = 0;
         int originalSize = 0;
         
         while((holderr = reader.read())!= -1){
            for(int i=0; i<replaceArr.size(); i++){
               if((char) holderr==((replaceArr.get(i)).original).charAt(0)){
                  bWrite.write((replaceArr.get(i)).replace);
                  finishedSize = finishedSize + ((replaceArr.get(i)).replace).length();
                  originalSize++;
               }
            }
         }
         bWrite.close();
         reader.close();
         System.out.println("Length of input file is: "+originalSize);
         System.out.println("After implementing Huffman, the length is now: "+finishedSize);
      }

      catch(IOException e){
         System.out.println("ERROR: IOEXCEPTION:  "+ e );
      }
   }

   //extracts minimum element and returns as node
   public static node eMin(ArrayList<node> list){
      node a = new node();
      int n = gMin(list);
      a.frequency = list.get(n).frequency;
      a.val = list.get(n).val;
      a.l = list.get(n).l;
      a.r = list.get(n).r;
      list.remove(n);
      return a;
   }

   //gets the minimum element. returns index.
   public static int gMin(ArrayList<node> list){
      int min = (list.get(0)).frequency;
      int index = 0;
      for(int i=1; i<list.size(); i++){
         if((list.get(i)).frequency < min){
            min = (list.get(i)).frequency;
            index=i;
         }
      }
      return index;
   }
}