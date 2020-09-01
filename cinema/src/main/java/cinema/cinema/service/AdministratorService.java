package cinema.cinema.service;

import cinema.cinema.entity.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public interface AdministratorService {
	
    Administrator create(Administrator employee) throws Exception;

    Administrator findOne(Long id);

    Administrator update(Administrator employee) throws Exception;

    void delete(Long id);

    List<Administrator> findAll();

    void save(Administrator administrator);
}