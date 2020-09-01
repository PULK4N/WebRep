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
import cinema.cinema.entity.User;
import cinema.cinema.entity.dto.AuditoriumDTO;
import cinema.cinema.entity.dto.CinemaDTO;
import cinema.cinema.entity.dto.MovieDTO;
import cinema.cinema.service.AuditoriumService;
import cinema.cinema.service.CinemaService;
import cinema.cinema.service.ManagerService;
import cinema.cinema.service.MovieService;
import cinema.cinema.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api")
public class CinemaController {
	
	private final CinemaService cinemaService;

	@Autowired
	public CinemaController(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private AuditoriumService auditoriumService;


	@PostMapping(
		value="/register-cinema",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CinemaDTO>> registerOrChangeCinema(@RequestBody CinemaDTO cinemaDTO) throws Exception{//Nadji cinema
		Cinema _cinema = cinemaService.findByName(cinemaDTO.getName());
		User user = userService.returnUserByUsername(cinemaDTO.getManager());
		Manager manager = null;
		if(user!=null){
			if( (manager = managerService.findOne(user.getId())) == null)
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}else{
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

		if(_cinema == null){
			Cinema cinema = new Cinema(cinemaDTO,manager);
			manager.getCinemas().add(cinema);
			cinemaService.create(cinema);
			managerService.save(manager);
		}else{
			_cinema.setName(cinemaDTO.getNewName());
			_cinema.setAddress(cinemaDTO.getAddress());
			_cinema.setEmail(cinemaDTO.getEmail());
			_cinema.setPhoneNumber(cinemaDTO.getPhoneNumber());
			_cinema.getManager().getCinemas().remove(_cinema);
			_cinema.setManager(manager);
			cinemaService.save(_cinema);
			manager.getCinemas().add(_cinema);
			managerService.save(manager);
		}
		return returnCinemas();
	}

	@PostMapping(
		value="/delete-cinema",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CinemaDTO>> deleteCinema(@RequestBody CinemaDTO cinemaDTO) throws Exception{//Nadji cinema
		Cinema _cinema = cinemaService.findByName(cinemaDTO.getName());

		if(_cinema == null){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}else{
			cinemaService.delete(_cinema.getId());
		}
		return returnCinemas();
	}

	@GetMapping(value = "/cinemas",
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<CinemaDTO>> getCinemas() throws Exception{
		return returnCinemas();
	}

	public ResponseEntity<List<CinemaDTO>> returnCinemas() throws Exception{
		List<Cinema> cinemas = cinemaService.findAll();

		List<CinemaDTO> cinemaDTOs = new ArrayList<>();

		for(Cinema cinema : cinemas){
			CinemaDTO cinemaDTO;

			cinemaDTO = new CinemaDTO(cinema);
		
			cinemaDTOs.add(cinemaDTO);
		}
		return new ResponseEntity<>(cinemaDTOs, HttpStatus.OK);
	}

	@PostMapping(
		value="/managers-cinema",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CinemaDTO>> managerCinema(@RequestBody User user) throws Exception{//Nadji cinema
		if(!(userService.returnUserByUsernameAndPassword(user) instanceof Manager)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Manager manager = (Manager) userService.returnUserByUsernameAndPassword(user);
		Set<Cinema> cinemas = manager.getCinemas();

		List<CinemaDTO> cinemaDTOs = new ArrayList<>();
	
		for(Cinema cinema : cinemas){
			CinemaDTO cinemaDTO;
	
			cinemaDTO = new CinemaDTO(cinema);
		
			cinemaDTOs.add(cinemaDTO);
		}
		return new ResponseEntity<>(cinemaDTOs, HttpStatus.OK);
	}

	@PostMapping(
		value="/managers-auditoriums",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditoriumDTO>> managerAuditoriums(@RequestBody CinemaDTO _cinema) throws Exception{
		if(cinemaService.findByName(_cinema.getName()) == null){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Cinema cinema = cinemaService.findByName(_cinema.getName());
		Set<Auditorium> auditoriums = cinema.getAuditoriums();

		List<AuditoriumDTO> AuditoriumDTOs = new ArrayList<>();
	
		for(Auditorium auditorium : auditoriums){
			AuditoriumDTO auditoriumDTO;
	
			auditoriumDTO = new AuditoriumDTO(auditorium);
		
			AuditoriumDTOs.add(auditoriumDTO);
		}
		return new ResponseEntity<>(AuditoriumDTOs, HttpStatus.OK);
	}

	@PostMapping(
		value="/register-auditorium",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditoriumDTO>> registerAuditorium(@RequestBody AuditoriumDTO auditoriumDTO) throws Exception{//Nadji cinema
		Auditorium auditorium = new Auditorium(auditoriumDTO);
		auditorium.setCinema(cinemaService.findByName(auditoriumDTO.getCinemaName()));
		Auditorium createdAuditorium = auditoriumService.create(auditorium);
		
		createdAuditorium.getCinema().getAuditoriums().add(createdAuditorium);
		List<AuditoriumDTO> auditoriums = new ArrayList<>();
		auditoriums.add(auditoriumDTO);
		return new ResponseEntity<>(auditoriums, HttpStatus.OK);
	}

	@PostMapping(
		value="/delete-auditorium",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuditoriumDTO>> deleteAuditorium(@RequestBody AuditoriumDTO auditoriumDTO) throws Exception{//Nadji cinema
		auditoriumService.delete(auditoriumDTO.getId());
		List<AuditoriumDTO> auditoriums = new ArrayList<>();

		return new ResponseEntity<>(auditoriums, HttpStatus.OK);
	}

	

	@GetMapping(value = "/get-movies",
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<MovieDTO>> getMovies() throws Exception{
		List<Movie> movies = movieService.findAll();

		List<MovieDTO> movieDTOs = new ArrayList<>();

		for(Movie movie : movies){
			MovieDTO movieDTO;

			movieDTO = new MovieDTO(movie);
		
			movieDTOs.add(movieDTO);
		}
		return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
	}

}