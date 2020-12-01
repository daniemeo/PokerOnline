package it.pokeronline.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;




@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		
		// TODO inserire i controlli
		
		
		User utenteCheAccede = userService.eseguiAccesso(usernameInput, passwordInput);
		User utente;
		try {
			//se non trovo nulla non deve essere possibile accedere
			if(utenteCheAccede == null) {
				request.setAttribute("errorMessage", "Utente non abilitato");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				return;
			}
			utente = userService.cercaByRuolo(usernameInput, passwordInput);
			
			//metto utente in sessione
			HttpSession session =  request.getSession();
			session.setAttribute("user", utente);
			session.setAttribute("isAdmin", utente.isAdmin());
			session.setAttribute("isSpecialPlayer", utente.isSpecialPlayer());
			session.setAttribute("isPlayer", utente.isplayer());
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

}
