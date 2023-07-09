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
            card.setType("Dye");
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

    public static ArrayList<Player> makeTable(int players){
        ArrayList<Player> table=new ArrayList<Player>(players);
        for (int i=0; i<players; i++) {
            Player player=new Player();
            table.add(player);
        }

        table.get(0).setRole("Agent");
        table.get(1).setRole("Agent");
        
        Collections.shuffle(table);
        return table;
    }

    public static void crewTurn(ArrayList<Card> vault, ArrayList<Card> alarm, ArrayList<Player> table, Van van){
        Card a=draw(alarm);
        Card v=null;
        Card e=null;

        if(a.getType()=="LET'S GET MOVING"){
            boolean cash=false;
            int i=0;
            while(cash=false){
                Card card=vault.get(i);
                if(card.getType()=="Cash"){
                    vault.remove(i);
                    van.addBag();
                    cash=true;
                }
                i++;
            }
        }else if(a.getType()=="HOLD IT RIGHT THERE"){
            int bags=0;
            int holder=0;
            int i=0;
            for (Player player : table) {
                if(player.getRole()=="Agent"){
                    if(player.getBags()>bags){
                        bags=player.getBags();
                        holder=i;
                    }
                    
                }
                i++;
            }
            if(bags>0){
                for (int j=0; j<bags; j++) {
                    van.addBag();
                }
                table.get(holder).clearBags();
            }
            v=draw(vault);

        }else if(a.getType()=="I'LL TAKE THAT"){
            v=draw(vault);
            e=draw(vault);
        }else{
            v=draw(vault);
        }

        if(v!=null&&v.getType()=="Cash"){
            van.addBag();
        }
        if(e!=null&&e.getType()=="Cash"){
            van.addBag();
        }
    }
    public static void agentTurn(ArrayList<Card> vault, ArrayList<Card> alarm, Player player, Player other, Van van){
        Card a=draw(alarm);
        Card v=null;
        Card e=null;

        if(a.getType()=="LET'S GET MOVING"){
            boolean cash=false;
            int i=0;
            while(cash=false){
                Card card=vault.get(i);
                if(card.getType()=="Cash"){
                    vault.remove(i);
                    cash=true;
                }
                i++;
            }
        }else if(a.getType()=="I'LL TAKE THAT"){
            v=draw(vault);
            e=draw(vault);
        }else{
            v=draw(vault);
        }

        if(v!=null&&v.getType()=="Cash"){
            player.addBag();
        }else if(v!=null&&v.getType()=="Dye"){
            other.removeBag();
        }
        if(e!=null&&e.getType()=="Cash"){
            player.addBag();
        }else if(e!=null&&e.getType()=="Dye"){
            other.removeBag();
        }
    }

    public static void main(String[] args) {
        //If 5-6, 3 turns
        //If 7-8, 2 turns
        ArrayList<Card> vault=makeVault();
        ArrayList<Card> alarm=makeAlarm();
        ArrayList<Player> table=makeTable(5);
        Van van=new Van();

        crewTurn(vault, alarm, table, van);
        System.out.println(van.getBags());
    }
}
