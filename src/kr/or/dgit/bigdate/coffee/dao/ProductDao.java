package kr.or.dgit.bigdate.coffee.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdate.coffee.DbConnection;
import kr.or.dgit.bigdate.coffee.dto.Product;

public class ProductDao {
	private static final ProductDao instance = new ProductDao();

	public static ProductDao getInstance() {
		return instance;
	}
	private ProductDao(){}
	public Product selectInfoByAll(String code){
		// code, title, price, amount, profit
		Product product = null;
		String sql = "select code, title from product where code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()){
				product = new Product(rs.getString("code"), rs.getString("title"));
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
		return product;
	}
	public List<Product> selectInfoByA(){
		List<Product> List =  new ArrayList<Product>();
		String sql = "select code, title from product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
		return new Product(rs.getString(1),rs.getString(2));
	}
}
