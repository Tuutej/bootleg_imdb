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

@WebServlet("/muokkaa-elokuvaa")
public class EditMovieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet() method called with request method: " + request.getMethod());
		// Elokuva id paramterina
		int movieId = Integer.parseInt(request.getParameter("id"));

		// Käytä findById() metodia elokuvana hakemiseen tietokannasta
		Movie movie = Movie.findById(movieId);

		request.setAttribute("movie", movie);

		// lähetä pyyntö editmovieform.jsp sivulle
		request.getRequestDispatcher("/WEB-INF/editmovieform.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doPost() method called with request method: " + request.getMethod());

		int movieId = Integer.parseInt(request.getParameter("id"));

		// Printta elokuvan id konsoliin
		System.out.println("Movie ID: " + movieId);

		Movie movie = Movie.findById(movieId);

		if (movie == null) {
			System.out.println("Movie not found with ID: " + movieId);
		} else {
			System.out.println("Movie: " + movie);

			movie.setName(request.getParameter("name"));
			movie.setDirector(request.getParameter("director"));
			movie.setGenre(request.getParameter("genre"));
			movie.setReleaseYear(Integer.parseInt(request.getParameter("releaseYear")));
			movie.setRuntimeMinutes(Integer.parseInt(request.getParameter("runtimeMinutes")));
			movie.setBoxOfficeUsd(Double.parseDouble(request.getParameter("boxOfficeUsd")));

			// tallenetaan muutokset tietokantaan.
			MovieDao moviedao = new MovieJdbcDao();
			boolean editSuccess = moviedao.editMovie(movie);

			if (editSuccess) {

				response.sendRedirect("/listaa-elokuvat");
			} else {
				request.setAttribute("viesti", "Elokuvan muokkauksessa tietokantaan tapahtui virhe.");

				request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
			}
		}
	}
}