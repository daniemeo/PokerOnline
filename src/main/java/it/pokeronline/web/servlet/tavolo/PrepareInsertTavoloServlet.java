package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.pokeronline.dto.TavoloDTO;



/**
 * Servlet implementation class PrepareInsertTavoloServlet
 */
@WebServlet("/tavolo/PrepareInsertTavoloServlet")
public class PrepareInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PrepareInsertTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("tavoloAttribute", new TavoloDTO());
		request.getRequestDispatcher("/tavolo/inserisciTavolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
