/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Saanvi Goyal
 *	@since	1/15/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String city; 
	private String state; 
	private String type;
	private int population;  
	
	// constructor
	public City(String sIn, String cIn, String tIn, int pIn){
		city = cIn; 
		state = sIn; 
		type = tIn; 
		population = pIn; 
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other){
		if(this.population != other.population)
			return this.population - other.population; 
		else if(!this.state.equals(other.state))
			return this.state.compareTo(other.state); 
		else
			return this.city.compareTo(other.city); 
		
		
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other){
		if(this.state.equals(other.state) && this.city.equals(other.city))
			return true;
		else return false; 
	 }
	
	/**	Accessor methods */
	public String getCityName() { return city; }
	public String getStateName() { return state; }
	public String getType() { return type; }
	public int getPopulation() { return population; }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, city, 
						type, population);
	}
}
