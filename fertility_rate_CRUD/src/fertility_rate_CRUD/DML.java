package fertility_rate_CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DML {
	private Connection conn;
	static DBconnUtil util = new DBconnUtil();
    //select
	public void selectDB(String year) {
		
		try {
			conn = util.getconnection();
			String sql = "select * from fertility_rate where year=?";
			PreparedStatement prestat= conn.prepareStatement(sql);
			prestat.setString(1, year);

			ResultSet rs = prestat.executeQuery();
            
			while (rs.next()) { 
				System.out.println(+rs.getInt(1) + " " + rs.getDouble(2)+" "+rs.getDouble(3)+" "+rs.getDouble(4)+" "+rs.getDouble(5)+" "+
			                       rs.getDouble(6)+" "+rs.getDouble(7)+" "+rs.getDouble(8)+" "+rs.getDouble(9)+" "+rs.getDouble(10)+" "+
						           rs.getDouble(11)+" "+rs.getDouble(12)+" "+rs.getDouble(13)+" "+rs.getDouble(14)+" "+rs.getDouble(15)+" "+
			                       rs.getDouble(16)+" "+rs.getDouble(17));
			}
			prestat.close();

		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	//updata
	public void updataDB(String cloumn ,String num,String year) {
		String sql= "update fertility_rate "
				+ "set " +cloumn+"="+num
				+ "where year="+year;
		
		try {
			System.out.println(sql);
			conn=util.getconnection();
			Statement stat=conn.createStatement();
			System.out.println("資料更新是否成功:"+!stat.execute(sql));
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//insert
	public void insertDB(String[] datas) {
		String sql="insert into fertility_rate "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = util.getconnection();
			PreparedStatement prestat=conn.prepareStatement(sql);
			if(datas.length==17) {
				int sum=1;
				for(int i=0;i<datas.length;i++) {
					if(sum<=17) {
						prestat.setString(sum, datas[i]);
						sum++;
					}
				}
			}else {
				System.out.println("輸入數量錯誤");
			}
			prestat.execute();
			System.out.println("寫入"+prestat.getUpdateCount()+"筆資料");
			prestat.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//delete
	public void deleteDB(String year) {
		String sql="delete from fertility_rate where year=?";
		try {
			conn =util.getconnection();
			PreparedStatement prestat=conn.prepareStatement(sql);
			prestat.setString(1, year);
			System.out.println("刪除幾筆"+prestat.executeUpdate()+"資料");
			prestat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
