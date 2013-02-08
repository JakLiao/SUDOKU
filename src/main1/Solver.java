package main1;

import javax.swing.*;
import java.awt.*;
class Solver{
	public Solver(){
	}
static boolean solve(int[][]matrix,JTextField[][]txtGame){
           //解数独（会得出最初遇上的那一组解）
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(matrix[i][j] == 0){
            outer:for(int value = 1; value <= 9; value++){
                      for(int k = 0; k < 9; k++){
         //如果所在行列有相同数字，就跳过该数字
        if((matrix[k][j] == value)||(matrix[i][k] == value)){
                      continue outer;
                }}
           int blockStartLine = (i / 3) * 3;
     //如果所在九宫格有相同数字，就跳过该数字
            int blockStartColumn = (j / 3) * 3;
   for (int x = blockStartLine; x < blockStartLine + 3; x++){
  for (int y = blockStartColumn; y < blockStartColumn + 3; y++){
                 if (matrix[x][y] == value){
                             continue outer;
                     }}}
       matrix[i][j] = value;//将这个格子设定为目前不矛盾的值
              if(solve(matrix,txtGame)){
    //递归。判断这个格子是否能够填入当前值但不会造成后续矛盾
            txtGame[i][j].setBackground(Color.CYAN);
                txtGame[i][j].setText("" + value);
                        return true;
                        }
    else{//如果造成矛盾则清空这个格子
               matrix[i][j]=0;
             txtGame[i][j].setBackground(null);
              txtGame[i][j].setText("");
                  }}
                    return false;
        }}}
        return true;
	}
static boolean solveC(int[][]matrix,JTextField[][]textGame){
	//与上述的解数独方法反向解数独，在判断题目是否有唯一解时//  有用。若是唯一解，那样两者解出的结果是一致的。
     for(int i = 0; i < 9; i++){
          for(int j = 0; j < 9; j++){
             if(matrix[i][j] == 0){
              outer:for(int value = 9; value >= 1; value--){
                        for(int k = 0; k < 9; k++){
       if((matrix[k][j] == value)||(matrix[i][k] == value)){
                             continue outer;
                             }}
                int blockStartLine = (i / 3) * 3;
                int blockStartColumn = (j / 3) * 3;
     for (int x = blockStartLine; x < blockStartLine + 3; x++){
  for (int y = blockStartColumn; y < blockStartColumn + 3; y++){
                if (matrix[x][y] == value){
                            continue outer;
                  }}}
                   matrix[i][j] = value;
            if(solveC(matrix,textGame)){
                 textGame[i][j].setBackground(Color.CYAN);
                 textGame[i][j].setText("" + value);
                   return true;
                        }
             else{
                     matrix[i][j] = 0;
                     textGame[i][j].setBackground(null);
                      textGame[i][j].setText("");
                        }
                  }
                    return false;
                }
            }
        }
        return true;
    }
    
} 
