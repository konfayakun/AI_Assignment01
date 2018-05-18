package sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class main {
    int method=0;
    final String[] METHOD_NAMES={"naive","mrv","mrv+lcv"};
    Stack<State> fringe=new Stack<>();
    long expandedStatesCount=0L,backtracks=0L,sum=0L;
    long startTime;
    ArrayList<String> details=new ArrayList<>();

    public main() throws IOException {
        System.out.println("salam");
        ArrayList<Tile> bufferOptions=new ArrayList<>();
        Integer varDomain[]={1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> variableDomain= new ArrayList<>();
        Arrays.stream(varDomain).forEach(e->variableDomain.add(e));
        for(int i=0;i<81;i++){
            bufferOptions.add(new Tile((ArrayList<Integer>) variableDomain.clone(),i,0));
        }
        List<String> initialBoards= Files.readAllLines(Paths.get("magictour.txt"));
        for(method=0;method<METHOD_NAMES.length;method++) {
            int ind=0;
            for (String board : initialBoards) {
                ind++;
                State initialState = new State(bufferOptions);
                int index = -1;
                for (char num : board.toCharArray()) {
                    index++;
                    if (num == '.') continue;
                    initialState = applyOption(initialState, index, num - '0');
                }
                startTime = System.currentTimeMillis();
                expandedStatesCount = 0L;
                backtracks = 0;
//                System.out.println(initialState);
                fringe.add(initialState);
                while (!fringe.isEmpty())
                    expand();

            }
            System.out.println(METHOD_NAMES[method]+" has been finished");
            Files.write(Paths.get(METHOD_NAMES[method] + "_data.txt"), details, StandardOpenOption.CREATE);
            details.clear();
        }
        System.out.println(sum);
    }
    void expand(){
        State finalState=fringe.peek();
        expandedStatesCount++;
        int index= getNext(finalState,method);
        if(index==82){
            long elapsedTime=(System.currentTimeMillis()-startTime);
            sum+=finalState.tiles.get(0).value*100+finalState.tiles.get(1).value*10+finalState.tiles.get(2).value;
//            System.out.println("solved!");
//            System.out.println(finalState);
//            System.out.println("expanded states :"+expandedStatesCount);
//            System.out.println("backtracks :"+backtracks);
//            System.out.println("solve time in ms: "+elapsedTime);
            details.add(expandedStatesCount+","+backtracks+","+elapsedTime);
            fringe.removeAllElements();
            return;
        }
        if(finalState.tiles.get(index).options.isEmpty()){
            fringe.pop();
            backtracks++;
            return;
        }
        Tile tile=finalState.tiles.get(index);
        int currentStep;
        if(method==3) { //lcv
            int bestValue = -1, minConstraining = Integer.MAX_VALUE;
            for (int value : tile.options) {
                int bufferConstraining = 0;
                int i = index / 9, j = index % 9;
                for (int offset = 0; offset < 9; offset++) {
                    if (finalState.tiles.get((offset) * 9 + j).options.contains(Integer.valueOf(value)))
                        bufferConstraining++; //row
                    if (finalState.tiles.get(i * 9 + offset).options.contains(Integer.valueOf(value)))
                        bufferConstraining++; //col
                }
                for (int xoffset = 0; xoffset < 3; xoffset++) {
                    for (int yoffset = 0; yoffset < 3; yoffset++) {
                        if (finalState.tiles.get(((i / 3) * 3 + xoffset) * 9 + (j / 3) * 3 + yoffset).options.remove(Integer.valueOf(value)))
                            bufferConstraining++; //block
                    }
                }
                if (minConstraining > bufferConstraining) {
                    minConstraining = bufferConstraining;
                    bestValue = value;
                }
            }
            tile.options.remove(Integer.valueOf(bestValue)); // remove current step form top stack state
            currentStep=bestValue;
        }
        else
            currentStep=tile.options.remove(0); // remove current step form top stack state
        fringe.push(applyOption(finalState,tile.tileIndex,currentStep));
        expand();
    }
    State applyOption(State initial, int tileIndex, int currentStep){
        State newState=new State(initial);
        int i=tileIndex/9,j=tileIndex%9;
        newState.tiles.get(tileIndex).value=currentStep;
        for(int offset=0;offset<9;offset++){
            newState.tiles.get((offset)*9+j).options.remove(Integer.valueOf(currentStep)); //row
            newState.tiles.get(i*9+offset).options.remove(Integer.valueOf(currentStep)); //col
        }
        for(int xoffset=0;xoffset<3;xoffset++) {
            for (int yoffset = 0; yoffset < 3; yoffset++) {
                newState.tiles.get(((i/3)*3+xoffset)*9+(j/3)*3+yoffset).options.remove(Integer.valueOf(currentStep)); //block
            }
        }
        return newState;
    }

    int getNext(State state,int method){
        List<Tile> emptyTiles=state.tiles.stream().filter(tile-> tile.value==0).collect(Collectors.toList()); // get empty tiles
        if(emptyTiles.isEmpty())return 82;                                                                   // solved if all tiles are not empty
        Comparator<Tile> comparator=null;
        if(method==0)
            comparator=Comparator.comparingInt(tile->tile.tileIndex);
        else
            comparator=Comparator.comparingInt(tile->tile.options.size());
        return Collections.min(emptyTiles,comparator).tileIndex;
    }


    public static void main(String[] args) throws IOException {
        new main();
    }
}
