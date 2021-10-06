package fertility_rate_CRUD;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Datas {
    private   Connection conn;
	private  DBconnUtil util=new DBconnUtil();
	//URLInput
//	URL url = new URL("https://data.ntpc.gov.tw/api/datasets/EA688FDA-9AFE-4C9F-B6BF-2C18D8A88158/csv/file");
	public void URLInputData(String urldata) {
		InputStream ips;
		BufferedInputStream bfi;
		PreparedStatement prestat;
		try {
			URL url = new URL(urldata);
			ips = url.openStream();
			bfi = new BufferedInputStream(ips);

			StringBuilder builder = new StringBuilder();
			int length;
			byte[] buffer = new byte[1024];
			while ((length = bfi.read(buffer)) != -1) {
				builder.append(new String(buffer, 0, length));
			}
			// 資料清理
//			System.out.println(builder.toString());
			String st = builder.toString().substring(164, builder.length() - 2);
			String st1 = st.replaceAll("\"\n\"", "\",\"");
			String data = st1.replaceAll("2019.0", "2019");
//            System.out.println(data);
			// 資料切割
			String[] datas = data.split("\",\"");
			
            //DB connection
			String sql="insert into fertility_rate"
					+ "(year,Fifteen_To_Nineteen_Man,"
					+ "Fifteen_To_Nineteen_woman,Twenty_To_Twentyfour_Men,"
					+ "Twenty_To_Twentyfour_Woman,Twentyfive_To_Twentynine_Man,"
					+ "Twentyfive_To_Twentynine_Woman,Thirty_To_Thirtyfour_Man,"
					+ "Thirty_To_Thirtyfour_Woman,Thirtyfive_To_Thirtynine_Man,"
					+ "Thirtyfive_To_Thirtynine_Woman,Forty_To_Fortyfour_Man,"
					+ "Forty_To_Fortyfour_Woman,Fortyfive_To_Fortynine_Man,"
					+ "Fortyfive_To_Fortynine_Woman,Fifty_To_Fiftynine_Man,"
					+ "Fifty_To_Fiftynine_Woman)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = util.getconnection();
			prestat=conn.prepareCall(sql);
			
			int sum=1;
			for(int i=0;i<datas.length;i++) {
				if(sum<=17) {
					prestat.setObject(sum, datas[i]);
					sum++;
				}else {
					prestat.execute();
					sum=1;
					i--;
				}
			}
			for(int i=221;i<datas.length;i++) {
				if(sum<=17) {
					prestat.setObject(sum, datas[i]);
					sum++;
				}else {
					prestat.execute();
					sum=1;
					i--;
				}
			}
			System.out.println("寫入:"+prestat.getLargeUpdateCount()+"筆資料");
			
			ips.close();
			bfi.close();
			conn.close();
			prestat.close();
			
			util.closeconnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//FileInput
	public void FileInputDB()  {
		InputStream ips;
		BufferedInputStream bips;
		
		PreparedStatement prestat;
		
		try {
			ips=new FileInputStream("C:\\java\\file\\年齡別生育率 .csv");
			bips=new BufferedInputStream(ips);
			byte[] buffer=new byte[1024];
			int length;
			StringBuilder builder=new StringBuilder();
			while ((length = bips.read(buffer)) != -1) {
				builder.append(new String(buffer, 0, length));
			}
			// 資料清理
			String st = builder.toString().substring(163, builder.length() - 2);
			String st1 = st.replaceAll("\"\n\"", "\",\"");
			String data = st1.replaceAll("2019.0", "2019");

			// 資料切割
			String[] datas = data.split("\",\"");
			
            //DB connection
			String sql="insert into fertility_rate"
					+ "(year,Fifteen_To_Nineteen_Man,"
					+ "Fifteen_To_Nineteen_woman,Twenty_To_Twentyfour_Men,"
					+ "Twenty_To_Twentyfour_Woman,Twentyfive_To_Twentynine_Man,"
					+ "Twentyfive_To_Twentynine_Woman,Thirty_To_Thirtyfour_Man,"
					+ "Thirty_To_Thirtyfour_Woman,Thirtyfive_To_Thirtynine_Man,"
					+ "Thirtyfive_To_Thirtynine_Woman,Forty_To_Fortyfour_Man,"
					+ "Forty_To_Fortyfour_Woman,Fortyfive_To_Fortynine_Man,"
					+ "Fortyfive_To_Fortynine_Woman,Fifty_To_Fiftynine_Man,"
					+ "Fifty_To_Fiftynine_Woman)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			DBconnUtil util = new DBconnUtil();
			conn = util.getconnection();
			prestat=conn.prepareCall(sql);
			
			int sum=1;
			for(int i=0;i<datas.length;i++) {
				if(sum<=17) {
					prestat.setObject(sum, datas[i]);
					sum++;
				}else {
					prestat.execute();
					sum=1;
					i--;
				}
			}
			for(int i=221;i<datas.length;i++) {
				if(sum<=17) {
					prestat.setObject(sum, datas[i]);
					sum++;
				}else {
					prestat.execute();
					sum=1;
					i--;
				}
			}
			System.out.println("寫入:"+prestat.getLargeUpdateCount()+"筆資料");
			
			ips.close();
			bips.close();
			conn.close();
			prestat.close();
			
			util.closeconnection();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //outputcsv
	public void DBOutputCsv() {
		try (	FileOutputStream fos=new FileOutputStream("C:\\java\\file\\outfile.csv");
			OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bfw=new BufferedWriter(osw);
			){
			String sql="select * from fertility_rate ";
		    conn=util.getconnection();
		    PreparedStatement prestat=conn.prepareStatement(sql);
		    ResultSet rs=prestat.executeQuery();
		    
		    bfw.write("year,Fifteen_To_Nineteen_Man,Fifteen_To_Nineteen_woman,Twenty_To_Twentyfour_Men,"
		    		+ "Twenty_To_Twentyfour_Woman,Twentyfive_To_Twentynine_Man,Twentyfive_To_Twentynine_Woman,"
		    		+ "Thirty_To_Thirtyfour_Man,Thirty_To_Thirtyfour_Woman,Thirtyfive_To_Thirtynine_Man,"
		    		+ "Thirtyfive_To_Thirtynine_Woman,Forty_To_Fortyfour_Man,Forty_To_Fortyfour_Woman,"
		    		+ "Fortyfive_To_Fortynine_Man,Fortyfive_To_Fortynine_Woman,Fifty_To_Fiftynine_Man,"
		    		+ "Fifty_To_Fiftynine_Woman");
		    bfw.newLine();
			while(rs.next()) {
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
					String data=rs.getString(i);
					bfw.write(data+",");
//					System.out.println(data);
					
				}
				bfw.newLine();
			}
			System.out.println("output finish!");
			prestat.close();
			conn.close();
			util.closeconnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
