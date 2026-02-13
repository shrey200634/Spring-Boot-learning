package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // means default constructor
@AllArgsConstructor // means all perimeterised cons .... comes from loombook
public class UserRoles {


     //for auto increament from sql
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name ="role_Id")

    private Long roleId ;
    private String  name ;


}
