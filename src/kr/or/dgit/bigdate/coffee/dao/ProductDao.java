package kr.or.dgit.bigdate.coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdate.coffee.DbConnection;
import kr.or.dgit.bigdate.coffee.jdbcUtil;
import kr.or.dgit.bigdate.coffee.dto.Sale;
import kr.or.dgit.bigdate.coffee.dto.Product;

public class ProductDao {
	private static final ProductDao instance = new ProductDao();
	
	public static ProductDao getInstance() {
		return instance;
	}
	private ProductDao(){}
	
	public int insertInfo(Sale info){
		String sql="insert into info values(?,?,?,?,?)";
		Connection con = DbConnection.getConnection();
		PreparedStatement pstmt = null;
		int res = -1;
// code, title, price, amount, profit		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, info.getCode().getCode());
			pstmt.setString(2, info.getCode().getTitle());
			pstmt.setInt(3, info.getPrice());
			pstmt.setInt(4, info.getAmount());
			pstmt.setInt(5, info.getProfit());
			
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return res;
	}
/*	public int updateInfo(Info info){
		String sql="update info set price=?,amount=?,propit=? where code=?";
		int res=-1;
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, info.getCode().getCode());
			pstmt.setString(2, info.getCode().getTitle());
			pstmt.setInt(3, info.getPrice());
			pstmt.setInt(4, info.getAmount());
			pstmt.setInt(5, info.getProfit());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return res;
	}
	
	public void deleteInfo(int no) {
		String sql = "delete from info where code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
	}
	*/
	public List<Sale> selectInfoByAll(){
		// code, title, price, amount, profit
		List<Sale> List =  new ArrayList<Sale>();
		String sql = "select code, title, price, amount, profit from sale";
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
			jdbcUtil.close(pstmt);
		}
		return List;
	}
	public Sale getObject(ResultSet rs) throws SQLException {
		ProductDao.getInstance().selectInfoByAll().get(0);
		return new Sale((Product) rs.getObject("code"),rs.getInt("price"),rs.getInt("amount"),rs.getInt("profit"));
	
		
	}
}
