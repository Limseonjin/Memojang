package seonjingame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class mains1 extends JFrame {
	mains3 jp1 = null;
	mains4 jp2 = null;
	mains2 jp3 = null;
	mains2 jp3_1 = null;
	mains22 jp4 = null;
	mains222 jp5 = null;
	
	
	public void change(String PanelName) {
		
		
        if (PanelName.equals("jp1")) {
        	jp1 = new mains3(this);
			getContentPane().removeAll();
			getContentPane().add(jp1);
			revalidate();
			repaint();
		}
        else if (PanelName.equals("jp2")) {
			jp2 = new mains4(this);
			getContentPane().removeAll();
			getContentPane().add(jp2);
			revalidate();
			repaint();
		}
		else if (PanelName.equals("jp3")){ 
			jp3 = new mains2(this);
			getContentPane().removeAll();
			getContentPane().add(jp3);
			revalidate();
			repaint();
		}
		else if (PanelName.equals("jp4")){ 
			jp4 = new mains22(this);
			getContentPane().removeAll();
			getContentPane().add(jp4);
			revalidate();
			repaint();
		}
		else if (PanelName.equals("jp5")){ 
			jp5 = new mains222(this);
			getContentPane().removeAll();
			getContentPane().add(jp5);
			revalidate();
			repaint();
		}
		
		
		
	}
	 public int getRandomNumber() {
	        Random random = new Random();
	        int randomNumber = random.nextInt(9) + 1;
	        return randomNumber;
	 }
	public void gamestart() {
		mains1 win = new mains1();
        win.setBounds(300,100,1000,800);
        win.setVisible(true);
        win.change("jp1");
	 }


    public static void main(String[] args) {
    	
        }
   
}