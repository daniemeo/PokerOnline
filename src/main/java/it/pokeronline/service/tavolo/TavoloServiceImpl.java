package it.pokeronline.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;
import it.pokeronline.repository.tavolo.TavoloRepository;
@Component
public class TavoloServiceImpl implements TavoloService{
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<Tavolo> listAllTavolo() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Transactional
	public Tavolo caricaSingoloTavolo(Long id) {
		String query= "select t from Tavolo t join fetch t.user u left join fetch t.users g where t.id = ?1";
		TypedQuery <Tavolo> querynew = entityManager.createQuery(query, Tavolo.class);
		querynew.setParameter(1, id).getResultList();
		return tavoloRepository.findById(id).orElse(null);
	}
	
	

	@Transactional
	public void aggiorna(Tavolo tavoloInstance) throws Exception  {
		Tavolo tavoloDaDb = (this.caricaSingoloTavolo(tavoloInstance.getId()));
		if(tavoloDaDb.getUsers().size() == 0) {
			tavoloInstance.setDataCreazione(tavoloDaDb.getDataCreazione());
			tavoloRepository.save(tavoloInstance);
		} 
	}

	@Transactional
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
		
	}

	@Transactional
	public void rimuovi(Tavolo tavoloInstance) throws Exception{
		Tavolo tavoloDaDb = this.caricaSingoloTavolo(tavoloInstance.getId());
		if(tavoloDaDb.getUsers().size() == 0) {
		tavoloRepository.delete(tavoloInstance);
		}
	}
	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		String query = "select t from Tavolo t where 1 = 1 ";

		if (example.getCifraMin() != null) {
			query += " and t.cifraMin = :cifraMin";
		}
		if (example.getExpMin() != null) {
			query += " and t.expMin = :expMin ";
		}
		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			query += " and t.denominazione like :denominazione";
		}
		if (example.getDataCreazione() != null ) {
			query += " and t.dataCreazione =  :dataCreazione";
		}
		if(example.getUser() != null) {
			query += " and t.user = :user";
		}
		TypedQuery<Tavolo> tavoloQuery = entityManager.createQuery(query, Tavolo.class);
		
		if (example.getCifraMin() != null) {
			 tavoloQuery.setParameter("cifraMin", example.getCifraMin());
		}
		if(example.getExpMin() != null) {
			tavoloQuery.setParameter("expMin", example.getExpMin());
		}
		if(StringUtils.isNotEmpty(example.getDenominazione())) {
			tavoloQuery.setParameter("denominazione","%" + example.getDenominazione() + "%");
		}
		if (example.getDataCreazione() != null ) {
			tavoloQuery.setParameter("dataCreazione", example.getDataCreazione());
		}
		if(example.getUser() != null) {
			tavoloQuery.setParameter("user", example.getUser());
		}
		 return tavoloQuery.getResultList();
	}
	
	@Override
	public List<Tavolo> ricercaPerPlayManagment(Tavolo example, User userInSessione) {
		String query = "select distinct t from Tavolo t left join fetch t.user u left join t.users g where 1 = 1 ";
		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			query += " and t.denominazione like :denominazione";
		}
		
		if (example.getDataCreazione() != null ) {
			query += " and t.dataCreazione =  :dataCreazione";
		}
		
		if (example.getCifraMin() != null ) {
			query += " and t.cifraMin = :cifraMin";
		}
		
		if(example.getUsers() != null && !example.getUsers().isEmpty()) {
			query += " and g in (:users)";
		}
		
		if(example.getUser() != null) {
			query += " and t.user = :user" ;
		}
		
		if(userInSessione != null) {
			query += " and t.expMin <= :esperienzaMinima";
			query += " and t.cifraMin <= :cifraMinima";
		}
	   
		TypedQuery<Tavolo> tavoloQuery = entityManager.createQuery(query, Tavolo.class);
		
		if (StringUtils.isNotEmpty(example.getDenominazione()))
			tavoloQuery.setParameter("denominazione","%" + example.getDenominazione() + "%");
		
		if (example.getDataCreazione() != null )
			tavoloQuery.setParameter("dataCreazione", example.getDataCreazione());
		
		if (example.getCifraMin() != null)
		   tavoloQuery.setParameter("cifraMin", example.getCifraMin());
		
		if(example.getUsers() != null && !example.getUsers().isEmpty())
			tavoloQuery.setParameter("users", example.getUsers());
		
		if(example.getUser() != null)
			tavoloQuery.setParameter("user", example.getUser());
		
		if(userInSessione != null) {
			tavoloQuery.setParameter("esperienzaMinima", userInSessione.getExpAccumulata());
			tavoloQuery.setParameter("cifraMinima", userInSessione.getCreditoAccumulato());
		}
		
		 return tavoloQuery.getResultList();
	}
	

}
