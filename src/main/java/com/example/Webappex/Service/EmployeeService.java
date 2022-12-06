package com.example.Webappex.Service;

import com.example.Webappex.Model.Employee;
import com.example.Webappex.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
 private EmployeeRepo repo;

    public Employee createEmployee(Employee emp) {
        return repo.save(emp);
    }

    public List<Employee> GetAllEmployee() {
        return repo.findAll();
    }

    public void UpdateEmployee(int id, Employee emp) {
        repo.save(emp);
    }

    public Employee GetEmployee(int id) {
       return repo.findById(id).get();
    }

    public void DeleteEmployee(int id) {
        repo.deleteById(id);

    }
}
