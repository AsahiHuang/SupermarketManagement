package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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

public class GoodManageInterFrm extends JFrame {

	private JPanel contentPane;
	private JTable goodTable;
	private JTextField s_goodNameTxt;
	private JComboBox comboBox;
	
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
					GoodManageInterFrm frame = new GoodManageInterFrm();
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
	public GoodManageInterFrm() {
		setTitle("商品查询");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 471);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JLabel lblNewLabel = new JLabel("商品查询");
		lblNewLabel.setIcon(new ImageIcon(GoodManageInterFrm.class.getResource("/images/ico_seletc_20_20.png")));
		
		
		//lblNewLabel.setIcon(new ImageIcon(GoodManageInterFrm.class.getResource("/images/ico_seletc_20*20.png")));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		//查询全部结果
		JButton bt_findAll = new JButton("查询全部");
		bt_findAll.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		bt_findAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				fillTable(new Good("","",""),0);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 525, Short.MAX_VALUE)
							.addComponent(bt_findAll, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bt_findAll))
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		s_goodNameTxt = new JTextField();
		s_goodNameTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		s_goodNameTxt.setColumns(10);
		
		JButton button = new JButton("名称查询");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodSearchActionPerformed(e);
			}
		});
		
		//填充下拉列表
		comboBox = new JComboBox();
		fillCB(comboBox);
		
		JButton findByCate = new JButton("类别查询");
		findByCate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodSearchCateActionPerformed(e);
			}
		});
		findByCate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel label_1 = new JLabel("商品类别：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(18)
					.addComponent(s_goodNameTxt, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(findByCate, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(s_goodNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(findByCate)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		panel.setLayout(gl_panel);
		
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
		contentPane.setLayout(gl_contentPane);
		
	}

	//商品查询事件处理-按名称
	private void goodSearchActionPerformed(ActionEvent evt) {
		//Good(name,category,state);
		Good good=new Good(this.s_goodNameTxt.getText().toString(),"","");
		this.fillTable(good,1);
	}
	//商品查询事件处理-按类别
	private void goodSearchCateActionPerformed(ActionEvent evt) {
		
		String category=(String) comboBox.getSelectedItem();
		this.fillTable(new Good("",category,""), 2);
		//重置名称输入
		this.s_goodNameTxt.setText("");
	}
	

	//将查询结构传入表格数据,参数 nButton 区别条件查询和全部查询，条件查询判断条件是否为空而全部不需要
	//0-全部查询，1-查询名称，2-查询类别
	private void fillTable(Good good,int nButton){
		if (nButton == 1 && StringUtil.isEmpty(good.getName())) {
			JOptionPane.showMessageDialog(null, "要查询的商品名不能为空!");
		}
		else {
			DefaultTableModel dtm=(DefaultTableModel) goodTable.getModel();
			dtm.setRowCount(0); // 设置成0行
			Connection con=null;
			try{
				con=dbUtil.getCon();
				ResultSet rs=goodDao.list(con, good);
				while(rs.next()){
					Vector<String> v=new Vector<>();
					v.add(rs.getString("id"));
					v.add(rs.getString("category"));
					v.add(rs.getString("name"));
					v.add(rs.getString("price"));
					v.add(rs.getString("count"));
					v.add(rs.getString("state"));
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
}
