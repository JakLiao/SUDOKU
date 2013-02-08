package main1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
//import java.util.*;
//import javax.swing.filechooser.FileFilter;

public class MyReader{
     private String lpath = "";
//读取存档的路径：如果是游戏存档即为save;题目存档即为puzzle
     MyReader(String lpath){//获取读取目录
		this.lpath = lpath;
     }
     int read(JTextField[][] txtGame,int[][] matrix,int[][] origin){//读取存档文件
		int saveTime = 0;
		JFileChooser file = new JFileChooser(".\\" + lpath);
    //将读取路径设定为需要路径并且开启FileChooser
		file.setAcceptAllFileFilterUsed(true);
   //允许读取所有路径下文件
		int result = file.showOpenDialog(null);//获取用户动作
		if(result == JFileChooser.APPROVE_OPTION){
   //若用户选择了一个文件就进行读取
			String path = file.getSelectedFile().getAbsolutePath();//获取文件的路径
			File readFile = new File(path);
			try{
				DataInputStream input = new DataInputStream(new FileInputStream(readFile));
				for(int i = 0;i < 9;i++){
 //按照储存的格式依次读取数组各个位置的值、按钮的颜色
					for(int j = 0;j < 9;j++){
						matrix[i][j] = input.readInt();
						int color = input.readInt();
						int temp = input.readInt();
            //为了方便调试在储存时用99做了分隔
						if(matrix[i][j] != 0){//设定按钮的显示数字
							txtGame[i][j].setText("" + matrix[i][j]);
							txtGame[i][j].setBackground(null);							
							txtGame[i][j].setEditable(false);
							if(lpath.equals("puzzle")){
 //若读取的是题目则将不为0的按钮颜色设定为表示题目的粉色
								txtGame[i][j].setBackground(Color.PINK);
								Creator c = new Creator();
								c.copy(matrix,origin);
							}
							else if(lpath.equals("save")){
     //若读取的是游戏存档则按照储存的颜色进行设定
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
// 为确保颜色显示没有错误，重新检查空的格子是否不能填入数字
		if(lpath.equals("save")){
 //若是读取游戏存档还要读取保存时的游戏时间
			saveTime = input.readInt();
		}
		input.close();
			}catch(IOException e){
			}}
		return saveTime;//返回读取的保存时间用来设定计时器。
	}}
