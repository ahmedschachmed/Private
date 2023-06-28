package Robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
 
public class ImageScanner {
 
    public static void main(String[] args) {
    	for(int i = 0; i < 3; i++) {
    		scan();
    	}
    }    
    
    private static void scan() {
    	try {
//    		TimeUnit.SECONDS.sleep((long) 5);
    		//es muss eine .png datei sein
    		BufferedImage bi = ImageIO.read(new File("C:\\Code\\Robot\\pic.png"));
           
           int numThreads = 512;
           
           long start = System.currentTimeMillis();
           
           Thread[] freds = new Thread[numThreads];
           BufferedImage image = null;
           try {
               image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
           } catch (Exception e) {
               e.printStackTrace();
           }
           System.out.println(image.getHeight() + " x " + image.getWidth());
           System.out.println(bi.getHeight() + " x " + bi.getWidth());
           
           boolean[] pres = new boolean[1];
           pres[0] = false;
           
           for (int i = 0; i < numThreads; i++) {
        	   Thread thread = new Thread(new ScanningThread(i, image, bi, pres));
        	   freds[i] = thread;
        	   freds[i].start();
           }
           boolean isOnScreen = pres[0];
           
           for (int i = 0; i < numThreads; i++) {
        	   freds[i].join();
           }
           
           long end = System.currentTimeMillis();
           
           
           System.out.println(pres[0]);
           System.out.println("Duration " + (end-start)/1000 + " s");
           
//           isOnScreen = isOnScreen(bi);
//           System.out.print(isOnScreen);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("caught");
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
                    	bot.mouseMove(k, l);
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
