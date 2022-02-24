package com.LeahGrace;

import java.util.ArrayList;

public abstract class Actor {
    private String name;
    public ArrayList<String> cardsArrayList;
    public int cardValue; //MOVE TO DECK

    public  Actor(String name){
        this.name = name;
        this.cardsArrayList = new ArrayList<String>();
        this.cardValue = 0;
    }

    public String revealCards(){
        String cardString = " ";
        for (String card : cardsArrayList){
                cardString += ("the " + card + " ");
        }
        return cardString;
    }

    public String getName() {
        return name;
    }

    public int getCardValue() { return cardValue; }
}
