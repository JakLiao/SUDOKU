package main1;

import javax.swing.*;

import java.awt.*;
import java.io.*;
//import java.util.*;

public class Saver{
	private String path = "";
 //�浵��·�����������Ϸ�浵��Ϊsave;��Ŀ�浵��Ϊpuzzle
	 Saver(String path){//�趨·��
		this.path = path;
	}
	 void save(JTextField[][] jbt,int[][] matrix,int saveTime){
       //����浵
		Boolean nameCheck = true;
		String name = JOptionPane.showInputDialog(null,"���������뱣����ļ�����ע�⣺��ʹ��Ӣ����ĸ�Լ����֣�");
		for(int i = 0;i < name.length();i++){
			if(!Character.isLetterOrDigit(name.charAt(i))) nameCheck = false;
			//����ļ����Ƿ������ĸ�����������������
		}
		if(nameCheck){
		    File saveFile = new File(path);
		    saveFile.mkdirs();
		    try{
		    	File file = new File(path + "\\" + name + ".sav");
		    	if(file.exists()){//������ͬ���ļ���ѯ���Ƿ񸲸�ԭ�ļ�
		    		int choose = JOptionPane.showConfirmDialog(null,"�Ƿ�Ҫ������ǰͬ���浵�ļ���");
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
		else JOptionPane.showMessageDialog(null,"�ļ������Ϸ�����ȷ���ļ�������Ӣ����ĸ��������ɣ������±��棡");
	}
	void dataWrite(JTextField txtGame[][],DataOutputStream output,int[][] matrix,int saveTime) throws IOException{//д������
		for(int i = 0;i < 9;i++){
			for(int j = 0;j < 9;j++){
				output.writeInt(matrix[i][j]);//д������ֵ
				if(txtGame[i][j].getBackground().equals(Color.RED))
					output.writeInt(1);//д�밴ť��ɫ
				else if(txtGame[i][j].getBackground().equals(Color.GREEN))
					output.writeInt(2);
				else if(txtGame[i][j].getBackground().equals(Color.YELLOW))
					output.writeInt(3);
				else if(txtGame[i][j].getBackground().equals(Color.PINK))
					output.writeInt(4);
				else output.writeInt(0);
					output.writeInt(99);//Ϊ���Է���д��99��Ϊ�ָ�
			}
		}
		if(path.equals("save")){
			output.writeInt(saveTime);//������Ϸ�浵д����Ϸ����ʱ��
		}
		output.close();
	}
}
