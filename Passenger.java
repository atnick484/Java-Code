public class Passenger 
{
	private String firstName; //first name instance variable
	private String lastName; //last name instance variable
	public Passenger() //constructor without parameters
	{
		firstName = "";
		lastName = "";
	}
	public Passenger(String fn, String ln) //constructor with fn/ln parameters
	{
		firstName = fn;
		lastName = ln;
	}
	public String toString() //returns string of passenger: firstLast (ex: AndrewN)
	{
		return firstName+lastName;
	}
	
	public String getFirst(){ //gets the first name of the passenger, to be used in printing the seating chart
		return firstName;
	}
}
