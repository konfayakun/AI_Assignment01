package nQueens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SimulatedAnnealing extends HillClimbing {
    long time;
    public SimulatedAnnealing(int boardSize, long numberOfIterations, boolean showAllSolutions) {
        super(boardSize, numberOfIterations, showAllSolutions);
        time=numberOfIterations;
        verbMode=true;
    }

    @Override
    protected State getNext(ArrayList<State> neighborhood, State currentState) {
        int currentHeuristic = Utils.heuristic(currentState);
        int randomIndex;
        do {
            time--;
            randomIndex = (int) (Math.random() * neighborhood.size());
            if (Utils.heuristic(neighborhood.get(randomIndex)) < currentHeuristic ||
                    Math.random() < (time + 0D) / numberOfIterations) {
//                System.out.println(Math.random()+"  "+(time + 0D) / numberOfIterations);
//                System.out.println(neighborhood.get(randomIndex)+"  "+Utils.heuristic(neighborhood.get(randomIndex))+"  "+currentHeuristic);
                return neighborhood.get(randomIndex);
            }
        }while (time>0);
//        System.out.println("Iteration limit has been reached! ");
//        System.out.println("best so far: "+currentState+" with collision number of "+currentHeuristic);
        return null;
    }
}
