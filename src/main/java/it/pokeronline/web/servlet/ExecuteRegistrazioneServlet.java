package it.pokeronline.web.servlet;

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

import it.pokeronline.dto.UserDTO;
import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;


/**
 * Servlet implementation class ExecuteRegistrazioneServlet
 */
@WebServlet("/ExecuteRegistrazioneServlet")
public class ExecuteRegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Autowired
	private UserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public ExecuteRegistrazioneServlet() {
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
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		String esperienzaInput = request.getParameter("esperienza");
		String creditoInput = request.getParameter("credito");
		
		esperienzaInput = esperienzaInput.isEmpty() ? Long.toString(0) : esperienzaInput;
		creditoInput = creditoInput.isEmpty() ? Integer.toString(0) : creditoInput;
		
		UserDTO userDTO = new UserDTO(nomeInput, cognomeInput, usernameInput,passwordInput, esperienzaInput, creditoInput);
		List<String> userErrors = userDTO.errors();
		if (!userErrors.isEmpty()) {
			request.setAttribute("userAttribute", userDTO);
			request.setAttribute("userErrors", userErrors);
			request.getRequestDispatcher("registrazione.jsp").forward(request, response);
			return;
		}
		User userInstance = UserDTO.buildModelFromDto(userDTO);
		userInstance.setExpAccumulata(0L);
		userInstance.setCreditoAccumulato(0);
		userService.inserisciNuovo(userInstance);
		
		request.setAttribute("successMessage", "Registrazione avvenuta con successo");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
