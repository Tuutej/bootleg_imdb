package model;

import database.MovieDao;
import database.MovieJdbcDao;

public class Movie {

	private int id;
	private String name;
	private String director;
	private String genre;
	private int releaseYear;
	private int runtimeMinutes;
	private double boxOfficeUsd;

	public Movie(int id, String name, String director, String genre, int releaseYear, int runtimeMinutes,
			double boxOfficeUsd) {
		super();
		this.id = id;
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.runtimeMinutes = runtimeMinutes;
		this.boxOfficeUsd = boxOfficeUsd;
	}

	public Movie(String name, String director, String genre, int releaseYear, int runtimeMinutes, double boxOfficeUsd) {
		super();
		this.id = 0;
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.runtimeMinutes = runtimeMinutes;
		this.boxOfficeUsd = boxOfficeUsd;
	}

	public Movie() {
		super();
		this.id = 0;
		this.name = null;
		this.director = null;
		this.genre = null;
		this.releaseYear = 0;
		this.runtimeMinutes = 0;
		this.boxOfficeUsd = 0.0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getRuntimeMinutes() {
		return runtimeMinutes;
	}

	public void setRuntimeMinutes(int runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public double getBoxOfficeUsd() {
		return boxOfficeUsd;
	}

	public void setBoxOfficeUsd(double boxOfficeUsd) {
		this.boxOfficeUsd = boxOfficeUsd;
	}

	@Override
	public String toString() {
		return id + " | Nimi: " + name + " | Ohjaaja: " + director + " | Genre: " + genre + " | Julkaisuvuosi: "
				+ releaseYear + " | Kesto: " + runtimeMinutes + " minuuttia | Tuotto: " + boxOfficeUsd
				+ " miljoonaa dollaria";
	}

	public static Movie findById(int id) {
		MovieDao moviedao = new MovieJdbcDao();
		return moviedao.findById(id);
	}

}
