package cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.cinema.entity.Administrator;

public interface AdministratorRepository  extends JpaRepository<Administrator, Long> {
	
}