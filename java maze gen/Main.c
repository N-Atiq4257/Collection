import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static final int _CELLSIZE = 10;
    static final int _XCELLS = 150;
    static final int _YCELLS = 80;

    public static Cell[][] board = new Cell[_XCELLS][_YCELLS];
    private static Stack<Cell> cellStack = new Stack<>();

    // this doesn't matter alot dw.
    private static final byte[] xMoves = {-1,1};
    private static final byte[] yMoves = {-1,1};

    // this will remove the walls between two cells.
    public static LinkedList<Cell> getCellNeighbors(Cell cell){
        LinkedList<Cell> result = new LinkedList<>();

        for (int x : xMoves){
            // this gets the left and right cells, makes sure that they're in bounds
            int xPos = cell.x + x;

            if(xPos>=0 && xPos < _XCELLS){
                Cell foundCell = board[xPos][cell.y];
                if (!foundCell.visited){
                    result.add(foundCell);
                }
            }

        }

        for (int y : yMoves){
            // this gets the up and down cells, makes sure that they're in bounds
            int yPos = cell.y + y;

            if(yPos>=0 && yPos < _YCELLS){
                Cell foundCell = board[cell.x][yPos];
                if (!foundCell.visited){
                    result.add(foundCell);
                }
            }

        }

        return result;
    }
    public static void removeCellWalls(Cell cell1, Cell cell2){
        // let's check the x walls. or something idk idk idk
        // code for this is always tedious i'm sorry >_<

        //this is where cell 2 is to the right of cell 1.
        if (cell2.x - cell1.x == 1){
            cell2.left = false;
            cell1.right = false;
        }
        // cell 2 is to the left of cell 1
        if (cell2.x - cell1.x == -1){
            cell2.right = false;
            cell1.left = false;
        }
        // cell 2 is below cell 1
        if (cell2.y - cell1.y == 1){
            cell2.up = false;
            cell1.down = false;
        }
        // cell 2 is above cell 1
        if (cell2.y - cell1.y == -1){
            cell2.down = false;
            cell1.up = false;
        }
    }

    public static void generateMaze(){
        // if this stack no longer has any new elements that can be added.
        do{
            // visit me!
            Cell currentCell = cellStack.peek();
            currentCell.visited = true;
            LinkedList<Cell> neighbors = getCellNeighbors(currentCell);

            // let's look at the size here.
            if (neighbors.size() != 0 ){
                // well, let's get a random neighbor.
                int randomIndex = ThreadLocalRandom.current().nextInt(0,neighbors.size());
                Cell foundCell = neighbors.get(randomIndex);

                //let's remove their walls ^_^
                removeCellWalls(currentCell, foundCell);

                cellStack.push(foundCell);

            } else if(cellStack.size() > 1 ){
                // no cell! backtrace >o<
                cellStack.pop();

            }
            // end of conditions
        } while (cellStack.size() > 1);
        // end of loop
    }

    public static void main(String[] args){
        // set up the board real quick first.
        for (int x = 0; x < _XCELLS; x++){
            for(int y = 0 ; y < _YCELLS ; y++){
                board[x][y] = new Cell(x,y);
            }
        }
        // this generates the maze with a set up first position.
        int startX = ThreadLocalRandom.current().nextInt(0,_XCELLS);
        int startY = ThreadLocalRandom.current().nextInt(0,_YCELLS);
        cellStack.push(board[startX][startY]);

        generateMaze();

        // they're things.
        Display dispy = new Display();
        JFrame framy = new JFrame();
        framy.setTitle(" ^_^ !Maze Generator! ^_^ ");

        // in all seriousness this just adds the frame from above.
        // this has some bad implications, REALLY EFFING BAD implications.
        framy.add(dispy);
        // now give it some properties.
        framy.setSize(_CELLSIZE*_XCELLS,_CELLSIZE*_YCELLS + 25);
        framy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        framy.setVisible(true);

    }
}
