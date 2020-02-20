package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.java.dao.GoodDao;
import com.java.dao.GoodTypeDao;
import com.java.model.Good;
import com.java.model.GoodType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.mysql.jdbc.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.ImageIcon;


public class GoodAddInterFrm extends JFrame {

	private JPanel contentPane;
	private JTextField goodNameTxt;
	private JTextField goodPriceTxt;
	private JTextField goodCountTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private DbUtil dbUtil=new DbUtil();
	private GoodTypeDao goodTypeDao=new GoodTypeDao();
	private GoodDao goodDao=new GoodDao();
	private JRadioButton sJrb;
	private JRadioButton ssJrb;
	private JRadioButton sssJrb;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodAddInterFrm frame = new GoodAddInterFrm();
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
	public GoodAddInterFrm() {
		setResizable(false);
		setTitle("\u5546\u54C1\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u7C7B\u522B\uFF1A\r\n");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		goodNameTxt = new JTextField();
		goodNameTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		goodNameTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("\u5546\u54C1\u4EF7\u683C\uFF1A");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		goodPriceTxt = new JTextField();
		goodPriceTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		goodPriceTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u6570\u91CF\uFF1A");
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		goodCountTxt = new JTextField();
		goodCountTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		goodCountTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("\u5546\u54C1\u72B6\u6001\uFF1A");
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		sJrb = new JRadioButton("\u8D27\u67B6\u51FA\u552E\u4E2D");
		sJrb.setSelected(true);
		buttonGroup.add(sJrb);
		
		ssJrb = new JRadioButton("\u8D27\u67B6\u5DF2\u51FA\u552E");
		buttonGroup.add(ssJrb);
		
		sssJrb = new JRadioButton("\u4ED3\u5E93\u4E2D\u5546\u54C1");
		buttonGroup.add(sssJrb);

		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerormed(e);
				
			}
		});
		
		comboBox = new JComboBox();
		fillCB(comboBox);
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		JLabel lblNewLabel = new JLabel("商品添加");
		lblNewLabel.setIcon(new ImageIcon(GoodAddInterFrm.class.getResource("/images/ico_addGood_50_50.png")));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton button = new JButton("添加类别");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 addGoodType(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(362, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_3)
							.addGap(18)
							.addComponent(goodCountTxt, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2)
							.addGap(18)
							.addComponent(goodPriceTxt, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label_4)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(sssJrb)
										.addComponent(ssJrb)
										.addComponent(sJrb)))
								.addComponent(goodNameTxt, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
					.addGap(92))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(goodNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(goodPriceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(goodCountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(sJrb)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ssJrb)
						.addComponent(btnNewButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(sssJrb)
						.addComponent(btnNewButton_1))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	//重置事件
	private void resetValueActionPerormed(ActionEvent e) {
		this.resetValue();
		
	}

	//商品添加事件处理
	private void goodAddActionPerformed(ActionEvent evt) {
		String category=(String) comboBox.getSelectedItem();
		String name=this.goodNameTxt.getText();
		String price=this.goodPriceTxt.getText();
		String count=this.goodCountTxt.getText();

		if(StringUtil.isEmpty(name))
		{
			JOptionPane.showMessageDialog(null,"商品名不能为空","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(price))
		{
			JOptionPane.showMessageDialog(null,"商品价格不能为空","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(count))
		{
			JOptionPane.showMessageDialog(null,"商品数量不能为空","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		String state="";
		if(sJrb.isSelected()) {
			state="货架，出售中";
		}else if (ssJrb.isSelected()) {
			state="已出售";
		}else if (sssJrb.isSelected()) {
			state="仓库中";
		}
		
		Good good=new Good(category,name,price,count,state);
		Connection con=null;
		try {
			con=(Connection) dbUtil.getCon();
			int addNum=goodDao.add(con, good);
			if(addNum==1) {
				JOptionPane.showMessageDialog(null, "商品:" + name  + "添加成功");
				resetValue();
			}else {
				JOptionPane.showMessageDialog(null, "商品添加失败");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// 类别下拉列表填充
	public  void fillCB(JComboBox<String> cb ) {
		Connection con = null;
		cb.removeAllItems();
		try {
			con = (Connection)dbUtil.getCon();
			ResultSet rs = goodTypeDao.list(con, new GoodType());
			while (rs.next()) {
				cb.addItem(rs.getString("goodTypeName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void addGoodType(ActionEvent e) {
		new GoodTypeAddInterFrm(this).setVisible(true);;
	}
	public JComboBox getCB() {
		return this.comboBox;
	}


	//重制表单
	private void resetValue() {
		this.goodNameTxt.setText("");
		this.goodPriceTxt.setText("");
		this.goodCountTxt.setText("");
		this.sJrb.setSelected(true);
	}
}













