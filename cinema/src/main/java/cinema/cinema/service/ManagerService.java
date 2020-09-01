package cinema.cinema.service;

import cinema.cinema.entity.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public interface ManagerService {
	
    Manager create(Manager manager) throws Exception;

    Manager findOne(Long id);

    Manager update(Manager manager) throws Exception;

    void delete(Long id);

    List<Manager> findAll();

    void save(Manager manager);
}