package it.pokeronline.web.servlet.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import it.pokeronline.dto.UserDTO;
import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.service.ruolo.RuoloService;
import it.pokeronline.service.user.UserService;

/**
 * Servlet implementation class ExecuteSearchUtenteServlet
 */
@WebServlet("/admin/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
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
    public ExecuteSearchUtenteServlet() {
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
		String nomeInput =  StringUtils.isNotEmpty(request.getParameter("nome"))? request.getParameter("nome"):null;
		String cognomeInput =  StringUtils.isNotEmpty(request.getParameter("cognome"))? request.getParameter("cognome"):null;
		String usernameInput = StringUtils.isNotEmpty(request.getParameter("username"))? request.getParameter("username"):null;
		String data = request.getParameter("data");
		StatoUser stato = null;
	
		String statoInInput = request.getParameter("stato");
		String ruoloInInput = request.getParameter("idRuolo");
		UserDTO userDTO = new UserDTO(nomeInput, cognomeInput, usernameInput, data);
		List<String> userErrors = userDTO.errorsSearch();
		if (!userErrors.isEmpty()) {
			request.setAttribute("userAttribute", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("/admin/cercaUtente.jsp").forward(request, response);
			return;
		}
		try {
			Date dateInput = StringUtils.isNotEmpty(request.getParameter("data"))? new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")) : null;
			if ( statoInInput != null && !statoInInput.isEmpty()) {
				stato = StatoUser.valueOf(statoInInput);
			}
		//	User userInstance = UserDTO.buildModelFromDto(userDTO);
			User user = new User(nomeInput,cognomeInput,usernameInput,dateInput,stato);
			if(ruoloInInput != null && !ruoloInInput.isEmpty()){
				Ruolo ruolo = ruoloService.caricaSingoloRuolo(Long.parseLong(ruoloInInput));
				 user.getRuoli().add(ruolo);
			}
			
			
			
		   
			request.setAttribute("listaUser", userService.ricercaUtente(user));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/resultRicercaUtente.jsp"); 
			rd.forward(request, response); 
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
	
		
	}

}
