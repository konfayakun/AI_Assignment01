package vaccumecleaner;

import java.util.*;

public class BFS_Solver {
    private long startTime;
    State goal;
    private Queue<State> fringe=new LinkedList<>();
    HashSet<State> expanded=new HashSet<>();
    List<String> possibleActions= Arrays.asList("s", "r", "d", "l", "u");
    BFS_Solver(State initialState) {
        fringe.add(initialState);
    }
    public State solve(){
        startTime=System.currentTimeMillis();
        boolean solved;
        do{
            solved=expand();
        }while(!solved);
        System.out.println("Solved in "+(System.currentTimeMillis()-startTime)/1000+" sec");
//        System.exit(0);
        System.out.println(goal);
        return goal;
    }
    boolean expand(){
        State state=fringe.remove();
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
        System.out.println(fringe.size());
        return false;
    }
}
