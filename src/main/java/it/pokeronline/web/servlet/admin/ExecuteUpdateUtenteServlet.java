package it.pokeronline.web.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.dto.UserDTO;
import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.ruolo.RuoloService;
import it.pokeronline.service.user.UserService;

/**
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/admin/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	@Autowired
	private RuoloService ruoloService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteUpdateUtenteServlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String statoUtenteInput = request.getParameter("stato");
		String[] ruoliInInput = request.getParameterValues("listaRuoli");
		List<Long> listaRuoli = new ArrayList<>();
		if (ruoliInInput == null) {
			ruoliInInput = new String[0];
		}
		for (String r : ruoliInInput) {
		
			listaRuoli.add(Long.parseLong(r));

		}
		if (idUser == null || idUser.isEmpty()) {
			request.setAttribute("error", "l'utente selezionato non esiste!!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}

		UserDTO userDTO = new UserDTO(Long.parseLong(idUser), nomeInput, cognomeInput, usernameInput, statoUtenteInput,
				listaRuoli);
		List<String> userErrors = userDTO.erroriUpdate();
		List<String> listaStati = Stream.of(StatoUser.values()).map(Enum::name).collect(Collectors.toList());
		if (!userErrors.isEmpty()) {
			User user =userService.caricaSingoloUserConRuoli(Long.parseLong(idUser));
			request.setAttribute("userAttribute", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.setAttribute("userId", idUser);
			request.setAttribute("listaStati", listaStati);
			request.setAttribute("listaRuoli", ruoloService.listAllRuolo());
			request.setAttribute("isCreato", user.getStato() == StatoUser.CREATO);
			request.getRequestDispatcher("/admin/updateUtente.jsp").forward(request, response);
			return;
		}

		User userInstance = UserDTO.dtoPerUpdate(userDTO);
		userService.aggiorna(userInstance);

		request.setAttribute("successMessage", "Aggiornamento avvenuto!");
		request.setAttribute("listaUser", userService.ListUtentiRuoli());
		request.getRequestDispatcher("/admin/resultRicercaUtente.jsp").forward(request, response);

	}

}
