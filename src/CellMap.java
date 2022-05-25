import config.Color;

public class CellMap {
    private int width;
    private int height;
    private int generationNum;
    private final int[][] grid;
    private final Cell[][] currentGen;
    private final Cell[][] nextGen;

    public CellMap(int[][] grid) {
        try {
            this.width = grid[0].length;
            this.height = grid.length;

            if (this.width <= 0) {
                throw new GridSizeError(Color.RED.getCode() + "Incorrect grid size." + Color.RESET.getCode());
            }

        } catch (GridSizeError ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }

        this.generationNum = 1;
        this.grid = grid;
        this.currentGen = new Cell[width][height];
        this.nextGen = new Cell[width][height];
    }

    public void init() {
        gridToCells();

        while (true) {
            int alive = 0;
            System.out.println(Color.GREEN.getCode() + generationNum + Color.RESET.getCode() + " generation");
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (!currentGen[x][y].getState())
                        System.out.print(Color.WHITE.getCode() + " . " + Color.RESET.getCode());
                    else {
                        alive++;
                        System.out.print(Color.GREEN.getCode() + " * " + Color.RESET.getCode());

                    }
                }
                System.out.println();
            }
            System.out.println();


            if (alive == 0) {
                System.out.print(Color.BLUE.getCode() + "Empty field! Game over!" + Color.RESET.getCode());
                System.exit(0);
            }

            nextGen();
            generationNum++;
            try {
                Thread.sleep(1000);
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
                nextGen[x][y] = new Cell();
            }
        }
        calculateAndSetNeighbours();
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
                byte aliveNeighbours = currentGen[x][y].getAliveNeighbours();
                boolean state = currentGen[x][y].getState();

                if (aliveNeighbours < 2 || aliveNeighbours > 3) nextGen[x][y].setState(false);
                else if (aliveNeighbours == 3 && !state) nextGen[x][y].setState(true);
                else nextGen[x][y] = currentGen[x][y];
            }
        }
        nextGenToCurrent();
        calculateAndSetNeighbours();
    }

    private void nextGenToCurrent() {
        for (int x = 0; x < width; x++) {
            if (height >= 0) System.arraycopy(nextGen[x], 0, currentGen[x], 0, height);
        }
    }

    class GridSizeError extends Exception {

        public GridSizeError(String message) {
            super(message);
        }
    }


}
