package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.java.model.*;
import com.java.util.*;
import com.java.dao.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table =null;
	public static int sumGoodType;
	public static int sumGood;
	private DbUtil dbUtil=new DbUtil();
	private GoodDao goodDao=new GoodDao();
	private GoodTypeDao goodTypeDao=new GoodTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm(new User());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrm(User nowUser) {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/ico_storge_50*50.png")));
		setResizable(false);
		setTitle("超市管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 680);
		this.setLocationRelativeTo(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u5546\u54C1\u4FE1\u606F");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("\u5546\u54C1\u7C7B\u522B\u7BA1\u7406");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5546\u54C1\u7C7B\u522B\u6DFB\u52A0");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodTypeAddInterFrm goodTypeAddInterFrm=new GoodTypeAddInterFrm(new GoodAddInterFrm());
				goodTypeAddInterFrm.setVisible(true);
				table.add(goodTypeAddInterFrm);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u5546\u54C1\u7C7B\u522B\u7EF4\u62A4");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoodTypeManageInterFrm goodTypeManageInterFrm=new GoodTypeManageInterFrm();
				goodTypeManageInterFrm.setVisible(true);
				table.add(goodTypeManageInterFrm);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu menu = new JMenu("\u5546\u54C1\u7BA1\u7406");
		mnNewMenu.add(menu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u5546\u54C1\u6DFB\u52A0");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoodAddInterFrm goodAddInterFrm=new GoodAddInterFrm();
				goodAddInterFrm.setVisible(true);
				table.add(goodAddInterFrm);
				
			}
		});
		menu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u5546\u54C1\u7EF4\u62A4");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GoodManageInterFrm goodManageInterFrm=new GoodManageInterFrm();
				goodManageInterFrm.setVisible(true);
				table.add(goodManageInterFrm);
				
			}
		});
		menu.add(mntmNewMenuItem_4);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA\u754C\u9762");
		menuItem.addActionListener(new ActionListener() {
			//退出事件处理
			public void actionPerformed(ActionEvent arg0) {
				int result=JOptionPane.showConfirmDialog(null, "是否退出界面");
				if(result==0)
				{
					dispose();
				}
				
			}
		});
		mnNewMenu.add(menuItem);
		
		JMenu mnNewMenu_3 = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u5207\u6362\u7528\u6237");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			
			//切换用户事件
			public void actionPerformed(ActionEvent arg0) {
				dispose();//关闭提示页面
				new LogOnFrm().setVisible(true);  //进入选择界面
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("关于");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("小组");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			//关于小组事件
			public void actionPerformed(ActionEvent arg0) {
				GroupnumberFrm groupnumberFrm=new GroupnumberFrm();
				groupnumberFrm.setVisible(true);
				table.add(groupnumberFrm);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GregorianCalendar ca = new GregorianCalendar();
		String nowTime = (ca.AM_PM ==0 )?"上午好，":"下午好，";
		JLabel lblNewLabel = new JLabel(nowTime + nowUser.getUserName());
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 30));
		String ico_user = new String();
		ico_user = (nowUser.getIdentify())?"/images/ico_manager_80*80.png" : "/images/ico_staff_80*80.png";
		lblNewLabel.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_manager_80_80.png")));
		
		JLabel label = new JLabel(" 商品类别");
		label.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_category_50580.png")));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton button_1 = new JButton("商品类别添加");
		button_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_add_20_20.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodTypeAddInterFrm(new GoodAddInterFrm()).setVisible(true);
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton button_2 = new JButton("商品类别删除");
		button_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_delete_20_20.png")));
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodTypeManageInterFrm().setVisible(true);
			}
		});
		JLabel label_1 = new JLabel(" 商品");
		label_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_good_50_50.png")));
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton button_3 = new JButton("商品查询");
		button_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_seletc_20_20.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodManageInterFrm().setVisible(true);
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton button_4 = new JButton("商品添加");
		button_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_add_20_20.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodAddInterFrm().setVisible(true);
			}
		});
		button_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton button_5 = new JButton("商品删除");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodDeEdFrm().setVisible(true);
			}
		});
		button_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_delete_20_20.png")));
		button_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("员工管理");
		label_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_manageuser_50_50.png")));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton button_6 = new JButton("个人信息");
		button_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_myselfs_20_20.png")));
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton button_7 = new JButton("员工管理");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserManagerFrm().setVisible(true);
			}
		});
		button_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_manageruser_20_20.png")));
		button_7.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		if (!nowUser.getIdentify()) {
			button_7.setEnabled(false);
		}
		
		JButton button_8 = new JButton("商品类别修改");
		button_8.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_update_20_20.png")));
		button_8.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodTypeManageInterFrm().setVisible(true);
			}
		});
		
		JButton button_9 = new JButton("商品信息修改");
		button_9.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_update_20_20.png")));
		button_9.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GoodDeEdFrm().setVisible(true);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("概览");
		lblNewLabel_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ico_sum_50_50.png")));
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		
		
		
		//输出商品类别总量
		this.sumGood = allSum(this.sumGood);
		JLabel sumGoodTypeTxt = new JLabel("商品类别总量：" + this.sumGoodType);
		sumGoodTypeTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel sumGoodTxt = new JLabel("商品总量：" + this.sumGood);
		sumGoodTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addComponent(label)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(317, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(sumGoodTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(sumGoodTypeTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(70)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(sumGoodTypeTxt))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(sumGoodTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(222))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
							.addGap(46))))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
	}
	
	
	//主页概览显示方法
	//待补充 19-11-24 AsahiHuang
	public int allSum(int nGood) {

		return 0;
	}
}
