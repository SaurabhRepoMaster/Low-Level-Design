package org.snakeAndLadder.tester;

import org.snakeAndLadder.SnakeAndLadder;
import org.snakeAndLadder.model.Player;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter board Size");
        int boardSize = scanner.nextInt();

        System.out.println("Enter number of players");
        int numberOfPlayers = scanner.nextInt();

        System.out.println("Enter number of snakes");
        int numSnakes = scanner.nextInt();

        System.out.println("Enter number of ladders");
        int numLadders = scanner.nextInt();

        SnakeAndLadder snakeAndLadder = new SnakeAndLadder(numLadders,numSnakes,boardSize);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter player name");
            String pName = scanner.next();
            Player player = new Player(pName);
            snakeAndLadder.addPlayer(player);
        }
        snakeAndLadder.playGame();

    }
}