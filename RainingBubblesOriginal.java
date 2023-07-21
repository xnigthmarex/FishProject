import java.awt.*;
import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.Timer;


//The start of the class with an extension of Applet(to make the applet window)

public class RainingBubblesOriginal extends Applet
{
    /**
	 * 
	 */
	//Constants to control size, speed, number of circles, and max velocity
    //Go ahead and change the values to see simple changes to the program
    private final int DELAY = 30;
    private final int MAX_SIZE = 100;
    private final int MAX_CIRCLES = 20;
    private final int MAX_VELOCITY = 3;
    private final int MAX_BUBBLES = 20;
   
    //Below are called "parallel arrays". Is there a better way to handle all of this data?
    //Hint:  These could all be ATTRIBUTES of a Bubble class.
    //Make that class and create a single array filled with Bubble objects.
    //Then change the code below to utilize the Bubble class.
    
    private Bubbles[] bubbles;
    private waterBubbles[] waterBubbles;
    private pumpWaterClass[] pump;
    //Every visual program needs a init(initialization) method to start the visuals.(Similar to a main method)
    public void init(){
        //What do you think the getWidth() and getHeight() methods do?  Why may they be needed?
        //Try changing the JApplet screen size and how that affects the bubble scene
        this.resize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        
       
        //Initializing the 4 parallel arrays
        //Change these parallel arrays into 1 array holding bubble objects.
        
        bubbles = new Bubbles[MAX_CIRCLES];
        waterBubbles = new waterBubbles[MAX_BUBBLES];
        pump = new pumpWaterClass[MAX_BUBBLES];
        for(int count = 0;count < MAX_CIRCLES; count++){
            //Find where the method definition is for resetCircle(int)
            resetCircle(count);
        }
        for(int count = 0;count < MAX_BUBBLES; count++){
            //Find where the method definition is for resetCircle(int)
            resetWater(count);
        }
        for(int count = 0;count < MAX_BUBBLES; count++){
            //Find where the method definition is for resetCircle(int)
            resetPump(count);
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
//    public void test(){
//    	Thread lol = new Thread(){
//    	 public void run(){
//    		 System.out.print("sdhfkhkadjsf");
//    	 }
//    	  
//    	};
//    	lol.start();
//    }

    
    
    
    //What is this method doing?  How does it relate to the visual we see.
    //This is where you can really make cool changes to what we see.
    //begin by again changing the parallel arrays to an array holding bubble objects.
    private void resetCircle(int index){
        
        bubbles[index] =  new Bubbles((int)(Math.random()*this.getWidth()),(int)(Math.random()*this.getHeight()-300),(Math.random()* MAX_VELOCITY)+1,100,(Math.random()* MAX_VELOCITY)+1,true,true);
    }
    private void resetWater(int index){
    	
        waterBubbles[index] =  new waterBubbles((int)(Math.random()*this.getWidth())-200,this.getHeight(),(int)(Math.random()*10)+5,(int)(Math.random()*20)+10);
    }
    private void resetPump(int index){
    	
        pump[index] =  new pumpWaterClass(getWidth()-75,(getHeight()/3 )+200,5,10,(int)(Math.random()*20)+10);
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        
        g2.setPaint(new Color(126, 194, 213));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(new Color(0,0,0));
       
        for(int i = 0;i<6;i++){
        	 g2.drawRect(0+i,0+i,getWidth()-(2*i),getHeight()-i);
        }
       
        ImageIcon background = new ImageIcon("./background.png");
        Image background1 = background.getImage();
        g2.drawImage(background1,-80,0, 1356, 606,null, null);
        for (int i = 0; i < MAX_BUBBLES; i++) {
      
            	if(pump[i].getY()<0){
            		resetPump(i);
            	}
            	else{
            		pump[i].setY(pump[i].getY()-pump[i].getYvelocity());
            	}
            	g2.setPaint(Color.WHITE);
            	g2.fill(new Ellipse2D.Double(pump[i].getX(),pump[i].getY(),pump[i].getSize(),pump[i].getSize()));
            }
        
        
        
        for (int i = MAX_BUBBLES; i < MAX_BUBBLES; i++) {
        	
            	if(waterBubbles[i].getY()<0){
            		resetWater(i);
            	}
            	else{
            		waterBubbles[i].setY(waterBubbles[i].getY()-waterBubbles[i].getYvelocity());
            	}
            	g2.setPaint(Color.WHITE);
            	g2.fill(new Ellipse2D.Double(waterBubbles[i].getX(),waterBubbles[i].getY(),waterBubbles[i].getSize(),waterBubbles[i].getSize()));
            }
        
        for (int count = 0; count < MAX_CIRCLES; count++) {
        	ImageIcon crab = new ImageIcon("./crab.png");
            Image crab1 =  crab.getImage();
            g2.drawImage(crab1, 825, 430, 225, 225, null);
            g2.drawImage(crab1, 250, 430, 225, 225, null);
        	ImageIcon fishIcon = bubbles[count].getFishBack();
        	
            if (bubbles[count].getX() > this.getWidth() - 100 || bubbles[count].getY() > this.getHeight()) {
                bubbles[count].setXmove(false);
            }
            if (bubbles[count].getX() < 0 || bubbles[count].getY() < 0) {
                bubbles[count].setXmove(true);
            }

            if (bubbles[count].isXmove()) {
                bubbles[count].setX(bubbles[count].getX() + bubbles[count].getXvelocity());
            
              
            } else {
                bubbles[count].setX(bubbles[count].getX() - bubbles[count].getXvelocity());
                fishIcon = bubbles[count].getFishFront();  
                
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
        
//            g2.setPaint(new Color(0, 0, 0));
//            g2.fillRect(getWidth()-125, 0, getWidth()/10, getHeight()/3);
//            g2.fillRect(getWidth()-75,getHeight()/3 ,getWidth()/40, getHeight()/5);
         
            
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
