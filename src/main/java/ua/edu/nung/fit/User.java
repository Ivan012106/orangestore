package ua.edu.nung.fit;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // 1. Обов'язковий конструктор для Hibernate та Jackson
    public User() {}

    // 2. Конструктор для зручності (додайте його)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // 3. Анотації @JsonProperty дозволяють Jackson бачити поля при перетворенні в JSON
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