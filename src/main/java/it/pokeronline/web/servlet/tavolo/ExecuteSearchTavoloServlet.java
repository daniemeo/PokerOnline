package it.pokeronline.web.servlet.tavolo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.service.tavolo.TavoloService;
import it.pokeronline.service.user.UserService;



/**
 * Servlet implementation class ExecuteSearchTavoloServlet
 */
@WebServlet("/ExecuteSearchTavoloServlet")
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
		Long esperienzaMinInput =StringUtils.isNumeric(request.getParameter("esperienza"))?Long.parseLong(request.getParameter("esperienza")):null;
		Double cifraMinInput =StringUtils.isNumeric(request.getParameter("cifra"))?Double.parseDouble(request.getParameter("cifra")):null;
		String denominazioneInput = StringUtils.isNotEmpty(request.getParameter("denominazione"))? request.getParameter("denominazione"):null;
		
		
		try {
			
		Date DateInput = StringUtils.isNotEmpty(request.getParameter("data"))? new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")) : null;
		 Tavolo tavolo = new Tavolo(esperienzaMinInput, cifraMinInput, denominazioneInput,DateInput);
		 if( StringUtils.isNotEmpty(request.getParameter("data"))){
			 Date data1 = StringUtils.isNumeric(request.getParameter("data"))? new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")) : null;
			 tavolo.setDataCreazione(data1);
		 }
		 User user = userService.caricaSingoloUser(idUtenteInput);
		 tavolo.setUser(user);
			request.setAttribute("listaTavoli", tavoloService.findByExample(tavolo));
			RequestDispatcher rd = request.getRequestDispatcher("/tavolo/resultSearch.jsp");
			rd.forward(request, response);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		
	}

}
