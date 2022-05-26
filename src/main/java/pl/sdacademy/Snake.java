package pl.sdacademy;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private List<pl.sdacademy.Point> body;
    private Direction direction;

    public Snake(Point head, List<Point> body, pl.sdacademy.Direction direction) {
        this.head = head;
        this.body = new ArrayList<>(body);
        this.direction = direction;
    }

    public pl.sdacademy.Point getHead() {
        return head;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }


    public void expand() {
        body.add(0, head);
        switch (direction) {
            case UP:
                head = new Point(head.getX(), head.getY() - 1);
                break;
            case RIGHT:
                head = new Point(head.getX() + 1, head.getY());
                break;
            case DOWN:
                head = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                head = new Point(head.getX() - 1, head.getY());
                break;
        }
    }

    public void cutTile() {
        body.remove(body.size() - 1);
    }

    public List<Point> getBody() {
        return body;
    }

    public boolean contains(Point point) {
        return head.equals(point) || body.contains(point);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

