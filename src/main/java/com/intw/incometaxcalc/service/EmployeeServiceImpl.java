package com.intw.incometaxcalc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intw.incometaxcalc.domain.Employee;
import com.intw.incometaxcalc.domain.EmployeeVo;
import com.intw.incometaxcalc.repo.EmployeeRepo;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepo repo;
	
	@Override
	public int employeeAdd(Employee emp) {

		return repo.save(emp).getEmpId();
	}

	@Override
	public List<EmployeeVo> fetchEmpData(String finYear) {
		double annSal;

		List<EmployeeVo> employVoList = new ArrayList<EmployeeVo>();

		String frTo = String.valueOf(Integer.parseInt(finYear) + 1);
		Optional<List<Employee>> employee = repo.getEmployeeData(finYear + "-04-01", frTo + "-03-31");
		if (employee.isPresent()) {
			List<Employee> empList = employee.get();
			for (Employee emp : empList) {
				EmployeeVo employVo = new EmployeeVo();
				employVo.setFirstName(emp.getFName());
				employVo.setEmployeeCode(emp.getEmpId());
				employVo.setLastName(emp.getLName());
				annSal = emp.getSalary() * 12;
				employVo.setYearlSalary(annSal);
				employVo.setTax(taxCalc(annSal));
				employVo.setCess(annSal > 2500000 ? annSal * 0.02 : annSal);
				employVoList.add(employVo);
			}

		}

		return employVoList;
	}

	private double taxCalc(double income) {
		double tax = 0.0;
		if (income <= 250000) {
			tax = 0.0;
		} else if (income > 250000 && income <= 500000) {
			tax = (income - 250000) * 0.05;
		} else if (income > 500000 && income <= 100000) {
			tax = 12500 + (income - 500000) * 0.1;
		} else if (income > 1000000) {
			tax = (income - 1000000) * 0.2 + 62500;
		}
		return tax;
	}
}
