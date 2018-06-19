import com.company.DBMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

// this class handles all of the player actions

public class Player extends JPanel
{
    private String playerImage = "src/rocket.png";
    private LinkedList<Enemy> e = Control.getEnemyBounds();

    private LinkedList<Rectangle> bullets = new LinkedList<Rectangle>();

    private Control c;


    // x and y coordinates
    int x = 0;
    int y = 0;
    // x / y change of speed
    int dx = 0;
    int dy = 0;

    // bullet location
    int bYLOC;
    int bXLOC;
    Rectangle bullet;

    int healthPack = 5;


    boolean shot = false;
    boolean readyFire = false;

    int Score;
    int Health = 100;
    int healthPackUI = healthPack;
    String Name;

    int bys = 30;


    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    // handles all of the drawing of images for the game

    public void draw(Graphics2D g2)
    {
        g2.drawImage(getImage(),x,y,this);
        g2.draw(getBounds());

        g2.drawString("Score: " + Score, 400, 10);
        g2.drawString("Health: " + Health, 10, 10);
        g2.drawString("Health Pack: " + healthPackUI, 100, 10);

        if (shot == true)
        {
            g2.setColor(Color.BLACK);
            for (Rectangle bullet:bullets) {
                g2.fillRect(bullet.x, bullet.y, 3, 5);
            }
            for(int i = 0; i < e.size(); i++)
            {
                for(Rectangle bullet: bullets){
                if (bullet.intersects(e.get(i).getBounds()))
                {
                    System.out.println("COLLISION");
                    e.remove(e.get(i));
                    bullets.remove();

                    c = new Control();
                    Score++;
                }
                }
            }

           // g2.draw(getBulletBounds());
        }


    }

    public Image getImage()
    {
        ImageIcon p = new ImageIcon(playerImage);
        return p.getImage();

    }

    public void update()
    {
        x+=dx;
        y+=dy;
        moveShoot();

        // checking for collision with the outer walls
        if(x < 0 )
        {
            x = 0;
        }

        if ( y < 0)
        {
            y = 0;
        }

        if (x >750)
        {
            x = 750;
        }

        if (y > 490)
        {
            y = 490;
        }
        playerCollision();

        //bulletCollision();
    }



    //methods for movement
    public void up()
    {
        dy = -3;
        dx = 0;
    }
    public void down()
    {
        dy = 3;
        dx = 0;
    }

    public void left()
    {
        dy = 0;
        dx = -3;
    }

    public void right()
    {
        dy = 0;
        dx = 3;
    }

    // method for bullet mechanics

    public void shootBullet()
    {
        if (bullets.size() < 7)
        {
            readyFire = true;

            if (readyFire = true)
           {
                bYLOC = y-7;
                bXLOC=x+18;

                Rectangle bullet = new Rectangle(bXLOC,bYLOC,3,5);
                bullets.add(bullet);

                shot = true;

            }
        }


    }

    // method for moving the bullet up the y axis

    public void moveShoot()
    {
        if (bullets.size() > 0)
        {

            for (Rectangle bullet:bullets)
            {
                bullet.y-=5;

            }


        }

    }


    // Diagonal movements to be added at later date

    public void upRight()
    {
        dy = -3;
        dx = 3;
    }

    public void upLeft()
    {
        dy = 1;
        dx = -1;
    }

    public void downRight()
    {

    }

    public void downLeft()
    {

    }


    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP)
        {
            up();
        }
        if (key == KeyEvent.VK_DOWN)
        {
            down();
        }
        if (key == KeyEvent.VK_LEFT)
        {
            left();
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            right();
        }
        if (key== KeyEvent.VK_SPACE)
        {
            shootBullet();
            moveShoot();

            System.out.println("Got here debug");
        }

        if (key == KeyEvent.VK_W)
        {
            up();
        }

        if (key == KeyEvent.VK_S)
        {
            down();
        }

        if (key == KeyEvent.VK_A)
        {
            left();
        }

        if (key == KeyEvent.VK_D)
        {
            right();
        }

        if (key == KeyEvent.VK_R)
        {
            if (healthPack  >=  1 && Health <= 120) {
                Health += 20;
                healthPack--;
                healthPackUI--;
            }
        }
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == e.VK_RIGHT)
        {
            dx=0;
        }
        if (key == e.VK_LEFT)
        {
            dx = 0;
        }
        if (key == e.VK_UP)
        {
            dy = 0;
        }
        if (key == e.VK_DOWN)
        {
            dy = 0;
        }

        if (key == e.VK_W)
        {
            dy = 0;
        }

        if (key == e.VK_S)
        {
            dy = 0;
        }

        if (key == e.VK_A)
        {
            dx = 0;
        }

        if (key == e.VK_D)
        {
            dx = 0;
        }

        if (key == e.VK_SPACE)
        {




        }


    }

    // creates rectangle around player for collision purposes
    public Rectangle getBounds()
    {
        return new Rectangle (x,y,31,61);
    }



    public void playerCollision()
    {
        for(int i = 0; i < e.size(); i++)
        {
            if (getBounds().intersects(e.get(i).getBounds()))
            {
                // removes the enemy hit
                e.remove(e.get(i));

                Health= Health -10;

                if (Health == 0)
                {
                    // Need to put these numbers into the database
                    // Pause Program to get Name
//                    try
//                    {
//                        Thread.sleep(4000);
//                    } catch (InterruptedException e1)
//                    {
//                        e1.printStackTrace();
//                    }
                    System.out.println("Game Over");
//                    System.out.println("Enter Name");

                    setScore();
                    System.out.print(setScore());

                    int Number = randomID();
                    System.out.print(randomID());

                    //Name = "DBB";
                    getNameFromUser();






                    // insert Data implementation
                    try {
                        insertData(Number,Score);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    System.exit(0);

                }




                c = new Control();


            }

        }
    }



    public int setScore()
    {
        return Score;
    }

    public int randomID()
    {
        Random r = new Random();
        int lowNumber = 7;
        int highNumber = 10000;
        int Number = r.nextInt(highNumber - lowNumber) + lowNumber;
        return Number;
    }

    public void getNameFromUser()
    {
        Name = JOptionPane.showInputDialog(null, "Game Over.  Enter your name: ");
        System.out.println("Name is: \t"+ Name);
    }

    // insert method

    public void insertData(int number, int score) throws SQLException {
         Connection connection;
        try
        {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/JavaGameDatabase.db";
            connection = DriverManager.getConnection(url);

            // Prepared Statement
            PreparedStatement pst = null;
            PreparedStatement pst2 = null;

            pst = connection.prepareStatement("INSERT INTO scoreTable(Number,Score,scoreTable_Name) VALUES(?,?, ?)");
            pst2 = connection.prepareStatement("INSERT INTO nameTable(Name)VALUES(?)");
            pst.setInt(1,randomID());
            pst.setInt(2,setScore());
            pst.setString(3,Name);
            pst2.setString(1,Name);
            pst.executeUpdate();
            pst2.executeUpdate();
            connection.close();

        } catch (ClassNotFoundException e1)
        {
            e1.printStackTrace();
        }


    }






} // end of class