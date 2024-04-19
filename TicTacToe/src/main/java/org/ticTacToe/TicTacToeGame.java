package org.ticTacToe;

import org.ticTacToe.model.*;
import org.ticTacToe.playingPiece.PlayingPiece;
import org.ticTacToe.playingPiece.PlayingPiece0;
import org.ticTacToe.playingPiece.PlayingPieceX;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    Deque<Player> players;
    Board gameBoard;

    public void initialize(){
        //Creating 2 players;
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        PlayingPiece0 noughtsPiece = new PlayingPiece0();

        Player player1 = new Player("Player1",crossPiece);
        Player player2 = new Player("Player2",noughtsPiece);
        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;

        while(noWinner)
        {
            //take out the player whose turn is and also put the player in the list back
            Player playerTurn = players.removeFirst();
            //get the free space from the board
            gameBoard.printBoard();
            List<int[][]> freeSpaces =  gameBoard.getFreeCells();
            if(freeSpaces.isEmpty())
                break;

            //read the user input
            System.out.print("Player:" + playerTurn.getPlayerName() + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);

            //place the piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow,inputColumn,playerTurn.playingPiece);
            if(!pieceAddedSuccessfully)
            {
                //player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);
            boolean isWinner = isThereWinner(inputRow,inputColumn,playerTurn.getPlayingPiece());
            if(isWinner){
                return playerTurn.getPlayerName();
            }
        }
        return "tie";
    }

    private boolean isThereWinner(int row, int column, PlayingPiece playingPiece) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i = 0;i< gameBoard.size;i++)
        {
            if(gameBoard.getBoard()[row][i]==null || gameBoard.getBoard()[row][i].getPieceType()!=playingPiece.getPieceType()){
                rowMatch = false;
                break;
            }
        }

        //need to check in column
        for(int i = 0;i< gameBoard.size;i++)
        {
            if(gameBoard.getBoard()[i][column]==null || gameBoard.getBoard()[i][column].getPieceType()!=playingPiece.getPieceType()){
                columnMatch = false;
                break;
            }
        }

        //need to check in diagonals
        for(int i = 0,j=0;i< gameBoard.size;i++,j++)
        {
            if(gameBoard.getBoard()[i][j]==null || gameBoard.getBoard()[i][j].getPieceType()!=playingPiece.getPieceType()){
                diagonalMatch = false;
                break;
            }
        }

        //need to check anti-diagonals
        for(int i = 0,j= gameBoard.getSize()-1;i< gameBoard.size;i++,j--)
        {
            if(gameBoard.getBoard()[i][j]==null || gameBoard.getBoard()[i][j].getPieceType()!=playingPiece.getPieceType()){
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
