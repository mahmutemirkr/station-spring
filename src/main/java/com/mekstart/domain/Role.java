package com.mekstart.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.mekstart.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false) // Tabloya enum int değeri ile değil String ifade ile kaydedilsin
    private UserRole name; //Normalde 1, 2, 3 diye gelir enumdan


    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }

}
