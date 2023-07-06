package com.intw.incometaxcalc.service;

import java.util.List;

import com.intw.incometaxcalc.domain.Employee;
import com.intw.incometaxcalc.domain.EmployeeVo;

public interface EmployeeService {
	
	 public int employeeAdd(Employee emp);
	 
	 public List<EmployeeVo> fetchEmpData(String finYear);

}
