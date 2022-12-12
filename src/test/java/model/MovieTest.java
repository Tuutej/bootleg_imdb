package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovieTest {

	@Test
	public void testConstructor() {

		String name = "The Shawshank Redemption";
		String director = "Frank Darabont";
		String genre = "Drama";
		int releaseYear = 1994;
		int runtimeMinutes = 142;
		double boxOfficeUsd = 28.3;
		Movie movie = new Movie(name, director, genre, releaseYear, runtimeMinutes, boxOfficeUsd);
		assertEquals(0, movie.getId());
		assertEquals(name, movie.getName());
		assertEquals(director, movie.getDirector());
		assertEquals(genre, movie.getGenre());
		assertEquals(releaseYear, movie.getReleaseYear());
		assertEquals(runtimeMinutes, movie.getRuntimeMinutes());
		assertEquals(boxOfficeUsd, movie.getBoxOfficeUsd(), 0.01);

		movie = new Movie();
		assertEquals(0, movie.getId());
		assertEquals(null, movie.getName());
		assertEquals(null, movie.getDirector());
		assertEquals(null, movie.getGenre());
		assertEquals(0, movie.getReleaseYear());
		assertEquals(0, movie.getRuntimeMinutes());
		assertEquals(0.0, movie.getBoxOfficeUsd(), 0.01);
	}

	@Test
	public void testGetters() {

		Movie movie = new Movie();

		movie.setId(1);
		movie.setName("The Shawshank Redemption");
		movie.setDirector("Frank Darabont");
		movie.setGenre("Drama");
		movie.setReleaseYear(1994);
		movie.setRuntimeMinutes(142);
		movie.setBoxOfficeUsd(28.3);

		assertEquals(1, movie.getId());
		assertEquals("The Shawshank Redemption", movie.getName());
		assertEquals("Frank Darabont", movie.getDirector());
		assertEquals("Drama", movie.getGenre());
		assertEquals(1994, movie.getReleaseYear());
		assertEquals(142, movie.getRuntimeMinutes());
		assertEquals(28.3, movie.getBoxOfficeUsd());

	}

	@Test
	public void testSetters() {

		Movie movie = new Movie();

		movie.setId(1);
		movie.setName("The Shawshank Redemption");
		movie.setDirector("Frank Darabont");
		movie.setGenre("Drama");
		movie.setReleaseYear(1994);
		movie.setRuntimeMinutes(142);
		movie.setBoxOfficeUsd(58.34);

		assertEquals(1, movie.getId());
		assertEquals("The Shawshank Redemption", movie.getName());
		assertEquals("Frank Darabont", movie.getDirector());
		assertEquals("Drama", movie.getGenre());
		assertEquals(1994, movie.getReleaseYear());
		assertEquals(142, movie.getRuntimeMinutes());
		assertEquals(58.34, movie.getBoxOfficeUsd());
	}

}
