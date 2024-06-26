package com.lazarev.springbootlesson2.service;

import com.lazarev.springbootlesson2.entity.Employee;
import com.lazarev.springbootlesson2.model.EmployeeAggregate;
import com.lazarev.springbootlesson2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<EmployeeAggregate> getAllEmployeesAverage() {
        return employeeRepository.findAllAverageByDepartment();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer id, Employee employee) {
        Employee updatableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));

        updatableEmployee.setDepartment(employee.getDepartment());
        updatableEmployee.setName(employee.getName());
        updatableEmployee.setSalary(employee.getSalary());

        employeeRepository.save(updatableEmployee);
    }

    public void deleteEmployee(Integer id) {
        int deleted = employeeRepository.deleteEmployeeById(id);

        if(deleted == 0) {
            throw new NoSuchElementException("Employee not found");
        }
    }
}
