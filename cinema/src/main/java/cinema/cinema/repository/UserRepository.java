package cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.cinema.entity.User;

public interface UserRepository  extends JpaRepository<User, Long> {
}