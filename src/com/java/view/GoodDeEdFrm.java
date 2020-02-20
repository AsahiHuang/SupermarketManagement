package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.dao.GoodDao;
import com.java.dao.GoodTypeDao;
import com.java.model.Good;
import com.java.model.GoodType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

//Author AsahiHuang
//2019-11-24

public class GoodDeEdFrm extends JFrame {

	private JPanel contentPane;
	private JTable goodTable;
	private JTextField goodNameTxt;
	private DbUtil dbUtil = new DbUtil();
	private GoodDao goodDao = new GoodDao();
	private GoodTypeDao goodTypeDao = new GoodTypeDao(); 
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBoxCate;

	private  int nGoods = 0;	//记录可操作项数目


	private JTextField newGoodNameTxt;
	private JTextField newGoodPriceTxt;
	private JTextField newCountTxt;
	//单选框组
	private JRadioButton sJrb;
	private JRadioButton ssJrb;
	private JRadioButton sssJrb;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodDeEdFrm frame = new GoodDeEdFrm();
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
	public GoodDeEdFrm() {
		setResizable(false);
		setTitle("商品删除与修改");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		
		JLabel label = new JLabel("商品管理（删除与修改）");
		label.setIcon(new ImageIcon(GoodDeEdFrm.class.getResource("/images/ico_manageGoodType_50_50.png")));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		goodTable = new JTable();
		goodTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u5546\u54C1\u7C7B\u578B", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C", "\u5546\u54C1\u6570\u91CF", "\u5546\u54C1\u72B6\u6001"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(goodTable);
		
		JLabel label_1 = new JLabel("*商品名称：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		goodNameTxt = new JTextField();
		goodNameTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		goodNameTxt.setColumns(10);
		
		JButton btFind = new JButton("查询");
		btFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodFindActionEvent(e);
			}
		});
		
		JButton btDelete = new JButton("删除");
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodDeleteActionEvent(e);
			}
		});	
		JLabel lblNewLabel = new JLabel("*删除与修改前请先查询到要删除的商品并确认编号再进行操作");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JLabel label_2 = new JLabel("选择操作商品的编号：");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_3 = new JLabel("新的商品名称：");
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_4 = new JLabel("新的商品类型：");
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_5 = new JLabel("新的商品价格：");
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_6 = new JLabel("新的商品状态：");
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btUpdate = new JButton("修改");
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodUpdateActionEvent(e);
			}
		});
		
		newGoodNameTxt = new JTextField();
		newGoodNameTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		newGoodNameTxt.setColumns(10);
		
		newGoodPriceTxt = new JTextField();
		newGoodPriceTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		newGoodPriceTxt.setColumns(10);
		
		comboBoxCate = new JComboBox<>();
		comboBoxCate.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel label_7 = new JLabel("*商品信息修改，确认商品及编号，留空输入则不修改");
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		sJrb = new JRadioButton("\u8D27\u67B6\u51FA\u552E\u4E2D");
		sJrb.setSelected(true);
		buttonGroup.add(sJrb);
		
		ssJrb = new JRadioButton("\u8D27\u67B6\u5DF2\u51FA\u552E");
		buttonGroup.add(ssJrb);
		
		sssJrb = new JRadioButton("\u4ED3\u5E93\u4E2D\u5546\u54C1");
		buttonGroup.add(sssJrb);
		
		JLabel label_8 = new JLabel("新的商品数量：");
		label_8.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		newCountTxt = new JTextField();
		newCountTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		newCountTxt.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
											.addGap(30)
											.addComponent(goodNameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(99)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btFind, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btDelete, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
								.addComponent(label_7, 0, 0, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btUpdate, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
											.addGap(96))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(ssJrb, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
												.addComponent(newGoodNameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addComponent(sJrb, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
												.addComponent(sssJrb, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(18)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(newCountTxt, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
														.addComponent(newGoodPriceTxt, 0, 0, Short.MAX_VALUE)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(comboBoxCate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(goodNameTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btFind))
					.addGap(3)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(btDelete)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label_7)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(newGoodNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxCate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(sJrb))
							.addGap(5)
							.addComponent(ssJrb)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sssJrb))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(newGoodPriceTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(newCountTxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addComponent(btUpdate))
		);
		contentPane.setLayout(gl_contentPane);
	}
	//商品查询事件
	//查询结果返回所有符合的操作对象并将id传入下拉列表 
	private void goodFindActionEvent(ActionEvent evt) {
		this.fillTable(new Good(this.goodNameTxt.getText().toString(),"",""),1);
		this.fillCB(comboBoxCate);
	}
	//商品删除事件-依据查询结果的下拉列表选择的商品编号
	private void goodDeleteActionEvent(ActionEvent evt) {
		deleteGood(Integer.parseInt((String)comboBox.getSelectedItem()));
	}
	//商品更新事件-依据查询结果的下拉列表选择的商品编号
	private void goodUpdateActionEvent(ActionEvent evt) {
		updateGood(Integer.parseInt((String)comboBox.getSelectedItem()));
	}
	
	
	// 填充表单
	//model 模式1:商品名查找填充，模式2:ID查找填充（适用于修改）
	private void fillTable(Good good,int model) {
		if (model == 1 && StringUtil.isEmpty(good.getName())) {
			JOptionPane.showMessageDialog(null, "要查询的商品名不能为空!");
		}
		else {
			int id;
			DefaultTableModel dtm=(DefaultTableModel) goodTable.getModel();
			dtm.setRowCount(0); // 设置成0行
			comboBox.removeAllItems(); //清空编号列表
			Connection con=null;
			try{
				con=dbUtil.getCon();
				ResultSet rs;
				if (model ==1) {
					rs=goodDao.list(con, good);
				}
				else
					rs=goodDao.listByID(con, good.getId());
				while(rs.next()){
					Vector<String> v=new Vector<>();
					id = rs.getInt("id");
					v.add(id+"");
					v.add(rs.getString("category"));
					v.add(rs.getString("name"));
					v.add(rs.getString("price"));
					v.add(rs.getString("count"));
					v.add(rs.getString("state"));
					comboBox.addItem(id+"");
					this.nGoods++;
					
					dtm.addRow(v);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//删除处理方法
	private void deleteGood(int id) {
		if (nGoods == 0) {
			JOptionPane.showMessageDialog(null, "先查找要删除的商品并选择其编号!", "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除编号id为 " + id + " 的商品？");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int delecteName = goodDao.delete(con, id);
				if (delecteName == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					nGoods--;
					//同时更新查询框和编号下拉列表
					this.fillTable(new Good(this.goodNameTxt.getText().toString(),"",""),1);
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");

			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//商品修改事件处理方法
	private void updateGood(int id) {
		//获取新值
		String newName = ""; 
		String newPrice = ""; 
		String newCount = "";
		String newState="";
		if(sJrb.isSelected()) {
			newState="货架，出售中";
		}else if (ssJrb.isSelected()) {
			newState="已出售";
		}else if (sssJrb.isSelected()) {
			newState="仓库中";
		}
		String newCate = (String)comboBoxCate.getSelectedItem();
		if (nGoods == 0) {
			JOptionPane.showMessageDialog(null, "先查找要修改的商品并选择其编号!", "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = goodDao.listByID(con, id);
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null,"商品不存在","",JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				String oldName = rs.getString("name");
				String oldPrice = rs.getString("price");
				String oldCount = rs.getString("count");
				newName = newGoodNameTxt.getText().toString();
				newPrice = newGoodPriceTxt.getText().toString();
				newCount = newCountTxt.getText().toString();
				if (StringUtil.isEmpty(newName)) {
					newName += oldName;
				}
				if (StringUtil.isEmpty(newPrice)) {
					newPrice += oldPrice;
				}
				if (StringUtil.isEmpty(newCount)) {
					newCount += oldCount;
				}
				Good newGood = new Good(id,newCate,newName,newPrice,newCount,newState);
				int modifyNum = goodDao.update(con, newGood);
				if (modifyNum == 1) {
					JOptionPane.showMessageDialog(null, "修改成功");
					this.fillTable(new Good(id),2);
					this.resetValue();
				}
				else 
					JOptionPane.showMessageDialog(null, "修改失败","",JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception e) {
			e.printStackTrace();
				JOptionPane.showMessageDialog(null, "修改失败","",JOptionPane.WARNING_MESSAGE);
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
	private void resetValue() {
		this.newGoodNameTxt.setText("");
		this.newGoodPriceTxt.setText("");
		this.newCountTxt.setText("");
	}
	
}
