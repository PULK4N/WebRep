package cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.cinema.entity.*;

public interface ManagerRepository extends JpaRepository<Manager, Long>{
	Manager findByUsernameAndPassword(String username,String password);
}
