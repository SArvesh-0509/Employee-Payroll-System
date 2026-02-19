package payrollsystem;

import java.io.*;
import java.util.*;

public class Employee {

    private int employee_id;
    private String name;
    private double employee_salary;

    // Default Constructor
    public Employee() {
        this.employee_id = 0;
        this.name = "";
        this.employee_salary = 0.0;
    }

    // Parameterized Constructor
    public Employee(int employee_id, String name, double employee_salary) {
        this.employee_id = employee_id;
        this.name = name;
        this.employee_salary = employee_salary;
    }

    public double getHRA() {
        return employee_salary *0.2;
    }

    public double getDA() {
        return employee_salary *0.1;
    }

    public double getNetSalary() {
        return employee_salary + getHRA() + getDA();
    }
    public String getName() {
        return name;
    }

    public double getSalary() {
        return employee_salary;
    }

    public int getEmployee_id() {
		return employee_id;
	}

    public static class Payroll {

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Search Employee");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                if (choice == 1) {

                    try {
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Basic Salary: ");
                        double salary = sc.nextDouble();

                        Employee emp = new Employee(id, name, salary);

                        FileWriter fw = new FileWriter("employee1.txt",true); 
                        fw.write(emp.getEmployee_id() + "," +
                                 emp.getName() + "," +
                                 emp.getSalary() + "," +
                                 emp.getNetSalary() + "\n");

                        fw.close();

                        System.out.println("Employee Added!");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                } else if (choice == 2) {

                    try {
                        BufferedReader br = new BufferedReader(new FileReader("employee1.txt"));
                        String line;

                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }

                        br.close();

                    } catch (Exception e) {
                        System.out.println("No data found.");
                    }

                } else if (choice == 3) {

                    try {
                        System.out.print("Enter Employee ID to search: ");
                        int searchId = sc.nextInt();

                        BufferedReader br = new BufferedReader(new FileReader("employee1.txt"));
                        String line;
                        boolean found = false;

                        while ((line = br.readLine()) != null) {

                            String[] data = line.split(",");
                            int id = Integer.parseInt(data[0]);

                            if (id == searchId) {
                                System.out.println("Employee Found:");
                                System.out.println("ID: " + data[0]);
                                System.out.println("Name: " + data[1]);
                                System.out.println("Basic Salary: " + data[2]);
                                System.out.println("Net Salary: " + data[3]);
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Employee not found.");
                        }

                        br.close();

                    } catch (Exception e) {
                        System.out.println("Error while searching.");
                    }
                }

            } while (choice != 4);

            sc.close();
        }
    }
}
