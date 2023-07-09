package Bank;

import java.util.Random;

public class Sim {
    public static Card[] makeVault(){
        Card[] vault=new Card[40];
        for (int i=0; i<vault.length; i++) {
            Card card=new Card();
            vault[i]=card;
        }

        Random num=new Random();

        int cash=0;
        while(cash<10){
            int spot=num.nextInt(40);
            if(vault[spot].getName()=="None"){
                vault[spot].setName("Cash");
                cash++;
            }
        }
        int dye=0;
        while(dye<2){
            int spot=num.nextInt(40);
            if(vault[spot].getName()=="None"){
                vault[spot].setName("Dye");
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

        Random num=new Random();

        int moving=0;
        while(moving<3){
            int spot=num.nextInt(25);
            if(alarm[spot].getName()=="None"){
                alarm[spot].setName("LET'S GET MOVING");
                moving++;
            }
        }

        int hold=0;
        while(hold<2){
            int spot=num.nextInt(25);
            if(alarm[spot].getName()=="None"){
                alarm[spot].setName("HOLD IT RIGHT THERE");
                hold++;
            }
        }

        int take=0;
        while(take<2){
            int spot=num.nextInt(25);
            if(alarm[spot].getName()=="None"){
                alarm[spot].setName("I'LL TAKE THAT");
                take++;
            }
        }

        return alarm;
    }

    public static void main(String[] args) {
        //If 5-6, 3 turns
        //If 7-8, 2 turns
        Card[] vault=makeVault();
        for (Card card : vault) {
            System.out.println(card.getName());
        }
        Card[] alarm=makeAlarm();
        for (Card card : alarm) {
            System.out.println(card.getName());
        }
    }
}
