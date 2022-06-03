public class Cell {
    private String name;
    private int number;
    private boolean sellable;
    private boolean sold=false;
    private Player owner;

    public Cell(){}

    public Cell(String name, int number, boolean sold, Player owner) {
        this.name = name;
        this.number = number;
        this.sold = sold;
        this.owner = owner;
    }


    public Cell(String name, int number, boolean sold, Player owner, boolean sellable) {
        this.name = name;
        this.number = number;
        this.sellable = sellable;
        this.sold = sold;
        this.owner = owner;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Cell:\n" +
                "Name:          "+ name + '\n' +
                "Number:        "+ number+'\n' +
                "Owner:         "+ owner;
    }
}
