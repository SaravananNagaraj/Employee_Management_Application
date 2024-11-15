package com.em.fs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.em.fs.entity.Employee;
import com.em.fs.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EntityManager entityManager;
	
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long id, Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id :" + id));
		employee.setEmpname(employeeDetails.getEmpname());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setDepartment(employeeDetails.getDepartment());
		employee.setLocation(employeeDetails.getLocation());

		return employeeRepository.save(employee);
	}

	@Transactional
	public void deleteEmployee(Long id) {
		if (!employeeRepository.existsById(id)) {
			throw new EntityNotFoundException("Employee not found with ID: " + id);
		}

		employeeRepository.deleteById(id);
		
		resetAutoIncrement();
	}

	// Method to reset the auto-increment value
	@Transactional
	public void resetAutoIncrement() {
		
		try {
			
			entityManager.createNativeQuery("ALTER TABLE employee DROP PRIMARY KEY, MODIFY id INT;").executeUpdate();
			entityManager.createNativeQuery("SET @new_id = 0;").executeUpdate();
			entityManager.createNativeQuery("UPDATE employee SET id = (@new_id := @new_id + 1);").executeUpdate();
			entityManager.createNativeQuery("ALTER TABLE employee MODIFY id INT PRIMARY KEY AUTO_INCREMENT;").executeUpdate();

		} catch (Exception e) {
			
			throw new RuntimeException("Error resetting auto-increment value: " + e.getMessage());
		}
	}
}
