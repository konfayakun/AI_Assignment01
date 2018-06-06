package vaccumecleaner;

import java.awt.*;
import java.util.Arrays;

public class State implements Cloneable{
    int[][] board;
    int collectedGarbage,boardSize,cost,visibleCells;
    String path;
    boolean[][] visibleByAgent;

    Point agentLocation;

    public State(int[][] board,Point agentLocation){
        this(board,agentLocation,"",0);
    }

    public State(int[][] board, Point agentLocation,String path,int collected) {
        this(board,agentLocation,path,collected,0);
    }
    public State(int[][] board, Point agentLocation,String path,int collected,int cost) {
        this.board = board;
        this.agentLocation = agentLocation;
        this.path=path;
        this.collectedGarbage=collected;
        this.cost=cost;
        boardSize= board.length*board[0].length;
        visibleByAgent=new boolean[board.length][board[0].length];
        updateAgentVision();
    }

    public State action(String action){
        int newBoard[][]=new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++)
            newBoard[i]=board[i].clone();
        Point newAgentLocation= (Point) agentLocation.clone();
        String newPath=path+action;
        int newCollected=collectedGarbage;
        int cost=2;
        try {
            switch (action) {
                case "r":
                    newBoard[agentLocation.x + 1][agentLocation.y] = board[agentLocation.x + 1][agentLocation.y];
                    newAgentLocation = new Point(agentLocation.x + 1, agentLocation.y);
                    break;
                case "l":
                    newBoard[agentLocation.x - 1][agentLocation.y] = board[agentLocation.x - 1][agentLocation.y];
                    newAgentLocation = new Point(agentLocation.x - 1, agentLocation.y);
                    break;
                case "u":
                    newBoard[agentLocation.x][agentLocation.y - 1] = board[agentLocation.x][agentLocation.y - 1];
                    newAgentLocation = new Point(agentLocation.x, agentLocation.y - 1);
                    break;
                case "d":
                    newBoard[agentLocation.x][agentLocation.y + 1] = board[agentLocation.x][agentLocation.y + 1];
                    newAgentLocation = new Point(agentLocation.x, agentLocation.y + 1);
                    break;
                case "s":
                    if(newBoard[agentLocation.x][agentLocation.y]>0){
                        newBoard[agentLocation.x][agentLocation.y]--;
                        newCollected++;
                        cost=1;
                    }
            }
            return new State(newBoard,newAgentLocation,newPath,newCollected,this.cost+cost);
        }catch (Exception ignored){}
     return null;
    }

    private void updateAgentVision(){
        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                try {
                    if(!visibleByAgent[agentLocation.x + i][agentLocation.y + j]){
                        visibleByAgent[agentLocation.x + i][agentLocation.y + j]=true;
                        visibleCells++;
                    }
                }catch (Exception ignored){}
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder ret= new StringBuilder();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(agentLocation.x==j&& agentLocation.y==i) ret.append("A");
                else if(Utils.restingPoints.contains(new Point(j,i))) ret.append("R");
                else ret.append(board[i][j]);
                ret.append(" ");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof State && (toString().equals(obj.toString()));
    }

    @Override
    public int hashCode() {
        return (Arrays.deepHashCode(board)+agentLocation.hashCode());
    }
}
