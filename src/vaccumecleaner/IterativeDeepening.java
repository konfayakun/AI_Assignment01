package vaccumecleaner;

import java.util.*;

public class IterativeDeepening {
    private long startTime;
    private State goal,initialState;
    private Stack<State> fringe=new Stack<>();
    private HashSet<State> expanded=new HashSet<>();
    private List<String> possibleActions= Arrays.asList("s", "r", "d", "l", "u");
    private int depthLimit=1;
    private boolean solved=false;

    private void increaseDepthLimit(int amount){
        fringe.clear();
        expanded.clear();
        fringe.add(initialState);
        depthLimit+=amount;
        System.out.println("Depth limit increased to "+depthLimit);
    }

    IterativeDeepening(State initialState) {
        this.initialState=initialState;
        fringe.add(initialState);
    }

    public State solve(){
        startTime=System.currentTimeMillis();

        while (!solved) {
            while (!fringe.isEmpty()) {
                expand();
            }
            increaseDepthLimit(1);
        }
        return goal;
    }

    private void expand(){
        State state=fringe.pop();
        expanded.add(state);
        if(Utils.isGoal(state)){
            goal=state;
            fringe.clear();
            System.out.println(goal);
            solved=true;
//            System.exit(0);
            return;
        }
        if(state.path.length()>=depthLimit) return;
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
