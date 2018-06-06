package vaccumecleaner;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }
    private Main(){
        int board[][]=new int[Utils.x][Utils.y];
        State initialState= new State(board,new Point(1,1));
        Utils.dirtGenerator(initialState,Utils.dirtPercentage);
        System.out.println(initialState);

        BFS_Solver bfsSolver=new BFS_Solver(initialState);
        DFS_Solver dfsSolver=new DFS_Solver(initialState);
        UCS_Solver ucsSolver=new UCS_Solver(initialState);
        IterativeDeepening id=new IterativeDeepening(initialState);
        Greedy_Solver greedySolver1=new Greedy_Solver(initialState,Utils::heuristic1);
        Greedy_Solver greedySolver2=new Greedy_Solver(initialState,Utils::heuristic2);

        System.out.println(bfsSolver.solve().path);
        System.out.println(dfsSolver.solve().path);
        System.out.println(ucsSolver.solve().path);
        System.out.println(id.solve().path);
        System.out.println(greedySolver1.solve().path);
        System.out.println(greedySolver2.solve().path);
    }

}
