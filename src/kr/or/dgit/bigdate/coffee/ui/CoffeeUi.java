package kr.or.dgit.bigdate.coffee.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdate.coffee.dao.ProductDao;
import kr.or.dgit.bigdate.coffee.dao.SaleDao;
import kr.or.dgit.bigdate.coffee.dto.Sale;
import kr.or.dgit.bigdate.coffee.list.CoffeeList;
import kr.or.dgit.bigdate.coffee.list.CoffeeLst;
import kr.or.dgit.bigdate.coffee.dto.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class CoffeeUi extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tftitle;
	private JTextField tfprice;
	private JTextField tfamount;
	private JTextField tfprofit;
	private JComboBox<Product> cbcode;
	private JButton btnAdd;
	private JButton btnResult1;

	/**
	 * Launch the application.
	 */
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
//		cbcode.addActionListener(this);
		List<Product> list=SaleDao.getInstance().selectInfoByAll();
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
		
		JLabel lblprofit = new JLabel("마진율");
		lblprofit.setHorizontalAlignment(SwingConstants.RIGHT);
		Main_panel.add(lblprofit);
		
		tfprofit = new JTextField();
		tfprofit.setColumns(10);
		Main_panel.add(tfprofit);
		
		JPanel Btn_panel = new JPanel();
		contentPane.add(Btn_panel, BorderLayout.SOUTH);
		
		btnAdd = new JButton("입력");
		btnAdd.addActionListener(this);
		Btn_panel.add(btnAdd);
		
		btnResult1 = new JButton("판매금액순");
		btnResult1.addActionListener(this);
		Btn_panel.add(btnResult1);
		
		JButton btnResult2 = new JButton("마진액순");
		Btn_panel.add(btnResult2);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnResult1) {
			actionPerformedBtnResult1(e);
		}
		
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Sale info = getObject(); //입력된 text 겟오브텍트하나만듬
		if(btnAdd.getText().equals("입력")){
			ProductDao.getInstance().insertInfo(info);
			JOptionPane.showMessageDialog(null, "입력되었습니다.");
		}else{ //수정
//			CoffeeDao.getInstance().updateInfo(info);
//			JOptionPane.showMessageDialog(null, "수정되었습니다.");
//			btnAdd.setText("추가"); 
		}
		clearTf();
	}

	private void clearTf() {
		cbcode.setSelectedIndex(0);
		tftitle.setText("");
		tfprice.setText("");
		tfamount.setText("");
		tfprofit.setText("");
		
	}

	private Sale getObject() {
		Product code = (Product) cbcode.getSelectedItem();
		String title = tftitle.getText().trim();
		int price = Integer.parseInt(tfprice.getText().trim());
		int amount = Integer.parseInt(tfamount.getText().trim());
		int profit = Integer.parseInt(tfprofit.getText().trim());
		return new Sale(code, price, amount, profit);
	}
	public void setObject(Sale sale){
		cbcode.setSelectedItem(sale.getCode());
		tfprice.setText(sale.getPrice()+"");
		tfamount.setText(sale.getAmount()+"");
		tfprofit.setText(sale.getProfit()+"");
//		btnAdd.setText("수정");
	}

	protected void actionPerformedBtnResult1(ActionEvent e) {
		CoffeeList list = new CoffeeList();
		/*setContentPane(list);*/
		list.setVisible(true);
		revalidate();
	}
}
