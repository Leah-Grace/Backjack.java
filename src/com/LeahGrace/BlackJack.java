package com.LeahGrace;

public class BlackJack {
    private static final int BLINDS = 100;

    private static final int BLACKJACK = 21;
    private int playerBet = 0;
    final private double ODDS = 1.5;

    public void greeting(int cash){
        System.out.println("Welcome to BlackJack \n Blinds are $100 and the game plays 3 to 2");
        System.out.println("You have $" + cash + " in cash. Would you like to play? \n Enter Y or N");

    }

    public void pass(int cash) {
        System.out.println("You leave the table with $" + cash);
        System.exit(0);
    }

    public void roundOne() {
        System.out.println("The dealer shuffles the deck and deals two cards to you and two to themselves");
        this.playerBet += BLINDS;
        System.out.println("The player has wagered $" + getPlayerBet());
    }

    public void nextRoundProposition(){
        System.out.println("Will you hit, stand or double down?");
    }

    public String whoWins(int playerPoints, int dealerPoints){
        String determination = "";
        if (playerPoints == BLACKJACK){
            determination = "Congratulations, you win";
            this.playerBet *= ODDS;
        } else if (playerPoints > BLACKJACK && dealerPoints > BLACKJACK) {
            determination = "Both players are over";
            this.playerBet = 0;
        } else if (playerPoints > BLACKJACK){
            determination = "You are over. The dealer wins";
            this.playerBet = 0;
        } else if (dealerPoints > BLACKJACK && playerPoints <= BLACKJACK){
            determination = "Thr dealer is over";
            this.playerBet *= ODDS;
        } else if (playerPoints > dealerPoints){
            determination = "Congratulations, you win.";
            this.playerBet *= ODDS;
        } else if (dealerPoints > playerPoints){
            determination = "Sorry, you lose";
            this.playerBet = 0;
        } else if (playerPoints == dealerPoints){
            determination = "Its a draw!";
        }
        return determination;
    }

    public int getPlayerBet() {
        return playerBet;
    }

    public void setPlayerBet(int playerBet) {
        this.playerBet += playerBet;
    }
}
