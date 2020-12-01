package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;

/**
 * Servlet implementation class PrepareUpdateTavoloSerlvlet
 */
@WebServlet("/PrepareUpdateTavoloSerlvlet")
public class PrepareUpdateTavoloSerlvlet extends HttpServlet {
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
    public PrepareUpdateTavoloSerlvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTavoloInput = request.getParameter("idTavolo");
		String idUserInput = request.getParameter("idUser");
		request.setAttribute("userAttribute", userService.caricaSingoloUser(Long.parseLong(idUserInput)));
		request.setAttribute("tavoloAttribute", tavoloService.caricaSingoloTavolo(Long.parseLong(idTavoloInput)));
	
		request.getRequestDispatcher("/tavolo/updateTavolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
