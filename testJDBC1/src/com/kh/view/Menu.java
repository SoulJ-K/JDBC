package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.EmployeeController;
import com.kh.model.vo.Employee;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		EmployeeController ec = new EmployeeController();
		
//		[Main Menu]
//		1. 전체 사원 정보 조회
//		2. 사번으로 사원 정보 조회
//		3. 새로운 사원 정보 추가
//		4. 사번으로 사원 정보 수정
//		5. 사번으로 사원 정보 삭제
//		0. 프로그램 종료
//		0번이 나올때까지 반복문으로 돌리기
		while (true) {
			System.out.println("[Main Menu]");
			System.out.println("1. 전체 사원 정보 조회");
			System.out.println("2. 사번으로 사원 정보 조회");
			System.out.println("3. 새로운 사원 정보 추가");
			System.out.println("4. 사번으로 사원 정보 수정");
			System.out.println("5. 사번으로 사원 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴번호를 입력하세요 : ");
			
//			int menu = sc.nextInt();
//			sc.nextLine();
			int menu = Integer.parseInt(sc.nextLine());
			
		switch (menu) {
		case 1 : ec.selectAll();   		break;
		case 2 : ec.selectEmployee();	break;
		case 3 : ec.insertEmployee();	break;
		case 4 : ec.updateEmployee();	break;
		case 5 : ec.deleteEmployee();	break;
		case 0 : System.out.println("프로그램 종료"); break;
		default : System.out.println("다른 번호 입력했습니다."); break;
		}
		
		if(menu == 0) {
			break;
		}
			
		}
		
	}

	public void selectAll(ArrayList<Employee> empList) {
		System.out.println("사번 / 이름 / 직책 / 직속상사 / 고용일 / 급여 / 커미션 / 부서번호");
		
		for(Employee emp : empList) {
			System.out.println(emp);
		}
	}

	public void displayError(String string) {
		System.out.println("서비스 요청 실패 : " + string);
		
	}

	public int selectEmpNo() {
		System.out.println("사번을 입력하세요 : ");
		int empNo = Integer.parseInt(sc.nextLine());
		return empNo;
	}

	public void selectEmployee(Employee emp) {
		System.out.println("사번 : " + emp.getEmpNo());
		System.out.println("이름 : " + emp.getEmpName());
		System.out.println("직책 : " + emp.getJob());
		System.out.println("직속상사 : " + emp.getMgr());
		System.out.println("고용일 : " + emp.getHireDate());
		System.out.println("급여 : " + emp.getSal());
		System.out.println("커미션 : " + emp.getComm());
		System.out.println("부서번호 : " + emp.getDeptNo());
		
	}

	public Employee insertEmployee() {
		System.out.println("[새로운 사원 정보 추가]");
		
		System.out.print("사번 : ");
		int empNo = Integer.parseInt(sc.nextLine());
		
		System.out.print("이름 : ");
		String empName = sc.nextLine();
		
		System.out.print("직책 : ");
		String job = sc.nextLine();
		
		System.out.print("직속 상사 사번 : ");
		int mgr = Integer.parseInt(sc.nextLine());
		
		System.out.print("급여 : ");
		int sal = Integer.parseInt(sc.nextLine());
		
		System.out.print("커미션(인센티브) : ");
		int comm = Integer.parseInt(sc.nextLine());
		
		System.out.print("부서번호 : ");
		int deptNo = Integer.parseInt(sc.nextLine());
		
		Employee emp = new Employee(empNo, empName, job, mgr, sal, comm, deptNo);
		
		return emp;
	}

	public void displaySuccess(String string) {
		System.out.println("서비스 요청 성공 : " + string); 
		
	}

	public Employee updateEmployee() {
		System.out.println("직책 : ");
		String job = sc.nextLine();
		
		System.out.println("급여 : ");
		int sal = Integer.parseInt(sc.nextLine());
		
		System.out.println("커미션(인센티브) : ");
		int comm = Integer.parseInt(sc.nextLine());
		
		Employee emp = new Employee(job, sal, comm);
		
		return emp;
		
	}

	public char deleteEmployee() {
		System.out.println("정말로 삭제하시겠습니까?(y/n) : ");
		
		char check = sc.nextLine().toLowerCase().charAt(0);
		
		return check;
	}

}
