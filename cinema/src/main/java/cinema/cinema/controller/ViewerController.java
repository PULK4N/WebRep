package cinema.cinema.controller;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import cinema.cinema.service.ViewerService;
import cinema.cinema.entity.Viewer;
import cinema.cinema.entity.dto.ViewerDTO;

@RestController
@RequestMapping(value = "/api/viewers")
public class ViewerController {
	private final ViewerService viewerService;

	@Autowired
	public ViewerController(ViewerService viewerService) {
		this.viewerService = viewerService;
	}

	// @GetMapping(
	// 	value = "/{id}",
	// 	produces = MediaType.APPLICATION_JSON_VALUE)  // tip odgovora
	// public ResponseEntity<ViewerDTO> getViewer(@PathVariable(name = "id") Long id) {
	// 	Viewer viewer = this.viewerService.findOne(id);
	// 	ViewerDTO viewerDTO = new ViewerDTO(viewer.getId(), viewer.getUsername(), viewer.getPassword(), viewer.getFirstName(), viewer.getLastName(), viewer.getPhoneNumber(), viewer.getEmail(), viewer.getDateOfBirth(), viewer.getRole());

	// 	return new ResponseEntity<>(viewerDTO, HttpStatus.OK);
	// }

	@GetMapping(                                               // value nije naveden, jer koristimo bazni url
	produces = MediaType.APPLICATION_JSON_VALUE)       // tip odgovora
	public ResponseEntity<List<ViewerDTO>> getViewers(){
		List<Viewer> viewers = this.viewerService.findAll();

		List<ViewerDTO> viewerDTOs = new ArrayList<>();

		for(Viewer viewer : viewers){
			ViewerDTO viewerDTO;

			viewerDTO = new ViewerDTO(viewer.getId(), viewer.getUsername(), viewer.getPassword(), viewer.getFirstName(), viewer.getLastName(), viewer.getPhoneNumber(), viewer.getEmail(), viewer.getDateOfBirth(), viewer.getRole());
		
			viewerDTOs.add(viewerDTO);
		}
		return new ResponseEntity<>(viewerDTOs, HttpStatus.OK);
	}

	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,     
		produces = MediaType.APPLICATION_JSON_VALUE)     
	public ResponseEntity<ViewerDTO> createViewer(@RequestBody ViewerDTO viewerDTO) throws Exception{
		
		Viewer viewer = new Viewer(viewerDTO.getId(), viewerDTO.getUsername(), viewerDTO.getPassword(), viewerDTO.getFirstName(), viewerDTO.getLastName(), viewerDTO.getPhoneNumber(),viewerDTO.getEmail(), viewerDTO.getDateOfBirth(),viewerDTO.getRole());

		Viewer newViewer = this.viewerService.create(viewer);

		ViewerDTO newViewerDTO = new ViewerDTO(newViewer.getId(), newViewer.getUsername(), newViewer.getPassword(), newViewer.getFirstName(), newViewer.getLastName(), newViewer.getPhoneNumber(),newViewer.getEmail(), newViewer.getDateOfBirth(),newViewer.getRole());

		return new ResponseEntity<>(newViewerDTO, HttpStatus.OK);
	}

	// @PostMapping(
	// 	value="/get-user",
	// 	consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
    //     produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<User> login(@RequestBody User user) throws Exception{
		
	// 	if(g!=null) {
	// 		Korisnik povratna=new Korisnik(g.getKorisnickoIme(),g.getLozinka(),g.getIme(),g.getPrezime(),g.getKontaktTelefon(),g.getEMail(),g.getDatumRodjenja(),g.getUloga());
	// 		return new ResponseEntity<>(povratna,HttpStatus.OK);
	// 	}else if(m!=null) {
	// 		Korisnik povratna=new Korisnik(m.getKorisnickoIme(),m.getLozinka(),m.getIme(),m.getPrezime(),m.getKontaktTelefon(),m.getEMail(),m.getDatumRodjenja(),m.getUloga());
	// 		return new ResponseEntity<>(povratna,HttpStatus.OK);
	// 	}else if(a!=null) {
	// 		Korisnik povratna=new Korisnik(a.getKorisnickoIme(),a.getLozinka(),a.getIme(),a.getPrezime(),a.getKontaktTelefon(),a.getEMail(),a.getDatumRodjenja(),a.getUloga());
	// 		return new ResponseEntity<>(povratna,HttpStatus.OK);
	// 	}else {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
	// }

}