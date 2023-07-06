package com.intw.incometaxcalc.domain;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "Employee",schema = "intwdb")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	@NotEmpty(message = "FirstName is mandatory field")
	private String fName;
	@NotEmpty(message = "LastName is mandatory field")
	private String lName;
	@Email(message = "Invalid Email ID")
	private String email;
	@NotEmpty(message = "mobileNumber is mandatory field")
	private List<String> mobileNumber;
	@NotNull(message = "Mandatory field")
	private Date doj;
	@NotNull(message = "Salary is mandatory field")
	private Double salary;
	

}
