package Robot;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PressingRobot {
	 public static void main(String args[]) {
	        try {

	            Robot bot = new Robot();
	            long mask = InputEvent.MOUSE_EVENT_MASK;

	            TimeUnit.SECONDS.sleep((long) 2.5);

//	            showPosition();
	            
	            // (592, 273)
	            
//	            bot.mouseMove(483, 938); 
//	           
//	            bot.mousePress((int) mask);     
//	            bot.mouseRelease((int) mask);

	            bot.keyPress(KeyEvent.VK_WINDOWS);
	            bot.keyPress(KeyEvent.VK_R);
	            bot.keyRelease(KeyEvent.VK_WINDOWS);
	            
	            TimeUnit.SECONDS.sleep((long) 2.5);
	            
	            
	            
//	            bot.keyPress(KeyEvent.VK_CONTROL);
//	            bot.keyPress(KeyEvent.VK_SHIFT);
	            bot.keyPress(KeyEvent.VK_ENTER);
//	            bot.keyRelease(KeyEvent.VK_CONTROL);
//	            bot.keyRelease(KeyEvent.VK_SHIFT);
	            bot.keyRelease(KeyEvent.VK_ENTER);
	            
	            TimeUnit.SECONDS.sleep((long) 2.5);
	            
//	            bot.keyPress(KeyEvent.VK_ENTER);
//	            bot.keyRelease(KeyEvent.VK_ENTER);
	            
	            String temp = "cd C:\\Program Files\\Notepad++";	            
	            type(temp, bot);
	            
	            bot.keyPress(KeyEvent.VK_ENTER);
	            bot.keyRelease(KeyEvent.VK_ENTER);
	            
	            temp = "start Notepad++.exe";
	            type(temp, bot);
	            
	            bot.keyPress(KeyEvent.VK_ENTER);
	            bot.keyRelease(KeyEvent.VK_ENTER);
	            
	            TimeUnit.SECONDS.sleep((long) 2.5);
	            
	            bot.keyPress(KeyEvent.VK_CONTROL);
	            bot.keyPress(KeyEvent.VK_N);
	            bot.keyRelease(KeyEvent.VK_CONTROL);
	            bot.keyRelease(KeyEvent.VK_N);
	            
	            TimeUnit.SECONDS.sleep((long) 1);
	            
	            temp = "Labas Mama";
	            type(temp,bot);
//	            bot.keyPress(KeyEvent.VK_SHIFT);
//	            bot.keyPress(KeyEvent.VK_Y);
//	            bot.keyRelease(KeyEvent.VK_SHIFT);
//	            bot.keyPress(KeyEvent.VK_E);
//	            bot.keyPress(KeyEvent.VK_S);
//	            bot.keyPress(KeyEvent.VK_B);
//	            bot.keyPress(KeyEvent.VK_A);
//	            bot.keyPress(KeyEvent.VK_N);
//	            bot.keyPress(KeyEvent.VK_K);
//	            bot.keyPress(KeyEvent.VK_1);

//	            TimeUnit.SECONDS.sleep((long) 4);
//	            bot.mouseMove(216, 779);
//	            bot.mousePress((int) mask);     
//	            bot.mouseRelease((int) mask);
	            
	        } 
	        catch (Exception e) 
	        {
	            System.out.println("Exception send--->"+e.getMessage());
	            e.printStackTrace();
	        } 
	    }

	    public static void showPosition() throws InterruptedException
	    {
	        try
	        {
	            while(true)
	            {
	                TimeUnit.SECONDS.sleep(1/2);
	                double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
	                double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
	                System.out.println("X:" + mouseX);
	                System.out.println("Y:" + mouseY);
	                //make sure to import 
	            }
	        }
	        catch(Exception e)
	        {
	            System.out.println("Excpetion inside showPosition-->"+e.getMessage());
	        }
	    }
	    
	    //print words with keyboard
	    public static void type(String temp, Robot bot) {
	    	for (char c : temp.toCharArray()) {
            	int keyCode = 0;
            	if (c == ':') {
            		bot.keyPress(KeyEvent.VK_SHIFT);
            		bot.keyPress(KeyEvent.VK_PERIOD);
            		bot.delay(50);
            		bot.keyRelease(KeyEvent.VK_SHIFT);
            		bot.keyRelease(KeyEvent.VK_PERIOD);
            		continue;
            	} 
            	if (c == '\\') {
            		bot.keyPress(KeyEvent.VK_ALT_GRAPH);
            		bot.keyPress(KeyEvent.VK_LESS);
            		bot.delay(50);
            		bot.keyRelease(KeyEvent.VK_ALT_GRAPH);
            		bot.keyRelease(KeyEvent.VK_LESS);
            		continue;
            	}
            	
            	if (c == '+') {
            		bot.keyPress(KeyEvent.VK_SHIFT);
            		bot.keyPress(KeyEvent.VK_1);
            		bot.delay(50);
            		bot.keyRelease(KeyEvent.VK_SHIFT);
            		bot.keyRelease(KeyEvent.VK_1);
            		continue;
            	}
            	
        		keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
	            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
	                throw new RuntimeException(
	                    "Key code not found for character '" + c + "'");
	            }
            	
	            bot.keyPress(keyCode);
	            bot.delay(50);
	            bot.keyRelease(keyCode);
            }
	    }
}
