package com.example.Webappex.contoller;

import com.example.Webappex.Model.Employee;
import com.example.Webappex.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Webcontroller {

    @Autowired
   private EmployeeService service;

    @GetMapping("/employee")
    public List<Employee> GetAllEmployee()
    {
        return service.GetAllEmployee();
    }
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee emp) {
       return service.createEmployee(emp);
    }

    @PutMapping("/employee/{id}")
    public void UpdateEmployee(@RequestBody Employee emp,@PathVariable int id)
    {
        service.UpdateEmployee(id,emp);
    }

    @GetMapping("/employee/{id}")
    public Employee GetEmployee(@PathVariable int id)
    {
        return service.GetEmployee(id);
    }

    @DeleteMapping("/employee/{id}")
    public  String DeleteTopic(@PathVariable int id)
    {
        service.DeleteEmployee(id);
        return "Employee deleted";
    }
}
