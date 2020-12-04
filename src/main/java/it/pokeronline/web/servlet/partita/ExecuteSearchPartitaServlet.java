package it.pokeronline.web.servlet.partita;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.dto.TavoloDTO;
import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;




/**
 * Servlet implementation class ExecuteSearchPartitaServlet
 */
@WebServlet("/ExecuteSearchPartitaServlet")
public class ExecuteSearchPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	@Autowired
	private TavoloService tavoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public ExecuteSearchPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String denominazioneInput = StringUtils.isNotEmpty(request.getParameter("denominazione"))? request.getParameter("denominazione"):null;
		String cifraMinInput = request.getParameter("cifra");
		Long idGiocatoreInput = StringUtils.isNumeric(request.getParameter("idGiocatore"))?Long.parseLong(request.getParameter("idGiocatore")):null;
		Long idCreatoreInput = StringUtils.isNumeric(request.getParameter("idCreatore"))?Long.parseLong(request.getParameter("idCreatore")):null;
		String dateInput = request.getParameter("data");
		boolean search = true;
		    TavoloDTO tavoloDTO = new TavoloDTO(denominazioneInput,dateInput,cifraMinInput,search);
			List<String> tavoloErrors = tavoloDTO.errorSearchPartita();
			if (!tavoloErrors.isEmpty()) {
				request.setAttribute("tavoloAttribute", tavoloDTO);
				request.setAttribute("tavoloErrors", tavoloErrors);
				request.getRequestDispatcher("/tavolo/searchPartita.jsp").forward(request, response);
				return;
			}
			
			Tavolo tavoloInstance = TavoloDTO.buildModelFromDtoPerSearchPartita(tavoloDTO);
			if (idCreatoreInput != null) {
				User user = new User(idCreatoreInput);
				tavoloInstance.setUser(user);
			}
			if (idGiocatoreInput != null) {
				User userGio = new User(idGiocatoreInput);
				tavoloInstance.getUsers().add(userGio);
			}
			User userInSessione = (User) request.getSession().getAttribute("user");

			request.setAttribute("listaTavoli", tavoloService.ricercaPerPlayManagment(tavoloInstance, userInSessione));
			RequestDispatcher rd = request.getRequestDispatcher("/partita/resultPartitaSearch.jsp"); 
			rd.forward(request, response);
			
		
	}

}
