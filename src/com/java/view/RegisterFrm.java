package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java.dao.GoodTypeDao;
import com.java.dao.UserRegisterDao;
import com.java.model.GoodType;
import com.java.model.UserRegister;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class RegisterFrm extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JTextField passwordTxt;
	private DbUtil dbUtil=new DbUtil();
	private UserRegisterDao userRegister=new UserRegisterDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrm frame = new RegisterFrm();
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
	public RegisterFrm() {
		setResizable(false);
		setTitle("\u6CE8\u518C\u754C\u9762");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAddActionPerformed(e);
				
			}
		});
		
		JButton button_1 = new JButton("\u91CD\u7F6E\u9519\u8BEF");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordTxt))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addGap(14)
									.addComponent(usernameTxt, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(70)
							.addComponent(button)
							.addGap(55)
							.addComponent(button_1)))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(usernameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		this.setLocationRelativeTo(null);//������ʾ
	}
	
	
	//注册事件
	private void userAddActionPerformed(ActionEvent evt) {
		String username=this.usernameTxt.getText();
		String password=this.passwordTxt.getText();
		if(StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		
		UserRegister userRegister=new UserRegister(username,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=UserRegisterDao.add(con, userRegister);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "注册成功");
				resetValue();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "注册失败");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "注册失败");
		}finally {
			try {
			dbUtil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}

	//重置表单
	private void resetValue() {
		this.usernameTxt.setText("");
		this.passwordTxt.setText("");
	}
	
	
	
	
}
