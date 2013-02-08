package main1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainFrame extends JFrame {
	public JPanel[] pnlGame;
	public JTextField[][] txtGame;
	boolean start = false;
	boolean winCheck = false;//是否要进行胜利判定
	int difficulty = 30;
//随机产生题目的难度（即随机产生有多少个空格的题目）。默认为//产生30个空格的题目
	MyReader mr = new MyReader("");//读档器
	Solver my = new Solver();//解题器
    Creator c = new Creator();//随机出题器
	int[][] origin = new int [9][9];//题目数字。
	int[][] matrix = new int [9][9];
//当前游戏数字填入情况的数据。
	TimeKeeper time = new TimeKeeper();//计时器
	JMenuItem jmi1 = new JMenuItem("退出");
	JMenuItem jmi2 = new JMenuItem("快速开局");
	JMenuItem jmi3 = new JMenuItem("自定义题目");
	JMenuItem jmi4 = new JMenuItem("显示答案");
	JMenuItem jmi5 = new JMenuItem("保存题目");
	JMenuItem jmi6 = new JMenuItem("载入题目");
	JMenuItem jmi7 = new JMenuItem("即时存档");
	JMenuItem jmi8 = new JMenuItem("载入先前存档");
	JMenuItem jmi9 = new JMenuItem("简单");
	JMenuItem jmi10 = new JMenuItem("√标准");
	JMenuItem jmi11 = new JMenuItem("困难");
	JMenuItem jmi12 = new JMenuItem("骨灰");
	JMenuItem jmi13 = new JMenuItem("重玩");
	JButton but1=new JButton("PLAY");
    JButton but2=new JButton("暂停");
    JButton but3=new JButton("00:00:00");
    JButton but4=new JButton("完成");
    public  MainFrame(){//主界面
    	pnlGame = new JPanel[9];
		txtGame = new JTextField[9][9];
		BorderLayout b1=new BorderLayout(5,5);
		setLayout(b1);
		JMenuBar jmb=new JMenuBar();
        JMenu jm1 = new JMenu("模式");
        JMenu jm2 = new JMenu("游戏");
        JMenu jm3 = new JMenu("自定义题目");
        JMenu jm4 = new JMenu("难度设定");
	    ActionListener exitListener = new ExitListener();
		ActionListener startListener = new StartListener();
		ActionListener customListener = new CustomListener();
		ActionListener loadListener = new LoadListener();
		ActionListener saveInGameListener = new SaveInGameListener();
		ActionListener answerListener = new AnswerListener();
		ActionListener saveListener = new SaveListener();
		ActionListener loadInGameListener = new LoadInGameListener();
		ActionListener difficultyListener = new DifficultyListener();
		ActionListener replayListener = new ReplayListener();
	 	MouseListener buttonListener = new ButtonListener();
	 // MouseListener textfieldListener = new TextFieldListener();
	    jmi1.addActionListener(exitListener);
		jmi2.addActionListener(startListener);
		jmi3.addActionListener(customListener);
		jmi6.addActionListener(loadListener);
		jmi4.addActionListener(answerListener);
		jmi5.addActionListener(saveListener);
		jmi7.addActionListener(saveInGameListener);
		jmi8.addActionListener(loadInGameListener);
		jmi9.addActionListener(difficultyListener);
		jmi10.addActionListener(difficultyListener);
		jmi11.addActionListener(difficultyListener);
		jmi12.addActionListener(difficultyListener);
		jmi13.addActionListener(replayListener);
		jm1.add(jmi2);
		jm1.add(jmi6);
		jm1.add(jmi3);
		jm1.add(jmi8);
		jm1.add(jmi1);
		jm2.add(jmi4);
		jm2.add(jmi7);
		jm2.add(jmi13);
		jm3.add(jmi5);
		jm4.add(jmi10);
		jm4.add(jmi9);
		jm4.add(jmi11);
		jm4.add(jmi12);
	
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		add(jmb,b1.NORTH);
		JPanel mainPanel1 = new JPanel();
		JPanel mainPanel2 = new JPanel();
		JPanel mainPanel3 = new JPanel();
		JPanel mainPanel4 = new JPanel();
		add(mainPanel1,b1.CENTER);
		time.setPreferredSize(new Dimension(80,300));
		add(time,b1.EAST);
		mainPanel2.add(but1);
		mainPanel2.add(but3);
		mainPanel2.add(but2);
		mainPanel2.add(but4);
		add(mainPanel2,b1.SOUTH);
		mainPanel1.setLayout(new GridLayout(3,3));
		for(int i = 0;i < 9;i++){
			pnlGame[i] = new JPanel();
			pnlGame[i].setBorder(BorderFactory.createLineBorder(Color.black));
			pnlGame[i].setLayout(new GridLayout(3,3));
			for(int j = 0;j < 9;j++){
				txtGame[i][j] = new JTextField();
				txtGame[i][j].setBorder(BorderFactory.createEtchedBorder());
				txtGame[i][j].setFont(new Font("Dialog", Font.ITALIC, 20));//设置字体大小
				txtGame[i][j].setHorizontalAlignment(JTextField.CENTER);//设置字体居中
			}
			mainPanel1.add(pnlGame[i]);
		}
		for(int i = 0;i < 3;i++){
			pnlGame[0].add(txtGame[i][0]);
			pnlGame[0].add(txtGame[i][1]);
			pnlGame[0].add(txtGame[i][2]);
			pnlGame[1].add(txtGame[i][3]);
			pnlGame[1].add(txtGame[i][4]);
			pnlGame[1].add(txtGame[i][5]);
			pnlGame[2].add(txtGame[i][6]);
			pnlGame[2].add(txtGame[i][7]);
			pnlGame[2].add(txtGame[i][8]);
		}
		for(int i = 3;i < 6;i++){
			pnlGame[3].add(txtGame[i][0]);
			pnlGame[3].add(txtGame[i][1]);
			pnlGame[3].add(txtGame[i][2]);
			pnlGame[4].add(txtGame[i][3]);
			pnlGame[4].add(txtGame[i][4]);
			pnlGame[4].add(txtGame[i][5]);
			pnlGame[5].add(txtGame[i][6]);
			pnlGame[5].add(txtGame[i][7]);
			pnlGame[5].add(txtGame[i][8]);
		}
		for(int i = 6;i < 9;i++){
			pnlGame[6].add(txtGame[i][0]);
			pnlGame[6].add(txtGame[i][1]);
			pnlGame[6].add(txtGame[i][2]);
			pnlGame[7].add(txtGame[i][3]);
			pnlGame[7].add(txtGame[i][4]);
			pnlGame[7].add(txtGame[i][5]);
			pnlGame[8].add(txtGame[i][6]);
			pnlGame[8].add(txtGame[i][7]);
			pnlGame[8].add(txtGame[i][8]);
		}
		but1.addMouseListener(buttonListener);
		but2.addMouseListener(buttonListener);
		but4.addMouseListener(buttonListener);
		for(int i = 0;i < 9;i++){
    //设定每个按钮大小、是否可用等参数
			for(int j = 0;j < 9;j++){
				if (matrix[i][j] == 0)
					txtGame[i][j].setText("");
				else {
				    txtGame[i][j].setText("" + matrix[i][j]);
				    txtGame[i][j].setBackground(null);
					txtGame[i][j].setSize(50,50);
                    txtGame[i][j].setEditable(false);//最开始时不能点击
				}
			}
		}
    }
    void  clear(){                         
    //在重新开局之后清空上次游戏的数据
			for(int i = 0;i < 9;i++){
				for(int j = 0;j < 9;j++){
					matrix[i][j] = 0;
					origin[i][j] = 0;
					txtGame[i][j].setText("");
					txtGame[i][j].setEditable(false);
					txtGame[i][j].setBackground(Color.WHITE);
				}
			}
	}
	 public class ExitListener implements ActionListener{
     //退出游戏
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
	}
	 public class ReplayListener implements ActionListener{
     //重玩本局
		 public void actionPerformed(ActionEvent e){
			time.stop();
			for(int i = 0;i < 9;i++){
			    for(int j = 0;j < 9;j++){
				    if(txtGame[i][j].getBackground().equals(Color.WHITE)){
				    	matrix[i][j] = 0;
				      txtGame[i][j].setText("");
				      txtGame[i][j].setEditable(false);					  
				     }
				 }
			}
		    time.setSecond(0);
	 } 
	 public class DifficultyListener implements ActionListener{
    //难度设定。选中难度的菜单选项前将会出现√
		 public void actionPerformed(ActionEvent e){
				jmi9.setText("简单");
				jmi10.setText("标准");
				jmi11.setText("困难");
				jmi12.setText("骨灰");
				if(e.getSource().equals(jmi9)){
					jmi9.setText("√简单");
					difficulty = 15;
				}
				if(e.getSource().equals(jmi10)){
					jmi10.setText("√标准");
					difficulty = 30;
				}
				if(e.getSource().equals(jmi11)){
					jmi11.setText("√困难");
					difficulty = 45;
				}
				if(e.getSource().equals(jmi12)){
					jmi12.setText("√骨灰");
					difficulty = 81;
				}
     //在确定解答的情况下尽可能多的挖掉格子。
		}
	 }
	 public class StartListener implements ActionListener{
             //开始随机游戏.
		public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(true);
//将每个模式可以使用的和不可用的菜单命令进行设定，下同.
			jmi5.setEnabled(false);
			jmi7.setEnabled(true);
			jmi13.setEnabled(true);
			start = true;
			clear();
			c.creator(origin,txtGame,difficulty);//产生随即题目
			c.copy(origin,matrix);//将结果复制给matrix数组
			winCheck = true;//需要进行胜利判定
			time.setSecond(0);//计时器清零
		}
	 }
	 public class SaveListener implements ActionListener{
      //检验自定义题目并储存题目。
		public void actionPerformed(ActionEvent e){
			Checker c = new Checker();
			c.checkCustom(txtGame,matrix);
   //检查题目是否有冲突以及是否有唯一解并保存
		}
	 }
	 public class SaveInGameListener implements ActionListener{
        //游戏中保存进度。
		public void actionPerformed(ActionEvent e){
		    Saver saver = new Saver("save");
     //将保存目录设定为save
		 saver.save(txtGame,matrix,time.getSecond());//保存
		}
	 }
	
	 public class LoadInGameListener implements ActionListener{
     //即时读档。
		 public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(true);
			jmi5.setEnabled(false);
			jmi7.setEnabled(true);
			jmi13.setEnabled(true);
			start = true;
			clear();
			int saveTime = 0;
			mr = new MyReader("save");
  //告诉读档器应该从save目录去读游戏存档
			saveTime = mr.read(txtGame,matrix,origin);
   //读取游戏存档以及时间
			winCheck = true;//需要进行胜利判定
			time.setSecond(saveTime);
     //将计时器时间设定为保存的时间
		 }
	 }
	 public class LoadListener implements ActionListener{
          //载入自定义题目
		 public void actionPerformed(ActionEvent e){
				jmi4.setEnabled(true);
				jmi5.setEnabled(false);
				jmi7.setEnabled(true);
				jmi13.setEnabled(true);
				clear();
				mr = new MyReader("puzzle");
	    //告诉读档器应该从puzzle目录去读自定义题目
				mr.read(txtGame,matrix,origin);//读取题目
				c.copy(origin,matrix);//把题目复制给origin
				start = true;
				winCheck = true;//需要进行胜利判定
				time.setSecond(0);//计时器清零
		 }
	 }
	 public class CustomListener implements ActionListener{
           //进入自定义题目模式
		public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(false);
			jmi5.setEnabled(true);
			jmi7.setEnabled(false);
			jmi13.setEnabled(false);
			clear();
			for(int i = 0;i < 9;i++){
				for(int j = 0;j < 9;j++){
					txtGame[i][j].setBackground(Color.WHITE);
					txtGame[i][j].setEditable(true);
				}
			}
			start = true;
			time.stop();
			winCheck = false;//不需要进行胜利判定
		}
	}  
	 public class ButtonListener extends MouseAdapter{
          //监听鼠标动作
		public void mouseClicked(MouseEvent e){
			Checker myChecker = new Checker();
			if(e.getSource() == but2){
				time.pause();
				}
			if(e.getSource() == but1){
				for(int i = 0;i < 9;i++){
					for(int j = 0;j < 9;j++){
						if(matrix[i][j] == 0){
							txtGame[i][j].setText("");
							txtGame[i][j].setEditable(true);
						}
					}
				}
			time.start();
			}
			if(e.getSource() == but4){
					for (int i = 0; i < 9; i ++) {
		                for (int j = 0; j < 9; j ++) {
		                	if (txtGame[i][j].getText().compareTo("") != 0){
		                		matrix[i][j]=(int)Integer.parseInt(txtGame[i][j].getText());
		                		txtGame[i][j].setText(""+matrix[i][j]);
		                    }
		                	else{
		                		matrix[i][j] = 0;
		                		txtGame[i][j].setText("");
		                    }
		                 myChecker.check(txtGame,matrix);
		    			 myChecker.checkGap(txtGame,matrix);
		                }
		             }
					winCheck=myChecker.checkWin(txtGame,matrix,time.getSecond(),winCheck);//进行胜利判定
			}
		}
	 }
 
													
	 public class AnswerListener implements ActionListener{
        //显示当前题目的答案。
		public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(false);
			jmi5.setEnabled(false);
			jmi7.setEnabled(false);
			jmi13.setEnabled(false);
			time.stop();
			start = false;
			winCheck = false;//需要进行胜利判定
			my.solve(origin,txtGame);//显示答案
		}
	 }
	 
	 public void main(String[] args){
			MainFrame m = new MainFrame();
			m.setTitle("数独游戏");
			m.setLocation(100,150);
			m.setLocationRelativeTo(null);
			m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    m.setSize(550,550);
			m.setVisible(true);
			m.setResizable(false);
	 }
}}
