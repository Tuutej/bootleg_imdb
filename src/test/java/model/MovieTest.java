package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovieTest {

	@Test
	public void testConstructor() {

		Movie movie = new Movie(1, "The Matrix", "Lana and Lilly Wachowski", "Sci-Fi", 1999, 136, 463.5);

		assertEquals(1, movie.getId());
		assertEquals("The Matrix", movie.getName());
		assertEquals("Lana and Lilly Wachowski", movie.getDirector());
		assertEquals("Sci-Fi", movie.getGenre());
		assertEquals(1999, movie.getReleaseYear());
		assertEquals(136, movie.getRuntimeMinutes());
		assertEquals(463.5, movie.getBoxOfficeUsd());
	}

	@Test
	public void testEmptyConstructor() {

		Movie movie = new Movie();

		assertEquals(0, movie.getId());
		assertEquals(null, movie.getName());
		assertEquals(null, movie.getDirector());
		assertEquals(null, movie.getGenre());
		assertEquals(0, movie.getReleaseYear());
		assertEquals(0, movie.getRuntimeMinutes());
		assertEquals(0.0, movie.getBoxOfficeUsd());
	}

	@Test
	public void testGetters() {

		Movie movie = new Movie(1, "The Matrix", "Lana and Lilly Wachowski", "Sci-Fi", 1999, 136, 463.5);

		assertEquals(1, movie.getId());
		assertEquals("The Matrix", movie.getName());
		assertEquals("Lana and Lilly Wachowski", movie.getDirector());
		assertEquals("Sci-Fi", movie.getGenre());
		assertEquals(1999, movie.getReleaseYear());
		assertEquals(136, movie.getRuntimeMinutes());
		assertEquals(463.5, movie.getBoxOfficeUsd());
	}

	@Test
	public void testSetters() {

		Movie movie = new Movie();

		movie.setId(1);
		movie.setName("The Matrix");
		movie.setDirector("Lana and Lilly Wachowski");
		movie.setGenre("Sci-Fi");
		movie.setReleaseYear(1999);
		movie.setRuntimeMinutes(136);
		movie.setBoxOfficeUsd(463.5);

		assertEquals(1, movie.getId());
		assertEquals("The Matrix", movie.getName());
		assertEquals("Lana and Lilly Wachowski", movie.getDirector());
		assertEquals("Sci-Fi", movie.getGenre());
		assertEquals(1999, movie.getReleaseYear());
		assertEquals(136, movie.getRuntimeMinutes());
		assertEquals(463.5, movie.getBoxOfficeUsd());
	}

}
