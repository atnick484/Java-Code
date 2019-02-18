import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ReservationTest {

	private static ArrayList<Passenger> pass = new ArrayList<Passenger>(); //arraylist of passenger objects
	@SuppressWarnings("resource")

	public static void main(String[]args) throws FileNotFoundException{   
		Scanner input = new Scanner(System.in); //scanner for name etc.

		BufferedReader br = new BufferedReader(new FileReader("src\\Period_5.txt")); //read the text line by line requires BufferReader
		String s = "";
		ArrayList<String> names = new ArrayList<String>(); //makes list of names
		while(s!=null)
		{
			try 
			{   //read each line into a String
				s= br.readLine();
				if(s!=null)  //add names to an ArrayList
					names.add(s);
			} 
			catch (IOException e) //if there is an exception
			{
				System.out.println("Something went wrong");
			}
		}
		for(String x:names)
		{  
			makePassenger(x);
		}


		Reservation flight1 = new Reservation(pass); //create a reservation object, forwarding the list of passengers to it to fill the seating chart partially 


		System.out.println("Welcome to flyNickerson Airlines. The initial seating chart is below:");
		flight1.viewSeatingChart(); //print seating chart
		System.out.println("Please enter a numerical option below:\nChoices:\n\t1: Add a passenger\n\t2: Remove a passenger\n\t3: Quit application");
		int choice = input.nextInt(); //get the option choice from user
		while(choice!=3){ //loop until user wants to quit
			if(choice==1){ //add a passenger
				System.out.println("\nAdd a Passenger********************************************************************************************");
				System.out.println("Please enter the first name of the passenger to be added:");
				input.nextLine(); //picks up extra space
				String firstN = input.next(); //gets the first name entered by user
				System.out.println("Please enter the last name of the passenger to be added:");
				input.nextLine(); //picks up the extra space
				String lastN = input.next(); //gets the last name entered by user
				flight1.addPassenger(firstN, lastN); //runs addPassenger() method
			}
			if(choice==2){
				System.out.println("\nRemove a Passenger********************************************************************************************");
				System.out.println("Please enter the first name of the passenger to be removed:");
				input.nextLine(); //picks up extra space

				String firstN = input.next(); //gets the first name entered by user
				System.out.println("Please enter the last name of the passenger to be removed:");
				input.nextLine(); //picks up extra space
				String lastN = input.next(); //gets the last name entered by user
				flight1.removePassenger(firstN, lastN); //runs removePassenger() method
			}
			System.out.println("\n\nAgain, please enter a numerical option below:"); //loops bnack
			System.out.println("Choices:\n\t1: Add a passenger\n\t2: Remove a passenger\n\t3: Quit application");

			input.nextLine();
			choice = input.nextInt(); //gets the user's next choice
		}
		//user quits (option 3)
		System.out.println("\nQuitting Application********************************************************************************************");
		System.out.println("\n\nThank you for flying with flyNickerson Airlines. The final seating chart is below\n");
		flight1.viewSeatingChart(); //prints seating chart when user quits (option 3)




	}
	public static void makePassenger(String p) //creates passenger objects to be put in passengers array for constructor
	{
		for(int i = 0;i<p.length();i++)
		{    //split the name at , or space delimeter , 
			if(p.charAt(i)==' '||p.charAt(i)==',')
			{ 
				String ln = p.substring(0,i);
				String fn = p.substring(i+1,p.length());
				pass.add(new Passenger(fn,ln));  //this example adds to an ArrayList, 
				// you will add to your Passenger 2D Array

				//break;
			}
		}

	}
}
