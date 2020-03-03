package Projeto;

import java.util.Random;

public class BloomFilter {
	private int n;		//Length of Bloom Filter
	private int[] F; 	//Bloom Filter Array
	private int k;		// Number of hash functions
	private int[] Vals1;
	private int[] Vals2;
	
	public BloomFilter(int n, int k) {
		super();
		this.n = n;
		this.k = k;
		this.F = new int[n];

		Vals1 = new int[k];
		Vals2 = new int[k];
		this.Vals1 = generateRand(Vals1);
		this.Vals2 = generateRand(Vals2);
		
	}
	
	private int[] generateRand(int[] arr) {
		Random r1 = new Random();
		for(int i = 0; i < k; i++) {
			arr[i] = r1.nextInt((5000011 - 1) + 1) +1;
		}
		return arr;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
	public int[] getF() {
		return F;
	}
	private int Hash(int key, int i) {
		//return (int) ((177573+key) % (Math.pow(2, 32)-1));		muitos falsos positivos
		return Math.abs((Vals1[i-1] * key + Vals2[i-1])%5000011) ;
		
	}
	
	public void addElement(int element) {
		
		int key = element;
		
		for (int i=1; i<=k; i++) {
			key = element + (i*i);
			int pos = Hash(key,i);
			pos = (pos % n);
			F[pos]++;
		}
	}
	
	public void removeElement(int element) {
		int key = element;
		if(isElement(element)) {
			for (int i=1; i<=k; i++) {
				key = element + (i*i);
				int pos = Hash(key,i);
				pos = (pos % n);
				if(F[pos] > 0) {
					F[pos]--;
				}
			}
		}
	}
	
	public boolean isElement(int element) {
		int key = element;
		
		for (int i=1; i<=k; i++) {
			key = element + (i*i);
			int pos = Hash(key,i);
			pos = (pos % n);
			
			if(F[pos]==0) {
				return false;
			}
		}
		return true;
	}
	
	public int countOfElement(int element) {
		int key = element;
		if(! isElement(element)){
            return 0;
        }
		
		int min = 9999999;
		
		for (int i=1; i<=k; i++) {
			key = element + (i*i);
			int pos = Hash(key,i);
			pos = (pos % n);
			
			if(F[pos] < min){
                min = F[pos];
            }
		}
		return min;
	}

}
