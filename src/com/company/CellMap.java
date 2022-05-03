package com.company;

public class CellMap {
    private final int width;
    private final int height;
    private int generationNum;
    private final int[][] grid;
    private final Cell[][] currentGen;
    private final Cell[][] nextGen;

    public CellMap(int[][] grid) {
        this.width = grid[0].length;
        this.height = grid.length;
        this.generationNum = 1;
        this.grid = grid;
        this.currentGen = new Cell[width][height];
        this.nextGen = new Cell[width][height];
    }

    public void init() {
        gridToCells();

        while (true) {
            System.out.println(generationNum + " generation");
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (!currentGen[x][y].getState())
                        System.out.print(" . ");
                    else
                        System.out.print(" * ");
                }
                System.out.println();
            }
            System.out.println();

            fillCells();
            nextGen();
            generationNum++;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }

    private void gridToCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean state = grid[x][y] == 1;
                currentGen[x][y] = new Cell(state, (byte) 0);
            }
        }
        calculateAndSetNeighbours();
    }

    private void fillCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setCell(x, y);
            }
        }
        calculateAndSetNeighbours();
    }

    private void setCell(int x, int y) {
        boolean state = currentGen[x][y].getState();
        currentGen[x][y] = new Cell(state, (byte)0);
        nextGen[x][y] = new Cell(false, (byte)0);
    }

    private void calculateAndSetNeighbours() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                byte aliveNeighbours = 0;
                for (int xn = -1; xn <= 1; xn++) {
                    for (int yn = -1; yn <= 1; yn++) {
                        if ((x + xn >= 0 && x + xn < width) && (y + yn >= 0 && y + yn < height)) {
                            aliveNeighbours += currentGen[x + xn][y + yn].getState() ? 1 : 0;
                        }
                    }
                }
                aliveNeighbours -= currentGen[x][y].getState() ? 1 : 0;
                currentGen[x][y].setAliveNeighbours(aliveNeighbours);
            }
        }

    }

    private void nextGen() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int aliveNeighbours = currentGen[x][y].getAliveNeighbours();
                boolean state = currentGen[x][y].getState();

                if (aliveNeighbours < 2 || aliveNeighbours > 3) nextGen[x][y].setState(false);
                else if (aliveNeighbours == 3 && !state) nextGen[x][y].setState(true);
                else nextGen[x][y] = currentGen[x][y];
            }
        }
        nextGetToCurrent();
    }

    private void nextGetToCurrent() {
        for (int x = 0; x < width; x++) {
            if (height >= 0) System.arraycopy(nextGen[x], 0, currentGen[x], 0, height);
        }
    }


}
