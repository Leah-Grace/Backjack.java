package com.LeahGrace;

import java.util.ArrayList;

public abstract class Actor {
    private static final int FACE_CARD = 10; //MOVE TO DECK
    private static final int LOW_ACE = 1; //MOVE TO DECK
    private static final int HIGH_ACE_DIFFERENTIAL = 10; //MOVE TO DECK
    private String name;
    public ArrayList<String> cardsArrayList;
    public int cardValue; //MOVE TO DECK

    public  Actor(String name, ArrayList<String> cardsArrayList, int cardValue){
        this.name = name;
        this.cardsArrayList = cardsArrayList;
        this.cardValue = cardValue;
    }

    public String revealCards(){
        String cardString = " ";
        for (String card : cardsArrayList){
                cardString += ("the " + card + " ");
        }
        return cardString;
    }

    public int convertCardValue(String card){
        char value = card.charAt(0);
        int points = 0;
        switch (value) {
            case 'A' -> points = LOW_ACE;
            case '1', 'J', 'Q', 'K' -> points = FACE_CARD;
            case '2', '3', '4', '5', '6', '7', '8', '9' -> points = Character.getNumericValue(value);
        }
        return points;
    }

    public int calculatePoints() {
        this.cardValue = 0;
        for (String card : cardsArrayList){
            this.cardValue += convertCardValue(card);
        }
        if (cardsArrayList.contains("Ace of Hearts") ||
                cardsArrayList.contains("Ace of Diamonds") ||
                cardsArrayList.contains("Ace of Clubs") ||
                cardsArrayList.contains("Ace Spades") && this.cardValue + HIGH_ACE_DIFFERENTIAL < 21){
            this.cardValue += HIGH_ACE_DIFFERENTIAL;
        }
        //if cardsArrayList has an Ace in it and the cardValue + 10 is still less than 21
        //add the ten

        return cardValue;
    }

    public String getName() {
        return name;
    }

    public int getCardValue() { return cardValue; }
}
