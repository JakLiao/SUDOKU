package main1;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.util.*;
class Checker{
     int[][] color = new int[9][9];//��ť����ɫ
	 Solver s = new Solver();
     public Checker(){
    	 
     }
     void check(JButton[][] jbt,int[][] matrix){
    	 //��������Ƿ������л����ǾŹ�����ֳ�ͻ����û�оͽ����ڰ�ť//��ɫ�趨Ϊ��ɫ����Ȼ���趨Ϊ��ɫ��
    	 String[] returnString = new String[2];
    	 for(int line = 0;line < 9;line++){
    		 for(int column = 0;column < 9;column++){
    			 returnString[0] = returnString[1] = "";
    			 for(int i = 0;i < 9;i++){//�������
    				 if ((i != column) && (matrix[line][column] == matrix[line][i])){
    					 returnString[1] += i;
    					 returnString[0] += line;
    				 }
    				 if ((i != line) && (matrix[line][column] == matrix[i][column])){
    					 returnString[0] += i;
    					 returnString[1] += column;
    				 }
    			 }
    			 int blockStartLine,blockStartColumn;//���Ź���
    			 if ((line + 1) % 3 == 0) blockStartLine = line - 2;
    			 else blockStartLine = ((line + 1) / 3 + 1) * 3 - 2 - 1;
    			 if ((column + 1) % 3 == 0) blockStartColumn = column - 2;
    			 else blockStartColumn = ((column + 1) / 3 + 1) * 3 - 2 - 1;
    			 for(int i = blockStartLine;i < blockStartLine + 3;i++){
    				 for(int j = blockStartColumn;j < blockStartColumn + 3;j++){
    					 if((i != line) || (j != column)){
    						 if(matrix[i][j] == matrix[line][column]){
    							 returnString[0] += i;
    							 returnString[1] += j;
    						 }
    					 }
    				 }
    			 }
    			 if((jbt[line][column].getBackground() !=Color.PINK)){
    				 //�趨��ɫ
    				 if(returnString[0]=="")jbt[line][column].setBackground(Color.GREEN);
    				 //��ԭ������ɫ�Ǵ�����Ŀ�ķ�ɫ�Ͳ��ı�
    				 else jbt[line][column].setBackground(Color.YELLOW);
    			 }}}
    	 for(int i = 0;i < 9;i++){
    		 for(int j = 0;j < 9;j++){
    			 if((matrix[i][j] ==0))
    				 jbt[i][j].setBackground(null);
    		 }
    	}
    }
     void checkGap(JTextField[][] jbt,int[][] matrix){
    	 //���յĸ����Ƿ��޷������κ����֣����ǣ��򽫸ð�ť����ɫ��//��Ϊ��ɫ
		boolean gameOverFlag = false;
		boolean firstFlag = true;
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				int gameOverCounter = 0;
				if (matrix[i][j] == 0){
					for(int k = 1;k < 10;k++){//�ڿո�������1~9��������Ѱ���Ƿ�ȫ�����г�ͻ
                        for(int i1 = 0;i1 < 9;i1++){
                        	if (matrix[i][i1] == k){
                        		gameOverCounter++;
                        		firstFlag = false;
                        		break;
                        	}
                        }
           for(int i1 = 0;i1 < 9;i1++){
                   if ((matrix[i1][j] == k) &&((firstFlag))){
                       gameOverCounter++;
                       firstFlag = false;
                        break;
                      }}
  		              int blockStartLine,blockStartColumn;
		     if ((i + 1) % 3 == 0) blockStartLine = i - 2;
		    else blockStartLine = ((i + 1) / 3 + 1) * 3 - 2 - 1;
		    if ((j + 1) % 3 == 0) blockStartColumn = j - 2;else blockStartColumn = ((j + 1) / 3 + 1) * 3 - 2 - 1;
outer3:for(int i1 = blockStartLine;i1 < blockStartLine + 3;i1++){
	   		for(int j1 = blockStartColumn;j1 < blockStartColumn + 3;j1++){
		      if ((matrix[i1][j1] == k) && (firstFlag)){
		                 gameOverCounter++;
		                    break outer3;
		           }
		      }
	   		}                               		       
		    if (gameOverCounter == 9){
		    	jbt[i][j].setBackground(Color.RED);
			    gameOverCounter = 0;
			    gameOverFlag = true;
		    }
		    firstFlag = true;    
	}}}}}
     void checkCustom(JTextField[][] jbt,int[][] matrix){
//����Զ�����Ŀ�Ƿ��г�ͻ�Լ��Ƿ���Ψһ��
		Boolean flag = true;
		JButton[][] dummy = new JButton[9][9];
//����������Ϊ�˷�ֹ����ʽ�����ݲ���Ӱ������õ���������
		JButton[][] dummy1 = new JButton[9][9];
		JButton[][] dummy2 = new JButton[9][9];
		int[][] key = new int[9][9];
		int[][] key1 = new int[9][9];
		int[][] key2 = new int[9][9];
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				dummy[i][j] = new JButton();
				dummy1[i][j] = new JButton();
				dummy2[i][j] = new JButton();
				key1[i][j] = key2[i][j] = key[i][j] = matrix[i][j];
		}}
		check(dummy,key);//�Ƿ����ִ��ڳ�ͻ
		checkGap(dummy,key);
//�Ƿ�ո��Ӵ��ڲ��������κ����ֵ����
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				if((dummy[i][j].getBackground() == Color.RED) || (dummy[i][j].getBackground() == Color.YELLOW)){
//�������˻�ɫ���Ǻ�ɫ�������ͻ
					flag = false;
		}}}
		if(flag){
			Boolean b1 = s.solve(key1,dummy1);//����ѭ������ѭ������ͳһ��Ŀ�Ľ⣬���������ͬ���������Ψһ��
			Boolean b2 = s.solveC(key2,dummy2);
			String s1 = "";
			String s2 = "";
			for(int i = 0;i < 9;i++){
				for(int j = 0;j < 9;j++){
					s1 += key1[i][j];
					s2 += key2[i][j];
					if(key1[i][j] != key2[i][j])flag = false;
			}}
			if(!flag)JOptionPane.showMessageDialog(null,"���Զ�����Ŀ���ڶ���⣬��������±��棡\n");
			else {//��û������ͱ���
				Saver saver = new Saver("puzzle");
				saver.save(jbt,matrix,0);
			JOptionPane.showMessageDialog(null,"������ϣ�");
		}}
		else JOptionPane.showMessageDialog(null,"���Զ�����Ŀ���ڳ�ͻ����������±��棡");
	}
     boolean checkRandom(int[][] matrix){
//��������������Ŀ�Ƿ���Ψһ�⣬������checkCustom��������//�ƣ��Զ�������Ŀ���㷨ȷ���˲����������ì�ܣ�
		JButton[][] dummy1 = new JButton[9][9];
		JButton[][] dummy2 = new JButton[9][9];
		int[][] key1 = new int[9][9];
		int[][] key2 = new int[9][9];
		Boolean flag = true;
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				dummy1[i][j] = new JButton();
				dummy2[i][j] = new JButton();
				key1[i][j] = key2[i][j] = matrix[i][j];
	}}
		Boolean b1 = s.solve(key1,dummy1);
		Boolean b2 = s.solveC(key2,dummy2);
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				if(key1[i][j] != key2[i][j])flag = false;
	}}
		return flag;
}
 	Boolean checkWin(JButton[][] jbt,int[][] matrix,int playTime,Boolean checkFlag){//����Ƿ�Ӯ��һ����Ϸ
		Boolean winFlag = true;
		File recordFile = new File("rec\\sud.rec");
		
		if(checkFlag){
//ȷ���������Զ�����Ŀ������½���ʤ���ж�
		    try{
			    if (recordFile.exists()){
				    DataInputStream input = new DataInputStream(new FileInputStream(recordFile));
				//�����и��Ӷ���������û�г�ͻ����Ϸ���
			    for(int j = 0;j < 9;j++){
				    if((matrix[i][j] == 0) || (jbt[i][j].getBackground().equals(Color.YELLOW)))winFlag = false;
			}
		    if(winFlag){
			    JOptionPane.showMessageDialog(null,"��ϲ��Ӯ�������Ϸ��\n������ʱ�� " + (playTime / 60) + " ���� " + (playTime % 60) +  " ��");
							
				   
		
checkFlag = false; //ȷ����Ϸʤ��֮�󲻻��ٴ��ж�ʤ��
else
	JOptionPane.showMessageDialog(null,"�Բ�����ʧ���ˣ�");
		    	
		}}
		return checkFlag;
}}	

