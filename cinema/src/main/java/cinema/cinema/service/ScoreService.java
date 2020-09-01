package cinema.cinema.service;

import cinema.cinema.entity.*;
import java.util.*;

public interface ScoreService {
	Score create(Score score) throws Exception;

    Score findOne(Long id);

    Score update(Score score) throws Exception;

    void delete(Long id);

    List<Score> findAll();

    void save(Score score);
}