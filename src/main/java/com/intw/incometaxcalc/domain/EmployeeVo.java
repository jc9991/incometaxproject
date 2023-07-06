package com.intw.incometaxcalc.domain;

import lombok.Data;

@Data
public class EmployeeVo {

	private int employeeCode;
	private String firstName;
	private String lastName;
	private double yearlSalary;
	private double tax;
	private Double cess;

}
