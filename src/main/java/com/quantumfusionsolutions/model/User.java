package com.quantumfusionsolutions.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Data   // it will provide default zero argument constructor for our class, getters & setters for all the variables
@Entity  // Marks the class as a JPA entity, meaning it maps to a database table.
@DynamicInsert  // Excludes null fields from INSERT statements to use database default values.
@DynamicUpdate  // Updates only modified fields in UPDATE statements to improve performance.
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "full_name")
        }
)
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId; // ✅ Must be String since UUID generation produces a String, not Long

   /**  IDENTITY: 1, 2, 3… → easy, sequential, predictable
        UUID:      a1b2c3d4-... → random, unique, harder to guess **/

    @Column(name = "full_name", nullable = false, unique = true, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    private boolean emailVerified = false;

    @Column(nullable = false)
    private String role;

    // ✅ Keep timestamps with proper types — use only Timestamp (not LocalDateTime)
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    // ✅ Automatically set timestamps before insert & update
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}




/**

### 1️⃣ IDENTITY (numeric)

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long userId;
```

DB example: 1, 2, 3…
Pros: simple, sequential
Cons: predictable

---------------------------------------------------------------------
### 2️⃣ UUID (string)

```java
@GeneratedValue(strategy = GenerationType.UUID)
private String userId;
```

DB example: `3f8b2c9a-4e88-4a5a-9e2b-1d8b8a0e9f2a`
Pros: globally unique, hard to guess
Cons: long, not human-friendly
*/