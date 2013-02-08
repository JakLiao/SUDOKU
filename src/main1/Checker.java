package main1;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.util.*;
class Checker{
     int[][] color = new int[9][9];//按钮的颜色
	 Solver s = new Solver();
     public Checker(){
    	 
     }
     void check(JButton[][] jbt,int[][] matrix){
    	 //检查数字是否与行列或者是九宫格出现冲突，若没有就将所在按钮//颜色设定为绿色，不然就设定为黄色。
    	 String[] returnString = new String[2];
    	 for(int line = 0;line < 9;line++){
    		 for(int column = 0;column < 9;column++){
    			 returnString[0] = returnString[1] = "";
    			 for(int i = 0;i < 9;i++){//检查行列
    				 if ((i != column) && (matrix[line][column] == matrix[line][i])){
    					 returnString[1] += i;
    					 returnString[0] += line;
    				 }
    				 if ((i != line) && (matrix[line][column] == matrix[i][column])){
    					 returnString[0] += i;
    					 returnString[1] += column;
    				 }
    			 }
    			 int blockStartLine,blockStartColumn;//检查九宫格
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
    				 //设定颜色
    				 if(returnString[0]=="")jbt[line][column].setBackground(Color.GREEN);
    				 //若原本的颜色是代表题目的粉色就不改变
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
    	 //检查空的格子是否无法填入任何数字，若是，则将该按钮的颜色设//定为红色
		boolean gameOverFlag = false;
		boolean firstFlag = true;
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				int gameOverCounter = 0;
				if (matrix[i][j] == 0){
					for(int k = 1;k < 10;k++){//在空格子中以1~9进行试填寻找是否全部都有冲突
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
//检查自定义题目是否有冲突以及是否有唯一解
		Boolean flag = true;
		JButton[][] dummy = new JButton[9][9];
//以下六行是为了防止对正式的数据产生影响而设置的虚拟数据
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
		check(dummy,key);//是否数字存在冲突
		checkGap(dummy,key);
//是否空格子存在不能填入任何数字的情况
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				if((dummy[i][j].getBackground() == Color.RED) || (dummy[i][j].getBackground() == Color.YELLOW)){
//若出现了黄色或是红色则表明冲突
					flag = false;
		}}}
		if(flag){
			Boolean b1 = s.solve(key1,dummy1);//用正循环和逆循环来求统一题目的解，若结果不相同则表明并非唯一解
			Boolean b2 = s.solveC(key2,dummy2);
			String s1 = "";
			String s2 = "";
			for(int i = 0;i < 9;i++){
				for(int j = 0;j < 9;j++){
					s1 += key1[i][j];
					s2 += key2[i][j];
					if(key1[i][j] != key2[i][j])flag = false;
			}}
			if(!flag)JOptionPane.showMessageDialog(null,"该自定义题目存在多组解，请检查后重新保存！\n");
			else {//若没有问题就保存
				Saver saver = new Saver("puzzle");
				saver.save(jbt,matrix,0);
			JOptionPane.showMessageDialog(null,"保存完毕！");
		}}
		else JOptionPane.showMessageDialog(null,"该自定义题目存在冲突，请检查后重新保存！");
	}
     boolean checkRandom(int[][] matrix){
//检查随机产生的题目是否有唯一解，方法与checkCustom方法的类//似（自动生成题目的算法确保了不会出现数字矛盾）
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
 	Boolean checkWin(JButton[][] jbt,int[][] matrix,int playTime,Boolean checkFlag){//检查是否赢得一局游戏
		Boolean winFlag = true;
		File recordFile = new File("rec\\sud.rec");
		
		if(checkFlag){
//确保不会在自定义题目的情况下进行胜利判定
		    try{
			    if (recordFile.exists()){
				    DataInputStream input = new DataInputStream(new FileInputStream(recordFile));
				//若所有格子都填满而且没有冲突则游戏完成
			    for(int j = 0;j < 9;j++){
				    if((matrix[i][j] == 0) || (jbt[i][j].getBackground().equals(Color.YELLOW)))winFlag = false;
			}
		    if(winFlag){
			    JOptionPane.showMessageDialog(null,"恭喜您赢得这局游戏！\n您的用时是 " + (playTime / 60) + " 分钟 " + (playTime % 60) +  " 秒");
							
				   
		
checkFlag = false; //确保游戏胜利之后不会再次判定胜利
else
	JOptionPane.showMessageDialog(null,"对不起！你失败了！");
		    	
		}}
		return checkFlag;
}}	

