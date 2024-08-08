package com.learning.ems.repository.impl;

import com.learning.ems.dto.EmployeeDto;
import com.learning.ems.entity.Employee;
import com.learning.ems.exception.ResourceNotFoundException;
import com.learning.ems.mapper.EmployeeMapper;
import com.learning.ems.repository.EmployeeRepository;
import com.learning.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        //transform Dto to entity
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saved = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saved);
    }

    @Override
    public EmployeeDto getEMployeeById(Long id) {

        Employee found = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for id: " + id));

        return EmployeeMapper.mapToEmployeeDto(found);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee found = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for id: " + id));
        found.setFirstName(employeeDto.getFirstName());
        found.setLastName(employeeDto.getLastName());
        found.setEmail(employeeDto.getEmail());
        Employee updated = employeeRepository.save(found);
        return EmployeeMapper.mapToEmployeeDto(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for id: " + id));
        employeeRepository.deleteById(id);
    }
}
