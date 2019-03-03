package com.andy.gameoflife;

class GameOfLife {

    private final int width;
    private final int height;
    private String[][] grid;

    GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new String[width][height];
    }

    void generate() {
        String [][] newGeneration = new String[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                checkNeighboursAroundLiveCell(newGeneration, y, x);
                checkNeighboursAroundDeadCell(newGeneration, y, x);
            }
        }

        grid = newGeneration;
    }

    String[][] getGrid() {
        return grid;
    }

    private void checkNeighboursAroundLiveCell(String[][] newGeneration, int y, int x) {
        if ("*".equals(grid[x][y])) {
            int count = countAroundCell(y, x);
            markAliveOrDead(newGeneration, y, x, count == 2 || count == 3);
        }
    }


    private void checkNeighboursAroundDeadCell(String[][] newGeneration, int y, int x) {
        if (".".equals(grid[x][y])) {
            markAliveOrDead(newGeneration, y, x, countAroundCell(y, x) == 3);
        }
    }

    private int countAroundCell(int y, int x) {
        int count = countAbove(x, y);
        count += countBelow(x, y);
        count += countLeft(x, y);
        count += countRight(x, y);
        return count;
    }

    void createRow(int y, String... s) {
        int x = 0;
        for (String cell : s) {
            grid[x++][y] = cell;
        }
    }

    int countAbove(int x, int y) {
        return count(y > 0, x, y - 1);
    }

    int countBelow(int x, int y) {
        return count(y < height - 1, x, y + 1);
    }

    private int count(boolean withinBounds, int x, int y) {
        int count = 0;

        if (withinBounds) {
            for (int xx = getStartXPos(x); xx < x + 2 && xx < width - 1; xx++) {
                if ("*".equals(grid[xx][y])) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getStartXPos(int x) {
        int startX = x;
        if (x > 0) {
            startX = x - 1;
        }
        return startX;
    }


    int countLeft(int x, int y) {
        return countToTheSide(x > 0, y, x - 1);
    }

    int countRight(int x, int y) {
        return countToTheSide(x < width - 1, y, x + 1);
    }

    private int countToTheSide(boolean withinBounds, int y, int i) {
        if (withinBounds && "*".equals(grid[i][y])) {
            return 1;
        }
        return 0;
    }

    private void markAliveOrDead(String[][] newGeneration, int y, int x, boolean shouldBeAlive) {
        if (shouldBeAlive) {
            newGeneration[x][y] = "*";
        } else {
            newGeneration[x][y] = ".";
        }
    }
}
