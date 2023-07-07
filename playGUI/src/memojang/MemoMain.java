package memojang;

import seonjingame.*;
import calendar.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MemoMain extends JFrame implements ActionListener{
	JTextArea ta;
	JFileChooser jfc;
	boolean isNew = false;
	File file = null;
	FontStyleView fontStyleView;
	StatusBar statusBar;
	JButton newBtn,openBtn, saveBtn, exitBtn, copyBtn, pasteBtn, cutBtn, fontBtn,colBtn;
	
	public MemoMain() {
		MainView();
		setTitle("선진이네 메모장");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ta = new JTextArea();
		JScrollPane jsp = new JScrollPane(ta);
		jfc = new JFileChooser();
		this.add(jsp);
		statusBar = new StatusBar(ta);
		this.add(BorderLayout.SOUTH, statusBar);
		setBounds(800,500,500,700);
		setVisible(true);
		
	}
	
	public void MainView() {
		KeyStroke key;
		
		JMenuBar mb = new JMenuBar();
		JMenu m1, m2, m3, m4, m5, m6;
		JMenuItem item;
		JToolBar tb = new JToolBar();
		
		
		///Toolbar Menu ////////////////////
		ImageIcon newF = new ImageIcon("image/new.png"); ImageIcon openF = new ImageIcon("image/evd.png");
		ImageIcon saveF = new ImageIcon("image/save.png"); ImageIcon exitF = new ImageIcon("image/haha.png");
		ImageIcon copyF = new ImageIcon("image/copy.png"); ImageIcon pasteF = new ImageIcon("image/paste.png");
		ImageIcon cutF = new ImageIcon("image/cut.png"); ImageIcon colF = new ImageIcon("image/color.png"); 
		ImageIcon fontF = new ImageIcon("image/fontt.png"); 
		
		newBtn = new JButton(newF); openBtn = new JButton(openF);
		saveBtn = new JButton(saveF); exitBtn = new JButton(exitF);
		copyBtn = new JButton(copyF); pasteBtn = new JButton(pasteF);
		cutBtn = new JButton(cutF); fontBtn = new JButton(fontF);
		colBtn = new JButton(colF);
		
		newBtn.addActionListener(this); 
		openBtn.addActionListener(this);
		saveBtn.addActionListener(this); 
		exitBtn.addActionListener(this); 
		
		copyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.copy();
			}}); 
		pasteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.paste();
			}}); 
		cutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.cut();
			}});
		fontBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontStyleView = new FontStyleView(ta);
				fontStyleView.setBounds(200,200,400,350);
				fontStyleView.setVisible(true);
			}});
		colBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.black);
				if(setColor != null)
					ta.setForeground(setColor);
				
			}});
		
		newBtn.setToolTipText("새파일을 작성합니다");
		openBtn.setToolTipText("파일을 불러옵니다");
		saveBtn.setToolTipText("파일을 저장합니다");
		exitBtn.setToolTipText("메모장을 종료합니다");
		copyBtn.setToolTipText("복사합니다");
		pasteBtn.setToolTipText("붙여넣습니다");
		cutBtn.setToolTipText("잘라입니다");
		fontBtn.setToolTipText("글꼴 바꾸기");
		colBtn.setToolTipText("색 바꾸기");
		
		tb.add(newBtn); tb.add(openBtn); tb.add(saveBtn); tb.add(exitBtn); tb.add(copyBtn); 
		tb.add(pasteBtn); tb.add(cutBtn); tb.addSeparator();tb.add(fontBtn); tb.add(colBtn); 
		
		//J 메뉴 ///////////////////////////////
		m1 = new JMenu("파일(F)"); m1.setMnemonic(KeyEvent.VK_F);
		
		item = new JMenuItem("새로 만들기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m1.add(item);
		
		item = new JMenuItem("열기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m1.add(item);
		
		item = new JMenuItem("저장");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m1.add(item);
		m1.addSeparator();
		
		item = new JMenuItem("다른이름으로 저장");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m1.add(item);
		m1.addSeparator();
		
		item = new JMenuItem("끝내기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m1.add(item);
		
		////////////////////////////////////////////////////////////
		
		m2 = new JMenu("편집(E)"); m2.setMnemonic(KeyEvent.VK_E);
		
		item = new JMenuItem("실행취소");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
			}});
		
		m2.add(item);
		
		item = new JMenuItem("잘라내기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.cut();
			}});
		m2.add(item);
		
		item = new JMenuItem("복사");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.copy();
			}});
		m2.add(item);
		m2.addSeparator();
		
		item = new JMenuItem("붙여넣기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.paste();
			}});
		m2.add(item);
	
		
		item = new JMenuItem("삭제");
		//key = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ta.cut();
				
			}});
		m2.add(item);
		m2.addSeparator();
		
		item = new JMenuItem("찾기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m2.add(item);
		
		
		item = new JMenuItem("찾아 바꾸기");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m2.add(item);
		
		item = new JMenuItem("이동");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(this);
		m2.add(item);
		m2.addSeparator();
		
		item = new JMenuItem("모두 선택");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.selectAll();
			}});
		m2.add(item);
		
		item = new JMenuItem("시간/날짜");
		key = KeyStroke.getKeyStroke("F5");
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date d = new Date();
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss");
				ta.append(sd.format(d).toString());
				
			}});
		m2.add(item);
		////////////////////////////////////////////////
		m3 = new JMenu("서식(O)"); m3.setMnemonic(KeyEvent.VK_O);
		
		item = new JMenuItem("배경색");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.yellow);
				if(setColor != null)
					ta.setBackground(setColor);
				
			}});
		m3.add(item);
		item = new JMenuItem("글꼴색");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color setColor = JColorChooser.showDialog(getParent(), "색상표", Color.black);
				if(setColor != null)
					ta.setForeground(setColor);
				
			}});
		m3.add(item);
		item = new JMenuItem("글꼴");
		key = KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK);
		item.setAccelerator(key);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fontStyleView = new FontStyleView(ta);
				fontStyleView.setBounds(200,200,400,350);
				fontStyleView.setVisible(true);
			}});
		m3.add(item);
		/////////////////////////////
		m4 = new JMenu("보기(V)"); m4.setMnemonic(KeyEvent.VK_V);
		JMenu submenu = new JMenu("확대하기/축소하기");
		m4.add(submenu);
		submenu.add(new JMenuItem("확대")).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Font ft = ta.getFont();
				int fts= ft.getSize()+2;
				ft = new Font(ft.getName(),ft.getStyle(),fts); //name,style,size
				ta.setFont(ft);
				
			}
			
		});
		submenu.add(new JMenuItem("축소")).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Font ft = ta.getFont();
				int fts= ft.getSize()-2;
				ft = new Font(ft.getName(),ft.getStyle(),fts); //name,style,size
				ta.setFont(ft);
			}
			
		});
		submenu.add(new JMenuItem("확대/축소하기 복원")).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Font ft = ta.getFont();
				ft = new Font(ft.getName(),ft.getStyle(),12); //name,style,size
				ta.setFont(ft);
			}
			
		});
		m4.addSeparator();
		JCheckBoxMenuItem cb = new JCheckBoxMenuItem("상태표시줄");
		cb.setState(true);
	      cb.addChangeListener(new ChangeListener() {

	      @Override
	      public void stateChanged(ChangeEvent e) {
	         // TODO Auto-generated method stub
	         if(cb.getState()==true) 
	        	 statusBar.setVisible(true);
	         else 
	        	 statusBar.setVisible(false);
	  	}
	      });
		m4.add(cb);
		
		/////////////////////////////
		m5 = new JMenu("도움말(H)"); m5.setMnemonic(KeyEvent.VK_H);
		item = new JMenuItem("도움말 보기");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Memoinfo();
			}
			
		});
		m5.add(item);
		
		item = new JMenuItem("메모장 정보");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String img = "C:/Users/hawoo/Desktop/webPro/fox.png";
				ImageIcon imageIcon = new ImageIcon(img);
				JOptionPane.showMessageDialog(null,"202116023 임선진\n "
						+ "\n Java Swing\n 버전1.0 \n 선진이에 의해 보호됩니다","선진이네 메모장 정보"
						,JOptionPane.INFORMATION_MESSAGE,imageIcon);
				
				
			}});
		m5.add(item);
		///////////////////////마지막/////////////////
		m6 = new JMenu("잡동사니");
		item = new JMenuItem("폰북");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new HelloSwing();
				
			}
			
		});
		m6.add(item);
		item = new JMenuItem("계산기");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Calulator();
			}
			
		});
		m6.add(item);
		item = new JMenuItem("스케줄러");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new calendar.MemoCalendar();
			}
			
		});
		m6.add(item);
		
		item = new JMenuItem("게임");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new seonjingame.mains1().gamestart();
			}
			
		});
		m6.add(item);
		
		///메뉴 끝/////////////////////////
		mb.add(m1);mb.add(m2);mb.add(m3);mb.add(m4);mb.add(m5);mb.add(m6);
		this.setJMenuBar(mb);
		this.add(tb,BorderLayout.NORTH);
		///////////////////아이콘 //////////////////
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MemoMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		String tool_cmd = e.getSource().toString();
		
		if (tool_cmd != null) {
			if(cmd.equals("새로 만들기") || e.getSource()==newBtn){
				int a = check();
				if (a==1) {
					int b= JOptionPane.showConfirmDialog(this,"작성된 내용을 저장?","저장여부확인",1);
					if (b==0) save(); //yes
					else if (b==1) { //no
						ta.setText("");
						isNew=false;
						file = null;
					}
				}else {
					ta.setText("");
					isNew=false;
					file = null;
				}
				
			}
		
			else if(cmd.equals("열기")|| e.getSource()==openBtn){
				int a = check();
				if (a==1) {
					int b= JOptionPane.showConfirmDialog(this,"작성된 내용을 저장?","저장여부확인",1);
					if (b==0) save();
					else if (b==1) open();
				
				}else {
					open();
				}
				
			}
			else if(cmd.equals("저장")|| e.getSource()==saveBtn) save( );
			else if (cmd.equals("다른이름으로 저장")) saveAs( );
			else if(cmd.equals("끝내기")|| e.getSource()==exitBtn) System.exit(0);	
			/////////////////////////////////////////////////
			else if(cmd.equals("찾기")){
				Find fi = new Find(this,ta);
				fi.showFind();
			}
			else if(cmd.equals("찾아 바꾸기")){
				Find fi = new Find(this,ta);
				fi.showReplace();
			}
		
		}
		}

	int check() {
		int a=0;
		String data ="";
		
		int ch;
		try {
			if (isNew==true) { //저장된 파일에 변화가 있는지 
				FileReader fr = new FileReader(file);
				while ((ch = fr.read()) != -1) 
					data = data + (char)ch;
				fr.close();
				
				if (!ta.getText().equals(data)) 
					a=1;		
			}
			else if (isNew == false && !ta.getText().equals(""))
				a=1;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			return a;
		}
		return a;
		
		
	}

 	 void open() {
 		int re = jfc.showOpenDialog(this);
 		if (re == jfc.APPROVE_OPTION) {
 			file = jfc.getSelectedFile();
 			this.setTitle(file.getAbsolutePath()+"-선진이네 메모장");
 			
 			String data ="";
 			int ch;
 			try {
				FileReader fr = new FileReader(file);
				while ((ch = fr.read()) != -1) {
					data = data + (char)ch;
				}
				ta.setText(data);
				fr.close();
				isNew = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
 		}
	}
 	void save() {
 		int re=0;
 		if (isNew == false) {
 			re = jfc.showSaveDialog(this);
 			isNew = true;
 		}
 		if (re == jfc.APPROVE_OPTION) {
 			file = jfc.getSelectedFile();	
 			String fileP = file.getAbsolutePath();
 			try {
 				this.setTitle(file.getAbsolutePath()+"-선진이네 메모장");
 				FileWriter fw = new FileWriter(file);
				fw.write(ta.getText());
				fw.close();
				JOptionPane.showMessageDialog(this, "파일을 저장했습니다.");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 	}
 	
 	void saveAs() {
 		isNew = false;
 		save();
	}
}
