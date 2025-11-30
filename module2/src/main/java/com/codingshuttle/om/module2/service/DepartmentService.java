package com.codingshuttle.om.module2.service;

import com.codingshuttle.om.module2.dto.DepartmentDto;
import com.codingshuttle.om.module2.entity.Department;
import com.codingshuttle.om.module2.exception.ResourceNotFoundException;
import com.codingshuttle.om.module2.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import javax.lang.model.type.DeclaredType;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    final private DepartmentRepository departmentRepository ;
    final private ModelMapper modelMapper ;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDto> getAllDepartment() {
        return departmentRepository
                .findAll()
                .stream()
                .map(department -> modelMapper.map(department,DepartmentDto.class))
                .toList() ;
    }

    public DepartmentDto getDepartmentById(Long id) {
        isExistElseThrow(id);
        return modelMapper.map(departmentRepository.findById(id), DepartmentDto.class) ;
    }

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto,Department.class) ;
        departmentRepository.save(department) ;
        return modelMapper.map(department, DepartmentDto.class) ;
    }

    public DepartmentDto putDepartmentById(DepartmentDto departmentDto, Long id) {
        isExistElseThrow(id);
        Department existingDepartment = departmentRepository.findById(id).orElseThrow() ;
        Department department = modelMapper.map(departmentDto,Department.class) ;
        department.setId(id);
        department.setCratedAt(existingDepartment.getCratedAt());
        departmentRepository.save(department);
        return modelMapper.map(department, DepartmentDto.class);
    }

    public DepartmentDto patchDepartmentById(Map<String,Object> updates,Long id) {
        isExistElseThrow(id);
        Department department = departmentRepository.findById(id).orElseThrow() ;
        updates.forEach((field,value) -> {
            Field fieldToBeUpdate = ReflectionUtils.getRequiredField(Department.class,field) ;
            fieldToBeUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdate,department,value);
        });
        departmentRepository.save(department) ;
        return modelMapper.map(department, DepartmentDto.class) ;
    }

    public String deleteDepartmentById(Long id) {
        isExistElseThrow(id);
        departmentRepository.deleteById(id);
        return "Department with id : " + id + " is Deleted Successfully" ;
    }

    public void isExistElseThrow(Long id) {
        if(!departmentRepository.existsById(id)) throw new ResourceNotFoundException("Department is NOT exist with id : " + id) ;
    }



}
