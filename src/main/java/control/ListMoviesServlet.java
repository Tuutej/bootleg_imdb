package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MovieDao;
import database.MovieJdbcDao;
import model.Movie;

@WebServlet("/listaa-elokuvat")
public class ListMoviesServlet extends HttpServlet {

	/**
	 * Lähettää selaimelle elokuvalistan
	 * 
	 * @param request  pyyntö
	 * @param response vastaus
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// haetaan asiakkaat tietokannasta
		MovieDao moviedao = new MovieJdbcDao();
		List<Movie> movies = moviedao.findAll();

		// movies-lista .jsp:n saataville
		request.setAttribute("movies", movies);
		// pyynnön eteenpäinlähetys movielist.jsp:lle
		request.getRequestDispatcher("/WEB-INF/movielist.jsp").forward(request, response);

	}

}
