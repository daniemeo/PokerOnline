package it.pokeronline.service.user;

import java.util.List;

import it.pokeronline.model.user.User;



public interface UserService {
	
	public List<User> listAll();

	public User caricaSingoloUser(Long id);

	public void aggiorna(User userInstance);

	public void inserisciNuovo(User userInstance);

	public void rimuovi(User userInstance);
	
	public List<User> findByExample(User example);
	
	public User eseguiAccesso(String username, String password);

	public User cercaByRuolo( String username, String password) throws Exception;
	
	public List<User> listaByRuolo();
}
