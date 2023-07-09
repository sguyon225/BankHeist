package Bank;

import java.util.ArrayList;
import java.util.Collections;

public class Sim {
    public static ArrayList<Card> makeVault(){
        ArrayList<Card> vault=new ArrayList<>(40);

        int cash=0;
        while(cash<10){
            Card card=new Card();
            card.setType("Cash");
            vault.add(card);
            cash++;
        }
        int dye=0;
        while(dye<2){
            Card card=new Card();
            card.setType("Cash");
            vault.add(card);
            dye++;
        }
        int none=0;
        while(none<28){
            Card card=new Card();
            vault.add(card);
            none++;
        }

        Collections.shuffle(vault);
        return vault;
    }

    public static ArrayList<Card> makeAlarm(){
        ArrayList<Card> alarm=new ArrayList<>(25);
        for (int i=0; i<alarm.size(); i++) {
            Card card=new Card();
            alarm.set(i,card);
        }

        int moving=0;
        while(moving<3){
            Card card=new Card();
            card.setType("HOLD IT RIGHT THERE");
            alarm.add(card);
            moving++;

        }

        int hold=0;
        while(hold<2){
            Card card=new Card();
            card.setType("LET'S GET MOVING");
            alarm.add(card);
            hold++;
        }
        int take=0;
        while(take<2){
            Card card=new Card();
            card.setType("I'LL TAKE THAT");
            alarm.add(card);
            take++;
        }
        int none=0;
        while(none<20){
            Card card=new Card();
            alarm.add(card);
            none++;
        }

        Collections.shuffle(alarm);
        return alarm;
    }

    public static Card draw(ArrayList<Card> deck){
        Card card=deck.remove(0);

        return card;
    }

    // public static Player[] makeTable(int players){
    //     Player[] table=new Player[players];
    //     for (int i=0; i<table.length; i++) {
    //         Player player=new Player();
    //         table[i]=player;
    //     }

    //     int agents=0;
    //     while(agents<2){
    //         int spot=num.nextInt(table.length);
    //         if(table[spot].getRole()=="Crew"){
    //             table[spot].setRole("Agent");
    //             agents++;
    //         }
    //     }
        
    //     return table;
    // }

    public static void crew(ArrayList<Card> vault, ArrayList<Card> alarm){
        Card a=draw(alarm);
        // Card v=draw(vault);

        if(a.getType()=="LET'S GET MOVING"){

        }

    }

    public static void main(String[] args) {
        //If 5-6, 3 turns
        //If 7-8, 2 turns
        ArrayList<Card> vault=makeVault();
        ArrayList<Card> alarm=makeAlarm();
        // Player[] table=makeTable(5);

        // for (Player player : table) {
        //     System.out.println(player.getRole());
        // }System.out.println("\n\n VAULT");
        for (Card card : vault) {
            System.out.println(card.getType());
        }System.out.println("\n\n ALARM");
        for (Card card : alarm) {
            System.out.println(card.getType());
        }
    }
}
