import javax.swing.*;
import java.awt.*;
import java.util.Random;


// this class handles all of the actions of the enemies
// they will be put in an linked list

// need to impelement action and key listener
// extends JPanel
// implements ActionListener
public class Enemy
{

    //LinkedList<Enemy> alienEnemy = new LinkedList<Enemy>();

    //Timer t2 = new Timer(5, this);
    private int enemySpeedX = 5;
    private int enemySpeedY = 5;
    private int x;
    private int y;
    private String image = "src/aliens.png";


    public Enemy(int x, int y)
    {
       // t2.start();
        this.x =x;
        this.y = y;
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(getImage(),x , y,null);
        g2.draw(getBounds());

    }

    public Image getImage()
    {
       // ImageIcon i = new ImageIcon("src/aliens.png");
        ImageIcon i = new ImageIcon(image);
        return i.getImage();
    }

    public void update()
    {
        x+=enemySpeedX;
        y+=enemySpeedY;

       if (x > 720)
        {
            enemySpeedX = -5;

        }

        if (x < 0)
        {
            enemySpeedX = 5;

        }

        if (y > 450)
        {
            enemySpeedY = -5;

        }

        if (y < 0)
        {
            enemySpeedY = 5;

        }

    }



    public Rectangle getBounds()
    {
        return new Rectangle(x,y,64,64);
    }


    //    public void actionPerformed(ActionEvent e)
//    {
//        //System.out.println("Enemy Action Performed Method");
//
//        x+=enemySpeedX;
//        y+=enemySpeedY;
//
//        if (x > 720)
//        {
//            enemySpeedX = -5;
//        }
//
//        if (x < 0)
//        {
//            enemySpeedX = 5;
//
//        }
//
//        if (y > 450)
//        {
//            enemySpeedY = -5;
//        }
//
//        if (y < 0)
//        {
//            enemySpeedY = 5;
//        }
//    }

} // end of class Enemy
