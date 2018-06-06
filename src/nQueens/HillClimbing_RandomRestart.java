package nQueens;

import java.util.*;
import java.util.stream.Collectors;

public class HillClimbing_RandomRestart extends HillClimbing{
    private int restartCount=-1;
    private double probabilityOfRestart;
    HillClimbing_RandomRestart(int boardSize, long numberOfIterations, boolean showAllSolutions, double probabilityOfRestart) {
        super(boardSize,numberOfIterations,showAllSolutions);
        this.probabilityOfRestart=probabilityOfRestart;
        this.verbMode=false;
    }

    @Override
    public void solve() {
        TreeSet<State> minimas=new TreeSet<>((state1,state2)->(Arrays.compare(state1.board,state2.board)));
        do{
            super.solve();
            minimas.add(new State(this.currentState.board));
            if(!showAllSolutions && Utils.heuristic(this.currentState)==0)break;
            restartCount++;
        }while (Math.random()<probabilityOfRestart);
        List<State> solutions=minimas.stream().filter(state->(Utils.heuristic(state)==0)).collect(Collectors.toList());
        System.out.println("State Changes: "+this.stateChanges+" Restart Counts: "+restartCount);
        System.out.println(solutions.size()+" solutions found!");
        if(solutions.size()>0)
            System.out.println(solutions);
        else{
            State best=Collections.min(minimas,Comparator.comparingInt(Utils::heuristic));
            System.out.println("best so far:");
            System.out.println(best+" with collision number of "+Utils.heuristic(best));
        }
    }
}
