package com.intw.incometaxcalc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.intw.incometaxcalc.domain.Employee;
import com.intw.incometaxcalc.domain.EmployeeVo;
import com.intw.incometaxcalc.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("api/income")
public class IncomeController {

	@Autowired
	EmployeeService empServ;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> employee(@Valid @RequestBody Employee emp) {
		int empId = empServ.employeeAdd(emp);
		return ResponseEntity.ok("Employee Created with ID :" + empId);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeVo>> getSlabDetails(@RequestParam("finYear") @Pattern(regexp="\\d{4}") String finYear) {
		List<EmployeeVo> employeeVoList = empServ.fetchEmpData(finYear);
		return ResponseEntity.ok(employeeVoList);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put("error", "Validation failed");
	    responseBody.put("details", ex.getBindingResult().getAllErrors());
	    return ResponseEntity.badRequest().body(responseBody);
	}
}
