package nQueens;

import java.util.*;

public class Genetic {
    private int population,boardSize;
    private long numberOfIterations;
    private double mutationRatio,crossoverRatio;
    private boolean findAllSolutions;

    Genetic(int boardSize, long numberOfIterations, int population, double mutationRatio, double crossoverRatio, boolean findAllSolutions) {
        this.boardSize=boardSize;
        this.numberOfIterations = numberOfIterations;
        this.population = population;
        this.mutationRatio = mutationRatio;
        this.crossoverRatio = crossoverRatio;
        this.findAllSolutions=findAllSolutions;
    }
    public void solve(){
        List<State> population=createInitialPopulation(this.population,boardSize);
        population.sort(Comparator.comparingInt(Utils::heuristic));
        State best=population.get(0);
        int generation=0;
        TreeSet<State> solutions=new TreeSet<>((state1,state2)-> Arrays.compare(state1.board,state2.board));
        while (generation<numberOfIterations){
            generation++;
            List<State> newGeneration=new ArrayList<>();
            for(int i=1;i<population.size();i++){
                if(Math.random()<crossoverRatio)
                newGeneration.addAll(crossover(best,population.get(i)));
            }
            population.addAll(newGeneration);
            population=killUnderdogs(population);
            best=population.get(0);
            if(Utils.heuristic(best)==0){
                if(!findAllSolutions) {
                    System.out.println("solution found!");
                    System.out.println(best);
                    break;
                }
                else{
                    int in=0;
                    while(Utils.heuristic(population.get(in))==0) {
                        solutions.add(population.get(in));
                        in++;
                        if(in>population.size()-1)break;
                    }
                }
            }
        }
        if(findAllSolutions) {
            System.out.println(solutions.size()+" solutions found!");
            System.out.println(solutions);
        }
    }
    private ArrayList<State> createInitialPopulation(int populationCount,int boardSize){
        ArrayList<State> initialPopulation=new ArrayList<>();
        for(int i=0;i<populationCount;i++){
            initialPopulation.add(Utils.generateRandomState(boardSize));
        }
        return initialPopulation;
    }
    private List<State> killUnderdogs(List<State> population){  //simply save best 1000 and kill others!
        population.sort(Comparator.comparingInt(Utils::heuristic));
        return population.subList(0,this.population);
    }
    private List<State> crossover(State parent1,State parent2){
        int crossoverPoint= (int) (Math.random()*boardSize-1)+1;
        int[] child1=new int[boardSize];
        int[] child2 = new int[boardSize];
        for(int i=0;i<boardSize;i++){
            child1[i]=(i<crossoverPoint)?parent1.board[i]:parent2.board[i];
            child2[i]=(i<crossoverPoint)?parent2.board[i]:parent1.board[i];
        }
        if(Math.random()<mutationRatio){
            int mutationIndex=(int) (Math.random()*boardSize);
            int mutationValue=(int) (Math.random()*boardSize)+1;
            child1[mutationIndex]=mutationValue;
        }
        if(Math.random()<mutationRatio){
            int mutationIndex=(int) (Math.random()*boardSize);
            int mutationValue=(int) (Math.random()*boardSize)+1;
            child2[mutationIndex]=mutationValue;
        }
        List<State> children=new ArrayList<>();
        children.add(new State(child1));
        children.add(new State(child2));
        return children;
    }

}
