package org.snakeAndLadder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;


@Getter
public class Dice {

    private int minValue;
    private int maxValue;
    private int currentValue;
    Random random;

    public Dice(int minValue, int maxValue, int currentValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        random = new Random();
    }

    public int roll(){
        return random.nextInt(minValue,maxValue+1);
    }
}
