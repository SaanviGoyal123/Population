import java.util.ArrayList; 

/**
 *	SortMethods - Sorts lists of Cities in different ways. 
 *
 *	@author Saanvi Goyal
 *	@since	01/17/23
 */ 
 
public class SortMethods{
	
	/**
	 *	Swaps City objects in a List given specified location
	 *	@param array	ArrayList of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(ArrayList<City> array, int x, int y) {
		City temp = array.get(x);
		array.set(x, array.get(y));
		array.set(y, temp);
	}
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param list		ArrayList of the cities that need to be sorted
	 */
	public void bubbleSort(ArrayList<City> list) {
		for (int outer = list.size() - 1; outer > 0; outer--) {
			for (int inner = 0; inner < outer; inner++) {
				if (list.get(inner).compareTo(list.get(inner+1)) > 0)
					swap(list, inner, inner + 1);
			}
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order 
	 *	@param list		ArrayList of the cities that need to be sorted
	 */
	public void insertionSort(ArrayList<City> list) {
		for (int outer = 1; outer < list.size(); outer++) {
			City temp = list.get(outer);
			int inner;
			for (inner = outer; inner > 0 && temp.compareTo(list.get(inner - 1)) < 0; inner--)
				list.set(inner, list.get(inner - 1));	
			list.set(inner, temp);
		}
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order 
	 *	@param list		ArrayList of the cities that need to be sorted
	 */
	public void selectionSort(ArrayList<City> list) {
		int greatestIndex;
		for (int outer = list.size() - 1; outer >= 0; outer--) {
			greatestIndex = 0;
			for (int inner = 0; inner <= outer; inner++) {
				if (list.get(greatestIndex).compareTo(list.get(inner)) < 0)
					greatestIndex = inner;
			}
			swap(list, greatestIndex, outer);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order 
	 *	@param arr		array list of City objects to be sorted
	 *  @param start    starting index of the array list to be sorted
	 *  @param end      ending index of the array list to be sorted
	 */
	 public static void mergeSort(ArrayList<City> cities, int start, int end) {
        if (cities == null || start >= end) {
            return;
        }
        
        int middle = start + (end - start) / 2;
        mergeSort(cities, start, middle);
        mergeSort(cities, middle + 1, end);
        
        ArrayList<City> temp = new ArrayList<City>();
        int i = start;
        int j = middle + 1;
        int k = 0;
        
        while (i <= middle && j <= end) {
            if (cities.get(i).getPopulation() > cities.get(j).getPopulation()) {
                temp.add(cities.get(i++));
            } else {
                temp.add(cities.get(j++));
            }
        }
        while (i <= middle) {
            temp.add(cities.get(i++));
        }
        while (j <= end) {
            temp.add(cities.get(j++));
        }
        
        for (i = start, k = 0; i <= end; i++, k++) {
            cities.set(i, temp.get(k));
        }
    }
	
	
}
