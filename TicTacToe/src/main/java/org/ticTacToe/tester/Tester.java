package org.ticTacToe.tester;

import org.ticTacToe.TicTacToeGame;

public class Tester {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.initialize();
        System.out.println("Game winner is: "+ game.startGame());
    }
}