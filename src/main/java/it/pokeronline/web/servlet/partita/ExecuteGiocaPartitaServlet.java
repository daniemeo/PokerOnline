package it.pokeronline.web.servlet.partita;

import java.io.IOException;

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

/**
 * Servlet implementation class PrepareGiocaServlet
 */
@WebServlet("/ExecuteGiocaPartitaServlet")
public class ExecuteGiocaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UserService userService;
    
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public ExecuteGiocaPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Integer creditoNuovo;
		double segno = Math.random();
		Integer somma = (int) (Math.random()*1000);
		Double tot = segno < 0.5 ? -1.0*somma : +1.0*somma;
		creditoNuovo = (int) (user.getCreditoAccumulato() + tot);
		creditoNuovo = creditoNuovo < 0 ? 0 : creditoNuovo;
		user.setCreditoAccumulato(creditoNuovo);
		userService.aggiorna(user);
		request.getSession().setAttribute("user", user);
		if (segno >= 0.5){
			request.setAttribute("successMessage", "Hai vinto!");
		} else {
			request.setAttribute("errorMessage", "Hai perso!");
			request.setAttribute("isAlVerde", creditoNuovo == 0);
		}
		
		request.getRequestDispatcher("/partita/gioca.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
