package com.omeracar.vault.service;

import com.omeracar.vault.entity.Employee;
import com.omeracar.vault.exception.ResourceNotFoundException;
import com.omeracar.vault.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: "+id));
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        Employee saved = getById(id);
        saved.setFirstName(employee.getFirstName());
        saved.setLastName(employee.getLastName());
        saved.setDepartment(employee.getDepartment());
        return employeeRepository.save(saved);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
