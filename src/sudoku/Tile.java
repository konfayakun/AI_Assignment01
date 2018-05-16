package sudoku;

import java.util.ArrayList;

public class Tile implements Cloneable{
    ArrayList<Integer> options;
    int tileIndex,value;

    public Tile(ArrayList<Integer> options, int tileIndex,int value) {
        this.options = (ArrayList<Integer>) options.clone();
        this.tileIndex = tileIndex;
        this.value=value;
    }

    @Override
    protected Object clone() {
        return new Tile(this.options,this.tileIndex,this.value);
    }
}
