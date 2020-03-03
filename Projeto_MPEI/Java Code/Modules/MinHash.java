package Projeto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MinHash {
	private int prime = 5000011;
	private int numHash;
	private int[] Vals1;
	private int[] Vals2;
	private int[][] Signatures;
	private ArrayList<MedicoesLocal> dados;
	private int numSets;
	
	public MinHash(int numHash, ArrayList<MedicoesLocal> dados) {
		this.numHash = numHash;
		Vals1 = new int[numHash];
		Vals2 = new int[numHash];
		this.Vals1 = generateRand(Vals1);
		this.Vals2 = generateRand(Vals2);
		this.dados = dados;
		this.numSets = dados.size();
	}
	
	private int[] generateRand(int[] arr) {
		Random r1 = new Random();
		for(int i = 0; i < numHash; i++) {
			arr[i] = r1.nextInt((prime - 1) + 1) +1;
		}
		return arr;
	}
	
	private int[] hashing(int[] set) {
		int[] hashedSet = new int[numHash];
		for(int j = 0; j < numHash; j++) {
			int[] Hk = new int[set.length];
			for(int k = 0; k < set.length; k++) {
				Hk[k] = ((Vals1[j] * set[k] + Vals2[j]) % prime);
			}
			Arrays.sort(Hk);
			hashedSet[j] = Hk[0];	//minimo
		}
		return hashedSet;
	}
	
	public void makeSigs() {
		Signatures = new int[numSets][numHash];
		for(int i=0; i<numSets;i++) {
			Signatures[i]=hashing(dados.get(i).getTemperaturas());
		}
	}
	
	public void getSimilarPairs(double threshold) {
		
        for(int i = 0; i < numSets; i++) {
            for(int j = i+1; j < numSets; j++) {
                int counter = 0;
                for(int k = 0; k < numHash; k++) {
                    if(Signatures[i][k] == Signatures[j][k]) {
                        counter++;
                    }
                }
                double distJ = 1 - ((double)(counter) / (double)(numHash));
                if(distJ <= threshold && distJ != 0) {
                    System.out.printf(dados.get(i).getData() + " in "+dados.get(i).getLocal()+" and "+ dados.get(j).getData() +" in "+ dados.get(j).getLocal() +" have similar temperature with Jaccard distance = %.2f\n",distJ);
                }
            }
        }
	}
	
}
