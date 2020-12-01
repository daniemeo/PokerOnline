package it.pokeronline.web.servlet.partita;

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




/**
 * Servlet implementation class ExecuteSearchPartitaServlet
 */
@WebServlet("/ExecuteSearchPartitaServlet")
public class ExecuteSearchPartitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	@Autowired
	private TavoloService tavoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
    public ExecuteSearchPartitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String denominazioneInput = StringUtils.isNotEmpty(request.getParameter("denominazione"))? request.getParameter("denominazione"):null;
		Double cifraMinInput =StringUtils.isNumeric(request.getParameter("cifra"))?Double.parseDouble(request.getParameter("cifra")):null;
		Long idGiocatoreInput = StringUtils.isNumeric(request.getParameter("idGiocatore"))?Long.parseLong(request.getParameter("idGiocatore")):null;
		Long idCreatoreInput = StringUtils.isNumeric(request.getParameter("idCreatore"))?Long.parseLong(request.getParameter("idCreatore")):null;
		try {
			Date dateInput = StringUtils.isNotEmpty(request.getParameter("data"))? new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")) : null;
			 Tavolo tavolo = new Tavolo(cifraMinInput, denominazioneInput,dateInput);
			 if(idCreatoreInput != null) {
			 User user = new User(idCreatoreInput);
			 tavolo.setUser(user);
			 }
			 if(idGiocatoreInput != null) {
			 User userGio = new User(idGiocatoreInput);
			 tavolo.getUsers().add(userGio);
			 }
			 
				request.setAttribute("listaTavoli", tavoloService.ricercaPerPlayManagment(tavolo));
				RequestDispatcher rd = request.getRequestDispatcher("/partita/resultPartitaSearch.jsp"); // fai la pagina!!!!
				rd.forward(request, response); 
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
