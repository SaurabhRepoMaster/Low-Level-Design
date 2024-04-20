package org.snakeAndLadder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private String name;
    private int position;
    private boolean hasWon;

    public Player(String name)
    {
        this.name = name;
        this.position=0;
        this.hasWon=false;
    }
}
