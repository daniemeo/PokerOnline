package it.pokeronline.web.servlet.partita;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.model.user.User;
import it.pokeronline.service.user.UserService;

/**
 * Servlet implementation class ExecuteCompraCreditoServlet
 */
@WebServlet("/ExecuteCompraCreditoServlet")
public class ExecuteCompraCreditoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public ExecuteCompraCreditoServlet() {
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
		String idUtente = request.getParameter("idUser");
		String cifraInput = request.getParameter("cifra");
		Long id = Long.parseLong(idUtente);
		if(cifraInput == null || cifraInput.isEmpty())  {
			request.setAttribute("errorMessage", "Il valore del campo deve essere positivo e maggiore di zero");
			request.getRequestDispatcher("partita/compraCifra.jsp").forward(request, response);
			return;
		}
		Double cifra = Double.parseDouble(cifraInput);
		
		if(cifra <= 0) {
			request.setAttribute("errorMessage", "Il valore del campo deve essere positivo e maggiore di zero");
			request.getRequestDispatcher("partita/compraCifra.jsp").forward(request, response);
		}
		else {
			User user = userService.caricaSingoloUser(id);
			user.setCreditoAccumulato(user.getCreditoAccumulato() + cifra);
			userService.aggiorna(user);
			
			// vado in pagina con ok
			request.setAttribute("successMessage", "complimenti!!");
			request.getRequestDispatcher("/partita/compraCifra.jsp").forward(request, response);
		}
		
	}

}
