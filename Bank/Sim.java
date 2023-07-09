package Bank;

import java.util.Random;

public class Sim {
    static int VAULT=0;
    static int ALARM=0;
    static Random num=new Random();

    public static Card[] makeVault(){
        Card[] vault=new Card[40];
        for (int i=0; i<vault.length; i++) {
            Card card=new Card();
            vault[i]=card;
        }

        int cash=0;
        while(cash<10){
            int spot=num.nextInt(40);
            if(vault[spot].getCard()=="None"){
                vault[spot].setCard("Cash");
                cash++;
            }
        }
        int dye=0;
        while(dye<2){
            int spot=num.nextInt(40);
            if(vault[spot].getCard()=="None"){
                vault[spot].setCard("Dye");
                dye++;
            }
        }
        return vault;
    }

    public static Card[] makeAlarm(){
        Card[] alarm=new Card[25];
        for (int i=0; i<alarm.length; i++) {
            Card card=new Card();
            alarm[i]=card;
        }

        int moving=0;
        while(moving<3){
            int spot=num.nextInt(25);
            if(alarm[spot].getCard()=="None"){
                alarm[spot].setCard("LET'S GET MOVING");
                moving++;
            }
        }

        int hold=0;
        while(hold<2){
            int spot=num.nextInt(25);
            if(alarm[spot].getCard()=="None"){
                alarm[spot].setCard("HOLD IT RIGHT THERE");
                hold++;
            }
        }

        int take=0;
        while(take<2){
            int spot=num.nextInt(25);
            if(alarm[spot].getCard()=="None"){
                alarm[spot].setCard("I'LL TAKE THAT");
                take++;
            }
        }

        return alarm;
    }

    public static Card draw(Card[] deck){
        Card card=new Card();
        if(deck.length==40){
            for (int i=0; i<deck.length; i++) {
                if(i==VAULT){
                    card=deck[i];
                }
            }
        }else{
            for (int i=0; i<deck.length; i++) {
                if(i==ALARM){
                    card=deck[i];
                }
            }
        }
        return card;
    }

    public static Player[] makeTable(int players){
        Player[] table=new Player[players];
        for (int i=0; i<table.length; i++) {
            Player player=new Player();
            table[i]=player;
        }

        int agents=0;
        while(agents<2){
            int spot=num.nextInt(table.length);
            if(table[spot].getRole()=="Crew"){
                table[spot].setRole("Agent");
                agents++;
            }
        }

        return table;
    }

    public static void main(String[] args) {
        //If 5-6, 3 turns
        //If 7-8, 2 turns
        Card[] vault=makeVault();
        Card[] alarm=makeAlarm();
        Player[] table=makeTable(5);
        
    }
}
