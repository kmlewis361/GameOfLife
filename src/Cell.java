public class Cell {
    private int x, y, size, row, column;
    private CellState cellState;
    //    private MooreRules rules;
    public Cell(int x, int y, int size, int row, int column, CellState cellState){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
    }

    public void applyRules(Cell[][] cells){
        if(countLiveNeighbors(cells)<2 || countLiveNeighbors(cells)>3 && !cellState.equals(CellState.DEAD)) {
            cellState = CellState.WILL_DIE;
        }else if(countLiveNeighbors(cells)==3 && !cellState.equals(CellState.ALIVE)){
            cellState = CellState.ALIVE;
        }
    }

    public void evolve(){
        if(cellState.equals(CellState.WILL_DIE)){
            cellState = CellState.DEAD;
        }else if(cellState.equals(CellState.WILL_REVIVE)){
            cellState = CellState.ALIVE;
        }

    }

    public void display(){
        if(cellState.equals(CellState.ALIVE)){
            Main.app.fill(0);
        }else{
            Main.app.fill(255);
        }
        Main.app.rect(x,y,size,size);
    }

    public void handleClick(){

    }

    public CellState getState(){
        return cellState;
    }

    private int countLiveNeighbors(Cell[][] cells){
        int liveNeighbors = 0;

        for(int i=row-1; i<=row+1; i++){
            for(int j=column-1; j<=column+1; i++){
                if(i!=0 && i!=cells.length && j!=0 && j!= cells[0].length) {
                    if (cells[i][j].getState().equals(CellState.ALIVE) || cells[i][j].getState().equals(CellState.WILL_REVIVE) && !cells[i][j].equals(this)) {
                        liveNeighbors++;
                    }
                }
            }
        }

        return liveNeighbors;
    }
}

