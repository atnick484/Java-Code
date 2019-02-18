import java.lang.IndexOutOfBoundsException;
import java.util.*;


public class Reservation {
	Scanner input = new Scanner(System.in); //to be used to ask user for options

	private Passenger[][] seatingChart = new Passenger[10][5]; //creats a seatingChart array with 10 rows, 5 columns
	private ArrayList<Passenger> waitingList = new ArrayList<Passenger>(); //empty arraylist that will hold Passenger objects on waitlist

	public ArrayList<Passenger> getWaitlist(){ //getter for waitlist
		return waitingList;
	}

	//RESERVATION CONSTRUCTOR
	public Reservation(ArrayList<Passenger> passengers){ //creates and partially fills seatingChart with passenger objects from text file
		//parameter passengers is an arraylist of passenger objects from test class (from txt file)
		int random1 = (int) (Math.random()*49+1); //random spots to leave as 'empty' in original seatingChart
		int random2 = (int) (Math.random()*49+1);
		int random3 = (int) (Math.random()*49+1);
		int random4 = (int) (Math.random()*49+1);

		int seatNum = 0; //numbers 0-49 seats
		for(int i=0;i<seatingChart.length;i++){  
			for(int j=0;j<seatingChart[0].length;j++){ //loop thru 2Darray
				if(seatNum==random1||seatNum==random2||seatNum==random3||seatNum==random4){ //skip this and leave empty
					seatingChart[i][j] = null;
					seatNum++;
				}
				else{
					seatingChart[i][j] = passengers.get(seatNum); //fill from passengers arraylist from test class
					seatNum++;
				}
			}
		}

	}

	//TOSTRING (PRINT SEATING CHART) METHOD
	public void viewSeatingChart(){
		System.out.println();
		System.out.printf("%-10s %-17s %-17s %-17s %-17s %-17s\n", " ", "1", "2","3","4","5"); //format columns
		int spacer = 0;
		String spacerx; //formatting spacer stuff (trial and error)
		System.out.println("\n");
		for(int x=0;x<10;x++){
			System.out.printf("%-11s", x+1); //row titles
			spacer=1;
			for(int y=0;y<5;y++){
				spacerx = "%" + (-17-spacer) + "s"; 
				if(seatingChart[x][y] instanceof Passenger){ //if is passenger object
					System.out.printf(spacerx, seatingChart[x][y].getFirst()); //spacer; loop thru seatingChart and prints first names
				}
				else{
					System.out.printf(spacerx, "empty"); //if seat is empty/null
				}
			}

			System.out.println("\n\n");
		}
		System.out.println("Waiting List: " + waitingList + "\n\n"); //print waiting list
	}

	public int[] findEmpty(int startAt){ //finds empty seats and returns a int[] of [row#,col#]
		int[] emptySpot = new int[2];
		emptySpot[0] = -1;
		emptySpot[1] = -1;
		int counter = 0; //counter is used to only start checking once startAt seat has been passed (so can find mult. empty seats)
		for(int r=0;r<seatingChart.length;r++){
			for(int c=0;c<seatingChart[0].length;c++){ //loop thru array
				if(counter>=startAt){
					if(seatingChart[r][c]==null){ //if seat is empty
						emptySpot[0]=r; //set the spot
						emptySpot[1]=c;
						return emptySpot;
					}
				}
				counter++;
			}
		}
		return emptySpot; //returns a int[] of [row#,col#] of empty seat
	}

	//ADD PASSENGER METHOD
	public void addPassenger(String first, String last)throws java.lang.IndexOutOfBoundsException{
		System.out.println("\nAdd a Passenger************************************************************");
		int startHere = 0; //changes to allow to find multiple empty seats

		if(findEmpty(startHere)[0]==-1){ //no empty seats in seatingChart
			System.out.println("There are no empty spots! You will be"
					+ " added to the waiting list. (Spot in line: " + (waitingList.size()+1) + ")");
			waitingList.add(new Passenger(first, last)); //adds passenger to waitlist
			viewSeatingChart(); //prints chart
		}
		else{ //seats available
			viewSeatingChart();
			System.out.println("There is at least one seat available! Seat(s) available:");
			int optionNum = 1; //to be printed out
			ArrayList<int[]> emptySeats= new ArrayList<int[]>(); //creats an arraylist of each set of [row#,col#] arrays to hold all empty seats

			while(findEmpty(startHere)[0]!=-1){ //loop to find ALL empty seats
				System.out.print("\tOption #" + optionNum+ " - Row: " + (findEmpty(startHere)[0]+1) + ", Column: " + (findEmpty(startHere)[1]+1)+"\n");
				emptySeats.add(findEmpty(startHere));
				startHere=(findEmpty(startHere)[0]*5)+1+(findEmpty(startHere)[1]); //calculates location to start at next seat after previous empty seat
				optionNum++;

			}

			System.out.println("\nWhat seat option would you like?");
			int seatChoice = input.nextInt()-1;
			try{   //sets selected mpty seat to user's name 
				seatingChart[emptySeats.get(seatChoice)[0]][emptySeats.get(seatChoice)[1]] = new Passenger(first,last);
			} 
			catch (java.lang.IndexOutOfBoundsException e){ //if user does not enter a valid option#
				System.out.println("You have selected an option that is not displayed. Please try running the prograam again.");
			}


			System.out.println("\nGreat! The new updated seating chart with your seat is below.");
			viewSeatingChart(); //prints chart
		}


	}


	//FIND PASSENGER METHOD
	public int[] findPassenger(String first, String last){ //to be used in removePassenger(); returns a int[] of [row#,col#]
		Passenger remover = new Passenger(first,last); //creates passenger object of person to be returned
		int[] passLocation = new int[2]; //to be returned
		passLocation[0]=-1;
		passLocation[1]=-1;

		for(int r=0;r<seatingChart.length;r++){
			for(int c=0;c<seatingChart[0].length;c++){ //loops thru seating chart array
				if(seatingChart[r][c] instanceof Passenger){ //makes sure current location is not null
					if(seatingChart[r][c].toString().equalsIgnoreCase(remover.toString())){
						passLocation[0] = r;
						passLocation[1] = c;
						return passLocation; //returns a int[] of [row#,col#] of passenger's seat

					}
				}
			}
		}


		return passLocation; //returns [-1,-1] if person not found
	}


	//REMOVE PASSENGER METHOD
	public void removePassenger(String first, String last){ 
		System.out.println("\nRemove a Passenger************************************************************");
		Passenger remover = new Passenger(first,last); //creates passenger object of person to be removed

		if(findPassenger(first,last)[0]!=-1){  //passenger is found
			int r=findPassenger(first,last)[0]; //row of passenger
			int c=findPassenger(first,last)[1]; //column of passenger
			System.out.println(seatingChart[r][c].toString() + " will now be removed from the seating chart.");
			seatingChart[r][c]=null; //removes person from chart
			if(waitingList.size()!=0){ //if waiting list is NOT empty
				seatingChart[r][c] = waitingList.get(0); //puts first person on waiting list in seating chart
				System.out.println("\n"+waitingList.get(0).toString() + " will now be added to the seating chart from the waiting list.");
				waitingList.remove(0); //removes this newly added person from the waiting list
			}
			else{
				System.out.println("The waiting list empty. Seat is now available"); //if waitinglist is empty (size is 0)
			}

		}

		else{
			System.out.println("Passenger "+remover.toString()+" not found."); //passenger to be removed not found in seating chart array
		}

		System.out.println();
		viewSeatingChart(); //print chart


	}




}





