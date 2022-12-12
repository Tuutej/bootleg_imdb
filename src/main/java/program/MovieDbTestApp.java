package program;

import java.util.List;
import java.util.Scanner;

import database.MovieDao;
import database.MovieJdbcDao;
import model.Movie;

public class MovieDbTestApp {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Elokuvasovellus");
		int valinta = -1;
		while (valinta != 0) {
			System.out.print("\n 1 Listaa elokuvat \n 2 Lisää elokuva \n 3 Poista elokuva \n 0 Lopeta");
			System.out.print("\n Syötä valintasi: ");
			valinta = input.nextInt();
			input.nextLine(); // syöttöpuskurin tyhjennys, rivinvaihtomerkin lukeminen erikseen

			if (valinta == 1) {
				listMovies();
			} else if (valinta == 2) {
				addMovie();
			} else if (valinta == 3) {
				removeMovie();
			} else if (valinta == 0) {
				System.out.println("Kiitos ja näkemiin!");
			} else {
				System.out.println("Virheellinen valinta. Valitse uudelleen!");
			}

		}
		while (valinta != 0)
			;
		input.close();
	}

	private static void listMovies() {

		MovieDao moviedao = new MovieJdbcDao();
		List<Movie> movies = moviedao.findAll();
		System.out.println("\nElokuvat: ");
		for (Movie movie : movies) {
			System.out.print(movie.getId());
			System.out.print(" " + movie.getName());
			System.out.print(" " + movie.getDirector());
			System.out.print(" " + movie.getGenre());
			System.out.print(" " + movie.getReleaseYear());
			System.out.print(" " + movie.getRuntimeMinutes());
			System.out.print(" " + movie.getBoxOfficeUsd() + "\n");

		}
	}

	private static void addMovie() {
		Movie newMovie = new Movie();
		System.out.println("\nSyötä lisättävän asiakkaan tiedot!");
		System.out.print("Anna elokuvan nimi:");
		newMovie.setName(input.nextLine());
		System.out.print("Anna elokuvan ohjaaja:");
		newMovie.setDirector(input.nextLine());
		System.out.print("Anna elokuvan genre:");
		newMovie.setGenre(input.nextLine());
		System.out.print("Anna elokuvan julkaisuvuosi:");
		newMovie.setReleaseYear(input.nextInt());
		System.out.print("Anna elokuvan kesto minuuteissa:");
		newMovie.setRuntimeMinutes(input.nextInt());
		System.out.print("Anna elokuvan tuotto ($): ");
		newMovie.setBoxOfficeUsd(input.nextDouble());
		input.nextLine(); // rivinvaihtomerkin lukeminen pois syöttöpuskurista
		// numeerisen arvon lukemisen jälkeen

		MovieDao moviedao = new MovieJdbcDao();
		// asiakkaan tietojen lisäys tietokantaan
		boolean onnistuiLisays = moviedao.addMovie(newMovie);
		if (onnistuiLisays) {
			System.out.println("Asiakkaan tietojen lisäys tietokantaan onnistui.");
		} else {
			System.out.println("Asiakkaan tietojen lisäys tietokantaan ei onnistunut.");
		}
	}

	private static void removeMovie() {

		int movieId = 0;
		System.out.print("\nSyötä poistettavan elokuvan id: ");
		movieId = input.nextInt();
		input.nextLine();

		MovieDao moviedao = new MovieJdbcDao();
		// elokuvan poisto tietokannasta
		boolean onnistuiPoisto = moviedao.removeMovie(movieId);
		if (onnistuiPoisto) {
			System.out.println("Elokuvan poisto tietokantasta onnistui.");
		} else {
			System.out.println("Elokuvan poisto tietokantasta ei onnistunut.");
		}

	}

}
