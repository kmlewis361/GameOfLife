public class Cell {
    private int x, y, size, row, column;
    private CellState cellState;
    private MooreRules rules;

    /**
     * Constructs a new Cell with x, y, size, row, column, cellState, and rules parameters
     * @param x - the x coordinate of the grid square
     * @param y - the y coordinate of the grid square
     * @param size - the width of the grid square
     * @param row - the row index of the cell
     * @param column - the column index of the cell
     * @param cellState - the starting state of the cell
     * @param rules - the MooreRules array that the cell will follow
     */
    public Cell(int x, int y, int size, int row, int column, CellState cellState, MooreRules rules){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    /**
     * applies the MooreRules to the cells by feeding the liveNeighbors into the rules.applyRules method
     * @param cells - the array of cells the rules are being applied to
     */
    public void applyRules(Cell[][] cells){
       int liveNeighbors = countLiveNeighbors(cells);
       cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /**
     * sets cells in intermediate states (WILL_DIE or WILL_REVIVE) to more permanent states (DEAD or ALIVE)
     */
    public void evolve(){
        if(cellState==CellState.WILL_DIE){
            cellState = CellState.DEAD;
        }else if(cellState==CellState.WILL_REVIVE){
            cellState = CellState.ALIVE;
        }else{
            return;
        }
    }

    /**
     * draws the rect and determines the fill color
     */
    public void display(){
        if(cellState==CellState.ALIVE){
            Main.app.fill(0);
        }else{
            Main.app.fill(255);
        }
        Main.app.rect(x,y,size,size);
    }

    /**
     * toggles the state
     */
    public void handleClick(){
        if(cellState==CellState.ALIVE){
            cellState = CellState.DEAD;
        }else{
            cellState = CellState.ALIVE;
        }
    }

    /**
     * returns the current cellState
     * @return the current cellState
     */
    public CellState getState(){
        return cellState;
    }

    /**
     * sets the current cellState
     * @param state - the new state
     */
    public void setState(CellState state){
        cellState = state;
    }

    /**
     * returns the number of live neighbors (not including the current cell)
     * @param cells - the array of cells to read live neighbors from
     * @return the number (int) of live neighbors
     */
    private int countLiveNeighbors(Cell[][] cells){

        int liveNeighbors = 0;
        for(int i=row-1; i<=row+1; i++){
            int a = i;
            if(i==-1){
                i = cells.length-1;
            }else if(i==cells.length){
                i = 0;
            }
            for(int j=column-1; j<=column+1; j++){
                int b = j;
                if(j<=-1){
                    j = cells[0].length-1;
                }
                if(j>=cells[0].length){
                    j = 0;
                }
                if (cells[i][j].getState()==CellState.ALIVE || cells[i][j].getState()==CellState.WILL_DIE){
                    liveNeighbors++;
                }
                j = b;
            }
            i = a;
        }
        if(cellState == cellState.ALIVE){
            liveNeighbors--;
        }
        return liveNeighbors;
    }

}

