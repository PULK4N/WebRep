package cinema.cinema.entity.dto;

import cinema.cinema.entity.Auditorium;

public class AuditoriumDTO {

	private Long id;
    private Integer capacity;
	private String cinemaName;

	public AuditoriumDTO() {
		super();
	}

	public AuditoriumDTO(Auditorium auditorium) {
		this.id = auditorium.getId();
		this.capacity = auditorium.getCapacity();
		this.cinemaName = auditorium.getCinema().getName();
	}

	public AuditoriumDTO(Long id, Integer capacity, String cinemaName) {
		this.id = id;
		this.capacity = capacity;
		this.cinemaName = cinemaName;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getCinemaName() {
		return this.cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

}