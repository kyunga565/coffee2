package kr.or.dgit.bigdate.coffee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/*
import kr.or.dgit.bigdate.coffee.list.CoffeeList;*/
import kr.or.dgit.bigdate.coffee.ui.CoffeeUi;

import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
public class CoffeeMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem ItemAdd;
	private JMenuItem ItemList;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeeMain frame = new CoffeeMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the frame.
	 *//*
	public CoffeeMain() {
		setTitle("상품별 판매실적");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Menu = new JMenu("커피정보");
		menuBar.add(Menu);
		
		ItemAdd = new JMenuItem("추가");
		ItemAdd.addActionListener(this);
		Menu.add(ItemAdd);
		
		ItemList = new JMenuItem("리스트");
		ItemList.addActionListener(this);
		Menu.add(ItemList);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ItemList) {
			actionPerformedMntmNewMenuItem_1(e);
		}
		if (e.getSource() == ItemAdd) {
			actionPerformedMntmNewMenuItem(e);
		}
	}
	protected void actionPerformedMntmNewMenuItem(ActionEvent e) {
		CoffeeUi ui = new CoffeeUi();
		ui.setVisible(true); 
			revalidate();
	}
	protected void actionPerformedMntmNewMenuItem_1(ActionEvent e) {
		CoffeeList list = new CoffeeList();
		setContentPane(list);
		revalidate();
	}
}
*/