package Robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScanningThread implements Runnable {
	
	BufferedImage image;
	BufferedImage bi;
	int x;
	boolean present;
	AtomicBoolean pres;
	
	ScanningThread(int x, BufferedImage image, BufferedImage bi){
		this.image = image;
		this.bi = bi;
		this.x = x;
		this.present = false;
	}
	
	@Override
	public void run() {
		for(int y = 0; y< image.getHeight();y++){
	        
//        	System.out.println("y: " + y);
//        	for(int x = 0; x< image.getWidth();x++){
			while(x < image.getWidth()) {
				
			
//            	System.out.println("x: " + x);
            	
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
                    present = true;
                }
                
                x += bi.getWidth();
            }
        }
        present = false; //If no image is found
    }

}
