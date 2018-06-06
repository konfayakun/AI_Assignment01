package vaccumecleaner;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.ToIntFunction;

class Greedy_Solver extends BFS_Solver{
    private Queue<State> fringe;
    Greedy_Solver(State initialState, ToIntFunction<State> function) {
        super(initialState);
        fringe=new PriorityQueue<>(Comparator.comparingInt(function));
        this.fringe.add(initialState);
    }
    @Override
    boolean expand(){
        State state=this.fringe.remove();
        expanded.add(state);
        if(Utils.isGoal(state)){
            goal=state;
            return true;
        }
        Collections.shuffle(possibleActions);
        for(String action:possibleActions){
            State newState=state.action(action);
            if(newState==null || expanded.contains(newState)){
                continue;
            }
            fringe.add(newState);
        }
        System.out.println(this.fringe.size());
        return false;
    }
}
