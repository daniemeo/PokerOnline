package it.pokeronline.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pokeronline.model.user.User;


public class UserDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String expAccumulata;
	private String creditoAccumulato;
	
	public UserDTO() {}
	public UserDTO(String nome, String cognome, String username, String password, String expAccumulata, String creditoAccumulato ) {
		this.nome = nome;
		this.cognome = cognome; 
		this.username = username; 
		this.password = password; 
		this.expAccumulata = expAccumulata;
		this.creditoAccumulato = creditoAccumulato;
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
	
	public List<String> errors(){
		List<String> result = new ArrayList<String>();
		if(StringUtils.isBlank(this.nome))
			result.add("Il campo nome non può essere vuoto");
		if(StringUtils.isBlank(this.cognome))
			result.add("Il campo cognome non può essere vuoto");
		if(StringUtils.isBlank(this.username))
			result.add("Il campo username non può essere vuoto");
		if(StringUtils.isBlank(this.password))
			result.add("Il campo password non può essere vuoto");
		if(StringUtils.isAllBlank(this.expAccumulata))
			result.add("Il campo esperienza Accumulata non può essere vuoto");
		if(StringUtils.isAllBlank(this.creditoAccumulato))
			result.add("il camopo credito accumulato non può essere vuoto");
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
		result.setCreditoAccumulato(Double.parseDouble(userDTO.getCreditoAccumulato()));
		return result;
	}
}
