package cinema.cinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.cinema.entity.*;
import cinema.cinema.repository.ScoreRepository;

import java.util.*;

import cinema.cinema.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{
	@Autowired
	ScoreRepository scoreRepository;

	@Override
	public Score create(Score score) throws Exception{
        if (score.getId() != null) {
            throw new Exception("ID must be null!");
        }
        Score newScore = this.scoreRepository.save(score);
        return newScore;
    }

    public Score findOne(Long id){
		return this.scoreRepository.getOne(id);
	}

    public Score update(Score score) throws Exception{//TODO
        // Score scoreToUpdate = this.scoreRepository.getOne(score.getId());
        // if (scoreToUpdate == null) {
        //     throw new Exception("score doesnt exist!");
        // }

        // scoreToUpdate.setFirstName(score.getFirstName());

        Score savedScore = this.scoreRepository.save(score);
        return savedScore;
    }

    public void delete(Long id){
		this.scoreRepository.deleteById(id);
	}

    public List<Score> findAll(){
		return this.scoreRepository.findAll();
    }
    
    public void save(Score score){
        this.scoreRepository.save(score);
    }
}