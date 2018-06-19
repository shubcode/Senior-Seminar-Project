import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class playerInput extends KeyAdapter
{
    //Player Object
    Player p;
    //Bullet b;


    //Constructor
    public playerInput(Player p)
    {
        this.p = p;
        //this.b = b;
    }

    public void keyPressed(KeyEvent e)
    {
        p.keyPressed(e);
       // b.keyPressed(e);

    }
    public void keyReleased(KeyEvent e)
    {
        p.keyReleased(e);
       // b.keyReleased(e);
    }




}
