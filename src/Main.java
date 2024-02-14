import processing.core.PApplet;

public class Main extends PApplet {
    private final int CELL_SIZE = 20;
    private final int NUM_ROWS = 9;
    private final int NUM_COLUMNS = 19;
    private final int DELAY = 300; //milliseconds of delay between generations
    private Cell[][] cells;
    private boolean antTime;
    private Ant ant;

    //setting up PApplet
    public static Main app;
    public static void main(String[] args) {
        PApplet.main("Main");
    }
    public Main() {
        app = this;
        antTime = false;
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
                cells[r][c] = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, false);
            }
        }
    }

    //periodic
    public void draw() {
        //delay(DELAY);
        display();
        if(antTime){
            ant.move(cells);
            ant.display();
            antTime=!antTime;
        }

    }

    public void mouseClicked(){
        //which cell did you click?
        int r = mouseY/CELL_SIZE;
        int c = mouseX/CELL_SIZE;
        setup();
        ant = new Ant(0,r, c, cells, CELL_SIZE);
        antTime = true;
    }

    public void keyPressed(){
        antTime = true;

    }
//    public void keyPressed(){
//        if(key=='r'){ //set them all to dead
//            for(int r=0; r<NUM_ROWS; r++){
//                for(int c=0; c<NUM_COLUMNS; c++){
//                    cells[r][c].flip();
//                }
//            }
//        }else if(key=='n'){ //set them all to new random states
//            setup();
//        }else{
//            evolve = !evolve;
//        }
//    }

//    private void applyRules(){
//        for(int r=1; r<NUM_ROWS-1; r++){
//            for(int c=1; c<NUM_COLUMNS-1; c++){
//                cells[r][c].applyRules(cells);
//            }
//        }
//    }

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
