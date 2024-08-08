package com.learning.ems.repository.impl;

import com.learning.ems.dto.DepartmentDto;
import com.learning.ems.entity.Department;
import com.learning.ems.exception.ResourceNotFoundException;
import com.learning.ems.mapper.DepartmentMapper;
import com.learning.ems.repository.DepartmentRepository;
import com.learning.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department saved = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(saved);
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        Department foundDepartment = departmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Department " + id + " Not Found"));
        return DepartmentMapper.mapToDepartmentDto(foundDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments =departmentRepository.findAll();
        return departments.stream().map(DepartmentMapper::mapToDepartmentDto).toList();
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department foundDepartment = departmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Department " + id + " Not Found"));
        foundDepartment.setDepartmentName(departmentDto.getDepartmentName());
        foundDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
        Department updatedDepartment = departmentRepository.save(foundDepartment);
        return DepartmentMapper.mapToDepartmentDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department foundDepartment = departmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Department " + id + " Not Found"));
        departmentRepository.delete(foundDepartment);
    }
}
