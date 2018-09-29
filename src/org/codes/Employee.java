package org.codes;

public class Employee {
	// instance variables
	protected int empId, type;
	protected String name;
	protected long phoneNumber;
	protected String dob, doj;
	protected double salary;
	protected double experience, age;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public Employee(int empId, String name, long phoneNumber, String dob,int type) {
		this.empId = empId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.type=type;
	}

	public Employee() {
	}

//public abstract double getPayment();
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
