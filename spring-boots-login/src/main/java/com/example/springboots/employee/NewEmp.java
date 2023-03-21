package com.example.springboots.employee;
import lombok.*;



@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Getter
public class NewEmp{
        
        private String name;
        private int age;
        private String phoneNumber;
        private String salary;
        private String department;
        private String position;
        
      
}

