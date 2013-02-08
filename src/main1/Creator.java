package main1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class Creator{
	void copy(int[][] array1,int[][] array2){
    //将array1的内容复制给array2
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				array2[i][j] = array1[i][j];
			}
		}
	}
	public void creator(int[][] matrix,JTextField[][] txtGame,int difficulty){//根据难度产生随即题目。
		Solver s = new Solver();
		Checker c = new Checker();
		for(int i = 1;i <= 9;i++){
    //在随机位置填入1~9各一个
			int random = (int)(Math.random() * 81);
			matrix[random / 9][random % 9] = i;
		}
		JTextField[][] dummy = new JTextField[9][9];
//虚拟的按钮数组，防止在下面的运行过程中对真正的按钮产生影响
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				dummy[i][j] = new JTextField();
			}
		}
		Boolean tempFlag = s.solve(matrix,dummy);
    //解出当前填了九个数字的数独的答案
		int counter = 0;//数出空了的格子
		for(int i = 1;i <= difficulty;i++){
//在答案中挖掉难度要求的格子，如果挖掉之后答案不唯一，则//重新用原来的数字填上这个格子。
			int random = (int)(Math.random() * 81);
			if(matrix[random / 9][random % 9] == 0)
				i--;
			else{
				int tempNum = matrix[random / 9][random % 9];
				matrix[random / 9][random % 9] = 0;
				if(!c.checkRandom(matrix)){
            //测试是否有唯一解
					matrix[random / 9][random % 9] = tempNum;
				}
			}
		}
		for(int i = 0;i < 9;i++){//设定按钮的文字和颜色
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