package ua.edu.nung.fit;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // ОБОВ'ЯЗКОВИЙ порожній конструктор для JPA
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // JSONProperty анотації над геттерами
    @JsonProperty
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @JsonProperty
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @JsonProperty
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}