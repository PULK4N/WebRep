package cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.cinema.entity.*;

public interface ScoreRepository extends JpaRepository<Score, Long> {

}
