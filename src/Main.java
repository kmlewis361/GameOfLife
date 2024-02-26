import processing.core.PApplet;

public class Main extends PApplet {
    private final int CELL_SIZE = 20;
    private final int NUM_ROWS = 9;
    private final int NUM_COLUMNS = 19;
    private final int DELAY = 145; //milliseconds of delay between generations
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
        MooreRules rules = new MooreRules(new int[]{3}, new int[]{2,3});
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                CellState state = CellState.DEAD;
               //if(r!=0 && r!=NUM_ROWS-1 && c!=0 && c!=NUM_COLUMNS-1){ //non-edges should be randomized
                    if(Math.random()>0.5){
                        state = CellState.ALIVE;
                    }
              //  }
                cells[r][c] = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, state, rules);
            }
        }
    }

    //periodic
    public void draw() {
        if(evolve){
            applyRules();
            evolve();
            delay(DELAY);
        }
        display();
    }

    public void mouseClicked(){
        //which cell did you click?
        int r = mouseY/CELL_SIZE;
        int c = mouseX/CELL_SIZE;
        cells[r][c].handleClick();
    }

    public void keyPressed(){
        if(key=='r'){ //set them all to dead
            for(int r=0; r<NUM_ROWS; r++){
                for(int c=0; c<NUM_COLUMNS; c++){
                    cells[r][c].setState(CellState.DEAD);
                }
            }
        }else if(key=='n'){ //set them all to new random states
            setup();
        }else{
            evolve = !evolve;
        }
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
