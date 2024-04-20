package org.snakeAndLadder;

import org.snakeAndLadder.model.*;

import java.util.*;

public class SnakeAndLadder {

    private int numberOfSnakes;
    private int numberOfLadders;

    private Deque<Player> players;
    private Map<Integer,Snake> snakes;
    private Map<Integer,Ladder> ladders;

    private Dice dice;
    private Board board;

    public SnakeAndLadder(int numberOfLadders, int numberOfSnakes, int boardSize)
    {
        this.numberOfSnakes=numberOfSnakes;
        this.numberOfLadders=numberOfLadders;
        this.players=new LinkedList<>();
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        board = new Board(boardSize);
        dice = new Dice(1,6,2);
        initBoard();
    }

    private void initBoard() {
        Set<Integer> snakeEndSet = new HashSet<>();
        for(int i = 0 ;i<numberOfSnakes;i++)
        {
            while(true)
            {
                Random random = new Random();
                int snakeStart = random.nextInt(board.getStart(), board.getSize());
                int snakeEnd = random.nextInt(board.getStart(), board.getSize());
                if(snakeEnd>=snakeStart || snakeEnd/10==snakeStart/10)
                    continue;
                if(!snakes.containsKey(snakeStart))
                {
                    Snake snake = new Snake(snakeStart,snakeEnd);
                    snakes.put(snakeStart,snake);
                    snakeEndSet.add(snakeEnd);
                    System.out.println("Snake "+(i+1)+" is starting at "+snakeStart+" and ending at "+snakeEnd);
                    break;
                }

            }
        }
        System.out.println();

        for(int i = 0 ;i<numberOfLadders;i++)
        {
            while(true)
            {
                Random random = new Random();
                int ladderStart = random.nextInt(board.getStart(), board.getSize());
                int ladderEnd = random.nextInt(board.getStart(), board.getSize());
                if(ladderStart>=ladderEnd || snakes.containsKey(ladderEnd) || snakeEndSet.contains(ladderStart) || ladderStart/10==ladderEnd/10)
                    continue;
                if(!ladders.containsKey(ladderStart))
                {
                    Ladder ladder = new Ladder(ladderStart,ladderEnd);
                    ladders.put(ladderStart,ladder);
                    System.out.println("Ladder "+(i+1)+" is starting at "+ladderStart+" and ending at "+ladderEnd);
                    break;
                }

            }
        }
        System.out.println();
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public void playGame() {
        while (true) {
            Player player = players.poll();
            int val = dice.roll();
            System.out.println();
            System.out.println("Dice for Player " + player.getName() + " is "+val);
            int newPosition = player.getPosition() + val;
            if (newPosition > board.getEnd()) {
                player.setPosition(player.getPosition());
                players.addLast(player);
            } else {
                player.setPosition(getNewPosition(player,newPosition));
                if (player.getPosition() == board.getEnd()) {
                    player.setHasWon(true);
                    System.out.println("Player " + player.getName() + " Won.");
                    return;
                } else {
                    System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
                    players.addLast(player);
                }
            }
        }
    }

    private int getNewPosition(Player player,int newPosition) {
            if(snakes.containsKey(newPosition)){
                System.out.println("Snake has bitten Player "+player.getName());
                return (snakes.get(newPosition)).getTail();
            }

            if(ladders.containsKey(newPosition))
            {
                System.out.println(player.getName()+" has climbed ladder");
                return ladders.get(newPosition).getEnd();
            }

            return newPosition;

    }


}
