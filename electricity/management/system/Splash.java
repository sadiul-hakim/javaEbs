package electricity.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
Thread t;
    Splash(){
        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image frameImage=il.getImage().getScaledInstance(730,550,Image.SCALE_SMOOTH);
        ImageIcon again_il=new ImageIcon(frameImage);
        JLabel image=new JLabel(again_il);

        add(image);
        this.setVisible(true);
        int x=1;
        for(int i=2;i<=500;i+=4,x+=1){
            this.setSize(i+x,i);
            this.setLocation(700-((i+x)/2),400-(i/2));

            try{
                Thread.sleep(20);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run(){
        try{
            Thread.sleep(7000);
            setVisible(false);
            new Login();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Splash();
    }
}
