package Bank;

public class Player {
    private String role;
    private int bags=0;

    public Player(){
        this.role="Crew";
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getBags() {
        return bags;
    }
    public void addBag() {
        this.bags+=1;
    }

    
}
