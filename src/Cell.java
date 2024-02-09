public class Cell {
    private int x, y, size, row, column, fillColor;
    private CellState cellState;
    //    private MooreRules rules;
    public Cell(int x, int y, int size, int row, int column, CellState cellState){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        fillColor = 255;
    }

    public void applyRules(Cell[][] cells){
       // System.out.println("applying to cell!");
        if(countLiveNeighbors(cells)<2 || countLiveNeighbors(cells)>3) {
            System.out.println("will die; " + countLiveNeighbors(cells));
            cellState = CellState.WILL_DIE;

        }else if(countLiveNeighbors(cells)==3){
            System.out.println("will revive");
            cellState = CellState.WILL_REVIVE;

        }else{
            System.out.println("stays the same");
        }
    }

    public void evolve(){
        if(cellState.equals(CellState.WILL_DIE)){
            cellState = CellState.DEAD;
            fillColor = 255;
        }else if(cellState.equals(CellState.WILL_REVIVE)){
            cellState = CellState.ALIVE;
            fillColor = 0;
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
            fillColor = 0;
        }else{
            cellState = CellState.ALIVE;
            fillColor = 255;
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
                    if (cells[i][j].getState().equals(CellState.ALIVE) /* && !cells[i][j].equals(this)*/ ){
                        System.out.println("increment lneighbors");
                        liveNeighbors++;
                        System.out.println(liveNeighbors);
                    }
                }
            }
        }

        return liveNeighbors;
    }

}

