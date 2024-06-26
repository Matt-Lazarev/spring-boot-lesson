package com.lazarev.springbootlesson2.controller;

import com.lazarev.springbootlesson2.entity.Employee;
import com.lazarev.springbootlesson2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/average")
    public String getEmployeesAggr(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployeesAverage());
        return "employees_avg";
    }

    @GetMapping("/employees/new")
    public String getNewEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/edit")
    public String getNewEmployee(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employee_form";
    }

    @PutMapping("/employees/{id}")
    public String updateEmployees(@PathVariable Integer id, @ModelAttribute Employee employee) {
        employeeService.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployees(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
