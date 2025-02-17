package com.writerxl.api.data.model;

import com.writerxl.api.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wxl_users")
public class UserEntity {

    @Id
    private String userKey;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "userStatus")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
