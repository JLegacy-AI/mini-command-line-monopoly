public class Board {
    private Cell[][] cells = new Cell[10][10];

    public Board(){
        cells[0][0] = new Cell("Go Space",1,false,null, false);
        cells[0][1] = new Cell("White House",2,false,null, true);
        cells[0][2] = new Cell("Chicago Avenue",3, false,null, true);
        cells[0][3] = new Cell("Texas Avenue",4,false,null, true);
        cells[0][4] = new Cell("Utility",5,false,null, true);
        cells[0][5] = new Cell("College Avenue",6,false,null, true);
        cells[0][6] = new Cell("Burger King",7,false,null, true);
        cells[0][7] = new Cell("Nothing*",8,false,null, false);
        cells[0][8] = new Cell("Holiday Inn Hotel",9,false,null, true);
        cells[0][9] = new Cell("Go to Jail",10,false,null, false);
        cells[1][9] = new Cell("Roll Again*",11,false,null, false);
        cells[2][9] = new Cell("Blue Mosque",12,false,null, false);
        cells[3][9] = new Cell("Railroads",13,false,null, true);
        cells[4][9] = new Cell("Luxury Tax",14,false,null, false);
        cells[5][9] = new Cell("City Park",15,false,null, false);
        cells[6][9] = new Cell("Nothing",16,false,null, false);
        cells[7][9] = new Cell("Give Half Your Money",17,false,null, false);
        cells[8][9] = new Cell("Mosque-",18,false,null, false);
        cells[9][9] = new Cell("Free Parking",19,false,null, false);
        cells[9][8] = new Cell("New York Avenue",20,false,null, true);
        cells[9][7] = new Cell("Colorado Avenue",21,false,null, true);
        cells[9][6] = new Cell("Income TAx",22,false,null, false);
        cells[9][5] = new Cell("Marvin Garden",23,false,null, false);
        cells[9][4] = new Cell("Nothing-",24,false,null, false);
        cells[9][3] = new Cell("Red House",25,false,null, true);
        cells[9][2] = new Cell("Blue House",26,false,null, true);
        cells[9][1] = new Cell("Hilton Hotel",27,false,null, true);
        cells[9][0] = new Cell("In Jail",28,false,null, false);
        cells[8][0] = new Cell("Mosque*",29,false,null, false);
        cells[7][0] = new Cell("Swap",30,false,null, false);
        cells[6][0] = new Cell("Nothing",31,false,null, false);
        cells[5][0] = new Cell("Sheraton Hotel",32,false,null, true);
        cells[4][0] = new Cell("Yellow House",33,false,null, true);
        cells[3][0] = new Cell("Washington Avenue",34,false,null, true);
        cells[2][0] = new Cell("Roll Again-",35,false,null, false);
        cells[1][0] = new Cell("Mall of Arabia",36,false,null, true);

    }

    public Cell[] getCells() {
        int x=0;
        Cell[] cells = new Cell[36];
        for (int i = 0; i < 10; i++, x++) {
             cells[x]= this.cells[0][i];
        }
        for (int i = 1; i < 9; i++, x++) {
            cells[x]= this.cells[i][9];
        }
        for (int i = 9; i>=0 ; i--, x++) {
            cells[x]= this.cells[9][i];
        }
        for (int i = 8; i >=1 ; i--, x++) {
            cells[x]=this.cells[i][0];
        }
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int[] getLocation(Cell cell){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(this.cells[i][j]!=null && this.cells[i][j].getName().equalsIgnoreCase(cell.getName())){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public void print(){
        System.out.println("_".repeat(250));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(this.cells[i][j]!=null){
                    String name= this.cells[i][j].getName();
                    System.out.print(name+" ".repeat(25-name.length())+" -|- ");
                }else{
                    System.out.print(" ".repeat(30));
                }
            }
            System.out.println();
        }
        System.out.println("_".repeat(250));
    }
}
