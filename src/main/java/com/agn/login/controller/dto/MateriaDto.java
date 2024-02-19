package com.agn.login.controller.dto;

import com.agn.login.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MateriaDto {

    private Long id;
    private String name;
    private String cantAlum;
    private User user;
}
