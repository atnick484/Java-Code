
public class Item {
	private int iD;
	private double price;
	private String name;
	
	
	
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	} 

	public void setName(String name) {
		this.name = name;
	}

	
	public Item(int iDIn, double priceIn, String nameIn){
		iD = iDIn;
		price = priceIn;
		name = nameIn;
	}
	
	public String toString(){
		return "ID: " + iD + ", Price: $" + price + ", Name: " + name;
	}
	
	public int getID(){
		return iD;
	}
	

}
