package kr.or.dgit.bigdate.coffee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	private static final DbConnection instance = new DbConnection();
	private static Connection con; //5
	//1.외부에서못만들게할려고 private()
	private DbConnection(){
		try {
//			Class.forName("com.mysql.jdbc.Driver");//3.try catch
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp","user_erp", "rootroot");//6
//위식을 아래로 바꿔줌
			Class.forName(Config.DRIVER);
			con = DriverManager.getConnection(Config.URL+Config.DB_NAME,Config.USER,Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			System.err.println("jdbc Driver 등록"); //4
		} catch (SQLException e) {//7-->6에서 오류뜨면 가져오면됨
			System.err.println("주소 혹은 계정 비밀번호 확인요망");
		}
	}
	
	//2.get가져옴
	public static Connection getConnection() { //이름바꾸뮤ㅠ....
		if(instance == null){ //8추가
			new DbConnection();
		}
		return instance.con;
	}
	public static void closeConnection(){
		if(con != null){//9
			try {
				con.close();
				con = null; //10종료11testmain만들어줌
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
