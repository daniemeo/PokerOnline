package it.pokeronline.web.servlet.admin;

import java.io.IOException;
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
 * Servlet implementation class PrepareUpdateUtenteServlet
 */
@WebServlet("/admin/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
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
    public PrepareUpdateUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUtente = request.getParameter("idUser");
		 List<String> listaStati = Stream.of(StatoUser.values()).map(Enum::name).collect(Collectors.toList());
		boolean isCreato = false;
		User user =userService.caricaSingoloUserConRuoli(Long.parseLong(idUtente));
		
		if(user.getStato() == StatoUser.CREATO) {
			isCreato = true;
		}
		
	  
		request.setAttribute("listaStati", listaStati);
		request.setAttribute("listaRuoli", ruoloService.listAllRuolo());
		request.setAttribute("userId", idUtente);
		request.setAttribute("userAttribute", UserDTO.buildDTOFromModel(user));
		request.setAttribute("isCreato", isCreato);
		request.getRequestDispatcher("/admin/updateUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
