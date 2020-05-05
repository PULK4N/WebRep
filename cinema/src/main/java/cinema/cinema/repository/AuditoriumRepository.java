package cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.cinema.entity.*;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {

}
