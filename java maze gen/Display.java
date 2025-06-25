import java.awt.*;

public class Display extends Canvas{

    public void paint(Graphics g){
        // i just wanna fuck a dude

        setBackground(Color.WHITE);

        g.setColor(Color.BLUE);

        for (int x = 0; x < Main._XCELLS ; x++){
            for (int y = 0 ; y < Main._YCELLS ; y++){
                //let's take into account each wall right now.
                Cell foundCell = Main.board[x][y];

                // the left wall.
                if (foundCell.left){
                    g.drawLine(x*Main._CELLSIZE,y*Main._CELLSIZE,x*Main._CELLSIZE, (y+1)*Main._CELLSIZE);
                }
                // right wall.
                if (foundCell.right){
                    g.drawLine((x+1)*Main._CELLSIZE,y*Main._CELLSIZE,(x+1)*Main._CELLSIZE,(y+1)*Main._CELLSIZE);
                }
                // up wall
                if (foundCell.up){
                    g.drawLine(x*Main._CELLSIZE,y*Main._CELLSIZE,(x+1)*Main._CELLSIZE,y*Main._CELLSIZE);
                }

                // down wall
                if (foundCell.down){
                    g.drawLine(x*Main._CELLSIZE,(y+1)*Main._CELLSIZE,(x+1)*Main._CELLSIZE,(y+1)*Main._CELLSIZE);
                }

                // that's all we really need to do here. you should watch strawberry panic, it's a nice show :)
            }
        }
        // idk
    }
}
