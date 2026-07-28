package com.cognizant.orm_learn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.service.EmployeeService;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        testGetAllEmployeesNative();

    }

    private void testGetAllEmployeesNative() {

        System.out.println("******** HANDS-ON 5 ********");

        List<Employee> employeeList = employeeService.getAllEmployeesNative();

        for (Employee employee : employeeList) {

            System.out.println("------------------------------");
            System.out.println("Id          : " + employee.getId());
            System.out.println("Name        : " + employee.getName());
            System.out.println("Salary      : " + employee.getSalary());
            System.out.println("Permanent   : " + employee.isPermanent());

            if (employee.getDepartment() != null) {
                System.out.println("Department  : " + employee.getDepartment().getName());
            }
        }
    }
}