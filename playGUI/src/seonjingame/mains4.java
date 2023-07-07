package seonjingame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class mains4 extends JPanel {
	JButton easy, nomal, hard;
	mains1 win;
	String img_url = "C:\\JavaSeonjin\\playGUI\\src\\img\\";
	
	public mains4(mains1 win) {
		this.win = win;
		
    	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	
    	JPanel xset = new JPanel();
    	xset.setLayout(new BoxLayout(xset,BoxLayout.X_AXIS));
    	
    	JLabel titleN = new JLabel();
    	titleN.setText("곰돌이 먹이 짝 맞추기");
    	add(titleN);
    	
		JPanel panel = new JPanel() {
        	Image background = new ImageIcon(img_url+"titleimg.png").getImage();
    		public void paint(Graphics g) {//그리는 함수
		        g.drawImage(background, 200, 50, null);//background를 그려줌   
		        super.paint(g);
		        }
    	};
    	panel.setOpaque(false);
    	panel.setPreferredSize(new Dimension(700, 400));
    	
    	
    	add(xset);
    	add(panel,BorderLayout.CENTER);
    	
		easy = new JButton("쉬움");
		nomal = new JButton("보통");
		hard = new JButton("어려움");
		xset.add(easy); xset.add(nomal); xset.add(hard);
		
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				win.change("jp3");
			}
    		
    	});
		nomal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				win.change("jp4");
			}
    		
    	});
		hard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				win.change("jp5");
			}
    		
    	});
		
	}
}
