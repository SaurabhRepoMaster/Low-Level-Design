package org.ticTacToe.model;

import lombok.Getter;
import org.ticTacToe.playingPiece.PlayingPiece;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {

    public int size;
    PlayingPiece board[][];

    public Board(int size)
    {
        this.size=size;
        board = new PlayingPiece[size][size];
    }

    public void printBoard() {
        for(int i = 0 ;i<size;i++)
        {
            for(int j = 0;j<size;j++)
            {
                if(board[i][j]!=null)
                    System.out.print(board[i][j].pieceType + "   ");
                else
                    System.out.print("    ");
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public List<int[][]> getFreeCells() {
        List<int[][]> freeSpaces = new ArrayList<>();
        for(int i = 0; i<size;i++)
        {
            for(int j = 0 ;j<size;j++)
            {
                if(board[i][j]==null)
                {
                    freeSpaces.add(new int[][]{{i,j}});
                }
            }
        }
        return freeSpaces;
    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece) {
        if(board[row][column]!=null)
            return false;

        board[row][column]=playingPiece;
        return true;
    }
}
