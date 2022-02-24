package com.LeahGrace;

import java.util.ArrayList;

public class Player extends Actor implements Table{
    private static final int BLINDS = 100;
    private int cashOnHand;
    private int winnings;

    public Player(String name){
        super(name);
        this.cashOnHand = setCashOnHand();
    }

    public void reset(){
        this.cardsArrayList = new ArrayList<String>();
        this.cardValue = 0;
    }

    public void cashLeft() {
        System.out.println("You're down to $" + this.cashOnHand + " in cash");

    }

    public void payBlinds(){
        this.cashOnHand -= BLINDS;
    }

    @Override
    public void roundOne(String firstCard, String secondCard) {
        payBlinds();  //subtract the $100 blinds from player's cashOnHand
        cashLeft();  //print how much $$ is left
        //get two cards/print to console
        System.out.println("You have been dealt the " + firstCard + " and the " + secondCard);
        this.cardsArrayList.add(firstCard);
        this.cardsArrayList.add(secondCard);
        this.cardValue = Deck.calculatePoints(this); //convert cards to points
        //print points to the console
        System.out.println("You have earned " + cardValue + " points");
        if (cardValue == 21){
            System.out.println("Congratulations " + this.getName() + ", you've won the game. You leave the table with $" + winnings());
        }
    }

    @Override
    public void stand() {
        System.out.println("You reveal your hand:" + revealCards());
        System.out.println("You have earned " + this.cardValue);
    }

    @Override
    public void hit(String card) {
        System.out.println("The dealer deals you the " + card);
        this.cardsArrayList.add(card);
        Deck.calculatePoints(this);
        System.out.println("you now have " + this.cardValue);

        if (this.cardsArrayList.size() == 5 && this.cardValue < 21){
            System.out.println("You have five cards with a value under 21, you win!");
        }
    }

    public int setCashOnHand() {
        return (int)(Math.ceil(Math.random() * 10) * BLINDS);
    } //You cash on hand to bet with: A random number from 1 to 10 and multiplied by the blinds.

    public void setCashOnHand(int bet) {
        this.cashOnHand -= bet;
    }

    private int winnings() {
       cashOnHand += (BLINDS * 1.5);
        return cashOnHand;
    }

    public int getCashOnHand() {
        return cashOnHand;
    }

}
