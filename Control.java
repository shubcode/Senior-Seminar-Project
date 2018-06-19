import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


// this class handles the linked list of the enemy
public class Control extends JPanel
{
    static LinkedList<Enemy> alienEnemy = new LinkedList<Enemy>();


    Enemy temp;

    private int enemyControl;

    public Control()
    {
            if (alienEnemy.size() < 10) {
                addEnemy(new Enemy(randomX(), randomY()));
                addEnemy(new Enemy(randomX(), randomY()));
            }

            if (alienEnemy.size() > 15)
            {
                addEnemy(new Enemy(randomX(),randomY()));
            }



    }

    public void draw(Graphics2D g2)
    {
        for (Enemy enemy : alienEnemy)
        {
            enemy.draw(g2);
        }

    }

    public void update()
    {
        for (int i = 0; i < alienEnemy.size(); i++)
        {
            temp = alienEnemy.get(i);

            temp.update();

        }

    }

    public void addEnemy(Enemy enemy)
    {
        alienEnemy.add(enemy);
    }

    public void removeEnemy(Enemy enemy)
    {
        alienEnemy.remove(enemy);
    }

    public static LinkedList<Enemy> getEnemyBounds()
    {
        return alienEnemy;
    }


    // method for rando spawn
    public int randomX()
    {
        Random xSpawn = new Random();
        int lowX =10;
        int highX = 750;
        int xCoord = xSpawn.nextInt(highX - lowX) + lowX;
        return xCoord;
    }

    public int randomY()
    {
        Random ySpawn = new Random();
        int lowY = 10;
        int highY = 600;
        int yCoord = ySpawn.nextInt(highY - lowY) + lowY;
        return yCoord;
    }





}
