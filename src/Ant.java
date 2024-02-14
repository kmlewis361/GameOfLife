public class Ant{
    private int orientation, r, c, size;
    private Cell[][] cells;
    public Ant(int orientation, int r, int c, Cell[][] cells, int size){
        this.orientation = orientation;
        this.r = r;
        this.c = c;
        this.cells = cells;
        this.size = size;
    }

    public void display(){ //draw a red rect over the cell at [r][c]
        Main.app.fill(255,0,0);
        int x = cells[r][c].getX();
        int y = cells[r][c].getY();
        Main.app.rect(x, y, size, size);
    }

    public void move(Cell[][] cells){
        //if white rotate clockwise, if black rotate counterclockwise
        if(cells[r][c].getState()){
            rotate(1);
        }else{
            rotate(-1);
        }
        //flip the cell color
        cells[r][c].flip();
        //move based on orientation
        if(orientation == 0){
            c++;
        }else if(orientation == 90){
            r--;
        }else if(orientation == 180){
            c--;
        }else if(orientation == 270){
            r++;
        }else{
            Main.app.println("INVALID ORIENTATION!!!");
        }

    }

    private void rotate(int direction){ //if direction is positive moves counterclockwise (think unit circle)
        if(Math.signum(direction) == 1) {
            if (orientation < 270) {
                orientation += 90;
            } else {
                orientation = 0;
            }
        }else{
            if(orientation>0){
                orientation -= 90;
            }else{
                orientation = 270;
            }
        }
        Main.app.println(orientation);
    }
}
