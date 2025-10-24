package com.quantumfusionsolutions.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Data   // it will provide default zero argument constructor for our class, getters & setters for all the variables
@Entity  // Marks the class as a JPA entity, meaning it maps to a database table.
@DynamicInsert  // Excludes null fields from INSERT statements to use database default values.
@DynamicUpdate  // Updates only modified fields in UPDATE statements to improve performance.
public class UserRequest {

    @Id
    private String userId;

    private String fullName;
    private String email;
    private String hashedPassword;
    private boolean emailVerified;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}



