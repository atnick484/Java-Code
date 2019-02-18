
public class ShoppingCart {

	private int qty;
	private static double totalCost;
	private Item[] itemList;
	private static int itemCount = 0;

	public ShoppingCart(){
		itemList = new Item[5];
		qty = 0;
		totalCost = 0.0;

	}


	public void addItems(int iDIn, int qtyIn, Item[] arrStoreIn){
		itemCount++;
		qty = qtyIn;
		itemList[itemCount-1] = arrStoreIn[iDIn-1]; //figure out itemList index
		totalCost+=arrStoreIn[iDIn-1].getPrice()*qty;
		if(itemCount==5){
			System.out.println("Your shopping cart is full! Please either enter (5) to be done, or remove an item(3)!!");
		}
	}

	public String toString(){
		String returner="";
		for(int k=0; k<itemList.length; k++){
			if(itemList[k]!=null){
				returner+= "\tName: " + itemList[k].getName()+" Price: $" + itemList[k].getPrice() + " ID: " + itemList[k].getID() + "\n";
			}
			else{
				continue;
			}
		}
		return returner;
	}

	public int searchForItem(int iDIn){ //fix!
		int index = 0;
		for(int i = 0; i<5; i++){
			if(itemList[i]!=null){
				if(iDIn == itemList[i].getID()){
					index = i;
					break;
				}
			}
			else{
				System.out.println("Item not in cart.");
				return -1;
			}
		}
		System.out.println(itemList[index].toString());
		System.out.println("Item above found in cart!");
		return index;
	}

	public void removeItem(int iDIn, Item[] arrStoreIn){
		int deleteIndex = searchForItem(iDIn);
		if(deleteIndex!=-1){
			if(deleteIndex!=itemList.length-1){
				for(int j=deleteIndex+1;j<itemList.length;j++){
					itemList[j-1] = itemList[j];
				}

			}
			else{
				itemList[deleteIndex] = null;
			}
			System.out.println("Item removed from cart.");
		}
		else{
			System.out.println("Item not in your cart to begin with.");
		}
		totalCost-=arrStoreIn[iDIn-1].getPrice()*qty;
	}

	public double getTotalCost(){
		double subtotal = totalCost;
		return subtotal*1.05;
	}

	public Item[] getItemList(){
		return itemList;
	}


}
