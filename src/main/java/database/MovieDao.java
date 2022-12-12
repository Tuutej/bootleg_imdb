package database;

import java.util.List;

import model.Movie;

public interface MovieDao {

	public List<Movie> findAll();

	public boolean addMovie(Movie movie);

	public boolean removeMovie(int movieId);

	public boolean editMovie(Movie movie);

	public Movie findById(int id);

}
