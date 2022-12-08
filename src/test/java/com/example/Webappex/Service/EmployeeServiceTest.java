package com.example.Webappex.Service;

import com.example.Webappex.Model.Employee;
import com.example.Webappex.Repo.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo emprep;

    private EmployeeService empser;

    @BeforeEach
    void setup(){
        this.empser =new EmployeeService(this.emprep);

    }

    @Test
    void createEmployee() {
        Employee empTest= new Employee();

        empTest.setEmployeeId(12);
        empTest.setEmployeeName("Ankur");
        empTest.setPhoneNumber(23212);


        emprep.save(empTest);
        when(emprep.save(empTest)).thenReturn(empTest);
        assertNotNull(empser.createEmployee(empTest));
    }

    @Test
    void getAllEmployee() {
        when(emprep.findAll()).thenReturn(Stream.of( new Employee(23,"Harsh",4534)).collect(Collectors.toList()));

        assertEquals(1,empser.GetAllEmployee().size());
    }

    @Test
    void updateEmployee() {
        Employee empTest= new Employee(12,"mariya",321);
        emprep.save(empTest);
        Employee newemp=new Employee(12,"Shivam",541);
        emprep.save(newemp);
        assertNotEquals(newemp,empTest);
    }

    @Test
    void getEmployee() {
        Employee empTest= new Employee(12,"mariya",321);
        emprep.save(empTest);
        int act= empTest.getEmployeeId();
        int exp= 12;
        assertEquals(exp,act);

    }

    @Test
    void deleteEmployee() {
        Employee empTest= new Employee(12,"mariya",321);
        emprep.save(empTest);
        emprep.deleteById(empTest.getEmployeeId());
        boolean id= emprep.existsById(empTest.getEmployeeId());
        assertFalse(id);
    }
}