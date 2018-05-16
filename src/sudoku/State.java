package sudoku;

import java.util.ArrayList;

public class State {
    ArrayList<Tile> tiles=new ArrayList<>(); // indexed from 1 to 81 each accorded to one tile
    public State(ArrayList<Tile> tiles){
        tiles.forEach(tile->this.tiles.add(((Tile) tile.clone())));
    }
    public State(State state){
        this(state.tiles);
    }

    @Override
    public String toString() {
        String ret="-----------------\n";
        for(Tile tile:tiles){
            ret+=(tile.value==0)?"- ":(tile.value+" ");
            if(tile.tileIndex%9==8) ret+="\n";
        }
        ret+="-----------------\n";
        return ret;
    }
}
