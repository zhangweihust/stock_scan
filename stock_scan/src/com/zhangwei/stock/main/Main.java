package com.zhangwei.stock.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.zhangwei.stock.net.SouHuStockHelper;
import com.zhangwei.stock.windows.TipWindowHelper;

public class Main {

	public static void main(String[] args) {
		/*
		 * JFrame frame = new JFrame("forbiddenChinese"); JLabel label = new
		 * JLabel(new ImageIcon(
		 * "D:/android/workspace/VideoEditActivity/res/drawable-xhdpi/bq_ye.png"
		 * )); label.setPreferredSize(new Dimension(3000, 10));
		 * frame.add(label,BorderLayout.CENTER); frame.pack();
		 * frame.setVisible(true);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.setAlwaysOnTop(true);
		 */
/*		DragWindow test = new DragWindow();
		test.setPreferredSize(new Dimension(3000, 30));
		// test.pack();window 没放东西这个先不要
		test.setVisible(true);
		test.setAlwaysOnTop(true);*/
		//JOptionPane.showMessageDialog(null,"sample dialog !\nddd\n中文、n\n", "title", JOptionPane.DEFAULT_OPTION);
	
	
/*		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://www.sina.com");
		urls.add("http://www.baidu.com");
		
		TipWindowHelper.getInstance().show("str_title", "str_action", "str_content_title", "str_content", urls);
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    TipWindowHelper.getInstance().show("str_title2", "str_action2", "str_content_title2", "str_content2", urls);*/
		SouHuStockHelper.getInstance().get_ranklist_from_souhu();
	
	}

}
