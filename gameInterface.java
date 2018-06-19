import com.company.DBMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// this handles the game interface

public class gameInterface extends JFrame implements ActionListener
{
    // VARIABLES
    private JFrame gameWindow;
    private JPanel buttonPanel;
    private int WIDTH = 800;
    private int HEIGHT = 600;
    private String backgroundImage = "src/Earth.jpeg";

    // OBJECTS
    Player p;
    Control c;


    public gameInterface()
    {
        // Initalizes Game Interface
        //initGameInterface();
    }

    // initialize game interface
    public void initGameInterface()
    {
        // sets the initial Game Window up
        gameWindow = new JFrame("Game Window");
        gameWindow.setSize(WIDTH,HEIGHT);
        gameWindow.setVisible(true);
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets up the panel so buttons can appear
        buttonPanel = new JPanel();
        gameWindow.add(buttonPanel);
        pack();




        // Beginning to set up the buttons

        // Start Game Button Code
        JButton startGame = new JButton("Start Game");
        startGame.setVisible(true);
        buttonPanel.add(startGame);
        startGame.addActionListener(this);

        // Tutorial Information Button Code
        JButton gameInformation = new JButton("Tutorial");
        gameInformation.setVisible(true);
        buttonPanel.add(gameInformation);
        gameInformation.addActionListener(this);

        // Options Button Code
        JButton optionsButton = new JButton("Display Top Scores");
        optionsButton.setVisible(true);
        buttonPanel.add(optionsButton);
        optionsButton.addActionListener(this);

        // Exit Game Button
        JButton exitGame = new JButton("Exit Game");
        exitGame.setVisible(true);
        buttonPanel.add(exitGame);
        exitGame.addActionListener(this);
        final JLabel exitingGame = new JLabel("Exiting Game, Thank you for playing.");
        exitingGame.setVisible(false);
        buttonPanel.add(exitingGame);


    }

    public void paint(Graphics g)
    {
        //super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(getImage(),WIDTH,HEIGHT,this);

    }

    public Image getImage()
    {
        ImageIcon background = new ImageIcon(backgroundImage);
        return background.getImage();
    }


    public void draw(Graphics2D g2)
    {
        // Drawing the background image
        g2.drawImage(getImage(),0,0, WIDTH,HEIGHT,this);
    }



    // Action code based on the button clicked with the mouse

    public void actionPerformed(ActionEvent e)
    {
        // The code below determines which button is pressed based upon the string definition.
        String action = e.getActionCommand();
        if (action.equals("Exit Game"))
        {

            System.exit(0);
        }

        else if(action.equals("Display Top Scores"))
        {



            try {
                DBMain.printToTable();



            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        }

        else if(action.equals("Tutorial"))
        {
            // JFrame for tutorial
            JFrame tutorialFrame = new JFrame("Tutorial");
            tutorialFrame.setVisible(true);
            tutorialFrame.setSize(WIDTH,HEIGHT);


            // Text / Instructions in JTextArea format

            JTextArea instructions = new JTextArea();
            Font instructionsFont = new Font("SansSerif",Font.BOLD,18);
            tutorialFrame.add(instructions);
            instructions.setFont(instructionsFont);
            instructions.setText("Controls: WASD for movement. Space bar to fire bullets. \n Enemies give 5 points to your score" +
                    "When enemies hit you, you lose ten points in health.  When you reach zero, it is game over.\n  The goal is to go as long as possible." +
                    "\n  Alernate movement scheme: up arrow, right arrow, down arrow, left arrow.\n" +
                    "Press R to use Health Packs.  You are limited to 5 per game, and they restore 25 Health.");
            instructions.setEditable(false);

        }

        else if (action.equals("Start Game"))
        {
            System.out.println("This is the start button");
            JFrame f = new JFrame("Game");

            f.pack();
            f.setLocationRelativeTo(null);

            f.add(new Test());
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(WIDTH,HEIGHT);
            f.setResizable(false);
        }

    } // End of the Action Performed function



} // end of the Game Interface Class
