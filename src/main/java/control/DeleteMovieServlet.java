package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MovieDao;
import database.MovieJdbcDao;

@WebServlet("/poista-elokuva")
public class DeleteMovieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Sijoitetaan muuttujaan pyynnön parametrina tullut poistettavan elokuvan
			// id-arvo
			String idStr = request.getParameter("id");
			int movieId = Integer.parseInt(idStr);
			// Luodaan moviedao
			MovieDao moviedao = new MovieJdbcDao();
			// Poistetaan elokuvan tiedot tietokannasta
			boolean deleteSuccess = moviedao.removeMovie(movieId);
			if (deleteSuccess) {
				// uudelleenohjataan pyyntö /listaa-elokuvat-endpointiin
				response.sendRedirect("/listaa-elokuvat");
			} else {
				request.setAttribute("viesti", "Elokuvan poistossa tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("viesti", "Sovelluksessa tapahtui virhe.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
		}

	}

}