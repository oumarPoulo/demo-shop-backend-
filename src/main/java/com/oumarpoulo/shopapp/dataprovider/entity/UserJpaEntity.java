package com.oumarpoulo.shopapp.dataprovider.entity;


import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "shop_user")
@Data
public class UserJpaEntity {
    @Id
    @GenericGenerator(name = "id_generator", strategy = "increment")
    @GeneratedValue(generator = "id_generator")
    private Long id = 0L;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
