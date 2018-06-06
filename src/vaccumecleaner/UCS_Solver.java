package vaccumecleaner;

import java.util.*;

class UCS_Solver extends BFS_Solver{
    private Queue<State> fringe=new PriorityQueue<>(Comparator.comparingInt(state->state.cost));
    UCS_Solver(State initialState) {
        super(initialState);
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
