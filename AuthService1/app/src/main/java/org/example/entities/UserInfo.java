package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="users")
public class UserInfo {

    @Id
    @Column(name ="user_Id")
    private String userId;
    @Column(name = "username")  // ← Add this

    private String username ;


    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="users_roles",
            joinColumns = @JoinColumn(name="user_Id"),
            inverseJoinColumns = @JoinColumn(name="role_Id")
            )
            private Set<UserRoles> roles =new HashSet<>();




}
