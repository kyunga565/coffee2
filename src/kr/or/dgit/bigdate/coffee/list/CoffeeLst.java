package kr.or.dgit.bigdate.coffee.list;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CoffeeLst extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeeLst frame = new CoffeeLst();
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
	public CoffeeLst() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(table, BorderLayout.SOUTH);
	}

	
	public void reloadData() {
		DefaultTableModel model = new DefaultTableModel(getRowData(), getColumn());
		table.setModel(model);
		//model바뀌는순강ㅅ로정렬해야ㅐ됨
		
	}

	private Object[][] getRowData() {
		// TODO Auto-generated method stub
		return null;
	}
	

	private String[] getColumn() {
		 return new String[]{"제품코드","제품명","제품단가","판매수량","마진율"};
	}
}
