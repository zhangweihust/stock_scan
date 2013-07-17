package com.zhangwei.stock.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Tom
 */
public class TipWindowHelper {
	private Map<String, String> feaMap = null;
	private Point oldP; // 上一次坐标,拖动窗口时用
	private TipWindow tw = null; // 提示框
	private ImageIcon img = null;// 图像组件
	private JLabel imgLabel = null; // 背景图片标签
	private JPanel headPan = null;
	private JPanel feaPan = null;
	private JPanel btnPan = null;
	private JLabel title = null;
	private JLabel head = null;
	private JLabel close = null;// 关闭按钮
	private JTextArea feature = null;
	private JScrollPane jfeaPan = null;
	private JLabel releaseLabel = null;
	private JLabel update = null;
	private SimpleDateFormat sdf = null;

	{
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		feaMap = new HashMap<String, String>();
/*		feaMap.put("name", "此次增加以下新特性(主内容标题,可省略)");*/
		feaMap.put("release", sdf.format(new Date()));
		/*feaMap.put("content",
				"这里是提示框主内容\n1.代码改进与完善,去掉了许多无用的代码\n2.自制菜单栏\n3.增加拖动功能\n4.自制背景图片\n");*/
	}
	
	private static TipWindowHelper ins = null;
	
	public static TipWindowHelper getInstance(){
		if(ins==null){
			ins = new TipWindowHelper();
		}
		
		return ins;
	}
	
	public void show(String str_title, String str_action, String str_content_title, String str_content, final ArrayList<String> urls){
		if(ins.tw!=null){
			ins.tw.close();
			ins.tw.removeAll();
		}

		// 新建300x250的消息提示框
		// Tom, 设置整个提示框大小
		tw = new TipWindow(300, 250);
		//handle(urls);
		init(str_title, str_action, str_content_title, str_content, urls);
		
		tw.run();
	}

	private TipWindowHelper() {

	}
	
    /**
     * @param str_title eg:Tom 气泡提示框程序 V1.0
     * @param str_action eg:打开网页
     * 
     * */
	private void init(String str_title, String str_action, String str_content_title, String str_content, final ArrayList<String> urls) {

		
		// 如果要提示框加上背景图,加上以下2句,把背景图放在项目根目录下
		// img = new ImageIcon("bg_u_all.gif");
		// imgLabel = new JLabel(img);
		// 设置各个面板的布局以及面板中控件的边界
		headPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		feaPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		btnPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));


		title = new JLabel(str_title); // 提示框标题
		title.setPreferredSize(new Dimension(260, 26));
		title.setVerticalTextPosition(JLabel.CENTER);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setFont(new Font("宋体", Font.PLAIN, 12));
		title.setForeground(Color.black);
		
		
		head = new JLabel(str_content_title);
		// Tom 内容上面
		// head.setPreferredSize(new Dimension(250, 35));
		head.setPreferredSize(new Dimension(250, 20));
		head.setVerticalTextPosition(JLabel.CENTER);
		head.setHorizontalTextPosition(JLabel.CENTER);
		head.setFont(new Font("宋体", Font.PLAIN, 12));
		head.setForeground(Color.blue);
		
		
		close = new JLabel(" x");// 关闭按钮
		close.setFont(new Font("Arial", Font.BOLD, 15));
		close.setPreferredSize(new Dimension(20, 20));
		close.setVerticalTextPosition(JLabel.CENTER);
		close.setHorizontalTextPosition(JLabel.CENTER);
		close.setCursor(new Cursor(12));
		close.setToolTipText("关闭");
		
		feature = new JTextArea(str_content);
		// Tom 主内容窗口的设置
		feature.setEditable(false);
		feature.setForeground(Color.red);
		// feature.setFont(new Font("宋体", Font.PLAIN, 13));
		feature.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		// feature.setBackground(new Color(184, 230, 172));
		feature.setBackground(Color.WHITE);
		// 设置文本域自动换行
		feature.setLineWrap(true);
		
		jfeaPan = new JScrollPane(feature);		
		// Tom 主内容位置设置
		// jfeaPan.setPreferredSize(new Dimension(250, 80));
		jfeaPan.setPreferredSize(new Dimension(250, 120));
		jfeaPan.setBorder(null);
		jfeaPan.setBackground(Color.black);
		
		
		releaseLabel = new JLabel("发布日期 " + feaMap.get("release"));
		releaseLabel.setForeground(Color.DARK_GRAY);
		releaseLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		
		update = new JLabel("升级");
		update.setPreferredSize(new Dimension(110, 30));
		// 设置标签鼠标手形
		update.setCursor(new Cursor(12));
		// Tom 更新按钮
		// update.setBackground(Color.BLACK);
		update.setText(str_action);


		// 为了隐藏文本域，帮加个空的JLabel将他挤到下面去
		// Tom, 主内容与主内容上方标题的距离
		JLabel jsp = new JLabel();
		jsp.setPreferredSize(new Dimension(300, 10));
		feaPan.setOpaque(false);
		feaPan.add(jsp);
		feaPan.add(jfeaPan);
		feaPan.add(releaseLabel);
		// btnPan.setOpaque(false); //Tom, 不要透明
		
		headPan.setOpaque(false);
		headPan.setPreferredSize(new Dimension(300, 60));
		headPan.add(title);
		headPan.add(close);
		headPan.add(head);

		btnPan.add(update);
		// Tom 最底下按钮层颜色
		btnPan.setBackground(Color.LIGHT_GRAY);
		// update.setBackground(Color.CYAN);

		
		// 将各个面板设置为透明，否则看不到背景图片
		((JPanel) tw.getContentPane()).setOpaque(false);
		
		// 设置JDialog的整个背景图片
		// tw.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		tw.setBackground(Color.WHITE); // Tom 整个提示框背景
		tw.setAlwaysOnTop(true);
		tw.setUndecorated(true);
		tw.setResizable(false);
		tw.setVisible(true);
		
		// 设置提示框的边框,宽度和颜色
		tw.getRootPane().setBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		tw.add(headPan, BorderLayout.NORTH);
		tw.add(feaPan, BorderLayout.CENTER);
		tw.add(btnPan, BorderLayout.SOUTH);
		
		
		// 为更新按钮增加相应的事件
		update.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showMessageDialog(tw, "谢谢，再见");
				try {   
					for(String url: urls){
						Runtime.getRuntime().exec("cmd.exe /c start " + url);
					}
                       
                } catch (Exception ex) {   
                    ex.printStackTrace();   
                }  
				//tw.close();
			}

			public void mouseEntered(MouseEvent e) {
				update.setBorder(BorderFactory.createLineBorder(Color.gray));
			}

			public void mouseExited(MouseEvent e) {
				update.setBorder(null);
			}
		});
		
		
		// 增加鼠标拖动事件
		title.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				Point newP = new Point(e.getXOnScreen(), e.getYOnScreen());
				int x = tw.getX() + (newP.x - oldP.x);
				int y = tw.getY() + (newP.y - oldP.y);
				tw.setLocation(x, y);
				oldP = newP;

			}
		});
		
		
		// 鼠标按下时初始坐标,供拖动时计算用
		title.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				oldP = new Point(e.getXOnScreen(), e.getYOnScreen());
			}
		});

		// 右上角关闭按钮事件
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tw.close();
			}

			public void mouseEntered(MouseEvent e) {
				close.setBorder(BorderFactory.createLineBorder(Color.gray));
			}

			public void mouseExited(MouseEvent e) {
				close.setBorder(null);
			}
		});

	}


/*	public static void main(String args[]) {
		new TipWindowHelper();
	}*/

}
