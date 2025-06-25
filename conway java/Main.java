import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.System.*;
class Main {

    static final int _XSIZE = 50;
    static final int _YSIZE = 25;

    static final int _FRAMERATE = 60;

    static final int _BOARDSIZEX = 1200;
    static final int _BOARDSIZEY = 600;

    static final boolean _INCLUDELINES = false;

    static TimeUnit sleepy = TimeUnit.MILLISECONDS;

    // set up the board for this thingy :)
    static Cell[][] board = new Cell[_XSIZE][_YSIZE];
    public static void main(String[] args) {
        Random random = new Random();
        try{
            // first let's start setting up the cells.
            for(int x = 0; x<_XSIZE;x++){
                for (int y = 0; y<_YSIZE;y++){
                    // juust set up each position ^o^
                    float rand = random.nextFloat();

                    if (rand>=.9){
                        board[x][y] = new Cell(x,y, true);
                    } else{
                        board[x][y] = new Cell(x,y, false);
                    }
                }
            }

            // let's display a window right now ^_^
            Display dispy = new Display();
            JFrame frame = new JFrame();
            frame.setTitle("Conway's Game of Life");

            // in all seriousness this just adds the frame from above.
            frame.add(dispy);
            // now give it some properties.
            frame.setSize(_BOARDSIZEX,_BOARDSIZEY+30);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);

            do{
                // essential make her sleep for a frame.
                sleepy.sleep(1000/_FRAMERATE);

                // repaint time
                dispy.revalidate();
                dispy.repaint();
            } while(true);


        }catch(Exception e){
            out.println(e);
            out.println("oh what the fuck?");

        }

    }
}
