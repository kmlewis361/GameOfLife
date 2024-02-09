import processing.core.PApplet;

public class Main extends PApplet {
    private final int CELL_SIZE = 10;
    private final int NUM_ROWS = 50;
    private final int NUM_COLUMNS = 100;
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
        size(NUM_COLUMNS*CELL_SIZE, NUM_ROWS*CELL_SIZE);
    }

    //init
    public void setup() {
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c] = new Cell(c*CELL_SIZE, r*CELL_SIZE, CELL_SIZE, r, c, CellState.DEAD);
            }
        }
    }

    //periodic
    public void draw() {
        if(evolve){
         //   System.out.println("evolving!");
            applyRules();
        //    System.out.println("applied!");
            evolve();
        }
        display();
    }

    public void mouseClicked(){
        int r = mouseY/CELL_SIZE;
        int c = mouseX/CELL_SIZE;
        //println("r: " + r);
        cells[r][c].handleClick();
    }

    public void keyPressed(){
        evolve = !evolve;
        println("evolve: " + evolve);
    }

    private void applyRules(){
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c].applyRules(cells);
               // System.out.println("applying!");
            }
        }
        //println("done applying!");
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
