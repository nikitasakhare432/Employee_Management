package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository repo;

    public Employee addEmployee(Employee employee) {   // add
        return repo.save(employee);
    }

    public List<Employee> showEmployee() {   // show all
        return repo.findAll();
    }

    public Employee showEmployeeById(int id) {   // show by id
        Optional<Employee> optionalShow = repo.findById(id);
        if (optionalShow.isPresent()) {
            return optionalShow.get();
        } else {
            return null;
        }
    }

    public Employee updateEmployee(int id, Employee newUpdate) {   // update
        Optional<Employee> optionalUpdate = repo.findById(id);
        if (optionalUpdate.isPresent()) {
            Employee employee = optionalUpdate.get();
            employee.setName(newUpdate.getName());
            employee.setDepartment(newUpdate.getDepartment());
            employee.setSalary(newUpdate.getSalary());
            employee.setEmail(newUpdate.getEmail());

            return repo.save(employee);
        } else {
            return null;
        }
    }

    public String deleteEmployee(int id) {   // delete
        Optional<Employee> optionalDelete = repo.findById(id);
        if (optionalDelete.isPresent()) {
            repo.delete(optionalDelete.get());
            return "Employee deleted successfully";
        } else {
            return "Employee not found";
        }
    }
}