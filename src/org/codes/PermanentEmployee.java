package org.codes;

import org.codes.Employee;

public class PermanentEmployee extends Employee {

	private double basic, da, hra;
	public PermanentEmployee(int empId, String name, long phoneNumber, String dob,int type, double basic, double hra, double da) {
		super(empId, name, phoneNumber, dob,type);
		this.basic = basic;
		this.hra = hra;
		this.da = da;
		// TODO Auto-generated constructor stub
	}
	public double getPayment() {
		salary=basic + (basic * hra) + (basic * da);
		return salary;
	}

}
