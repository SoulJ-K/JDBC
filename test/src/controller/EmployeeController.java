package controller;

import java.util.ArrayList;

import dao.EmployeeDAO;
import view.Menu;
import vo.Employee;

public class EmployeeController {
	private EmployeeDAO empDao = new EmployeeDAO();
	private Menu me = new Menu();

	public void all() {
		ArrayList<Employee> empList = empDao.all();
		
		
	}

}
