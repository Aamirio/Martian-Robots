package com.tech.mai.robots;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aamirio on 05/06/2017.
 *
 * Tests Martian Robot maneuverability
 */

public class MarsTest {

    private Mars mars;

    @Test
    public void testFinalGridPosition() {

        mars = new Mars("5 3");

        String initialPosition = "1 1 E";
        String movementSequence = "RFRFRFRF";
        String expectedOutput = "1 1 E";

        assertEquals(expectedOutput, mars.getFinalGridPosition(initialPosition, movementSequence));

        initialPosition = "3 2 N";
        movementSequence = "FRRFLLFFRRFLL";
        expectedOutput = "3 3 N LOST";

        assertEquals(expectedOutput, mars.getFinalGridPosition(initialPosition, movementSequence));

        initialPosition = "0 3 W";
        movementSequence = "LLFFFLFLFL";
        expectedOutput = "2 3 S";

        assertEquals(expectedOutput, mars.getFinalGridPosition(initialPosition, movementSequence));
    }
}
