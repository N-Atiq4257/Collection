public class Cell {

    public int x;
    public int y;

    // these are the walls for this cell, which can be altered by the maze generator.
    public boolean left = true;
    public boolean right = true;
    public boolean up = true;
    public boolean down = true;

    public boolean visited = false;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
      
    }

}
