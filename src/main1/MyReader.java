package main1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
//import java.util.*;
//import javax.swing.filechooser.FileFilter;

public class MyReader{
     private String lpath = "";
//��ȡ�浵��·�����������Ϸ�浵��Ϊsave;��Ŀ�浵��Ϊpuzzle
     MyReader(String lpath){//��ȡ��ȡĿ¼
		this.lpath = lpath;
     }
     int read(JTextField[][] txtGame,int[][] matrix,int[][] origin){//��ȡ�浵�ļ�
		int saveTime = 0;
		JFileChooser file = new JFileChooser(".\\" + lpath);
    //����ȡ·���趨Ϊ��Ҫ·�����ҿ���FileChooser
		file.setAcceptAllFileFilterUsed(true);
   //�����ȡ����·�����ļ�
		int result = file.showOpenDialog(null);//��ȡ�û�����
		if(result == JFileChooser.APPROVE_OPTION){
   //���û�ѡ����һ���ļ��ͽ��ж�ȡ
			String path = file.getSelectedFile().getAbsolutePath();//��ȡ�ļ���·��
			File readFile = new File(path);
			try{
				DataInputStream input = new DataInputStream(new FileInputStream(readFile));
				for(int i = 0;i < 9;i++){
 //���մ���ĸ�ʽ���ζ�ȡ�������λ�õ�ֵ����ť����ɫ
					for(int j = 0;j < 9;j++){
						matrix[i][j] = input.readInt();
						int color = input.readInt();
						int temp = input.readInt();
            //Ϊ�˷�������ڴ���ʱ��99���˷ָ�
						if(matrix[i][j] != 0){//�趨��ť����ʾ����
							txtGame[i][j].setText("" + matrix[i][j]);
							txtGame[i][j].setBackground(null);							
							txtGame[i][j].setEditable(false);
							if(lpath.equals("puzzle")){
 //����ȡ������Ŀ�򽫲�Ϊ0�İ�ť��ɫ�趨Ϊ��ʾ��Ŀ�ķ�ɫ
								txtGame[i][j].setBackground(Color.PINK);
								Creator c = new Creator();
								c.copy(matrix,origin);
							}
							else if(lpath.equals("save")){
     //����ȡ������Ϸ�浵���մ������ɫ�����趨
								Checker c = new Checker();
								switch(color){
									case 1 : txtGame[i][j].setBackground(Color.RED);break;
									case 3 : txtGame[i][j].setBackground(Color.YELLOW);break;
									case 4 : txtGame[i][j].setBackground(Color.PINK);
											origin[i][j] = matrix[i][j];break;
									case 2 : txtGame[i][j].setBackground(Color.GREEN);break;
									case 0 : break;
			    	       }}}}}
		Checker checker = new Checker();
		checker.checkGap(txtGame,matrix);
// Ϊȷ����ɫ��ʾû�д������¼��յĸ����Ƿ�����������
		if(lpath.equals("save")){
 //���Ƕ�ȡ��Ϸ�浵��Ҫ��ȡ����ʱ����Ϸʱ��
			saveTime = input.readInt();
		}
		input.close();
			}catch(IOException e){
			}}
		return saveTime;//���ض�ȡ�ı���ʱ�������趨��ʱ����
	}}
