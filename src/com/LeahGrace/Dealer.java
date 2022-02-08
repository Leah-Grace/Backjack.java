package com.LeahGrace;

import java.util.ArrayList;

public class Dealer extends Actor implements Table{
    private String disposition;

    public Dealer(String name) {
        super(name);
        this.disposition = "";
    }


    @Override
    public void roundOne(String firstCard, String secondCard) {
        System.out.println("The dealer reveals they have been dealt the " + firstCard);
        this.cardsArrayList.add(firstCard);
        this.cardsArrayList.add(secondCard);
        calculatePoints();
        setDisposition();
        System.out.println("Side note: The dealer has " + getCardValue() + " and chooses to " + this.disposition);

    }

    @Override
    public void stand() {
        System.out.println("The dealer reveals their hand:" + revealCards());
        System.out.println("The dealer has earned " + this.cardValue + " points");
    }

    public void hit(String card) {
        System.out.println("The dealer chooses to " + this.disposition);
        System.out.println("The dealer reveals they have been dealt the " + card);
        this.cardsArrayList.add(card);
        this.cardValue = calculatePoints();
        setDisposition();
        if (this.cardsArrayList.size() == 5 && this.cardValue <= 21){
            System.out.println("The Dealer has five card all under 21 so they win");
            this.disposition = "Stand";
        } else if (this.cardValue <= 21) {
            setDisposition();
            System.out.println("Side note: The dealer has " + getCardValue() + " and chooses to " + this.disposition);
        } else if (this.cardValue > 21){
            System.out.println("The " + this.getName() + " has gone over");
        }
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition() {
        int value = this.getCardValue();
        if (value <= 16){
            this.disposition = "Hit";
        } else {
            this.disposition = "Stand";
        }
    }

}
