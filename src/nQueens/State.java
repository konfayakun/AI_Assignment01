package nQueens;

import java.util.Arrays;

public class State{
    int board[];

    public State(int[] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return Arrays.toString(board);
    }

}
