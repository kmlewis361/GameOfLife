public class Cell {
    private int x, y, size, row, column;
    private CellState cellState;
    private MooreRules rules;
    //    private MooreRules rules;
    public Cell(int x, int y, int size, int row, int column, CellState cellState, MooreRules rules){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    public void applyRules(Cell[][] cells){
       int liveNeighbors = countLiveNeighbors(cells);
       cellState = rules.applyRules(cellState, liveNeighbors);
    }

    public void evolve(){
        if(cellState.equals(CellState.WILL_DIE)){
            cellState = CellState.DEAD;
        }else if(cellState.equals(CellState.WILL_REVIVE)){
            cellState = CellState.ALIVE;
        }else{
            return;
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
        if(cellState.equals(CellState.ALIVE)){
            cellState = CellState.DEAD;
        }else{
            cellState = CellState.ALIVE;
        }
    }

    public CellState getState(){
        return cellState;
    }

    public int countLiveNeighbors(Cell[][] cells){
        int liveNeighbors = 0;
        for(int i=row-1; i<=row+1; i++){
            for(int j=column-1; j<=column+1; j++){
                if(i>=0 && i<cells.length && j>=0 && j<cells[0].length) {
                    if (cells[i][j].getState().equals(CellState.ALIVE) || cells[i][j].getState().equals(CellState.WILL_DIE)){
                        System.out.println("increment lneighbors");
                        liveNeighbors++;
                        System.out.println(liveNeighbors);
                    }
                }
            }
        }
        if(cellState == cellState.ALIVE){
            liveNeighbors--;
        }
        return liveNeighbors;
    }

}

