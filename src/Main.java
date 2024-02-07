import processing.core.PApplet;

public class Main extends PApplet {
    private final int CELL_SIZE = 10;
    private final int NUM_ROWS = 100;
    private final int NUM_COLUMNS = 50;
    private Cell[][] cells;
    private boolean evolve;


    //setting up PApplet
    public static Main app;
    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public Main() {
        app = this;
        evolve = false;
    }

    //put size here (not in setup)
    public void settings() {
        size(NUM_ROWS*CELL_SIZE, NUM_COLUMNS*CELL_SIZE);
    }

    //init
    public void setup() {
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c] = new Cell(r*CELL_SIZE, c*CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD);
            }
        }
    }

    //periodic
    public void draw() {
        if(evolve){
            applyRules();
            evolve();
        }
        display();
    }

    public void mouseClicked(){

    }

    public void keyPressed(){

    }

    private void applyRules(){
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c].applyRules(cells);
            }
        }
    }

    private void evolve(){
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c].evolve();
            }
        }
    }

    private void display(){
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c].display();
            }
        }
    }
}
