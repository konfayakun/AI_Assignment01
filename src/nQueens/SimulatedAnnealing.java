package nQueens;

import java.util.ArrayList;

public class SimulatedAnnealing extends HillClimbing {
    private long time;
    SimulatedAnnealing(int boardSize, long numberOfIterations, boolean showAllSolutions) {
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
                return neighborhood.get(randomIndex);
            }
        }while (time>0);
        return null;
    }
}
