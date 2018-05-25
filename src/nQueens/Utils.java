package nQueens;

public interface Utils {
     static int heuristic(State state){
        int collisionCount=0;
        for(int i=0;i<state.board.length;i++){
            for(int j=0;j<state.board.length;j++){
                if(i==j)continue;
                if(state.board[i]==state.board[j])collisionCount++;
                if(state.board[i]-i==state.board[j]-j)collisionCount++;
                if(state.board[i]+i==state.board[j]+j)collisionCount++;
            }
        }
        return collisionCount/2;
    }
    static State generateRandomState(int boardSize){
         int[] board=new int[boardSize];
         for(int i=0;i<boardSize;i++){
             board[i]= (int) (Math.random()*boardSize+1);
         }
         return new State(board);
    }
    static State swap(State initialState,int i,int j){
         int buffer[]=initialState.board.clone();
         int temp=buffer[i];
         buffer[i]=buffer[j];
         buffer[j]=temp;
         return new State(buffer);
    }
    static State move(State initialState,int col,int to){
        int buffer[]=initialState.board.clone();
        buffer[col]=to;
        return new State(buffer);
    }
}
