package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.dto.TavoloDTO;
import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;

/**
 * Servlet implementation class ExecuteInsertTavoloServlet
 */
@WebServlet("/tavolo/ExecuteInsertTavoloServlet")
public class ExecuteInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;
	


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public ExecuteInsertTavoloServlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String esperienzaInput = request.getParameter("esperienza");
		String cifraInput = request.getParameter("cifra");
		String denominazioneInput = request.getParameter("denominazione");
		User utenteInSessione =(User) request.getSession().getAttribute("user");
		TavoloDTO tavoloDTO = new TavoloDTO(esperienzaInput, cifraInput, denominazioneInput);

		List<String> tavoloErrors = tavoloDTO.errors();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/inserisciTavolo.jsp").forward(request, response);
			return;
		}
		
		// se arrivo qui significa che va bene
		Date data = new Date();
		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		tavoloInstance.setUser(utenteInSessione);
		tavoloInstance.setDataCreazione(data);
		tavoloService.inserisciNuovo(tavoloInstance);
          
		// vado in pagina con ok
		request.setAttribute("successMessage", "Inserimento avvenuto con successo");
		request.setAttribute("listaTavolo", tavoloService.listAllTavolo());
		request.getRequestDispatcher("/tavolo/resultSearch.jsp").forward(request, response);

	}

}
