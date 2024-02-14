import processing.core.PApplet;

public class Main extends PApplet {
    private final int CELL_SIZE = 5;
    private final int NUM_ROWS = 200;
    private final int NUM_COLUMNS = 200;
    private Cell[][] cells;
    private Ant ant;

    //setting up PApplet
    public static Main app;
    public static void main(String[] args) {
        PApplet.main("Main");
    }
    public Main() {
        app = this;
    }

    //put size here (not in setup)
    public void settings() {
        size(NUM_COLUMNS*CELL_SIZE, NUM_ROWS*CELL_SIZE);
    }

    public void setup() {
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c] = new Cell(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, r, c, false);
            }
        }
    }

    public void draw() {
        display();
        if(ant!= null){
            ant.move(cells);
            ant.display();
        }
    }

    public void mouseClicked(){
        //which cell did you click?
        int r = mouseY/CELL_SIZE;
        int c = mouseX/CELL_SIZE;
        setup();
        ant = new Ant(180,r, c, cells, CELL_SIZE);
    }

    private void display(){
        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++){
                cells[r][c].display();
            }
        }
    }

}
