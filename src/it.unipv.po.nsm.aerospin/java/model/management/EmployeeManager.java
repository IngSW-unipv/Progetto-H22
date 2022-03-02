package model.management;

import model.persistence.entity.Employee;
import model.persistence.entity.Role;
import model.persistence.service.EmployeeService;
import model.persistence.service.RoleService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManager {

    private List<String> roles = new ArrayList<>();
    private RoleService roleService = new RoleService();
    private List<Role> roleList = new ArrayList<>();
    private EmployeeService employeeService = new EmployeeService();

    public List<String> getRoles(){
        //roles.clear();
        roleList = roleService.findAll();
        for (int i = 0; i <roleList.size(); i++) {
            roles.add(roleList.get(i).getRole());
        }
        roles.sort(Comparator.naturalOrder());
       return roles.stream().distinct().collect(Collectors.toList());
    }


    public void saveEmployee(String name, String surname, String role,double salary){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setHiringDate( new Date(System.currentTimeMillis()));
        employeeService.persist(employee);

    }






}
