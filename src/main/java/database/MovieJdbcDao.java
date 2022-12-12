package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Movie;

public class MovieJdbcDao implements MovieDao {

	/**
	 * Hakee tietokannan taulusta kaikki elokuvat ja luo ja palauttaa tiedot
	 * Movie-tyyppisten olioiden listana (ArrayList)
	 * 
	 * @return ArrayList<Movie> Lista elokuvat
	 */
	public ArrayList<Movie> findAll() {
		Connection connection = null; // tietokantayhteys
		PreparedStatement statement = null; // sql-lause
		ResultSet resultset = null; // select-lauseen tulostaulu
		ArrayList<Movie> movies = new ArrayList<Movie>(); // tyhjä elokuvalista
		Movie movie = null;
		try {
			// Luodaan yhteys
			connection = Database.getDBConnection();
			// Luodaan komento: haetaan kaikki rivit elokuva-taulusta
			String sqlSelect = "SELECT id, name, director, genre, releaseYear, runtimeMinutes, boxOfficeUsd FROM movie;";
			// Valmistellaan komento:
			statement = connection.prepareStatement(sqlSelect);
			// Lähetetään select-komento suoritettavaksi tietokantapalvelimelle:
			resultset = statement.executeQuery();
			// Käydään tulostaulun rivit läpi
			while (resultset.next()) {
				movie = createMovieObjectFromRow(resultset);
				// lisätään elokuva listaan
				movies.add(movie);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(resultset, statement, connection); // Suljetaan yhteys
		}

		return movies;
	}

	/**
	 * Lukee kyselyn tulostaulusta aktiiviseltä tietoriviltä (yhden elokuvan
	 * tiedot). Luo ja palauttaa tietojen perusteella Movie-tyyppisen olion
	 * 
	 * @param resultset tietokannasta kyselyllä haettu tulostaulu
	 * @return Movie movie-olio
	 */
	private Movie createMovieObjectFromRow(ResultSet resultset) {
		try {
			// Haetaan yhden elokuvan tiedot kyselyn tulostaulun (ResultSet-tyyppinen
			// resultset-olion) aktiiviselta tietoriviltä
			int id = resultset.getInt("id");
			String name = resultset.getString("name");
			String director = resultset.getString("director");
			String genre = resultset.getString("genre");
			int releaseYear = resultset.getInt("releaseYear");
			int runtimeMinutes = resultset.getInt("runtimeMinutes");
			double boxOfficeUsd = resultset.getDouble("boxOfficeUsd");

			// Luodaan ja palautetaan uusi Movie-luokan olio
			return new Movie(id, name, director, genre, releaseYear, runtimeMinutes, boxOfficeUsd);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Lisää elokuvan tiedot tietokantaan
	 * 
	 * @param movie Movie-luokan olio
	 */

	public boolean addMovie(Movie movie) {
		Connection connection = null;
		PreparedStatement stmtInsert = null;
		boolean updateSuccess = false;

		try {
			// Luodaan tietokantayhteys
			connection = Database.getDBConnection();
			// Luodaan komento: luodaan uusi elokuva tietokannan tauluun
			String sqlInsert = "INSERT INTO movie (name, director, genre, releaseYear, runtimeMinutes, boxOfficeUsd) VALUES (?, ?, ?, ?, ?, ?)";
			// Valmistellaan komento:
			stmtInsert = connection.prepareStatement(sqlInsert);
			// Asetetaan parametrisoidun komennon parametrit yksi kerrallaan
			// movie-taulussa id-sarakkeen arvo autom. generoituva, joten ei mukana
			// insertissä
			stmtInsert.setString(1, movie.getName());
			stmtInsert.setString(2, movie.getDirector());
			stmtInsert.setString(3, movie.getGenre());
			stmtInsert.setInt(4, movie.getReleaseYear());
			stmtInsert.setInt(5, movie.getRuntimeMinutes());
			stmtInsert.setDouble(6, movie.getBoxOfficeUsd());
			// Lähetetään INSERT-komento suoritettavaksi tietokantapalvelimelle
			int rowAffected = stmtInsert.executeUpdate();
			if (rowAffected == 1)
				updateSuccess = true;

		} catch (SQLException e) {
			e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(stmtInsert, connection); // Suljetaan statement ja yhteys
		}
		return updateSuccess;
	}

	// poistaa elokuvan ja sen tiedot tietokannasta

	public boolean removeMovie(int movieId) {
		Connection connection = null;
		PreparedStatement stmtDelete = null;
		boolean updateSuccess = false;

		try {
			// Luodaan tietokantayhteys
			connection = Database.getDBConnection();
			// Poistetaan elokuva tietokantasta:
			String sql = "DELETE FROM movie WHERE id = ?";
			stmtDelete = connection.prepareStatement(sql);
			// Asetetaan parametrisoidun delete-komennon parametri
			stmtDelete.setInt(1, movieId);
			// Lähetetään DELETE-komento suoritettavaksi tietokantapalvelimelle
			int rowAffected = stmtDelete.executeUpdate();
			if (rowAffected == 1)
				updateSuccess = true;

		} catch (SQLException e) {
			e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(stmtDelete, connection); // Suljetaan statement ja yhteys
		}
		return updateSuccess;
	}

	public Movie findById(int id) {
		Connection connection = null; // tietokantayhteys
		PreparedStatement statement = null; // sql-lause
		ResultSet resultset = null; // select-lauseen tulostaulu
		Movie movie = null;
		try {
			// Luodaan yhteys
			connection = Database.getDBConnection();
			// Luodaan komento: haetaan elokuva ID:llä
			String sqlSelect = "SELECT id, name, director, genre, releaseYear, runtimeMinutes, boxOfficeUsd FROM movie WHERE id = ?;";
			// Valmistellaan komento:
			statement = connection.prepareStatement(sqlSelect);
			statement.setInt(1, id);
			// Lähetetään select-komento suoritettavaksi tietokantapalvelimelle:
			resultset = statement.executeQuery();
			// Käydään tulostaulun rivi läpi
			if (resultset.next()) {
				movie = createMovieObjectFromRow(resultset);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(resultset, statement, connection); // Suljetaan yhteys
		}

		return movie;
	}

	public boolean editMovie(Movie movie) {
		Connection connection = null; // tietokantayhteys
		PreparedStatement stmtUpdate = null; // sql-lause
		boolean updateSuccess = false;
		try {
			// Luodaan yhteys
			connection = Database.getDBConnection();
			// Luodaan komento: päivitetään elokuvan tiedot
			String sql = "UPDATE movie SET name = ?, director = ?, genre = ?, releaseYear = ?, runtimeMinutes = ?, boxOfficeUsd = ? WHERE id = ?";
			// Valmistellaan komento:
			stmtUpdate = connection.prepareStatement(sql);
			stmtUpdate.setString(1, movie.getName());
			stmtUpdate.setString(2, movie.getDirector());
			stmtUpdate.setString(3, movie.getGenre());
			stmtUpdate.setInt(4, movie.getReleaseYear());
			stmtUpdate.setInt(5, movie.getRuntimeMinutes());
			stmtUpdate.setDouble(6, movie.getBoxOfficeUsd());
			stmtUpdate.setInt(7, movie.getId());
			// Lähetetään päivityskomento suoritettavaksi tietokantapalvelimelle:
			int rowsAffected = stmtUpdate.executeUpdate();
			if (rowsAffected == 1) {
				updateSuccess = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(stmtUpdate, connection); // Suljetaan yhteys
		}
		return updateSuccess;
	}
}
