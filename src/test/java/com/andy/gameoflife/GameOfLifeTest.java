package com.andy.gameoflife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOfLifeTest {

    GameOfLife gameOfLife;
    String [][] grid;
    @Before
    public void setup() {
        gameOfLife = new GameOfLife(4, 4);
    }
    @Test
    public void dieWhenNoNeighbours() {
        gameOfLife.createRow(0, ".", ".", ".", ".");
        gameOfLife.createRow(1, ".", ".", ".", ".");
        gameOfLife.createRow(2, ".", "*", ".", ".");
        gameOfLife.createRow(3, ".", ".", ".", ".");

        gameOfLife.generate();
        String [][] newGeneration = gameOfLife.getGrid();

        assertRow(newGeneration, 0, ".", ".", ".", ".");
        assertRow(newGeneration, 1, ".", ".", ".", ".");
        assertRow(newGeneration, 2, ".", ".", ".", ".");
        assertRow(newGeneration, 3, ".", ".", ".", ".");
    }

    @Test
    public void dieWhenOneNeighbour() {
        gameOfLife.createRow(0, ".", ".", ".", ".");
        gameOfLife.createRow(1, ".", "*", ".", ".");
        gameOfLife.createRow(2, ".", "*", ".", ".");
        gameOfLife.createRow(3, ".", ".", ".", ".");

        gameOfLife.generate();
        String [][] newGeneration = gameOfLife.getGrid();

        assertRow(newGeneration, 0, ".", ".", ".", ".");
        assertRow(newGeneration, 1, ".", ".", ".", ".");
        assertRow(newGeneration, 2, ".", ".", ".", ".");
        assertRow(newGeneration, 3, ".", ".", ".", ".");
    }

    @Test
    public void cellShouldLiveWhenTwoNeighbours() {
        gameOfLife.createRow(0, ".", ".", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");
        gameOfLife.createRow(2, ".", "*", ".", ".");
        gameOfLife.createRow(3, ".", ".", ".", ".");

        gameOfLife.generate();
        String [][] newGeneration = gameOfLife.getGrid();

        assertRow(newGeneration, 0, ".", ".", ".", ".");
        assertRow(newGeneration, 1, ".", "*", "*", ".");
        assertRow(newGeneration, 2, ".", "*", "*", ".");
        assertRow(newGeneration, 3, ".", ".", ".", ".");
    }

    @Test
    public void cellShouldLiveWhenThreeNeighbours() {
        gameOfLife.createRow(0, ".", ".", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");
        gameOfLife.createRow(2, ".", "*", "*", ".");
        gameOfLife.createRow(3, ".", ".", ".", ".");

        gameOfLife.generate();
        String [][] newGeneration = gameOfLife.getGrid();


        assertRow(newGeneration, 0, ".", ".", ".", ".");
        assertRow(newGeneration, 1, ".", "*", "*", ".");
        assertRow(newGeneration, 2, ".", "*", "*", ".");
        assertRow(newGeneration, 3, ".", ".", ".", ".");
    }

    @Test
    public void liveCellShouldDieWhenMoreThan3Neighbours() {
        gameOfLife.createRow(0, ".", ".", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");
        gameOfLife.createRow(2, ".", "*", "*", ".");
        gameOfLife.createRow(3, ".", "*", ".", ".");

        gameOfLife.generate();
        String [][] newGeneration = gameOfLife.getGrid();

        assertRow(newGeneration, 0, ".", ".", ".", ".");
        assertRow(newGeneration, 1, ".", "*", "*", ".");
        assertRow(newGeneration, 2, "*", ".", ".", ".");
        assertRow(newGeneration, 3, ".", "*", "*", ".");
    }

    @Test
    public void deadCellWithExactly3NeighboursBecomesAlive() {
        gameOfLife.createRow(0, ".", ".", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");
        gameOfLife.createRow(2, ".", "*", "*", ".");
        gameOfLife.createRow(3, ".", "*", ".", ".");

        gameOfLife.generate();
        String [][] newGeneration = gameOfLife.getGrid();

        assertRow(newGeneration, 0, ".", ".", ".", ".");
        assertRow(newGeneration, 1, ".", "*", "*", ".");
        assertRow(newGeneration, 2, "*", ".", ".", ".");
        assertRow(newGeneration, 3, ".", "*", "*", ".");
    }

    @Test
    public void shouldCountAbove() {
        gameOfLife.createRow(0, ".", "*", "*", ".");
        gameOfLife.createRow(1, ".", "*", ".", ".");

        int count = gameOfLife.countAbove(1, 1);

        assertEquals(2, count);
    }

    @Test
    public void shouldCountAboveOnEdge() {
        gameOfLife.createRow(0, ".", "*", "*", ".");
        gameOfLife.createRow(1, ".", "*", ".", ".");

        int count = gameOfLife.countAbove(1, 0);

        assertEquals(0, count);
    }

    @Test
    public void shouldCountBelow() {
        gameOfLife.createRow(0, ".", "*", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");

        int count = gameOfLife.countBelow(1, 0);

        assertEquals(2, count);
    }

    @Test
    public void shouldCountBelowOnEdge() {
        gameOfLife.createRow(0, ".", "*", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");

        int count = gameOfLife.countBelow(1, 1);

        assertEquals(0, count);
    }

    @Test
    public void shouldCountLeft() {
        gameOfLife.createRow(0, ".", "*", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");

        int count = gameOfLife.countLeft(2, 0);

        assertEquals(1, count);
    }

    @Test
    public void shouldCountLeftOnEdge() {
        gameOfLife.createRow(0, ".", "*", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");

        int count = gameOfLife.countLeft(0, 0);

        assertEquals(0, count);
    }

    @Test
    public void shouldCountRight() {
        gameOfLife.createRow(0, ".", "*", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", ".");

        int count = gameOfLife.countRight(1, 1);

        assertEquals(1, count);
    }

    @Test
    public void shouldCountRightOnEdge() {
        gameOfLife.createRow(0, ".", "*", ".", ".");
        gameOfLife.createRow(1, ".", "*", "*", "*");

        int count = gameOfLife.countRight(3, 1);

        assertEquals(0, count);
    }

    private void assertRow(String[][] grid, int y, String s, String s1, String s2, String s3) {
        assertEquals(s + s1 + s2 + s3, grid[0][y] + grid[1][y] + grid[2][y] + grid[3][y]);
    }

}
