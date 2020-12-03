package it.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;

/**
 * Servlet implementation class PrepareGiocaPartitaSerlvlet
 */
@WebServlet("/PrepareGiocaPartitaSerlvlet")
public class PrepareGiocaPartitaSerlvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;

	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public PrepareGiocaPartitaSerlvlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User utenteInSessione = (User)request.getSession().getAttribute("user");
		utenteInSessione = userService.caricaSingoloUser(utenteInSessione.getId());
		String idTavoloInput = request.getParameter("idTavolo");

		if (utenteInSessione == null || idTavoloInput.isEmpty()) {
			request.setAttribute("errorMessage", "Il tavolo selezionato non esiste!!");
			request.getRequestDispatcher("/partita/resultPartitaSearch.jsp").forward(request, response);
			return;

		} else if (utenteInSessione.getTavolo() != null) {
			if(utenteInSessione.getTavolo().getId() != Long.parseLong(idTavoloInput)) {
				request.setAttribute("errorMessage", "Hai gia una partita in corso, terminala per poterne iniziare una nuova!");
				request.getRequestDispatcher("/partita/resultPartitaSearch.jsp").forward(request, response);
				return;
			}
		} 
		
		Tavolo tavolo = tavoloService.caricaSingoloTavolo(Long.parseLong(idTavoloInput));
		utenteInSessione.setTavolo(tavolo);
		userService.aggiorna(utenteInSessione);
		request.getSession().setAttribute("user", utenteInSessione);
		request.setAttribute("isAlVerde", utenteInSessione.getCreditoAccumulato() == 0);
		
		
		request.setAttribute("success", "Benvenuto giocatore!");
		request.getRequestDispatcher("/partita/gioca.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
