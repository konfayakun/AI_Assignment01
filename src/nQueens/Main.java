package nQueens;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        int boardSize=5;
        long numberOfIterations=1000000L;
        boolean showAllSolutions=false;
        System.out.println("General parameters:");
        System.out.printf("Board size:%d ,number of iterations:%d ,show all solutions:%s \n\n",boardSize,numberOfIterations,(showAllSolutions)?"Yes":"No");
        System.out.println("Hill Climbing:\n");
        HillClimbing hc=new HillClimbing(boardSize,numberOfIterations,showAllSolutions);
        hc.solve();
        System.out.println("--------------\nHill Climbing using random restart with restart probability of 0.99999:\n");
        HillClimbing hcr=new HillClimbing_RandomRestart(boardSize,numberOfIterations,showAllSolutions,0.99999D);
        hcr.solve();
        System.out.println("--------------\nSimulated Annealing:\n");
        HillClimbing sim=new SimulatedAnnealing(boardSize,numberOfIterations,showAllSolutions);
        sim.solve();
    }



}
