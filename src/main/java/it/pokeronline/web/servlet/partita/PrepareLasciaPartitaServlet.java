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
 * Servlet implementation class PrepareLasciaPartitaServlet
 */
@WebServlet("/PrepareLasciaPartitaServlet")
public class PrepareLasciaPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

    public PrepareLasciaPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User utenteInSessione = (User)request.getSession().getAttribute("user");
		utenteInSessione = userService.caricaSingoloUser(utenteInSessione.getId());
		
		if (utenteInSessione.getTavolo() != null) {
			utenteInSessione.setTavolo(null);
		}
		utenteInSessione.setExpAccumulata(utenteInSessione.getExpAccumulata() + 1);
		userService.aggiorna(utenteInSessione);

		request.setAttribute("successMessage", "Sei uscito dal gioco!");

		request.getRequestDispatcher("partita/searchPartita.jsp").forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
