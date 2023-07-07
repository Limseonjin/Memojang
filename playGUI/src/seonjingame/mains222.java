package seonjingame;

import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class mains222 extends JPanel implements ActionListener {
	
	int SIZE1 = 6; // 버튼 그리드의 크기
    int SIZE2 = 8;
    JPanel centerPanel;
    JButton[][] buttons; // 버튼 그리드
    JButton selectedButton; 
    int matchedCount;
    List<ImageIcon> iconList = new ArrayList<>();
    mains1 win;
    
    int time = 30;
    JLabel timL;
    private ScheduledExecutorService executor;
    
    String img_url = "C:\\JavaSeonjin\\playGUI\\src\\img\\";
    String audio_url = "C:\\JavaSeonjin\\playGUI\\src\\adio\\";
    
    public mains222(mains1 win) {
    	this.win = win;
    	this.setLayout(new BorderLayout());
    	
    	timL = new JLabel();
     	timL.setText(time+"");
     	
     	Font ft = timL.getFont();
 		int fts= ft.getSize()*2;
 		ft = new Font(ft.getName(),ft.getStyle(),fts); //name,style,size
 		timL.setFont(ft);
 		
         add(timL,BorderLayout.NORTH);
         
    	 // 중앙에 GridLayout 배치
        JPanel centerPanel = new JPanel() {
        	Image background = new ImageIcon(img_url+"bear"+ win.getRandomNumber()+".png").getImage();
        		public void paint(Graphics g) {//그리는 함수
			        g.drawImage(background, 200, 100, null);//background를 그려줌   
			        super.paint(g);
			        }
	  };
	  centerPanel.setOpaque(false);
	  centerPanel.setLayout(new GridLayout(SIZE1, SIZE2));
	  add(centerPanel, BorderLayout.CENTER);

	  initbutton(centerPanel);
	  timmer();
  
    }
    public void timmer() {
        executor = Executors.newSingleThreadScheduledExecutor();
        
        // 일정 시간마다 실행될 작업
        Runnable task = () -> {
           time--;
           timL.setText(time+"");
           if (time == 0) {
        	   MP3Play("Hobbit Oh aw");
        	   stopTimer();
		       int re =JOptionPane.showConfirmDialog(this, "다시 하시겠습니까?","게임오버",JOptionPane.OK_CANCEL_OPTION);
		       	if (re == 0) {
		       		win.change("jp5");
		       	}
		       	else {
		       		win.change("jp1");
		       	}
           }
        	   
        };
        
        // 1초마다 작업을 실행합니다.
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
	    
    public void stopTimer() {
    	if (executor != null){
    		executor.shutdown();
    	}
    }

    private void initbutton(JPanel jp) {
		// TODO Auto-generated method stub
    	 buttons = new JButton[SIZE1][SIZE2];
         
         String[] imgPath = new String[24];
         for (int i = 1; i <= 24; i++) {
         	imgPath[i - 1] = img_url+"image" + i + ".png";
         }
         //img
         
         for (int i = 0; i < (SIZE1*SIZE2)/2; i++) {
         	ImageIcon icon = new ImageIcon(imgPath[i]);
             iconList.add(icon);
             iconList.add(icon);
         }
         
        Collections.shuffle(iconList);
      // 버튼을 프레임에 추가
        int k =0;
         for (int i = 0; i < SIZE1; i++) {
             for (int j = 0; j < SIZE2; j++) {
                 buttons[i][j] = new JButton(iconList.get(k));
                // buttons[i][j].setContentAreaFilled(false);//내용채우기 x 
                 buttons[i][j].setFocusPainted(false);
                 buttons[i][j].addActionListener(this);
                 buttons[i][j].setPreferredSize(new Dimension(100, 100));
                 jp.add(buttons[i][j]);
                 k++;
             }
         }
         
	}

	// 버튼 클릭 이벤트 처리
    public void actionPerformed(ActionEvent e) {
    	JButton clickedButton = (JButton) e.getSource();

        if (selectedButton == null) {
            selectedButton = clickedButton;
            selectedButton.setEnabled(false); // 선택한 버튼 비활성화
        } else {
            // 두 번째 버튼을 선택한 경우
            selectedButton.setEnabled(true); // 첫 번째 버튼 다시 활성화

            if (selectedButton == clickedButton) {
                // 같은 버튼을 다시 클릭한 경우, 선택 취소
                selectedButton = null;
            } else {
                // 다른 버튼을 선택한 경우
                clickedButton.setEnabled(false); // 선택한 버튼 비활성화

                int selectedRow = -1;
                int selectedColumn = -1;
                int clickedRow = -1;
                int clickedColumn = -1;

                // 선택한 버튼과 클릭한 버튼의 위치 찾기
                for (int i = 0; i < SIZE1; i++) {
                    for (int j = 0; j < SIZE2; j++) {
                        if (buttons[i][j] == selectedButton) {
                            selectedRow = i;
                            selectedColumn = j;
                        } else if (buttons[i][j] == clickedButton) {
                            clickedRow = i;
                            clickedColumn = j;
                        }
                    }
                }

                if (selectedButton.getIcon()==clickedButton.getIcon()) {
                    // 짝이 맞는 경우
                    matchedCount += 2;
                    selectedButton.setVisible(false); // 첫 번째 버튼 숨김 처리
                    clickedButton.setVisible(false); // 두 번째 버튼 숨김 처리
                    selectedButton = null;
                    
                    MP3Play("Trring");
                    
                    if (matchedCount == SIZE1 * SIZE2) {
                        // 모든 짝을 맞췄을 때
                    	stopTimer();
                    	MP3Play("win");
                    	int re =JOptionPane.showConfirmDialog(this, "다시 하시겠습니까?","축하합니다!",JOptionPane.OK_CANCEL_OPTION);
                    	if (re == 0) {
                    		win.change("jp5");
                    	}
                    	else {
                    		win.change("jp1");
                    	}
                    }
                    
                } else {
                    // 짝이 맞지 않는 경우
                    selectedButton.setEnabled(true); // 첫 번째 버튼 활성화
                    clickedButton.setEnabled(true); // 두 번째 버튼 활성화
                    selectedButton = null; // 선택 취소
                 }
            }
        }

    }
    public void MP3Play(String name) {
		 try {
            FileInputStream fis = new FileInputStream(new File(audio_url+name+".mp3"));
            Player player = new Player(fis);
            new Thread() {
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }.start();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
	}
    
}