package it.pokeronline.service.ruolo;

import java.util.List;

import it.pokeronline.model.ruolo.Ruolo;

public interface RuoloService {
	
	public List<Ruolo> listAllRuolo();

	public Ruolo caricaSingoloRuolo(Long id);
}
