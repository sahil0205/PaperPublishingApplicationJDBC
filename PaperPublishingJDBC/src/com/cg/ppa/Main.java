package com.cg.ppa;

import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		DBConnection obj_ConnectDB = new DBConnection();
		System.out.println(obj_ConnectDB.get_connection());
		CategoryMain category = new CategoryMain();
		UserMain userMain =  new UserMain();
		NewsMain newsMain = new NewsMain();
		PaperMain paperMain = new PaperMain();
		
		int ch = 0, ex = 0;
		System.out.println("Welcome to Paper Publishing Application");
		do {
			System.out.println("1. User Module\n2. Category Module\n3. News Module\n4. Paper Module\n5. Exit");
			System.out.print("Enter your choice: ");
			ch = sc.nextInt();
			switch(ch) {
				case 1: 
					userMain.main();
					break;
				
				case 2:
					category.main();
					break;
				
				case 3:
					newsMain.main();
					break;
					
				case 4:
					paperMain.main();
					break;
					
				case 5:
					break;
				
				default:
					System.out.println("Invalid Choice");
					break;
			}
			System.out.println("Press 1 to continue or 2 to exit");
			ex = sc.nextInt();
		}while(ex==1);

	}

}
