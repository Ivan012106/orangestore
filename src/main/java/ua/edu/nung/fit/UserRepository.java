package ua.edu.nung.fit; // або ua.edu.nung.fit.resources, якщо файл лежить у цій папці

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Тіло інтерфейсу залишаємо порожнім
}