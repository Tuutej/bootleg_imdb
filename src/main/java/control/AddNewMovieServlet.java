package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MovieDao;
import database.MovieJdbcDao;
import model.Movie;

@WebServlet("/lisaa-elokuva")
public class AddNewMovieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/addmovieform.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pyydetään lomakkeella syötetyn movien tiedot request-oliolta

		try {

			String name = request.getParameter("name"); // nimi
			String director = request.getParameter("director"); // ohjaaja
			String genre = request.getParameter("genre"); // genre
			String releaseYearStr = request.getParameter("releaseYear"); // julkaisuvuosi
			int releaseYear = Integer.parseInt(releaseYearStr);
			String runtimeMinutesStr = request.getParameter("runtimeMinutes"); // kesto
			int runtimeMinutes = Integer.parseInt(runtimeMinutesStr);
			String boxOfficeUsdStr = request.getParameter("boxOfficeUsd");// tuotto
			boxOfficeUsdStr = boxOfficeUsdStr.replace(",", ".");
			double boxOfficeUsd = Double.parseDouble(boxOfficeUsdStr);

			// Luodaan uusi Movie-luokan olio edellisillä parametreillä
			Movie newmovie = new Movie(name, director, genre, releaseYear, runtimeMinutes, boxOfficeUsd);
			System.out.println("Movie: " + newmovie.toString());

			// talletaan movien tiedot tietokantaan

			MovieDao moviedao = new MovieJdbcDao();

			boolean additionSuccess = moviedao.addMovie(newmovie);

			if (additionSuccess)
				// ohjataan /listaa-elokuvat endpointtiin .jsp-käsittelyn sijaan

				response.sendRedirect("/listaa-elokuvat");

			else {
				request.setAttribute("viesti", "Elokuvan lisäyksessä tietokantaan tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
			}

		} catch (NumberFormatException e) {

			e.printStackTrace(); // tulostetaan Consoleen virhetilanteessa metodikutsupinoa, josta näkee
									// rivinumeron, jossa Exception tapahtuu

			request.setAttribute("viesti", "Elokuva-lomakkeella syötetyt tiedot eivät olleet kelvolliset.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
		}
	}
}