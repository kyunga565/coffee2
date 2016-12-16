package kr.or.dgit.bigdate.coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import kr.or.dgit.bigdate.coffee.DbConnection;
import kr.or.dgit.bigdate.coffee.jdbcUtil;
import kr.or.dgit.bigdate.coffee.dto.Sale;
import kr.or.dgit.bigdate.coffee.dto.Product;

public class SaleDao {
	private static final SaleDao instance = new SaleDao();
	private int margin;
	private int amount;
	private int price;
	private int rank;
	private Product code;
	private Product title;
	private int supply_price;
	private int addTax;
	private int sale_price;
	private int margin_price;
	
	public static SaleDao getInstance() {
		return instance;
	}
	private SaleDao(){}
	
	public int insertInfo(Sale sale){
		String sql="insert into sale values(?,?,?,?,?)";
		Connection con = DbConnection.getConnection();
		PreparedStatement pstmt = null;
		int res = -1;
//  rank, code, price, amount, margin, supply_price, addTax, sale_price, margin_price	
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sale.getCode().getCode());
			pstmt.setString(2, sale.getCode().getTitle());
			pstmt.setInt(3, sale.getPrice());
			pstmt.setInt(4, sale.getAmount());
			pstmt.setInt(5, sale.getMargin());
		
			res = pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "입력되었습니다.");
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());//1062=중복
			if(e.getErrorCode()== 1062 ){
				JOptionPane.showMessageDialog(null,"중복입니다.");
			}
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return res;
	}

	public List<Sale> selectInfoByAll(boolean issale_price){
		StringBuilder sql = new StringBuilder();
		
		if (issale_price){
			sql.append("select (select count(*)+1 from output_t where sale_price > t.sale_price) as rank,code,title,price,amount,margin,sale_price,addTax,supply_price,margin_price from output_t t order by rank");
		}else{
			sql.append("select (select count(*)+1 from output_t where margin_price > o.margin_price) as rank, code,title,price,amount,margin,sale_price,addTax,supply_price,margin_price from output_o o  order by rank");
		}
	
		List<Sale> List =  new ArrayList<Sale>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				List.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return List;
	}
	
	public Sale getObject(ResultSet rs) throws SQLException {

		rank = rs.getInt("rank");
		code = (Product) ProductDao.getInstance().selectInfoByAll(rs.getString("code"));
		title = (Product) ProductDao.getInstance().selectInfoByAll(rs.getString("title"));
		price = rs.getInt("price");
		amount = rs.getInt("amount");
		margin = rs.getInt("margin");
		
		int a =  price*amount; //판매금액
		int b = (int) Math.ceil(a/11); //부가세액
		int c = (int)a-b; //공급가액
		int d = (int)(c*margin); //마진액
		
		supply_price = c;
		addTax = b;
		sale_price = a;
		margin_price = d;
		
		return new Sale(rank, code, title, price, amount, margin, supply_price, addTax, sale_price, margin_price);

	}	
	
	public String[] getTotal(){
		Connection con = DbConnection.getConnection();
		String sql = "select sum(supply_price) , sum(addTax) ,sum(sale_price),  sum(margin_price) from output_t";
		String[] total = new String[10];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			DecimalFormat df = new DecimalFormat("#,###");
			if (rs.next()){
				total[0]="합계";
				Arrays.fill(total, 1, 5, "");
				total[6]=df.format(rs.getInt(1));
				total[7]=df.format(rs.getInt(2));
				total[8]=df.format(rs.getInt(3));
				total[9]=df.format(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		return total;
	}
}
