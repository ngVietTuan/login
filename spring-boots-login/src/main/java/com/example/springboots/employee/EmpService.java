package com.example.springboots.employee;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.sql.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor
@Service
@Slf4j
public class EmpService {
    private final EmployeeRepository repository;
    
    
    public Employee addNewEmp(Employee newEmp){
        
        log.info("Employee {} is saved", newEmp.getName());
        return repository.save(newEmp);
    }
   public List<Employee> getAll(){
        return repository.findAll();
   }
    
    
  
}