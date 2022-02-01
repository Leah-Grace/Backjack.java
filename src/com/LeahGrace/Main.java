package com.LeahGrace;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//INSTANTIATIONS
        var game = new BlackJack();
        var deck = new Deck();
        var dealer = new Dealer("Dealer", new ArrayList<>(), 0);
        var player = new Player("New guy", new ArrayList<>(), 0, 0);
        player.setCashOnHand(); //use a random number to allocate $$ to the player
//GREET PLAYER
        game.greeting(player.getCashOnHand()); //Greet the player and let them know how much $$ they have to bet with
//DECISION TO JOIN
        Scanner scan = new Scanner(System.in);
        String joinGame = scan.next();

        if (!joinGame.equalsIgnoreCase("Y")) { // if the player does not join the game
            game.pass(player.getCashOnHand()); //end the game
        }
//START ROUND ONE
        game.roundOne();
            player.roundOne(deck.dealCard(), deck.dealCard());
            dealer.roundOne(deck.dealCard(), deck.dealCard());
//START ROUND TWO
        game.nextRoundProposition();
            char roundTwoMove = scan.next().charAt(0);

//DOUBLE DOWN
        if (String.valueOf(roundTwoMove).equalsIgnoreCase("D")){
            if (!(player.getCashOnHand() > 0)){
                System.out.println("Sorry, you don't have enough cash to double down");
            } else {
                System.out.println("You choose to double down, with $" + player.getCashOnHand() + " cash on hand. How much will you wager?");
                int raise = scan.nextInt();
                if (raise > player.getCashOnHand()){
                    System.out.println("Nice try " + player.getName());
                } else{
                    game.setPlayerBet(raise);
                    player.setCashOnHand(raise);
                    System.out.println("The player now wagers $" + game.getPlayerBet());
                    System.out.println("The player is down to $" + player.getCashOnHand());
                }
            }
            roundTwoMove = 'H';
        }

//YOU STAND
        if(String.valueOf(roundTwoMove).equalsIgnoreCase("S")){
            System.out.println("You choose to stand");
        //THE DEALER TAKES THEIR TURN
            while (!dealer.getDisposition().equals("Stand")){
                dealer.hit(deck.dealCard());
            }
        //BOTH PLAYERS STAND
            player.stand();
            dealer.stand();
        //EVALUATE WINNERS
            System.out.println(game.whoWins(player.cardValue, dealer.cardValue));
        //CALCULATE LOSS/GAIN
            game.pass((player.getCashOnHand() + game.getPlayerBet()));
            //Game Ends
//YOU HIT
        } else if (String.valueOf(roundTwoMove).equalsIgnoreCase("H")){
            System.out.println("You choose to hit");
            player.hit(deck.dealCard());
            if (dealer.getDisposition().equals("Hit")) {
                dealer.hit(deck.dealCard());
            }
            //PLAYERS STAND
            player.stand();
            dealer.stand();
            //EVALUATE WINNERS
            System.out.println(game.whoWins(player.cardValue, dealer.cardValue));
            //CALCULATE LOSS/GAIN
            game.pass((player.getCashOnHand() + game.getPlayerBet()));
            //Game Ends
//YOU WASTE THE DEALER'S TIME
        } else {
            System.out.println("Hey, " + player.getName() + ", get the hell away from my table!!!");
        }

        }

    }

