package it.pokeronline.service.ruolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.repository.ruolo.RuoloRepository;

@Component
public class RuoloServiceImpl implements RuoloService{
	@Autowired
	private RuoloRepository ruoloRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<Ruolo> listAllRuolo() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Ruolo caricaSingoloRuolo(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}
	

}
