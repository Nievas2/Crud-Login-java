package com.agn.login.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*uno a uno*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cantAlum;



}
