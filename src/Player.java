import java.util.ArrayList;


public class Player {
	private String name;
	private int money =1500;
	private Cell currentLocation;
	private ArrayList<Cell> owner;
	private boolean arrest=false;

	Player(){
		currentLocation = new Cell();
		owner = new ArrayList<>();
	}

	public boolean isArrest() {
		return arrest;
	}

	public void setArrest(boolean arrest) {
		this.arrest = arrest;
	}

	public ArrayList<Cell> getOwner() {
		return owner;
	}

	public void setOwner(ArrayList<Cell> owner) {
		this.owner = owner;
	}

	public Cell getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Cell currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void addProperty(Cell cell){
		owner.add(cell);
	}

	public String properties(){
		String owner="";
		for (Cell cell : this.owner) {
			owner+=cell.getName()+" ,";
		}
		return owner;
	}

	@Override
	public String toString() {
		return "Player: \n"+
				"Name:				" + name + '\n' +
				"Money:				" + money +'\n'+
				"Properties:		" + properties() +'\n'+
				"Cell-Name:			" + (currentLocation!=null?currentLocation.getName():"NULL")+'\n'+
				"Cell-Position:		" + (currentLocation!=null?""+currentLocation.getNumber():"-1")+'\n'+
				"Bank-Balance:		" + Bank.bankBalance;
	}
}
	
	
