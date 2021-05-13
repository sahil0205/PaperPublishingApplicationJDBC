package com.cg.ppa;

import java.util.Scanner;

import com.cg.ppa.service.LoginService;

public class UserMain {

	static Scanner sc = new Scanner(System.in);

	public void main() throws Exception {
		// TODO Auto-generated method stub
		DBConnection obj_ConnectDB = new DBConnection();
		System.out.println(obj_ConnectDB.get_connection());

		LoginService service = new LoginService();

		int ch = 0, ex = 0;
		System.out.println("Welcome to User Module");
		do {
			System.out.println(" 1. Create New User\n 2. View list of Users\n 3. Find User By ID\n "
					+ "4. Remove User\n 5. Update User\n 6. Login \n 7. Exit");
			System.out.print("Enter your choice: ");
			ch = sc.nextInt();
			switch (ch) {
			case 1: {
				System.out.println(service.addUser());
				break;
			}
			case 2: {
				System.out.println(service.viewAllUsers());
				break;
			}
			case 3: {
				System.out.print("Enter User Id to search: ");
				int id = sc.nextInt();
				System.out.println(service.viewUserById(id));
				break;
			}
			case 4: {
				System.out.print("Enter User Id to delete: ");
				int id = sc.nextInt();
				service.deleteUser(id);
				break;
			}
			case 5: {
				System.out.print("Enter User Id to update: ");
				int id = sc.nextInt();
				System.out.println(service.updateUser(id));
			}
			case 6: {
				System.out.print("Enter email: ");
				String email = sc.next();
				System.out.print("Enter password: ");
				String password = sc.next();
				System.out.println(service.loginUser(email, password));
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
