package vaccumecleaner;

import java.util.*;

public class DFS_Solver {
    private long startTime;
    private State goal;
    private Stack<State> fringe=new Stack<>();
    private HashSet<State> expanded=new HashSet<>();
    private List<String> possibleActions= Arrays.asList("s", "r", "d", "l", "u");

    DFS_Solver(State initialState) {
        fringe.add(initialState);
    }
    public State solve(){
        startTime=System.currentTimeMillis();
        while(!fringe.isEmpty())
            expand();
        System.out.println(goal);
        return goal;
    }
    private void expand(){
        State state=fringe.pop();
        expanded.add(state);
        if(Utils.isGoal(state)){
            goal=state;
            fringe.clear();
            return;
        }
        Collections.shuffle(possibleActions);
        for(String action:possibleActions){
            State newState=state.action(action);
            if(newState==null || expanded.contains(newState)){
                continue;
            }
            fringe.push(newState);
        }
    }
}
