package Robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScanningThread implements Runnable {

	BufferedImage image;
	BufferedImage bi;
	private int start;
	boolean present;
	boolean[] pres;

	ScanningThread(int x, BufferedImage image, BufferedImage bi, boolean[] pres) {
		this.image = image;
		this.bi = bi;
		this.start = x;
		this.present = false;
		this.pres = pres;
	}

	@Override
	public void run() {

//		int x = start;
		try {

//			for(int y = 0; y< image.getHeight();y++){
			for (int x = start; x < image.getWidth(); x += bi.getWidth()) {
					if (pres[0]) break; //stops if reads that other thread finished already 
					
//		        	System.out.println("x: " + x);
//		        	for(int x = 0; x< image.getWidth();x++){
//					while(x < image.getWidth()) {
				for (int y = 0; y < image.getHeight(); y++) {

//		            	System.out.println("y: " + y);

					Robot bot = null;
					try {
						bot = new Robot();
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//						try {
//							TimeUnit.SECONDS.sleep((long) 1);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}

//						System.out.println("x: " + x + ", y: " + y);
//		            	bot.mouseMove(x, y);

					boolean invalid = false;
					
					//checks the amount of errors
					int errors = 0;
					int errorRange = 3;//bi.getHeight()/50;
					
					//how many pixels to skip
					int step = 3;
					
					int k = x;
					int l = y;
					for (int a = 0; a < bi.getWidth(); a+=step) { //a++
						l = y;

						for (int b = 0; b < bi.getHeight(); b+=step) { //b++
							int rgbError = 1000000; //checks if pixels are in range
							if (/*
								 * a<bi.getWidth() && b<bi.getHeight() && k < image.getWidth() && l <
								 * image.getHeight() &&
								 */
//							bi.getRGB(a, b) != image.getRGB(k, l)) { 
								bi.getRGB(a, b) < image.getRGB(k, l) - rgbError 
									|| bi.getRGB(a, b) > image.getRGB(k, l) + rgbError 	
								) {
								invalid = true;
								errors++;
								
//								break;
							} else {
								l+=step; //++
							}
						}
//						if (invalid) {
						if (errors > errorRange) {
							break;
						} else {
							k+=step; //++
						}

					}

//					if (!invalid) {
					if (errors < errorRange) {
						System.out.println("x: " + x + ", y: " + y);
						present = true;
						pres[0] = true;
						break;
					}

				}
			}
			present = false; // If no image is found
		} catch (Exception e) {
			
		}
	}
//		

}
