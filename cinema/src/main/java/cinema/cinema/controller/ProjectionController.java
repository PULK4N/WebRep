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
import cinema.cinema.entity.User;
import cinema.cinema.entity.Viewer;
import cinema.cinema.entity.dto.ProjectionDTO;
import cinema.cinema.entity.dto.ReserveTicketDTO;
import cinema.cinema.service.AuditoriumService;
import cinema.cinema.service.MovieService;
import cinema.cinema.service.ProjectionService;
import cinema.cinema.service.UserService;
import cinema.cinema.service.ViewerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api")
public class ProjectionController {
	private final ProjectionService projectionService;

	@Autowired
	public ProjectionController(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ViewerService viewerService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private AuditoriumService auditoriumService;


	@PostMapping(
		value="/register-projection",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectionDTO>> registerProjection(@RequestBody ProjectionDTO projectionDTO) throws Exception{
		Projection projection = new Projection(projectionDTO);
		Movie movie = movieService.findByName(projectionDTO.getMovieName());
		projection.setMovie(movie);
		projection.setAuditorium(auditoriumService.findOne(projectionDTO.getAuditoriumId()));
		Projection newProjection = projectionService.create(projection);
		newProjection.getAuditorium().getSchedule().add(newProjection);
		movie.getProjections().add(projection);
		movieService.save(movie);

		List<ProjectionDTO> projList = new ArrayList<>();
		projList.add(projectionDTO);

		return new ResponseEntity<>(projList, HttpStatus.OK);
	}

	@PostMapping(
		value="/change-projection",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectionDTO>> changeProjection(@RequestBody ProjectionDTO projectionDTO) throws Exception{
		Projection projection = projectionService.findOne(projectionDTO.getId());
		projection.setScheduledTime(projectionDTO.getScheduledTime());
		projectionService.save(projection);

		List<ProjectionDTO> projList = new ArrayList<>();
		projList.add(projectionDTO);

		return new ResponseEntity<>(projList, HttpStatus.OK);
	}

	@PostMapping(
		value="/delete-projection",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectionDTO>> deleteProjection(@RequestBody ProjectionDTO projectionDTO) throws Exception{
		Projection projection = projectionService.findOne(projectionDTO.getId());
		projection.getAuditorium().getSchedule().remove(projection);
		projectionService.delete(projectionDTO.getId());

		List<ProjectionDTO> projList = new ArrayList<>();
		projList.add(projectionDTO);

		return new ResponseEntity<>(projList, HttpStatus.OK);
	}

	@PostMapping(value = "/managers-projections",
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<ProjectionDTO>> getProjections(@RequestBody User user) throws Exception{
		if(!(userService.returnUserByUsernameAndPassword(user) instanceof Manager)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		List<ProjectionDTO> projectionDTOs = new ArrayList<>();
		Manager manager = (Manager)userService.returnUserByUsernameAndPassword(user);
		Set<Cinema> cinemas = manager.getCinemas();
		for (Cinema cinema : cinemas) {
			Set<Auditorium> auditoriums = cinema.getAuditoriums();
			for(Auditorium auditorium : auditoriums){
				Set<Projection> projections = auditorium.getSchedule();
				for (Projection projection : projections) {
					ProjectionDTO projectionDTO;

					projectionDTO = new ProjectionDTO(projection);
				
					projectionDTOs.add(projectionDTO);
				}
			}
		}
		return new ResponseEntity<>(projectionDTOs, HttpStatus.OK);
	}

	@PostMapping(value = "/viewers-projections",
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<ProjectionDTO>> getViewerProjections(@RequestBody User user) throws Exception{
		if(!(userService.returnUserByUsernameAndPassword(user) instanceof Viewer)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		List<ProjectionDTO> projectionDTOs = new ArrayList<>();
		Viewer viewer = (Viewer)userService.returnUserByUsernameAndPassword(user);

		Set<Projection> projections = viewer.getReservedCardsForMovies();
		for (Projection projection : projections) {
			ProjectionDTO projectionDTO;

			projectionDTO = new ProjectionDTO(projection);
		
			projectionDTOs.add(projectionDTO);
		}
		return new ResponseEntity<>(projectionDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/projections",
	produces = MediaType.APPLICATION_JSON_VALUE)       
	public ResponseEntity<List<ProjectionDTO>> getProjections() throws Exception{
		List<Projection> projections = projectionService.findAll();

		List<ProjectionDTO> projectionDTOs = new ArrayList<>();

		for(Projection projection : projections){
			ProjectionDTO projectionDTO;

			projectionDTO = new ProjectionDTO(projection);
		
			projectionDTOs.add(projectionDTO);
		}
		return new ResponseEntity<>(projectionDTOs, HttpStatus.OK);
	}

	@PostMapping(
		value="/reserve-ticket",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReserveTicketDTO> reserveTicket(@RequestBody ReserveTicketDTO reserveTicketDTO) throws Exception{
		User user = userService.returnUserByUsername(reserveTicketDTO.getUsername());
		if(user==null){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}else if(!(user instanceof Viewer)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Viewer viewer = viewerService.findOne(user.getId());
		Projection projection = projectionService.findOne(reserveTicketDTO.getProjectionId());
		if(projection.getReservedCards()==null){
			projection.setReservedCards(0);
		}
		if(projection.getReservedCards() < projection.getAuditorium().getCapacity()){
			if(viewer.getReservedCardsForMovies().contains(projection)==false){
				projection.setReservedCards(projection.getReservedCards() + 1);
				viewer.getReservedCardsForMovies().add(projection);
				projection.getViewers().add(viewer);
				viewerService.save(viewer);
				projectionService.save(projection);
				return new ResponseEntity<>(reserveTicketDTO, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(
		value="/cancel-reserve-ticket",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReserveTicketDTO> cancelReservation(@RequestBody ReserveTicketDTO reserveTicketDTO) throws Exception{
		User user = userService.returnUserByUsername(reserveTicketDTO.getUsername());
		if(user==null){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}else if(!(user instanceof Viewer)){
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Viewer viewer = viewerService.findOne(user.getId());
		Projection projection = projectionService.findOne(reserveTicketDTO.getProjectionId());
		if(projection.getReservedCards()==null){
			projection.setReservedCards(0);
		}
		viewer.getReservedCardsForMovies().remove(projection);
		projection.setReservedCards(projection.getReservedCards()-1);
		projection.getViewers().remove(viewer);

		viewerService.save(viewer);
		projectionService.save(projection);
		return new ResponseEntity<>(reserveTicketDTO, HttpStatus.OK);
	}
}