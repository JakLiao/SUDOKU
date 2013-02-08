package main1;

import javax.swing.*;

import java.awt.*;
import java.io.*;
//import java.util.*;

public class Saver{
	private String path = "";
 //存档的路径：如果是游戏存档即为save;题目存档即为puzzle
	 Saver(String path){//设定路径
		this.path = path;
	}
	 void save(JTextField[][] jbt,int[][] matrix,int saveTime){
       //保存存档
		Boolean nameCheck = true;
		String name = JOptionPane.showInputDialog(null,"请输入你想保存的文件名，注意：请使用英语字母以及数字！");
		for(int i = 0;i < name.length();i++){
			if(!Character.isLetterOrDigit(name.charAt(i))) nameCheck = false;
			//检查文件名是否仅限字母和数字以免出现问题
		}
		if(nameCheck){
		    File saveFile = new File(path);
		    saveFile.mkdirs();
		    try{
		    	File file = new File(path + "\\" + name + ".sav");
		    	if(file.exists()){//若出现同名文件则询问是否覆盖原文件
		    		int choose = JOptionPane.showConfirmDialog(null,"是否要覆盖先前同名存档文件？");
		    		if(choose == 0){
		    			file.delete();
		    			DataOutputStream output = new  DataOutputStream(new FileOutputStream(path + "\\" + name + ".sav"));
		    			dataWrite(jbt,output,matrix,saveTime);
		    		}
		    	}
		        else{
		        	DataOutputStream output = new DataOutputStream(new FileOutputStream(path + "\\" + name + ".sav"));
		        	dataWrite(jbt,output,matrix,saveTime);  
		        }
		    }catch(IOException ex){
		    }
		}
		else JOptionPane.showMessageDialog(null,"文件名不合法！请确保文件名是由英语字母和数字组成！请重新保存！");
	}
	void dataWrite(JTextField txtGame[][],DataOutputStream output,int[][] matrix,int saveTime) throws IOException{//写入数据
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				output.writeInt(matrix[i][j]);//写入数组值
				if(txtGame[i][j].getBackground().equals(Color.RED))
					output.writeInt(1);//写入按钮颜色
				else if(txtGame[i][j].getBackground().equals(Color.GREEN))
					output.writeInt(2);
				else if(txtGame[i][j].getBackground().equals(Color.YELLOW))
					output.writeInt(3);
				else if(txtGame[i][j].getBackground().equals(Color.PINK))
					output.writeInt(4);
				else output.writeInt(0);
					output.writeInt(99);//为调试方便写入99作为分割
			}
		}
		if(path.equals("save")){
			output.writeInt(saveTime);//若是游戏存档写入游戏保存时间
		}
		output.close();
	}
}
