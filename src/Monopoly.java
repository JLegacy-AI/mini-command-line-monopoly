import java.util.Scanner;

public class Monopoly{

	private Scanner scan = new Scanner(System.in);
	private Player[] players;
	private Board board =new Board();
	private Player plyCur;
	private boolean turn=false;
	private int playersTurn=0;
	private Bank bank= new Bank();

	public void startGame(){
		players= new Player[getPlayer()];
		getPlayersName(players,players.length);
		gameMenu();
		playerStartMenu();

	}

	public int getPlayer(){
		while(true){
			System.out.println("_".repeat(30));
			System.out.println("How Many Players?");
			int n=-1;
			try{
				n = scan.nextInt();
			}catch (Exception e){
				System.out.println("Please Enter Numbers Only:");
				continue;
			}
			if(2>n || n>6){
				System.out.println("Player Can Be 2-6:");
			}else {
				return n;
			}
			System.out.println("_".repeat(30));
		}
	}

	public void getPlayersName(Player[] players , int x){
		for (int i = 0; i <x; i++) {
			System.out.println("_".repeat(30));
			do {
				System.out.println("Enter Player-" + (i + 1) + "'s name:");
				players[i] = new Player();
				addPlayersToInitialLocation(players[i]);
				players[i].setName(scan.next());
				System.out.println("_".repeat(30));
			} while (players[i].getName().isEmpty() || players[i].getName().length() > 8);
		}
	}

	public void gameMenu(){
		while(true){
			System.out.println("_".repeat(30));
			System.out.println("(1)->Select Player to Play (2)->Print the Board (3)->End Game");
			int op = scan.nextInt();
			switch (op) {
				case 1 -> {
					selectPlayer();
					return;
				}
				case 2 -> board.print();
				case 3 -> {
					System.out.println("_".repeat(30));
					System.out.println("Game End !!!");
					System.exit(0);
				}
				default -> System.out.println("Wrong Input Try Again :(");
			}
		}
	}

	public void selectPlayer(){
		while (true){
			System.out.println("_".repeat(30));
			System.out.println("Select Player :)");
			for (int i=0; i<players.length; i++) {
				System.out.println((i+1)+") Player "+players[i].getName());
			}
			int op = scan.nextInt();
			if(op>0 && op<=players.length){
				System.out.println(players[--op]);
				plyCur = players[op];
				playersTurn=op;
				break;
			}
			System.out.println("_".repeat(30));
		}
	}

	public void printAllPlayers(){
		for (Player player : players) {
			System.out.println("_".repeat(30));
			System.out.println(player);
			System.out.println("_".repeat(30));
		}
	}

	public void exit(){
		System.out.println("_".repeat(30));
			System.out.println("Do you Really Want to End Game? (Y/N)");
			char op = scan.next().charAt(0);
			if(op=='Y' || op=='y'){
				System.exit(0);
			}
		System.out.println("_".repeat(30));
	}

	public void playerStartMenu(){
		while(true){
			System.out.println("_".repeat(30));
			System.out.println("1)Roll-Dice 	2)Print-Board	3)End-Game ");
			int op = scan.nextInt();
			if(op==1){
				int gotNumber = diceRoll();
				int prevLocation = plyCur.getCurrentLocation().getNumber();
				Cell newLocation = board.getCells()[(prevLocation+gotNumber-1)%36];
				int[] locationXY = board.getLocation(newLocation);
				addMoneyToAccount(prevLocation, newLocation.getNumber(), newLocation);
				plyCur.setCurrentLocation(newLocation);
				System.out.println("_".repeat(30));
				System.out.println("You Got: "+gotNumber);
				System.out.println("New-Cell-Location:");
				System.out.println("Cell: ("+locationXY[0]+" , "+locationXY[1]+" )");
				System.out.println("Cell-Name: "+newLocation.getName());
				System.out.println("Cell-Owner: "+newLocation.getOwner());
				System.out.println("_".repeat(30));
				actionsPlaces(newLocation);
				System.out.println("_".repeat(30));
				System.out.println(plyCur);
				System.out.println("_".repeat(30));
				if(plyCur.getMoney()<0){
					System.out.println("Game Over");
					printAllPlayers();
					System.exit(0);
				}
				if(turn && !newLocation.getName().equalsIgnoreCase("Roll Again")){
					playersTurn++;
					plyCur = players[playersTurn%players.length];
					System.out.println(plyCur);
					System.out.println("_".repeat(30));
				}
				plyCur.setArrest(false);
			}else if(op==2){
				board.print();
			}else{
				printAllPlayers();
				exit();
			}
		}
	}

	public int diceRoll(){
		int x =(int) (Math.random() * 17)%6 + 1;
		int y =(int) (Math.random() * 19)%6 + 1;
		if(x==y){
			System.out.println("Roll Again:");
			turn=false;
			return x+y;
		}
		turn= true;
		return x+y;
	}



	public void addPlayersToInitialLocation(Player ply){
		ply.setCurrentLocation(board.getCells()[0]);
	}

	public void actionsPlaces(Cell cell){


		//if There is any Owner
		if(cell.getOwner()!=null){
			//if current Player is Owner
			if(cell.getOwner()==plyCur){
				System.out.println("You are Owner of This Cell:-> So Nothing happen");
				return;
			}
			//if other player is Owner ot that Cell
			//if Utillity and RailRoads
			if(cell.getName().equalsIgnoreCase("Utility") || cell.getName().equalsIgnoreCase("railRoads") ){
				plyCur.setMoney(plyCur.getMoney()-50);
				bank.add(50);
				return;
			}
			//if other property
			plyCur.setMoney(plyCur.getMoney()-200);
			bank.add(200);
			return;
		}

		if(!cell.isSellable()){
			System.out.println("You Cannot Buy This House");
		}

		//if cell is sellable
		//then give him options
		if(cell.isSellable()){
			if(!buyProperty(cell)){
				System.out.println("You Have to Pay Rent of this Place:");
				int money = plyCur.getMoney()-200;
				plyCur.setMoney(money);
				bank.add(200);
			}else{
				return;
			}
		}



		if(cell.getNumber()==1){
			if(plyCur.isArrest()){
			}else{
				plyCur.setMoney(plyCur.getMoney()+200);
				bank.remove(200);
			}
		}if(cell.getNumber()==2){

		}if(cell.getNumber()==3){

		}if(cell.getNumber()==4){

		}if(cell.getNumber()==5){

		}if(cell.getNumber()==6){

		}if(cell.getNumber()==7){

		}if(cell.getNumber()==8){

		}if(cell.getNumber()==9){

		}if(cell.getNumber()==10){
			System.out.println("Oops! You are Arrested :(");
			int money = (int)(plyCur.getMoney()*0.1f);
			bank.add(money);
			payMoney(plyCur,0.1f, false);
			plyCur.setArrest(true);
		}if(cell.getNumber()==11){
			System.out.println("Roll Again");
		}if(cell.getNumber()==12){
			plyCur.setMoney(plyCur.getMoney()-5);
		}if(cell.getNumber()==13){

		}if(cell.getNumber()==14){
			payMoney(plyCur, (float) 1/12, true);
		}if(cell.getNumber()==15){
			int money = plyCur.getMoney();
			plyCur.setMoney(money-20);
			bank.add(20);
		}if(cell.getNumber()==16){

		}if(cell.getNumber()==17){
			System.out.println("Give Your Half of Money");
			int halfMoney = plyCur.getMoney()/2;
			plyCur.setMoney(halfMoney);
			if(players.length==1){
				bank.add(halfMoney);
			}else{
				int equalParts = halfMoney/(players.length-1);
				for (Player player : players) {
					if(player!=plyCur){
						player.setMoney(player.getMoney()+equalParts);
					}
				}
			}
		}if(cell.getNumber()==18){
			plyCur.setMoney(plyCur.getMoney()-5);
			System.out.println("Mosque");
		}if(cell.getNumber()==19){
			System.out.println("Free Parking No Charges:");
		}if(cell.getNumber()==20){

		}if(cell.getNumber()==21){

		}if(cell.getNumber()==22){
			payMoney(plyCur, (float) 1/7, true);
		}if(cell.getNumber()==23){

		}if(cell.getNumber()==24){

		}if(cell.getNumber()==25){

		}if(cell.getNumber()==26){

		}if(cell.getNumber()==27){

		}if(cell.getNumber()==28){

		}if(cell.getNumber()==29){
			System.out.println("Mosque");
			plyCur.setMoney(plyCur.getMoney()-5);
		}if(cell.getNumber()==30){
			System.out.println("Swap Function Perform");
			swapProperty();
		}if(cell.getNumber()==31){

		}if(cell.getNumber()==32){

		}if(cell.getNumber()==33){

		}if(cell.getNumber()==34){

		}if(cell.getNumber()==35){
			System.out.println("Roll Again");
		}if(cell.getNumber()==36){

		}
	}

	public  void payMoney(Player ply, float pct, boolean bankPay){
		float amountPercent = ply.getMoney()*pct;
		ply.setMoney((int)(ply.getMoney()-amountPercent));
		if(bankPay){
			bank.add((int) amountPercent);
		}
	}

	public boolean buyProperty(Cell cell){
		while(true){
			System.out.println("Do you Want to Buy "+cell.getName()+"? Y/N");
			char op = scan.next().charAt(0);
			if(op=='Y' || op=='y'){
				cell.setOwner(plyCur);
				plyCur.addProperty(cell);
				if(cell.getName().equalsIgnoreCase("Utility") || cell.getName().equalsIgnoreCase("Railroads") ){
					plyCur.setMoney(plyCur.getMoney()-100);
					bank.add(100);
					return true;
				}else{
					plyCur.setMoney(plyCur.getMoney()-300);
					bank.add(300);
					return true;
				}
			}else if(op=='n' || op=='N'){
				return false;
			}
		}
	}

	public void swapProperty(){
		while(true){
			System.out.println("Lists of Property Owned By "+plyCur.getName()+": ");
			if(plyCur.getOwner().size()>0 && plyCur.getOwner()!=null){
				for (int i = 0; i < plyCur.getOwner().size(); i++) {
					System.out.println((i+1)+") "+plyCur.getOwner().get(i).getName());
				}
				int op = scan.nextInt();
				if(1>op || op>plyCur.getOwner().size())
					continue;
				Cell cell = plyCur.getOwner().get(op-1);
				plyCur.getOwner().remove(cell);
				Player nextPlayer =  players[(playersTurn+1)%players.length];
				nextPlayer.addProperty(cell);
				return;
			}
			else{
				System.out.println("Oops: You have No property to Swap");
			}
		}
	}

	public void addMoneyToAccount(int x, int y, Cell newLocation){
		if(y-x<12 && y-x>0 && !newLocation.getName().contains("Jail")){
			plyCur.setMoney(plyCur.getMoney()+200);
			bank.remove(200);
		}
	}
}