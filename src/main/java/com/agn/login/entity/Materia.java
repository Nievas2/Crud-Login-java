package com.agn.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*uno a muchos*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cantAlum;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Materia(String name, String cantAlum, User user) {
        this.name = name;
        this.cantAlum = cantAlum;
        this.user = user;
    }
}
