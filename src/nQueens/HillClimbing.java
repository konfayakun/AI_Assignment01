package nQueens;

import java.util.*;

public class HillClimbing {
    int boardSize;
    long numberOfIterations,stateChanges=0L;
    boolean showAllSolutions;
    boolean verbMode=true;
    State currentState;

    public HillClimbing(int boardSize, long numberOfIterations, boolean showAllSolutions) {
        this.boardSize = boardSize;
        this.numberOfIterations = numberOfIterations;
        this.showAllSolutions = showAllSolutions;
    }
    public void solve(){
        currentState=Utils.generateRandomState(boardSize);
        ArrayList<State> neighborhood=new ArrayList<>();
        for(int step=0;step<numberOfIterations;step++){
            neighborhood.clear();
            for(int i=0;i<boardSize-1;i++){
                for(int j=i+1;j<boardSize;j++){
                    if(currentState.board[i]==j)continue;
//                    if(Math.random()>0.5D)
                    neighborhood.add(Utils.swap(currentState,i,j)); //swap strategy

                    neighborhood.add(Utils.move(currentState,i,j));
                }
            }
            State candidate=Collections.min(neighborhood,Comparator.comparingInt(Utils::heuristic));
            if(Utils.heuristic(candidate)<Utils.heuristic(currentState)) {
                currentState = candidate;
                stateChanges++;
            }
            else{
                if(verbMode) {
                    System.out.println("reached to a local minimum!");
                    System.out.println(currentState);
                    System.out.println(Utils.heuristic(currentState));
                }
                break;
            }
            if(Utils.heuristic(currentState)==0){
                if(verbMode) {
                    System.out.println("solved!");
                    System.out.println(currentState);
                }
                if(!showAllSolutions) break;
            }
//            System.out.println(Utils.heuristic(currentState));
        }
//        System.out.println("Iteration limit has been reached! Cannot solve problem in this limit!");
    }
}
