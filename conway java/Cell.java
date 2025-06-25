public class Cell {
    private boolean alive;
    public int x;
    public int y;

    public Cell(int x, int y, boolean alive){
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    // you kill yourself :)
    public void die(){
        this.alive = false;
    }

    // Swiftcast + Ascend on this hoe
    public void resurrect(){
        this.alive = true;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public String toString(){
        return "Cell located at x: " + this.x + ", y: " + this.y + " Living: " + this.getAlive() ;
    }
}
