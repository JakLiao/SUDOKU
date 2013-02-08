package main1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class Creator{
	void copy(int[][] array1,int[][] array2){
    //��array1�����ݸ��Ƹ�array2
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				array2[i][j] = array1[i][j];
			}
		}
	}
	public void creator(int[][] matrix,JTextField[][] txtGame,int difficulty){//�����ѶȲ����漴��Ŀ��
		Solver s = new Solver();
		Checker c = new Checker();
		for(int i = 1;i <= 9;i++){
    //�����λ������1~9��һ��
			int random = (int)(Math.random() * 81);
			matrix[random / 9][random % 9] = i;
		}
		JTextField[][] dummy = new JTextField[9][9];
//����İ�ť���飬��ֹ����������й����ж������İ�ť����Ӱ��
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				dummy[i][j] = new JTextField();
			}
		}
		Boolean tempFlag = s.solve(matrix,dummy);
    //�����ǰ���˾Ÿ����ֵ������Ĵ�
		int counter = 0;//�������˵ĸ���
		for(int i = 1;i <= difficulty;i++){
//�ڴ����ڵ��Ѷ�Ҫ��ĸ��ӣ�����ڵ�֮��𰸲�Ψһ����//������ԭ������������������ӡ�
			int random = (int)(Math.random() * 81);
			if(matrix[random / 9][random % 9] == 0)
				i--;
			else{
				int tempNum = matrix[random / 9][random % 9];
				matrix[random / 9][random % 9] = 0;
				if(!c.checkRandom(matrix)){
            //�����Ƿ���Ψһ��
					matrix[random / 9][random % 9] = tempNum;
				}
			}
		}
		for(int i = 0;i < 9;i++){//�趨��ť�����ֺ���ɫ
			for(int j = 0;j < 9;j++){
				if(matrix[i][j] != 0){
					txtGame[i][j].setText("" + matrix[i][j]);
					txtGame[i][j].setEditable(false);
					txtGame[i][j].setBackground(null);
									}
				else {
					txtGame[i][j].setText("");
					txtGame[i][j].setEditable(false);
				}
			}
		}
	}
}