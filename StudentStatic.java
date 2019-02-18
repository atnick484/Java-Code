
public class StudentStatic {

	private String name;
	private static int numStudents;

	public StudentStatic(String nameInput){ //constructor

		name = nameInput; //instance variable = nameInput
		numStudents+=1; //accumulates the number of students; adds one each time student object is created
	}

	public void setName(String nameIn){ //mutator method for name
		name = nameIn;
	}
	public String getName(){ //accessor method for name
		return name;
	}
	public static int returnNumStudents(){ //utility/accessor method that returns number of students created
		return numStudents;
	}

	public String toString(){ //to string returns info about student (name and total students created)
		return "Name: " + name + "   Number of Students: " + numStudents;
	}



}
