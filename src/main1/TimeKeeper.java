package main1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TimeKeeper extends JPanel{
	private int second = 0;//当前用时
    private boolean start = false;//是否开始计时
    Timer timer = new Timer(1000, new TimerListener());
         //计时器，每隔1秒画出时间
    public TimeKeeper(){//设定时间显示的字体
      	setFont(new Font("Courier", Font.BOLD, 18));
	}
    public void paintComponent(Graphics g){//画出当前的时间
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
//以小时、分钟、秒钟为格式显示时间
        }
        else output = "00:00:00";
        g.drawString(output,0,200);
	}
    void start(){//计时开始
    	start = true;
		if(start){
			timer.stop();
		    timer.start();
		}
	}
    void pause(){//计时暂停
		if(start){
			timer.stop();
			}
		}
    void stop(){//计时结束
		if(start){
			timer.stop();
			second = 0;
			start = false;
			repaint();
		}
	}
    int getSecond(){//获得当前时间
		return second;
	}
    public void setSecond(int second){//设定当前时间（读档时使用）
		this.second = second;
	}
	class TimerListener implements ActionListener {
    //每隔一秒钟进行重画
		public void actionPerformed(ActionEvent e){
			repaint();
		}}	}
