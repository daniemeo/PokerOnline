package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;
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
import it.pokeronline.service.user.UserService;



/**
 * Servlet implementation class ExecuteUpdateTavoloServlet
 */
@WebServlet("/ExecuteUpdateTavoloServlet")
public class ExecuteUpdateTavoloServlet extends HttpServlet {
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
    public ExecuteUpdateTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUser = request.getParameter("idUser");
	String idTavolo = request.getParameter("idTavolo");
	String espMinInput = request.getParameter("esperienza");
	String cifraMinInput = request.getParameter("cifra");
	String denominazioneInput = request.getParameter("denominazione");
	TavoloDTO tavoloDTO = new TavoloDTO(Long.parseLong(idTavolo),espMinInput,cifraMinInput,denominazioneInput);
	
	List<String> tavoloErrors = tavoloDTO.errors();
	if (!tavoloErrors.isEmpty()) {
		request.setAttribute("tavoloAttribute",tavoloDTO);
		request.setAttribute("tavoloErrors", tavoloErrors);
		request.getRequestDispatcher("/tavolo/updateTavolo.jsp").forward(request, response);
		return;
	}
	User user = userService.caricaSingoloUser(Long.parseLong(idUser));
	Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
	tavoloInstance.setUser(user);
	try {
		tavoloService.aggiorna(tavoloInstance);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//vado in pagina con ok
	request.setAttribute("successMessage", "Modifica avvenuta con successo");
	request.setAttribute("listaTavoli", tavoloService.listAllTavolo());
	request.getRequestDispatcher("/tavolo/gestioneTavolo.jsp").forward(request, response);
	}

}
