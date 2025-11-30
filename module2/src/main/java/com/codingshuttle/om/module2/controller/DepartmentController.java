package com.codingshuttle.om.module2.controller;

import com.codingshuttle.om.module2.advice.ApiResponse;
import com.codingshuttle.om.module2.dto.DepartmentDto;
import com.codingshuttle.om.module2.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    final private DepartmentService departmentService ;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartment() ;
        return ResponseEntity.ok(departmentDtoList) ;
    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "departmentId") Long id) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id) ;
        return ResponseEntity.ok(departmentDto) ;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto) ;
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> putDepartmentById(@PathVariable(name = "departmentId") Long id ,@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartment = departmentService.putDepartmentById(departmentDto,id) ;
        return ResponseEntity.ok(updatedDepartment) ;
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> patchDepartmentById(@PathVariable(name = "departmentId") Long id,@RequestBody Map<String,Object> updates) {
        DepartmentDto updatedDepartment = departmentService.patchDepartmentById(updates,id) ;
        return ResponseEntity.ok(updatedDepartment) ;
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<ApiResponse<String>> deleteDepartmentById(@PathVariable(name = "departmentId") Long id) {
        String deleteMessaage = departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok(new ApiResponse<>(deleteMessaage));
    }

}
