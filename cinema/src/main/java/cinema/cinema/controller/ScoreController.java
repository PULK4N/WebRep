package cinema.cinema.controller;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cinema.cinema.entity.Auditorium;
import cinema.cinema.entity.Cinema;
import cinema.cinema.entity.Manager;
import cinema.cinema.entity.Movie;
import cinema.cinema.entity.Projection;
import cinema.cinema.entity.Score;
import cinema.cinema.entity.User;
import cinema.cinema.entity.Viewer;
import cinema.cinema.entity.dto.BuyTicketDTO;
import cinema.cinema.entity.dto.ProjectionDTO;
import cinema.cinema.entity.dto.ReserveTicketDTO;
import cinema.cinema.entity.dto.ViewerDTO;
import cinema.cinema.entity.dto.ScoreDTO;
import cinema.cinema.service.AuditoriumService;
import cinema.cinema.service.MovieService;
import cinema.cinema.service.ProjectionService;
import cinema.cinema.service.ScoreService;
import cinema.cinema.service.UserService;
import cinema.cinema.service.ViewerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api")
public class ScoreController{
	private final ScoreService scoreService;

	@Autowired
	public ScoreController(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ViewerService viewerService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private ProjectionService projectionService;

	@PostMapping(
		value="/create-score",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyTicketDTO> createScore(@RequestBody BuyTicketDTO buyTicketDTO) throws Exception{
		User user = userService.returnUserByUsername(buyTicketDTO.getUsername());
		if(!(user instanceof Viewer)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Viewer viewer = (Viewer)userService.returnUserByUsernameAndPassword(user);
		Movie movie = projectionService.findOne(buyTicketDTO.getProjectionId()).getMovie();
		Score _score = new Score(movie,viewer,0);
		scoreService.create(_score);

		movie.getScores().add(_score);
		movie.getProjections().remove(projectionService.findOne(buyTicketDTO.getProjectionId()));
		
		viewer.getReservedCardsForMovies().remove(projectionService.findOne(buyTicketDTO.getProjectionId()));
		viewer.getWatchedMoviesWithScores().add(_score);

		viewerService.save(viewer);
		movieService.save(movie);



		return new ResponseEntity<>(buyTicketDTO, HttpStatus.OK);
	}

	@PostMapping(
		value="/score-a-movie",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScoreDTO> scoreMovie(@RequestBody ScoreDTO scoreDTO) throws Exception{
		User user = userService.returnUserByUsername(scoreDTO.getUsername());
		if(!(user instanceof Viewer)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		System.out.println(scoreDTO.getScoreId());
		Score _score = scoreService.findOne(scoreDTO.getScoreId());
		_score.setValue(scoreDTO.getMovieScore());
		_score.getWatchedMovie().setGrade((_score.getWatchedMovie().getGrade()+_score.getValue())/_score.getWatchedMovie().getScores().size());
		scoreService.save(_score);

		return new ResponseEntity<>(scoreDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/viewer-scores",
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<ScoreDTO>> getViewerScores(@RequestBody User user) throws Exception{
		User _user = userService.returnUserByUsernameAndPassword(user);
		if(!(_user instanceof Viewer)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		List<ScoreDTO> scoreDTOs = new ArrayList<>();
		Viewer viewer = (Viewer)_user;

		Set<Score> scores = viewer.getWatchedMoviesWithScores();
		for (Score score : scores) {
			ScoreDTO scoreDTO;

			scoreDTO = new ScoreDTO(score.getWatchedMovie().getName(),score.getValue());
			scoreDTO.setScoreId(score.getId());
			scoreDTOs.add(scoreDTO);
		}
		return new ResponseEntity<>(scoreDTOs, HttpStatus.OK);
	}
}