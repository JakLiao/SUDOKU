package main1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TimeKeeper extends JPanel{
	private int second = 0;//��ǰ��ʱ
    private boolean start = false;//�Ƿ�ʼ��ʱ
    Timer timer = new Timer(1000, new TimerListener());
         //��ʱ����ÿ��1�뻭��ʱ��
    public TimeKeeper(){//�趨ʱ����ʾ������
      	setFont(new Font("Courier", Font.BOLD, 18));
	}
    public void paintComponent(Graphics g){//������ǰ��ʱ��
		super.paintComponent(g);
		if(start){
        second++;}
        String output;
        String hour=new String("");
        String minute=new String("");
        String second1=new String("");
        if(start == true){
        	if((second/3600)<10)
        	     hour="0";
        	if((second%3600/60)<10)
            	 minute="0";
        	if((second%60)<10)
            	second1="0";
        	output =hour+ (second / 3600) + ":" +minute+ (second % 3600 / 60) + ":" +second1+ (second % 60);
//��Сʱ�����ӡ�����Ϊ��ʽ��ʾʱ��
        }
        else output = "00:00:00";
        g.drawString(output,0,200);
	}
    void start(){//��ʱ��ʼ
    	start = true;
		if(start){
			timer.stop();
		    timer.start();
		}
	}
    void pause(){//��ʱ��ͣ
		if(start){
			timer.stop();
			}
		}
    void stop(){//��ʱ����
		if(start){
			timer.stop();
			second = 0;
			start = false;
			repaint();
		}
	}
    int getSecond(){//��õ�ǰʱ��
		return second;
	}
    public void setSecond(int second){//�趨��ǰʱ�䣨����ʱʹ�ã�
		this.second = second;
	}
	class TimerListener implements ActionListener {
    //ÿ��һ���ӽ����ػ�
		public void actionPerformed(ActionEvent e){
			repaint();
		}}	}
