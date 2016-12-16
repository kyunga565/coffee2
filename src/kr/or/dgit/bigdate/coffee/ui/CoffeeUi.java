package kr.or.dgit.bigdate.coffee.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdate.coffee.dao.SaleDao;
import kr.or.dgit.bigdate.coffee.dao.ProductDao;
import kr.or.dgit.bigdate.coffee.dto.Sale;
import kr.or.dgit.bigdate.coffee.list.CoffeeList;
import kr.or.dgit.bigdate.coffee.dto.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class CoffeeUi extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tftitle;
	private JTextField tfprice;
	private JTextField tfamount;
	private JTextField tfmargin;
	private JComboBox<Product> cbcode;
	private List<Product> list;
	private JButton btnAdd;
	private JButton btnResult1;
	private JButton btnResult2;
	private CoffeeList list1;
	private CoffeeList list2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeeUi frame = new CoffeeUi();
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
	public CoffeeUi() {
		setTitle("판매실적계산");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel Main_panel = new JPanel();
		Main_panel.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPane.add(Main_panel, BorderLayout.CENTER);
		Main_panel.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblcode = new JLabel("제품코드");
		lblcode.setHorizontalAlignment(SwingConstants.RIGHT);
		Main_panel.add(lblcode);
		
		cbcode = new JComboBox<>();
		list = ProductDao.getInstance().selectInfoByA();
		for (Product product : list) {
			cbcode.addItem(product);
		}
		Main_panel.add(cbcode);
			
		JLabel lbltitle = new JLabel("제품명");
		lbltitle.setHorizontalAlignment(SwingConstants.RIGHT);
		Main_panel.add(lbltitle);
		
		tftitle = new JTextField();
		tftitle.setEditable(false);
		
		cbcode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tftitle.setText(list.get(cbcode.getSelectedIndex()).getTitle());
			}
		});
		
		Main_panel.add(tftitle);
		tftitle.setColumns(10);
		
		JLabel lblprice = new JLabel("제품단가");
		lblprice.setHorizontalAlignment(SwingConstants.RIGHT);
		Main_panel.add(lblprice);
		
		tfprice = new JTextField();
		tfprice.setColumns(10);
		Main_panel.add(tfprice);
		
		JLabel lblamount = new JLabel("판매수량");
		lblamount.setHorizontalAlignment(SwingConstants.RIGHT);
		Main_panel.add(lblamount);
		
		tfamount = new JTextField();
		tfamount.setColumns(10);
		Main_panel.add(tfamount);
		
		JLabel lblmargin = new JLabel("마진율");
		lblmargin.setHorizontalAlignment(SwingConstants.RIGHT);
		Main_panel.add(lblmargin);
		
		tfmargin = new JTextField();
		tfmargin.setColumns(10);
		Main_panel.add(tfmargin);
		
		JPanel Btn_panel = new JPanel();
		contentPane.add(Btn_panel, BorderLayout.SOUTH);
		
		btnAdd = new JButton("입력");
		btnAdd.addActionListener(this);
		Btn_panel.add(btnAdd);
		
		btnResult1 = new JButton("마진액순");
		btnResult1.addActionListener(this);
		Btn_panel.add(btnResult1);
		
		btnResult2 = new JButton("판매액순");
		btnResult2.addActionListener(this);
		Btn_panel.add(btnResult2);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnResult2) {
			showTableList("판 매 액 순 위", true);
		}
		if (e.getSource() == btnResult1) {
			showTableList("마 진 액 순 위", false);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	
 private void showTableList(String title, boolean issale_price) {
		if (issale_price){
			if (list1==null) {
				list1 = new CoffeeList(issale_price);
				list1.setTitle(title);
			}
			refreshListFrame(list1);
		}else{
			if (list2 == null){
				list2 = new CoffeeList(issale_price);
				list2.setTitle(title);
			}
			refreshListFrame(list2);
		}
	}	
	
	private void refreshListFrame(CoffeeList list) {
			list.reloadData();
			list.setVisible(true);		
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Sale sale = getObject(); 
		if(btnAdd.getText().equals("입력")){
			SaleDao.getInstance().insertInfo(sale);
		}
		clearTf();	
		if (list1 != null){
			list1.reloadData();
		}
		if (list2 != null){
			list2.reloadData();
		}
	}

	private void clearTf() {
		cbcode.setSelectedIndex(0);
		tftitle.setText("");
		tfprice.setText("");
		tfamount.setText("");
		tfmargin.setText("");
	}

	private Sale getObject() {
		Product code = (Product) cbcode.getSelectedItem();
		int price = Integer.parseInt(tfprice.getText().trim());
		int amount = Integer.parseInt(tfamount.getText().trim());
		int margin = Integer.parseInt(tfmargin.getText().trim());
		
		return new Sale(code, price, amount, margin);
	}
	
	public void setObject(Sale sale){
		cbcode.setSelectedItem(sale.getCode());
		
		tfprice.setText(sale.getPrice()+"");
		tfamount.setText(sale.getAmount()+"");
		tfmargin.setText(sale.getMargin()+"");
	}
}
