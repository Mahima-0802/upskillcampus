/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private int leaveBalance;
    private String position;

    public Employee(int id, String name, int leaveBalance, String position) {
        this.id = id;
        this.name = name;
        this.leaveBalance = leaveBalance;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Position: " + position + ", Leave Balance: " + leaveBalance;
    }
}

class LeaveApplication {
    private int employeeId;
    private int leaveDays;
    private boolean approved;

    public LeaveApplication(int employeeId, int leaveDays) {
        this.employeeId = employeeId;
        this.leaveDays = leaveDays;
        this.approved = false;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public boolean isApproved() {
        return approved;
    }

    public void approve() {
        this.approved = true;
    }
}

class EmployeeManagementSystem {
    private Map<Integer, Employee> employees;
    private Map<Integer, LeaveApplication> leaveApplications;

    public EmployeeManagementSystem() {
        this.employees = new HashMap<>();
        this.leaveApplications = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public void updateEmployee(int employeeId, String newPosition) {
        Employee employee = employees.get(employeeId);
        if (employee != null) {
            employee.setPosition(newPosition);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void applyLeave(int employeeId, int leaveDays) {
        if (employees.containsKey(employeeId)) {
            LeaveApplication leaveApplication = new LeaveApplication(employeeId, leaveDays);
            leaveApplications.put(employeeId, leaveApplication);
            System.out.println("Leave applied successfully. Waiting for approval.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void approveLeave(int employeeId) {
        if (leaveApplications.containsKey(employeeId)) {
            LeaveApplication leaveApplication = leaveApplications.get(employeeId);
            if (!leaveApplication.isApproved()) {
                Employee employee = employees.get(employeeId);
                if (employee != null && employee.getLeaveBalance() >= leaveApplication.getLeaveDays()) {
                    employee.setLeaveBalance(employee.getLeaveBalance() - leaveApplication.getLeaveDays());
                    leaveApplication.approve();
                    System.out.println("Leave approved successfully.");
                } else {
                    System.out.println("Leave approval failed. Insufficient leave balance.");
                }
            } else {
                System.out.println("Leave has already been approved.");
            }
        } else {
            System.out.println("Leave application not found.");
        }
    }

    public void viewEmployees() {
        for (Employee employee : employees.values()) {
            System.out.println(employee);
        }
    }

    public void deleteEmployee(int employeeId) {
        if (employees.containsKey(employeeId)) {
            employees.remove(employeeId);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nEmployee Management System Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. View Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Apply for Leave");
            System.out.println("6. Approve Leave");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Employee Name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter Initial Leave Balance: ");
                    int leaveBalance = scanner.nextInt();
                    ems.addEmployee(new Employee(id, name, leaveBalance, position));
                    System.out.println("Employee added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Employee ID to update: ");
                    int updateEmployeeId = scanner.nextInt();
                    System.out.print("Enter New Position: ");
                    scanner.nextLine(); // Consume newline
                    String newPosition = scanner.nextLine();
                    ems.updateEmployee(updateEmployeeId, newPosition);
                    break;
                case 3:
                    ems.viewEmployees();
                    break;
                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteEmployeeId = scanner.nextInt();
                    ems.deleteEmployee(deleteEmployeeId);
                    break;
                case 5:
                    System.out.print("Enter Employee ID: ");
                    int applyEmployeeId = scanner.nextInt();
                    System.out.print("Enter Leave Days: ");
                    int leaveDays = scanner.nextInt();
                    ems.applyLeave(applyEmployeeId, leaveDays);
                    break;
                case 6:
                    System.out.print("Enter Employee ID to approve leave: ");
                    int approveEmployeeId = scanner.nextInt();
                    ems.approveLeave(approveEmployeeId);
                    break;
                case 0:
                    System.out.println("Exiting Employee Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
}