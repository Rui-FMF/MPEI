package Projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Projeto para a cadeira de MPEI
		//Rui Fernandes; nMEC 92952
		//Carolina Araujo; nMEC 93248
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("---------------------------");
			System.out.println("      - Weather App -      ");
			System.out.println("  (Temperatures in Kelvin) ");		//-10 degrees C = 263 degrees K ; 30 degrees C = 303 degrees K
			System.out.println("----------------------------");
			System.out.println("1. Bloom Filter");
			System.out.println("2. Search for Similarities");
			System.out.println("3. Exit App");
			System.out.println("----------------------------");
			System.out.print("Option: ");
			
			int option = sc.nextInt();
			
			switch(option) {
				case 1:
					bloomFilterOp(sc);
					break;
				case 2:
					searchSimilaritiesOp(sc);
					break;
				case 3:
					System.out.println("\nThank you, come again!");
					System.exit(0);
					break;
				default:
					System.out.println("\nOops! That's not an option.");
					break;
			}
		}
	}
	
	//Menu Functions

	public static void bloomFilterOp(Scanner sc) {
		while (true) {
			System.out.println("\n----------------------------");
			System.out.println("Bloom Filter Option Menu");
			System.out.println("1. Min and Max Temperature Bloom Filter");
			System.out.println("2. Average Temperature Bloom Filter");
			System.out.println("3. Go back to the Main Menu");
			System.out.println("----------------------------");
			System.out.print("--> ");
			
			int op = sc.nextInt();

            switch (op) {
        	case 3:
        		return;
        	case 1:
        		minMaxOp(sc);
        		break;
        	case 2:
        		AverageOp(sc);
        		break;
        	default:
				System.out.println("\nOops! That's not an option.");
				break;
            }
		}
	}
	
	public static void minMaxOp(Scanner sc) {
		String[] TimePeriod = {"Day", "Month", "Year"};
		int time =-1;
		while( time < 0 || time>2 ) {
			System.out.println("\nChoose Time period:");
			System.out.println("0. Daily   1. Monthly   2. Yearly");
			time = sc.nextInt();
		}
		
		ArrayList<MedicoesLocal> data = DataMenu(sc, TimePeriod[time]);
		System.out.println("Geting Extremes...");
		ArrayList<Integer> extremes = getExtremes(data);
		System.out.println("Filling Bloom Filter...");
		BloomFilter BF = getBF(extremes);
		
		while(true) {
			System.out.println("\n----------------------------");
			System.out.println("Min and Max Temperature BF");
			System.out.println("1. Check ocurrences of Min-Max Temperature pair");
			System.out.println("2. Remove Min-Max Temperature pair");
			System.out.println("3. Go back to the BF Menu");
			System.out.println("----------------------------");
			System.out.print("--> ");
			
			int op = sc.nextInt();
			
			switch(op) {
			case 3:
				return;
			case 1:
				System.out.println("Minimum Temperature?");
				int min = sc.nextInt();
				System.out.println("Maximum Temperature?");
				int max = sc.nextInt();
				checkMaxMinCount(min,max,BF);
				break;
			case 2:
				System.out.println("Minimum Temperature?");
				int removemin = sc.nextInt();
				System.out.println("Maximum Temperature?");
				int removemax = sc.nextInt();
				BF.removeElement(Integer.parseInt(Integer.toString(removemax) + Integer.toString(removemin)));
				break;
			default:
        		System.out.println("\nOops! That's not an option.");
				break;
			}
		}
	}
	
	public static void AverageOp(Scanner sc) {
		String[] TimePeriod = {"Day", "Month", "Year"};
		int time =-1;
		while( time < 0 || time>2 ) {
			System.out.println("\nChoose Time period:");
			System.out.println("0. Daily   1. Monthly   2. Yearly");
			time = sc.nextInt();
		}
		
		ArrayList<MedicoesLocal> data = DataMenu(sc, TimePeriod[time]);
		System.out.println("Geting Averages...");
		ArrayList<Integer> averages = getAverages(data);
		System.out.println("Filling Bloom Filter...");
		BloomFilter BF = getBF(averages);
		
		while(true) {
			System.out.println("\n----------------------------");
			System.out.println("Average Temperature BF");
			System.out.println("1. Check ocurrences of Average Temperature");
			System.out.println("2. Remove Average Temperature");
			System.out.println("3. Go back to the BF Menu");
			System.out.println("----------------------------");
			System.out.print("--> ");
			
			int op = sc.nextInt();
			
			switch(op) {
			case 3:
				return;
			case 1:
				System.out.println("Average Temperature?");
				int avg = sc.nextInt();
				checkAvgCount(avg,BF);
				break;
			case 2:
				System.out.println("Average Temperature?");
				int removeavg = sc.nextInt();
				BF.removeElement(removeavg);
				break;
			default:
        		System.out.println("\nOops! That's not an option.");
				break;
			}
		}
	}
	
	public static void searchSimilaritiesOp(Scanner sc) {
		while (true) {
			System.out.println("\n----------------------------");
			System.out.println("Search for Similarities Option Menu");
			System.out.println("1. Simmilar Daily Temperatures");
			System.out.println("2. Simmilar Monthly Temperatures");
			System.out.println("3. Simmilar Annual Temperatures");
			System.out.println("4. Go back to the Main Menu");
			System.out.println("----------------------------");
			System.out.print("--> ");
			
			int op = sc.nextInt();

            switch (op) {
            	case 4:
            		return;
            	case 1:
            		ArrayList<MedicoesLocal> Ddata = DataMenu(sc, "Day");
            		System.out.println("Making MinHash...");
            		MinHash DH = new MinHash(300, Ddata);
            		System.out.println("Making Signatures...");
            		DH.makeSigs();
            		DH.getSimilarPairs(0.04);
            		break;
            	case 2:
            		ArrayList<MedicoesLocal> Mdata = DataMenu(sc, "Month");
            		System.out.println("Making MinHash...");
            		MinHash MH = new MinHash(300, Mdata);
            		System.out.println("Making Signatures...");
            		MH.makeSigs();
            		MH.getSimilarPairs(0.05);
            		break;
            	case 3:
            		ArrayList<MedicoesLocal> Adata = DataMenu(sc, "Year");
            		System.out.println("Making MinHash...");
            		MinHash AH = new MinHash(300, Adata);
            		System.out.println("Making Signatures...");
            		AH.makeSigs();
            		AH.getSimilarPairs(0.05);
            		break;
            	default:
            		System.out.println("\nOops! That's not an option.");
					break;
            }
		}
	}
	
	public static ArrayList<MedicoesLocal> DataMenu(Scanner sc, String time){
		while (true) {
			System.out.println("\n----------------------------");
			System.out.println("Choose City Data to use:");
			System.out.println("1. All Cities");
			System.out.println("2. One City");
			System.out.println("----------------------------");
			System.out.print("--> ");
			
			int op = sc.nextInt();
			ArrayList<MedicoesLocal> data = new ArrayList<MedicoesLocal>();
			
			switch(op) {
				case 1:
					System.out.println("Loading Data...");
					data = getAllCityData(time);
					return data;
				case 2:
					System.out.println("0. Vancouver   1. Portland   2. San Francisco   3. Seattle   4. Los Angeles   5. San Diego   6. Las Vegas   7. Phoenix   8. Albuquerque   9. Denver");
					System.out.println("10. San Antonio   11. Dallas   12. Houston   13. Kansas City   14. Minneapolis   15. Saint Louis   16. Chicago   17. Nashville   18. Indianapolis   19. Atlanta");
					System.out.println("20. Detroit   21. Jacksonville   22. Charlotte   23. Miami   24. Pittsburgh   25. Toronto   26. Philadelphia   27. New York   28. Montreal   29. Boston");
					int city = sc.nextInt();
					if(city<0 || city>29) {
						System.out.println("Not a valid option, going back...");
						break;
					}
					System.out.println("Loading Data...");
					data = getData(time,city);
					return data;
					
			}
			
		}
	}
	
	//Functions relative to Bloom Filter and formating data
	
	private static ArrayList<Integer> getExtremes(ArrayList<MedicoesLocal> arr) {
		ArrayList<Integer> extremes = new ArrayList<Integer>();

		for (int i = 0; i < arr.size(); i++) {
			int min = 500;
			int max = 0;
			for (int k = 0; k < arr.get(i).getTemperaturas().length; k++) {
				int temp = arr.get(i).getTemperaturas()[k];

				if (temp > max) {
					max = temp;
				}
				if (temp < min) {
					min = temp;
				}

			}
			extremes.add(Integer.parseInt(Integer.toString(max) + Integer.toString(min)));
		}

		return extremes;
	}
	
	private static ArrayList<Integer> getAverages(ArrayList<MedicoesLocal> arr) {
		ArrayList<Integer> averages = new ArrayList<Integer>();

		for (int i = 0; i < arr.size(); i++) {
			int sum = 0;
			int size = arr.get(i).getTemperaturas().length;
			for (int k = 0; k < size; k++) {
				int temp = arr.get(i).getTemperaturas()[k];
				sum+=temp;
			}
			averages.add((int)Math.round(sum/size));
		}

		return averages;
	}

	private static BloomFilter getBF(ArrayList<Integer> values) {
		BloomFilter bloomF = new BloomFilter(values.size()*10, 5);

		for (int i = 0; i < values.size(); i++) {
			int maxmin = values.get(i);
			bloomF.addElement(maxmin);
		}

		return bloomF;
	}

	public static void checkMaxMinCount(int min, int max, BloomFilter bloomF) {
			
		int maxminArg = Integer.parseInt(Integer.toString(max) + Integer.toString(min));
		int count = bloomF.countOfElement(maxminArg);
		
			if( count > 0 ) {
				System.out.printf("Min-Max Temperatures: %d-%d occured %d times",min, max,count);
			} else {
			System.out.printf("No occurences of Min-Max Temperatures: %d-%d", min, max);
		}
	}
	
	public static void checkAvgCount(int avg, BloomFilter bloomF) {
		int count = bloomF.countOfElement(avg);
		
			if( count > 0 ) {
				System.out.printf("Average Temperature: %d occured %d times",avg,count);
			} else {
			System.out.printf("No occurences of Average Temperature: %d", avg);
		}
	}
	
	//Functions to get various amounts and types of Data
	
	public static ArrayList<MedicoesLocal> getData(String time, int index){
		ArrayList<MedicoesLocal> arr = new ArrayList<MedicoesLocal>();
		
		if(time.equals("Day")) {
			arr = getDailyData(index);
		} else if(time.equals("Month")){
			arr = getMonthlyData(index);
		} else if(time.equals("Year")){
			arr = getAnnualData(index);
		}
		return arr;
	}
	
	public static ArrayList<MedicoesLocal> getDailyData(int index) {		//index -> indice da Cidade no array de cidades
		
		ArrayList<MedicoesLocal> arr = new ArrayList<MedicoesLocal>();
		
		String[] Cities = getCities();
		String Local = Cities[index];
		
		
		String csvFile = "src/Projeto/temperature.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        		
        	line = br.readLine();
        	
        	int counter = 0;
        	int[] temps = new int[24];
        	
            while ((line = br.readLine()) != null) {

                String[] lineArray = line.split(",");
                if(lineArray[index+1].equals("")) {
                	temps[counter] = 0;
                }else {
                    temps[counter] = Integer.parseInt(lineArray[index+1]);	
                }
                counter++;
                if(counter>23) {
                	arr.add(new MedicoesLocal(Local, lineArray[0].split("\\s+")[0], temps));
                	counter = 0;
                	temps = new int[24];
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		return arr;
	}
	
	public static ArrayList<MedicoesLocal> getMonthlyData(int index) {
			
			ArrayList<MedicoesLocal> arr = new ArrayList<MedicoesLocal>();
			
			String[] Cities = getCities();
			String Local = Cities[index];
			
			String csvFile = "src/Projeto/temperature.csv";
	        String line = "";
	
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        		
	        	line = br.readLine();
	        	line = br.readLine();
	        	
	        	loop:
	        	for(int ano = 2013; ano<2018; ano++) {
	        		for(int mes = 1; mes<13; mes++) {
	        			if(line == null) {
	        				break loop;
	        			}
	        			ArrayList<Integer> temps = new ArrayList<Integer>();
	        			String[] lineArray = line.split(",");
	        			String[] data = lineArray[0].split("\\s+")[0].split("/");
	        			
	        			while(Integer.parseInt(data[1]) == mes){
	        				if(lineArray[index+1].equals("")) {
	        					temps.add(0);
	                        }else {
	                        	temps.add(Integer.parseInt(lineArray[index+1]));
	                        }
	        				line = br.readLine();
	        				if(line == null) {
	        					arr.add(new MedicoesLocal(Local, data[1]+"/"+data[2], temps.stream().mapToInt(Integer::intValue).toArray()));
	            				break loop;
	            			}
	            			lineArray = line.split(",");
	            			data = lineArray[0].split("\\s+")[0].split("/");
	        			}
	        			if(data[1].equals("01")) {
	        				arr.add(new MedicoesLocal(Local, "12/"+(Integer.parseInt(data[2])-1), temps.stream().mapToInt(Integer::intValue).toArray()));
	        			}else {
	        				arr.add(new MedicoesLocal(Local, (Integer.parseInt(data[1])-1)+"/"+data[2], temps.stream().mapToInt(Integer::intValue).toArray()));
	        			}
	        		}
	        	}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return arr;
		}

	public static ArrayList<MedicoesLocal> getAnnualData(int index) {
		
		ArrayList<MedicoesLocal> arr = new ArrayList<MedicoesLocal>();
		
		String[] Cities = getCities();
		String Local = Cities[index];
		
		String csvFile = "src/Projeto/temperature.csv";
	    String line = "";
	
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	    		
	    	line = br.readLine();
	    	line = br.readLine();
	    	
	    	loop:
	    	for(int ano = 2013; ano<2018; ano++) {
	    			if(line == null) {
	    				break loop;
	    			}
	    			ArrayList<Integer> temps = new ArrayList<Integer>();
	    			String[] lineArray = line.split(",");
	    			String[] data = lineArray[0].split("\\s+")[0].split("/");
	    			
	    			while(Integer.parseInt(data[2]) == ano){
	    				if(lineArray[index+1].equals("")) {
	    					temps.add(0);
	                    }else {
	                    	temps.add(Integer.parseInt(lineArray[index+1]));
	                    }
	    				line = br.readLine();
	    				if(line == null) {
	    					arr.add(new MedicoesLocal(Local, data[2], temps.stream().mapToInt(Integer::intValue).toArray()));
	        				break loop;
	        			}
	        			lineArray = line.split(",");
	        			data = lineArray[0].split("\\s+")[0].split("/");
	    			}
	    			arr.add(new MedicoesLocal(Local, ""+(Integer.parseInt(data[2])-1), temps.stream().mapToInt(Integer::intValue).toArray()));
	    	}
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return arr;
	}
	
	public static String[] getCities(){
		String[] Cities = {"Vancouver", "Portland", "San Francisco", "Seattle", "Los Angeles", "San Diego", "Las Vegas", "Phoenix", "Albuquerque", "Denver", "San Antonio",
				"Dallas","Houston",	"Kansas City", "Minneapolis", "Saint Louis", "Chicago",	"Nashville", "Indianapolis", "Atlanta",	"Detroit", "Jacksonville", "Charlotte",	"Miami", 
				"Pittsburgh", "Toronto", "Philadelphia", "New York", "Montreal", "Boston"};
		return Cities;
	}
	
	public static ArrayList<MedicoesLocal> getAllCityData(String time){			//time is "Day" , "Month" or "Year"
		String[] Cities = getCities();
		ArrayList<MedicoesLocal> arr = new ArrayList<MedicoesLocal>();
		
		for (int i=0; i<Cities.length; i++) {
			ArrayList<MedicoesLocal> temparr = new ArrayList<MedicoesLocal>();
			
			if(time.equals("Day")) {
				temparr = getDailyData(i);
			} else if(time.equals("Month")){
				temparr = getMonthlyData(i);
			} else if(time.equals("Year")){
				temparr = getAnnualData(i);
			}
			arr.addAll(temparr);
		}
		return arr;
	}

}
