package ua.edu.nung.fit;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Каже Spring, що цей клас обробляє REST-запити (повертає JSON)
@RequestMapping("/api/v1/users") // Базовий шлях (URL) для всіх методів у цьому класі
public class UserController {

    private final UserRepository userRepository;

    // Впровадження залежності (Dependency Injection): Spring сам передасть сюди наш репозиторій
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Метод для отримання всіх користувачів (GET http://localhost:8080/api/v1/users)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Метод для створення нового користувача (POST http://localhost:8080/api/v1/users)
    @PostMapping
    public User createUser(@RequestBody User user) {
        // @RequestBody автоматично перетворює вхідний JSON у об'єкт User
        return userRepository.save(user);
    }
}