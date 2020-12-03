package it.pokeronline.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import it.pokeronline.model.ruolo.Ruolo;
import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;
import it.pokeronline.util.Util;

public class UserDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String expAccumulata;
	private String creditoAccumulato;
	private String stato;
	private String dataRegistrazione;
	private List<Long> ruoli;

	public UserDTO() {
	}

	public UserDTO(String nome, String cognome, String username, String password, String expAccumulata,
			String creditoAccumulato) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.expAccumulata = expAccumulata;
		this.creditoAccumulato = creditoAccumulato;
	}

	public UserDTO(Long id, String nome, String cognome, String username, String stato, List<Long> ruoli) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.stato = stato;
		this.ruoli = ruoli;
	}

	public UserDTO(String nome, String cognome, String username, String data) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.dataRegistrazione = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExpAccumulata() {
		return expAccumulata;
	}

	public void setExpAccumulata(String expAccumulata) {
		this.expAccumulata = expAccumulata;
	}

	public String getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(String creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public List<Long> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Long> ruoli) {
		this.ruoli = ruoli;
	}

	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome)) {
			result.add("Il campo nome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.cognome)) {
			result.add("Il campo cognome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.username)) {
			result.add("Il campo username non può essere vuoto");
		}
		if (StringUtils.isBlank(this.password)) {
			result.add("Il campo password non può essere vuoto");
		}
		if (StringUtils.isAllBlank(this.expAccumulata)) {
			result.add("Il campo esperienza Accumulata non può essere vuoto");
		}
		if (StringUtils.isAllBlank(this.creditoAccumulato)) {
			result.add("il camopo credito accumulato non può essere vuoto");
		}
		return result;
	}

	public List<String> erroriUpdate() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome)) {
			result.add("Il campo nome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.cognome)) {
			result.add("il campo cognome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.username)) {
			result.add("il campo username non può essere vuoto");

		}
		if (this.stato == null || StringUtils.isBlank(this.stato)) {
			result.add("il campo stato non può essere vuoto");
		}

		if (this.ruoli == null || this.ruoli.size() == 0) {
			result.add("deve essere selezionato almeno  un ruolo!!");
		}
		return result;
	}

	public static User buildModelFromDto(UserDTO userDTO) {
		User result = new User();
		result.setId(userDTO.getId());
		result.setNome(userDTO.getNome());
		result.setCognome(userDTO.getCognome());
		result.setUsername(userDTO.getUsername());
		result.setPassword(userDTO.getPassword());
		result.setExpAccumulata(Long.parseLong(userDTO.getExpAccumulata()));
		result.setCreditoAccumulato(Integer.parseInt(userDTO.getCreditoAccumulato()));
		return result;
	}

	public static User dtoPerUpdate(UserDTO userDTO) {
		User result = new User();
		result.setId(userDTO.getId());
		result.setNome(userDTO.getNome());
		result.setCognome(userDTO.getCognome());
		result.setUsername(userDTO.getUsername());
		result.setStato(StatoUser.valueOf(userDTO.getStato()));
		Set<Ruolo> listaRuoli = new HashSet<>();
		if (userDTO.getRuoli() != null && userDTO.getRuoli().size() >= 0) {
			for (Long idRuolo : userDTO.getRuoli()) {
				Ruolo ruolo = new Ruolo();
				ruolo.setId(idRuolo);
				listaRuoli.add(ruolo);
			}

			result.setRuoli(listaRuoli);
		}
		return result;
	}
	
	public static UserDTO buildDTOFromModel(User user) {
		UserDTO dto = new UserDTO();
		
		dto.setId(user.getId());
		dto.setNome(user.getNome());
		dto.setCognome(user.getCognome());
		dto.setUsername(user.getUsername());
		dto.setStato(user.getStato().toString());
		List<Long> ruoliIDs = new ArrayList<>();
		if(user.getRuoli() != null && user.getRuoli().size() >=0 ) {
			for(Ruolo ruolo : user.getRuoli()) {
				ruoliIDs.add(ruolo.getId());
			}
		}
		dto.setRuoli(ruoliIDs);
		
		return dto;
	}
	

	public List<String> errorsSearch() {
		List<String> result = new ArrayList<String>();
		if (this.dataRegistrazione != null && !this.dataRegistrazione.isEmpty()) {
			if (!Util.isDate(this.dataRegistrazione)) {
				result.add("il campo data non è valido!!");
			}
		}

		return result;
	}

//	public static User DtoPerRicerca(UserDTO userDTO) {
//		User result = new User();
//		
//		Date date = null; 
//		if(userDTO.getDataRegistrazione() != null ) {
//			try {
//				date = new SimpleDateFormat("yyyy-MM-dd").parse(userDTO.getDataRegistrazione());
//				result.setDataRegistrazione(date);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		//result.setId(userDTO.getId());
//		return result;
//	}

}
