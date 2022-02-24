package com.LeahGrace;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {
    static final int FACE_CARD = 10; //MOVE TO DECK
    static final int LOW_ACE = 1; //MOVE TO DECK
    static final int HIGH_ACE_DIFFERENTIAL = 10; //MOVE TO DECK
    public final int MAX_DEALT = 4;
    public Map<String, Integer> dealtCards = new HashMap<>();

    public final String[] CARDS_STRINGS = {
            "2 of Hearts", "3 of Hearts", "4 of Hearts", "5 of Hearts", "6 of Hearts", "7 of Hearts", "8 of Hearts", "9 of Hearts", "10 of Hearts", "Jack of Hearts", "Queen of Hearts", "King of Hearts", "Ace of Hearts",
            "2 of Diamonds", "3 of Diamonds", "4 of Diamonds", "5 of Diamonds", "6 of Diamonds", "7 of Diamonds", "8 of Diamonds", "9 of Diamonds", "10 of Diamonds", "Jack of Diamonds", "Queen of Diamonds", "King of Diamonds", "Ace of Diamonds",
            "2 of Clubs", "3 of Clubs", "4 of Clubs", "5 of Clubs", "6 of Clubs", "7 of Clubs", "8 of Clubs", "9 of Clubs", "10 of Clubs", "Jack of Clubs", "Queen of Clubs", "King of Clubs", "Ace of Clubs",
            "2 of Spades", "3 of Spades", "4 of Spades", "5 of Spades", "6 of Spades", "7 of Spades", "8 of Spades", "9 of Spades", "10 of Spades", "Jack of Spades", "Queen of Spades", "King of Spades", "Ace Spades",
    };

    public static int convertCardValue(String card){
        char value = card.charAt(0);
        int points = 0;
        switch (value) {
            case 'A' -> points = LOW_ACE;
            case '1', 'J', 'Q', 'K' -> points = FACE_CARD;
            case '2', '3', '4', '5', '6', '7', '8', '9' -> points = Character.getNumericValue(value);
        }
        return points;
    }

    public static int calculatePoints(Actor actor) {
        actor.cardValue = 0;
        for (String card : actor.cardsArrayList){
            actor.cardValue += convertCardValue(card);
        }
        if (actor.cardsArrayList.contains("Ace of Hearts") ||
                actor.cardsArrayList.contains("Ace of Diamonds") ||
                actor.cardsArrayList.contains("Ace of Clubs") ||
                actor.cardsArrayList.contains("Ace Spades") && actor.cardValue + HIGH_ACE_DIFFERENTIAL < 21){
            actor.cardValue += HIGH_ACE_DIFFERENTIAL;
        }
        //if cardsArrayList has an Ace in it and the cardValue + 10 is still less than 21
        //add the ten

        return actor.cardValue;
    }

    public String dealCard() {
       Random r = new Random();
       int randomNumber = r.nextInt(CARDS_STRINGS.length);
       String card = CARDS_STRINGS[randomNumber];
       if (dealtCards.containsKey(card)){
           if(dealtCards.get(card) == MAX_DEALT){
               return dealCard(); //if the card has been dealt more than four times, return a different card
           } else if (dealtCards.get(card) < MAX_DEALT){
               dealtCards.put(card, ((int)dealtCards.get(card) + 1)); //if the card has been dealt less than four times, increment the count in the map
           }
       } else{
           dealtCards.put(card, 1); //new cards are added to the HashMap with count of 1.
       }
       return card;
   }

   public void shuffleCards(){
       dealtCards.clear(); //clear the HashMap/count of cards
   }

}
