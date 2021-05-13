package com.cg.ppa;

import java.util.Scanner;

import com.cg.ppa.service.NewsService;

public class NewsMain {

	static Scanner sc = new Scanner(System.in);

	public void main() throws Exception {
		// TODO Auto-generated method stub
		NewsService service = new NewsService();
		DBConnection obj_ConnectDB = new DBConnection();
		System.out.println(obj_ConnectDB.get_connection());

		int ch = 0, ex = 0;
		System.out.println("Welcome to News Module");
		do {
			System.out.println(" 1. Enter New News\n 2. View list of News\n 3. Find News By ID\n "
					+ "4. Delete News\n 5. Update News\n 6. View News By Location \n 7. Exit");
			System.out.print("Enter your choice: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1: {
				System.out.println(service.addNews());
				break;
			}
			case 2: {
				System.out.println(service.viewAllNews());
				break;
			}
			case 3: {
				System.out.print("Enter News Id to search: ");
				int id = sc.nextInt();
				System.out.println(service.viewNewsById(id));
				break;
			}
			case 4: {
				System.out.print("Enter News Id to delete: ");
				int id = sc.nextInt();
				service.deleteNews(id);
				break;
			}
			case 5: {
				System.out.print("Enter News Id to update: ");
				int id = sc.nextInt();
				System.out.println(service.updateNews(id));
				break;
			}
			case 6: {
				System.out.print("Enter Location: ");
				String location = sc.next();
				System.out.println(service.viewNewsByLocation(location));
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
