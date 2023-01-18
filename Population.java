import java.util.ArrayList;
import java.util.Scanner; 

/**
 *	Population - This program processes a list of data on the population 
 *  of cities across the US. Based on the user input, it can provide things
 *  like the top 50 most populous cities and the Top 50 cities when sorted
 *  by their name in ascending order. 
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Saanvi Goyal
 *	@since	1/15/22
 */
public class Population {
	
	// List of cities
	private ArrayList<City> cities;
	//Instance of Sort Methods
	private SortMethods sort; 
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	
	public Population(){
		cities = new ArrayList<City>(); 
		sort = new SortMethods(); 
	}
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	public static void main(String[] args){
		Population ppl = new Population();
		ppl.run(); 
	}
	
	/**
	 * This method runs the whole game. It calls the method to print out 
	 * the introduction, read the data file, print the number of cities 
	 * in the file, and calls the method to prompt the user until they quit. 
	 * Based on which option the user chooses, it will call the method to 
	 * run the sort and display the data. If the user wants to quit, it will
	 * print a thank you message and then quit. 
	 **/
	public void run(){
		printIntroduction(); 
		readFile(); 
		printCount(); 
		int in = -1; 
		while(in != 9){
			printMenu(); 
			in = printPrompt();
			if(in == 1) option1();
			if(in == 2) option2(); 
			if(in == 3) option3(); 
			if(in == 4) option4();
			if(in == 5) option5(); 
			if(in == 6) option6(); 
			if(in == 9) System.out.println("\n\nThanks for using Population!"); 
		}
	}
	
	/**
	 * This method uses FileUtils to read the data file. It creates 
	 * new city objects using the data. 
	 **/
	public void readFile(){
		Scanner scan = FileUtils.openToRead(DATA_FILE); 
		scan = scan.useDelimiter("[\t\n]"); 
		while(scan.hasNext()){
			City city = new City(scan.next(),scan.next(),scan.next(),scan.nextInt()); 
		 	cities.add(city);	 
		}
	}
	
	/**
	 * This method prints the formatted header
	 **/
	public void printHeader(){
		System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
	}
	
	/**
	 * This method prints the number of cities that are in the database 
	 **/
	public void printCount(){
		System.out.println("\n\n" + cities.size() + " cities in database."); 
	}
	
	/**
	 * This method prompts the user to enter a valid selection until 
	 * they input a valid number. 
	 **/
	public int printPrompt(){
		boolean valid = false; 
		int in = -1; 
		while(in < 1 || in == 7 || in == 8 || in > 9){
			in = Prompt.getInt("Enter Selection"); 
		}
		return in; 
	}
	
	/**
	 * This method uses selection sort to print out the fifty least 
	 * populous cities. 
	 **/
	public void option1() {
		long startMillisec = System.currentTimeMillis();
		sort.selectionSort(cities);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec; 
		System.out.println("\nFifty least populous cities");
		printHeader(); 
		for (int i = 0; i < 50; i++) {
			System.out.printf("\n%5s%s", (i+1) + ": ", cities.get(i).toString());
		}
		
		System.out.println("\n\nElapsed Time " + time + " milliseconds\n");
	}
	
	/**
	 * This method uses merge sort to print out the fifty most populous
	 * cities. 
	 **/
	public void option2() {
		long startMillisec = System.currentTimeMillis();
		sort.mergeSort(cities, 0, cities.size()-1);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec; 
		System.out.println("\nFifty most populous cities");
		printHeader(); 

		for (int i = 0; i < 50; i++) {
			System.out.printf("\n%5s%s", (i+1) + ": ", cities.get(i).toString());
		}
		
		System.out.println("\n\nElapsed Time " + time + " milliseconds\n");
	}
	
	/**
	 * This method uses and insertion sort to sort the cities by name in 
	 * ascending order. 
	 **/
	public void option3() {
		long startMillisec = System.currentTimeMillis();
		for (int outer = 1; outer < cities.size(); outer++) {
			City temp = cities.get(outer);
			int inner;
			for (inner = outer; inner > 0 && 
			temp.getCityName().compareTo(cities.get(inner - 1).getCityName()) < 0; inner--)
				cities.set(inner, cities.get(inner - 1));
			
			cities.set(inner, temp);
		}
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec; 
		
		System.out.println("\nFifty cities sorted by name");
		printHeader(); 
		for (int i = 0; i < 50; i++) {
			System.out.printf("\n%5s%s", (i + 1) + ": ", cities.get(i).toString());
		}
		
		System.out.println("\n\nElapsed Time " + time + " milliseconds\n");
	}
	
	/**
	 * This method uses merge sort to print out the bottom fifty cities
	 * sorted by name. 
	 **/
	public void option4() {
		long startMillisec = System.currentTimeMillis();
		split(cities);
		long endMillisec = System.currentTimeMillis();
		long time = endMillisec - startMillisec; 
		
		System.out.println("\nFifty cities sorted by name descending");
		printHeader(); 
		int len = cities.size(); 
		for (int i = len - 1; i >= len - 50; i--) {
			System.out.printf("\n%5s%s", (len-i) + ": ", cities.get(i).toString());
		}
		System.out.println("\n\nElapsed Time " + time + " milliseconds\n");
	}
	
	/**
	 * This method prompts the user for a state name and uses merge sort 
	 * to print out the top 50 most populous cities in that state. 
	 **/
	public void option5() {
		ArrayList<City> citiesInState = new ArrayList<City>();
		String state = Prompt.getString("Enter state name (ie. Alabama)");
		for (int i = 0; i < cities.size(); i++) {
			if (state.equals(cities.get(i).getStateName()))
				citiesInState.add(cities.get(i));
		}
		
		if (citiesInState.size() == 0) {
			System.out.println("ERROR: " + state + " is not valid");
			option5();
		}
		else {
			int len = citiesInState.size()-1; 
			sort.mergeSort(citiesInState, 0, len);
			System.out.println("\nFifty most populous cities in " + state);
			printHeader(); 
			for (int i = 0; i < 50; i++) {
				System.out.printf("\n%5s%s", (i+1) + ": ", citiesInState.get(i).toString());
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * This method prompts the user for a city name and prints out all the 
	 * cities with that name in ascending order of their poppulation. It 
	 * uses merge sort to do so. 
	 **/
	public void option6() {
		ArrayList<City> matchingCities = new ArrayList<City>();
		String city = Prompt.getString("Enter city name");
		for (int i = 0; i < cities.size(); i++) {
			if (city.equals(cities.get(i).getCityName()))
				matchingCities.add(cities.get(i));
		}
		
		if (matchingCities.size() == 0) {
			System.out.println("ERROR: " + city + " is not valid");
			option6();
		}
		else {
			int len = matchingCities.size()-1;
			sort.mergeSort(matchingCities, 0, len);
			System.out.println("\nCity " + city + " by population");
			printHeader();  
			for (int i = 0; i <= len; i++) {
				System.out.printf("\n%5s%s", (i+1) + ": ", matchingCities.get(i).toString());
			}
			System.out.println("\n");
		}
	}
	
	
	/** This merges two arrays in to one. 
	 * 	@param list			The result array.
	 * 	@param listL		The list on the left to be merged
	 * 	@param listR 		the list on the right to be merged*/
	public void merge(ArrayList<City> list, ArrayList<City> listL, ArrayList<City> listR) {
		int l = 0;
		int r = 0;
		int i = 0;
		int llen = listL.size(); 
		int rlen = listR.size(); 
		while (l < llen && r < rlen) {
			if (listL.get(l).getCityName().compareTo(listR.get(r).getCityName()) < 0) {
				list.set(i, listL.get(l));
				i++;
				l++;
			}
			else {
				list.set(i, listR.get(r));
				i++;
				r++;
			}
		}
		while (l < llen) {
			list.set(i, listL.get(l));
			i++;
			l++;
		}
		while (r < rlen) {
			list.set(i, listR.get(r));
			i++;
			r++;
		}
	}
	
	/** Splits an Array List at the midpoint 
	 * 	@param list		the array to be split */
	public void split(ArrayList<City> list) {
		ArrayList<City> listLeft;
		ArrayList<City> listRight;

		if (list.size() > 2) {
			int split = (list.size() - 1)/2;
			listLeft = new ArrayList<City>();
			listRight = new ArrayList<City>();
			for (int a = 0; a < split; a++) {
				listLeft.add(a, list.get(a));
			}
			for (int b = split; b < list.size(); b++) {
				listRight.add(b - split, list.get(b));
			}
			split(listLeft);
			split(listRight);
			
			merge(list, listLeft, listRight);
		}
		else {
			if (list.size() == 2) {
				if (list.get(0).getCityName().compareTo(list.get(1).getCityName()) > 0) {
					City temp = list.get(0);
					list.set(0, list.get(1));
					list.set(1, temp);
				}
			}
			return;
		}
	}
}
