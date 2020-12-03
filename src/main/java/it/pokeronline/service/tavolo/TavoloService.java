package it.pokeronline.service.tavolo;

import java.util.List;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.model.user.User;



public interface TavoloService {
	public List<Tavolo> listAllTavolo();

	public Tavolo caricaSingoloTavolo(Long id);

	public void aggiorna(Tavolo tavoloInstance) throws Exception;

	public void inserisciNuovo(Tavolo tavoloInstance);

	public void rimuovi(Tavolo tavoloInstance) throws Exception;

	public List<Tavolo> findByExample(Tavolo example);

	public List<Tavolo> ricercaPerPlayManagment(Tavolo example, User userInSessione);

}
