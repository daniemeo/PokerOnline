package it.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pokeronline.model.user.User;

/**
 * Servlet implementation class PrepareGestionePartitaServlet
 */
@WebServlet("/PrepareGestionePartitaServlet")
public class PrepareGestionePartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareGestionePartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userInSessione = (User) request.getSession().getAttribute("user");
		
		if(userInSessione.getTavolo() != null) {
			request.setAttribute("showRicerca", false);
		} else {
			request.setAttribute("showRicerca", true);
		}
		
		request.getRequestDispatcher("/partita/gestionePartita.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
