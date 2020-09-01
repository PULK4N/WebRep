package cinema.cinema.service;

import cinema.cinema.entity.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public interface ViewerService {
    Viewer create(Viewer viewer) throws Exception;

    Viewer findOne(Long id);

    Viewer findByUsernameAndPassword(String username, String password);

    Viewer update(Viewer viewer) throws Exception;

    void delete(Long id);

    List<Viewer> findAll();

    void save(Viewer viewer);
}