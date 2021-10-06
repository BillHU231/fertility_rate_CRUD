package fertility_rate_CRUD;

import java.util.Scanner;



public class fertility_action {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Datas da = new Datas();
		// 建立資料表
		CreateTable crt = new CreateTable();
		crt.getTable();
		// 下載檔案到DB
		System.out.print("輸入下載的url:");
		String url = sc.nextLine();
		da.URLInputData(url);

		while (true) {
			System.out.print("請輸入代碼要執行的功能(1.查詢,2.更新,3.新增,4.刪除,5.輸出成檔案,6.結束程式):");
			int a = sc.nextInt();

			// DML
			// SELECT
			DML dml = new DML();
			if (a == 1) {
				System.out.println("輸入查詢的西元年份(範圍:2006年~2019年):");
				int select_year = sc.nextInt();
				while (select_year < 2006 || select_year > 2019) {
					System.out.print("輸入年份超出範圍,請在輸入(範圍:2006年~2019年):");
					select_year = sc.nextInt();
					continue;
				}
				dml.selectDB(Integer.toString(select_year));
			}
			// UPDATA
			if (a == 2) {
				System.out.print("請輸入要更改欄位名:");
				String cloumn = sc.next();
				System.out.println("請輸入要更改的年份:");
				String updata_year = sc.next();
				System.out.println("請輸入要更改的值:");
				String num = sc.next();
				dml.updataDB(cloumn, num, updata_year);
			}
			// insert
			if (a == 3) {
				String[] datas = new String[17];
				System.out.print("請輸入資料(17筆):");
				for (int i = 0; i < 17; i++) {
					datas[i] = sc.next();
				}
				dml.insertDB(datas);
			}
			// delect
			if (a == 4) {
				System.out.print("請輸入要刪除年份:");
				String delete_year = sc.next();
				dml.deleteDB(delete_year);
			}
			// ouyput
			if (a == 5) {
				da.DBOutputCsv();
			}
			if (a == 6) {
				break;
			}
			if (a > 6) {
				System.out.print("輸入超出範圍(1~6),請重新輸入:");
				a = sc.nextInt();
				continue;
			}
		}
		sc.close();
		System.out.println("程式結束!!");
	}

}
