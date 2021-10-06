package fertility_rate_CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
	private Connection conn;
	public void getTable() {
		DBconnUtil util=new DBconnUtil();
		String sql="create table fertility_rate("
				+ "year int primary key not null,"
				+ "Fifteen_To_Nineteen_Man numeric(9,6),"
				+ "Fifteen_To_Nineteen_woman numeric(9,6),"
				+ "Twenty_To_Twentyfour_Men numeric(9,6),"
				+ "Twenty_To_Twentyfour_Woman numeric(9,6),"
				+ "Twentyfive_To_Twentynine_Man numeric(9,6),"
				+ "Twentyfive_To_Twentynine_Woman numeric(9,6),"
				+ "Thirty_To_Thirtyfour_Man numeric(9,6),"
				+ "Thirty_To_Thirtyfour_Woman numeric(9,6),"
				+ "Thirtyfive_To_Thirtynine_Man numeric(9,6),"
				+ "Thirtyfive_To_Thirtynine_Woman numeric(9,6),"
				+ "Forty_To_Fortyfour_Man numeric(9,6),"
				+ "Forty_To_Fortyfour_Woman numeric(9,6),"
				+ "Fortyfive_To_Fortynine_Man numeric(9,6),"
				+ "Fortyfive_To_Fortynine_Woman numeric(9,6),"
				+ "Fifty_To_Fiftynine_Man numeric(9,6),"
				+ "Fifty_To_Fiftynine_Woman numeric(9,6) )";
		try {
			
			conn=util.getconnection();
			PreparedStatement prestat=conn.prepareStatement(sql);
			boolean b=! prestat.execute();
			
			System.out.println("create tabe finish ?="+b);
		} catch (SQLException e) {
			System.out.println("create table error");
			e.printStackTrace();
		}
	}
}
