package model.management;

import model.persistence.entity.Role;
import model.persistence.service.RoleService;

import javax.persistence.Column;
import java.util.List;

public class TestEmployeeManager {

    public static void main(String[] args) {
        EmployeeManager employeeManager =new EmployeeManager();
        RoleService roleService = new RoleService();
        List<Role> roles = roleService.findAll();


//        for (Role r:roles) {
//            System.out.println(r);
//        }
//          System.exit(0);

//        List<String> s =  employeeManager.getRoles();
//        for (String a:s) {
//            System.out.println(a);
//        }
//        System.exit(0);

        employeeManager.saveEmployee("Dante","Alighieri","Captain",85000);
        System.exit(0);
    }
}
