package org.ticTacToe.model;

import lombok.Getter;
import lombok.Setter;
import org.ticTacToe.playingPiece.PlayingPiece;

@Getter
@Setter
public class Player {

    public String playerName;
    public PlayingPiece playingPiece;

    public Player(String playerName, PlayingPiece playingPiece)
    {
        this.playerName = playerName;
        this.playingPiece = playingPiece;
    }

}
