import java.awt.*;
import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.lang.Thread;
import java.awt.geom.Arc2D.Double;

//The start of the class with an extension of Applet(to make the applet window)
public class RainingBubblesOriginal extends Applet
{
    /**
	 * 
	 */
	//Constants to control size, speed, number of circles, and max velocity
    //Go ahead and change the values to see simple changes to the program
    private final int DELAY = 100;
    private final int MAX_SIZE = 100;
    private final int MAX_CIRCLES = 10;
    private final int MAX_VELOCITY = 10;
    private int test = 213;
    //Below are called "parallel arrays". Is there a better way to handle all of this data?
    //Hint:  These could all be ATTRIBUTES of a Bubble class.
    //Make that class and create a single array filled with Bubble objects.
    //Then change the code below to utilize the Bubble class.
    
    private Bubbles[] bubbles;
    //Every visual program needs a init(initialization) method to start the visuals.(Similar to a main method)
    public void init(){
        //What do you think the getWidth() and getHeight() methods do?  Why may they be needed?
        //Try changing the JApplet screen size and how that affects the bubble scene
        this.resize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        
       
        //Initializing the 4 parallel arrays
        //Change these parallel arrays into 1 array holding bubble objects.
        
        bubbles = new Bubbles[MAX_CIRCLES];

        for(int count = 0;count < MAX_CIRCLES; count++){
            //Find where the method definition is for resetCircle(int)
            resetCircle(count);
        }

        //I needed this to "repaint" the screen. It uses a timer which is "listenedTo" by an ActionListener
        //This code listens to the timer and when to reset a bubble.  No need to change the code.
        ActionListener taskPerformer = new ActionListener(){
            public void actionPerformed(ActionEvent evt){
               repaint();
            }
        };

        //What type of object are we creating?
        Timer x = new Timer(DELAY, taskPerformer);
        x.start();
    }
//********************* END OF INIT METHOD  ********************************************************

    //What is this method doing?  How does it relate to the visual we see.
    //This is where you can really make cool changes to what we see.
    //begin by again changing the parallel arrays to an array holding bubble objects.
    private void resetCircle(int index){
        
        bubbles[index] =  new Bubbles((int)(Math.random()*this.getWidth()),(int)(Math.random()*this.getHeight()-300),(Math.random()* MAX_VELOCITY)+3,100,(Math.random()* MAX_VELOCITY)+5,true,true);
    }
    

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(126, 194, 213));
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        for (int count = 0; count < MAX_CIRCLES; count++) {
        	ImageIcon fishIcon = new ImageIcon("./fish-removebg-preview back.png");
            if (bubbles[count].getX() > this.getWidth() - 10 || bubbles[count].getY() > this.getHeight()) {
                bubbles[count].setXmove(false);
            }
            if (bubbles[count].getX() < 0 || bubbles[count].getY() < 0) {
                bubbles[count].setXmove(true);
            }

            if (bubbles[count].isXmove()) {
                bubbles[count].setX(bubbles[count].getX() + bubbles[count].getXvelocity());
             //   ImageIcon fishIcon = new ImageIcon("./fish-removebg-preview.png");  // Replace "fish.png" with the actual path to your fish image
              
            } else {
                bubbles[count].setX(bubbles[count].getX() - bubbles[count].getXvelocity());
                fishIcon = new ImageIcon("./fish-removebg-preview.png");  // Replace "fish.png" with the actual path to your fish image
                
            }

            bubbles[count].setY((Math.sin(Math.toRadians(bubbles[count].getX())) * 50) + (bubbles[count].getX() / 2)+bubbles[count].getYfixforsinmovementfunction());
        //    System.out.println((Math.sin(Math.toRadians(bubbles[count].getX())) * 50) + (bubbles[count].getX() / 2)+bubbles[count].getYfixforsinmovementfunction());
            g2.setPaint(Color.blue);

            double x = bubbles[count].getX();
            double y = bubbles[count].getY();
            double size = bubbles[count].getSize();

            // Calculate rocking angle
            double angle = Math.cos(Math.toRadians(x)) * 30;

            // Draw fish body
            g2.rotate(Math.toRadians(angle), x + size / 2, y + size / 4);
            g2.fill(new Ellipse2D.Double(x, y, 1, 1));
            g2.rotate(-Math.toRadians(angle), x + size / 2, y + size / 4);
            
            Image fishImage = fishIcon.getImage();
            g2.rotate(Math.toRadians(angle), bubbles[count].getX() + bubbles[count].getSize() / 2, bubbles[count].getY() + bubbles[count].getSize() / 4);
            g2.drawImage(fishImage, (int) bubbles[count].getX(), (int) bubbles[count].getY()-20, (int) bubbles[count].getSize()+30, (int) (bubbles[count].getSize() / 1.1), null);
            g2.rotate(-Math.toRadians(angle), bubbles[count].getX() + bubbles[count].getSize() / 2, bubbles[count].getY() + bubbles[count].getSize() / 4);
        
            
        }
    }



    //This method is to "double buffer".  If it wasn't here,
    //the animations would flicker.  No need to modify/comment anything in this method.
    public void update(Graphics g){
        Graphics offgc;
        Image offscreen = null;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();

        offgc.setColor(getBackground());
        offgc.fillRect(0,0,d.width,d.height);
        offgc.setColor(getForeground());

        paint(offgc);

        g.drawImage(offscreen, 0, 0, this);
    }
}
