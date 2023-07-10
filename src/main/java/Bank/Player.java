package Bank;

public class Player {
    private String role;
    private int bags=0;
    private int cards=0;

    public Player(){
        this.role="Crew";
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int cards(){
        return cards;
    }

    public int getBags() {
        return bags;
    }
    public void addBag() {
        this.bags+=1;
    }
    public void removeBag(){
        if(bags>0){
            this.bags-=1;
        }
    }
    public void clearBags(){
        this.bags=0;
    }

    public int getCards() {
        return cards;
    }
    public void addCard() {
        this.cards+=1;
    }
    public void removeCard(){
        if(cards>0){
            this.cards-=1;
        }   
    }    
}
