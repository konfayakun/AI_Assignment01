package vaccumecleaner;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface Utils {
    int dirtAmount=1,x=10,y=10;
    double dirtPercentage=0.1;
    static void dirtGenerator(State state, double dirtyPercentage){
        int sizeOfBoard=state.board.length*state.board[0].length;
        int amountOfDirt= (int) (sizeOfBoard*dirtyPercentage);
        HashSet<Integer> randomIndexes=new HashSet<>();
        while(randomIndexes.size()<amountOfDirt){
            randomIndexes.add((int) (Math.random()*sizeOfBoard));
        }
        for(int randomIndex:randomIndexes){
            state.board[randomIndex/state.board[0].length][randomIndex%state.board.length]=dirtAmount;
        }
    }
    static boolean isGoal(State state){
        return state.collectedGarbage >=(int)(state.boardSize*dirtPercentage) && restingPoints.contains(state.agentLocation);
    }
    List<Point> restingPoints=Arrays.asList(new Point(0,0),new Point(x-1,0),new Point(0,y-1),new Point(x-1,y-1));
    static int heuristic1(State state){
        return (int)(state.boardSize*dirtPercentage)-state.collectedGarbage+ distanceFromRestingPoints(state);
    }
    static int heuristic2(State state){
        int mapScore=state.visibleCells;
        int cleaningScore=(int)(state.boardSize*dirtPercentage)-state.collectedGarbage;
        int comebackSoonScore=distanceFromRestingPoints(state);

        return mapScore+cleaningScore+comebackSoonScore;
    }
//    private int mapDiscoveryScore(State state){
//
//    }
    private static int distanceFromRestingPoints(State state){
        return restingPoints.stream().mapToInt(restingPoint -> Math.abs(restingPoint.x-state.agentLocation.x)+Math.abs(restingPoint.y-state.agentLocation.y)).min().getAsInt();
    }
//    private static int addedVision(State currentState,Point newAgentLocation){
//        int added=0;
//        for(int i=-1;i<2;i++){
//            for(int j=-1;j<2;j++){
//                if(!currentState.visibleByAgent[newAgentLocation.x+i][newAgentLocation.y+j])
//                    added++;
//            }
//        }
//        return added;
//    }
}
