package com.LeahGrace;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

//INSTANTIATIONS
        var game = new BlackJack();
        var deck = new Deck();
        var dealer = new Dealer("Dealer");
        var player = new Player("New guy");

//GREET PLAYER
        game.greeting(player.getCashOnHand()); //Greet the player and let them know how much $$ they have to bet with
//DECISION TO JOIN


            Scanner scan = new Scanner(System.in);
            String joinGame = String.valueOf(scan.next().charAt(0));

            if (!joinGame.equalsIgnoreCase("y")) { // if the player does not join the game
                game.pass(player.getCashOnHand()); //end the game
            }


            String keepPlaying = "";


            while (!keepPlaying.equalsIgnoreCase("q")) {

                System.out.println("start playing");


                keepPlaying = String.valueOf(scan.next().charAt(0));



//START ROUND ONE
                game.roundOne();
                player.roundOne(deck.dealCard(), deck.dealCard());
                dealer.roundOne(deck.dealCard(), deck.dealCard());
//START ROUND TWO
                game.nextRoundProposition();
                char roundTwoMove = scan.next().charAt(0);


                if (String.valueOf(roundTwoMove).equalsIgnoreCase("D")) {
                    if (!(player.getCashOnHand() > 0)) {
                        System.out.println("Sorry, you don't have enough cash to double down");
                    } else {
                        System.out.println("You choose to double down, with $" + player.getCashOnHand() + " cash on hand. How much will you wager?");
                        int raise = scan.nextInt();
                        if (raise > player.getCashOnHand()) {
                            System.out.println("Nice try " + player.getName());
                        } else {
                            game.setPlayerBet(raise);
                            player.setCashOnHand(raise);
                            System.out.println("The player now wagers $" + game.getPlayerBet());
                            System.out.println("The player is down to $" + player.getCashOnHand());
                        }
                    }
                    roundTwoMove = 'H';
                }

//YOU STAND
                if (String.valueOf(roundTwoMove).equalsIgnoreCase("S")) {
                    System.out.println("You choose to stand");
                    //THE DEALER TAKES THEIR TURN
                    while (!dealer.getDisposition().equals("Stand")) {
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
                } else if (String.valueOf(roundTwoMove).equalsIgnoreCase("H")) {
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
                    System.out.println("Cash on hand " + player.getCashOnHand());
                    game.pass((player.getCashOnHand() + game.getPlayerBet()));
                    System.out.println("Cash on hand " + player.getCashOnHand());
                    //Game Ends
                    System.out.println("player stats " + player.getCashOnHand());
                    //RESET PROPERTIES FOR NEW GAME
                    player.reset();
                    System.out.println("player stats " + player.getCashOnHand());

//YOU WASTE THE DEALER'S TIME
                } else if (String.valueOf(roundTwoMove).equalsIgnoreCase("q")) {

                    game.pass((player.getCashOnHand() + game.getPlayerBet()));
                    System.out.println("You leave the game");
                } else {
                        System.out.println("Hey, " + player.getName() + ", get the hell away from my table!!!");
                    }
                }
        game.pass(player.getCashOnHand());
        System.out.println("You quit the game");
            }
    }

