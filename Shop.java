import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;
import java.util.Scanner; //needed for the Scanner class

import javax.swing.JOptionPane;
public class Shop { 


	public static void main(String[]args) throws IOException{
		Item [] arrStore = new Item[10]; //Student class is in the same project

		BufferedReader br = new BufferedReader(new FileReader("src\\store.txt")); //or E:\\FolderName\\list.txt

		String line = null; //each line in the text file

		int i = 0; // index for my students array

		String[] values = new String[10];//pick up the values in the file separated by commas

		while ((line = br.readLine()) != null) { //while there are lines to pick up in the text file
			values = line.split(",");  //pick up item separated by comma

			//note, text file stores everything in text file as String/text. If you want to use
			//ints and doubles as numbers then you have to parse them example Integer.parseInt(whatever)
			arrStore[i]= new Item(Integer.parseInt(values[0]), Double.parseDouble(values[1]),values[2]);

			i++; //ensures you fill the next element in the students array

		}

		System.out.println(Arrays.toString(arrStore));

		//at this point you can do whatever you want with the array filled with Student objects,
		//can pass up students array as a parameter for instance





		/////////////////////////////////////////////////////////////////////////////////
		System.out.println("Items in store:");
		System.out.println(arrStore[0].getID()+". " + arrStore[0].toString());
		System.out.println(arrStore[1].getID()+". " + arrStore[1].toString());
		System.out.println(arrStore[2].getID()+". " + arrStore[2].toString());
		System.out.println(arrStore[3].getID()+". " + arrStore[3].toString());
		System.out.println(arrStore[4].getID()+". " + arrStore[4].toString());
		System.out.println(arrStore[5].getID()+". " + arrStore[5].toString());
		System.out.println(arrStore[6].getID()+". " + arrStore[6].toString());
		System.out.println(arrStore[7].getID()+". " + arrStore[7].toString());
		System.out.println(arrStore[8].getID()+". " + arrStore[8].toString());
		System.out.println(arrStore[9].getID()+". " + arrStore[9].toString());

		ShoppingCart cart1 = new ShoppingCart();


		//want to figure out how to do the inventory deductions?
		//ALSO FIGURE OUT WHAT TO DO IF USER TRIES TO REMOVE SOMETHING NOT IN CART OR OTHER ERROR CATCHERS LIKE SETTING A LIMIT OF CART TO 5?
		String response = "";
		int x = 0;
		int choice = Integer.parseInt(JOptionPane.showInputDialog("The choices are as follows:\n(1) view shopping list\n(2) add items\n(3) remove items\n(4) search for items\n(5) done shopping"));
		while(choice!=5){
			x++;
			if(choice==2){
				int desiredItem = Integer.parseInt(JOptionPane.showInputDialog("What would you like to add?\n\tStore:\n\t1. " + arrStore[0].toString()+"\n\t2. " + arrStore[1].toString()+"\n\t3. " + arrStore[2].toString()+"\n\t4. " + 
						arrStore[3].toString()+"\n\t5. " + arrStore[4].toString() + "\n\t6. "+arrStore[5].toString()+ "\n\t7. " + arrStore[6].toString()+ "\n\t8. "+arrStore[7].toString()+"\n9. \t"+
						arrStore[8].toString()+"\n\t10. "+arrStore[9].toString()));
				Item itemWanted = arrStore[desiredItem-1]; //
				int desiredQty = Integer.parseInt(JOptionPane.showInputDialog("How many " + itemWanted.getName() + "s would you like?"));

				cart1.addItems(itemWanted.getID(), desiredQty, arrStore);
				System.out.println("Item added to cart.");
			}
			if(choice==1){
				System.out.println("Items in store:");
				System.out.println(arrStore[0].getID()+". " + arrStore[0].toString());
				System.out.println(arrStore[1].getID()+". " + arrStore[1].toString());
				System.out.println(arrStore[2].getID()+". " + arrStore[2].toString());
				System.out.println(arrStore[3].getID()+". " + arrStore[3].toString());
				System.out.println(arrStore[4].getID()+". " + arrStore[4].toString());
				System.out.println(arrStore[5].getID()+". " + arrStore[5].toString());
				System.out.println(arrStore[6].getID()+". " + arrStore[6].toString());
				System.out.println(arrStore[7].getID()+". " + arrStore[7].toString());
				System.out.println(arrStore[8].getID()+". " + arrStore[8].toString());
				System.out.println(arrStore[9].getID()+". " + arrStore[9].toString());

			}
			if(choice==3){
				int desiredItem = Integer.parseInt(JOptionPane.showInputDialog("Which previously added item (enter ID) would you like to remove from the cart?\n"));
				Item itemRemove = arrStore[desiredItem-1];

				cart1.removeItem(itemRemove.getID(), arrStore);
			}
			if(choice==4){
				int desiredID = Integer.parseInt(JOptionPane.showInputDialog("What ID would you like to search for within your cart?"));
				cart1.searchForItem(desiredID);
			}




			choice = Integer.parseInt(JOptionPane.showInputDialog("Again, the choices are as follows:\n(1) view shopping list\n(2) add items\n(3) remove items\n(4) search for items\n(5) done shopping"));
		}

		System.out.println();
		System.out.println("Items in your cart (quantities not shown):"); //because qty is an instance of ShoppingCart, I did not print the quantity of each item.
		System.out.println(cart1.toString());
		System.out.print("Total Price w/5% tax  : $");
		System.out.printf("%.2f", cart1.getTotalCost());

		br.close();
	}

}
