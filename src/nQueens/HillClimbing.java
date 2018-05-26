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
        ArrayList<State> neighborhood;
        for(int step=0;step<numberOfIterations;step++){
            neighborhood=getNeighborhood(currentState);
            State newState=getNext(neighborhood,currentState);
            if(newState!=null) {
                currentState = newState;
                stateChanges++;
            }
            else{
                if(verbMode) {
                    System.out.println("stuck in local minimum!");
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
        }
    }
    protected ArrayList<State> getNeighborhood(State currentState){
        ArrayList<State> neighborhood=new ArrayList<>();
        for(int i=0;i<boardSize-1;i++){
            for(int j=i+1;j<boardSize;j++){
                if(currentState.board[i]==j)continue;
//                    if(Math.random()>0.5D)
                neighborhood.add(Utils.swap(currentState,i,j)); //swap strategy
                neighborhood.add(Utils.move(currentState,i,j));
            }
        }
        return neighborhood;
    }
    protected State getNext(ArrayList<State> neighborhood,State currentState){
        State candidate=Collections.min(neighborhood,Comparator.comparingInt(Utils::heuristic));
        if(Utils.heuristic(candidate)<Utils.heuristic(currentState)) {
            return candidate;
        }
        return null;
    }
}
