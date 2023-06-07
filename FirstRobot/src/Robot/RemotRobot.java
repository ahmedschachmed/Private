package Robot;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit; 

public class RemotRobot {
    public static void main(String args[])
    {
        try 
        {
            //showPosition();

//            System.out.println("Remote Desktop for-->"+args[0]);
//            String IP = "";
//
//            if("DEV".equalsIgnoreCase(args[0]))
//            {
//                IP = "mstsc /v:10.0.41.101";
//            }
//            
//            else if("UAT".equalsIgnoreCase(args[0]))
//            {
//                IP = "mstsc /v:10.0.45.43";
//            }
//            else if("PRE-PROD".equalsIgnoreCase(args[0]))
//            {
//                IP = "mstsc /v:10.0.45.209";
//            }
//            Process p = Runtime. getRuntime(). exec(IP);
            Robot bot = new Robot();
            long mask = InputEvent.MOUSE_EVENT_MASK;

            TimeUnit.SECONDS.sleep((long) 2.5);

            showPosition();
            
            bot.mouseMove(607, 290); 
            TimeUnit.SECONDS.sleep((long) 2.5);
            bot.mousePress((int) mask);     
            bot.mouseRelease((int) mask);

            bot.keyPress(KeyEvent.VK_SHIFT);
            bot.keyPress(KeyEvent.VK_Y);
            bot.keyRelease(KeyEvent.VK_SHIFT);
            bot.keyPress(KeyEvent.VK_E);
            bot.keyPress(KeyEvent.VK_S);
            bot.keyPress(KeyEvent.VK_B);
            bot.keyPress(KeyEvent.VK_A);
            bot.keyPress(KeyEvent.VK_N);
            bot.keyPress(KeyEvent.VK_K);
            bot.keyPress(KeyEvent.VK_1);

            bot.mouseMove(765, 508);           
            bot.mousePress((int) mask);     
            bot.mouseRelease((int) mask);

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


}
