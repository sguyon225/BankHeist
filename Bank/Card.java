package Bank;

public class Card {
    private String name;

    public Card(){
        this.name="None";
    }

    public String getType() {
        return name;
    }
    public void setType(String name) {
        this.name = name;
    }
}
