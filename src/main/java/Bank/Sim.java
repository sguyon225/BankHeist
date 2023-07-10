package Bank;

import java.util.ArrayList;
import java.util.Collections;
/*
 * Not Accounted for:
 *      Guilty Conscience attribute adding another agent + if it's taken away
 *      guilty will give all cash to cops
 *      need attribute slot on players
 */
public class Sim {
    static int CASH=10;

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

        int moving=0;
        while(moving<3){
            Card card=new Card();
            card.setType("LET'S GET MOVING");
            alarm.add(card);
            moving++;
        }
        int hold=0;
        while(hold<2){
            Card card=new Card();
            card.setType("HOLD IT RIGHT THERE");
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
        while(none<18){
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
            if(CASH>0){
                boolean cash=false;
                int i=0;
                while(cash==false){
                    Card card=vault.get(i);
                    if(card.getType()=="Cash"){
                        vault.remove(i);
                        van.addBag();
                        CASH--;
                        cash=true;
                    }
                    i++;
                }
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
                    CASH--;
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
            CASH--;
        }
        if(e!=null&&e.getType()=="Cash"){
            van.addBag();
            CASH--;
        }
    }
    public static void agentTurn(ArrayList<Card> vault, ArrayList<Card> alarm, Player player, Player other, Van van){
        Card a=draw(alarm);
        Card v=null;
        Card e=null;

        if(a.getType()=="LET'S GET MOVING"){
            if(CASH>0){
                boolean cash=false;
                int i=0;
                while(cash==false){
                    Card card=vault.get(i);
                    if(card.getType()=="Cash"){
                        vault.remove(i);
                        other.addBag();
                        CASH--;
                        cash=true;
                    }
                    i++;
                }
            }
        }else if(a.getType()=="HOLD IT RIGHT THERE"){
            if(player.cards()==0&&player.getBags()>0){
                player.removeBag();
                other.addBag();
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
            if(other.getBags()>0){
                other.removeBag();
            }else{
                if(player.getCards()>0){
                    player.removeCard();
                    other.addCard();
                }else if(player.getBags()>0){
                    player.removeBag();
                    other.addBag();
                }
            }            
        }
        if(e!=null&&e.getType()=="Cash"){
            player.addBag();
        }else if(e!=null&&e.getType()=="Dye"){
            other.removeBag();
        }
    }

    public static void takeTurn(Player player, ArrayList<Card> vault, ArrayList<Card> alarm, ArrayList<Player> table, Van van, int spot){
        if(player.getRole()=="Crew"){
            crewTurn(vault, alarm, table, van);
        }else{
            Player other=table.get(spot);
            for(int i=0; i<table.size(); i++){
                if(i!=spot&&table.get(i).getRole()=="Agent"){
                    other=table.get(i);
                }
            }
            agentTurn(vault, alarm, player, other, van);
        }
    }
    public static void main(String[] args) {
        int players=5;
        int turns=0;
        float tests=1000000;
        int i=1;
        int wins=0;
        int bags=0;
        int max=0;
        
        while(i<=tests){
            ArrayList<Card> vault=makeVault();
            ArrayList<Card> alarm=makeAlarm();
            ArrayList<Player> table=makeTable(players);
            Van van=new Van();

            if(players==5||players==6){
                turns=3;
            }else if(players==7||players==8){
                turns=2;
            }
            for(int j=0; j<turns; j++){
                int spot=0;
                for (Player player : table) {
                    takeTurn(player, vault, alarm, table, van, spot);
                    spot++;
                }
            }
            if(van.getBags()>=5){
                wins++;
            }
            if(van.getBags()==10){
                max++;
            }
            bags+=van.getBags();
            System.out.println(van.getBags()+" bags in van");
            i++;
        }
        float percent=(wins/tests)*100;
        float avg=(bags/tests);

        System.out.println("\n\nPlayers: "+players+"\nGames Played: 10,000,000");
        System.out.println("\nCrew won "+wins+" times ("+percent+"%).");
        System.out.println(""+bags+" bags in van ("+avg+" bags/game) with all 10 bags "+max+" times.");
    }
}
