import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// This is the Demo class
public class Test extends JPanel implements ActionListener
{
    Timer testTimer;

    Control c;
    Player p;
    Bullet b;
    public Test()
    {
        setFocusable(true);
        testTimer = new Timer(10, this);
        testTimer.start();

        p = new Player(300,600);
        c = new Control();
       // b = new Bullet();

        addKeyListener(new playerInput(p));
        //addKeyListener(new bulletInput(b));

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;


        p.draw(g2);
        c.draw(g2);
       // b.draw(g2);

        //g.drawString("Hello",10,10);
    }

    public void actionPerformed(ActionEvent ee)
    {

        p.update();
        c.update();

        repaint();
    }







}


