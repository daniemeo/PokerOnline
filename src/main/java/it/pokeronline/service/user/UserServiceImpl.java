package it.pokeronline.service.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.repository.user.UserRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User caricaSingoloUser(Long id) {
		String query = "select u from User u join fetch u.ruoli r where u.id = ?1";
		TypedQuery<User> querynew = entityManager.createQuery(query, User.class);
		querynew.setParameter(1, id).getResultList();
		return userRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(User userInstance) {
		userRepository.save(userInstance);

	}

	@Transactional
	public void inserisciNuovo(User userInstance) {
		userRepository.save(userInstance);

	}

	@Transactional
	public void rimuovi(User userInstance) {
		userRepository.delete(userInstance);

	}

	@Override
	public List<User> findByExample(User example) {
		String query = "select u from User u where u.id = u.id ";

		if (StringUtils.isNotEmpty(example.getNome()))
			query += " and u.nome like '%" + example.getNome() + "%' ";
		if (StringUtils.isNotEmpty(example.getCognome()))
			query += " and u.cognome like '%" + example.getCognome() + "%' ";
		if (StringUtils.isNotEmpty(example.getUsername()))
			query += " and u.username like '%" + example.getUsername() + "%' ";
		if (example.getStato() != null)
			query += " and u.stato = " + example.getStato();

		return entityManager.createQuery(query, User.class).getResultList();
	}

//	@Transactional(readOnly = true)
//	public User (String username, String password) {
//		return userRepository.findByUsernameAndPasswordAndStato(username, password);
//	}
	@Transactional(readOnly = true)
	public User eseguiAccesso(String username, String password) {
		return userRepository.findByUsernameAndPasswordAndStato(username, password, StatoUser.ATTIVO);
	}

	@Override
	public User cercaByRuolo(String username, String password) throws Exception {
		if (username == null || password == null) {
			throw new Exception("Problema valore in input");
		} else {
			TypedQuery<User> query = entityManager
					.createQuery("select u from User u left join fetch u.tavolo join fetch u.ruoli where u.username=?1 and u.password=?2",
							User.class)
					.setParameter(1, username).setParameter(2, password);
			return query.getSingleResult();
		}
	}

	@Override
	public List<User> listaByRuolo() {
		String query = "select u from User u join u.ruoli r where r.codice ='ADMIN_ROLE' or r.codice ='SPECIAL_PLAYER_ROLE' ";
		return entityManager.createQuery(query, User.class).getResultList();
	}

	@Override
	public List<User> ricercaUtente(User utente) {
		String query = "SELECT distinct u FROM User u left join fetch u.ruoli r WHERE 1=1";
		if (utente.getNome() != null && !utente.getNome().isEmpty()) {
			query += " and u.nome = :nome";
		}
		if (utente.getCognome() != null && !utente.getCognome().isEmpty()) {
			query += " and u.cognome = :cognome";
		}
		if (utente.getUsername() != null && !utente.getUsername().isEmpty()) {
			query += " and u.username = :username";
		}
		if (utente.getDataRegistrazione() != null) {
			query += " and u.dataRegistrazione = :dataRegistrazione";
		}
		if (utente.getStato() != null) {
			query += " and u.stato = :stato";
		}
		if (utente.getRuoli() != null && !utente.getRuoli().isEmpty()) {
			query += " and r in (:ruoli)";
		}

		TypedQuery<User> newquery = entityManager.createQuery(query, User.class);
		if (utente.getNome() != null && !utente.getNome().isEmpty()) {
			newquery.setParameter("nome", utente.getNome());
		}
		if (utente.getCognome() != null && !utente.getCognome().isEmpty()) {
			newquery.setParameter("cognome", utente.getCognome());
		}
		if (utente.getUsername() != null && !utente.getUsername().isEmpty()) {
			newquery.setParameter("username", utente.getUsername());
		}
		if (utente.getDataRegistrazione() != null) {
			newquery.setParameter("dataRegistrazione", utente.getDataRegistrazione());
		}
		if (utente.getStato() != null) {
			newquery.setParameter("stato", utente.getStato());
		}
		if (utente.getRuoli() != null && !utente.getRuoli().isEmpty()) {
			newquery.setParameter("ruoli", utente.getRuoli());
		}
		return newquery.getResultList();

	}

	@Override
	public List<User> ListUtentiRuoli() {
		return userRepository.ListUtentiRuoli();
	}

	@Override
	public User caricaSingoloUserConRuoli(Long id) {
		String query = "select u from User u left join fetch u.ruoli r where u.id = ?1";
		TypedQuery<User> querynew = entityManager.createQuery(query, User.class);
		try {
			return querynew.setParameter(1, id).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
