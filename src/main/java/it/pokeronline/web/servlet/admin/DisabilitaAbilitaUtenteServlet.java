package it.pokeronline.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;

/**
 * Servlet implementation class DisabilitaAbilitaUtenteServlet
 */
@WebServlet("/admin/DisabilitaAbilitaUtenteServlet")
public class DisabilitaAbilitaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public DisabilitaAbilitaUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idInput = request.getParameter("idUser");
		User user = userService.caricaSingoloUser(Long.parseLong(idInput));
		if (user.getStato() == StatoUser.ATTIVO) {
			if (user.getTavolo() != null) {
				user.setTavolo(null);
			}
			user.setStato(StatoUser.DISABILITATO);
			userService.aggiorna(user);

			request.setAttribute("successMessage", "L'utente è stato disattivato!");

			request.getRequestDispatcher("/admin/resultRicercaUtente.jsp").forward(request, response);
			return;

		} else if (user.getStato() == StatoUser.DISABILITATO) {
			user.setStato(StatoUser.ATTIVO);
			userService.aggiorna(user);
			request.setAttribute("successMessage", "L'utente è stato riattivato!");

			request.getRequestDispatcher("/admin/resultRicercaUtente.jsp").forward(request, response);
			return;
		}

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
