package com.em.fs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "employee_name", nullable = false)
    private String empname;
	
	@Column(name = "email_id", unique = true)
    private String emailId;
	
	@Column(name = "mobile_No", unique = true)
    private String mobileNo;
	
	@Column(nullable = false)
    private String department;
	
	@Column(nullable = false)
    private String location;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empname, String emailId, String mobileNo, String department, String location) {
		super();
		this.empname = empname;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.department = department;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
