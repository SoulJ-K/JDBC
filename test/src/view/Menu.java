package view;

import java.util.Scanner;

import controller.EmployeeController;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		EmployeeController ec = new EmployeeController();
		
		while(true) {
		System.out.println("[Main Menu]");
		System.out.println("1. 전체 사원 정보 조회");
		System.out.println("2. 사번으로 사원 정보 조회");
		System.out.println("3. 새로운 사원 정보 추가");
		System.out.println("4. 사번으로 사원 정보 수정");
		System.out.println("5. 사번으로 사원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴번호를 입력하세요 : ");
		int menu = Integer.parseInt(sc.nextLine());
		
		switch(menu) {
		case 1 : ec.all();	break;
		case 2 : break;
		case 3 : break;
		case 4 : break;
		case 5 : break;
		case 0 : System.out.println("프로그램을 종료합니다");	break;
		default : System.out.println("다른 번호를 입력했어");	break;
		}
		
		if(menu == 0) {
			break;
		}
	 }
	}
}
