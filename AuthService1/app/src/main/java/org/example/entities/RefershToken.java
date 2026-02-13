package org.example.entities;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name ="tokens")

public class RefershToken {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
        private int id;

    private String token;

    private Instant expiryDate;

    @OneToOne // one user have only one ... soo one to one
    @JoinColumn(name ="id", referencedColumnName = "user_Id")
    private UserInfo userInfo;


}
