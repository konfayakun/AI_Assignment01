package nQueens;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        HillClimbing hc=new HillClimbing(5,1000000L,false);
        hc.solve();
        System.out.println("--------------");
        HillClimbing hcr=new HillClimbing_RandomRestart(5,1000000L,true,0.99999D);
        hcr.solve();
    }



}
