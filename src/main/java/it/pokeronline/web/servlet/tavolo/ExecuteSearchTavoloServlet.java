package it.pokeronline.web.servlet.tavolo;

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
import it.pokeronline.service.user.UserService;



/**
 * Servlet implementation class ExecuteSearchTavoloServlet
 */
@WebServlet("/tavolo/ExecuteSearchTavoloServlet")
public class ExecuteSearchTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UserService userService;
    
	@Autowired
	private TavoloService tavoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public ExecuteSearchTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idUtenteInput = StringUtils.isNumeric(request.getParameter("idUser"))?Long.parseLong(request.getParameter("idUser")):null;
		String esperienzaMinInput = request.getParameter("esperienza");
		String cifraMinInput = request.getParameter("cifra");
		String dataInput = request.getParameter("data");
		String denominazioneInput = StringUtils.isNotEmpty(request.getParameter("denominazione"))? request.getParameter("denominazione"):null;
		
		TavoloDTO tavoloDTO = new TavoloDTO(esperienzaMinInput, cifraMinInput,denominazioneInput, dataInput);
		
		List<String> tavoloErrors = tavoloDTO.errorSearch();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("userAttribute", userService.caricaSingoloUser(idUtenteInput));
			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/searchTavolo.jsp").forward(request, response);
			return;
		}
		
			
	     Tavolo tavoloInstance = TavoloDTO.buildModelFromDtoPerSearch(tavoloDTO);
		 User user = userService.caricaSingoloUser(idUtenteInput);
		 tavoloInstance.setUser(user);
			request.setAttribute("listaTavoli", tavoloService.findByExample(tavoloInstance));
			RequestDispatcher rd = request.getRequestDispatcher("/tavolo/resultSearch.jsp");
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		
	}

}
