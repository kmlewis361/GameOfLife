public class Cell {
    private int x, y, size, row, column;
    private boolean alive;

    /**
     * Constructs a new Cell with x, y, size, row, column, cellState, and rules parameters
     * @param x - the x coordinate of the grid square
     * @param y - the y coordinate of the grid square
     * @param size - the width of the grid square
     * @param row - the row index of the cell
     * @param column - the column index of the cell
     * @param alive - a boolean representing whether the cell is alive or dead
    */
    public Cell(int x, int y, int size, int row, int column, boolean alive){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.alive = alive;
    }
    /**
     * draws the rect and determines the fill color
     */
    public void display(){
        if(alive){
            Main.app.fill(0);
        }else{
            Main.app.fill(255);
        }
        Main.app.rect(x,y,size,size);
    }

    public boolean getState(){
        return alive;
    }

    public void flip(){
        alive = !alive;
    }

    public int getX(){return x;}

    public int getY(){return y;}


}

