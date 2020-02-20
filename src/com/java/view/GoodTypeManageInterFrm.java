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

import com.java.dao.GoodTypeDao;
import com.java.dao.GoodDao;
import com.java.model.GoodType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class GoodTypeManageInterFrm extends JFrame {

	private JPanel contentPane;
	private JTable goodTypeTable;
	private DbUtil dbUtil = new DbUtil();
	private GoodDao goodDao = new GoodDao();
	private GoodTypeDao goodTypeDao = new GoodTypeDao();
	private JTextField goodTypeNameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodTypeManageInterFrm frame = new GoodTypeManageInterFrm();
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
	public GoodTypeManageInterFrm() {
		setResizable(false);
		setTitle("\u5546\u54C1\u7C7B\u522B\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 716, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("商品类别管理（删除与修改）");
		lblNewLabel.setIcon(new ImageIcon(GoodTypeManageInterFrm.class.getResource("/images/ico_manageGoodType_50_50.png")));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		JLabel label_2 = new JLabel("操作的商品类别名称：");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		goodTypeNameTxt = new JTextField();
		goodTypeNameTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		goodTypeNameTxt.setColumns(10);

		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodTypeUpdateActionEvent(e);
			}
		});

		JButton button_2 = new JButton("\u5220\u9664");
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodTypeDeleteActionEvent(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING).addGroup(Alignment.LEADING,
								gl_contentPane.createSequentialGroup().addComponent(label_2).addGap(18)
										.addComponent(goodTypeNameTxt, GroupLayout.PREFERRED_SIZE, 181,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
										.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 120,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(button_1, GroupLayout.PREFERRED_SIZE, 120,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
								.addComponent(goodTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(79)));

		goodTypeTable = new JTable();
		goodTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		goodTypeTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u7F16\u53F7",
				"\u5546\u54C1\u7C7B\u522B\u540D\u79F0", "\u5546\u54C1\u7C7B\u522B\u63CF\u8FF0" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		goodTypeTable.getColumnModel().getColumn(1).setPreferredWidth(104);
		goodTypeTable.getColumnModel().getColumn(2).setPreferredWidth(102);
		scrollPane.setViewportView(goodTypeTable);
		contentPane.setLayout(gl_contentPane);

		this.fillTable(new GoodType(""));
	}

	// 商品类别删除事件处理 只处理类别名称的输入
	private void goodTypeDeleteActionEvent(ActionEvent evt) {
		String typeName = goodTypeNameTxt.getText();
		if (StringUtil.isEmpty(typeName)) {
			JOptionPane.showMessageDialog(null, "输入要删除的商品类", "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除商品类：" + typeName + " 及该类别所有商品?");
		if (n == 0) {
			Connection con = null;
			try {
				int modifyNum = 0;
				con = dbUtil.getCon();
				//添加删除类别后同时删除对应商品
				modifyNum=goodTypeDao.delete(con, typeName) + goodDao.deleteByCate(con,typeName);
				//删除成功后修改数量一定大于等于1
				if (modifyNum >= 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new GoodType());
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

	// 商品类别修改事件处理
	private void goodTypeUpdateActionEvent(ActionEvent evt) {

		String goodTypeName=goodTypeNameTxt.getText().toString();
		String goodTypeDesc = "";
		int id;
		if(StringUtil.isEmpty(goodTypeName)){
			JOptionPane.showMessageDialog(null,"输入要修改的商品类","",JOptionPane.WARNING_MESSAGE);
			return;
		}
		//先判断修改的对象是否存在
		Connection con=null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = goodTypeDao.findRs(con, goodTypeName);
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null,"输入的商品类不存在","",JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				String newGoodTypeName = JOptionPane.showInputDialog(null,"输入修改后的商品类名","修改商品类别:"+ goodTypeName,JOptionPane.PLAIN_MESSAGE); 
				String newGoodTypeDesc = JOptionPane.showInputDialog(null,"输入修改后的商品类描述","修改商品类别:"+ goodTypeName,JOptionPane.PLAIN_MESSAGE); 
				goodTypeDesc = rs.getString("goodTypeDesc");
				id = rs.getInt("id");
				//对话框留空则不修改
				if (StringUtil.isEmpty(newGoodTypeName))
					newGoodTypeName += goodTypeName;
				if (StringUtil.isEmpty(newGoodTypeDesc)) 
					newGoodTypeDesc += goodTypeDesc;
				GoodType goodType=new GoodType(id,newGoodTypeName,newGoodTypeDesc);
				int modifyNum = goodTypeDao.update(con, goodType);
				if (modifyNum == 1) {
					JOptionPane.showMessageDialog(null, "修改成功");
					this.resetValue();
					this.fillTable(new GoodType());
				}
				else
					JOptionPane.showMessageDialog(null, "修改失败","",JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception e) {
			e.printStackTrace();	
			JOptionPane.showMessageDialog(null,"修改失败","",JOptionPane.WARNING_MESSAGE);
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 填充表格
	private void fillTable(GoodType goodType) {
		DefaultTableModel dtm = (DefaultTableModel) goodTypeTable.getModel();
		dtm.setRowCount(0);//
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = goodTypeDao.list(con, goodType);
			while (rs.next()) {
				Vector<String> v = new Vector<>();
				v.add(rs.getString("id"));
				v.add(rs.getString("goodTypeName"));
				v.add(rs.getString("goodTypeDesc"));
				dtm.addRow(v);
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

	// 重置表单
	private void resetValue() {
		this.goodTypeNameTxt.setText("");
	}
}
