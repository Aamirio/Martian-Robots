package com.tech.mai.robots;

/**
 * Created by Aamirio on 05/06/2017.
 *
 * A Position which contains the direction it is facing in, along with a pair of X and Y coordinates
 */
public class Position {

    private Direction direction;
    private Coordinates coordinates;

    public Position(Direction direction, Coordinates coordinates){
        this.direction = direction;
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", coordinates.getX(), coordinates.getY(), direction.toString());
    }
}
