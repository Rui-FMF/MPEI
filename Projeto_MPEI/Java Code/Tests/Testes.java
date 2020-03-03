package Projeto;
import java.util.*;
import java.io.*;

public class Testes {
	public static void main(String[] args) {
		ArrayList<MedicoesLocal> dailyArr = getPortlandDailyData();
		
		// Bloom Filter Test n1 //
		BloomFilterTest(dailyArr);
		
		// Bloom Filter Test n2 //
		BloomFilterTestn2();
		
		// MinHash Test //
		MinHashTest(dailyArr);
	}
	
	
	public static void BloomFilterTest(ArrayList<MedicoesLocal> dailyArr) {

        System.out.println("\n*******************************");
        System.out.println("     Bloom Filter Test");
        System.out.println("*******************************\n");
        
        System.out.println("This test uses temperature values in Portland throughtout the years (2013-2017)");
        
        // Getting data..
        ArrayList<Integer> data = new ArrayList<Integer>(); 
        
        for (int i = 0; i < dailyArr.size(); i++) {
        	for (int k = 0; k < dailyArr.get(i).getTemperaturas().length; k++) {
            	data.add(dailyArr.get(i).getTemperaturas()[k]);
        	}
        }
        
        // Placing data onto the bloom filter
        BloomFilter bFilterTest = new BloomFilter(data.size()*10, 100);
        
        for (int i = 0; i < data.size(); i++) {
        		bFilterTest.addElement(data.get(i));
        }
        
        // Checking if the bloom filter is empty after insertion of elements
        System.out.printf("Is the Bloom Filter empty? %s \n", dailyArr.isEmpty());
        
        // Testing if values are elements of the bloom filter and their occurrences
        System.out.println("\nTesting if values are elements of our bloom filter...\nOur data set is in Kelvin.\nAs such, temperatures like 50 K (-223 C) or 400 K (126 C) can't be found in any part of our data set. Logically, these values should fail to be elements of our bloom filter.\nSome temperatures between [270,285]ºK can be found - thus, some of them should pass as elements of the bloom filter.\n");

        int[] tempTests = {49,66,250,199,434,332,277,289,273,268};
        for (int i = 0; i < tempTests.length; i++) {
        	if( bFilterTest.isElement(tempTests[i]) ) {
        		System.out.printf("%d is most likely in the bloom filter.\n%d occurrences\n\n", tempTests[i], bFilterTest.countOfElement(tempTests[i]));
        	}else {
        		System.out.printf("%d is not in the bloom filter.\n%d occurrences.\n\n", tempTests[i], bFilterTest.countOfElement(tempTests[i]));
        	}
        }
    }
	
	public static ArrayList<MedicoesLocal> getPortlandDailyData() {
		
		ArrayList<MedicoesLocal> arr = new ArrayList<MedicoesLocal>();
		
		String csvFile = "src/Projeto/temperature.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        		
        	line = br.readLine();
        	
        	int counter = 0;
        	int[] temps = new int[24];
        	
            while ((line = br.readLine()) != null) {

                String[] lineArray = line.split(",");
                if(lineArray[2].equals("")) {
                	temps[counter] = 0;
                }else {
                    temps[counter] = Integer.parseInt(lineArray[2]);	
                }
                counter++;
                if(counter>23) {
                	arr.add(new MedicoesLocal("Portland", lineArray[0].split("\\s+")[0], temps));
                	counter = 0;
                	temps = new int[24];
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		return arr;
	}
	
	public static void BloomFilterTestn2() {
		System.out.println("*******************************\n");
		System.out.println("This test uses random integer values to further test the capibilities of our Bloom Filter\n");
		
		ArrayList<Integer> Set1 = new ArrayList<>();
		ArrayList<Integer> Set2 = new ArrayList<>();
		
		// Setting the number of slots of our Bloom Filter (n) and choosing how many elements are going to be inserted (m)
		int m = 50000;
		int n = 500000;
		System.out.printf("Creating a Bloom Filter with %d slots and filling it with %d elements...\n",n,m);
		
		// Filling our sets with random integers, both between 1 and 99999999
		Random r = new Random();
		for(int i=0;i<m;i++) {
			Set1.add(r.nextInt((99999999 - 1) + 1) + 1);
			Set2.add(r.nextInt((99999999 - 1) + 1) + 1);
		}
		
		// Finding out the perfect k 
		double optimalK = (double)(0.693*n)/m;
		int optimalKRounded = (int)Math.rint(optimalK);
		System.out.printf("The optimal value of the number of hash functions that should be used (k) is %d\n", optimalKRounded);
		
		System.out.println("\nStarting the test...\nComputing the false positive probability and the number of false positives for some values of k...\n");
		
		for(int k=1; k<=20; k+=3) {
			double PFalsosPos_Teorica = Math.pow( (1 - Math.pow( (1 - (double)1/n) ,(m*k) ) ) , k );
			
			BloomFilter bFilterTest = new BloomFilter(n,k);
			
			for(int i=0;i<m;i++) {
				bFilterTest.addElement(Set1.get(i));
			}
			
			int FalsePos_Count = 0;
			
			for(int j=0;j<m;j++) {
				if( (bFilterTest.isElement(Set2.get(j))) && !(Set1.contains(Set2.get(j)))) {
					FalsePos_Count++;
				}
			}
			double PFalsosPos_Pratica = ((double)FalsePos_Count / (double)m);
			
			System.out.printf("k = %02d and the theoretical probability of false positives is %.4f, whereas the real one is %.4f > Number of false positives: %d\n", k, PFalsosPos_Teorica, PFalsosPos_Pratica, FalsePos_Count); 
		}
		
		// End of the bloom filter test
        System.out.println("\n*******************************");
        System.out.println("Ending Bloom Filter Test...\n\n");
	}
	
	public static void MinHashTest(ArrayList<MedicoesLocal> dailyArr) {
		System.out.println("\n*******************************");
        System.out.println("         MinHash Test");
        System.out.println("*******************************\n");
        
        // Creating the MinHash
        MinHash mHashTests = new MinHash(300, dailyArr);
        
        // Creating the signatures
        System.out.println("Computing the signatures...");
        mHashTests.makeSigs();
        
        // Finding similar pairs
        System.out.println("Finding similar pairs with a Jaccard distance equal or smaller than 0.05 between themselves...\n");
        mHashTests.getSimilarPairs(0.05);
        
        // End of the MinHash tests
        System.out.println("\n*******************************");
        System.out.println("Ending MinHash Test...\n\n");
	}
}