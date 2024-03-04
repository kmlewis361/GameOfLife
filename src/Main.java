import processing.core.PApplet;

public class Main extends PApplet {
    private final int CELL_SIZE = 10;
    private final int NUM_ROWS = 45;
    private final int NUM_COLUMNS = 54;
    private final int DELAY = 145; //milliseconds of delay between generations
    private Cell[][] cells;
    private boolean evolve;
    private double height = CELL_SIZE/2*Math.sqrt(3);
    private double offset = Math.sqrt(CELL_SIZE*CELL_SIZE - height*height);

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
        size(NUM_COLUMNS*CELL_SIZE*2, NUM_ROWS*CELL_SIZE*2);
    }

    //init
    public void setup() {
        MooreRules rules = new MooreRules(new int[]{3}, new int[]{2,3});
        cells = new Cell[NUM_ROWS][NUM_COLUMNS];

        for(int r=0; r<NUM_ROWS; r++){
            for(int c=0; c<NUM_COLUMNS; c++) {
                CellState state = CellState.DEAD;
                //if(r!=0 && r!=NUM_ROWS-1 && c!=0 && c!=NUM_COLUMNS-1){ //non-edges should be randomized
                if (Math.random() > 0.5) {
                    state = CellState.ALIVE;
                }
                //  }
                if (c % 2 == 0) {
                    cells[r][c] = new Cell((int)(c* (1.5 * CELL_SIZE )), (int)(r * height * 2), CELL_SIZE*2, r, c, state, rules);
                } else {
                    cells[r][c] = new Cell((int)(c* (1.5* CELL_SIZE)), (int)(r * height * 2 + height), CELL_SIZE*2, r, c, state, rules);
                }
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
       // Cell.drawHex(30,40,CELL_SIZE);
    }

    public void mouseClicked(){
        //which cell did you click?
        int c = (int)(mouseX/(int)(1.5*CELL_SIZE))/2;
        int r = 0;
        if(c%2==0){
            r = (int)(mouseY/(height*2))/2;
        }else {

            r = (int)((mouseY - height)/(height*2))/2;
        }
        println(r + ", " + c);
        cells[r][c].handleClick();
//        for(Cell[] c1 : cells){
//            for(Cell c : cells[0]){
//                if(mouseX <= (c.getX() + CELL_SIZE/2) && mouseX >= (c.getX() - CELL_SIZE/2)){
//                    if(mouseY <= (c.getY() + CELL_SIZE/2) && mouseY >= (c.getY() - CELL_SIZE/2)) {
//                       c.handleClick();
//                    }
//                }
//            }
//        }
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
