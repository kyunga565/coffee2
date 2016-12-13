package kr.or.dgit.bigdate.coffee.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdate.coffee.DbConnection;
import kr.or.dgit.bigdate.coffee.dto.Sale;
import kr.or.dgit.bigdate.coffee.dto.Product;

public class SaleDao {
	private static final SaleDao instance = new SaleDao();

	public static SaleDao getInstance() {
		return instance;
	}
	private SaleDao(){}
	public List<Product> selectInfoByAll(){
		// code, title, price, amount, profit
		List<Product> List =  new ArrayList<Product>();
		String sql = "select code, title from product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;//결과를참조한다는뜻 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				List.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close(); 
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return List;
	}
	private Product getObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return new Product(rs.getString(1),rs.getString(2));
	}
}
