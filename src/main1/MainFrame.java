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
	boolean winCheck = false;//�Ƿ�Ҫ����ʤ���ж�
	int difficulty = 30;
//���������Ŀ���Ѷȣ�����������ж��ٸ��ո����Ŀ����Ĭ��Ϊ//����30���ո����Ŀ
	MyReader mr = new MyReader("");//������
	Solver my = new Solver();//������
    Creator c = new Creator();//���������
	int[][] origin = new int [9][9];//��Ŀ���֡�
	int[][] matrix = new int [9][9];
//��ǰ��Ϸ����������������ݡ�
	TimeKeeper time = new TimeKeeper();//��ʱ��
	JMenuItem jmi1 = new JMenuItem("�˳�");
	JMenuItem jmi2 = new JMenuItem("���ٿ���");
	JMenuItem jmi3 = new JMenuItem("�Զ�����Ŀ");
	JMenuItem jmi4 = new JMenuItem("��ʾ��");
	JMenuItem jmi5 = new JMenuItem("������Ŀ");
	JMenuItem jmi6 = new JMenuItem("������Ŀ");
	JMenuItem jmi7 = new JMenuItem("��ʱ�浵");
	JMenuItem jmi8 = new JMenuItem("������ǰ�浵");
	JMenuItem jmi9 = new JMenuItem("��");
	JMenuItem jmi10 = new JMenuItem("�̱�׼");
	JMenuItem jmi11 = new JMenuItem("����");
	JMenuItem jmi12 = new JMenuItem("�ǻ�");
	JMenuItem jmi13 = new JMenuItem("����");
	JButton but1=new JButton("PLAY");
    JButton but2=new JButton("��ͣ");
    JButton but3=new JButton("00:00:00");
    JButton but4=new JButton("���");
    public  MainFrame(){//������
    	pnlGame = new JPanel[9];
		txtGame = new JTextField[9][9];
		BorderLayout b1=new BorderLayout(5,5);
		setLayout(b1);
		JMenuBar jmb=new JMenuBar();
        JMenu jm1 = new JMenu("ģʽ");
        JMenu jm2 = new JMenu("��Ϸ");
        JMenu jm3 = new JMenu("�Զ�����Ŀ");
        JMenu jm4 = new JMenu("�Ѷ��趨");
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
				txtGame[i][j].setFont(new Font("Dialog", Font.ITALIC, 20));//���������С
				txtGame[i][j].setHorizontalAlignment(JTextField.CENTER);//�����������
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
    //�趨ÿ����ť��С���Ƿ���õȲ���
			for(int j = 0;j < 9;j++){
				if (matrix[i][j] == 0)
					txtGame[i][j].setText("");
				else {
				    txtGame[i][j].setText("" + matrix[i][j]);
				    txtGame[i][j].setBackground(null);
					txtGame[i][j].setSize(50,50);
                    txtGame[i][j].setEditable(false);//�ʼʱ���ܵ��
				}
			}
		}
    }
    void  clear(){                         
    //�����¿���֮������ϴ���Ϸ������
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
     //�˳���Ϸ
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
	}
	 public class ReplayListener implements ActionListener{
     //���汾��
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
    //�Ѷ��趨��ѡ���ѶȵĲ˵�ѡ��ǰ������֡�
		 public void actionPerformed(ActionEvent e){
				jmi9.setText("��");
				jmi10.setText("��׼");
				jmi11.setText("����");
				jmi12.setText("�ǻ�");
				if(e.getSource().equals(jmi9)){
					jmi9.setText("�̼�");
					difficulty = 15;
				}
				if(e.getSource().equals(jmi10)){
					jmi10.setText("�̱�׼");
					difficulty = 30;
				}
				if(e.getSource().equals(jmi11)){
					jmi11.setText("������");
					difficulty = 45;
				}
				if(e.getSource().equals(jmi12)){
					jmi12.setText("�̹ǻ�");
					difficulty = 81;
				}
     //��ȷ����������¾����ܶ���ڵ����ӡ�
		}
	 }
	 public class StartListener implements ActionListener{
             //��ʼ�����Ϸ.
		public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(true);
//��ÿ��ģʽ����ʹ�õĺͲ����õĲ˵���������趨����ͬ.
			jmi5.setEnabled(false);
			jmi7.setEnabled(true);
			jmi13.setEnabled(true);
			start = true;
			clear();
			c.creator(origin,txtGame,difficulty);//�����漴��Ŀ
			c.copy(origin,matrix);//��������Ƹ�matrix����
			winCheck = true;//��Ҫ����ʤ���ж�
			time.setSecond(0);//��ʱ������
		}
	 }
	 public class SaveListener implements ActionListener{
      //�����Զ�����Ŀ��������Ŀ��
		public void actionPerformed(ActionEvent e){
			Checker c = new Checker();
			c.checkCustom(txtGame,matrix);
   //�����Ŀ�Ƿ��г�ͻ�Լ��Ƿ���Ψһ�Ⲣ����
		}
	 }
	 public class SaveInGameListener implements ActionListener{
        //��Ϸ�б�����ȡ�
		public void actionPerformed(ActionEvent e){
		    Saver saver = new Saver("save");
     //������Ŀ¼�趨Ϊsave
		 saver.save(txtGame,matrix,time.getSecond());//����
		}
	 }
	
	 public class LoadInGameListener implements ActionListener{
     //��ʱ������
		 public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(true);
			jmi5.setEnabled(false);
			jmi7.setEnabled(true);
			jmi13.setEnabled(true);
			start = true;
			clear();
			int saveTime = 0;
			mr = new MyReader("save");
  //���߶�����Ӧ�ô�saveĿ¼ȥ����Ϸ�浵
			saveTime = mr.read(txtGame,matrix,origin);
   //��ȡ��Ϸ�浵�Լ�ʱ��
			winCheck = true;//��Ҫ����ʤ���ж�
			time.setSecond(saveTime);
     //����ʱ��ʱ���趨Ϊ�����ʱ��
		 }
	 }
	 public class LoadListener implements ActionListener{
          //�����Զ�����Ŀ
		 public void actionPerformed(ActionEvent e){
				jmi4.setEnabled(true);
				jmi5.setEnabled(false);
				jmi7.setEnabled(true);
				jmi13.setEnabled(true);
				clear();
				mr = new MyReader("puzzle");
	    //���߶�����Ӧ�ô�puzzleĿ¼ȥ���Զ�����Ŀ
				mr.read(txtGame,matrix,origin);//��ȡ��Ŀ
				c.copy(origin,matrix);//����Ŀ���Ƹ�origin
				start = true;
				winCheck = true;//��Ҫ����ʤ���ж�
				time.setSecond(0);//��ʱ������
		 }
	 }
	 public class CustomListener implements ActionListener{
           //�����Զ�����Ŀģʽ
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
			winCheck = false;//����Ҫ����ʤ���ж�
		}
	}  
	 public class ButtonListener extends MouseAdapter{
          //������궯��
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
					winCheck=myChecker.checkWin(txtGame,matrix,time.getSecond(),winCheck);//����ʤ���ж�
			}
		}
	 }
 
													
	 public class AnswerListener implements ActionListener{
        //��ʾ��ǰ��Ŀ�Ĵ𰸡�
		public void actionPerformed(ActionEvent e){
			jmi4.setEnabled(false);
			jmi5.setEnabled(false);
			jmi7.setEnabled(false);
			jmi13.setEnabled(false);
			time.stop();
			start = false;
			winCheck = false;//��Ҫ����ʤ���ж�
			my.solve(origin,txtGame);//��ʾ��
		}
	 }
	 
	 public void main(String[] args){
			MainFrame m = new MainFrame();
			m.setTitle("������Ϸ");
			m.setLocation(100,150);
			m.setLocationRelativeTo(null);
			m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    m.setSize(550,550);
			m.setVisible(true);
			m.setResizable(false);
	 }
}}
