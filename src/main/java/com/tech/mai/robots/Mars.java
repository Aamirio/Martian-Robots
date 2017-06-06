package com.tech.mai.robots;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aamirio on 05/06/2017.
 *
 * Planet Mars which provides robot maneuverability
 */
public class Mars {

    private int width;
    private int height;
    private Position currentPosition;
    private List<Coordinates> scentedEdges = new ArrayList<>();
    private String charLost = "";

    public Mars(String coordinates){

        String[] coordinatesArray = coordinates.split("\\s+");

        width = Integer.valueOf(coordinatesArray[0]);
        height = Integer.valueOf(coordinatesArray[1]);
    }

    public String getFinalGridPosition(String initialPosition, String movementSequence) {

        String[] initialPositionArray = initialPosition.split("\\s+");

        int x = Integer.valueOf(initialPositionArray[0]);
        int y = Integer.valueOf(initialPositionArray[1]);

        Coordinates initialCoordinates = new Coordinates(x, y);

        currentPosition = new Position(Direction.valueOf(initialPositionArray[2]), initialCoordinates);

        movementSequence.chars().forEach(letter -> updatePosition(letter));

        if (StringUtils.isNotBlank(charLost)) {
            String tempCharLost = charLost;
            charLost = "";
            return tempCharLost;
        }

        return currentPosition.toString();
    }

    private void updatePosition(int letter) {

        switch ((char)letter) {
            case 'L':
                rotateLeft();
                break;
            case 'R':
                rotateRight();
                break;
            case 'F':
                moveForward();
                break;
            //new commands can be added here
            default: //There should be some error handling here
        }
    }

    private void rotateLeft() {
        switch (currentPosition.getDirection()) {
            case N:
                currentPosition.setDirection(Direction.W);
                break;
            case S:
                currentPosition.setDirection(Direction.E);
                break;
            case E:
                currentPosition.setDirection(Direction.N);
                break;
            case W:
                currentPosition.setDirection(Direction.S);
                break;
        }
    }

    private void rotateRight() {
        switch (currentPosition.getDirection()) {
            case N:
                currentPosition.setDirection(Direction.E);
                break;
            case S:
                currentPosition.setDirection(Direction.W);
                break;
            case E:
                currentPosition.setDirection(Direction.S);
                break;
            case W:
                currentPosition.setDirection(Direction.N);
                break;
        }
    }

    private void moveForward() {

        int currentXCoord = currentPosition.getCoordinates().getX();
        int currentYCoord = currentPosition.getCoordinates().getY();
        Coordinates newCoordinates;

        switch (currentPosition.getDirection()) {
            case N:
                newCoordinates = new Coordinates(currentXCoord, currentYCoord + 1);
                break;
            case S:
                newCoordinates = new Coordinates(currentXCoord, currentYCoord - 1);
                break;
            case E:
                newCoordinates = new Coordinates(currentXCoord + 1, currentYCoord);
                break;
            case W:
                newCoordinates = new Coordinates(currentXCoord - 1, currentYCoord);
                break;
            default:
                newCoordinates = new Coordinates(0,0);
        }

        if (newCoordinates.getX() > width || newCoordinates.getY() > height ) {
            if (scentedEdges.contains(newCoordinates)) {
                return;
            }
            else if (!StringUtils.isNotBlank(charLost)){
                charLost = String.format("%s %s %s LOST",
                        currentPosition.getCoordinates().getX(),
                        currentPosition.getCoordinates().getY(),
                        currentPosition.getDirection().toString());
                scentedEdges.add(newCoordinates);
            }
        }
        currentPosition.setCoordinates(newCoordinates.getX(), newCoordinates.getY());
    }
}
