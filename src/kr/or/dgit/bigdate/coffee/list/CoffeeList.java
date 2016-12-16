package kr.or.dgit.bigdate.coffee.list;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.bigdate.coffee.dao.SaleDao;
import kr.or.dgit.bigdate.coffee.dto.Sale;

@SuppressWarnings("serial")
public class CoffeeList extends JFrame {
	JPanel contentpane;
	JTable table;
	public boolean issale_price;
	
	public CoffeeList(boolean issale_price) {
		this.issale_price = issale_price;
		setBounds(10, 10, 800, 200);
		setTitle("판매금액순위");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(getRowData(),getColumnData());
		scrollPane.setViewportView(table);

		reloadData();

	}
	
	private String[] getColumnData() {
		return new String[]{"순위","제품코드","제품명","제품단가","판매수량","마진율","공급가액","부가세액","판매금액","마진액"};
	}
	
 	private String[][] getRowData() {
		List<Sale> cList = SaleDao.getInstance().selectInfoByAll(issale_price);
		String[][] rowDatas = new String[cList.size()+1][];
		for(int i =0;i<cList.size();i++){
			String[] ar = cList.get(i).toArray();
			rowDatas[i] = ar; 
		}
		rowDatas[cList.size()] = SaleDao.getInstance().getTotal();
		return rowDatas;
	}

	public void reloadData() {
		DefaultTableModel model = new DefaultTableModel(getRowData(), getColumnData());
		table.setModel(model);
		tableCellAlignment(SwingConstants.CENTER, 0,1,2);
		tableCellAlignment(SwingConstants.RIGHT, 3,4,5,6,7,8,9);
		tableSetWidth(80);
	}
	
	public void tableCellAlignment(int align,int...idx){
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);//수평정렬
		
		TableColumnModel model = table.getColumnModel();
			for(int i=0; i<idx.length;i++){
				model.getColumn(idx[i]).setCellRenderer(dtcr);
			}
	}
	public void tableSetWidth(int...width){
		TableColumnModel model = table.getColumnModel();
		for(int i=0;i<width.length;i++){
			model.getColumn(i).setPreferredWidth(width[i]);
		}
	}
}
