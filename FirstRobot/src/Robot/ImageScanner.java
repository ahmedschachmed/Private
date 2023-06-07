package Robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
public class ImageScanner {
 
    public static void main(String[] args) {
        try {
               BufferedImage bi = ImageIO.read(new File("C:\\Users\\plytn\\OneDrive\\Desktop\\CMD\\ScanningRobot\\screenshot.png"));
               
               int numThreads = 10;
               Thread[] freds = new Thread[numThreads];
               BufferedImage image = null;
               try {
                   image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
               } catch (Exception e) {
                   e.printStackTrace();
               }
               
               for (int i = 0; i < numThreads; i++) {
            	   Thread thread = new Thread(new ScanningThread(i, image, bi));
            	   freds[i] = thread;
            	   freds[i].start();
               }
               boolean isOnScreen = false;
               for (int i = 0; i < numThreads; i++) {
            	   freds[i].join();
               }               
               
               
//               boolean isOnScreen = isOnScreen(image);
//               System.out.print(isOnScreen);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
    }
    
    private static boolean isOnScreen(BufferedImage bi){
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int y = 0; y< image.getHeight();y++){
        
        	System.out.println("y: " + y);
        	for(int x = 0; x< image.getWidth();x++){
            	System.out.println("x: " + x);
            	
            	Robot bot = null;
				try {
					bot = new Robot();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//            	bot.mouseMove(x, y);
            	
                boolean invalid = false;
                int k = x,l = y;
                for(int a = 0;a<bi.getWidth();a++){
                    l = y;
                    for(int b = 0;b<bi.getHeight();b++){
                        if(bi.getRGB(a, b) != image.getRGB(k, l)){
                            invalid = true;
                            break;
                        }
                        else{
                            l++;
                        }
                    }
                    if(invalid){
                        break;
                    }else{
                        k++;
                    }
                        
                }
                
                if(!invalid){
                	System.out.println("x: " + x + ", y: " + y);
                    return true;
                }
            }
        }
        return false; //If no image is found
    }
 
}
