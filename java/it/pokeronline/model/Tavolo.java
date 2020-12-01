package it.pokeronline.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tavolo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long esperienzaAccumulato;
	private Double creditoAccumulato;
	private String denominazione;

	@Temporal(TemporalType.DATE)
	private Date dataCreazione;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo", orphanRemoval = true)
	private Set<User> users = new HashSet<>();

	public Tavolo(Long esperienzaAccumulato, Double creditoAccumulato, String denominazione, Date dataCreazione) {

		this.esperienzaAccumulato = esperienzaAccumulato;
		this.creditoAccumulato = creditoAccumulato;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getGiocatori() {
		return users;
	}

	public void setGiocatori(Set<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEsperienzaAccumulato() {
		return esperienzaAccumulato;
	}

	public void setEsperienzaAccumulato(Long esperienzaAccumulato) {
		this.esperienzaAccumulato = esperienzaAccumulato;
	}

	public Double getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(Double creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

}
