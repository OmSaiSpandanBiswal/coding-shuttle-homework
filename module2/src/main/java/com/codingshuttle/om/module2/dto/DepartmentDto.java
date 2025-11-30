package com.codingshuttle.om.module2.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id ;
    @NotBlank(message = "Department must have a Title")
    private String title ;
    @NotNull(message = "Department either active or inactive")
    private Boolean isActive ;
    private LocalDateTime cratedAt ;
}
