package org.test;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codes.*;

class WrongDatesException extends Exception {
	public WrongDatesException(String message) {
		super(message);
	}
}

class InvalidNumberFormat extends Exception {
	public InvalidNumberFormat(String message) {
		super(message);
	}
}

class InvalidNameExc extends Exception {
	public InvalidNameExc(String message) {
		super(message);
	}
}

public class TestEmployee2 {
	String name, dob, doj;
	int empId, numberOfDays;
	long phoneNumber;
	int c = 0;
	int age, experience;
	double perDayWage, perHourWage, hoursWorked, stipend, basic, da, hra, salary;
	Employee[] e = new Employee[10];
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestEmployee2 te = new TestEmployee2();
		for (int i = 0; i < 10; i++) {
			te.e[i] = new Employee();
		}
		te.runMenu();
	}

	public void runMenu() throws Exception {
		int updateOrg;
		int salaryFlag = 0, invalidChoice = 0;
		do {
			System.out.println("Update Organisation" + "\n1.Add new Employee" + "\n2.Update employee"
					+ "\n3.Delete employee" + "\n4.Display employee details"
					+ "\n5.Employees with experience more than 5 years" + "\n6.Employees with salary more than 5Lacs"
					+ "\n7.Employees with age between 18-30" + "\n8.Average age of the organization"
					+ "\n9.Average experience of the organization" + "\n10.Show all employees" + "\n11.Exit");
			updateOrg = Integer.parseInt(br.readLine());
			switch (updateOrg) {
			case 1:
				System.out.println("Type of Employee :");
				System.out.println("1.Permanent\n2.Contractual\n(1/2)");
				int ch = Integer.parseInt(br.readLine());
				switch (ch) {
				case 1:
					inputName();
					inputEmpId();
					inputPhoneNumber();
					inputDates();
					do {
						System.out.println("Basic Salary");
						basic = Double.parseDouble(br.readLine());
						System.out.println("Dearness Allowance");
						da = Double.parseDouble(br.readLine());
						System.out.println("House Rent Allowance");
						hra = Double.parseDouble(br.readLine());
						e[c].setType(1);
						PermanentEmployee pe = new PermanentEmployee(e[c].getEmpId(), e[c].getName(),
								e[c].getPhoneNumber(), e[c].getDob(), e[c].getType(), basic, da, hra);
						e[c].setSalary(pe.getPayment());
						if (e[c].getSalary() > 100000) {
							salaryFlag = 1;
							c++;
						} else {
							c--;
							System.out
									.println("Sorry employee cannot be registered because salary is less than 1 lacs!");
						}
					} while (basic < 0 && hra < 0 && da < 0 && salaryFlag != 1);
					salaryFlag = 0;
					break;
				case 2:
					inputName();
					inputEmpId();
					inputPhoneNumber();
					inputDates();
					do {
						System.out.println("Number of days worked");
						numberOfDays = Integer.parseInt(br.readLine());
						System.out.println("Per Day Wage");
						perDayWage = Double.parseDouble(br.readLine());
						e[c].setType(2);
						ContractualEmployee ce = new ContractualEmployee(e[c].getEmpId(), e[c].getName(),
								e[c].getPhoneNumber(), e[c].getDob(), e[c].getType(), numberOfDays, perDayWage);
						e[c].setSalary(ce.getPayment());
						if (e[c].getSalary() > 100000) {
							salaryFlag = 1;
							c++;
						} else {
							System.out
									.println("Sorry employee cannot be registered because salary is less than 1 lacs!");
							c--;
						}
					} while (numberOfDays < 0 && perDayWage < 0 && salaryFlag != 1);
					salaryFlag = 0;
					break;
				default:
					System.out.println("Invalid Choice");
				}
				break;
			case 2:
				System.out.println("Update employee");
				updateDetails();
				break;
			case 3:
				deleteEmp();
				break;
			case 4:
				showDetail();
				break;
			case 5:
				for (int i = 0; i < c; i++) {
					if (e[i].getExperience() > 5) {
						System.out.println("\n===============================\n");
						System.out.println("Name              : " + e[i].getName() + "\nEmployee ID       : "
								+ e[i].getEmpId() + "\nDate of joining   : " + e[i].getDoj() + "\nContact Number    : "
								+ e[i].getPhoneNumber() + "\nExperience        : " + e[i].getExperience()
								+ "\nDate of birth     : " + e[i].getDob() + "\nSalary            : " + e[i].getSalary()
								+ "\nAge               : " + e[i].getAge());
						System.out.println("\n===============================\n");
					}
				}
				break;
			case 6:
				for (int i = 0; i < c; i++) {
					if (e[i].getSalary() > 500000) {
						System.out.println("\n===============================\n");
						System.out.println("Name              : " + e[i].getName() + "\nEmployee ID       : "
								+ e[i].getEmpId() + "\nDate of joining   : " + e[i].getDoj() + "\nContact Number    : "
								+ e[i].getPhoneNumber() + "\nExperience        : " + e[i].getExperience()
								+ "\nDate of birth     : " + e[i].getDob() + "\nSalary            : " + e[i].getSalary()
								+ "\nAge               : " + e[i].getAge());
						System.out.println("\n===============================\n");
					}
				}
				break;
			case 7:
				for (int i = 0; i < c; i++) {
					if (e[i].getAge() >= 18 && e[i].getAge() <= 30) {
						System.out.println("\n===============================\n");
						System.out.println("Name              : " + e[i].getName() + "\nEmployee ID       : "
								+ e[i].getEmpId() + "\nDate of joining   : " + e[i].getDoj() + "\nContact Number    : "
								+ e[i].getPhoneNumber() + "\nExperience        : " + e[i].getExperience()
								+ "\nDate of birth     : " + e[i].getDob() + "\nSalary            : " + e[i].getSalary()
								+ "\nAge               : " + e[i].getAge());
						System.out.println("\n===============================\n");
					}
				}
				break;
			case 8:
				int ageSum = 0;
				for (int i = 0; i < c; i++) {
					ageSum += (int) e[i].getAge();
				}
				System.out.println("\nAverage age of the organization : " + (double) (ageSum / c)+"\n");
				break;
			case 9:
				int expSum = 0;
				for (int i = 0; i < c; i++) {
					expSum += (int) e[i].getExperience();
				}
				System.out.println("\nAverage experience of the organization : " + (double) (expSum / c)+"\n");
				break;
			case 10:
				showAll();
				break;
			case 11:
				System.out.println("Thank you for using the application!");
				System.exit(0);
				break;
			default:
				invalidChoice++;
				System.out.println("Invalid choice");
			}
		} while (updateOrg != 11 && invalidChoice < 3);
		if (invalidChoice >= 3) {
			System.out.println("Sorry no more input can be taken...Maximum number of wrong inputs exceeded!!");
		}
	}


	public void inputName() {
		int nameFlag = 0;
		try {
			do {
				try {
					System.out.println("Name");
					name = br.readLine();
					if (!validateLetters(name)) {
						throw new InvalidNameExc("Invalid name");
					} else {
						e[c].setName(name);
						nameFlag = 1;
					}
				} catch (InvalidNameExc e) {
					System.out.println(e.getMessage());
				}
			} while (nameFlag != 1);
		} catch (IOException e) {
		}
	}

	public void inputEmpId() {
		int sameID = 0, id = 0, idFlag = 0;
		try {
			do {
				try {
					System.out.println("Unique Employee ID (5 digits)");
					empId = Integer.parseInt(br.readLine());
					int tempID = empId;
					if (c > 0) {
						for (int i = 0; i < c; i++) {
							if (empId == e[i].getEmpId()) {
								System.out.println("Employee ID already exists!");
								sameID = 1;
								break;
							}
						}
					}
					while (tempID != 0) {
						tempID /= 10;
						id++;
					}
					if (sameID != 1 && id == 5) {
						idFlag = 1;
						e[c].setEmpId(empId);
					} else {
						if (id < 5 || id > 5)
							System.out.println("Invalid ID format");
						id = 0;
						sameID = 0;
						throw new InvalidNumberFormat("Invalid Number");
					}
				} catch (InvalidNumberFormat e) {
					System.out.println(e.getMessage());
				}
			} while (idFlag != 1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void inputPhoneNumber() {
		int numFlag = 0, num = 0, sameNum = 0;
		try {
			do {
				try {
					System.out.println("Phone Number");
					phoneNumber = Long.parseLong(br.readLine());
					long tempNum = phoneNumber;
					if (c > 0) {
						for (int i = 0; i < c; i++) {
							if (phoneNumber == e[i].getPhoneNumber()) {
								System.out.println("Phone number already exists!");
								sameNum = 1;
								break;
							}
						}
					}
					while (tempNum != 0) {
						tempNum /= 10;
						num++;
					}
					if (sameNum != 1 && num == 10) {
						numFlag = 1;
						e[c].setPhoneNumber(phoneNumber);
					} else {
						num = 0;
						sameNum = 0;
						throw new InvalidNumberFormat("Invalid number");
					}
				} catch (InvalidNumberFormat e) {
					System.out.println(e.getMessage());
				}
			} while (numFlag != 1);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void inputDates() {
		int birthFlag = 0, joiningFlag = 0, dateFlag = 0;

		do {
			try {
				DateFormat df = new SimpleDateFormat("dd MM yyyy");
				do {

					do {
						try {
							System.out.println("Date of birth (dd mm yyyy)");
							dob = br.readLine();
							if (validateDate(dob) != true)
								throw new NumberFormatException("Invalid input");
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						}
					} while (dob.length() != 10);
					int day = Integer.parseInt(dob.substring(0, 2));
					int month = Integer.parseInt(dob.substring(3, 5));
					int year = Integer.parseInt(dob.substring(6, 10));
					try {
						if (!(day >= 1 && day <= 31 && month >= 1 && month <= 12
								&& year < Calendar.getInstance().get(Calendar.YEAR))) {
							throw new WrongDatesException("Invalid Birth Date!\nInput Again");
						} else {
							e[c].setDob(dob);
							birthFlag = 1;
						}
					} catch (WrongDatesException e) {
						System.out.println(e.getMessage());
					}
				} while (birthFlag != 1);

				do {

					do {
						try {
							System.out.println("Date of joining (dd mm yyyy)");
							doj = br.readLine();
							if (validateDate(doj) != true)
								throw new NumberFormatException("Invalid input");
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						}
					} while (doj.length() != 10);
					int day = Integer.parseInt(doj.substring(0, 2));
					int month = Integer.parseInt(doj.substring(3, 5));
					try {
						if (!(day >= 1 && day <= 31 && month >= 1 && month <= 12)) {
							throw new WrongDatesException("Wrong Birth Date!\nInput Again");
						} else {
							e[c].setDob(dob);
							joiningFlag = 1;
						}
					} catch (WrongDatesException e) {
						System.out.println(e.getMessage());
					}
				} while (joiningFlag != 1);
				e[c].setDoj(doj);
				Date birthDate = df.parse(e[c].getDob());
				Date joiningDate = df.parse(e[c].getDoj());
				GregorianCalendar joinDate = new GregorianCalendar();
				joinDate.setTime(joiningDate);
				GregorianCalendar bornDate = new GregorianCalendar();
				bornDate.setTime(birthDate);
				GregorianCalendar currentDate = new GregorianCalendar();
				if (bornDate.getTimeInMillis() >= joinDate.getTimeInMillis())
					throw new WrongDatesException("Wrong dates input");
				else {
					age = (int) Math.round(0.002738
							* ((currentDate.getTimeInMillis() - bornDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)));
					e[c].setAge(age);
					experience = (int) Math.round(0.002738
							* ((currentDate.getTimeInMillis() - joinDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)));
					e[c].setExperience(experience);
					dateFlag = 1;

				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			} catch (WrongDatesException e) {
				System.out.println(e.getMessage());
			}
		} while (dateFlag != 1);
	}

	public boolean validateLetters(String txt) {

		String regx = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();

	}

	public boolean validateDate(String date) {
		String regx = "^([0-2][0-9]|(3)[0-1])(\\s)(((0)[0-9])|((1)[0-2]))(\\s)\\d{4}$";
		Pattern pattern = Pattern.compile(regx, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(date);
		return matcher.find();
	}

	public void updateDetails() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Employee ID");
		int id = Integer.parseInt(br.readLine());
		System.out.println("1.Update salary\n2.Update name");
		int updateEmp = Integer.parseInt(br.readLine());
		switch (updateEmp) {
		case 1:
			int flag = 0;
			for (int i = 0; i < c; i++) {
				if (id == e[i].getEmpId()) {
					System.out.println("Updated Salary");
					salary = Double.parseDouble(br.readLine());
					e[i].setSalary(salary);
					flag = 1;
					break;
				}
			}
			if (flag != 1)
				System.out.println("Employee not found");
			break;
		case 2:
			flag = 0;
			for (int i = 0; i < c; i++) {
				if (id == e[i].getEmpId()) {
					System.out.println("Updated Name");
					name = br.readLine();
					e[i].setName(name);
					flag = 1;
					break;
				}
			}
			if (flag != 1)
				System.out.println("Employee not found");
			break;

		default:
			System.out.println("Invalid Choice");
		}
	}

	public void deleteEmp() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int flag = 0, pos = 0;
		System.out.println("Employee ID");
		int id = Integer.parseInt(br.readLine());
		for (int i = 0; i < c; i++) {
			if (id == e[i].getEmpId()) {
				pos = i;
				flag = 1;
				break;
			}
		}
		if (flag != 1)
			System.out.println("Employee not found");
		else {
			System.out.println(e[pos].getName() + " was deleted");
			if (c > 0)
				for (int i = pos; i < c; i++) {
					e[i] = e[i + 1];
				}
			c--;
		}
	}

	public void showDetail() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int flag = 0;
		System.out.println("Search by :\n1.Name\n2.Employee ID");
		int ch = Integer.parseInt(br.readLine());
		switch (ch) {
		case 1:
			System.out.println("Enter Name");
			String nameSearch = br.readLine();
			for (int i = 0; i < c; i++) {
				if (nameSearch.equalsIgnoreCase(e[i].getName())) {
					System.out.println("\n===============================\n");
					System.out.println("Name              : " + e[i].getName() + "\nEmployee ID       : "
							+ e[i].getEmpId() + "\nDate of joining   : " + e[i].getDoj() + "\nContact Number    : "
							+ e[i].getPhoneNumber() + "\nExperience        : " + e[i].getExperience()
							+ "\nDate of birth     : " + e[i].getDob() + "\nSalary            : " + e[i].getSalary()
							+ "\nAge               : " + e[i].getAge());
					System.out.println("\n===============================\n");
					flag = 1;
				}
			}
			if (flag != 1)
				System.out.println("Employee not found");
			break;
		case 2:
			System.out.println("Enter Employee ID");
			int id = Integer.parseInt(br.readLine());
			for (int i = 0; i < c; i++) {
				if (id == e[i].getEmpId()) {
					System.out.println("\n===============================\n");
					System.out.println("Name              : " + e[i].getName() + "\nEmployee ID       : "
							+ e[i].getEmpId() + "\nDate of joining   : " + e[i].getDoj() + "\nContact Number    : "
							+ e[i].getPhoneNumber() + "\nExperience        : " + e[i].getExperience()
							+ "\nDate of birth     : " + e[i].getDob() + "\nSalary            : " + e[i].getSalary()
							+ "\nAge               : " + e[i].getAge());
					System.out.println("\n===============================\n");
					flag = 1;
					break;
				}
			}
			if (flag != 1)
				System.out.println("Employee not found");
			break;
		default:
			System.out.println("Invalid choice");
		}
		flag = 0;
	}

	public void showAll() {
		if (c > 0)
			for (int i = 0; i < c; i++) {
				System.out.println("\n===============================\n");
					System.out.println("Name              : " + e[i].getName() + "\nEmployee ID       : "
							+ e[i].getEmpId() + "\nDate of joining   : " + e[i].getDoj() + "\nContact Number    : "
							+ e[i].getPhoneNumber() + "\nExperience        : " + e[i].getExperience()
							+ "\nDate of birth     : " + e[i].getDob() + "\nSalary            : " + e[i].getSalary()
							+ "\nAge               : " + e[i].getAge());
				System.out.println("\n===============================\n");
			}
		else
			System.out.println("No employee found");
	}
}
