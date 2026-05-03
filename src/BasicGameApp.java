//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too


    /*Pokemon Game Play Summary


The game has the following characters:
Main Player - Magikarp (Turns into Gyrados upon victory)
Villains - Alakazam, Blaziken, Greninja, Mew
Object - Pokeball, Health ball


The main player and villains all start with 100 health points each. The health ball moves constantly from a
random position and bounces off the walls. The health ball moves at 5 units at time. Main player moves 2 units
with each press of the arrow keys (up, down, left, right) or by holding down the keys. Pressing two keys moves
the main player diagonally. Main player can shoot a pokeball in the direction of travel at 10 units at a time
by clicking the mouse. The pokeball continues through the screen until it either hits a villain or hits the
edge of the screen. If the pokeball hits a villain, the villain loses health points (20 points each hit).
If the main player hits the health ball, it gains 20 points of health.

The villains move randomly on the screen, 2 units at a time. Each villain starts off by either bouncing off
the edge of the screen or by wrapping to the opposed edge of the screen. The initial movement of the villains
is selected at random. If a villain crashes into the main player, the main player loses health points
(20 points each hit). Once the villain is either hit with a pokeball or hits the main player, its method
of movement switches from bouncing to wrapping or vice versa.

A player loses or dispappears when their health points are zero. Keep track of the enemy health points using
an array. Display the health points for the main player and the villains with a health number below their
character.

The background is a pokemon image that is 1000 pixels wide by 800 pixels high. Each character is 100 pixels by
100 pixels. The pokeball and the health objects are 25 pixels by 25 pixels.

Once all the villains have been defeated, the main player turns from Magikarp to Gyrados and it bounces off
the walls with a “you win” sign. If the main player is defeated, the main player disappears and the screen
flashes “you lose”.
*/


    //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
    public Image BackgroundPic;
    public Image AlakazamPic;
    public Image BlazikenPic;
    public Image GreninjaPic;
    public Image MewPic;
    public Image MagikarpPic;
    public Image PokeballPic;
    public Image HealballPic;
    int mainHealth = 100;
    int[] enemyHealth = {100,100,100,100};

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type

    private Alakazam Alakazam;
    private Blaziken Blaziken;
    private Greninja Greninja;
    private Mew Mew;
    private Magikarp Magikarp;
    private Pokeball Pokeball;
    private Healball Healball;



   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("Background.png");//load the pictur
        AlakazamPic = Toolkit.getDefaultToolkit().getImage("Alakazam.png");//load the pictur
        BlazikenPic = Toolkit.getDefaultToolkit().getImage("Blaziken.png");//load the pictur
        GreninjaPic = Toolkit.getDefaultToolkit().getImage("Greninja.png");//load the pictur
        MewPic = Toolkit.getDefaultToolkit().getImage("mew.png");//load the pictur
        MagikarpPic = Toolkit.getDefaultToolkit().getImage("Magikarp.png");//load the pictur
        PokeballPic = Toolkit.getDefaultToolkit().getImage("Pokeball.png");//load the pictur
        HealballPic = Toolkit.getDefaultToolkit().getImage("Healball.png");//load the pictur


        Alakazam = new Alakazam(500,400);
        Blaziken = new Blaziken(3,9);
        Greninja = new Greninja(600,560);
        Mew = new Mew(500,50);
        Magikarp = new Magikarp(300, 500);
        Pokeball = new Pokeball(56,78);
        Healball = new Healball(80,357);



	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
        Alakazam.move();
        Magikarp.move();
        Blaziken.move();
        Greninja.move();
        Mew.move();
        Pokeball.move();
        Healball.move();

        crashing();

	}
    public void crashing(){
        // Magikarp touches healball
        if (Magikarp.hitbox.intersects(Healball.hitbox)) {
            mainHealth += 20;
            Healball.xpos = (int)(Math.random()*900);
            Healball.ypos = (int)(Math.random()*600);
        }

        // Pokeball hits Alakazam
        if (Pokeball.isAlive && Pokeball.hitbox.intersects(Alakazam.hitbox)) {
            enemyHealth[0] -= 20;
            Pokeball.isAlive = false;
        }

        if (Pokeball.isAlive && Pokeball.hitbox.intersects(Blaziken.hitbox)) {
            enemyHealth[1] -= 20;
            Pokeball.isAlive = false;
        }

        if (Pokeball.isAlive && Pokeball.hitbox.intersects(Greninja.hitbox)) {
            enemyHealth[2] -= 20;
            Pokeball.isAlive = false;
        }

        if (Pokeball.isAlive && Pokeball.hitbox.intersects(Mew.hitbox)) {
            enemyHealth[3] -= 20;
            Pokeball.isAlive = false;
        }

        // enemies hit player
        if (Magikarp.hitbox.intersects(Alakazam.hitbox)) {
            mainHealth -= 20;
        }

    }


	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.

       canvas.addKeyListener(this);
       canvas.addMouseListener(this);
       canvas.setBounds(0, 0, WIDTH, HEIGHT);
       canvas.setIgnoreRepaint(true);
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("" + mainHealth, Magikarp.xpos + 40, Magikarp.ypos + 170);
        g.drawString("" + enemyHealth[0], Alakazam.xpos + 40, Alakazam.ypos + 150);
        g.drawString("" + enemyHealth[1], Blaziken.xpos + 40, Blaziken.ypos + 150);
        g.drawString("" + enemyHealth[2], Greninja.xpos + 40, Greninja.ypos + 150);
        g.drawString("" + enemyHealth[3], Mew.xpos + 40, Mew.ypos + 150);

        if(enemyHealth[0] > 0){
            g.drawImage(AlakazamPic, Alakazam.xpos, Alakazam.ypos,
                    Alakazam.width, Alakazam.height, null);
        }
        if(enemyHealth[0] <=0 && enemyHealth[1] <=0 && enemyHealth[2] <=0 && enemyHealth[3] <=0) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("YOU WIN!", 300, 300);

            MagikarpPic = Toolkit.getDefaultToolkit().getImage("Gyrados.png");
        }
        if(mainHealth <= 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("YOU LOSE", 300, 300);
        }

      //draw the image of the astronaut
        g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);
        //g.drawImage(AlakazamPic, Alakazam.xpos, Alakazam.ypos, Alakazam.width, Alakazam.height, null);
        g.drawImage(AlakazamPic, Alakazam.xpos, Alakazam.ypos, Alakazam.width, Alakazam.height, null);
        g.drawImage(BlazikenPic, Blaziken.xpos, Blaziken.ypos, Blaziken.width, Blaziken.height, null);
        g.drawImage(GreninjaPic, Greninja.xpos, Greninja.ypos, Greninja.width, Greninja.height, null);
        g.drawImage(MewPic, Mew.xpos, Mew.ypos, Mew.width, Mew.height, null);
        g.drawImage(MagikarpPic, Magikarp.xpos, Magikarp.ypos, Magikarp.width, Magikarp.height, null);
        g.drawImage(PokeballPic, Pokeball.xpos, Pokeball.ypos, Pokeball.width, Pokeball.height, null);
        g.drawImage(HealballPic, Healball.xpos, Healball.ypos, Healball.width, Healball.height, null);

       /*/ g.drawRect(Alakazam.hitbox.x, Alakazam.hitbox.y, Alakazam.hitbox.width, Alakazam.hitbox.height);
        g.drawRect(Magikarp.hitbox.x, Magikarp.hitbox.y, Magikarp.hitbox.width, Magikarp.hitbox.height);/*/

		g.dispose();

		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key typed" + e.getKeyCode());
        if (e.getKeyCode() == 38) {//makes Magikarp go up
            System.out.println("pressed 'up'");
           // Magikarp.dy = -Math.abs(Magikarp.dy);
            Magikarp.dy = -2;
        }
        if (e.getKeyCode() == 39) {//makes Magikarp go to the left
            System.out.println("pressed 'right' ");
            //Alakazam.dx = Math.abs(Alakazam.dx);
            Magikarp.dx = 2;

        }
        if (e.getKeyCode() == 37) {//makes Magikarp go down
            System.out.println("pressed 'left' ");
            Magikarp.dx = -2;
        }
        if (e.getKeyCode() == 40) {//makes Magikarp go to the right
            System.out.println("pressed 'down' ");
            //Magikarp.dx = Math.abs(Magikarp.dx);
            Magikarp.dy = 2;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            System.out.println("not pressed up");
            Magikarp.dy = 0;
        }
        if (e.getKeyCode() == 40) {
            System.out.println("not pressed down");
            Magikarp.dy = 0;
        }
        if (e.getKeyCode() == 39) {
            System.out.println("not pressed right >");
            Magikarp.dx = 0;
        }
        if (e.getKeyCode() == 37) {
            System.out.println("not pressed left");
            Magikarp.dx = 0;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Pokeball.shoot(
                Magikarp.xpos + 50,
                Magikarp.ypos + 50,
                Magikarp.dx,
                Magikarp.dy
        );

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}