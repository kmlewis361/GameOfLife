public class MooreRules {
    private static final int NUM_NEIGHBORS = 7;
    private boolean[] birthRules; //each index corresponds to a number of live neighbors
    private boolean[] survivalRules; //same as above

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        //birthNeighbors and survivalNeighbors contain ints that correspond to acceptable number of live neighbors
        birthRules = new boolean[NUM_NEIGHBORS];
        survivalRules = new boolean[NUM_NEIGHBORS];

        for(int neighbors: birthNeighbors){ //go through birthNeighbors and convert every int to an index. set that index in birthRules to true
            birthRules[neighbors] = true;
        }
        for(int neighbors: survivalNeighbors){ //same as above
            survivalRules[neighbors] = true;
        }
    }

    public boolean shouldBeBorn(int liveNeighbors){
        return birthRules[liveNeighbors];
    }

    public boolean shouldSurvive(int liveNeighbors){
        return survivalRules[liveNeighbors];
    }

    public CellState applyRules(CellState cellState, int liveNeighbors){
        if(cellState == CellState.DEAD && shouldBeBorn(liveNeighbors) == true){
            return CellState.WILL_REVIVE;
        }else if(cellState == CellState.ALIVE && shouldSurvive(liveNeighbors) == false){
            return CellState.WILL_DIE;
        }else{
            return cellState;
        }
    }
}
