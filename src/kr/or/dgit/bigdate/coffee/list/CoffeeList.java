package kr.or.dgit.bigdate.coffee.list;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.bigdate.coffee.dao.ProductDao;
import kr.or.dgit.bigdate.coffee.dao.SaleDao;
import kr.or.dgit.bigdate.coffee.dto.Sale;
import kr.or.dgit.bigdate.coffee.dto.Product;
import kr.or.dgit.bigdate.coffee.ui.CoffeeUi;

public class CoffeeList extends JFrame {
	JPanel contentpane;
	JTable table;
	
	public CoffeeList() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable(getRowData(),getColumnData());//getRowData(),getColumnData()
		scrollPane.setViewportView(table);
		reloadData();
	}
	private String[] getColumnData() {
		return new String[]{"제품코드","제품명","제품단가","판매수량","마진율"};
	}

	private String[][] getRowData() {
		List<Product> cList = SaleDao.getInstance().selectInfoByAll();
		String[][] rowDatas = new String[cList.size()][];
		for(int i =0;i<cList.size();i++){
			Product d = cList.get(i);
			String[] ar = cList.get(i).toArray();
			rowDatas[i] = ar; 
		}
		return rowDatas;
	}
/*	private String[][] getRowData2() {
		List<Info> List = CoffeeDao.getInstance().selectInfoByAll();
		String[][] rowDatas = new String[List.size()][];
		for(int i =0;i<List.size();i++){
			Info d = List.get(i);
			String[] ar = List.get(i).toArray();
			rowDatas[i] = ar; 
		}
		return rowDatas;
	}*/
	public void reloadData() {
		DefaultTableModel model = new DefaultTableModel(getRowData(), getColumnData());
		table.setModel(model);
		//model바뀌는순강ㅅ로정렬해야ㅐ됨
		
	}
}
