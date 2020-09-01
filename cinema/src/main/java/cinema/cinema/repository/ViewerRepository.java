package cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.cinema.entity.*;

public interface ViewerRepository extends JpaRepository<Viewer, Long> {
	Viewer findByUsernameAndPassword(String username,String password);

	Viewer findByUsername(String username);
}
