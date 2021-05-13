package com.cg.ppa;

import java.sql.Date;
import java.util.Scanner;

import com.cg.ppa.service.PaperService;

public class PaperMain {

	static Scanner sc = new Scanner(System.in);

	public void main() throws Exception {
		// TODO Auto-generated method stub
		PaperService service = new PaperService();
		DBConnection obj_ConnectDB = new DBConnection();
		System.out.println(obj_ConnectDB.get_connection());

		int ch = 0, ex = 0;
		System.out.println("Welcome to Paper Module");
		do {
			System.out.println(" 1. Create New Paper\n 2. View list of Paper\n 3. Find Paper By ID\n "
					+ "4. Remove Paper\n 5. Update Paper\n 6. View Paper by Publish Date \n 7. Exit");
			System.out.print("Enter your choice: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1: {
				System.out.println(service.createPaper());
				break;
			}
			case 2: {
				System.out.println(service.viewAllPaper());
				break;
			}
			case 3: {
				System.out.print("Enter Paper Id to search: ");
				int id = sc.nextInt();
				System.out.println(service.viewPaperById(id));
				break;
			}
			case 4: {
				System.out.print("Enter Paper Id to delete: ");
				int id = sc.nextInt();
				service.deletePaper(id);
				break;
			}
			case 5: {
				System.out.print("Enter Paper Id to update: ");
				int id = sc.nextInt();
				System.out.println(service.updatePaper(id));
				break;
			}
			case 6: {
				System.out.print("Enter date of publishing: ");
				String date = sc.next();
				Date publishDate = Date.valueOf(date);
				System.out.println(service.viewPaperByPublishDate(publishDate));
				break;
			}
			case 7:
				break;
			default:
				System.out.println("Invalid Choice");
			}
			System.out.println("Press 1 to continue or 2 to exit");
			ex = sc.nextInt();
		} while (ex == 1);

	}

}
