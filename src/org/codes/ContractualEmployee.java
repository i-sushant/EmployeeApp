package org.codes;

import org.codes.Employee;

public class ContractualEmployee extends Employee {
	private int numberOfDays;
	private double perDayWage;
 public ContractualEmployee(int empId, String name, long phoneNumber, String dob,int type,int numberOfDays,double perDayWage) {
	 super(empId, name, phoneNumber, dob,type);
	 this.numberOfDays=numberOfDays;
	 this.perDayWage=perDayWage;
 }
 public double getPayment() {
	 super.salary=numberOfDays*perDayWage;
	 return salary;
 }
}
