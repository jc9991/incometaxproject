package com.intw.incometaxcalc.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intw.incometaxcalc.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT * FROM employee WHERE DATE_FORMAT(doj, '%Y-%m-%d') BETWEEN :fromDate and :toDate", nativeQuery = true)
	public Optional<List<Employee>> getEmployeeData(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
