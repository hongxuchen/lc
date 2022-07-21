package p.s0353;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeGame {

    private final int[][] food;
    private final int width;
    private final int height;
    private final Queue<int[]> body;
    private int length = 1;
    private int[] pos = new int[]{0, 0};
    private int foodIndex = 0;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.body = new LinkedList<>();
        this.body.add(new int[]{0, 0});
    }

    public int getScore(int[] toPos) {
        if (foodIndex < food.length) {
            int[] curFood = food[foodIndex];
            if (toPos[0] == curFood[1] && toPos[1] == curFood[0]) {
                this.length++;
                this.foodIndex++;
            }
        }
        if (this.body.size() > this.length) {
            this.body.poll();
        }
        // check body
        int i = 0;
        for (int[] b : body) {
            i++;
            if (toPos[0] == b[0] && toPos[1] == b[1] && i != 1) {
                return -1;
            }
        }
        this.body.offer(toPos);
        this.pos = toPos;
        return this.length - 1;
    }

    public int move(String direction) {

        switch (direction) {
            case "R": {
                int[] toPos = new int[]{pos[0] + 1, pos[1]};
                if (toPos[0] >= this.width) {
                    return -1;
                }
                return getScore(toPos);
            }
            case "L": {
                int[] toPos = new int[]{pos[0] - 1, pos[1]};
                if (toPos[0] < 0) {
                    return -1;
                }
                return getScore(toPos);
            }
            case "D": {
                int[] toPos = new int[]{pos[0], pos[1] + 1};
                if (toPos[1] >= this.height) {
                    return -1;
                }
                return getScore(toPos);
            }
            case "U": {
                int[] toPos = new int[]{pos[0], pos[1] - 1};
                if (toPos[1] < 0) {
                    return -1;
                }
                return getScore(toPos);
            }
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) {

//        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
//        System.out.println(snakeGame.move("R"));
//        System.out.println(snakeGame.move("D"));
//        System.out.println(snakeGame.move("R"));
//        System.out.println(snakeGame.move("U"));
//        System.out.println(snakeGame.move("L"));
//        System.out.println(snakeGame.move("U"));

        SnakeGame snakeGame = new SnakeGame(2, 2, new int[][]{{1, 1,}, {0, 0}, {0, 1}});
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("R"));

    }

}
