package main1;

import javax.swing.*;
import java.awt.*;
class Solver{
	public Solver(){
	}
static boolean solve(int[][]matrix,JTextField[][]txtGame){
           //����������ó�������ϵ���һ��⣩
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(matrix[i][j] == 0){
            outer:for(int value = 1; value <= 9; value++){
                      for(int k = 0; k < 9; k++){
         //���������������ͬ���֣�������������
        if((matrix[k][j] == value)||(matrix[i][k] == value)){
                      continue outer;
                }}
           int blockStartLine = (i / 3) * 3;
     //������ھŹ�������ͬ���֣�������������
            int blockStartColumn = (j / 3) * 3;
   for (int x = blockStartLine; x < blockStartLine + 3; x++){
  for (int y = blockStartColumn; y < blockStartColumn + 3; y++){
                 if (matrix[x][y] == value){
                             continue outer;
                     }}}
       matrix[i][j] = value;//����������趨ΪĿǰ��ì�ܵ�ֵ
              if(solve(matrix,txtGame)){
    //�ݹ顣�ж���������Ƿ��ܹ����뵱ǰֵ��������ɺ���ì��
            txtGame[i][j].setBackground(Color.CYAN);
                txtGame[i][j].setText("" + value);
                        return true;
                        }
    else{//������ì��������������
               matrix[i][j]=0;
             txtGame[i][j].setBackground(null);
              txtGame[i][j].setText("");
                  }}
                    return false;
        }}}
        return true;
	}
static boolean solveC(int[][]matrix,JTextField[][]textGame){
	//�������Ľ�����������������������ж���Ŀ�Ƿ���Ψһ��ʱ//  ���á�����Ψһ�⣬�������߽���Ľ����һ�µġ�
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
