package com.cg.ppa;

import java.util.Scanner;

import com.cg.ppa.service.CategoryService;

public class CategoryMain {

	static Scanner sc = new Scanner(System.in);

	public void main() throws Exception {
		// TODO Auto-generated method stub
		DBConnection obj_ConnectDB = new DBConnection();
		System.out.println(obj_ConnectDB.get_connection());

		CategoryService service = new CategoryService();

		int ch = 0, ex = 0;
		System.out.println("Welcome to Category Module");
		do {
			System.out.println(" 1. Create New Category\n 2. View list of Categories\n "
					+ "3. Find Category By ID\n 4. Remove Category\n 5. Update Category \n 7. Exit");
			System.out.print("Enter your choice: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1: {
				System.out.println(service.addCategory());
				break;
			}
			case 2: {
				System.out.println(service.viewAllCategory());
				break;
			}
			case 3: {
				System.out.print("Enter Category Id to search: ");
				int id = sc.nextInt();
				System.out.println(service.viewCategoryById(id));
				break;
			}
			case 4: {
				System.out.print("Enter Category Id to delete: ");
				int id = sc.nextInt();
				service.deleteCategory(id);
				break;
			}
			case 5: {
				System.out.print("Enter id of category to be updated: ");
				int id = sc.nextInt();
				System.out.println(service.updateCategory(id));
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
