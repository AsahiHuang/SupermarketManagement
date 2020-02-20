package com.java.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.java.dao.GoodTypeDao;
import com.java.model.GoodType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import com.java.view.GoodAddInterFrm;

public class GoodTypeAddInterFrm extends JFrame {

	private JPanel contentPane;
	private JTextField goodTypeNameTxt;
	private JTextArea goodTypeDescTxt;
	private DbUtil dbUtil=new DbUtil();
	private GoodTypeDao goodTypeDao=new GoodTypeDao();
	private boolean successAdd = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodTypeAddInterFrm frame = new GoodTypeAddInterFrm(new GoodAddInterFrm());
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
	public GoodTypeAddInterFrm(GoodAddInterFrm gai) {
		setResizable(false);
		setTitle("\u5546\u54C1\u7C7B\u522B\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u7C7B\u522B\u540D\u79F0\uFF1A");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		goodTypeNameTxt = new JTextField();
		goodTypeNameTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		goodTypeNameTxt.setColumns(10);
		
		 goodTypeDescTxt = new JTextArea();
		 goodTypeDescTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodTypeAddActionPerformed(e);
				gai.fillCB(gai.getCB());
			}
		});
		
		JButton button_1 = new JButton("重置输入");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBackground(new Color(255, 255, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		JLabel label_2 = new JLabel("商品类别添加");
		label_2.setIcon(new ImageIcon(GoodTypeAddInterFrm.class.getResource("/images/ico_addGood_50_50.png")));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(goodTypeNameTxt, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(goodTypeDescTxt, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(button_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
							.addGap(6)))
					.addGap(93))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_2)
					.addContainerGap(394, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_2)
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(goodTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(goodTypeDescTxt, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_1)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		goodTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,true));
		
	}
	//商品类别添加事件
	private void goodTypeAddActionPerformed(ActionEvent evt) {
		String goodTypeName=this.goodTypeNameTxt.getText();
		String goodTypeDesc=this.goodTypeDescTxt.getText();
		if(StringUtil.isEmpty(goodTypeName)) {
			JOptionPane.showMessageDialog(null, "商品类别不能为空");
			return;
		}
		
		GoodType goodType=new GoodType(goodTypeName,goodTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=goodTypeDao.add(con, goodType);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				this.successAdd = true;
				resetValue();
				
			}else {
				JOptionPane.showMessageDialog(null, "添加" + goodType.getGoodTypeName() + "失败");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败");
		}finally {
			try {
			dbUtil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//重置事件处理
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}
	public boolean getSuccessAdd() {
		return this.successAdd;
	}

	//重置表单
	private void resetValue() {
		this.goodTypeNameTxt.setText("");
		this.goodTypeDescTxt.setText("");
	}
	
}
