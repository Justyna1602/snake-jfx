package pl.sdacademy;

import java.util.Collections;
import java.util.Random;

public class SnakeGame {
    private int xBound;
    private int yBound;
    private Snake snake;
    private Point apple;
    private pl.sdacademy.SnakeGamePrinter printer;

    public SnakeGame(int xBound, int yBound, Snake snake, SnakeGamePrinter printer) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = snake;

        this.printer = printer;
    }

    public SnakeGame(int xBound, int yBound, SnakeGamePrinter printer) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.printer = printer;
        snake = new Snake(new Point(0, 0), Collections.emptyList(), Direction.RIGHT);
    }

    public void start() {
        randomizeApple();
        printer.print(this);
        while (isSnakeINBounds())
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        snake.expand();
        if (snake.getHead().equals(apple)) {
            randomizeApple();
        } else {
            snake.cutTile();
        }
        printer.print(this);
    }


    private boolean isSnakeINBounds() {
        Point head = snake.getHead();
        int headX = head.getX();
        int headY = head.getY();
        return headY >= 0 && headY < yBound && headX >= 0 && headX < xBound;
    }

    private void randomizeApple() {
        Random myRandom = new Random();
        do {
            int aplleX = myRandom.nextInt(xBound);
            int aplleY = myRandom.nextInt(yBound);
            apple = new Point(aplleX, aplleY);
        } while (snake.contains(apple));


    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < yBound; x++) {
            for (int y = 0; y < xBound; y++) {
                Point point = new Point(x, y);
                char boardCharacter = getBoardCharacterAt(point);
                stringBuilder.append(boardCharacter);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private char getBoardCharacterAt(Point point) {
        if (snake.getHead().equals(point)) {
            return 'H';
        } else if (snake.getBody().contains(point)) {
            return 'B';
        } else if (apple.equals(point)) {
            return 'A';
        } else {
            return '.';
        }
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public int getXBound() {
        return xBound;
    }

    public int getYBound() {
        return yBound;
    }
}
