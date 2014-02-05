GreedyKnapsack : GreedyKnapsack.class
HeapSort : heap.class
HuffmanCoding : HuffmanCoding.class
MinimalEditDistance : med.class
RadixSort : RadixSort.class
DivideAndConquer : DivideAndConquer.class

GreedyKnapsack.class :
	javac $ GreedyKnapsack.java
	java $ GreedyKnapsack
 
heap.class :
	javac $ heap.java
	java $ heap
	rm -f $ heap.class

HuffmanCoding.class :
	javac $ HuffmanCoding.java
	java $ HuffmanCoding
	rm -f $ HuffmanCoding.class

med.class :
	javac $ med.java
	java $ med
	rm -f $ med.class

RadixSort.class :
	javac $ RadixSort.java
	java $ RadixSort
	rm -f $ RadixSort.class

DivideAndConquer.class :
	g++ $ DivideAndConquer.cpp
	./a.out
	rm -f $ a.out

clean : 
	rm -f $ *.class