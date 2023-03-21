package com.example.springboots.employee;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/employees")
@ResponseStatus(code = HttpStatus.OK)
public class EmpController{
   @Autowired
   private EmpService empService;

   @PostMapping("/")
   @ResponseStatus(HttpStatus.CREATED)
   public @ResponseBody Employee addNewEmp(@RequestBody Employee newEmp){
     return empService.addNewEmp(newEmp);
   }
  

   @GetMapping("/")
   public List<Employee> getAllEmp(){
    return empService.getAll();
    
   }

   // @GetMapping("/employees/{id}")
   // public static Employee findById(PathVariable String id ){
   //    int empId = Integer.parseInt(id);
   //    return Repository.findById(empId);
   // }
    
}
