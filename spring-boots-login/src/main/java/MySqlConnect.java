




import java.sql.*;
import java.util.*;

import com.example.springboots.employee.Employee;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author viett
 */
public class MySqlConnect {
    private static String url = "jdbc:mysql://localhost:3306/qlnv";
    private static String user = "root";
    private static String pass = "Sql18012002";
    private static String driver = "com.mysql.jdbc.Driver";
    
    public ArrayList<Employee> callAllEmp(){
        ArrayList<Employee> emps = new ArrayList<Employee>();
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from employee");
            while(rs.next()){
                Employee emp = null;
                emp.setName(rs.getString("nameEmployee"));
                emp.setAge(rs.getInt("ageEmployee"));
                emp.setPhoneNumber(rs.getString("Employee_Phone"));
                emp.setSalary(rs.getString("EmployeeSalary"));
                emp.setDepartment(rs.getString("Employee_Department"));
                emp.setPosition(rs.getString("Employee_Postion"));
                emps.add(emp);
                
            }
            con.close();
            
        }catch(Exception e){System.out.println(e);}
        return emps;

    }
    public static void addnewEmp(Employee emp){
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO employee (nameEmployee, ageEmployee, Employee_Phone_Number, EmployeeSalary, Employee_Department, Employee_Position)" +
                                                            "VALUES( ?, ?, ?, ?, ?, ?)");
            con.setAutoCommit(false);
            // pstmt.setInt(1, emp.getId());
            pstmt.setString(1, emp.getName());
            pstmt.setInt(2, emp.getAge());
            pstmt.setString(3, emp.getPhoneNumber());
            pstmt.setString(4, emp.getSalary());
            pstmt.setString(5, emp.getDepartment());
            pstmt.setString(6, emp.getPosition());
            pstmt.addBatch();
            pstmt.executeBatch();
            con.commit();
        }catch(Exception e){System.out.println(e);}
    }
    public static void main(String[] args) {
        Employee emp = new Employee();
        
        // emp.setId(2);
        emp.setName("Tuan");
        emp.setAge(21);
        emp.setPhoneNumber("0927338497");
        emp.setSalary("100000000");
        emp.setDepartment("IT");
        emp.setPosition("fresher");
        addnewEmp(emp);
    }
}
