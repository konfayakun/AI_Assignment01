package nQueens;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private Main() {
        int boardSize=8;
        long numberOfIterations=3000L;
        boolean showAllSolutions=true;
        System.out.println("General parameters:");
        System.out.printf("Board size:%d ,number of iterations:%d ,show all solutions:%s \n\n",boardSize,numberOfIterations,(showAllSolutions)?"Yes":"No");
        System.out.println("Hill Climbing:\n");
        HillClimbing hc=new HillClimbing(boardSize,numberOfIterations,showAllSolutions);
        hc.solve();
        System.out.println("--------------\nHill Climbing using random restart with restart probability of 0.99:\n");
        HillClimbing hcr=new HillClimbing_RandomRestart(boardSize,numberOfIterations,showAllSolutions,0.99D);
        hcr.solve();
        System.out.println("--------------\nSimulated Annealing:\n");
        HillClimbing sim=new SimulatedAnnealing(boardSize,numberOfIterations,showAllSolutions);
        sim.solve();
        System.out.println("--------------\nGenetic:\n");
        Genetic gen=new Genetic(boardSize,numberOfIterations,1000,0.02D,0.8D,true);
        gen.solve();
    }



}
